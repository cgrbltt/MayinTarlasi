

import java.awt.Point;




public class Mayin {
    Point loc;
    boolean dolu;
    boolean bakildi;
    public Mayin(Point location)
        {
            dolu = false; 
            loc = location;
          
        }

  //Mayının koordinatı
    public Point getKonum()
        {
            return loc;
        }
  //Alanda mayın var mı?
    public boolean getMayin()
        {
            return dolu;
        }
  
    public void setMayin(boolean dolu)
        {
           this.dolu=dolu;
        }
   //Alan kontrol edildi mi?
    public boolean getBakildi()
        {
            return bakildi;
        }
    public void setBakildi(boolean dolu)
        {
            this.bakildi=dolu;
        }
   






















}