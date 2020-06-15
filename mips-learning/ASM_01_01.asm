#list 1 (polynomial) Nykonchuk Illia 245693

.data
	strpolynomial: .asciiz "y = ax^3+bx^2+cx+d\n\n"
	
	
	stra: .asciiz "Enter a:"
	strb: .asciiz "Enter b:"
	strc: .asciiz "Enter c:"
	strd: .asciiz "Enter d:"
	strx: .asciiz "Enter x:"
	
	stra2: .asciiz "a:"
	strb2: .asciiz "\nb:"
	strc2: .asciiz "\nc:"
	strd2: .asciiz "\nd:"
	strx2: .asciiz "\nx:"
	
	separator: .asciiz "\n--------------------------------\n"
	n: .asciiz "\n"
	space: .asciiz " "
	powx: .asciiz "\nx^"
	result: .asciiz "Result is: "

.text
	main:
	li $v0, 4
	la $a0, strpolynomial
	syscall
	
	jal EnterValues
	jal PrintSeparator
	
	jal PrintValues
	
	#find x^3
	add $a1, $zero, $t5
	addi $a2, $zero, 3
	
	jal Pow
	add $s1, $s0, $zero
	
	#find x^2
	addi $a2, $zero, 2
	jal Pow
	add $s2, $s0, $zero
	
	#x^3 stored in s1
	#x^3 stored in s2
	
	#A*X^3 STORED IN S3
	#B*X^2 STORED IN S4
	#C*X STORED IN S5
	li $v0, 4
	
	la $a0, separator 
	syscall
	
	mul $s3, $t1, $s1 #ax^3
	mul $s4, $t2, $s2 #bx^2
	mul $s5, $t3, $t5 #cx
	
	add $s6, $s3, $s4 # s6 = ax^3 + bx^2
	add $s6, $s6, $s5 #s6 = s6 + cx
	add $s6, $s6, $t4
	
	li $v0 4
	la $a0, result
	syscall
	li $v0 1
	add $a0, $zero, $s6
	syscall
	 li $v0,10
	 syscall 
	
	
	EnterValues:
	
	#a
	li $v0,4
	la $a0,stra
	syscall
	li $v0,5
	syscall
	add $t1, $zero, $v0
	
	#b
	li $v0,4
	la $a0,strb
	syscall
	li $v0,5
	syscall
	add $t2, $zero, $v0
	
	#c
	li $v0,4
	la $a0,strc
	syscall
	li $v0,5
	syscall
	add $t3, $zero, $v0
	
	#d
	li $v0,4
	la $a0,strd
	syscall
	li $v0,5
	syscall
	add $t4, $zero, $v0
	#x
	li $v0,4
	la $a0,strx
	syscall
	li $v0,5
	syscall
	add $t5, $zero, $v0
	jr $ra
	
	PrintValues:
	
	
	#a
	li $v0,4
	la $a0, stra2
	syscall
	li $v0,1
	add $a0, $t1, $zero
	syscall
	
	#b
	li $v0,4
	la $a0, strb2
	syscall
	li $v0,1
	add $a0, $t2, $zero
	syscall
	
	#c
	li $v0,4
	la $a0, strc2
	syscall
	li $v0,1
	add $a0, $t3, $zero
	syscall
	
	#d
	li $v0,4
	la $a0, strd2
	syscall
	li $v0,1
	add $a0, $t4, $zero
	syscall
	
	#x
	li $v0,4
	la $a0, strx2
	syscall
	li $v0,1
	add $a0, $t5, $zero
	syscall
	jr $ra
	
	PrintSeparator:
	li $v0,4
	la $a0, separator
	syscall
	jr $ra
	
	Pow: 
	#li $v0,4
	#la $a0,pow
	#syscall
	
	#li $v0, 1
	#add $a0, $zero, $a1
	#syscall
	#li $v0, 1
	#add $a0, $zero, $a2
	addi $t0, $zero, 2
	add $s0, $zero, $a1
	while:
		bgt $t0,$a2,exit	
		#li $v0,4
		#la $a0,pow
		#syscall
		mul $s0, $s0,$a1
		addi $t0,$t0,1
		j while
		
	exit:
		li $v0, 4
		la $a0, powx
		syscall
		li $v0, 1
		add $a0 $zero, $a2
		syscall
		li $v0, 4
		la $a0, space
		syscall 
		li $v0,1
		add $a0,$s0,$zero
		syscall
		jr $ra
		syscall
	
	jr $ra
	
	
