import java.io.*;

public class IOTest {
    public static void main(String[] args) {
        //Reader -> InputStreamReader -> FileReader
        //Writer -> OutputStreamWriter -> FileWriter
        //Reader -> BufferedReader
        //Writer -> BufferedWriter
        //InputStream -> FileInputStream
        //OutputStream -> FileOutputStream
        //InputStream -> FilterInputStream -> BufferedInputStream
        //OutputStream -> FilterOutputStream -> BufferedOutputStream
        try {
            test1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1() throws IOException {
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 输入 'quit' 退出。");
        do {
            str = br.readLine();
            System.out.println("您输入的字符是:" + str);
        } while (!str.equals("quit"));
        br.close();
    }

    private static void test2() throws IOException {
        String path = "F:/test/hello.txt";
        String str = "hello world";
        FileWriter fw = new FileWriter(path);
        fw.write(str);
        fw.close();

        FileReader fr = new FileReader(path);
        StringBuffer sb = new StringBuffer();
        while (fr.ready()) {
            sb.append((char) fr.read());
        }
        System.out.println("输出:" + sb.toString());
        fr.close();
    }

    private static void test3() throws IOException {
        String path = "F:/test/hello.txt";
        String str = "你好!";
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(str);
        bw.close();
        fw.close();

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        while (br.ready()) {
            sb.append((char) br.read());
        }
        System.out.println("输出:" + sb.toString());
        br.close();
        fr.close();
    }

    private static void test4() throws IOException {
        String path = "F:/test/hello.txt";
        String path2 = "F:/test/你好.txt";
        String str = "你好!";
        InputStream input = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(input, "UTF-8");
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
        }
        input.close();
        reader.close();

        OutputStream output = new FileOutputStream(path2);
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(sb + str);
        writer.close();
        output.close();

        InputStream input2 = new FileInputStream(path2);
        InputStreamReader reader2 = new InputStreamReader(input2, "UTF-8");
        StringBuffer sb2 = new StringBuffer();
        while (reader2.ready()) {
            sb2.append((char) reader2.read());
        }
        System.out.println("输出:" + sb2);
        input2.close();
        reader2.close();
    }

    private static void test5() throws IOException {
        String path = "F:/test/test2";
        String path2 = "F:/test/test3/test3";
        String path3 = "F:/test/test2/test2.txt";
        File f = new File(path);
        File f2 = new File(path2);
        File f3 = new File(path3);
        //创建文件夹
        System.out.println("=" + f.mkdir());
        //创建文件夹和所有父文件夹
        System.out.println("==" + f2.mkdirs());
        //创建一个文本
        System.out.println("===" + f3.createNewFile());
        //获取名称
        System.out.println("===" + f3.getName());
        //获取父级名称
        System.out.println("===" + f3.getParent());
        //获取当前路径
        System.out.println("===" + f3.getPath());
        //判断是否是目录
        System.out.println("==" + f2.isDirectory());
        System.out.println("===" + f3.isDirectory());
        //删除该文件
        System.out.println("===" + f3.delete());
    }
}
