package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.CodecAdapter;

public class LzopCodec extends CodecAdapter {
   public LzopCodec() {
      super((configuration) -> new LzopHadoopStreams(LzoCodec.getBufferSize(configuration)));
   }
}
