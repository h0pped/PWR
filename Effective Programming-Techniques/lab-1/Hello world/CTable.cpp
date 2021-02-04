#include "CTable.h"
#include <iostream>

using namespace std;

const string CTable::DEFAULT_NAME = "default_name";
const int CTable::DEFAULT_SIZE = 10;

CTable::CTable() {
	s_name = DEFAULT_NAME;
	i_tab_length = DEFAULT_SIZE;

	piTab = new int[i_tab_length];
	cout << "without: '" << s_name <<"'"<< endl;
}
CTable::CTable(string sName, int iTableLen) {
	s_name = sName;
	i_tab_length = iTableLen;

	piTab = new int[i_tab_length];

	cout << "parameter: '" << s_name << "'" << endl;

}
CTable::CTable(CTable& other) {
	s_name = other.s_name+"_copy";
	i_tab_length = other.i_tab_length;
	piTab = new int[i_tab_length];
	for (int i = 0;i < other.i_tab_length;i++) {
		piTab[i] = other.piTab[i];
	}
	cout << "copy: '" << s_name<<"'"<<endl;
}
CTable::~CTable(){
	delete[] piTab;
	cout << "removing: " << s_name << endl;
}
void CTable::vSetName(string sName){
	s_name = sName;
}

bool CTable::bSetNewSize(int iTableLen)
{
	if (iTableLen < 0||iTableLen==i_tab_length)
		return false;
	else
	{
		i_tab_length = iTableLen;
		delete[]piTab;
		piTab = new int[i_tab_length];
		return true;
	}

}

CTable* CTable::pcClone() {
	return new CTable(*this);
}


int CTable::i_getLength() {
	return i_tab_length;
}

string CTable::s_getName()
{
	return s_name;
}


