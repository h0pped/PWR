
/*

template<typename T> 
CTreeDynamic<T>::CTreeDynamic()
{
	pc_root = new CNodeDynamic();
}
template<typename T>

CTreeDynamic<T>::~CTreeDynamic() {
	//delete pc_root;
}


template<typename T>

void CTreeDynamic<T>::vPrintTree() {
	pc_root->vPrintAllBelow();
}

template<typename T>
bool CTreeDynamic<T>::bMoveSubtree(CNodeDynamic<T>* pcParentNode, CNodeDynamic<T>* pcNewChildNode) {
	if (pcParentNode != NULL && pcNewChildNode != NULL) {
		CNodeDynamic* pc_parent = pcNewChildNode->pcGetParent();
		pcParentNode->vAddNewChild(pcNewChildNode);
		pc_parent->vRemoveChild(pcNewChildNode);
		return true;
	}
	return false;
}
*/