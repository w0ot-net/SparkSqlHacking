package io.airlift.compress.gzip;

import io.airlift.compress.hadoop.CodecAdapter;

public class JdkGzipCodec extends CodecAdapter {
   public JdkGzipCodec() {
      super((configuration) -> new JdkGzipHadoopStreams());
   }
}
