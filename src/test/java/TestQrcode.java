import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xu wen feng 8/3/2018 16:27
 */
public class TestQrcode {

    //@Test
    public void qrcode() throws Exception {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        jsonObject.put("type", "device");
        jsonObject.put("dname", "11111");
        jsonObject.put("ne1did", "22222");
        String content = jsonObject.toString();
        int width = 300;
        int height = 300;
        String format = "png";
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        Path path = Paths.get("/tmp/qrcode.png");
        System.out.println("path=" + path);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        createImage();
        String[] files = {"/tmp/pic.png","/tmp/qrcode.png"};
        String targetFile = "/tmp/targetFile.png";
        mergeImage(files,1,targetFile);
        System.out.println("输出成功");
    }

    private static void insert2Image(String path, String insertContent) throws IOException {
        BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 250, 30);//填充整个屏幕
        g.setColor(Color.BLACK);
        g.scale(1.5, 1.5); //压缩或放大图片
        char[] data = insertContent.toCharArray();
        g.drawChars(data, 0, data.length, 10, 15);
        Thumbnails.of(path).size(300, 300)
                .watermark(Positions.CENTER_LEFT, bi, 0.9f)
                .toFile(new File(path));

    }

    public static void mergeImage(String[] files, int type, String targetFile) {
        int len = files.length;
        if (len < 1) {
            throw new RuntimeException("图片数量小于1");
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                src[i] = new File(files[i]);
                images[i] = ImageIO.read(src[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int width = images[i].getWidth();
            int height = images[i].getHeight();
            ImageArrays[i] = new int[width * height];
            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);
        }
        int newHeight = 0;
        int newWidth = 0;
        for (int i = 0; i < images.length; i++) {
            // 横向
            if (type == 1) {
                newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
                newWidth += images[i].getWidth();
            } else if (type == 2) {// 纵向
                newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
                newHeight += images[i].getHeight();
            }
        }
        if (type == 1 && newWidth < 1) {
            return;
        }
        if (type == 2 && newHeight < 1) {
            return;
        }

        // 生成新图片
        try {
            BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            int height_i = 0;
            int width_i = 0;
            for (int i = 0; i < images.length; i++) {
                if (type == 1) {
                    ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, ImageArrays[i], 0,
                            images[i].getWidth());
                    width_i += images[i].getWidth();
                } else if (type == 2) {
                    ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), ImageArrays[i], 0, newWidth);
                    height_i += images[i].getHeight();
                }
            }
            //输出想要的图片
            ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //@Test
    public void createImage() throws IOException {
        File file = new File("/tmp/pic.png");
        String s="ssssssssssssss";
        String s1 = "sss1111111111";
        String s2 = "sss2222222222";
        Font font = new Font("微软雅黑", Font.BOLD, 25);
        BufferedImage bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, 300, 300);
        g2.setPaint(Color.BLACK);
        g2.setFont(font);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(s, context);
        double x = (300 - bounds.getWidth()) / 2;
        double y = (300 - bounds.getHeight()) / 2;
        double y1 = (150 - bounds.getHeight()) / 2;
        double y2 = (450 - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        g2.drawString(s, (int) x, (int) (y + ascent));
        g2.drawString(s1, (int) x, (int) (y1 + ascent));
        g2.drawString(s2, (int) x, (int) (y2 + ascent));
        ImageIO.write(bi, "png", file);
    }
}
