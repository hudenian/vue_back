package com.huma.hadoop.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author hudenian
 * @date 2021/6/23
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    public IntWritable intValue = new IntWritable(0);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> value,
                          Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        while (value.iterator().hasNext()) {
            sum += value.iterator().next().get();
        }
        intValue.set(sum);
        context.write(key, intValue);
    }
}

