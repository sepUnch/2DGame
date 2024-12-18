package main;

import object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean massageOn = false;
    public String massage = "";
    int massageCounter = 0;
    public boolean gameFinish = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMassage(String text){

        massage = text;
        massageOn = true;
    }
    public void draw(Graphics2D g2) {

        if (gameFinish == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);


            String text;
            int textLength;
            int x;
            int y;

            text = "Kamu mendapatkan harta karun!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Waktu Anda :" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);


            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Selamat!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasKey, 74, 65);

            // TIME
            playTime +=(double)1/60;
            g2.drawString("Time:"+dFormat.format(playTime), gp.tileSize*11, 65);

            // MASSAGE
            if(massageOn == true) {

                g2.setFont((g2.getFont().deriveFont(30F)));
                g2.drawString(massage, gp.tileSize/2, gp.tileSize*5);

                massageCounter++;

                if (massageCounter > 120){
                    massageCounter = 0;
                    massageOn = false;
                }
            }
        }
    }
}
