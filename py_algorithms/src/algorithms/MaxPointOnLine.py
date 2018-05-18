'''
Created on May 14, 2018

@author: mrinal
'''
from copy import copy, deepcopy
'''
Given n points on a 2D plane, find the maximum number of 
points that lie on the same straight line.
'''

import json
import math

class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b

class Solution:
    def maxPoints(self, points):
        if(len(points) == 0):
            return 0
        elif len(points) == 1:
            return 1
        lineSlopeDict = {}
        """
        :type points: List[Point]
        :rtype: int
        """
        for point1 in points:
            slopeDict = {}
            count = 1
            slope = None
            pointsCopy = copy(points)
            pointsCopy.remove(point1)
            #print("x:{}, y:{}",point1.x, point1.y)
            for point2 in pointsCopy:
                if point1.x == point2.x and point1.y == point2.y:
                    count = count + 1
                    continue
                slope = self.slope(point1,point2)
                if slopeDict.get(slope) == None:
                    slopeDict[slope] = 1 
                else:
                    slopeDict[slope] = slopeDict[slope]+1
            for key, value in  slopeDict.items():
                value = value + count
                slopeDict[key] = value
            else:
                if len(slopeDict.values()) == 0:
                    slopeDict[0] = count
            print("len:{}, count:{} , dump{},",str(len(slopeDict)), str(count), json.dumps(slopeDict))
            lineSlopeDict.update(slopeDict)
        values = lineSlopeDict.values()
        print(json.dumps(lineSlopeDict))
        return sorted(values).pop() if len(values) > 0 else 0
    
    def slope(self,p1:Point,p2:Point):
        if p1.x == p2.x and p1.y == p2.y:
            return 0
        elif p2.x == p1.x:
            return float('inf')
        else:
            #print("slope:"+ str((p2.y - p1.y)/((p2.x-p1.x)*1.0)))
            return (p2.y - p1.y)/((p2.x-p1.x)*1.0)
        
def stringToIntegerList(input):
    return json.loads(input)

def main():
    import sys
    def readlines():
        for line in sys.stdin:
            yield line.strip('\n')
            
    def listToPoints(pointList):
        points = []
        for point in pointList:
            points.append(Point(point[0],point[1]))
        return points
            

    lines = readlines()
    while True:
        try:
            line = next(lines)
            numbers = stringToIntegerList(line)
            points = listToPoints(numbers)
            ret = Solution().maxPoints(points)
            print(ret)
            
        except StopIteration: 
            break
if __name__ == '__main__':
    main()