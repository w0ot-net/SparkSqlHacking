package io.airlift.compress.gzip;

import io.airlift.compress.hadoop.HadoopInputStream;
import io.airlift.compress.hadoop.HadoopOutputStream;
import io.airlift.compress.hadoop.HadoopStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class JdkGzipHadoopStreams implements HadoopStreams {
   private static final int GZIP_BUFFER_SIZE = 8192;

   public String getDefaultFileExtension() {
      return ".gz";
   }

   public List getHadoopCodecName() {
      return Collections.singletonList("org.apache.hadoop.io.compress.GzipCodec");
   }

   public HadoopInputStream createInputStream(InputStream in) throws IOException {
      return new JdkGzipHadoopInputStream(in, 8192);
   }

   public HadoopOutputStream createOutputStream(OutputStream out) throws IOException {
      return new JdkGzipHadoopOutputStream(out, 8192);
   }
}
