.data
	message: .asciiz "zanuda\n:)\n"
	character: .byte 'm'
	integer: .word 22
	float: .float 5.5
	
	double: .double 3.3
	zero_double: .double 0.0
	
	number1: .word 15
	number2: .word 5
	
.text
	#a0-a3 for arguments
	#v0-v1 for results of functions
	

	#1 - integer (lw)
	#2 - float (lwc1 $f12)
	#3 - double (ldc1)
	#4 - string (la)
	#10 - end of program
	
	#print integer
	#li $v0, 1
	#lw $a0, integer 
	
	#print string
	#li $v0, 4
	#la $a0, message
	
	#print float
	#li $v0, 2
	#lwc1 $f12, float
	
	
	#PRINT DOUBLE
	#if we want to store our variable in another place besides $f12 and then print it we need to add it with 0.0 value and store the result in $f12.
	# That's why we use add.d with zero_double.(Cause it takes 3 arguments)
	#ldc1 $f12, double
	#ldc1 $f0, zero_double
	#li $v0, 3
	#add.d $f12, $f2, $f0 
	
	
	#ADD TWO INTEGERS
	##lw $t0, number1($zero)
	##lw $t1, number2($zero)
	##add $t2,$t0,$t1 #add num1 and num2 and store in $t2
	##add $a0,$t2,$zero
	##li $v0,1
	
	
	#SUBSTRACTION
	#lw $s0, number1($zero)
	#lw $s1, number2($zero)
	#sub $t0,$s0,$s1 #t0 = s0-s1	
	#li $v0, 1
	#move $a0,$t0
	
	#MULTIPLICATION MUL
	#addi $s0, $zero, 10 #addi to store data withous .data
	#addi $s1, $zero, 4
	#mul $t0,$s0, $s1
	#li $v0, 1
	#move $a0, $t0
	
	#multiplication mult
	#addi $t0, $zero, 100000
	#addi $t1, $zero, 100
	#mult $t0,$t1 #SAVE TO $HI AND $LOW (IF THE RESULT OF MULTIPLICATION IS TOO BIG
	#mflo $s0 #move from lo to $s0
	#mfhi $s1 #move from hi to $s1
	#Displays the product
	#li $v0, 1
	#add $a0, $zero, $s0
	
	#MULTIPLICATION SLL
	#addi $s0, $zero,4
	#sll $t0, $s0,3 # ssl is always 2 to the power of i (3)
	#li $v0,1
	#add $a0,$t0,$zero
	
	#Dividing and save to register
	#addi $t0, $zero,40
	#addi $t1, $zero,5
	#div $s0,$t0,$t1
	#li $v0, 1
	#add $a0, $s0,$zero
	
	#Dividing and save to hi lo
	#addi $t0, $zero, 30
	#addi $t1, $zero, 8
	#div $t0, $t1 #quotient in lo, reminder in hi
	#mflo $s0 #Quotient
	#mfhi $s1 #Reminder
	#li $v0, 1
	#add $a0, $s1,$zero
	
	main:
		jal displayMessage
		
		addi $a1, $zero, 15
		addi $a2, $zero, 50
		jal addNumbers
		
		li $v0, 1
		add $a0, $v1,$zero
		syscall
		
		#Tell the system that the program is done (to not call next procedures)
		li $v0, 10
		syscall	
	
	displayMessage:
		li $v0,4
		la $a0, message
		syscall
		jr $ra #go back to the function where it was called
	
	addNumbers:
		add $v1,$a1,$a2
		
		jr $ra 
		