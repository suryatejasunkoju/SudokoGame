import java.util.*;
 public class Board
 {
    ArrayList<ArrayList<int[]>> allLinesAL = new ArrayList<ArrayList<int[]>>(); //ArrayList of three AL
    //Each AL will take 3 arrays
     ArrayList<int[]> first = new ArrayList<int[]>(3);
    ArrayList<int[]> second = new ArrayList<int[]>(3);
    ArrayList<int[]> third = new ArrayList<int[]>(3);
    //integer arrays which represent each row of the sudoku game
    private int[] lineOne = {2,9,6,3,1,8,5,7,4};
    private int[] lineTwo = {5,8,4,9,7,2,6,1,3};
    private int[] lineThree = {7,1,3,6,4,5,2,8,9};
    private int[] lineFour = {6,2,5,8,9,7,3,4,1};
    private int[] lineFive = {9,3,1,4,2,6,8,5,7};
    private int[] lineSix = {4,7,8,5,3,1,9,2,6};
    private int[] lineSeven = {1,6,7,2,5,3,4,9,8};
    private int[] lineEight = {8,5,9,7,6,4,1,3,2};
    private int[] lineNine = {3,4,2,1,8,9,7,6,5};

    private int [][] finalBoard = new int[9][9];

    // constructing my sudoku board
    public Board()
    {
        initializeThreeAL(lineOne,lineTwo,lineThree,lineFour,lineFive,lineSix,lineSeven,lineEight,lineNine);
        randomizeRows(); // shuffling my rows
        initializeThreesAL(allLinesAL);
        randomizeColumns();
        createBoard();
        hideBoardValue();
        System.out.println("Printing board from Board Class");
        printBoard();
    }
    public void printBoard() 
    {
        for (int i = 0; i < finalBoard.length; i++) 
        {
            for (int j = 0; j < finalBoard.length; j++) 
            {
                System.out.print(finalBoard[i][j]+",");
            }    
            System.out.println();
        }
    }
    public int[][] getFinalBoard() 
    {
        return finalBoard;    
    }
    private void constructLinesFromAL(ArrayList<int[]>al, int rowCounter)
    {
        for(int alIndex=0;alIndex<3;alIndex++)
        {
            for(int i=0;i<9;i++) 
            {
                finalBoard[rowCounter + alIndex][i] = al.get(alIndex)[i];
            }
        }
    }
    // Creating my board
    private void createBoard()
    {
        constructLinesFromAL(first,0);
        constructLinesFromAL(second,0);
        constructLinesFromAL(third,0);
    }
    private void randomizeRows()
    {
        Collections.shuffle(first);
        Collections.shuffle(second);
        Collections.shuffle(third);
        // need to rearrange for new game
        allLinesAL.clear();
        allLinesAL.add(first);
        allLinesAL.add(second);
        allLinesAL.add(third);

        allLinesAL = Collectionshuffle(allLinesAL);
    }
    private ArrayList Collectionshuffle(ArrayList<ArrayList<int[]>> al)
    {
        for(int i=0;i<al.size();i++)
        {
            //fisher-yates approach of shuffling
            int index = (int) (Math.random()*al.size()); // because I don't want to shuffle my sub elements
            ArrayList<int[]> temp = new ArrayList<int[]>();
            temp=al.get(i);
            al.set(i,al.get(index));
            al.set(index,temp);
        }
        return al;
    }
    private void initializeThreeAL(int []lineOne, int []lineTwo,int []lineThree,int []lineFour,int []lineFive,
                                   int []lineSix,int []lineSeven,int []lineEight,int []lineNine)
    {
        first.clear();
        second.clear();
        third.clear();

        first.add(lineOne);
        first.add(lineTwo);
        first.add(lineThree);
        second.add(lineFour);
        second.add(lineFive);
        second.add(lineSix);
        third.add(lineSeven);
        third.add(lineEight);
        third.add(lineNine);


    }
    private void initializeThreesAL(ArrayList<ArrayList<int[]>> al)
    {
        for(int i=0;i<al.size();i++)
        {
            if(i==0)
            {
                first=al.get(i);
            }
            else if(i==1)
            {
                second=al.get(i);
            }
            else
            {
                third=al.get(i);
            }
        }
    }
    private void hideBoardValue()
    {
        // use any algorithm to hide values
        for(int row=0;row<9;row++)
        {
            int zeroToMake = (int)(Math.random()*3)+3; // produces a random from 3-5
            double temp;
            double removeZeroChance;
            for(int col=0;col<9;col++)
            {
                temp=Math.random();
                removeZeroChance=(double) zeroToMake/(double) (9-col);
                if(removeZeroChance>=temp)
                {
                    zeroToMake--;
                    finalBoard[row][col]=0;
                }
            }
        }
    }
    private void randomizeColumns()
    {
        int [] lineOne = new int[9];
        int [] lineTwo = new int[9];
        int [] lineThree = new int[9];
        int [] lineFour = new int[9];
        int [] lineFive = new int[9];
        int [] lineSiX = new int[9];
        int [] lineSeven = new int[9];
        int [] lineEight = new int[9];
        int [] lineNine = new int[9];

        for(int j=0;j<9;j++)
        {
            for(int i=8;i>=0;i--)
            {
                int inneralcounter;
                if((i+1)%3==0)
                {
                    inneralcounter=2;
                }
                else if((i+1)%3==2)
                {
                    inneralcounter=1;
                }
                else
                {
                    inneralcounter=0;
                }

                int CellValue = allLinesAL.get(i/3).get(inneralcounter)[j];
                // System.out.println("C Value: "+CellValue);

                switch(j)
                {
                    case 0:
                        lineOne[8-i]=CellValue;
                        break;
                    case 1:
                        lineTwo[8-i]=CellValue;
                        break;
                    case 2:
                        lineThree[8-i]=CellValue;
                        break;
                    case 3:
                        lineFour[8-i]=CellValue;
                        break;
                    case 4:
                        lineFive[8-i]=CellValue;
                        break;
                    case 5:
                        lineSiX[8-i]=CellValue;
                        break;
                    case 6:
                        lineSeven[8-i]=CellValue;
                        break;
                    case 7:
                        lineEight[8-i]=CellValue;
                        break;
                    case 8:
                        lineNine[8-i]=CellValue;
                        break;
                }
            }
        }
        initializeThreeAL(lineOne,lineTwo,lineThree,lineFour,lineFive,lineSiX,lineSeven,lineEight,lineNine);
    }
}



