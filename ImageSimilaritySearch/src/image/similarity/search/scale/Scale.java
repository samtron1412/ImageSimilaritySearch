package image.similarity.search.scale;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Scale {
  public static BufferedImage resizeImage(BufferedImage image, int type, int newWidth, int newHeight) {
    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
    Graphics2D graphic = resizedImage.createGraphics();
    graphic.drawImage(image, 0, 0, newWidth, newHeight, null);
    graphic.dispose();
    return resizedImage;
  }
  
//  private static BufferedImage img;
//  public static void main(String[] args) {
//    // TODO Auto-generated method stub
//    try {
//      img = ImageIO.read(new File("/Users/admin/Downloads/skull.jpg"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    
//    int type = img.getType();
//    if (type==0) {
//      type = BufferedImage.TYPE_INT_ARGB;
//    }
//    BufferedImage resizedImage = resizeImage(img, type, 200, 200);
//    
//    try {
//      ImageIO.write(resizedImage, "jpg", new File("/Users/admin/Downloads/skull_resized.jpg"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
}
