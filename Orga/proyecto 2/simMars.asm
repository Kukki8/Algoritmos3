.data

#Dimensiones

D: .word 0x10008000 	#Direccion a partir de la cual empieza el simulador
M: .word 32		#Columnas
N: .word 32		#Filas
S: .word 2		#Refrescamiento

#Colores

CPared: .word	0xCCCCFF
SCCabeza: .word	0x006633
SCCola: .word  0x009900
CManzanas: .word 0xFF3333
Negro: .word 0x000000

#Stats

Aumentopuntos: .word 10
SVelocidad: .word 200
Metas: .word 100 , 250 , 500 

#Mensajes

MPerdida: .asciiz "Usted ha perdido... Su puntuacion fue: "
MVictoria: .asciiz "Usted ha ganado!"

#Serpiente

SCabezaX: .word 16
SCabezaY: .word 16
SColaX: .word 16
SColaY: .word 20

#Manzana

ManzanaX: .word
ManzanaY: .word

.text

main:

###################################################
#Seccion lectura cambio de variables por el usuario
#Inicializacion customizada
###################################################

###################################################
#********************Pintando********************#
###################################################

#----------------------x-------------------------#
		#Pintar borde
#----------------------x-------------------------#

PintarIz:
	li $t0, 0		#Iniciamos el contador
	lw $t1, N
PintarIz_loop:
	move $a0, $t0
	li $a1, 0		#La coordenada X no cambiara durante el coloreado, permaneciendo en la primera columna
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, CPared
	jal Colorear
	add $t0, $t0, 1
	
	bne $t0 , $t1, PintarIz_loop
	
	
PintarDer:
	li $t0, 0		#Iniciamos el contador
	lw $t1, N
PintarDer_loop:
	move $a0, $t0
	lw $a1, M
	add $a1, $a1, -1	#Evitamos que se salga del borde
	jal AlinearDireccion	#La coordenada X coincidira con la ultima column y no cambiara durante el coloreado del borde derecho
	move $a0, $v0
	lw $a1, CPared
	jal Colorear
	add $t0, $t0, 1
	
	bne $t0, $t1 , PintarDer_loop
	
		
PintarArr:
	li $t0, 0		#Iniciamos el contador
	lw $t1, M
PintarArr_loop:
	move $a1,$t0
	li $a0 , 0		#La coordenada Y no cambiara durante el coloreado, permaneciendo en la primera fila
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, CPared
	jal Colorear
	add $t0, $t0, 1
	
	bne $t0, $t1 , PintarArr_loop
	
	
PintarAba:
	li $t0, 0		#Iniciamos el contador
	lw $t1, M
PintarAba_loop:
	move $a1,$t0
	lw $a0, N
	add $a0, $a0, -1
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, CPared
	jal Colorear
	add $t0, $t0, 1
	
	bne $t0, $t1, PintarAba_loop
	

#----------------------x-------------------------#
	#Pintar Serpiente en posicion inicial
#----------------------x-------------------------#

PintarCabeza:
	lw $a0, SCabezaY
	lw $a1 ,SCabezaX
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, SCCabeza
	jal Colorear


PintarMedio:					#Ciclo para pintar seccion del medio (3 caracteres)
	li $t0, 1
PintarMedio_loop:
	lw $a0, SCabezaY
	lw $a1, SCabezaX
	add $a0, $a0, $t0
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, SCCola
	jal Colorear
	add $t0, $t0, 1
	bne $t0, 4, PintarMedio_loop
	
	
PintarCola:
	lw $a0, SColaY
	lw $a1, SColaX
	jal AlinearDireccion
	move $a0,$v0
	lw $a1, SCCola
	jal Colorear

#----------------------x-------------------------#
	#Ubicar Manzana en posicion inicial
#----------------------x-------------------------#

UbicarManzana:
	li $v0, 42
	li $a1 ,30			#Limite superior de manera de que no quede en el borde der (O proximamente, en el borde sup)
	addiu $a0, $a0, 1		#Ya que no podemos determinar el limite inferior, debemos asegurar que nunca quede en el borde iz.Ademas, no tomamos en consideracion valores neg
	sw $a0, ManzanaX		#Asignamos el num random a la coordenada X de la manzana
	syscall				#Llamamos nuevamente para generar numero random para la cordenada Y
	addiu $a0, $a0, 1		#Mismo caso anterior, aseguramos que no quede en el borde inferior
	sw $a0, ManzanaY

#----------------------x-------------------------#
	#Pintar Manzana en posicion inicial
#----------------------x-------------------------#
	
PintarManzana:
	lw $a0, ManzanaX
	lw $a1, ManzanaY

	jal AlinearDireccion
	move $a0, $v0
	
	lw $t0, ($a0)			#Chequeamos si colisiona con la serpiente
	bnez $t0, UbicarManzana		#Si la dir dada es diferente a 0, quiere decir que choca con la serpiente (ya que ya nos aseguramos de que no ocurriera con las paredes)
					#Por lo tanto, buscamos una nueva direccion.
	lw $a1, CManzanas
	jal Colorear
	
###################################################
#********************Logica***********************#
###################################################
main_game:
	li $a0, -1					
	li $a1, 0
	jal Mover_accion
	jal Frame
	
	b main_game
	
#1)Chequear los inputs
#2)Actualizar datos(Mover/calcular colisiones) (Y)
#3)Pintar
#4)Sumar puntos/Incremento tamano
#5)Tiempo de espera que genera un frame (Timer)(Y)
#6)Loop (Y)

#----------------------x-------------------------#
FinJuego:
li $v0, 10
syscall
#----------------------x-------------------------#


#----------------------x-------------------------#
		#Movimientos
#----------------------x-------------------------#

Mover_accion:
	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	move $t0, $a0		#Muevo a $t0 la coord en Y a donde voy
	move $t1, $a1		#Muevo a $t1 la coord X a donde voy
	
	
	lw $s0, SCabezaY	#Cargamos en $s0 la coord inicial en Y de la cabeza
	lw $s1, SCabezaX	#Cargamos en $s1 la coord inicial en X de la cabeza
	add $t2, $s0, $t0	#Sumo a la coord Y de la cabeza el cambio a realizar
	add $t3, $s1, $t1	#Sumo a la coord X de la cabeza el cambio a realizar
	sw $t2, SCabezaY	#Guardo la nueva coord Y que tendra la cabeza
	sw $t3, SCabezaX	#Guardo la nueva coord X que tendra la cabeza
	move $a0, $t2
	move $a1, $t3
	
	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	jal AlinearDireccion
	move $a0, $v0
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
	
	addiu $sp, $sp, -12	#Prologo
	sw $fp, 12($sp)
	sw $ra, 8($sp)
	sw $a0, 4($sp)
	addiu $fp , $sp, 12
	
	jal ChequearColisiones
	
	lw $a0, 4($sp)		#Epilogo
	lw $ra, 8($sp)		
	lw $fp, 12($sp)
	addiu $sp, $sp , 12
	
	lw $a1, SCCabeza
	
	addiu $sp, $sp, -20  	#Prologo
	sw $fp, 20($sp)
	sw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	sw $a2, 4($sp)
	addiu $fp , $sp, 20
	
	jal Colorear
	
	lw $a2, 4($sp)		#Epilogo
	lw $a1, 8($sp)	
	lw $a0, 12($sp)
	lw $ra, 16($sp)
	lw $fp, 20($sp)
	addiu $sp , $sp, 20
	
	move $a0, $s0		#Pos antigua de la cabeza en Y
	move $a1, $s1		#Pos antigua de la cabeza en X
	
	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	jal AlinearDireccion
	move $a0, $v0
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
	
	lw $a1, SCCola
	
	addiu $sp, $sp, -20  	#Prologo
	sw $fp, 20($sp)
	sw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	sw $a2, 4($sp)
	addiu $fp , $sp, 20
	
	jal Colorear

	lw $a2, 4($sp)		#Epilogo
	lw $a1, 8($sp)	
	lw $a0, 12($sp)
	lw $ra, 16($sp)
	lw $fp, 20($sp)
	addiu $sp , $sp, 20
	
	lw $a0, SColaY		#Cola original
	lw $a1, SColaX
	move $t2, $a0
	move $t3, $a1		
	add $t4, $a0, $t0	#Calculo la dif entre la coord Y original y la nueva en $t4
	add $t5 , $a1, $t1	#Calculo la dif entre la coord X original y la nueva en $t
	sw $t4, SColaY		#Guardo la nueva coord Y de la cola
	sw $t5, SColaX		#Guardo la nueva coord X de la cola
	
	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	jal AlinearDireccion
	move $a0,$v0
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
	
	lw $a1, Negro
	
	addiu $sp, $sp, -20  	#Prologo
	sw $fp, 20($sp)
	sw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	sw $a2, 4($sp)
	addiu $fp , $sp, 20
	
	jal Colorear

	lw $a2, 4($sp)		#Epilogo
	lw $a1, 8($sp)	
	lw $a0, 12($sp)
	lw $ra, 16($sp)
	lw $fp, 20($sp)
	addiu $sp , $sp, 20
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
	
	jr $ra

#----------------------x-------------------------#
		#Chuequear Colisiones
#----------------------x-------------------------#

ChequearColisiones:

	addiu $sp, $sp, -16	#Prologo
	sw $fp, 16($sp)
	sw $ra, 12($sp)
	sw $s0, 8($sp)
	sw $s1, 4($sp)
	addiu $fp , $sp, 16

	lw $s0, ($a0)					#Cargamos en $t0 el contenido de la direccion a verificar
	beqz $s0, ChequearColisiones_fin		#Verificamos si el espacio a moverse esta vacio
	lw $s1, CManzanas				#Cargamos en $t1 el color de las manzanas.
	beq $s0, $s1, IncrementarPuntuacion		#Verificamos si la cabeza colisiono con una manzana. De ser asi, salta a IncPuntuacion
	lw $s1, SCCola					#Cargamos en $t1 el color de la cola.
	beq $s0, $t1, FinJuego				#Verificamos si la cabeza colisiono con el cuerpo. De ser asi, salta a FinJuego
	lw $s1, CPared					#Cargamos en $t1 el color de  las paredes.
	beq $s0, $s1, FinJuego				#Verificamos si la cabeza colisiono con una pared. De ser asi, salta a FinJuego
	
ChequearColisiones_fin:

	lw $s1, 4($sp)			#Epilogo
	lw $s0, 8($sp)
	lw $ra, 12($sp)
	lw $fp, 16($sp)
	addiu $sp, $sp , 16
	
	jr $ra

#----------------------x-------------------------#
		#Actualizacion
#----------------------x-------------------------#
	
Frame:
	lw $a0, SVelocidad
	li $v0, 32
	syscall
	jr $ra

#----------------------x-------------------------#
		#Funciones de ayuda
#----------------------x-------------------------#
AlinearDireccion:
	lw $v0 , M		#Cargar el ancho de la pantalla en $v0
	mul $v0, $v0, $a0	#Nos movemos por el tablero, desplazandonos casilla por casilla hasta conseguir la fila deseada (Posicion y)
	add $v0, $v0, $a1	#Sumamos la coordenada x, para ubicarnos en la columna deseada (Posicion x)
	mul $v0, $v0, 4		#Finalmente, multiplicamos por 4 para obtener la casilla en cuestion, que cumple con ambas coordenadas
	add $v0, $v0, $gp	#Ahora,guardamos la direccion deseada, tomando a $gp como referencia
	jr $ra
	
	
Colorear:
	sw $a1, ($a0)		#Colocamos el color en el pixel
	jr $ra

IncrementarPuntuacion:
	


