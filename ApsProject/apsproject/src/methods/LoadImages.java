package apsproject.src.methods;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImages {

    private Image image, matchImg, simuImg, wallpaper, mainHelpImg, leftMainPanel, mainWallpaper, startImg, rankingImg;
    private Image leftPanel, leftPanelMS, rankRightPanel, matchRightPanel, simuRightPanel, helpMatch1, helpMatch2, helpMatch3;
    private Image helpSimu1, helpSimu2, helpSimu3, helpRank1, helpRank2, helpRank3, upPanel;
    
    public LoadImages() {

        try {
            
            //=> Carregando as imagens que serao utilizadas no programa
            image = ImageIO.read(new File("./apsproject/src/assets/StartMatchsImg.png"));
            wallpaper = image;
            image = ImageIO.read(new File("./apsproject/src/assets/upPanel.png"));
            upPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/MatchScreenRightPanel.png"));
            matchRightPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/SimuScreenRightPanel.png"));
            simuRightPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/CodSearchImageRank.png"));
            rankRightPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/LeftPanelMS.png"));
            leftPanelMS = image;
            image = ImageIO.read(new File("./apsproject/src/assets/MatchImg.png"));
            matchImg = image;
            image = ImageIO.read(new File("./apsproject/src/assets/SimuImg.png"));
            simuImg = image;
            image = ImageIO.read(new File("./apsproject/src/assets/mainHelpImg.png"));
            mainHelpImg = image;
            image = ImageIO.read(new File("./apsproject/src/assets/LeftPanel.png"));
            leftPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/leftPanelMain.png"));
            leftMainPanel = image;
            image = ImageIO.read(new File("./apsproject/src/assets/StartImg.png"));
            startImg = image;
            image = ImageIO.read(new File("./apsproject/src/assets/MainWallpaper.png"));
            mainWallpaper = image;
            image = ImageIO.read(new File("./apsproject/src/assets/RankingImg.png"));
            rankingImg = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HMI1.png"));
            helpMatch1 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HMI2.png"));
            helpMatch2 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HMI3.png"));
            helpMatch3 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HSI1.png"));
            helpSimu1 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HSI2.png"));
            helpSimu2 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HSI3.png"));
            helpSimu3 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HRI1.png"));
            helpRank1 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HRI2.png"));
            helpRank2 = image;
            image = ImageIO.read(new File("./apsproject/src/assets/HRI3.png"));
            helpRank3 = image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public Image getMatchImg() {
        return matchImg;
    }

    public Image getUpPanel() {
        return upPanel;
    }

    public Image getRightPanelMatch() {
        return matchRightPanel;
    }

    public Image getRightPanelSimu() {
        return simuRightPanel;
    }

    public Image getRankRightPanel() {
        return rankRightPanel;
    }

    public Image getSimuImg() {
        return simuImg;
    }

    public Image getWallpaper() {
        return wallpaper;
    }

    public Image getMainHelpImg() {
        return mainHelpImg;
    }

    public Image getLeftMainPanel() {
        return leftMainPanel;
    }

    public Image getLeftPanel() {
        return leftPanel;
    }

    public Image getLeftPanelMS() {
        return leftPanelMS;
    }

    public Image getMainWallpaper() {
        return mainWallpaper;
    }

    public Image getStartImg() {
        return startImg;
    }

    public Image getRankingImg() {
        return rankingImg;
    }

    public Image getHelpMatch1() {
        return helpMatch1;
    }

    public Image getHelpMatch2() {
        return helpMatch2;
    }

    public Image getHelpMatch3() {
        return helpMatch3;
    }

    public Image getHelpSimu1() {
        return helpSimu1;
    }

    public Image getHelpSimu2() {
        return helpSimu2;
    }

    public Image getHelpSimu3() {
        return helpSimu3;
    }

    public Image getHelpRank1() {
        return helpRank1;
    }

    public Image getHelpRank2() {
        return helpRank2;
    }

    public Image getHelpRank3() {
        return helpRank3;
    }


}
