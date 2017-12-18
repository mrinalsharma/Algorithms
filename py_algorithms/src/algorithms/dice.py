'''
Created on Dec 14, 2017

@author: mrinal
'''
from builtins import int

def diceHelper(chosen:list, dices:int):
    if(dices == 0):
        print(chosen)
        return
    else:
        for i in range(1,7):
            chosen.append(i)
            diceHelper(chosen, dices-1)
            chosen.pop()
            

def diceRoll(dices:int):
    chosen = []
    diceHelper(chosen, dices)

if __name__ == '__main__':
    diceRoll(2)