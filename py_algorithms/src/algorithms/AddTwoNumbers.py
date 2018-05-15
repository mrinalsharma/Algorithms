# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

import json
import math

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        start = l3 = ListNode(0)
        carry =- 0
        while l1 != None or l2 != None:
            val1 = l1.val if l1 != None else 0 
            val2 = l2.val if l2 != None else 0
            l3.val = (val1 + val2 + carry) % 10
            carry = (val1 + val2 + carry) // 10
            if l1 != None:
                l1 = l1.next
            if l2 != None:
                l2 = l2.next
            if l1 != None or l2 != None:
                l3.next = ListNode(0)
                l3 = l3.next
        if carry > 0:
            l3.next = ListNode(carry)
            
        return start
def stringToIntegerList(input):
    return json.loads(input)

def stringToListNode(input):
    # Generate list from the input
    numbers = stringToIntegerList(input)

    # Now convert that list into linked list
    dummyRoot = ListNode(0)
    ptr = dummyRoot
    for number in numbers:
        ptr.next = ListNode(number)
        ptr = ptr.next

    ptr = dummyRoot.next
    return ptr

def listNodeToString(node):
    if not node:
        return "[]"

    result = ""
    while node:
        result += str(node.val) + ", "
        node = node.next
    return "[" + result[:-2] + "]"

def main():
    import sys
    def readlines():
        for line in sys.stdin:
            yield line.strip('\n')

    lines = readlines()
    while True:
        try:
            line = next(lines)
            l1 = stringToListNode(line);
            line = next(lines)
            l2 = stringToListNode(line);
            
            ret = Solution().addTwoNumbers(l1, l2)

            out = listNodeToString(ret);
            print(out)
        except StopIteration:
            break

if __name__ == '__main__':
    main()