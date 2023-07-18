import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundPanel {
    static public BufferedImage image = null;
    static final String IMG_FILE_NAME = "background";
    static public Vector position;
    public int height;
    public int width;

    BackgroundPanel() {
        position = new Vector(0,0);
        if(image == null) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\" + IMG_FILE_NAME + ".jpg"));
            } catch (Exception e) {
                System.out.println("Error!");
                e.printStackTrace();
            }
        }
        height = getImage().getHeight();
        width = getImage().getWidth();
    }


    public void render(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        transform.translate(position.getX(), position.getY());
        // Apply the translation using the AffineTransform
        g2d.drawImage(getImage(), transform, null);
        g2d.setTransform(transform);
    }


    public static void setBackgroundPos(Vector pos) {
        position = pos;
    }

    public BufferedImage getImage() { return image; }

}
