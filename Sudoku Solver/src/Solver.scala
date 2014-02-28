/**
 * ************************
 * Scott Bouloutian
 * 2/27/2014
 * Solver.scala
 * Represents a Solver for
 * 	a Sudoku Puzzle
 *
 * 	This solver is designed to read a 16x16 sudoku puzzle from a file
 * and output its solution to the user. The file should use the numbers
 * 1-9, the letters A-G, and the '.' symbol to represent an empty space.
 * This program is a demonstration of the Scala programming language,
 * utilizing both its object oriented and functional paradigms.
 * ************************
 */

import scala.actors._
import Actor._
import scala.io._

object Solver {
  // The symbols used to represent the sudoku
  val symbols = List('.', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
  // Solves the sudoku as much as possible without any guesswork by looking at constraints
  def simplify(puzzle: Sudoku) = puzzle.elements.indices.foreach(puzzle.solveSpace(_))

  // Starting point of the program
  def main(args: Array[String]) {
    println("Sudoku Solver Initialized")
    val puzzle = readFile(args(0))
    val result = DFS(puzzle)
    if (result._1) {
      println("Solution Found")
      println(result._2);
    }
    println("Done Program")
  }

  // Reads a 16x16 sudoku puzzle from a file
  def readFile(fileName: String): Sudoku = {
    val array = new Array[Int](256)
    var i = 0
    for (line <- Source.fromFile(fileName).getLines()) {
      for (col <- (0 to 15)) {
        array(i) = symbols.indexOf(line.charAt(col))
        i = i + 1
      }
    }
    new Sudoku(array)
  }

  // The depth first search algorithm used to solve the sudoku
  def DFS(puzzle: Sudoku): (Boolean, Sudoku) = {
    if (puzzle.solved) {
      return (true, puzzle)
    }
    val emptySpace = puzzle.nextOpenSpace
    val moves = puzzle.validMoves(emptySpace)
    // For each possible move in the empty space, guess that move and recurse concurrently
    val caller = self
    for (e <- moves) {
      actor {
        val newPuzzle = new Sudoku(puzzle.elements.updated(emptySpace, e))
        simplify(newPuzzle)
        caller ! DFS(newPuzzle)
      }
    }
    // Receive the results of the concurrent DFS
    for (i <- 1 to moves.size) {
      receive {
        case r:(Boolean,Sudoku) =>
          if(r._1){
            return (true,r._2)
          }
      }
    }
    return (false, puzzle)
  }

}
