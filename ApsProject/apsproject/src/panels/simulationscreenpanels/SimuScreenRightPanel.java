package apsproject.src.panels.simulationscreenpanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import apsproject.src.methods.LoadImages;

public class SimuScreenRightPanel extends JPanel{

    private Image backgroundImage;
    private LoadImages images;

    public SimuScreenRightPanel() {

        images = new LoadImages();
        backgroundImage = images.getRightPanelMatch();
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
}

