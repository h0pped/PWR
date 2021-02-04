
#include <iostream>
#include "CMySmartPointer.h"
#include <chrono>
#include "CTab.h"
#include "CTable.h"

using namespace std;
int main()
{

    std::cout << "Hello World!\n";

    CMySmartPointer<int> cmp1(new int(5));
    cmp1.printStatus();


    cout << endl << endl;
    CMySmartPointer<int>cmp3(new int(4));
    cmp1 = cmp3;
    cmp1.printStatus();


    chrono::high_resolution_clock::time_point t1 = chrono::high_resolution_clock::now();

    for (int i = 0; i < 100; i++) {
        CTab Ct1;
        CTab Ct2 = std::move(Ct1);
    }

    chrono::high_resolution_clock::time_point t2 = chrono::high_resolution_clock::now();
    chrono::duration<double, std::milli> time = t2 - t1;

    cout << "\n Time to move: " << time.count() << "\n";



    t1 = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < 100; i++) {
        int* tab = new int[100];
        int* tab2 = new int[100];
        copy(tab, tab + 99, tab2);
    }
    t2 = std::chrono::high_resolution_clock::now();
    time = t2 - t1;
    cout << "\n Time to copy: " << time.count() << "\n";


    chrono::high_resolution_clock::time_point t3 = chrono::high_resolution_clock::now();

    for (int i = 0; i < 100; i++) {
        CTable Ct1;
        Ct1.bSetNewSize(100);
        CTable Ct2 = std::move(Ct1);
    }

    chrono::high_resolution_clock::time_point t4 = chrono::high_resolution_clock::now();
     time = t4 - t3;

    cout << "\n Time to move ctable: " << time.count() << "\n";



    t3 = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < 100; i++) {
        CTable Ct1;
        Ct1.bSetNewSize(100);
        CTable ct2;
        ct2.bSetNewSize(100);
        copy(Ct1.getPiTab(), Ct1.getPiTab() + 99, ct2.getPiTab());
    }
    t4 = std::chrono::high_resolution_clock::now();
    time = t4 - t3;
    cout << "\n Time to copy : " << time.count() << "\n";

    return 0;
}