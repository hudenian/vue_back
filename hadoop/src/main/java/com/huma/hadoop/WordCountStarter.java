package com.huma.hadoop;

import com.huma.hadoop.mapper.WordCountMapper;
import com.huma.hadoop.reducer.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.net.URI;

/**
 * @author hudenian
 * @date 2021/6/24
 */
public class WordCountStarter {

    private static Configuration conf;
    private static FileSystem fs;
    private static final int ARG_PARAM = 2;
//    private static final String  hdfdUrl= "hdfs://master:9000";
    private static final String  hdfdUrl= "hdfs://localhost:9000";

    static {
        try {
            conf = new Configuration();
            conf.set("fs.defaultFS", hdfdUrl);
            conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
            fs = FileSystem.get(new URI(hdfdUrl), conf);
        } catch (Exception e) {
            System.err.println("connect to hdfs fail");
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != ARG_PARAM) {
            System.err.println("Usage: WordCountStarter <in> <out>");
            System.exit(2);
        }
        String input = otherArgs[0];
        String output = otherArgs[1];

        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCountStarter.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        Path outputPath = new Path(output);
        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        fs.close();

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
