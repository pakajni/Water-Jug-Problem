/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pankaj
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TicTacToe {
	static int A[][] = new int[3][3];
	static int k = 1;
	static int count = 0;
	public static void main(String args[]) {

		Frame1 frame = new Frame1();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Frame1 extends JFrame {
	int DEFAULT_SIZE = 200;
	Frame1() 
                setBounds(0, 0, DEFAULT_SIZE, DEFAULT_SIZE );
		setLayout(new GridLayout(3,3));
            JPanel x = new JPanel();
            
		
		String A[]={"0","1","2","3","4","5","6","7","8"};
                
		for(int i = 0, k=0;i<3;i++,k+=3)
			for(int j=1;j<4;j++)
			{ 
                            final JButton btn = new JButton();
                            btn.setActionCommand(A[k+j-1]);
                            btn.addActionListener(new ActionListener(){
                            @Override
                                public void actionPerformed(ActionEvent e) {
                                    // TODO Auto-generated method stub
                                    if(tictactoe.k==1)
                                        tictactoe.k=-1;
                                    else
                                        tictactoe.k=1;
                                    String c;

                                    btn.setFont(new Font("Serif",Font.BOLD,30));
                                    String s = e.getActionCommand();
                                    int i = Integer.parseInt(s)/3;
                                    int j = Integer.parseInt(s)%3;
                                    tictactoe.A[i][j]=tictactoe.k;
                                    if(tictactoe.A[i][j]==1)
                                        c= "X";
                                    else
                                        c= "O";
                                    btn.setText(c);
                                    btn.setEnabled(false);
                                    tictactoe.count++;
                                    check(i,j);
                                }
                            });
                        add(btn);
                    }
		}
void check(int i, int j){}
 /*void check(int i,int j){
		int sum = 0;
		if(i==j){
			sum =(tictactoe.A[0][0]+tictactoe.A[1][1]+tictactoe.A[2][2]);
		}
		if(i+j==2){
			sum=(tictactoe.A[0][2]+tictactoe.A[1][1]+tictactoe.A[2][0]);
		}
			if(!((sum!=-3&&sum==3)||(sum==-3&&sum!=3)))
			sum=(tictactoe.A[i][0]+tictactoe.A[i][1]+tictactoe.A[i][2]);
			if(!((sum!=-3&&sum==3)||(sum==-3&&sum!=3)))
			sum=(tictactoe.A[0][j]+tictactoe.A[1][j]+tictactoe.A[2][j]);
		    if((sum!=-3&&sum==3)||(sum==-3&&sum!=3)||(tictactoe.count==9)){
			if(sum==-3)
		    	System.out.println("1st Player Wins");
			else if(sum==3)
		    	System.out.println("2nd Player Wins");
			else
				System.out.println("MATCH DRAW");
			System.exit(0);
		    }
		
	}*/
}


