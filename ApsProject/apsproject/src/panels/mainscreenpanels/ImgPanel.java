package apsproject.src.panels.mainscreenpanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import apsproject.src.methods.LoadImages;


public class ImgPanel extends JPanel{

    private Image backgroundImage;

    private LoadImages image;

    public ImgPanel() {

        image = new LoadImages();
        backgroundImage = image.getMainWallpaper();
            
    }

    //=> Sobreescrevendo o metodo paintComponent do JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension dim = this.getSize();
        int dWidth = (int) dim.getWidth();
        int dHeight = (int) dim.getHeight();

        BufferedImage imgBg = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imgBg.createGraphics();

        g2.drawImage(backgroundImage, 0, 0, dWidth, dHeight, null);
        g2.dispose();

        g.drawImage(imgBg, 0, 0, null);

    }

    //=> Metodo responsavel por atualizar as imagens de fundo do Painel
    private void setImgBackground(int imageIndex) {
        switch (imageIndex) {
            case 1:
                backgroundImage = image.getStartImg();
                break;
            case 2:
                backgroundImage = image.getRankingImg();
                break;
            case 3:
                backgroundImage = image.getMainWallpaper();
                break;
            default:
                break;
        }
        repaint();
    }

    //=> Classe responsavel pelo efeito de hover
    public class HoverEffects extends MouseAdapter {

        private final int imageIndex;

        public HoverEffects(JButton button, int imageIndex) {
            this.imageIndex = imageIndex;
        }

        //=> Sobreescrevendo o metodo mouseEntered da classe MouseAdapter
        @Override
        public void mouseEntered(MouseEvent event) {
            if (imageIndex == 1) {
                setImgBackground(1);
            } else {
                setImgBackground(2);
            }
        }

        //=> Sobreescrevendo o metodo mouse exited da Classe Mouse Adapter
        @Override
        public void mouseExited(MouseEvent event) {
            setImgBackground(3);
        }

    }

}

