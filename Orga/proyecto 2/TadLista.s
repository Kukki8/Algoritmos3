# Descripcion: Implementa El TDA Lista Enlazada.
# Autor: Jonathan Bautista 16-10109 Yerimar Manzo 14-10611

.data 
lista_size: .word 12
Par_size: .word 8
sep: .asciiz " "
sep2: .asciiz ","
salto_linea: .asciiz "\n"

.text
#.globl main
#main:

################################################
 
# Funcion list_crear
# Descripcion: funcion quq inicializa la lista enlazada con dos funciones del tipo element imprimir/comparar
# Parametros: $a0=fun compara $a1= fun imprimir ambas del tipo elemento
# Retorna: la direccione de la lista inicializada

list_crear:
#prologo
	sw $fp, ($sp) # pone la direccion de fp en el contenido de sp
	move $fp, $sp #mueve el apuntador de fp a $sp
	addiu $sp, $sp, -4 # mueve la posicion del apuntador
#cuerpo de la funcion:
	move $t0, $a0
	li $v0, 9 
	lw $a0, lista_size #reserva tama�o, cantidad de pa�abras del nodo
	syscall		# guarda en $v0 la direccion del nodo
	beqz $a0, lista_crear_error #ve si el espacio reservado es 0 si es salta
	sw $t0, 0($v0) # en la posicion 0 del nodo coloca la funcion compara
	sw $a1, 4($v0) # en la posicion 4 del nodo coloca la funcion imprimir
	sw $0, 8($v0) # coloca el valor de 0
	li $v1, 0 # guarda la direccion
	b lista_crear_fin
lista_crear_error: # carga en $v1 -1 para la salida
	li $v1, -1	
#prologo
lista_crear_fin:
	addi $sp, $sp, 4 #coloca el apuntador sp en una casilla antes
	lw $fp, ($sp) # coloca el contenido de sp en fp
	jr $ra # salta a la linea que va despues de la llamada de esta funcion
######################################################
 
# Funcion list_insertar
# Descripcion: funcion que inserta un elemento del tipo element en la lista
# Parametros: $a0, direccion de la lista $a1, elemento a insertar
# Retorna: $v0 el valor de 0 si se inserta el elemento bien $v1 la direccion de la lista
list_insertar:
#prologo
	sw $fp, ($sp)  # pone la direccion de fp en el contenido de sp
	move $fp, $sp
	addiu $sp, $sp, -4
	sw $ra, ($sp) # guarda el registro $ra que sera usado mas tarde al finalizar la funcion
	addiu $sp, $sp, -4
	move $t7, $a0 #guarda momentaneamente la direccion de la memoria en $t7
#cuerpo de la funcion
	lw $t0, 0($a0) # pone la primera funcion comparacion en $t0
	lw $t1, 4($a0) # pone la primera funcion imprimir en $t1
	lw $t2, 8($a0) # coloca en el registro $t2 el apuntador al siguiente nodo "---> siguiente" o 0 si es el final de la lista
	move $t3, $a0 # guarda el apuntador de la direccion de la lista en $t3
	
loop: 	beqz $t2, list_insertame ### si llega al final de la lista 
	lw $a0, 4($t2) # coloca el valor del nodo actual
	jalr $t0 # salto a la funcion de comparacion
	move $t4, $v0 # resultado lo coloca en $t4
	bgez $t4, list_insertame #si el numero a insertar es menor que el que esta en la posicion
	move $t3, $t2 #mueve el apuntador de $t3 al de $t2
	lw $t2, 8($t3) # mueve el apuntado de $t2 al nodo siguiente que esta en $t3 o lo iguala a 0 en caso de ser el ultimo
	b loop

list_insertame:
	li $v0, 9 ## crea un nuevo nodo 
	lw $a0, lista_size## reservando el tama�o en memoria
	syscall
	beqz $a0, list_insert_error # si el tama�o reservado en el nodo es 0 salta a error
	sw $a1, 4($v0) # inserta el nuevo valor en el nuevo nodo
	sw $t3, 0($v0) # pone el apuntador al previo
	sw $t2, 8($v0) # coloco el apuntador al siguiente nodo
	sw $v0, 8($t3) # pone el apuntador del nodo siguiente a el nodo creado
	li $v0, 0 # carga 0 en $v0 que sera retornado mas adelante
	b list_fin
list_insert_error:
	li $v0, -1	# en caso de error carga -1
#epilogo
list_fin:
	addi $sp, $sp, 4 #coloca el apuntador sp en una casilla antes
	lw $ra, ($sp) # guarda el primer $ra que se encontraba en $ra
	addi $sp, $sp, 4 #sube el apuntador sp en una casilla
	lw $fp ($sp) # carga el apuntador $fp con el contenido de $sp
	move $v1, $t7 #mueve la direccion de la pila en $v1
	jr $ra # salto a la linea despues de la llamada de la funcion
	
###############################################3	
# Funcion list_imprimir
# Descripcion: funcion que imprime cada elemento de la lista
# Parametros: $a0, direccion de la lista
# Retorna: imprime cada elemento -1 en el caso de error sin embargo no se encuentra ningun error posible
list_imprimir:
# prologo
	sw $fp, ($sp)
	move $fp, $sp
	addiu $sp, $sp, -4
	sw $ra, ($sp)
	addiu $sp, $sp, -4
	move $s7, $a0
	#cuerpo de la funcion
	lw $t0, 0($a0)
	lw $t1, 4($a0)
	lw $t2, 8($a0)
	move $t3, $a0
loop2: 	beqz $t2, list_imprimir_fin
	lw $a0, 4($t2) #coloca el elemento en $a0
	jalr $t1 #llama a la funcion imprimir de la lista enlaza
	li $v0, 4 # inicializa para hacer impresion de un entero
	la $a0, sep # imprime un separador del tipo Sring " " para que los elementos no aparezcan pegados
	syscall
	move $t3, $t2 
	lw $t2, 8($t3)
	b loop2
#prologo
list_imprimir_fin:
	addi $sp, $sp, 4
	lw $ra, ($sp)
	addi $sp, $sp, 4
	lw $fp ($sp)
	move $v1, $t7
	jr $ra
##########################################################
# Funcion list_longitud
# Descripcion: funcion que devuelve el tama�o de la lista
# Parametros: $a0, direccion de la lista 
# Retorna: $v0 el tama�o de la lista
list_longitud:
#epilogo
	sw $fp, ($sp)##cuarda el vlaor de fp en sp
	move $fp, $sp# mueve el apuntador fp al de sp
	addiu $sp, $sp, -4 ## baja el tope de la pila
#cuerpo de la funcion
	lw $t0, 0($a0)
	lw $t1, 4($a0)
	lw $t2, 8($a0)
	move $t3, $a0
	li $t5, 0
loop3:	beqz $t2, list_longitud_fin #recorre la list hasta lelgar al final 
	addi $t5, $t5, 1 #suma 1 por cada nodo encontrado
	move $t3, $t2
	lw $t2, 8($t3)
	b loop3
#epilogo
list_longitud_fin:
	addi $sp, $sp, 4
	lw $fp, ($sp)
	move $v0, $t5# mueve el contenido de $t5 en $v0 para el retorno el cual sera el tama�o
	jr $ra
############################################
# Funcion list_obtener
# Descripcion: funcion que obtiene un eleemento dependiendo de la posicion de la lista
# Parametros: $a0, direccion de la lista $a1 la posicion del elemento
# Retorna: $v0 el elemento de dicha posicion
list_obtener:
#prologo
	sw $fp, ($sp)##cuarda el vlaor de fp en sp
	move $fp, $sp# mueve el apuntador fp al de sp
	addiu $sp, $sp, -4 ## baja el tope de la pila
#cuerpo de la funcon
	lw $t0, 0($a0)
	lw $t1, 4($a0)
	lw $t2, 8($a0)
	move $t3, $a0
	li $t5, 0 #carga en $t5 el valor de 0

loop4:	beq $t5, $a1, list_obtener_fin #si $t5 es igual al numero de la posicion salta a fin
	addi $t5, $t5, 1 #suma 1 a $s5
	lw $t4, 4($t2) # guarda el elemento para luego ser retornado dependiendo
	move $t3, $t2
	lw $t2, 8($t3)
	b loop4
#epilogo
list_obtener_fin:
	addi $sp, $sp, 4
	move $v0, $t4
	lw $fp, ($sp)
	jr $ra
############################
# Funcion list_destruir
# Descripcion: funcion que coloca 0 en cada parte de la memoria de lista
# Parametros: $a0, direccion de la lista 
# Retorna: $v0 el valor de 0 si se borra la lista bien
list_destruir:
#prologo
	sw $fp, ($sp)##cuarda el vlaor de fp en sp
	move $fp, $sp# mueve el apuntador fp al de sp
	addiu $sp, $sp, -4 ## baja el tope de la pila
	move $t3, $a0
#cuerpo de la funcion
	li $t1, 0 #carga en $t1 el vlalor de 0
	sw $t1, ($a0) #hace 0 el nodo de la direccion de la pila
	sw $t1, 4($a0)
	lw $t2, 8($a0)
loop5:	beqz $t2, Destruir_fin
	sw $t1, 4($t2)
	sw $t1, 0($t2)### poco a poco va haciendo 0 cada parte de la lista hast alelgar al ultimo que es 0
	move $t3, $t2
	sw $t1, 8($t2)
	lw $t2, 8($t3)
	b loop5
Destruir_fin:
	addi $sp, $sp, 4
	li, $v0, 0
	lw $fp, ($sp)
	jr $ra
