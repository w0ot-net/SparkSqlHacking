package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.CodecAdapter;
import java.util.Optional;
import org.apache.hadoop.conf.Configuration;

public class LzoCodec extends CodecAdapter {
   public LzoCodec() {
      super((configuration) -> new LzoHadoopStreams(getBufferSize(configuration)));
   }

   static int getBufferSize(Optional configuration) {
      return (Integer)configuration.map((conf) -> conf.getInt("io.compression.codec.lzo.buffersize", 262144)).orElse(262144);
   }
}
