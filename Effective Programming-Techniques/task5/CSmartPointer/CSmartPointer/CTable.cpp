#include "CTable.h"
#include <iostream>

using namespace std;

const string CTable::DEFAULT_NAME = "default_name";
const int CTable::DEFAULT_SIZE = 10;

CTable::CTable() {
	s_name = DEFAULT_NAME;
	i_tab_length = DEFAULT_SIZE;

	piTab = new int[i_tab_length];
	cout << "without: '" << s_name << "'" << endl;
}
CTable::CTable(string sName, int iTableLen) {
	s_name = sName;
	i_tab_length = iTableLen;

	piTab = new int[i_tab_length];

	cout << "parameter: '" << s_name << "'" << endl;

}
CTable::CTable(const CTable& other) {
	s_name = other.s_name + "_copy";
	i_tab_length = other.i_tab_length;
	piTab = new int[i_tab_length];
	for (int i = 0; i < other.i_tab_length; i++) {
		piTab[i] = other.piTab[i];
	}
	cout << "copy: '" << s_name << endl;
}

CTable::CTable(CTable&& pcOther)
{
	this->s_name = pcOther.s_name + "_copyMove";
	this->i_tab_length= pcOther.i_tab_length;
	this->piTab = pcOther.piTab;

	pcOther.piTab = NULL;

	cout << "\nMove: " << s_name << endl;
}

CTable::~CTable() {
	delete piTab;
	cout << "removing: " << s_name << endl;
}
void CTable::vSetName(string sName) {
	s_name = sName;
}

bool CTable::bSetNewSize(int iTableLen)
{
	if (iTableLen < 0 || iTableLen == i_tab_length)
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

void CTable::vSetValueAt(int iOffset, int iNewVal) {
	if (iOffset >= 0 && iOffset <= i_tab_length) {
		piTab[iOffset] = iNewVal;
	}
	else {
		cout << "Offset is out of bound" << endl;
	}
}
void CTable::vPrint() {
	for (int i = 0; i < i_tab_length; i++) {
		cout << piTab[i] << " ";
	}
	cout << endl;
}
CTable CTable::operator+(CTable& other) {

	CTable third;
	third.bSetNewSize(i_tab_length + other.i_getLength());
	for (int i = 0; i < i_tab_length; i++) {
		third.vSetValueAt(i, piTab[i]);
	}
	for (int i = 0; i < other.i_getLength(); i++) {
		third.vSetValueAt(i_tab_length + i, other.piTab[i]);
	}
	return third;
}
CTable CTable::operator=(CTable&& pcOther) {
	if (this->piTab != NULL)
		delete(this->piTab);

	this->s_name = pcOther.s_name;
	this->i_tab_length = pcOther.i_tab_length;
	this->piTab = pcOther.piTab;

	pcOther.piTab = NULL;
	std::cout << "MOVE op="<<endl;

	return std::move(*this);
}

