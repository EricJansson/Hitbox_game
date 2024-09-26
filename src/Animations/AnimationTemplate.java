package Animations;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This will hold the animation image, sub-image amount and sizes
 * Here we will only add the image data, NOT create the actual animation with selected frames and time spans
 */
public class AnimationTemplate {
    public BufferedImage image = null;
    public String imageName;
    public int subImageWidth;
    public int subImageHeight;
    public int subImageVerticalCount;
    public int subImageHorizontalCount;

    /**
     * The function REQUIRES that the image is the correct size.
     * For example: If the base-image is 10 pixels too wide at the end, it will skew the whole animation
     * @param imageName is the filename without extension. (expects .png)
     * @param verticalImages The amount of desired vertical subimages in the base-image
     * @param horizontalImages The amount of desired horizontal subimages in the base-image
     */
    public AnimationTemplate(String imageName, int horizontalImages, int verticalImages) {
        if(image == null && imageName != null) {
            try {
                image = ImageIO.read(new File(".\\src\\assets\\AnimationImages\\" + imageName + ".png"));
            } catch (Exception e) {
                System.out.println("AnimationTemplate() init Error!");
                e.printStackTrace();
            }
        }
        this.imageName = imageName;
        this.subImageVerticalCount = verticalImages;
        this.subImageHorizontalCount = horizontalImages;

        int imgWidth = getImage().getWidth();
        int imgHeight = getImage().getHeight();

        this.subImageWidth = imgWidth / horizontalImages;
        this.subImageHeight = imgHeight / verticalImages;
    }

    public BufferedImage getSubImage(int xIndex, int yIndex) {
        int x = xIndex * subImageWidth;
        int y = yIndex * subImageHeight;
        return image.getSubimage(x, y, subImageWidth, subImageHeight);
    }


    public BufferedImage getImage() { return image; }

}
