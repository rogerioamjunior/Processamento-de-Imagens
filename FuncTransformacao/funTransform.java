
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class funTransform {
    
    private static BufferedImage Gamma(BufferedImage img){
 
        int pixel[] = new int[3];
        int k;
        double r,luminancia; 
        for(int i=0; i<img.getWidth(); i++){

            for(int j=0; j<img.getHeight(); j++){

               pixel = img.getRaster().getPixel(i, j, pixel);
               r= (double)pixel[0]/(double)255;
         
               luminancia = ((Math.pow(r, 1.2)));
               k =(int) (luminancia*255);
               
               //Cria uma cor sRGB
               Color color = new Color(k, k, k);

               int rgb = color.getRGB();

               img.setRGB(i, j, rgb);

            }
        }
        return img;
    }
        private static BufferedImage Logaritmo(BufferedImage img){
        
        int pixel[] = new int[3];
        int k;
        double r,s; 
        for(int i=0; i<img.getWidth(); i++){

            for(int j=0; j<img.getHeight(); j++){

                pixel = img.getRaster().getPixel(i, j, new int[3]);
               //r nivel de cinza(intensidade de um pxel)
                r= (double)pixel[0]/(double)255;
                s = ((Math.log(1+r)));
                k =(int) (s*255);

                //Cria uma cor sRGB
                Color color = new Color(k, k, k);

                int rgb = color.getRGB();

                img.setRGB(i, j, rgb);

            }
        }
        return img;
    }
    
    private static BufferedImage toGrayscale(BufferedImage img) { 
        int pixel[] = new int[3];
        for(int i=0; i<img.getWidth(); i++){

            for(int j=0; j<img.getHeight(); j++){

                pixel = img.getRaster().getPixel(i, j, pixel);

                int k =(int)((0.3*pixel[0])+(0.59*pixel[1])+(0.11*pixel[2]));

                Color color = new Color(k, k, k);

                int rgb = color.getRGB();

               img.setRGB(i, j, rgb);

            }

        }
        return img;
    }
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("SwordGray.png"));
     //  image = toGrayscale(image);
     //   ImageIO.write(image, "PNG", new File("SwordGray.png"));
        
        image = Gamma(image);
        ImageIO.write(image, "PNG", new File("tGamma.png"));  

        image = Logaritmo(image);
        ImageIO.write(image, "PNG", new File("tLogaritmica.png"));  
    }
    
}