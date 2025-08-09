package io.airlift.compress.lz4;

import io.airlift.compress.hadoop.CodecAdapter;
import java.util.Optional;
import org.apache.hadoop.conf.Configuration;

public class Lz4Codec extends CodecAdapter {
   public Lz4Codec() {
      super((configuration) -> new Lz4HadoopStreams(getBufferSize(configuration)));
   }

   private static int getBufferSize(Optional configuration) {
      return (Integer)configuration.map((conf) -> conf.getInt("io.compression.codec.lz4.buffersize", 262144)).orElse(262144);
   }
}
