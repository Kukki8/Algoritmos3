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

###.include "TadLista.s"

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
	li $a1 ,62			#Limite superior de manera de que no quede en el borde der (O proximamente, en el borde sup)
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
	#chequear si colisiona con la serpiente
	
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, CManzanas
	jal Colorear
	

###################################################
#********************Logica***********************#
###################################################
li $a0, 6
jal MoverhaciaArriba

#----------------------x-------------------------#
li $v0, 10
syscall
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



MoverhaciaArriba:
sw $fp, ($sp)
move $fp, $sp
addiu $sp, $sp, -4
# cuerpo de la funcion
	move $t3, $a0
	li $t4, 1
loop1:	beq $t3, $t4  finloop
	lw $a0, SCabezaY
	lw $a1, SCabezaX
	move $a2, $a0
	add $a0, $a0, -1
	sw $a0, SCabezaY
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, SCCabeza
	jal Colorear
	
	move $a0, $a2
	lw $a1, SCabezaX
	jal AlinearDireccion
	move $a0, $v0
	lw $a1, SCCola
	jal Colorear
	
	lw $a0, SColaY
	lw $a1, SColaX
	add $a3, $a0, -1
	sw $a3, SColaY
	jal AlinearDireccion
	move $a0,$v0
	lw $a1, Negro
	jal Colorear
	
	add $t3, $t3, 1
	b loop1
finloop:	
	jr $ra


