/**************************
 * Scott Bouloutian
 * 2/27/2014
 * Sudoku.scala
 * Represents a Sudoku puzzle
 **************************/

class Sudoku(val puzzleArray: Array[Int]) {
  
  
  val elements = puzzleArray
  val symbols = List('.', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G')
  val nums=List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
  
  def index(row: Int, col: Int) = row * 16 + col
  def at(row: Int, col: Int): Int = elements(index(row, col))
  def solved:Boolean=(!elements.contains(0))
  def getRow(i:Int)=i/16
  def getCol(i:Int)=i%16
  def getCell(i:Int)=((i/16)/4)*4+((i%16)/4)
  def nextOpenSpace=elements.indexOf(0)
  
  def validMoves(i:Int): List[Int] = {
    val element = elements(i)
    val indexedList = elements.zipWithIndex;
    val rowElements = indexedList.filter(e => getRow(e._2)==getRow(i) && e._2 != i).unzip._1
    val colElements = indexedList.filter(e => getCol(e._2)==getCol(i) && e._2 != i).unzip._1
    val cellElements = indexedList.filter(e => getCell(e._2)==getCell(i) && e._2 != i).unzip._1
    val scopeElements = (rowElements ++ colElements ++ cellElements).distinct
    nums.diff(scopeElements)
  }
  
  def solveSpace(i:Int)={
    if(elements(i)==0){
      val moves=validMoves(i)
      if(moves.size==1){
        elements(i)=moves.head
      }
    }
  }
  
  override def toString = {
    var result = ""
    for (row <- (0 to 15)) {
      if(row%4==0){
        result+=" -------------------------------\n"
      }
      for (col <- (0 to 15)) {
        if(col%4==0){
          result+="|"
        }
        result += symbols(at(row, col))
        if(col%4<3){
          result+=" "
        }
      }
      result += "|\n"
    }
    result+=" -------------------------------"
    result
  }
}