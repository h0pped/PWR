#pragma once


#include "CRefCounter.h"

template <typename T> 
class CMySmartPointer {
public:
	CMySmartPointer(T* pcPointer) {
		pc_pointer = pcPointer;
		pc_counter = new CRefCounter();
		pc_counter->iAdd();
	}
	CMySmartPointer(const CMySmartPointer& pcOther) {
		pc_pointer = pcOther.pc_pointer;
		pc_counter = pcOther.pc_counter;
		pc_counter->iAdd();
	}
	~CMySmartPointer() {
		if (pc_counter->iDec() == 0) {
			cout << "\n\nDELETE POINTER " << *pc_pointer << endl << endl;


			delete pc_pointer;
			delete pc_counter;
		}
	}
	T& operator*() { return(*pc_pointer); }
	T* operator->() { return(pc_pointer); }

	void operator=(const CMySmartPointer& tmp)
	{
		if (pc_counter->iDec() == 0)
		{
			cout << "\n\nDELETE POINTER " << *pc_pointer << endl << endl;
			delete pc_pointer;
			delete pc_counter;
		}
		pc_counter = tmp.pc_counter;
		pc_pointer = tmp.pc_pointer;
		pc_counter->iAdd();
	}
	void printStatus(){
		cout << "Pointer: " << (*this->pc_pointer);
		cout << "\nCount: " << this->pc_counter->iGet();
	}
	
private:
	CRefCounter* pc_counter;
	T* pc_pointer;
};