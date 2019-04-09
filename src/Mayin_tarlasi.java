

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Mayin_tarlasi {
    Dimension buyukluk;
    List<Mayin> mayinlar;
    int dolu_mayin_sayisi;
    Random rdm = new Random();
    

//Mayin tarlası oluşturuluyor
    public Mayin_tarlasi(Dimension buyukluk,int mayin_Sayisi)
        {
            mayinlar = new ArrayList<Mayin>();
            dolu_mayin_sayisi = mayin_Sayisi;
            this.buyukluk = buyukluk;
               for(int x = 0; x < buyukluk.width; x = x + 20)
                {
                    for(int y = 0;y < buyukluk.height;y = y + 20)
                        {
                            Mayin m = new Mayin(new Point(x,y));
                            Alanlari_ekle(m);
                        }
                }
               Mayinlari_doldur();
        }
   
    public void Alanlari_ekle(Mayin m)
        {
            mayinlar.add(m);
        }
    //Mayınlar alana (dimension'a) rastgele ekleniyor.
    public void Mayinlari_doldur()
        {
            int sayi = 0;
            while(sayi< dolu_mayin_sayisi)
            {
                int i = rdm.nextInt(mayinlar.size());
                Mayin item = mayinlar.get(i);
                if (item.getMayin() == false)
                    {
                        item.setMayin(true);
                        sayi++;
                    }
            }
        }    
    //Mayın tarlasının boyutlarını dönen metod
    public Dimension buyuklugu()
        {
            return buyukluk;
        }
    //Mayının koordinatını alan metod
    public Mayin mayin_koordinati(Point loc)
        {
            for (int i = 0;i < mayinlar.size();i++)
                {
                     
                    if( mayinlar.get(i).getKonum().equals(loc))
                    {
                        return mayinlar.get(i);
                    }
                        
                }
            return null;
        }
    //Bu metodla mayın tarlasındaki bütün değerleri dönüyoruz.
    public List<Mayin> ButunDegerleriDon()
            {
                return mayinlar;
            }
     
    //Alan sayısını dönüyoruz.
    public double toplam_alan()
        {
            return (buyukluk.getWidth() * buyukluk.getHeight() )/ 400;
        }
    //Toplam Mayın sayısını dönüyoruz.
    public double toplam_mayin_sayisi()
        {
            return dolu_mayin_sayisi;
        }













}