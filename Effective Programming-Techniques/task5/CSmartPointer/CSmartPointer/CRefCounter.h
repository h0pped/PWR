#pragma once


#include <iostream>
using namespace std;

class CRefCounter
{
public:
	CRefCounter() { i_count = 0; }
	int iAdd() {
		int flag = i_count + 1;
		if (flag > i_count)
			i_counter_now = flag;
		return(++i_count);
	}
	int iDec() { return(--i_count); }
	int iGet() { return(i_count); }
	int iGetCounterNow() { return i_counter_now; }
private:
	int i_count;
	int i_counter_now;
};