package org.apache.parquet.hadoop.example;

import org.apache.parquet.hadoop.ParquetInputFormat;

public class ExampleInputFormat extends ParquetInputFormat {
   public ExampleInputFormat() {
      super(GroupReadSupport.class);
   }
}
