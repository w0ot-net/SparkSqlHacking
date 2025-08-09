package io.airlift.compress.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.hadoop.io.compress.CompressionInputStream;

final class CompressionInputStreamAdapter extends CompressionInputStream {
   private static final InputStream FAKE_INPUT_STREAM = new InputStream() {
      public int read() {
         throw new UnsupportedOperationException();
      }
   };
   private final HadoopInputStream input;
   private final PositionSupplier positionSupplier;

   public CompressionInputStreamAdapter(HadoopInputStream input, PositionSupplier positionSupplier) throws IOException {
      super(FAKE_INPUT_STREAM);
      this.input = (HadoopInputStream)Objects.requireNonNull(input, "input is null");
      this.positionSupplier = (PositionSupplier)Objects.requireNonNull(positionSupplier, "positionSupplier is null");
   }

   public int read() throws IOException {
      return this.input.read();
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.input.read(b, off, len);
   }

   public long getPos() throws IOException {
      return this.positionSupplier.getPosition();
   }

   public void resetState() {
      this.input.resetState();
   }

   public void close() throws IOException {
      try {
         super.close();
      } finally {
         this.input.close();
      }

   }

   public interface PositionSupplier {
      long getPosition() throws IOException;
   }
}
