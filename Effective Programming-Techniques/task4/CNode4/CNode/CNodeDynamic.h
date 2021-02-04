#pragma once
#include <iostream>
#include <vector>

using namespace std;
template< typename T >class CNodeDynamic
{
public:
	CNodeDynamic() {};
	~CNodeDynamic() {};
	void vSetValue(T iNewVal) { i_val = iNewVal; } 
	int iGetChildrenNumber() { return(size(v_children)); } 
	void vAddNewChild() {
		CNodeDynamic<T>* child = new CNodeDynamic;
		child->pc_parent_node = this;
		v_children.push_back(child);
	};
	void vAddNewChild(CNodeDynamic<T>* cNode) {
		cNode->pc_parent_node = this;
		v_children.push_back(cNode);
	};
	CNodeDynamic<T>* pcGetChild(int iChildOffset) {

		if (iChildOffset >= size(v_children)) return NULL;
		else return v_children[iChildOffset];
	};
	
	void vPrint() { cout << " " << i_val; };
	void vPrintAllBelow() {
		vPrint();
		for (int ii = 0; ii < size(v_children); ii++)
		{
			v_children[ii]->vPrintAllBelow();
		}
	};

	CNodeDynamic<T>* pcGetParent() { return pc_parent_node; } 
	bool operator==(const CNodeDynamic<T>* cNodeDynamic) {
		return cNodeDynamic->i_val == i_val;
	};
	void vRemoveChild(CNodeDynamic* cNode)
	{
		cNode->pc_parent_node = NULL;
		int iPosition = iGetChildPosition(cNode);
		v_children.erase(v_children.begin() + iPosition);
	};
	int iGetChildPosition(const CNodeDynamic<T>* cNode)
	{
		typename vector<CNodeDynamic<T>*>::iterator it = find(v_children.begin(), v_children.end(), cNode);
		if (it != v_children.end())
			return distance(v_children.begin(), it);
		return -1;
	};
	int countOfElements(T val) {
		int count = 0;
		for (int i = 0; i < iGetChildrenNumber(); i++) {
			CNodeDynamic<T>* children = v_children.at(i);
			count += children->countOfElements(val);
		}
		if (i_val == val) { 
			return count += 1;
		}

		return count;
	}

private:
	CNodeDynamic<T>* pc_parent_node;
	vector<CNodeDynamic<T>*> v_children;
	T i_val;
};