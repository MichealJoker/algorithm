package h;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: jzh
 * @date: created in 2021/5/2
 * @description: 用来爬漫画的
 * @version: 1.0
 */
public class miaoshenshi implements Runnable{


    String name ;
    String phName;
    int page;

    public static void main(String[] args) {
        String title= "[アキレルショウジョ (アキレ)] 僕だけに甘えさせてくれる巨乳Jk妻を他の男に抱かせてみた 5";
        String photo = "https://i1.pthcdn.xyz/galleries/2012452/";
        int num = 41;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        miaoshenshi m1 = new miaoshenshi();
        m1.name = title;
        m1.phName = photo;
        m1.page = num;
        miaoshenshi m2 = new miaoshenshi();
        m2.name = title;
        m2.phName = photo;
        m2.page = num;
        executorService.execute(m1);
        executorService.execute(m2);
        miaoshenshi m3 = new miaoshenshi();
        m3.name = title;
        m3.phName = photo;
        m3.page = num;
        executorService.execute(m3);
        miaoshenshi m4 = new miaoshenshi();
        m4.name = title;
        m4.phName = photo;
        m4.page = num;
        executorService.execute(m4);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if(name.endsWith("4")||name.endsWith("2")){
            while (true){
                doIt2(this.name,phName,page);
            }
        }else{
            while (true){
                doIt(this.name,phName,page);
            }
        }

    }

    public static void doIt(String name, String pname,int page){
        String str = pname+"{plz}.jpg";
        if(!new File("H:\\漫画\\"+name).exists()){
            new File("H:\\漫画\\"+name).mkdirs();
        }
        for (int i = 1; i <= page; i++) {
            String path = str.replace("{plz}", "" + i);
            File file1 = new File("H:\\漫画\\"+name+"\\" + i + ".jpg");
            try {
                getFile(file1,path);
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    getFile(file1,path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void doIt2(String name,String pname,int page){
        String str = pname+"{plz}.jpg";
        if(!new File("H:\\漫画\\"+name).exists()){
            new File("H:\\漫画\\"+name).mkdirs();
        }
        for (int i = page; i >= 1; i--) {
            String path = str.replace("{plz}", "" + i);
            File file1 = new File("H:\\漫画\\"+name+"\\" + i + ".jpg");
            try {
                getFile(file1,path);
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    getFile(file1,path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void getFile(File file1,String path) throws IOException {
        if(file1.exists()){
            return;
        }
        URL url = new URL(path);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36 QIHU 360SE");
        // 输入流
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        OutputStream out = new FileOutputStream(file1);
        while ((len = is.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        out.flush();
        out.close();
    }
}
