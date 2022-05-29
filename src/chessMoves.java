import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chessMoves  {
    char [][]emptyBoard={
        {'.','-','.','-','.','-','.','-'},
        {'-','.','-','.','-','.','-','.'},
        {'.','-','.','-','.','-','.','-'},
        {'-','.','-','.','-','.','-','.'},
        {'.','-','.','-','.','-','.','-'},
        {'-','.','-','.','-','.','-','.'},
        {'.','-','.','-','.','-','.','-'},
        {'-','.','-','.','-','.','-','.'}};
    char [][]board={
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P','P','P','P','P','P'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'p','p','p','p','p','p','p','p'},
        {'r','n','b','q','k','b','n','r'}};
    char[][] tempBoard={
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P','P','P','P','P','P'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.'},
        {'p','p','p','p','p','p','p','p'},
        {'r','n','b','q','k','b','n','r'}};
public char EMPTY='.';
    boolean isUpper(char x){
        if (x>='A'&&x<='Z')
            return true;
        else
            return false;
    }
    //function return true if char is lowercase
    boolean isLower(char x){
        if (x>='a'&&x<='z')
            return true;
        else
            return false;
    }
    boolean changeType(char x,char y,int n){
        if(isLower(x)&&n==0)
            return isUpper(y);
        else if (isLower(x)&&n==1)
            return isLower(y);
        else if (isUpper(x)&&n==0)
            return isLower(y);
        else if (isUpper(x)&&n==1)
            return isUpper(y);
        return true;
    }

    boolean validRookMove(int fromRow, int fromCol, int toRow, int toCol){
        int i,j=0;
        // Attempt to move to the same cell
        if (fromRow == toRow && fromCol == toCol)
            return false;

        if (fromRow == toRow) { // Horizontal move
            int x = (fromCol < toCol) ? 1 : -1;

            for (i = fromCol + x; i != toCol; i += x)

                if (board[fromRow][i]!='.')
                    j++;
            if ((changeType(board[fromRow][fromCol],board[fromRow][i],1))|| j!=0)
                return false;
        }else if (fromCol == toCol) { // Vertical move
            int y = (fromRow < toRow) ? 1 : -1;
            for (i = fromRow + y; i != toRow; i += y)
                if (board[i][fromCol]!='.')
                    j++;
            if ((changeType(board[fromRow][fromCol],board[i][fromCol],1)) ||j!=0)
                return false;
        } else // Not valid rook move
            return false;

        // Return true if destination cell is free
        return ((board[toRow][toCol] == '.') || changeType(board[fromRow][fromCol],board[toRow][toCol],0));
    }
    boolean validBishopMove(int fromRow, int fromCol, int toRow, int toCol){
        int i,j,k=0,diagonal1,diagonal2,temp;

        // Attempt to move to the same cell
        if (fromRow == toRow && fromCol == toCol)
            return false;

        diagonal1= (fromRow > toRow) ? -1 : 1;
        diagonal2= (fromCol > toCol) ? -1 : 1;
        if (((fromCol+fromRow)==(toCol+toRow)) || ((fromCol-fromRow)==(toCol-toRow))){
            temp=diagonal2;
            for (i = fromRow + diagonal1; i != toRow; i += diagonal1) {
                j = fromCol + diagonal2;
                if (board[i][j]!=EMPTY)
                    k++;
                diagonal2+=temp;
            }
            if (((board[i][toCol] != EMPTY) && (changeType(board[fromRow][fromCol],board[i][toCol],1))) || k != 0)
                return false;

        } else
            return false;
        return ((board[toRow][toCol] == EMPTY)||(changeType(board[fromRow][fromCol],board[toRow][toCol],0)));
    }
    boolean validQueenMove(int fromRow, int fromCol, int toRow, int toCol){
        if (validBishopMove(fromRow,fromCol,toRow,toCol)||validRookMove(fromRow,fromCol,toRow,toCol))
            return true;
        else
            return false;
    }
    boolean validKingMove(int fromRow, int fromCol, int toRow, int toCol){
        // Attempt to move to the same cell
        if (fromRow == toRow && fromCol == toCol)
            return false;
        if ((((fromRow +1== toRow)&&(fromCol==toCol))||((fromRow-1 == toRow)&&(fromCol==toCol))||
                ((fromCol+1 == toCol)&&(fromRow==toRow))||((fromCol-1 == toCol)&&(fromRow==toRow)))||
                ( ((fromRow+1==toRow )&& (fromCol+1==toCol) ) || ((fromRow-1==toRow)&&(fromCol-1==toCol))||
                        ((fromRow+1==toRow)&&(fromCol-1==toCol))||((fromRow-1==toRow)&&(fromCol+1==toCol))))
            return ((board[toRow][toCol] == EMPTY) || (changeType(board[fromRow][fromCol], board[toRow][toCol], 0)));
        else
            return false;

    }




    public static void main(String[] args) {


        Myframe myframe=new Myframe();
    }


}
