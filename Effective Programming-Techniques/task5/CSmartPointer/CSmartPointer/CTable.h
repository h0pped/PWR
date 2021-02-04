#pragma once

using namespace std;
#include <string>
class CTable
{

private:
	string s_name;
	int* piTab;
	int i_tab_length;

public:
	static const string DEFAULT_NAME;
	static const int DEFAULT_SIZE;

	CTable();
	CTable(string sName, int iTableLen);
	CTable(const CTable& other);
	CTable(CTable&& pcOther);
		~CTable();

	void vSetName(string sName);
	bool bSetNewSize(int iTableLen);
	int* getPiTab() { return piTab; };
	CTable* pcClone();

	int i_getLength();
	string s_getName();

	void vSetValueAt(int iOffset, int iNewVal);

	void vPrint();
	CTable operator+(CTable& other);
	CTable operator=(CTable&& pcOther);


};