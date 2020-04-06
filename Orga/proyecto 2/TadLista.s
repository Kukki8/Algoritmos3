# Descripcion: Implementa El TDA Lista Enlazada.
# Autor: Jonathan Bautista 16-10109 Yerimar Manzo 14-10611

.text

################################################
 
# Funcion list_crear
# Descripcion: funcion que inicializa la lista enlazada
# Parametros: $a0= direccion inicial, $a1= entero tamano inicial de la lista
# Retorna: la direccione de la lista inicializada

list_crear:

	addiu $sp, $sp, -12	#Prologo 
	sw $fp, 12($sp)
	sw $ra, 8($sp)
	sw $s0, 4($sp)
	addiu $fp , $sp, 12

	move $t0, $a0	#Cargamos en $t0 la direccion inicial de la lista
	move $t1, $a1	#Cargamos en $t1 en tamano de la lista
	li $v0, 9 
	li $a0, 12     	#Reserva espacio del header
	syscall		
	move $s0, $v0	#Guarda en $s0 la direccion del header
	move $a0, $t0	
	
	addiu $sp, $sp, -24    	#Prologo
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	addiu $fp , $sp, 24
	
	jal list_crear_nodo
	
	move $t2, $v0
	lw $t1, 4($sp)		#Epilogo
	lw $t0, 8($sp)
	lw $a1, 12($sp)
	lw $a0, 16($sp)
	lw $ra, 20($sp)
	lw $fp, 24($sp)
	addiu $sp , $sp, 24
	
	sw $t2, ($s0)		#guardo la direccion de la cabeza en la primera pos del header
	li $t3, 1		#Cargamos tamano inicial (solo con header)
	sw $t3, 4($s0) 		#Guardo el tamano de la lista en la primera pos del header
	sw $t2, 8($s0)		#guardo la direccion de la cola en la ultima pos del header
	
	add $t3, $t1, -1
	lw $t4, ($t2)		#Cargamos el valor de la cabeza de la lista (primer nodo)
list_crear_loop:
	beqz $t3, list_crear_fin
	add $t4, $t4, 128		#Valor del siguiente nodo
	
	move $a0, $s0		#Cargamos en $a0 la dir de la lista		
	move $a1, $t4		#Cargamos el valor del nodo nuevo como argumento para list_insertar   

	addiu $sp, $sp, -44    	#Prologo
	sw $fp, 44($sp)
	sw $ra, 40($sp)
	sw $a0, 36($sp)
	sw $a1, 32($sp)
	sw $a2, 28($sp)
	sw $t0, 24($sp)
	sw $t1, 20($sp)
	sw $t2, 16($sp)
	sw $t3, 12($sp)
	sw $t4, 8($sp)
	sw $t5, 4($sp)
	addiu $fp , $sp, 44
	
	jal list_insertar

	lw $t5, 4($sp)		#Epilogo
	lw $t4, 8($sp)
	lw $t3, 12($sp)
	lw $t2, 16($sp)
	lw $t1, 20($sp)
	lw $t0, 24($sp)
	lw $a2, 28($sp)
	lw $a1, 32($sp)
	lw $a0, 36($sp)
	lw $ra, 40($sp)
	lw $fp, 44($sp)
	addiu $sp , $sp, 44
	
	add $t3, $t3, -1
	b list_crear_loop
	
list_crear_fin:
	move $v0, $s0
	
	lw $s0, 4($sp)		#Epilogo
	lw $ra, 8($sp)
	lw $fp, 12($sp)
	addiu $sp, $sp , 12
		
	jr $ra
	
######################################################

# Funcion list_crear_nodo
# Descripcion: funcion que crea un nodo
# Parametros: $a0= valor
# Retorna: la direccion del nodo

list_crear_nodo:
	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	move $t0, $a0
	li $a0, 8
	li $v0, 9
	syscall
	move $t1, $v0		#Obtengo la direccion inicial del espacio reservado (nodo)
	sw $t0 , ($t1)
	move $v0, $t1
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
		
	
	jr $ra


######################################################
 
# Funcion list_insertar
# Descripcion: funcion que inserta un elemento del tipo element en la lista
# Parametros: $a0, direccion de la lista,  $a1= valor elemento a insertar
# Retorna: $v0 el valor de 0 si se inserta el elemento bien, $v1 la direccion de la lista
list_insertar:

	addiu $sp, $sp, -8	#Prologo
	sw $fp, 8($sp)
	sw $ra, 4($sp)
	addiu $fp , $sp, 8
	
	move $t0, $a0		 #Guarda el apuntador de la direccion de la lista en $t0
	move $a0, $a1		#Guardo en $a0, el valor/direccion del elemento a insertar
	
	addiu $sp, $sp, -16    	#Prologo
	sw $fp, 16($sp)
	sw $ra, 12($sp)
	sw $a0, 8($sp)
	sw $t0, 4($sp)
	addiu $fp , $sp, 16
	
	jal list_crear_nodo
	move $t1, $v0		#Movemos a $t1 la dir del nuevo nodo
	
	lw $t0, 4($sp)		#Epilogo
	lw $a0, 8($sp)
	lw $ra, 12($sp)
	lw $fp, 16($sp)
	addiu $sp , $sp, 16
	
	lw $t2, ($t0)		#Cargo en $t2 la direccion del primer nodo
	lw $t3, 4($t2)		#Cargo en $t3 el nodo siguiente 
	
list_insertar_loop: 	
	beqz $t3, list_insertar_final	 #si llega al final de la lista 
	move $t2, $t3
	lw $t3, 4($t2) 			#carga en $t3, el nodo siguiente del siguiente 
	b list_insertar_loop

list_insertar_final:
	sw $t1, 4($t2)
	lw $t4, 4($t0)
	addi $t4, $t4, 1
	sw $t4, 4($t0)
	sw $t1, 8($t0)
	
	lw $ra, 4($sp)		#Epilogo
	lw $fp, 8($sp)
	addiu $sp, $sp , 8
	
	jr $ra 
	

############################
# Funcion list_destruir
# Descripcion: funcion que coloca 0 en cada parte de la memoria de lista
# Parametros: $a0, direccion de la lista 
# Retorna: $v0 el valor de 0 si se borra la lista bien
list_destruir:

	sw $fp, ($sp)		#Prologo
	move $fp, $sp
	addiu $sp, $sp, -4 
	move $t3, $a0

	li $t1, 0 		#carga en $t1 el vlalor de 0
	sw $t1, ($a0) 		#hace 0 el nodo de la direccion de la pila
	sw $t1, 4($a0)
	lw $t2, 8($a0)
list_destruir_loop:	beqz $t2, Destruir_fin
	sw $t1, 4($t2)
	sw $t1, 0($t2)		# poco a poco va haciendo 0 cada parte de la lista hasta llegar al ultimo que es 0
	move $t3, $t2
	sw $t1, 8($t2)
	lw $t2, 8($t3)
	b list_destruir_loop
Destruir_fin:
	addi $sp, $sp, 4	#Epilogo
	li, $v0, 0
	lw $fp, ($sp)
	jr $ra
