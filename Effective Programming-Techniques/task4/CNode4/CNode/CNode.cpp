

#include <iostream>
#include "СNodeStatic.h"
#include "CTreeStatic.h"
#include "CTreeDynamic.h"
using namespace std;



int main(){

	cout << "DOUBLE TREE\n\n";

	CTreeDynamic<double> tree;
	tree.pcGetRoot()->vSetValue(1.2);
	tree.pcGetRoot()->vAddNewChild();
	tree.pcGetRoot()->vAddNewChild();
	tree.pcGetRoot()->pcGetChild(0)->vSetValue(5.1);
	tree.pcGetRoot()->pcGetChild(0)->vAddNewChild();
	tree.pcGetRoot()->pcGetChild(0)->vAddNewChild();
	tree.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue(2.2);
	tree.pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue(2.4);
	tree.pcGetRoot()->pcGetChild(1)->vSetValue(12.7);
	tree.vPrintTree();
		
	cout << "\n\nSTRING TREE\n\n";
	CTreeDynamic<string> tree1;
	tree1.pcGetRoot()->vSetValue("root");
	tree1.pcGetRoot()->vAddNewChild();
	tree1.pcGetRoot()->vAddNewChild();
	tree1.pcGetRoot()->pcGetChild(0)->vSetValue("   root_child0   ");
	tree1.pcGetRoot()->pcGetChild(0)->vAddNewChild();
	tree1.pcGetRoot()->pcGetChild(0)->vAddNewChild();
	tree1.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vSetValue("   root_child0_child0   ");
	tree1.pcGetRoot()->pcGetChild(0)->pcGetChild(1)->vSetValue("   root_child0_child1   ");
	tree1.pcGetRoot()->pcGetChild(1)->vSetValue("   root_child1   ");
	tree1.vPrintTree();

	cout << "\n\nCount of 'root_child0': " << tree1.pcGetRoot()->countOfElements("   root_child0   ") << endl;
	return 0;
}