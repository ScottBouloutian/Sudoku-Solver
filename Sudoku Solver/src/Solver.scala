// Scott Bouloutian

object Solver {
  val symbols = List('.', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
  def simplify(puzzle: Sudoku) = puzzle.elements.indices.foreach(puzzle.solveSpace(_))

  def main(args: Array[String]) {
    println("Sudoku Solver Initialized")
    val puzzle = readFile("res/sudoku.txt")
    val result=DFS(puzzle)
    if(result._2==true){
      println("Solution Found")
      println(result._1);
    }
    println("Done Program")
  }

  def readFile(fileName: String): Sudoku = {
    val array = new Array[Int](256)
    var i = 0
    for (line <- io.Source.fromFile(fileName).getLines()) {
      for (col <- (0 to 15)) {
        array(i) = symbols.indexOf(line.charAt(col))
        i = i + 1
      }
    }
    new Sudoku(array)
  }

  def DFS(puzzle: Sudoku): (Sudoku, Boolean) = {
    if (puzzle.solved) {
      println("solved")
      (puzzle, true)
    } else {
      val emptySpace = puzzle.nextOpenSpace
      val moves = puzzle.validMoves(emptySpace)
      moves.foreach(e => branchDFS(new Sudoku(puzzle.elements.updated(emptySpace, e))))
    }
    (puzzle, false)
  }

  def branchDFS(puzzle:Sudoku) = {
    simplify(puzzle)
    DFS(puzzle)
  }
}
