package de.umr.ds.images;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class MyBufferedImage extends JFrame {
    MyBufferedImage() {
    }

    public void createGUI() {
        this.setTitle("HelloGUI");
        this.setDefaultCloseOperation(3);
        MyBufferedImage.MyPaintPanel paintPanel = new MyBufferedImage.MyPaintPanel();
        this.getContentPane().add(paintPanel);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyBufferedImage myGUI = new MyBufferedImage();
                myGUI.createGUI();
            }
        });
    }

    static class MyPaintPanel extends JPanel {
        //private BufferedImage img = new BufferedImage(256, 256, 1);
        private BufferedImage img = null;

        public MyPaintPanel() {
            try {
                //img = ImageIO.read(new File("C:\\Users\\xiao\\Desktop\\vbox_data\\Unbenannt48.PNG"));
                URL url = getClass().getResource("example.jpg");
                File file = new File(url.getPath());
                img = ImageIO.read(file);

            } catch (IOException e) {
                System.out.println(e.toString());
            }
            if (this.img != null) {
                this.setPreferredSize(new Dimension(this.img.getWidth(), this.img.getHeight()));
            }

        }

        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            int rgb = -256;

            /**
            for(int i = 0; i < 33; ++i) {
                for(int k = 0; k < 33; ++k) {
                    this.img.setRGB(112 + i, 112 + k, rgb);
                }
            }*/

            final double rCoeff = 0.299;
            final double gCoeff = 0.587;
            final double bCoeff = 0.114;
            final int xMax = img.getWidth();
            final int yMax = img.getHeight();
            int[][] listOrigin = new int[xMax][yMax];
            int[][] listGray = new int[xMax][yMax];
            for(int x = 0; x < xMax; x++){
                for(int y = 0; y < yMax; y++){
                    int pixel = img.getRGB(x, y);
                    listOrigin[x][y] = pixel;
                }
            }
            BufferedImage grayImg = new BufferedImage(xMax, yMax, BufferedImage.TYPE_INT_RGB);

            for(int x = 0; x < xMax; x++){
                for(int y = 0; y < yMax; y++){
                    int color = listOrigin[x][y];
                    int r2 = (color >> 16) & 0xFF;
                    int g2 = (color >> 8) & 0xFF;
                    int b2 = color & 0xFF;
                    int newG = (int)(rCoeff * r2 + g2 * gCoeff + b2 * bCoeff);
                    int gray = newG << 8 | newG <<16 | newG ;


                    //listGray[x][y] = gray;
                    grayImg.setRGB(x,y,gray);
                }
                img = grayImg;

            }

            g.drawImage(this.img, 0, 0, (ImageObserver)null);
        }
    }
}

