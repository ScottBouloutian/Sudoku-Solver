// Scott Bouloutian

object Solver {
  val symbols = List('.', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G')

  def main(args: Array[String]) {
    println("Sudoku Solver Initialized")
    val list = new Array[Int](256)
    var i = 0;
    for (line <- io.Source.fromFile("res/sudoku.txt").getLines()) {
      for (col <- (0 to 15)) {
        list(i) = symbols.indexOf(line.charAt(col))
        i = i + 1
      }
    }

    val puzzle = new Sudoku(list)
    DFS(puzzle)
    println("Done Program")
  }

  def simplify(puzzle: Sudoku)=puzzle.elements.indices.foreach(puzzle.solveSpace(_))

  def DFS(puzzle: Sudoku): Boolean = {
    if (puzzle.solved) {
      println("Solution to the puzzle was found!")
      println(puzzle)
      true
    } else {
      var emptySpace = puzzle.nextOpenSpace
      for (n <- 1 to 16) {
        if (puzzle.validMoves(emptySpace).contains(n)) {
          var newElements = puzzle.elements.updated(emptySpace, n)
          var newPuzzle = new Sudoku(newElements)
          simplify(newPuzzle)
          DFS(newPuzzle)
        }
      }
    }
    false
  }

}
