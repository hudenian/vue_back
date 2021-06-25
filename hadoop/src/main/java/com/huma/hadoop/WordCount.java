//package com.huma.hadoop;
//
//import com.huma.hadoop.mapper.WordMapper;
//import com.huma.hadoop.reducer.WordReducer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//
//import java.io.IOException;
//import java.net.URI;
//
///**
// * @author hudenian
// * @date 2021/6/23
// */
//@Slf4j
//public class WordCount {
//
//    private static Configuration conf;
//    private static FileSystem fs;
//
//    static {
//        try {
//            conf = new Configuration();
//            conf.set("fs.defaultFS", "hdfs://localhost:9000");
//            conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//            fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
//        } catch (Exception e) {
//            log.error("connect to hdfs fail", e);
//        }
//    }
//
//    public static void main(String[] args) {
//
//      /*  System.out.println(fs);
//        try {
//            fs.delete(new Path("/input"),true);
//            //hdfs文件系统创建目录
//            fs.mkdirs(new Path("/input"));
//
//            //上传本地文件至hdfs
//            fs.copyFromLocalFile(new Path("file:/C:/input/simple.txt"), new Path("/input"));
//        } catch (IOException e) {
//            log.error("upload file to hdfs fail:{}", e.getMessage(), e);
//        }*/
//        log.info("upload local file to hdfs success");
//        try {
//            wordCount("/input", "/output");
//        } catch (Exception e) {
//            log.error("word Count fail:{}", e.getMessage());
//        }
//    }
//
//    static void wordCount(String input, String output) throws Exception {
//        Job job = Job.getInstance(conf, "word count");
//        job.setJarByClass(WordCount.class);
//        job.setMapperClass(WordMapper.class);
//        job.setCombinerClass(WordReducer.class);
//        job.setReducerClass(WordReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(IntWritable.class);
//
//        FileInputFormat.addInputPath(job, new Path(input));
//        FileOutputFormat.setOutputPath(job, new Path(output));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//
//        //提交job到mapreduce处理
//        if (job.waitForCompletion(true)) {
//            log.info("process success");
//        } else {
//            log.error("process fail");
//        }
//
//    }
//
//}