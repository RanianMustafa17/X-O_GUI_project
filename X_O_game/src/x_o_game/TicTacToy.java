package x_o_game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToy extends JFrame implements ActionListener{
    private final JLabel textfield ;
    private final JPanel title_panel ;
    private final JPanel button_panel ;
    private final JButton[] buttons ;
    public TicTacToy(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(50,50,50));
        this.setVisible(true);
        title_panel = new JPanel();
        button_panel = new JPanel();
        buttons = new JButton[9];
  
        textfield = new JLabel("Tic Tac Toe");
        textfield.setBackground(new Color(0,102,102));
	textfield.setForeground(new Color(153,255,255));
	textfield.setFont(new Font("MV Boli",Font.BOLD,75));
	textfield.setHorizontalAlignment(JLabel.CENTER);
	textfield.setText("Tic Tac Toe");
	textfield.setOpaque(true);
        
        title_panel.setLayout(new BorderLayout());
	title_panel.setBounds(0,0,800,100);
        
        button_panel.setLayout(new GridLayout(3,3));
	button_panel.setBackground(new Color(150,150,150));
        
        for(int i=0;i<9;i++) {
		buttons[i] = new JButton();
		button_panel.add(buttons[i]);
		buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
		buttons[i].setFocusable(false);
		buttons[i].addActionListener(this);
		}
        title_panel.add(textfield);
        this.add(title_panel,BorderLayout.NORTH);
        this.add(button_panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0 ; i<9 ; i++){
            if(e.getSource()==buttons[i]) {
		if(buttons[i].getText()=="") {
		buttons[i].setForeground(new Color(0,102,102));
		buttons[i].setText("X");
                check();
                if (evaluate()==0){
                    if(willwin()!=-100){
                        buttons[willwin()].setText("O");
                        check();
                    }
                    else{
                int x=findBestMove();
                buttons[x].setText("O");
                check();} }               												
	}	    
       }         
       }  
    }
    public boolean isMovesLeft()
{
    for (int i = 0; i<9; i++){		
	if (buttons[i].getText()=="")
	     return true;}
	return false;
}
public int evaluate(){
    if(
	((buttons[0].getText()=="X") &&(buttons[1].getText()=="X") &&
	(buttons[2].getText()=="X"))||((buttons[3].getText()=="X") &&
	(buttons[4].getText()=="X") &&(buttons[5].getText()=="X")
	)||((buttons[6].getText()=="X") &&(buttons[7].getText()=="X") &&
	(buttons[8].getText()=="X"))||((buttons[0].getText()=="X") &&
	(buttons[3].getText()=="X") &&(buttons[6].getText()=="X")
	)||((buttons[1].getText()=="X") &&(buttons[4].getText()=="X") &&
	(buttons[7].getText()=="X"))||((buttons[2].getText()=="X") &&
	(buttons[5].getText()=="X") &&(buttons[8].getText()=="X")
	)||((buttons[0].getText()=="X") &&(buttons[4].getText()=="X") &&
	(buttons[8].getText()=="X")
	) ||((buttons[2].getText()=="X") &&(buttons[4].getText()=="X") &&
	(buttons[6].getText()=="X")))
		       return 10;              
    else if(
	((buttons[0].getText()=="O") &&(buttons[1].getText()=="O") &&
	(buttons[2].getText()=="O"))||((buttons[3].getText()=="O") &&
	(buttons[4].getText()=="O") &&(buttons[5].getText()=="O")
	)||((buttons[6].getText()=="O") &&
	(buttons[7].getText()=="O") &&(buttons[8].getText()=="O")
	)||((buttons[0].getText()=="O") &&(buttons[3].getText()=="O") &&
	(buttons[6].getText()=="O"))||((buttons[1].getText()=="O") &&
	(buttons[4].getText()=="O") &&(buttons[7].getText()=="O")
	)||((buttons[2].getText()=="O") &&(buttons[5].getText()=="O") &&
	(buttons[8].getText()=="O"))||((buttons[0].getText()=="O")
        &&(buttons[4].getText()=="O") &&(buttons[8].getText()=="O")
	) ||((buttons[2].getText()=="O") &&(buttons[4].getText()=="O") &&
	(buttons[6].getText()=="O"))) 
		return -10;
    else return 0;
    }
    public int minimax(int depth, boolean isMax){
        if(evaluate()!=0)   return evaluate();
        else if (isMovesLeft()==false)
		return 0;
        if(isMax) {
            int best=-1000;
            for(int i=0;i<9;i++) {                
                if(buttons[i].getText()=="") {
                buttons[i].setForeground(new Color(255,0,0));
                buttons[i].setText("X");
                int score=minimax(depth+1, false);
                buttons[i].setText("");
                if ( score- depth > best)
                        best = score - depth;
                
                    }                
            }
            return best;
        }        
        else {
            int best=1000;
            for(int i=0;i<9;i++) {               
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("O");
                        int score=minimax(depth+1, false);
                        buttons[i].setText("");
                        if (( score+ depth) < best)
                        best = score + depth;
                        
                    }           
            }
            return best;
        }
    }      
    public int findBestMove(){
        int bestVal = -1000;
	int bestMove=0 ;
       
        for(int i=0;i<9;i++) {               
            if(buttons[i].getText()=="") {
            buttons[i].setForeground(new Color(255,0,0));
            buttons[i].setText("X");
            
            int moveVal = minimax(0, false);
            buttons[i].setText("");                   
                if (moveVal >bestVal)
		{
                    bestVal = moveVal;
		    bestMove = i; 
		}
	}		
	}   
        return bestMove;      
    }   
    public void check(){
        if (evaluate()==-10){
          if(
		(buttons[0].getText()=="O") &&
		(buttons[1].getText()=="O") &&
		(buttons[2].getText()=="O")
		) {
		oWins(0,1,2);}
                
        if(
		(buttons[3].getText()=="O") &&
		(buttons[4].getText()=="O") &&
		(buttons[5].getText()=="O")
		) {
		oWins(3,4,5); }
                
        if(
		(buttons[6].getText()=="O") &&
		(buttons[7].getText()=="O") &&
		(buttons[8].getText()=="O")
		) {
		oWins(6,7,8) ;}               
         if(
		(buttons[0].getText()=="O") &&
		(buttons[3].getText()=="O") &&
		(buttons[6].getText()=="O")
		) {
		oWins(0,3,6) ;}            
         if(
		(buttons[1].getText()=="O") &&
		(buttons[4].getText()=="O") &&
		(buttons[7].getText()=="O")
		) {
		oWins(1,4,7) ;}        
         if(
		(buttons[2].getText()=="O") &&
		(buttons[5].getText()=="O") &&
		(buttons[8].getText()=="O")
		) {
		oWins(2,5,8) ;}         
         if(
		(buttons[0].getText()=="O") &&
		(buttons[4].getText()=="O") &&
		(buttons[8].getText()=="O")
		) {
		oWins(0,4,8) ;}         
         if(
		(buttons[2].getText()=="O") &&
		(buttons[4].getText()=="O") &&
		(buttons[6].getText()=="O")
		) {
		oWins(2,4,6) ;}
        }
        else if(evaluate()==10) {
            if(
		(buttons[0].getText()=="X") &&
		(buttons[1].getText()=="X") &&
		(buttons[2].getText()=="X")
		) {
		xWins(0,1,2);}
                
        if(
		(buttons[3].getText()=="X") &&
		(buttons[4].getText()=="X") &&
		(buttons[5].getText()=="X")
		) {
		xWins(3,4,5); }               
        if(
		(buttons[6].getText()=="X") &&
		(buttons[7].getText()=="X") &&
		(buttons[8].getText()=="X")
		) {
		xWins(6,7,8) ;}               
         if(
		(buttons[0].getText()=="X") &&
		(buttons[3].getText()=="X") &&
		(buttons[6].getText()=="X")
		) {
		xWins(0,3,6) ;}   
         
         if(
		(buttons[1].getText()=="X") &&
		(buttons[4].getText()=="X") &&
		(buttons[7].getText()=="X")
		) {
		xWins(1,4,7) ;}
         
         if(
		(buttons[2].getText()=="X") &&
		(buttons[5].getText()=="X") &&
		(buttons[8].getText()=="X")
		) {
		xWins(2,5,8) ;}
         
         if(
		(buttons[0].getText()=="X") &&
		(buttons[4].getText()=="X") &&
		(buttons[8].getText()=="X")
		) {
		xWins(0,4,8) ;}
         
         if(
		(buttons[2].getText()=="X") &&
		(buttons[4].getText()=="X") &&
		(buttons[6].getText()=="X")
		) {
		xWins(2,4,6) ;}
        }
        else if(isMovesLeft()==false)   tie();       
    }
    public void oWins(int a,int b,int c) {
		buttons[a].setBackground(Color.RED);
		buttons[b].setBackground(Color.RED);
		buttons[c].setBackground(Color.RED);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
	}
    public void xWins(int a,int b,int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
     }
    public void tie() {
         
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("It is Draw!!!");}   

public int willwin(){
     for(int i=0;i<9;i++) {             
            if(buttons[i].getText()=="") {
            buttons[i].setForeground(new Color(255,0,0));
            buttons[i].setText("O"); 
            if(evaluate()==-10){
                buttons[i].setText("");  
                return i;
            }
            buttons[i].setText("");            
            }
        }
     return -100;
}
}