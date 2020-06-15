#  Nykonchuk Illia 245693 TASK 4

#Please write a program that asks to enter a string and then checks if the string is a palindrome or not.
#If  the string is a palindrome the program should output "This is a palindrome" and in other case it should output "This is not a palindrome".

.data

input_str: .asciiz "Enter your string: "
palindrome_str: .asciiz "\nThis is a Palindrome!"
not_palindrome_str: .asciiz "\nThis is not a Palindrome!"
space: .space 32

.text
	main:
		jal inputString
		jal stringLength
		jal palindromeCheck
		
		j exit
		
	inputString:
		li $v0, 4
		la $a0, input_str
		syscall
		
		li $v0, 8
		la $a0, space
		li $a1, 32
		syscall
		move $s0, $a0 
		
		jr $ra
		
		
	stringLength:
	#t2 stores length
		move $t0, $s0
		
		loop:
			lb $t1, ($t0)		
			addi $t0, $t0, 1
			beqz $t1, return
			addi $t2, $t2, 1
			j loop
			
			move $s1, $t2
			addi $s1, $s1, -1
			jr $ra
			
			
	palindromeCheck:
		move $t0, $s0
		move $t1, $s0
		li $t2, 0
		#t1 to the end of str to check it with t0 which is beginning of str
		toEnd:
			addi $t1, $t1, 1
			addi $t2, $t2, 1
			bne $t2, $s1 toEnd
			addi $t1, $t1, -1	
		PalindromeLoop:
			lb $t2, ($t0)		
			lb $t3, ($t1)		
			bne $t2, $t3, notPalindrome	# different - is not palindrome
			slt $t2, $t0, $t1
			beqz $t2, Palindrome	# if end  
			addi $t0, $t0, 1	# begining+1
			addi $t1, $t1, -1	# end-1
			j PalindromeLoop
	
		notPalindrome:
			la $a0, not_palindrome_str
			li $v0, 4
			syscall
			jr $ra
		
		Palindrome:
			la $a0, palindrome_str
			li $v0, 4
			syscall
			jr $ra
			
			
	exit:
		li $v0 10
		syscall