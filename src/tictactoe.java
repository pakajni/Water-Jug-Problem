/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pankaj
 */


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class tictactoe {
	static int A[][] = new int[3][3];
	static int k = 1;
	static int count = 0;
	public static void main(String args[]) {
            Frame frame = new Frame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
	}
}

class Frame extends JFrame {
	int DEFAULT_SIZE = 300;
	Frame() {
                minMax = new MinMaxAlgorithm();
		setBounds(0, 0, 400, 300);
                setLocationRelativeTo( null );
                JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
                JPanel playPanel = new JPanel();
                playPanel.setLayout(new GridLayout(3, 3));
                JButton [][]btn1 = new JButton[3][3];
                for(int i=0;i<3;i++)
                for(int j=0;j<3;j++){
                    btn1[i][j] = new JButton();
                    //btn1[i][j].setText(""+i);
                    playPanel.add(btn1[i][j]);
                    btn1[i][j].setEnabled(false);
                }
                container.add(playPanel, BorderLayout.CENTER);
                JPanel optionPanel = new JPanel();
                optionPanel.setLayout(new GridLayout(1, 2));
                
                JComboBox dropPlayer = new JComboBox();
                
                dropPlayer.addItem(makeObj("Player"));
                dropPlayer.addItem(makeObj("Player 1"));
                dropPlayer.addItem(makeObj("Player 2"));
                
                JComboBox dropLevel = new JComboBox(); //Difficulty level
                
                dropLevel.addItem(makeObj("Level"));
                dropLevel.addItem(makeObj("Easy"));
                dropLevel.addItem(makeObj("Medium"));
                dropLevel.addItem(makeObj("Hard"));
                
                dropLevel.setEnabled(false);
                playerListActionListener(dropPlayer, dropLevel);
                levelListActionListener(dropLevel, btn1);
                
                for(int i=0; i<3; i++)
                    for(int j=0; j<3; j++){
                        buttonActionListner(btn1, i, j);
                    }
                
                optionPanel.add(dropPlayer);
                optionPanel.add(dropLevel);
                
                container.add(optionPanel, BorderLayout.SOUTH);
                add(container);
		
		}
     private Object makeObj(final String item)  {
     return new Object() {@Override
 public String toString() { return item; } };
     }
     
     private void buttonActionListner(JButton btn[][], int i, int j){
        //int place;
        btn[i][j].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btn[i][j].setFont(new Font("Serif",Font.BOLD,30));
                if(getSelectedPLayer().equalsIgnoreCase("player 1")){
                    btn[i][j].setText("X");
                    
                    //minMax.setIndex(i, j, 3);
                    
                    setAndCheck(i, j, 3, 27, btn, 1);
                }
                else{
                    btn[i][j].setText("O");
                    
                    //minMax.setIndex(i, j, 5);
                    
                    setAndCheck(i, j, 5, 125, btn, 2);
                }
                btn[i][j].setEnabled(false);
                int place = minMax.makeMove();
                
                if(place == -1){
                    disable(btn);
                    Frame frame = new Frame();
                    JOptionPane.showMessageDialog(frame, "Match Draw!!");
                }
                else{
                    //System.out.println(place);
                    btn[place/3][place%3].setFont(new Font("Serif", Font.BOLD, 30));
                    if(getSelectedPLayer().equalsIgnoreCase("player 1")){
                        btn[place/3][place%3].setText("O");

                        //minMax.setIndex(place/3, place%3, 5);

                        setAndCheck(place/3, place%3, 5, 125, btn, 2);
                    }
                    else{
                        btn[place/3][place%3].setText("X");

                        //minMax.setIndex(place/3, place%3, 3);

                        setAndCheck(place/3, place%3, 3, 27, btn, 1);
                    }
                    btn[place/3][place%3].setEnabled(false);
                }
            }
        }); 
     }
     private void playerListActionListener(JComboBox playerList, JComboBox levelList){
         playerList.addActionListener(new ActionListener(){

             @Override
            public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 setSelectedPlayer(playerList.getSelectedItem());
                 levelList.setEnabled(true);
                 playerList.setEnabled(false);
             }
             
         });
     }
     private void levelListActionListener(JComboBox levelList, JButton btn[][]){
         levelList.addActionListener(new ActionListener(){

             @Override
             public void actionPerformed(ActionEvent e) {
                 
                 setSelectedLevel(levelList.getSelectedItem());
                 levelList.setEnabled(false);
                 for(int i=0;i<3; i++)
                    for(int j=0;j<3; j++){
                        btn[i][j].setEnabled(true);
                    }
                 setPlayerAndLevelInMinMax();
                 
                 int place = minMax.makeFirstMove();
                ////System.out.println(place);
                if(place != 10){
                    
                    btn[place/3][place%3].setFont(new Font("Serif",Font.BOLD,30));
                    if(getSelectedPLayer().equalsIgnoreCase("player 1")){
                        btn[place/3][place%3].setText("O");
                        
                        //minMax.setIndex(place/3, place%3, 5);
                        
                        setAndCheck(place/3, place%3, 5, 125, btn, 1);
                    }
                    else{
                        btn[place/3][place%3].setText("X");
                        
                        //minMax.setIndex(place/3, place%3, 3);
                        
                        setAndCheck(place/3, place%3, 3, 3, btn, 2);
                    }
                    btn[place/3][place%3].setEnabled(false);
                }
             }
         });
     }
     
     public String getSelectedPLayer(){
         return selectedPlayer.toString();
     }
     private void setSelectedPlayer(Object selectedPlayer){
         this.selectedPlayer = selectedPlayer;
     }
     
     public String getSelectedLevel(){
         return selectedLevel.toString();
     }
     
     private void setSelectedLevel(Object selectedLevel){
         this.selectedLevel = selectedLevel;
     }
     
     private void setPlayerAndLevelInMinMax(){
         minMax.setFirstPlayer(getSelectedPLayer());
         minMax.setLevel(getSelectedLevel());
     }
     
    private void setAndCheck(int i, int j, int value, int pro, JButton [][]btn, int player){
        
        minMax.setIndex(i, j, value);
        
        if(minMax.check(pro)){
            disable(btn);
            if(minMax.getFirstPlayer() == player){
                JOptionPane.showMessageDialog(this, "You Win");
            }
            else{
                JOptionPane.showMessageDialog(this, "Computer Wins!!");
            }
        }
    }

    private void disable(JButton [][]btn){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                btn[i][j].setEnabled(false);
            }
    }
    private Object selectedPlayer;
    private Object selectedLevel;
    MinMaxAlgorithm minMax;
}

class Position{
    int i;
    int j;
}

class MinMaxAlgorithm{
    
    int [][]playBoard;
    int turn = 0;
    private int firstPlayer = 0;
    private int level = -1;
    private int t;
    
    public MinMaxAlgorithm(){
        playBoard = new int [3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                playBoard[i][j]=2;
            }
    }
    private void decideTurns(){
        switch(level){
            case 0:
                t = 4;
                break;
            case 1:
                t=3;
                break;
            case 2:
                t=0;
                break;
            case 3:
                t=1;
                break;
            default:
                t=0;
        }
    }
    void setFirstPlayer(String x){
        if(x == null){
        }
        else if(x.equalsIgnoreCase("player 1")){
            firstPlayer = 1;
        }
        else if(x.equalsIgnoreCase("player 2")){
            firstPlayer = 2;
        }
    }
    int getFirstPlayer(){
        return firstPlayer;
    }
    void setLevel(String x){
        if(x == null){
        }
        else if(x.equalsIgnoreCase("Easy")){
            level = 0;
        }
        else if(x.equalsIgnoreCase("Medium")){
            level =1;
        }
        else if(x.equalsIgnoreCase("Hard")){
            level=2;
        }
        decideTurns();
    }
    
    public int makeFirstMove(){
        if(firstPlayer == 1){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Your Move");
            //turn++;
            return 10;
        }
        else{
            return makeMove();
        }
    }
    public void setIndex(int i, int j, int index){
        playBoard[i][j] = index;
        turn++;
        System.out.println(turn);
    }
    public int makeMove(){
        //turn++;
        Random rand = new Random();
        int x;
        do{
        x = rand.nextInt(8)+1;
        }while(playBoard[x/3][x%3] != 2);
        
        System.out.println("t = " + t);
        
        if(turn <= t){
            return x;
        }
        
        Position possiblePlace;
        if(firstPlayer == 2){
            possiblePlace = possWin(18);
            if(possiblePlace.i !=-1){
                //System.out.println(possiblePlace.i+" "+possiblePlace.j);
                //setIndex(possiblePlace.i, possiblePlace.j, 5);
                return possiblePlace.i * 3 + possiblePlace.j;
            }
            else {
                possiblePlace = possWin(50);
                if(possiblePlace.i!=-1){
                    //System.out.println(possiblePlace.i+" "+possiblePlace.j);
                    //setIndex(possiblePlace.i, possiblePlace.j, 5);
                    return possiblePlace.i * 3 + possiblePlace.j;
                }
                else{
                    //System.err.println("hello 1");
                    //setIndex(make().i, make().j, 5);
                    if(make() != null)
                        return make().i * 3+make().j;
                    else
                        return -1;
                }
            }
        }
        else if(firstPlayer == 1){
            possiblePlace = possWin(50);
            if(possiblePlace.i !=-1){
                //System.out.println("x.i="+possiblePlace.i+" "+possiblePlace.j);
                //setIndex(possiblePlace.i, possiblePlace.j, 3);
                return possiblePlace.i * 3 + possiblePlace.j;
            }
            else{
                possiblePlace = possWin(18);
                if(possiblePlace.i != -1){
                    //System.out.println("x.j="+possiblePlace.i+" "+possiblePlace.j);
                    //setIndex(possiblePlace.i, possiblePlace.j, 3);
                    return possiblePlace.i * 3 + possiblePlace.j;
                }
                else{
                    //System.err.println("hello 2");
                    //setIndex(make().i, make().j, 3);
                    if(make() != null)
                        return make().i * 3+make().j;
                    else
                        return -1;
                }
            }
            
        }
        return -1;
    }
    
    public Position make(){
        Position x = new Position();
        if(playBoard[1][1] == 2){
            x.i = 1;
            x.j = 1;
            return x;
        }
        else if(playBoard[0][0] == 2){
            x.i = 0;
            x.j = 0;
            return x;
        }
        else if(playBoard[0][2] == 2){
            x.i = 0;
            x.j = 2;
            return x;
        }
        else if(playBoard[2][2] == 2){
            x.i = 2;
            x.j = 2;
            return x;
        }
        else if(playBoard[2][0] == 2){
            x.i = 2;
            x.j = 0;
            return x;
        }
        return null;
    }
    private Position possWin(int pro){
        Position x = new Position();
        x.i=-1;
        x.j=-1;
        int dumi = -1,dumj=-1;
        int product = 1;
        for(int i=0; i<3; i++){
            product = 1;
            for(int j=0; j<3; j++){
                product = product*playBoard[i][j];
                if(playBoard[i][j] == 2){
                    dumi=i; dumj=j;
                }
            }
            if(product==pro){
                x.i=dumi;
                x.j=dumj;
                return x;
            }
        }
        
        for(int j=0; j<3; j++){
            product = 1;
            for(int i=0; i<3; i++){
                product = product*playBoard[i][j];
                if(playBoard[i][j] == 2){
                    dumi=i; dumj=j;
                }
            }
            if(product==pro){
                x.i=dumi;
                x.j=dumj;
                return x;
            }
        }
        product = 1;
        for(int i=0;i<3; i++){
            product = product*playBoard[i][i];
            if(playBoard[i][i] == 2){
                dumi=i;
            }
        }
        if(product == pro){
            x.i = x.j =  dumi;
            return x;
        }
        product = 1;
        for(int i=0, j=2;i<3; i++, j--){
            product = product*playBoard[i][j];
            if(playBoard[i][j] == 2){
                dumi=i;dumj = j;
            }
        }
        if(product == pro){
            x.i = dumi;
            x.j = dumj;
            return x;
        }
        return x;
    }
    public boolean check(int pro){
        ////System.out.println("hello in check" + pro);
        int product = 1;
        for(int i=0;i<3;i++){
            product = 1;
            for(int j=0; j<3; j++){
                product = product * playBoard[i][j];
            }
            ////System.out.println("product = "+product);
            if(product == pro){
                return true;
            }
        }
        
        for(int j=0; j<3;j++){
            product = 1;
            for(int i=0; i<3; i++){
                product = product * playBoard[i][j];
            }
            if(product == pro)
                return true;
        }
        product = 1;
        for(int i=0; i<3; i++){
            product = product * playBoard[i][i];
        }
        if(product == pro){
            return true;
        }
        product = 1;
        for(int i=0, j=0; i<3; i++ ,j--){
            product = product * playBoard[i][i];
        }
        if(product == pro)
            return true;
        return false;
    }
}
