# Descripcion: Ejemplo de un Tipo que implenta un Element
# Autor: Jonathan bautista 16-10109 Yerimar Manzo 14-10611
# Fecha:21/02/2020

	.data
Par_neutro: .word 0,0
Par_size: .word 8
sep: .asciiz ","
	.text
# Funcion Par_Crear
# Descripcion: 
# Parametros:
# Retorna:
.globl main
main:
	li $a0, 5
	li $a1, 10
	jal Par_Crear
	move $t1, $v0
	li $a0, 5
	li $a1, 10
	jal Par_Crear
	move $a0, $v0
	move $a1, $t1
	jal Par_Compara
	
	move $a0, $v0
	li $v0, 1
	syscall
	
	li $v0,10
	syscall
	




Par_Crear:
	# prologo
	sw $fp, ($sp)
	move $fp, $sp
	addiu $sp, $sp, -4
	move $t0, $a0
	# cuerpo de la funcionS
	li $v0, 9
	lw $a0, Par_size
	syscall
	beqz $a0, Par_error
	sw $t0, ($v0)
	sw $a1, 4($v0)
	b Par_salir
Par_error: li $v0, -1
	# epilogo
Par_salir: 
	addi $sp, $sp, 4
	lw $fp, ($sp)
	jr $ra
	
	

# Funcion HashCode
# Descripcion: 
# Parametros:
# Retorna:

Par_HashCode:
	# prologo
	sw $fp, ($sp)
	move $fp, $sp
	addiu $sp, $sp, -4
	# cuerpo de la funcionS
	lw $t0, ($a0)
	lw $t1, 4($a0)
	add $t0, $t0, $t1
	li $t1, 2
	div $t0, $t1
	mfhi $v0
	b fin_hashcode
	# epilogo
fin_hashcode:	
	addi $sp, $sp, 4
	lw $fp, ($sp)
	jr $ra



# Funcion Par_Compara
# Descripcion: 
# Parametros:
# Retorna:

Par_Compara:
	# prologo
	sw $fp, ($sp)
	move $fp, $sp
	addiu $sp, $sp, -4
	# cuerpo de la funcionS
	lw $t0, ($a0)
	lw $t1, 4($a0)
	lw $t2, ($a1)
	lw $t3, 4($a1)
	add $t0, $t0, $t1
	add $t1, $t2, $t3
	blt $t0, $t1, Menor
	beq $t0, $t1, Igual
	bgt $t0, $t1, Mayor
Menor:	li $v0, -1
	b fin
Igual: 	li $v0, 0
	b fin
Mayor: li $v0, 1
	b fin
	# epilogo
fin: 	addi $sp, $sp, 4
	lw $fp, ($sp)
	jr $ra


# Funcion Par_Imprimir
# Descripcion: 
# Parametros:
# Retorna:

Par_Imprimir:
	# prologo
	sw $fp, ($sp)
	move $fp, $sp
	addiu $sp, $sp, -4
	# cuerpo de la funcionS
	lw $t0, ($a0)
	lw $t1, 4($a0)
	la $t2, sep
	
	li $v0, 1
	move $a0, $t0
	syscall
	li $v0, 4
	move $a0, $t2
	syscall
	li $v0, 1
	move $a0, $t1
	syscall
	b fin_imprimir
	

	# epilogo
fin_imprimir:
	addi $sp, $sp, 4
	lw $fp, ($sp)
	jr $ra


# Ademas, habrá otras funciones de acuerdo al tipo


