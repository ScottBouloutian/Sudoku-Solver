Sudoku-Solver
=============
This is a 16 by 16 sudoku solver written to gain experience in the Scala programming language.

Overview
-------------
The project I chose to implement in Scala was the Sudoku Solver. This program utilizes both the functional and object oriented paradigms of Scala to solve a 16 by 16 sudoku puzzle. The proram reads a sudoku puzzle from a text file and outputs its solution to the terminal. Example sudoku inputfiles are included. An example of the successful compilation and execution of the program is included as a script file.


Instructions
-------------
Compile the included Solver.scala and Sudoku.scala source files by running the command:

	scalac Solver.scala Sudoku.scala

Run the solver with one of the example sudoku text files as a command line argument:

	scala Solver sudoku.txt
