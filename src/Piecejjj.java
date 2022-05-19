/*import java.util.LinkedList;

public class Piece {

    int xp;
    int yp;
    LinkedList<Piece> ps;
    boolean isWhite;
    String pieceName;
    public Piece(int xp, int yp, boolean isWhite , String pieceName,LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.pieceName=pieceName;
        ps.add(this);
    }
    public LinkedList<Piece> getPieces(){
        return ps;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public void move(int xp , int yp ){
        for (Piece p : ps) {
            if (p.xp==xp && p.yp==yp){
                p.kill();
            }
        }
        this.xp=xp;
        this.yp=yp;
    }

    public void kill(){
        ps.remove(this);
    }
}*/
