package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.HadoopInputStream;
import io.airlift.compress.hadoop.HadoopOutputStream;
import io.airlift.compress.hadoop.HadoopStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class LzopHadoopStreams implements HadoopStreams {
   private static final int DEFAULT_OUTPUT_BUFFER_SIZE = 262144;
   private final int bufferSize;

   public LzopHadoopStreams() {
      this(262144);
   }

   public LzopHadoopStreams(int bufferSize) {
      this.bufferSize = bufferSize;
   }

   public String getDefaultFileExtension() {
      return ".lzo";
   }

   public List getHadoopCodecName() {
      return Collections.singletonList("com.hadoop.compression.lzo.LzopCodec");
   }

   public HadoopInputStream createInputStream(InputStream in) throws IOException {
      return new LzopHadoopInputStream(in, this.bufferSize);
   }

   public HadoopOutputStream createOutputStream(OutputStream out) throws IOException {
      return new LzopHadoopOutputStream(out, this.bufferSize);
   }
}
