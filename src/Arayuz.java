import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;



 

public class Arayuz implements ActionListener {
    
     JFrame frame;
     JPanel panel;
     Mayin_tarlasi mayin_tarlamiz;
     JButton btn;
     List<Mayin>mayinlarimiz;
     int temiz_alan;
    
   
    public Arayuz() 
        {
            gui();
        }
    
    public void gui()
        {
            mayin_tarlamiz = new Mayin_tarlasi(new Dimension(400,400),20);
            frame = new JFrame("Mayın Tarlası");
            frame.setSize(500,500);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            temiz_alan = 0;
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.red);
            panel.setLocation(20, 20);
            panel.setSize(mayin_tarlamiz.buyuklugu());
            frame.add(panel);
            Alanlari_ekle();
            yenile();
            
        
            
           
        }
    
    
    public static void main(String[] args) 
        {
            new Arayuz();
        }
    
    public void  Alanlari_ekle ()
            
        {
            for(int x = 0;x < panel.getWidth();x = x + 20)
                {
                    for(int y = 0;y < panel.getHeight();y = y + 20)
                        {
                            Buttonlari_ekle(new Point(x,y));
                        }
                }
        }

    public void Buttonlari_ekle (Point loc)
        {
            btn = new JButton();
            btn.setName("Nokta"+"; "+ loc.x +","+ loc.y );
            btn.setSize(20,20);
            btn.setLocation(loc);
            btn.setPreferredSize(new Dimension(20, 20));
            btn.setBorder(new BevelBorder(1));
            btn.addActionListener(this);
            btn.addMouseListener(new MouseAdapter() {

    //Mayın bulunduğunu düşündüğümüz yeri işaretliyoruz.
    public void mouseClicked(MouseEvent e) {
        btn = (JButton) e.getSource(); 
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) 
            {
                if(btn.getText()==" ")
                    {
                        btn.setForeground(Color.red);
                        btn.setText("!");
                        return;
                    }
                if(btn.getText()=="!")
                    {
                        btn.setForeground(Color.black);
                        btn.setText(" ");
                    }
               
            }
    }
});
            panel.add(btn);
        }  
         
   
    
    
    public void actionPerformed(ActionEvent e) 
        {
        btn = (JButton) e.getSource();
      
        Mayin myn = mayin_tarlamiz.mayin_koordinati
        (btn.getLocation());
        mayinlarimiz = new ArrayList<Mayin>();
        if(myn.getMayin())
            {
                Mayinlari_goster();
                JOptionPane.showMessageDialog(null,"Mayına Bastın");
                frame.setVisible(false);
            }
        else
            {
                int s= etrafta_kac_mayin_var(myn);
                if( s == 0)
                    {
                        mayinlarimiz.add(myn);
                        for (int i = 0;i< mayinlarimiz.size();i++)
                            {
                                Mayin item = mayinlarimiz.get(i);
                                if(item!=null)
                                    {
                                    if(item.getBakildi()==false&&item.getMayin()==false)
                                        {
                                            JButton btn = (JButton) panel.findComponentAt(item.getKonum().x,item.getKonum().y);
                                            if(etrafta_kac_mayin_var(mayinlarimiz.get(i))==0)
                                                {
                                                    btn.setEnabled(false);
                                    
                                                    cevresindekileri_ekle(item);
                                                }
                                            else
                                                {
                                                    btn.setText(String.valueOf(etrafta_kac_mayin_var(item)));
                                                }
                                            temiz_alan++;
                                            item.setBakildi(true);
                                        }
                                    }
                          
                            }
                    }
                else
                    {
                      btn.setText(String.valueOf(s));
                      temiz_alan++;
                    }
            }
        //Eyer bütün güvenli alanlara, hiç mayına basılmadan, basılırsa bu metod çalışıcak.
        if (temiz_alan >= mayin_tarlamiz.toplam_alan() - mayin_tarlamiz.toplam_mayin_sayisi())
            {
              JOptionPane.showMessageDialog(null,"Kazandın");
              frame.setVisible(false);
            }
        }
    //Bastığımız butonun etrafındaki butonlarda mayın olup olmadığını kontrol ediyoruz.
    public int etrafta_kac_mayin_var(Mayin m)
        {
            int sayi = 0;
            if (m.getKonum().x > 0)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20,m.getKonum().y)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().y < panel.getHeight()-20&&m.getKonum().x<panel.getWidth()-20)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20,m.getKonum().y + 20)).getMayin())
                        {
                            sayi++;
                        }
                }   
            if (m.getKonum().x <panel.getWidth()-20)
                {            
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20,m.getKonum().y)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().x > 0&&m.getKonum().y > 0)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20,m.getKonum().y - 20)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().y > 0)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x,m.getKonum().y - 20)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().x > 0&&m.getKonum().y<panel.getHeight()-20)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20,m.getKonum().y + 20)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().y < panel.getHeight()- 20 )
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x ,m.getKonum().y + 20)).getMayin())
                        {
                            sayi++;
                        }
                }
            if (m.getKonum().x < panel.getWidth()-20&&m.getKonum().y > 0)
                {
                    if(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20,m.getKonum().y - 20)).getMayin())
                        {
                            sayi++;
                        }
                }
            return sayi;
            
        }
    /*Etrafında hiç mayın olmayan bir alana bastığımız en yakınındaki mayınlara
    ulaşana kadar yine kendisi gibi etrafı güvenli bütün butonlar deaktif hale geliyor.
    */
    public void cevresindekileri_ekle(Mayin m)
        {
            boolean b1=false;
            boolean b2=false;
            boolean b3=false;
            boolean b4=false;
            if(m.getKonum().x > 0)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20 ,m.getKonum().y)));
                    b1 = true;
                }
            if(m.getKonum().y > 0)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x,m.getKonum().y - 20)));
                    b2 = true;
                }
                     
            if(m.getKonum().x < panel.getWidth())
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20 ,m.getKonum().y)));
                    b3 = true;
                }
            if(m.getKonum().y < panel.getHeight())
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x ,m.getKonum().y + 20)));
                    b4 = true;
                }
            if(b1 && b2)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20,m.getKonum().y - 20)));
                }
            if(b1 && b4)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x - 20,m.getKonum().y + 20)));
                }
            if(b2 && b3)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20 ,m.getKonum().y - 20)));
                }
            if(b3 && b4)
                {
                    mayinlarimiz.add(mayin_tarlamiz.mayin_koordinati(new Point(m.getKonum().x + 20 ,m.getKonum().y + 20)));
                }
            
        }
    /*Mayınların Yerlerini gösteriyoruz. Mayına basıldığında bu metod dönücek ve 
    oyun kapanmadan önce diğer mayınların yeri gösterilicek.
    */
    public void Mayinlari_goster()
        {
            
            for (Mayin myn : mayin_tarlamiz.ButunDegerleriDon()) 
                {
                    if(myn.getMayin())
                        {
                            JButton btn = (JButton) panel.findComponentAt(myn.getKonum().x,myn.getKonum().y);
                            btn.setText("M");
                        }
                }
        }
    public void yenile()
        {
             for (Mayin myn : mayin_tarlamiz.ButunDegerleriDon()) 
                {
                    if(myn.getMayin())
                        {
                            JButton btn = (JButton) panel.findComponentAt(myn.getKonum().x,myn.getKonum().y);
                            btn.setText(" ");
                        }
                    else
                        {
                            JButton btn = (JButton) panel.findComponentAt(myn.getKonum().x,myn.getKonum().y);
                            btn.setText(" ");
                        }
                }
        }
    
        
   
    
        
    
}
