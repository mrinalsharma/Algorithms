#include <iostream>
#include "console.h"
#include "gwindow.h" // for GWindow
#include "simpio.h"  // for getLine
#include "vector.h"  // for Vector

using namespace std;
int g_totalCalls = 0;
void diceHelper(Vector<int> &permutation, int dices, int sumSoFar, int desiredSum)
{
    if(dices == 0)
    {
        g_totalCalls++;
        if(sumSoFar == desiredSum)
        {
            cout << "{" << permutation << "}" << endl;
        }
        return;
    }
    else {
        for(int i =1; i<=6;i++ )
        {

            /*
             * too big
             * sumSoFar + i + 1*(dices -1),minimum selected value should not be greater than desiredSum. If its greater than
             * desired sum then the final value would be greater than desired value.
             *
             * too small
             * sumSoFar + i + 6 * (dices -1), maximum selected value should not be less than desiredSum. if its less than
             * desired sum then final value would be less then the desired value
             *
             * So the sumSoFar plus the sum of all the next dices minimum value should not be too big && sumSoFar plus the sum of all the next dices maximum value
             * should not be tooSmall.
             *
             */

            if((sumSoFar + i + 1 * (dices - 1)) <= desiredSum &&
              (sumSoFar + i + 6 * (dices - 1)) >= desiredSum )
            {
                //chose
                permutation.add(i);

                //explore
                diceHelper(permutation,dices-1,sumSoFar + i, desiredSum);

                //unChose
                permutation.remove(permutation.size()-1);
            }

        }
    }
}

void diceSum(int dices,int desiredSum)
{
   Vector<int> chosen;
   diceHelper(chosen,dices,0, desiredSum);
}


int main()
{
    diceSum(10,60);
    cout << "total calls " << g_totalCalls;
    return 0;
}
