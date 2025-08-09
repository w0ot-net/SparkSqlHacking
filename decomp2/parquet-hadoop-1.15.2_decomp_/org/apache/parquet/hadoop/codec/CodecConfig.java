package org.apache.parquet.hadoop.codec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CodecConfig {
   private static final Logger LOG = LoggerFactory.getLogger(CodecConfig.class);

   public abstract boolean isHadoopCompressionSet();

   public abstract Class getHadoopOutputCompressorClass(Class var1);

   public abstract Configuration getConfiguration();

   public static CodecConfig from(JobConf jobConf) {
      return new MapredCodecConfig(jobConf);
   }

   public static CodecConfig from(TaskAttemptContext context) {
      return new MapreduceCodecConfig(context);
   }

   public static boolean isParquetCompressionSet(Configuration conf) {
      return conf.get("parquet.compression") != null;
   }

   public static CompressionCodecName getParquetCompressionCodec(Configuration configuration) {
      return CompressionCodecName.fromConf(configuration.get("parquet.compression", CompressionCodecName.UNCOMPRESSED.name()));
   }

   public CompressionCodecName getCodec() {
      Configuration configuration = this.getConfiguration();
      CompressionCodecName codec;
      if (isParquetCompressionSet(configuration)) {
         codec = getParquetCompressionCodec(configuration);
      } else if (this.isHadoopCompressionSet()) {
         codec = this.getHadoopCompressionCodec();
      } else {
         LOG.info("Compression set to false");
         codec = CompressionCodecName.UNCOMPRESSED;
      }

      LOG.info("Compression: {}", codec.name());
      return codec;
   }

   private CompressionCodecName getHadoopCompressionCodec() {
      CompressionCodecName codec;
      try {
         Class<?> codecClass = this.getHadoopOutputCompressorClass(CompressionCodecName.UNCOMPRESSED.getHadoopCompressionCodecClass());
         LOG.info("Compression set through hadoop codec: {}", codecClass.getName());
         codec = CompressionCodecName.fromCompressionCodec(codecClass);
      } catch (CompressionCodecNotSupportedException e) {
         LOG.warn("codec defined in hadoop config is not supported by parquet [{}] and will use UNCOMPRESSED", e.getCodecClass().getName(), e);
         codec = CompressionCodecName.UNCOMPRESSED;
      } catch (IllegalArgumentException e) {
         LOG.warn("codec class not found: {}", e.getMessage(), e);
         codec = CompressionCodecName.UNCOMPRESSED;
      }

      return codec;
   }

   private static class MapreduceCodecConfig extends CodecConfig {
      private final TaskAttemptContext context;

      public MapreduceCodecConfig(TaskAttemptContext context) {
         this.context = context;
      }

      public boolean isHadoopCompressionSet() {
         return FileOutputFormat.getCompressOutput(this.context);
      }

      public Class getHadoopOutputCompressorClass(Class defaultCodec) {
         return FileOutputFormat.getOutputCompressorClass(this.context, defaultCodec);
      }

      public Configuration getConfiguration() {
         return ContextUtil.getConfiguration(this.context);
      }
   }

   private static class MapredCodecConfig extends CodecConfig {
      private final JobConf conf;

      public MapredCodecConfig(JobConf conf) {
         this.conf = conf;
      }

      public boolean isHadoopCompressionSet() {
         return org.apache.hadoop.mapred.FileOutputFormat.getCompressOutput(this.conf);
      }

      public Class getHadoopOutputCompressorClass(Class defaultCodec) {
         return org.apache.hadoop.mapred.FileOutputFormat.getOutputCompressorClass(this.conf, defaultCodec);
      }

      public Configuration getConfiguration() {
         return this.conf;
      }
   }
}
