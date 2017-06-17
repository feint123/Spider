package com.feint.utilities;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 */
public class FileUtils{
    //download file from internet
    public static void download(URL url,String filePath)throws Exception{
        InputStream input=url.openConnection().getInputStream();

        File file=new File(filePath);
        FileOutputStream fileOut=new FileOutputStream(file);

        int len=0;
        byte[]  bs=new byte[1024];

        while((len=input.read(bs))!=-1){
            fileOut.write(bs);
            fileOut.flush();
        }

        input.close();
        fileOut.close();
    }
}