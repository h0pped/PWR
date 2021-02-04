#include <iostream>
#include "CTable.h"
using namespace std;


//1
void v_alloc_table_add_5(int iSize)
{
    if (iSize > 0) {
    
        int* myarr = new int[iSize]; //creating a pointer and pointing it to the new array

        for (int i = 0; i < iSize; i++) //initializing the data
        {
            myarr[i] = i+5;
        }
         cout << "Elements are: ";

        for (int *i = myarr; i!=myarr+iSize ; i++) //prinring the data
        {
           cout << *i<< " ";
        }
        cout << endl;

        delete[] myarr; //clearing the data to have no memory leaks
    }
    else {
        cout << "error size" << endl;
    }
}


//2
bool b_alloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY) { //*** because we are pointing to **

    if ((iSizeX < 0) || (iSizeY < 0)) // if size is incorrect then false
        return false;

    int** piTab = new int* [iSizeX]; //creating array of pointers

    for (int i = 0; i < iSizeX; i++) //assign one-dimensional array to each pointer  so at the end we have two-dimensional array
        piTab[i] = new int[iSizeY];

    for (int i = 0;i < iSizeX;i++)  //initializing array by values
        for (int j = 0;j < iSizeY;j++) 
            piTab[i][j] = i + j + 5;

    *piTable = piTab; //assign new array to piTable

    return true;
}

//3 
bool b_dealloc_table_2_dim(int** piTable, int iSizeX, int iSizeY) {
    if ((iSizeX < 0)) //if size is wrong then return false
        return false;

    /*
        as we have dynamic two dimensional array, we have an array of pointers which
        points to sub-arrays, so easily we just need to clear each sub-array 
        and then clear the array of pointers
    */

    for (int i = 0;i < iSizeX;i++) {
        delete[]piTable[i]; //clear each sub-array
    }

    delete[]piTable; //clear the array of pointers

    return true;
}

//4

void v_mod_tab(CTable* pcTab, int iNewSize) { 
    pcTab->bSetNewSize(iNewSize);
}

void v_mod_tab(CTable cTab, int iNewSize) {
    cTab.bSetNewSize(iNewSize);
}

int main()
{

    cout << "Array size: ";
    int input;
    cin >> input;
    const int iSize = input;
    v_alloc_table_add_5(iSize);



    int** pi_table;
    cout << "\n\nb_alloc_table_2_dim: " << b_alloc_table_2_dim(addressof(pi_table), 5, 3) << endl;

    for (int i = 0;i < 5;i++) {
        for (int j = 0;j < 3;j++) {
            cout << pi_table[i][j] << " ";

        }
        cout << endl;
    }

    cout << "b_dealloc_table_2_dim: " << b_dealloc_table_2_dim(pi_table, 5, 3) << endl << endl << endl;

    /*for (int i = 0;i < 5;i++) {                                        //error because 
                                                                           already deallocated
        for (int j = 0;j < 3;j++) {
            cout << pi_table[i][j] << " ";

        }
        cout << endl;
    }*/



    cout << "-----------------------------" << endl;

    CTable ctable("a",5);
    cout << "\nDefault table length: " << ctable.i_getLength() << endl;

    cout << "v_mod_tab(s_tab_default)" << endl;
    v_mod_tab(ctable, 5);
    cout << "Length table_default : " << ctable.i_getLength() << endl;

    cout << "\nv_mod_tab(&s_tab_default)" << endl;
    v_mod_tab(&ctable, 15);
    cout << "Length table_default : " << ctable.i_getLength() << endl;

    cout << "\n\n----------------------\nTable Clone" << endl;
    CTable* table_clone = ctable.pcClone();
    table_clone->vSetName("Cloned_table");
    table_clone->bSetNewSize(50);


    cout << "Default table: " << "Name: " + ctable.s_getName() << "; Size: " << ctable.i_getLength() << endl;
    cout << "Cloned table: " << "Name: " + table_clone->s_getName() << "; Size: " << table_clone->i_getLength() << endl << endl;

    delete table_clone;

    return 0;
}