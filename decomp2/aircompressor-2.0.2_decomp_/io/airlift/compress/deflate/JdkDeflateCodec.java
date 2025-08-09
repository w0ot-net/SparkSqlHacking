package io.airlift.compress.deflate;

import io.airlift.compress.hadoop.CodecAdapter;

public class JdkDeflateCodec extends CodecAdapter {
   public JdkDeflateCodec() {
      super((configuration) -> new JdkDeflateHadoopStreams());
   }
}
