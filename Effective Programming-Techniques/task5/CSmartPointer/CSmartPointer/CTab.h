#pragma once
#include <iostream>


#define DEF_TAB_SIZE 10

class CTab
{
public:
	CTab() { pi_tab = new int[DEF_TAB_SIZE]; i_size = DEF_TAB_SIZE; }
	CTab(const CTab& cOther) {
		v_copy(cOther);
	};

	//move
	CTab(CTab&& cOther) {
		pi_tab = cOther.pi_tab;
		i_size = cOther.i_size;

		cOther.pi_tab = NULL;
	};
	CTab operator=(const CTab& cOther) {
		if (pi_tab != NULL) {
			delete pi_tab;
		}
		v_copy(cOther);
		return *this;
	};
	~CTab() {
		if(pi_tab!=NULL)
		delete pi_tab;
	};
	bool bSetSize(int iNewSize) {
		this->i_size = iNewSize;
		if (pi_tab != NULL) {
			delete(this->pi_tab);

		}

		this->pi_tab = new int[this->i_size];
		return true;
	};
	int iGetSize() { return(i_size); }
private:
	void v_copy(const CTab& cOther) {
		pi_tab = new int[cOther.i_size];
		i_size = cOther.i_size;

		for (int i = 0; i< cOther.i_size; i++)
			pi_tab[i] = cOther.pi_tab[i];
	};
	int* pi_tab;
	int i_size;
};