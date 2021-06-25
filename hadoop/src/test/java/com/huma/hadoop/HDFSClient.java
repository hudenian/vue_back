package com.huma.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author hudenian
 * @date 2021/6/23
 */
public class HDFSClient {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        //1.创建连接
        Configuration conf = new Configuration();
//        conf.set("fs.default.name","hdfs://master:9000");
//        FileSystem fs = FileSystem.get(conf);
        FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
        //创建文件夹
        fs.mkdirs(new Path("/wordcount1"));
        //删除文件夹
//        fs.delete(new Path("/wordcount1"),true);
        //3.关闭连接
        fs.close();
        System.out.println("运行结束");
    }
}
