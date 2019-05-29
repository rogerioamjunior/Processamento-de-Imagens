

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class operacaoAritmetica {
    public static double arredondar(double x){
        int aux = (int)x;
        double count = x-aux;
        if(count<=0.5)
            return Math.floor(x);
        return  Math.ceil(x);
    }
    public static int Add(int p1,int p2){
        int result;
        double green,red,blue, alpha;
        int xA,yA,xR,yR,xG,yG,xB,yB;
       
        xA = (p1>>24)&0xff;
        yA  = (p2>>24)&0xff;
        xR = (p1>>16)&0xff;
        yR  = (p2>>16)&0xff;
        xG = (p1>>8)&0xff;
        yG  = (p2>>8)&0xff;
        xB = (p1)&0xff;
        yB  = (p2)&0xff;
        
        red  = arredondar((xR + yR)/2);
        green = arredondar((xG + yG)/2);
        blue = arredondar((xB + yB)/2);

        result =  0xff000000 | ((((int)red)<<16)&0xff0000) |
                ((((int)green)<<8)&0xff00) | ((int)blue)&0xff ;
       
        return result;
    }
    public static int Sub(int p1,int p2){
        int result;
        double green,red,blue;
        int xA,yA,xR,yR,xG,yG,xB,yB;
       
        xA = (p1>>24)&0xff;
        yA  = (p2>>24)&0xff;
        xR = (p1>>16)&0xff;
        yR  = (p2>>16)&0xff;
        xG = (p1>>8)&0xff;
        yG  = (p2>>8)&0xff;
        xB = (p1)&0xff;
        yB  = (p2)&0xff;
     
        red  = xR - yR;
        green = xG - yG;
        blue = xB - yB;

        if(red <0)
            red = 0;
        if(green<0)
            green =0;
        if(blue<0)
            blue = 0;
        result =  0xff000000 | ((((int)red)<<16)&0xff0000) |
                ((((int)green)<<8)&0xff00) | ((int)blue)&0xff ;
     
        return result;
    }
    
    public static int[][] novaImagem(int[][] m_img1,int[][]m_img2,int width,int height, int flag){
        int i,j;
        
        int[][] temp = new int[height][width];
        if(flag ==1){
            for(i=0; i<height; i++){
                for(j=0; j<width; j++){
                    temp[i][j] = Add(m_img1[i][j],m_img2[i][j]); 
                }
            }
        }
        else{
            for(i=0; i<height; i++){
                for(j=0; j<width; j++){
                    temp[i][j] = Sub(m_img1[i][j],m_img2[i][j]);
                }
            }
        }
        return temp ;
    
    }

    public static int[][] getMatrix(BufferedImage input){
             int height = input.getHeight(); 
             int width = input.getWidth();
             int [][] array = new int[height][width];
             for(int i=0; i<height; i++){
                 for(int j=0; j<width; j++)
                     array[i][j] = input.getRGB(j, i);
             }
             return array;
     }
    public static int[] toArray(int[][] mO){ // mO: Matriz Original
        int[] retorno = new int[mO.length*mO[0].length];
        for (int i = 0; i < mO.length; i++) 
            for (int j = 0; j < mO[0].length; j++)
                retorno[(i*mO[0].length)+j] = mO[i][j];
        return retorno;
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
     public static void main(String[] args) throws IOException{
        BufferedImage image = ImageIO.read(new File("321139.png"));
        BufferedImage image2 = ImageIO.read(new File("545909.png"));
        int width = image.getWidth();
	int height = image.getHeight();
        
       // image = toGrayscale(image);
       // image2 = toGrayscale(image2);
        //ImageIO.write(image, "PNG", new File("321139_Gray.png"));
        //ImageIO.write(image2, "PNG", new File("545909_Gray.png"));
        
        int[][] m_img1 = getMatrix(image);
        int[][] m_img2 = getMatrix(image2);


        int[][] resultante = novaImagem(m_img1,m_img2, width, height,1);
        int[][] resultante2 = novaImagem(m_img1,m_img2, width, height,2);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.setRGB(0, 0, width, height, toArray(resultante), 0, width);
        ImageIO.write(bufferedImage, "PNG", new File("Soma.png"));
        
        bufferedImage.setRGB(0, 0, width, height, toArray(resultante2), 0, width);
        ImageIO.write(bufferedImage, "PNG", new File("Subtracao.png"));
        
        
    }
}
    
