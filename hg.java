import java.util.*;

public class hg{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        char[][] board={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        while(true){
            printBoard(board);
            playerTurn(board, sc);
            printBoard(board);
            if(hasWon(board,'X')){
                System.out.println("player has won");
                break;
            }
            if(isFull(board)){
                System.out.println("Draw");
                break;
            }
            aiTurn(board);
            printBoard(board);
            if(hasWon(board,'O')){
                System.out.println("AI has won");
                break;
            }
            if(isFull(board)){
                System.out.println("Draw");
                break;
            }
        }
    }

    public static void aiTurn(char board[][]){
        int move[]=minmax(board,'O');
        board[move[0]][move[1]]='O';
    }

    public static int[] minmax(char[][]board,char c){
        int move[]={-1,-1};
        int score=0;
        if(c=='X'){
            score=Integer.MAX_VALUE;
        }else{
            score=Integer.MIN_VALUE;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    board[i][j]=c;
                    int sc=helper(board,0,false);
                    board[i][j]=' ';
                    if((sc>score && c=='O') || (sc<score && c=='X')){
                        score=sc;
                        move[0]=i;
                        move[1]=j;
                    }
                }
            }
        }
        return move;
    }

    public static int helper(char[][]board,int d,boolean b){
        if(hasWon(board,'X')){
            return -1;
        }if(hasWon(board, 'O')){
            return 1;
        }if(isFull(board)){
            return 0;
        }
        int score=0;
        char c=' ';
        if(b){
            c='O';
        }else{
            c='X';
        }
        if(b){
            score=Integer.MIN_VALUE;
        }else{
            score=Integer.MAX_VALUE;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    board[i][j]=c;
                    int sc=helper(board, d+1, !b);
                    board[i][j]=' ';
                    if(b){
                        score=Math.max(sc,score);
                    }else{
                        score=Math.min(sc,score);
                    }
                }
            }
        }
        return score;
    }

    public static void playerTurn(char[][]board,Scanner sc){
        int a=0;
        int b=0;
        do{
            System.out.println("Enter Numbers in terms of row and col");
            a=sc.nextInt();
            b=sc.nextInt();
        }while(a<1 || a>3 || b<1 || b>3 || board[a-1][b-1]!=' ');
        board[a-1][b-1]='X';
    }

    public static boolean hasWon(char[][]board,char c){
        for(int i=0;i<3;i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]==c){
                return true;
            }
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]==c){
                return true;
            }
        }
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]==c){
            return true;
        }if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]==c){
            return true;
        }return false;
    }

    public static boolean isFull(char[][]board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][]board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("|"+board[i][j]+"|");
            }
            System.out.println();
            System.out.println("----------");
        }
    }
}