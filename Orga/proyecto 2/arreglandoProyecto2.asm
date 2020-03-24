.data
width: .word 5
height: .word 5
posX: .word 1
posY: .word 0


.text
.globl main
main:

lw $a0, width
lw $a1, height
lw $a2, posX
lw $a3, posY
jal CrearMuros
li $a0, 2
lw $a1, width
jal PseudoNumero
move $a0, $v0

li $v0, 1
syscall

li $v0, 10
syscall
#####################################################
CrearMuros:
#prologo
	li $s0, 35
#cuerpo de la funcion:
CrearMuroSuperior:
	move $s1, $a0
	addi $s1, $s1, 1
	li $t4, 1
loop:	beq $t4, $s1 FinCrearMuroSuperior
	move $t0, $a2
	sll $t1, $t0, 20
	move $t2, $a3
	sll $t3, $t2, 8
	or $t1, $t1, $t3
	ori $s2, $t1, 7	
	sw $s2, 0xffff000c
	sw $s0, 0xffff000c
	addi $t4, $t4, 1
	move $a2, $t4
	b loop
FinCrearMuroSuperior:
	addi $t4, $t4, -1
	move $a2, $t4
	addi $t2, $t2, 1
	move $a3, $t2
CrearMuroDerecho:
	move $s1, $a1
	move $t4, $a3
loop1:	beq $t4, $s1 CrearMuroInferior
	move $t0, $a2
	sll $t1, $t0, 20
	move $t2, $a3
	sll $t3, $t2, 8
	or $t1, $t1, $t3
	ori $s2, $t1, 7	
	sw $s2, 0xffff000c
	sw $s0, 0xffff000c
	
	addi $t4, $t4, 1
	move $a3, $t4
	b loop1
CrearMuroInferior:
	move $t4, $a2 
loop2: 	beqz $t4 FinCrearMuroInferior
 	move $t0, $a2
	sll $t1, $t0, 20
	move $t2, $a3
	sll $t3, $t2, 8
	or $t1, $t1, $t3
	ori $s2, $t1, 7	
	sw $s2, 0xffff000c
	sw $s0, 0xffff000c
	
	addi $t4, $t4, -1
	move $a2, $t4
	b loop2
FinCrearMuroInferior:
	addi $t2, $t2, -1
	move $a3, $t2
	addi $t4, $t4, 1
	move $a2, $t4
CrearMuroIzquierdo:
	move $t4, $a3
loop3:	beqz $t4, FinCrearMuro
	move $t0, $a2
	sll $t1, $t0, 20
	move $t2, $a3
	sll $t3, $t2, 8
	or $t1, $t1, $t3
	ori $s2, $t1, 7	
	sw $s2, 0xffff000c
	sw $s0, 0xffff000c
	addi $t4, $t4, -1
	move $a3, $t4
	b loop3
#epilogo
FinCrearMuro:
	jr $ra
######################################################
PseudoNumero:
#epilogo
sw $fp, ($sp)
move $fp, $sp
addiu $sp, $sp, -4

#Cuepo de la funcion:
li $t0, 3 # numero entre 1 y lo que le pases
li $t1, 3 # numero ente 1 y lo que le pases

addi $a0, $a0, -1

mul $t0, $t0, $a0
add $t1, $t1, $a0

div $t1, $a1
mfhi $v0
#epilogo
finPseudoNumero:
addi $sp, $sp, 4
lw $fp, ($sp)
jr $ra

