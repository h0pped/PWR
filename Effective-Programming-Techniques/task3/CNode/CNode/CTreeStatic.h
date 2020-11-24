#pragma once
#include <iostream>
#include "�NodeStatic.h"

using namespace std;

class CTreeStatic
{
public:
	CTreeStatic();
	~CTreeStatic();
	CNodeStatic* pcGetRoot() { return(&c_root); }
	void vPrintTree();
	bool bMoveSubTree(CNodeStatic* pcParentNode, CNodeStatic* pcNewChildNode);
private:
	CNodeStatic c_root;
};