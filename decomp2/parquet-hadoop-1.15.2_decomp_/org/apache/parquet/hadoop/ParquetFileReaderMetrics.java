package org.apache.parquet.hadoop;

public enum ParquetFileReaderMetrics {
   ReadTime("time spent in reading Parquet file from storage"),
   SeekTime("time spent in seek when reading Parquet file from storage"),
   ReadSize("read size when reading Parquet file from storage (MB)"),
   ReadThroughput("read throughput when reading Parquet file from storage (MB/sec)"),
   DecompressTime("time spent in block decompression"),
   DecompressSize("decompressed data size (MB)"),
   DecompressThroughput("block decompression throughput (MB/sec)");

   private final String desc;

   private ParquetFileReaderMetrics(String desc) {
      this.desc = desc;
   }

   public String description() {
      return this.desc;
   }
}
