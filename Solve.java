public class Solve 
{
    int[][] board =
    {
        {7, 0, 2, 0, 5, 0, 6, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {1, 0, 0, 0, 0, 9, 5, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 9, 0},
        {0, 4, 3, 0, 0, 0, 7, 5, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 8},
        {0, 0, 9, 7, 0, 0, 0, 0, 5},
        {0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
    };
    public int GRID_SIZE = 9;
    public int[][] getSolvedBoard() 
    {
        return board;
    }
    public void setBoard(int[][] matrix)
    {
      for (int i = 0; i < GRID_SIZE; i++) 
      {
        for (int j = 0; j < GRID_SIZE; j++) 
        {
          board[i][j]=matrix[i][j];  
        }  
      }
    }
    Solve(int[][] m)
    {
        this.setBoard(m);
        System.out.println("Before Solving Sudoko(in Solve Class)");
        printBoard(board);
        if(solveBoard(board)) 
        {
            System.out.println("Solved successfully!");
        }
        else 
        {
            System.out.println("Unsolvable board :(");
        }
        System.out.println("After Solving Sudoko(in Solve Class)");
        printBoard(board);
    }
    public static void main(String[] args) 
    {
      int[][] temp={{}};
        new Solve(temp);
    }
  
  
  private void printBoard(int[][] board) 
  {
    for (int row = 0; row < GRID_SIZE; row++) 
    {
      if (row % 3 == 0 && row != 0) 
      {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRID_SIZE; column++) 
      {
        if (column % 3 == 0 && column != 0) 
        {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }


  private boolean isNumberInRow(int[][] board, int number, int row) 
  {
    for (int i = 0; i < GRID_SIZE; i++) 
    {
      if (board[row][i] == number) 
      {
        return true;
      }
    }
    return false;
  }
  
  private boolean isNumberInColumn(int[][] board, int number, int column) 
  {
    for (int i = 0; i < GRID_SIZE; i++) 
    {
      if (board[i][column] == number) 
      {
        return true;
      }
    }
    return false;
  }
  
  private boolean isNumberInBox(int[][] board, int number, int row, int column) 
  {
    int localBoxRow = row - row % 3;
    int localBoxColumn = column - column % 3;  
    for (int i = localBoxRow; i < localBoxRow + 3; i++) 
    {
      for (int j = localBoxColumn; j < localBoxColumn + 3; j++) 
      {
        if (board[i][j] == number) 
        {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean isValidPlacement(int[][] board, int number, int row, int column) 
  {
    return !isNumberInRow(board, number, row) &&
        !isNumberInColumn(board, number, column) &&
        !isNumberInBox(board, number, row, column);
  }
  public boolean checkIfInputIsValid(int[][] matrix) 
  {
    for(int i=0; i<9; i++)
    {
      for(int j=0; j<9; j++)
      {
        if(matrix[i][j]>=10 && matrix[i][j]<0)
         return false;
      }
    }  
    return true;
  }
  private boolean solveBoard(int[][] board) 
  {
    if(checkIfInputIsValid(board))
    {
      for (int row = 0; row < GRID_SIZE; row++) 
      {
        for (int column = 0; column < GRID_SIZE; column++) 
        {
          if (board[row][column] == 0) 
          {
            for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) 
            {
              if (isValidPlacement(board, numberToTry, row, column)) 
              {
                board[row][column] = numberToTry;
                
                if (solveBoard(board)) 
                {
                  return true;
                }
                else 
                {
                  board[row][column] = 0;
                }
              }
            }
            return false;
          }
        }
      }
    }
    else 
    {
      return false;
    }
    return true;
  } 
}


