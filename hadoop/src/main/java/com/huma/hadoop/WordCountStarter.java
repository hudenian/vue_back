package com.huma.hadoop;

import com.huma.hadoop.comparator.IntWritableDecreasingComparator;
import com.huma.hadoop.mapper.WordCountMapper;
import com.huma.hadoop.reducer.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.net.URI;
import java.util.Random;

/**
 * @author hudenian
 * @date 2021/6/24
 */
public class WordCountStarter {

    private static final int ARG_PARAM = 2;
    //    private static final String  hdfdUrl= "hdfs://master:9000";
    private static final String hdfdUrl = "hdfs://localhost:9000";
    private static Configuration conf;
    private static FileSystem fs;

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
        job.setOutputValueClass(IntWritable.class);

        Path outputPath = new Path(output);
        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        fs.close();

        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

        //定义一个临时目录
     /*   Path tempDir = new Path("/tmp/wordCount-temp-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, tempDir);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        if (job.waitForCompletion(true)) {
            Job sortJob = Job.getInstance(conf, "sort");
            sortJob.setJarByClass(WordCountStarter.class);

            FileInputFormat.addInputPath(sortJob, tempDir);
            sortJob.setInputFormatClass(SequenceFileInputFormat.class);

            *//*InverseMapper由hadoop库提供，作用是实现map()之后的数据对的key和value交换*//*
            sortJob.setMapperClass(InverseMapper.class);
            *//*将 Reducer 的个数限定为1, 最终输出的结果文件就是一个。*//*
            sortJob.setNumReduceTasks(1);

            Path outputPath = new Path(output);
            if (fs.exists(outputPath)) {
                fs.delete(outputPath, true);
            }
            fs.close();

            FileOutputFormat.setOutputPath(sortJob, new Path(output));

            sortJob.setOutputKeyClass(IntWritable.class);
            sortJob.setOutputValueClass(Text.class);
            *//*Hadoop 默认对 IntWritable 按升序排序，而我们需要的是按降序排列。
             * 因此我们实现了一个 IntWritableDecreasingComparator 类,
             * 并指定使用这个自定义的 Comparator 类对输出结果中的 key (词频)进行排序*//*
            sortJob.setSortComparatorClass(IntWritableDecreasingComparator.class);

            System.exit(sortJob.waitForCompletion(true) ? 0 : 1);
        }*/
    }
}
