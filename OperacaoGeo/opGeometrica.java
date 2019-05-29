import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class opGeometrica {
    public static int[][] Espelhamento(int[][] m_img1,int width,int height){
        int i,j, x;
        int[][] temp = new int[height][width];
        for(i=0; i<height; i++){
            for(j=0; j<width; j++){
                x = -j +(width -1);
                temp[i][x]=m_img1[i][j];
            }
        }
        return temp;   
    }
     public static int[][] Reflexao(int[][] m_img1,int width,int height){
        int i,j, x;
        int[][] temp = new int[height][width];
        for(i=0; i<height; i++){
            for(j=0; j<width; j++){
                x = -i +(height -1);
                temp[x][j]=m_img1[i][j];
            }
        }
        return temp;   
    }
    public static int[][] getMatrix(BufferedImage input){
             int height = input.getHeight(); 
             int width = input.getWidth();
             int [][] array = new int[height][width];
             for(int i=0; i<height; i++)
                 for(int j=0; j<width; j++)
                     array[i][j] = input.getRGB(j, i);
             return array;
     }
    public static int[] toArray(int[][] mO){ // mO: Matriz Original
        int[] retorno = new int[mO.length*mO[0].length];
        for (int i = 0; i < mO.length; i++) 
            for (int j = 0; j < mO[0].length; j++)
                retorno[(i*mO[0].length)+j] = mO[i][j];
        return retorno;
    }

     public static void main(String[] args) throws IOException{
        BufferedImage image = ImageIO.read(new File("545909.png"));
        int width = image.getWidth();
	int height = image.getHeight();

       
        int[][] m_img1 = getMatrix(image);


        int[][] resultante  =Espelhamento(m_img1, width, height);
        int[][] resultante2  =Reflexao(m_img1, width, height);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(0, 0, width, height, toArray(resultante), 0, width);
        ImageIO.write(bufferedImage, "PNG", new File("Espelhamento.png"));
        bufferedImage.setRGB(0, 0, width, height, toArray(resultante2), 0, width);
        ImageIO.write(bufferedImage, "PNG", new File("Reflet.png"));  
        
        
    }
}
    
