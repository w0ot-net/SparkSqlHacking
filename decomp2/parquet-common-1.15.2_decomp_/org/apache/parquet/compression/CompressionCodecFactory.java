package org.apache.parquet.compression;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

public interface CompressionCodecFactory {
   BytesInputCompressor getCompressor(CompressionCodecName var1);

   BytesInputDecompressor getDecompressor(CompressionCodecName var1);

   void release();

   public interface BytesInputCompressor {
      BytesInput compress(BytesInput var1) throws IOException;

      CompressionCodecName getCodecName();

      void release();
   }

   public interface BytesInputDecompressor {
      BytesInput decompress(BytesInput var1, int var2) throws IOException;

      void decompress(ByteBuffer var1, int var2, ByteBuffer var3, int var4) throws IOException;

      void release();
   }
}
