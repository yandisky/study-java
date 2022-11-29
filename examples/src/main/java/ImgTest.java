import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String image = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F28%2F146442648401554353.jpeg&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624266708&t=a5be96eb677e69566d5d4aed0582c356";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(image);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5 * 1000);
            inputStream = httpURLConnection.getInputStream();
            //验证不同读取方式的时间
            long start = System.currentTimeMillis();
            //逐个字节读取
            /*outputStream = new FileOutputStream("target/iotest.jpg");
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }*/
            //内存块读取
            /*outputStream = new FileOutputStream("target/iotest.jpg");
            byte[] bytes = new byte[2 * 1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }*/
            //缓存流
            outputStream = new BufferedOutputStream(new FileOutputStream("target/iotest.jpg"));
            byte[] bytes = new byte[2 * 1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            //输出执行时间
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
