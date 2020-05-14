# Guess Number
guess-number micro service version.  
猜数字游戏。

## Overview
游戏有四个格子，每个格子有一个0到9的数字，任意两个格子的数字都不一样。你有六次猜测机会，
如果猜对则获胜，否则失败。每次猜测时需依序输入4个数字，程序会根据猜测的情况给出xAxB的反馈，
A前面的数字代表位置和数字都对的个数，B前面的数字代表数字对但是位置不对的个数。  

例如：答案是1 2 3 4，那么对于不同的输入，有如下的输出

Example：  
> 答案是1 2 3 4，那么对于不同的输入，有如下输出  

```text
Input     Output        Instruction
1 5 6 7    1A0B          1 correct
2 4 7 8    0A2B          2 and 4 wrong position
0 3 2 4    1A2B          4 correct, 2 and 3 wrong position
5 6 7 8    0A0B          all wrong
4 3 2 1    0A4B          4 numbers position wrong
1 2 3 4    4A0B          win, all correct
1 1 2 3   Wrong Input, Input again
1 2       Wrong Input, Input again
```
答案在游戏开始时随机生成。玩家通过访问API进行游戏，每次游戏输入只有6次机会，在每次猜测时，程序应给出当前的猜测结果，
以及之前所有猜测的数字和结果以供玩家参考。

要求：
* 设计和编写测试用例
* 完成已有测试文件中的测试，并保证此测试文件所测类的测试覆盖率为100%
* 单元测试应涵盖所有核心业务逻辑
* 用小步骤进行单元测试重构
* 为单元测试和方法命名有意义的名称
* 代码通过小步骤提交并附上有意义的评论

## Analysis
先根据需求提炼一下角色，Game，Rule，Generator。  
规则是属于游戏的，游戏依赖于规则，游戏和规则的关系属于聚合关系，因此要实现游戏，要像搭积木一样，先搭底层。
生成器属于游戏，用于生成一组4个不相同数字的答案。

### Rule
职责：仅计算结果  
利用正交分解法对一般等价类进行分析，如下：  
|  | 数字全对 | 数字部分对 | 数字全不对 |
| --- | --- | --- | --- |
| 位置全对   | 4A0B | N/A | N/A |
| 位置部分对 | 2A2B | 1A1B | N/A |
| 位置全不对 | 0A4B | 0A1B | 0A0B |

### Generator
随机答案生成器，根据需求有以下3个场景：  
1. 生成的数位数为4位
2. 生成的数有0-9数字组成
3. 生成的数不存在相同的数

### Game

## Tasking

### Rule Tasking
1. 0A0B  

2. 4A0B  

3. 0A4B

4. 2A2B

5. 0A1B

6. 1A1B
