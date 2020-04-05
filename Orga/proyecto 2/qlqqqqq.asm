.data
.text
main:
	charloop:
		jal getChar
		addi $a0, $v0, 0
		jal putChar
		j charloop
li $v0, 10
syscall 
getChar:
	lui $a3, 0xffff
	ckReady:
		lw $t1, 0($a3)
		andi $t1, $t1, 0x1
		beqz $t1, ckReady
		lw $v0, 4($a3)
		jr $ra
putChar:
	lui $a3, 0xffff
	xReady:
		lw $t1, 8($a3)
		andi $t1, $t1, 0x1
		beqz $t1, xReady
		sw $a0, 12($a3)
		jr $ra
