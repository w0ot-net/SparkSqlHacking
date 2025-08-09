package io.airlift.compress.bzip2;

import io.airlift.compress.hadoop.CodecAdapter;

public class BZip2Codec extends CodecAdapter {
   public BZip2Codec() {
      super((configuration) -> new BZip2HadoopStreams());
   }
}
