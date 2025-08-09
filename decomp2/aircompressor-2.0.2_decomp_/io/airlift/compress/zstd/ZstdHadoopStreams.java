package io.airlift.compress.zstd;

import io.airlift.compress.hadoop.HadoopInputStream;
import io.airlift.compress.hadoop.HadoopOutputStream;
import io.airlift.compress.hadoop.HadoopStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class ZstdHadoopStreams implements HadoopStreams {
   public String getDefaultFileExtension() {
      return ".zst";
   }

   public List getHadoopCodecName() {
      return Collections.singletonList("org.apache.hadoop.io.compress.ZStandardCodec");
   }

   public HadoopInputStream createInputStream(InputStream in) throws IOException {
      return new ZstdHadoopInputStream(in);
   }

   public HadoopOutputStream createOutputStream(OutputStream out) throws IOException {
      return new ZstdHadoopOutputStream(out);
   }
}
