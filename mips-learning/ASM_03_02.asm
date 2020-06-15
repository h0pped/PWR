# Nykonchuk Illia 245693
#fixed: main

.data
	.align 4
	array: .space 400 # 100 elements and each element takes 4

	InputSize: .asciiz "Please input the size of the array: "
	InputNums: .asciiz "Please input the integers:\n"
	unsorted: .asciiz "Unsorted: "
	sorted: .asciiz "Sorted: "
	separator: .asciiz ", "
	n: .asciiz "\n"

.text
main:
	#input size of array
	jal inputsize
	jal inputnums
	jal addElements
	jal sort
	
	li $v0, 10
	syscall
	
	
inputsize:
	li $v0, 4
	la $a0, InputSize
	syscall
	li $v0, 5
	syscall
	blt $v0, 1, inputsize
	move $t1, $v0
	
	la $t0, array	 	
	li $t2, 4		
	mul $t1, $t1, $t2
	add $t1, $t1, $t0
	
	jr $ra
	
	inputnums:
	li $v0, 4
	la $a0, InputNums
	syscall
	jr $ra
	
	#read elements function
addElements:
	li $v0, 5
	syscall
	sw $v0, 0($t0)		
	addi $t0, $t0, 4 # next element (int takes 4)
	bne $t0, $t1, addElements
	
	li $v0, 4
	la $a0, n
	syscall
	
	addi $t1, $t1, -4		
	addi $t0, $t0, -4	
	jr $ra
sort:
	addi $t0, $t0, 4
	beq $t0, $t1, printSorted	# if at the end of the array
	#load two elements
	lw $a1, 0($t0)
	lw $a2, 4($t0)
	
	#comparing
	#if a2<a1 then a0 =0
	#if a0=0 then going to next elements
	# if not - swapping a2 and a1
	slt $a0, $a2, $a1
	beq $a0, $0, sort
		
	jal swap
	j sort
	
swap:
	sw $a1, 4($t0)	
	sw $a2, 0($t0)
	
	#reset position
	la $t0, array
	#move to another elem			
	addi $t0, $t0, -4
	la $a0,0
	jr $ra
	
printArray:
	lw $a0, 0($t0)
	addi $t0, $t0, 4
	li $v0, 1
	syscall
	bne $t0, $t1, Separator
	jr $ra
	
Separator:
	li $v0, 4
	la $a0, separator
	syscall
	j printArray
	jr $ra
	
printSorted:		
	la $t0, array
	addi $t1, $t1, 4
	li $v0, 4
	la $a0, sorted
	syscall
	jal printArray
	jal end

	
end:
	li $v0, 10
	syscall