package com.huma.hadoop.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author hudenian
 * @date 2021/6/24
 */
public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        byte[] bt = value.getBytes();
        InputStream ip = new ByteArrayInputStream(bt);
        Reader read = new InputStreamReader(ip);
        IKSegmenter iks = new IKSegmenter(read,true);
        Lexeme t;
        while ((t = iks.next()) != null)
        {
            System.out.println("获取分词："+t.getLexemeText());
            word.set(t.getLexemeText());
            context.write(word, one);
        }

       /* StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            this.word.set(itr.nextToken());
            context.write(this.word, one);
        }*/
    }
}
