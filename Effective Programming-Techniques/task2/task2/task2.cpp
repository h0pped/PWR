// task2.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <iostream>
#include "CTable.h"




int main()
{
    std::cout << "Hello World!\n";


    //if we have destructor we get error 
    //becasue we at the end we try to delete object 
    //which was already deleted  
        
    //if we remove destructor it works fine
    /*
    CTable c_tab_0, c_tab_1;
    c_tab_0.bSetNewSize(6);
    c_tab_1.bSetNewSize(4);
    c_tab_0 = c_tab_1;
    */

    /*CTable c_tab_0, c_tab_1;
    c_tab_0.bSetNewSize(6);
    c_tab_1.bSetNewSize(4);
    //initialize table
     c_tab_0 = c_tab_1;
    c_tab_1.vSetValueAt(2, 123);
    c_tab_0.vPrint();
    c_tab_1.vPrint(); */

    CTable ct1, ct2;
    ct1.bSetNewSize(6);
    ct2.bSetNewSize(4);

    ct1.vSetValueAt(0, 10);
    ct1.vSetValueAt(1, 11);
    ct1.vSetValueAt(2, 12);
    ct1.vSetValueAt(3, 13);
    ct1.vSetValueAt(4, 14);
    ct1.vSetValueAt(5, 15);

    ct1.vPrint();

    ct2.vSetValueAt(0, 20);
    ct2.vSetValueAt(1, 21);
    ct2.vSetValueAt(2, 22);
    ct2.vSetValueAt(3, 23);
    ct2.vPrint();

    ct1  = ct1 + ct2;

    ct1.vPrint();


}
