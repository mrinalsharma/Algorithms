'''
Created on May 5, 2018

@author: mrinal
'''

from argparse import ArgumentParser

def arithmeticSeriesFormula(a1:int,aN:int,step:int):
    """" Sum = (A1+An)/2*n"""
    return ((a1+aN)/2)*((aN-a1)/step)

def arithmeticSeries(values:list):
    if values.__len__() == 1:
        return values[0];
    else:
        return values[0] + arithmeticSeries(values[1:])
if __name__ == '__main__':
    parser = ArgumentParser("Find sum of Arithmetic Series")
    parser.add_argument("series",metavar='N', type=int, nargs='+',help='an integer for the series')
    args = parser.parse_args()
    print(args.series)
    print("Sum =" + str(arithmeticSeries(args.series)))
    print("Sum from Formula =" + str(arithmeticSeriesFormula(args.series[0],args.series[len(args.series)-1],(args.series[1]-args.series[0]-1))))
    