#list 2 (factorial) Nykonchuk Illia 245693

#Program shows correct value of factorial up to 13 

.data
 	input:  .asciiz "Enter your num: " 
 	result: .asciiz "\nResult: "
 	overflow: .asciiz "Overflow error"
 
 .text
 	message:
 	li $v0, 4
	la $a0, input
	syscall
	
	getNum: 
	li $v0, 5
	syscall
	move $s0, $v0
	
	IfZero:
	li $s1, 1
	li $s2, 1
	beq $s0, $0, result #if s0 == 0 then print 1
	
	FactorialWhileDo:
	addi $t0, $s0, 1
	li $t1, 1
	whileLoop:
		# While
		addi $t0, $t0, -1 #decrease by 1
		beq $t0, $0, MoveWhile #while variable >0
		
		# Do
		mult $t1, $t0 #multiplication 
		mfhi $t1
		bgtz $t1 error #if we have something in hi, then print overflow error
		mflo $t1 
		j whileLoop
	MoveWhile:
		move $s1, $t1
			
# Factorial do while
FactorialD:
	li $t0, 1
	li $t1, 1
	loopDoWhile:
		# Do
		mult $t0, $t1 #multiplication
		mfhi $t0 #move from hi to t0
		bgtz $t0 error #if we have something in hi, then print overflow error
		
		#if not, then move result of multiplication into lo 
		mflo $t0 
		# While
		beq $t1, $s0, saveDoWhile #if end of loops
		addi $t1, $t1, 1
		j loopDoWhile
	saveDoWhile:
		move $s2, $t0

printResult:
	li $v0, 4 #message
	la $a0, result
	syscall
	
	move $a0, $s1 # result of while do
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, result
	syscall
	
	move $a0, $s2 #  result of do while
	li $v0, 1
	syscall


exit:
	li $v0, 10
	syscall
	
	
error:
	li $v0, 4
	la $a0 overflow
	syscall
	j exit
	
