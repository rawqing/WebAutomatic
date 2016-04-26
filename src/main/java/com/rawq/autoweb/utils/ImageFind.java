package com.rawq.autoweb.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFind {
	
	private BufferedImage passiveImage;       //被查找图片
	private BufferedImage keyImage;           //查找目标图片
   
	private int passiveImgWidth ;              //被查找图片宽度
	private int passiveImgHeight ;             //被查找图片高度
      
	private int keyImgWidth ;                  //查找目标图片宽度
	private int keyImgHeight;                 //查找目标图片高度
    
	private int[][] passiveImageRGBData ;   //被查找图片RGB数据
	private int[][] keyImageRGBData ;          //查找目标图片RGB数据
	
	
	/**
	 * 根据BufferedImage获取图片RGB数组
	 * @param bfImage
	 * @return
	 */
    public int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
             for (int w = 0; w < width; w++) {
                 //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                 result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
         }
        return result;
     }
    
    /**
      * 从本地文件读取目标图片
      * @param ImagePath - 图片绝对路径
      * @return 本地图片的BufferedImage对象
      */
    public BufferedImage getBfImageFromPath(String ImagePath) {
        BufferedImage bfImage = null;
         try {
            bfImage = ImageIO.read(new File(ImagePath));
        } catch (IOException e) {
            e.printStackTrace();
         }
         return bfImage;
     }
    
    /**
     * 判断被查找图片上目标图映射范围内的全部点是否全部和小图的点一一对应。
     * @param y - 与目标图左上角像素点想匹配的被查找图片y坐标
     * @param x - 与目标图左上角像素点想匹配的被查找图片x坐标
     * @return
     */
    private boolean isMatchAll(int y, int x) {
        int biggerY = 0;
        int biggerX = 0;
        int xor = 0;
        for(int smallerY=0; smallerY<keyImgHeight; smallerY++) {
            biggerY = y+smallerY;
            for(int smallerX=0; smallerX<keyImgWidth; smallerX++) {
                biggerX = x+smallerX;
                if(biggerY>=passiveImgHeight || biggerX>=passiveImgWidth) {
                    return false;
                }
                xor = keyImageRGBData[smallerY][smallerX]^passiveImageRGBData[biggerY][biggerX];
                if(xor!=0) {
                    return false;
                }
            }
            biggerX = x;
        }
        return true;
    }

    /**
     * 查找图片
     */
    public String findImage(String keyImagePath,String passiveImagePath) {
    	passiveImage = getBfImageFromPath(passiveImagePath);       //被查找图片
    	keyImage = getBfImageFromPath(keyImagePath);           //查找目标图片
       
    	passiveImgWidth = passiveImage.getWidth();              //被查找图片宽度
    	passiveImgHeight = passiveImage.getHeight();             //被查找图片高度
          
    	keyImgWidth = keyImage.getWidth();                  //查找目标图片宽度
    	keyImgHeight = keyImage.getHeight();                 //查找目标图片高度
        
        passiveImageRGBData = getImageGRB(passiveImage);   //被查找图片RGB数据
        keyImageRGBData = getImageGRB(keyImage);          //查找目标图片RGB数据
          
        //遍历被查找图片像素点数据
        for(int y=0; y<=passiveImgHeight-keyImgHeight; y++) {
            for(int x=0; x<=passiveImgWidth-keyImgWidth; x++) {
                //根据目标图的尺寸，得到目标图四个角映射到被查找图片上的四个点，
                //判断被查找图片上对应的四个点与图B的四个角像素点的值是否相同，
                //如果相同就将被查找图片上映射范围内的所有的点与目标图的所有的点进行比较。
                if((keyImageRGBData[0][0]^passiveImageRGBData[y][x])==0
                        && (keyImageRGBData[0][keyImgWidth-1]^passiveImageRGBData[y][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][keyImgWidth-1]^passiveImageRGBData[y+keyImgHeight-1][x+keyImgWidth-1])==0
                        && (keyImageRGBData[keyImgHeight-1][0]^passiveImageRGBData[y+keyImgHeight-1][x])==0) {
                    
                   
                    if(isMatchAll(y, x)) 		 //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                    {
                   	 	return x+","+y;
                    }
                }
            }
        }
		return "not find";
    }
}
