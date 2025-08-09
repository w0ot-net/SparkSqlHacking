package org.apache.parquet.bytes;

import java.io.IOException;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LittleEndianDataOutputStream extends OutputStream {
   private static final Logger LOG = LoggerFactory.getLogger(LittleEndianDataOutputStream.class);
   private final OutputStream out;
   private byte[] writeBuffer = new byte[8];

   public LittleEndianDataOutputStream(OutputStream out) {
      this.out = out;
   }

   public void write(int b) throws IOException {
      this.out.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.out.write(b, off, len);
   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public final void writeBoolean(boolean v) throws IOException {
      this.out.write(v ? 1 : 0);
   }

   public final void writeByte(int v) throws IOException {
      this.out.write(v);
   }

   public final void writeShort(int v) throws IOException {
      this.out.write(v >>> 0 & 255);
      this.out.write(v >>> 8 & 255);
   }

   public final void writeInt(int v) throws IOException {
      this.out.write(v >>> 0 & 255);
      this.out.write(v >>> 8 & 255);
      this.out.write(v >>> 16 & 255);
      this.out.write(v >>> 24 & 255);
   }

   public final void writeLong(long v) throws IOException {
      this.writeBuffer[7] = (byte)((int)(v >>> 56));
      this.writeBuffer[6] = (byte)((int)(v >>> 48));
      this.writeBuffer[5] = (byte)((int)(v >>> 40));
      this.writeBuffer[4] = (byte)((int)(v >>> 32));
      this.writeBuffer[3] = (byte)((int)(v >>> 24));
      this.writeBuffer[2] = (byte)((int)(v >>> 16));
      this.writeBuffer[1] = (byte)((int)(v >>> 8));
      this.writeBuffer[0] = (byte)((int)(v >>> 0));
      this.out.write(this.writeBuffer, 0, 8);
   }

   public final void writeFloat(float v) throws IOException {
      this.writeInt(Float.floatToIntBits(v));
   }

   public final void writeDouble(double v) throws IOException {
      this.writeLong(Double.doubleToLongBits(v));
   }

   public void close() {
      try {
         OutputStream os = this.out;
         Throwable var2 = null;

         try {
            os.flush();
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (os != null) {
               if (var2 != null) {
                  try {
                     os.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  os.close();
               }
            }

         }
      } catch (Exception e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Exception in flushing arrayOut before close", e);
         }
      }

   }
}
