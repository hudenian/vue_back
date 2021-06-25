//package com.huma.hadoop.mapper;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Mapper;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
///**
// * @author hudenian
// * @date 2021/6/23
// */
//public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {
//
//    public Text keyText = new Text();
//    public IntWritable intValue = new IntWritable(1);
//
//    @Override
//    protected void map(Object key, Text value,
//                       Context context)
//            throws IOException, InterruptedException {
//        String str = value.toString();
//        StringTokenizer to = new StringTokenizer(str);
//        while (to.hasMoreTokens()) {
//            String t = to.nextToken();
//            //此处为判断统计字符串的地方
//            String countWord = "is";
//            if (t.equals(countWord)) {
//                keyText = new Text(t);
//                context.write(keyText, intValue);
//            }
//
//        }
//    }
//}