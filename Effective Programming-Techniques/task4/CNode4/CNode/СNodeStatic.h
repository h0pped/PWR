#pragma once
#pragma once
#include "iostream"
#include <vector>

using namespace std;

class CNodeStatic {
private:
	int i_val;
	std::vector<CNodeStatic> v_children;
	CNodeStatic* pc_parent_node;
public:
	CNodeStatic() { i_val = 0;pc_parent_node = NULL; };
	~CNodeStatic();
	void vSetValue(int iNewVal) { i_val = iNewVal; };
	int iGetChildrenNumber() { return(v_children.size()); };
	int iGetChildPosition(CNodeStatic&cNode) {
		vector<CNodeStatic>::iterator it = find(v_children.begin(), v_children.end(), cNode);
		return distance(v_children.begin(), it);
	};
	void vAddNewChild();
	void vAddNewChild(CNodeStatic& child);
	void vRemoveChild(CNodeStatic& cNode);
	CNodeStatic* pcGetChild(int iChildOffset);
	CNodeStatic* pcGetParent() { return pc_parent_node; };

	void vPrint() { cout << " " << i_val; };
	void vPrintUp();
	void vPrintAllBelow();

	bool operator==(const CNodeStatic& cNodeStatic) {
		return i_val == cNodeStatic.i_val;
	}
	


};