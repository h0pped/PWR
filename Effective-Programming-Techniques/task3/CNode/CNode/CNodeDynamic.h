#pragma once
#include <iostream>
#include <vector>

using namespace std;
class CNodeDynamic
{
public:
	CNodeDynamic() { i_val = 0; };
	~CNodeDynamic();
	void vSetValue(int iNewVal) { i_val = iNewVal; };
	int iGetChildrenNumber() { return(size(v_children)); };
	void vAddNewChild();
	void vAddNewChild(CNodeDynamic* cNode);
	CNodeDynamic* pcGetChild(int iChildOffset);
	
	void vPrint() { cout << " " << i_val; };
	void vPrintAllBelow();

	CNodeDynamic* pcGetParent() {return pc_parent_node;}
	bool operator==(const CNodeDynamic* cNodeDynamic);
	void vRemoveChild(CNodeDynamic* cNode);
private:
	CNodeDynamic* pc_parent_node;
	vector<CNodeDynamic*> v_children;
	int i_val;
};