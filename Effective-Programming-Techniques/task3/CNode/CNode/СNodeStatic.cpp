#include "ÑNodeStatic.h"
#include <iostream>
using namespace std;


CNodeStatic* CNodeStatic::pcGetChild(int iChildOffset)
{
	return (iChildOffset >= v_children.size() || iChildOffset < 0)
		? NULL
		: &v_children[iChildOffset];
}
void CNodeStatic::vAddNewChild()
{
	CNodeStatic child;
	child.pc_parent_node = this;

	v_children.push_back(child);
}
void CNodeStatic::vAddNewChild(CNodeStatic&child)
{
	child.pc_parent_node = this;

	v_children.push_back(child);
}
void CNodeStatic::vRemoveChild(CNodeStatic& cNode)
{
	cNode.pc_parent_node = NULL;
	int iChildPosition = iGetChildPosition(cNode);
	v_children.erase(v_children.begin() + iChildPosition);
}
void CNodeStatic::vPrintAllBelow()
{
	vPrint();
	for (int ii = 0; ii < size(v_children); ii++)
	{

		v_children[ii].vPrintAllBelow();
	}
}
void CNodeStatic::vPrintUp()
{
	vPrint();
	if (pc_parent_node != NULL) pc_parent_node->vPrintUp();}
CNodeStatic::~CNodeStatic() {

}


