# IQ PUZZLER PRO SOLVER
Abrar Abhirama Widyadhana, 13523038
<br>
This program utilizes a brute force algorithm to find solutions for the IQ Puzzler Pro game by systematically exploring all possible block placements until a valid configuration is found.

# SET UP && COMPILE INSTRUCTIONS

1. Git Clone
```bash
git clone https://github.com/Abrar-Abhirama/Tucil1_13523038.git
```
2. COMPILE
<br>
Make sure the current directory is set to the \Tucil1_13523038 folder.
<br>

```bash
javac -d bin src/*.java
```
3. RUN THE PROGRAM
```bash
java -cp bin src/Main
```

- to enter the test case (.txt), make sure the file is in the test folder
- Sometime, it takes more time to finish
- You can choose to save the solution to a text file or png

# Input File Format
1. DEFAULT Mode : 
```
N M P
DEFAULT
puzzle_1_shape
puzzle_2_shape
...
puzzle_P_shape
```
example : 
```
5 5 7
DEFAULT
A
AA
B
BB
C
CC
D
DD
EE
EE
E
FF
FF
F
GGG
```

2. CUSTOM MODE
```
N M P
CUSTOM
...X...
.XXXXX.
XXXXXXX
.XXXXX.
...X...
puzzle_1_shape
puzzle_2_shape
...
puzzle_P_shape
```
example : 
```
2 3 2
CUSTOM
X.X
.XX
AA
A
B
```
