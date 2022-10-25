import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
class SudokoGame extends JFrame implements ActionListener
{
    final static int BOARD_LENGTH=9;
    JTextField[][] inputGridTextField;
    JButton showAnsBtn, createNewBoardBtn, exitBtn;
    int height=30, width=30;
    int x=50, y=50,score=Integer.MAX_VALUE;
    String text="";
    SudokoGame()
    {
        this.setLayout(null);
        inputGridTextField=new JTextField[BOARD_LENGTH][BOARD_LENGTH];
        int[][] board=createRandomBoard();
        
        draw(board);

        showAnsBtn=new JButton("Show Solution");
        showAnsBtn.setBounds(50, 500, 150, 30);
        showAnsBtn.addActionListener(this);
        this.add(showAnsBtn);

        createNewBoardBtn=new JButton("Create New Board");
        createNewBoardBtn.setBounds(250, 500, 150, 30);
        createNewBoardBtn.addActionListener(this);
        this.add(createNewBoardBtn);

        exitBtn=new JButton("Exit");
        exitBtn.setBounds(450, 500, 150, 30);
        exitBtn.addActionListener(this);
        this.add(exitBtn);

        this.setBackground(Color.BLACK);
        this.setBounds(150,150,1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==showAnsBtn)
        {
            int[][] grid=getGridValues(inputGridTextField);
            int[][] solvedAns=solveSudoko(grid);
            undraw(inputGridTextField);
            draw(solvedAns);
        }
        else if(a.getSource()==createNewBoardBtn)
        {
            undraw(inputGridTextField);
            int b[][]=createRandomBoard();
            draw(b);
        }
        else if(a.getSource()==exitBtn)
        {
            System.exit(ABORT);
        }
    }
    public void draw(int[][] matrix) 
    {
        x=50;
        y=50;
        inputGridTextField=new JTextField[BOARD_LENGTH][BOARD_LENGTH];
        for (int i = 0; i < matrix.length; i++) 
        {
            for (int j = 0; j < matrix.length; j++) 
            {
                if(matrix[i][j]!=0)
                {
                    text=matrix[i][j]+"";
                }
                else
                {
                    text="0";
                }
                JTextField jTextField=new JTextField(text);   
                // jTextField.setName("0");
                jTextField.setFont(new Font("serif", Font.BOLD, 25)); 
                System.out.println(jTextField.getName());
                jTextField.setBounds(x,y,30,30);
                this.add(jTextField);
                x+=32;    
            }    
            x=50;
            y+=32;
        }
    }
    public void undraw(JTextField[][] grid) 
    {
        for (JTextField[] jTextFields : grid) 
        {
            for (JTextField curr : jTextFields) 
            {
                this.remove(curr);        
            }
        }
    }
    public int[][] getGridValues(JTextField[][] grid)
    {
        JTextField curr=null;
        int[][] val=new int[9][9];
        int temp=0;
        for (int i = 0; i < val.length; i++) 
        {
            for (int j = 0; j < val.length; j++) 
            {
                curr=grid[i][j];
                if(curr.getText().equals(""))
                {
                    temp=0;
                    val[i][j]=temp;   
                }
                else if(curr.getText().length()==1)
                {
                    temp=Integer.valueOf(curr.getText());
                    val[i][j]=temp;   
                }
                else
                {
                    //JOptionPane
                    System.out.println("Enter 1 to 9 digits only");
                    val[i][j]=0;
                }
            }    
        }
        return val;
    }
    public int[][] createRandomBoard() 
    {
        Board b=new Board();
        int[][] board=b.getFinalBoard(); 
        return board;    
    }
    public int[][] solveSudoko(int[][] grid) 
    {
        Solve s=new Solve(grid);
        return s.getSolvedBoard();
    }
    public static void main(String[] args) 
    {
        new SudokoGame();
    }
}