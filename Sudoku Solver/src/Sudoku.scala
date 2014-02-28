/**
 * ************************
 * Scott Bouloutian
 * 2/27/2014
 * Sudoku.scala
 * Represents a Sudoku puzzle
 * ************************
 */

class Sudoku(val puzzleArray: Array[Int]) {
  val elements = puzzleArray // The array containing the sudoku elements
  val symbols = List('.', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
  val nums = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

  def index(row: Int, col: Int) = row * 16 + col // Gets the index in the array containing the element at the given row and column
  def at(row: Int, col: Int): Int = elements(index(row, col)) // Gets the element at the given row and column
  def solved: Boolean = (!elements.contains(0)) // Determines if the sudoku puzzle is filled completely (equivalent to solved since only valid moves are made)
  def getRow(i: Int) = i / 16 // Gets the row corresponding to the specified index
  def getCol(i: Int) = i % 16 //Gets the column corresponding to the specified index
  def getCell(i: Int) = ((i / 16) / 4) * 4 + ((i % 16) / 4) // Gets the cell corresponding to the speecified index
  def nextOpenSpace = elements.indexOf(0) // Gets the next empty space of the sudoku

  // Comes up with a list of possible moves given an index
  def validMoves(i: Int): List[Int] = {
    val indexedList = elements.zipWithIndex; // Make an array of tuples of the form (element, index)
    val rowElements = indexedList.filter(e => getRow(e._2) == getRow(i) && e._2 != i).unzip._1 // Get all other elements in the row
    val colElements = indexedList.filter(e => getCol(e._2) == getCol(i) && e._2 != i).unzip._1 // Get all other elements in the column
    val cellElements = indexedList.filter(e => getCell(e._2) == getCell(i) && e._2 != i).unzip._1 // Get all other elements in the cell
    val scopeElements = (rowElements ++ colElements ++ cellElements).distinct // Combines the lists of elements
    nums.diff(scopeElements) // Find the elements not is any row, column, or cell
  }

  // Solves the space at the given index is there is only one possible move there
  def solveSpace(i: Int) = {
    if (elements(i) == 0) {
      val moves = validMoves(i)
      if (moves.size == 1) {
        elements(i) = moves.head
      }
    }
  }

  // Displays the sudoku is an easy to read way
  override def toString = {
    var result = ""
    for (row <- (0 to 15)) {
      if (row % 4 == 0) {
        result += " -------------------------------\n"
      }
      for (col <- (0 to 15)) {
        if (col % 4 == 0) {
          result += "|"
        }
        result += symbols(at(row, col))
        if (col % 4 < 3) {
          result += " "
        }
      }
      result += "|\n"
    }
    result += " -------------------------------"
    result
  }
}