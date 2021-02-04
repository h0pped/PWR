#include "CNodeDynamic.h"

CNodeDynamic::~CNodeDynamic()
{
	//for (int i = 0; i < size(v_children)-1; ++i) {
	//	cout << "\nDeleting " << pcGetChild(i)->i_val;
	//	delete pcGetChild(i);
	//}
}


void CNodeDynamic::vAddNewChild()
{
	CNodeDynamic* child = new CNodeDynamic;
	child->pc_parent_node = this;
	v_children.push_back(child);

}

void CNodeDynamic::vAddNewChild(CNodeDynamic* child)
{
	child->pc_parent_node = this;
	v_children.push_back(child);
}

CNodeDynamic* CNodeDynamic::pcGetChild(int iChildOffset)
{

	if (iChildOffset >= size(v_children)) return NULL;
	else return v_children[iChildOffset];
}

void CNodeDynamic::vPrintAllBelow()
{
	vPrint();
	for (int ii = 0; ii < size(v_children); ii++)
	{
		v_children[ii]->vPrintAllBelow();
	}
}

bool CNodeDynamic::operator==(const CNodeDynamic* cNodeDynamic)
{
	return cNodeDynamic->i_val == i_val;
}

void CNodeDynamic::vRemoveChild(CNodeDynamic* cNode)
{
}
