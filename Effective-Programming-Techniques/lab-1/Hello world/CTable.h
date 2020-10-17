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
	CTable(CTable& other);
	~CTable();

	void vSetName(string sName);
	bool bSetNewSize(int iTableLen);
	CTable* pcClone();

	int i_getLength();
	string s_getName();



};

