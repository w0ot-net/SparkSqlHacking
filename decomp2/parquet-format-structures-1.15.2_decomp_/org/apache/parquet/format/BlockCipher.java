package org.apache.parquet.format;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public interface BlockCipher {
   public interface Decryptor {
      byte[] decrypt(byte[] var1, byte[] var2);

      ByteBuffer decrypt(ByteBuffer var1, byte[] var2);

      byte[] decrypt(InputStream var1, byte[] var2) throws IOException;
   }

   public interface Encryptor {
      byte[] encrypt(byte[] var1, byte[] var2);
   }
}
