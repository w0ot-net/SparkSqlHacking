package io.airlift.compress.bzip2;

import io.airlift.compress.hadoop.HadoopInputStream;
import io.airlift.compress.hadoop.HadoopOutputStream;
import io.airlift.compress.hadoop.HadoopStreams;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class BZip2HadoopStreams implements HadoopStreams {
   public String getDefaultFileExtension() {
      return ".bz2";
   }

   public List getHadoopCodecName() {
      return Collections.singletonList("org.apache.hadoop.io.compress.BZip2Codec");
   }

   public HadoopInputStream createInputStream(InputStream in) {
      return new BZip2HadoopInputStream(in);
   }

   public HadoopOutputStream createOutputStream(OutputStream out) {
      return new BZip2HadoopOutputStream(out);
   }
}
