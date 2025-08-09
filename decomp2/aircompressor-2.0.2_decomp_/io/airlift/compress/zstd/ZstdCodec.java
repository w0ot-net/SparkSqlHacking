package io.airlift.compress.zstd;

import io.airlift.compress.hadoop.CodecAdapter;

public class ZstdCodec extends CodecAdapter {
   public ZstdCodec() {
      super((configuration) -> new ZstdHadoopStreams());
   }
}
