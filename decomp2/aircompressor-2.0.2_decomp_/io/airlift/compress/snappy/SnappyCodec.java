package io.airlift.compress.snappy;

import io.airlift.compress.hadoop.CodecAdapter;
import java.util.Optional;
import org.apache.hadoop.conf.Configuration;

public class SnappyCodec extends CodecAdapter {
   public SnappyCodec() {
      super((configuration) -> new SnappyHadoopStreams(getBufferSize(configuration)));
   }

   private static int getBufferSize(Optional configuration) {
      return (Integer)configuration.map((conf) -> conf.getInt("io.compression.codec.snappy.buffersize", 262144)).orElse(262144);
   }
}
