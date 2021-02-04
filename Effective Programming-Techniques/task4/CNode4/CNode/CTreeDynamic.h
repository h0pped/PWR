#include "CNodeDynamic.h"

template< typename T > class CTreeDynamic
{
public:
	CTreeDynamic() {
		pc_root = new CNodeDynamic<T>();
	};
	~CTreeDynamic() {
		//delete pc_root;
	};
	CNodeDynamic<T>* pcGetRoot() { return(pc_root); }
	void vPrintTree() {
		pc_root->vPrintAllBelow();
	};
	bool bMoveSubtree(CNodeDynamic<T>* pcParentNode, CNodeDynamic<T>* pcNewChildNode) {
		if (pcParentNode != NULL && pcNewChildNode != NULL) {
			CNodeDynamic<T>* pc_parent = pcNewChildNode->pcGetParent();
			pcParentNode->vAddNewChild(pcNewChildNode);
			pc_parent->vRemoveChild(pcNewChildNode);
			return true;
		}
		return false;
	};
private:
	CNodeDynamic<T>* pc_root;
};