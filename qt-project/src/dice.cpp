#include <iostream>
#include "console.h"
#include "gwindow.h" // for GWindow
#include "simpio.h"  // for getLine
#include "vector.h"  // for Vector
using namespace std;
void diceHelper(Vector<int> &permutation, int dices)
{
    if(dices == 0)
    {
        cout << "{" << permutation << "}" << endl;
        return;
    }
    else {
        for(int i =1; i<=6;i++ )
        {
            //chose
            permutation.add(i);

            //explore
            diceHelper(permutation,dices-1);

            //unChose
            permutation.remove(permutation.size()-1);

        }
    }
}
void diceRolls(int dices)
{
    Vector<int> chosen;
   diceHelper(chosen,dices);
}


int main()
{
    diceRolls(10);
    return 0;
}
