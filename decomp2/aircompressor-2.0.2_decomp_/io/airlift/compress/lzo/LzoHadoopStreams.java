package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.HadoopInputStream;
import io.airlift.compress.hadoop.HadoopOutputStream;
import io.airlift.compress.hadoop.HadoopStreams;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LzoHadoopStreams implements HadoopStreams {
   private static final List HADOOP_CODEC_NAMES = Collections.unmodifiableList(Arrays.asList("org.apache.hadoop.io.compress.LzoCodec", "com.hadoop.compression.lzo.LzoCodec"));
   private static final int DEFAULT_OUTPUT_BUFFER_SIZE = 262144;
   private final int bufferSize;

   public LzoHadoopStreams() {
      this(262144);
   }

   public LzoHadoopStreams(int bufferSize) {
      this.bufferSize = bufferSize;
   }

   public String getDefaultFileExtension() {
      return ".lzo_deflate";
   }

   public List getHadoopCodecName() {
      return HADOOP_CODEC_NAMES;
   }

   public HadoopInputStream createInputStream(InputStream in) {
      return new LzoHadoopInputStream(in, this.bufferSize);
   }

   public HadoopOutputStream createOutputStream(OutputStream out) {
      return new LzoHadoopOutputStream(out, this.bufferSize);
   }
}
