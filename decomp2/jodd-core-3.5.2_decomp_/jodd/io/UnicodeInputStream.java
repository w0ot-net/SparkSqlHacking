package jodd.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class UnicodeInputStream extends InputStream {
   public static final int MAX_BOM_SIZE = 4;
   private PushbackInputStream internalInputStream;
   private boolean initialized;
   private int BOMSize = -1;
   private String encoding;
   private String targetEncoding;
   public static final byte[] BOM_UTF32_BE = new byte[]{0, 0, -2, -1};
   public static final byte[] BOM_UTF32_LE = new byte[]{-1, -2, 0, 0};
   public static final byte[] BOM_UTF8 = new byte[]{-17, -69, -65};
   public static final byte[] BOM_UTF16_BE = new byte[]{-2, -1};
   public static final byte[] BOM_UTF16_LE = new byte[]{-1, -2};

   public UnicodeInputStream(InputStream in, String targetEncoding) {
      this.internalInputStream = new PushbackInputStream(in, 4);
      this.targetEncoding = targetEncoding;
   }

   public String getDetectedEncoding() {
      if (!this.initialized) {
         try {
            this.init();
         } catch (IOException ioex) {
            throw new IllegalStateException(ioex);
         }
      }

      return this.encoding;
   }

   protected void init() throws IOException {
      if (!this.initialized) {
         if (this.targetEncoding == null) {
            byte[] bom = new byte[4];
            int n = this.internalInputStream.read(bom, 0, bom.length);
            int unread;
            if (bom[0] == BOM_UTF32_BE[0] && bom[1] == BOM_UTF32_BE[1] && bom[2] == BOM_UTF32_BE[2] && bom[3] == BOM_UTF32_BE[3]) {
               this.encoding = "UTF-32BE";
               unread = n - 4;
            } else if (bom[0] == BOM_UTF32_LE[0] && bom[1] == BOM_UTF32_LE[1] && bom[2] == BOM_UTF32_LE[2] && bom[3] == BOM_UTF32_LE[3]) {
               this.encoding = "UTF-32LE";
               unread = n - 4;
            } else if (bom[0] == BOM_UTF8[0] && bom[1] == BOM_UTF8[1] && bom[2] == BOM_UTF8[2]) {
               this.encoding = "UTF-8";
               unread = n - 3;
            } else if (bom[0] == BOM_UTF16_BE[0] && bom[1] == BOM_UTF16_BE[1]) {
               this.encoding = "UTF-16BE";
               unread = n - 2;
            } else if (bom[0] == BOM_UTF16_LE[0] && bom[1] == BOM_UTF16_LE[1]) {
               this.encoding = "UTF-16LE";
               unread = n - 2;
            } else {
               unread = n;
            }

            this.BOMSize = 4 - unread;
            if (unread > 0) {
               this.internalInputStream.unread(bom, n - unread, unread);
            }
         } else {
            byte[] bom = null;
            if (this.targetEncoding.equals("UTF-8")) {
               bom = BOM_UTF8;
            } else if (this.targetEncoding.equals("UTF-16LE")) {
               bom = BOM_UTF16_LE;
            } else if (!this.targetEncoding.equals("UTF-16BE") && !this.targetEncoding.equals("UTF-16")) {
               if (this.targetEncoding.equals("UTF-32LE")) {
                  bom = BOM_UTF32_LE;
               } else if (this.targetEncoding.equals("UTF-32BE") || this.targetEncoding.equals("UTF-32")) {
                  bom = BOM_UTF32_BE;
               }
            } else {
               bom = BOM_UTF16_BE;
            }

            if (bom != null) {
               byte[] fileBom = new byte[bom.length];
               int n = this.internalInputStream.read(fileBom, 0, bom.length);
               boolean bomDetected = true;

               for(int i = 0; i < n; ++i) {
                  if (fileBom[i] != bom[i]) {
                     bomDetected = false;
                     break;
                  }
               }

               if (!bomDetected) {
                  this.internalInputStream.unread(fileBom, 0, fileBom.length);
               }
            }
         }

         this.initialized = true;
      }
   }

   public void close() throws IOException {
      this.internalInputStream.close();
   }

   public int read() throws IOException {
      this.init();
      return this.internalInputStream.read();
   }

   public int getBOMSize() {
      return this.BOMSize;
   }
}
