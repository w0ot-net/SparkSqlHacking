package org.apache.orc;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface CompressionCodec extends Closeable {
   Options getDefaultOptions();

   boolean compress(ByteBuffer var1, ByteBuffer var2, ByteBuffer var3, Options var4) throws IOException;

   void decompress(ByteBuffer var1, ByteBuffer var2) throws IOException;

   void reset();

   void destroy();

   CompressionKind getKind();

   void close();

   public static enum SpeedModifier {
      FASTEST,
      FAST,
      DEFAULT;

      // $FF: synthetic method
      private static SpeedModifier[] $values() {
         return new SpeedModifier[]{FASTEST, FAST, DEFAULT};
      }
   }

   public static enum DataKind {
      TEXT,
      BINARY;

      // $FF: synthetic method
      private static DataKind[] $values() {
         return new DataKind[]{TEXT, BINARY};
      }
   }

   public interface Options {
      Options copy();

      Options setSpeed(SpeedModifier var1);

      Options setData(DataKind var1);
   }
}
