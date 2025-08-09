package org.apache.parquet.hadoop.example;

import org.apache.hadoop.mapreduce.Job;
import org.apache.parquet.hadoop.ParquetOutputFormat;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.schema.MessageType;

public class ExampleOutputFormat extends ParquetOutputFormat {
   public static void setSchema(Job job, MessageType schema) {
      GroupWriteSupport.setSchema(schema, ContextUtil.getConfiguration(job));
   }

   public static MessageType getSchema(Job job) {
      return GroupWriteSupport.getSchema(ContextUtil.getConfiguration(job));
   }

   public ExampleOutputFormat() {
      super(new GroupWriteSupport());
   }
}
