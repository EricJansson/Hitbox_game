package GameFiles;

import Animations.AllAnimationData;
import DataFormats.Vector;
import MapObjects.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

import Background.*;
import Utils.*;

public class GamePanel extends JPanel implements ActionListener {
    public static GameModel model;
    public static Field field;
    public static MainGraphics mainGFX;
    public static AllAnimationData allAnimationData;
    public static Hero hero;
    static final int FRAMES_PER_SECOND = 60;
    static Timer timer;
    long startTime;
    public static long currentTime;
    public GamePanel() {
        startTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
        model = new GameModel();
        field = new Field();
        mainGFX = new MainGraphics(this);
        allAnimationData = new AllAnimationData();

        // Background.CameraView.target = null;
        // Background.CameraView.goToTile(14,10);
    }

    public void start() {
        timer = new Timer((1000 / FRAMES_PER_SECOND), this);
        timer.start();
    }

    public void updateGlobalTime() {
        GamePanel.currentTime = System.currentTimeMillis();
    }

    public void drawMap(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        CameraView.updateCam();
        field.background.render((Graphics2D) g);
        // field.background.displayMap((Graphics2D) g);
        mainGFX.renderAllEntities((Graphics2D) g);
        field.background.renderAir((Graphics2D) g);

        // BackgroundPanel.renderAllObstacles((Graphics2D) g);

        // Make another function that renders the airbournes above the entities!!!!!

        // Disable everything being dependent on the background
        g2d.setTransform(originalTransform);
        // mainGFX.displayBorderWindow((Graphics2D) g, 600, 400);
    }

    private void concludeAnimations() {
        model.updateAnimations();
    }

    public void updateModel() {
        model.updateEntities();
    }

    public void checkControls() {
        if (WindowKeyListener.a) {
            GamePanel.hero.accelerate('W');
        }
        if (WindowKeyListener.d) {
            GamePanel.hero.accelerate('E');
        }
        if (WindowKeyListener.w) {
            GamePanel.hero.accelerate('N');
        }
        if (WindowKeyListener.s) {
            GamePanel.hero.accelerate('S');
        }
        if (WindowKeyListener.space) {
            GamePanel.hero.boost();
            GamePanel.hero.renderer.startAnimation("slimeExplode", 2);
            System.out.println("BOOST!!");
            WindowKeyListener.space = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == timer && !WindowKeyListener.paused) {
            updateGlobalTime();
            concludeAnimations();
            checkControls();
            updateModel();
            repaint();
        } else {
            if (WindowKeyListener.n) {
                updateGlobalTime();
                concludeAnimations();
                WindowKeyListener.n = false;
                System.out.println("          ~~   Next frame   ~~");
                checkControls();
                updateModel();
                repaint();
            }
        }
    }


}
