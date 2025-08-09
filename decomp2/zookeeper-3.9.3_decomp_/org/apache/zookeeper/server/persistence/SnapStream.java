package org.apache.zookeeper.server.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.zookeeper.common.AtomicFileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.SnappyCodec;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

public class SnapStream {
   private static final Logger LOG = LoggerFactory.getLogger(SnapStream.class);
   public static final String ZOOKEEPER_SHAPSHOT_STREAM_MODE = "zookeeper.snapshot.compression.method";
   private static StreamMode streamMode;

   public static CheckedInputStream getInputStream(File file) throws IOException {
      FileInputStream fis = new FileInputStream(file);

      try {
         InputStream is;
         switch (getStreamMode(file.getName())) {
            case GZIP:
               is = new GZIPInputStream(fis);
               break;
            case SNAPPY:
               is = new SnappyInputStream(fis);
               break;
            case CHECKED:
            default:
               is = new BufferedInputStream(fis);
         }

         return new CheckedInputStream(is, new Adler32());
      } catch (IOException e) {
         fis.close();
         throw e;
      }
   }

   public static CheckedOutputStream getOutputStream(File file, boolean fsync) throws IOException {
      OutputStream fos = (OutputStream)(fsync ? new AtomicFileOutputStream(file) : new FileOutputStream(file));
      OutputStream os;
      switch (streamMode) {
         case GZIP:
            try {
               os = new GZIPOutputStream(fos);
               break;
            } catch (IOException e) {
               fos.close();
               throw e;
            }
         case SNAPPY:
            os = new SnappyOutputStream(fos);
            break;
         case CHECKED:
         default:
            os = new BufferedOutputStream(fos);
      }

      return new CheckedOutputStream(os, new Adler32());
   }

   public static void sealStream(CheckedOutputStream os, OutputArchive oa) throws IOException {
      long val = os.getChecksum().getValue();
      oa.writeLong(val, "val");
      oa.writeString("/", "path");
   }

   public static void checkSealIntegrity(CheckedInputStream is, InputArchive ia) throws IOException {
      long checkSum = is.getChecksum().getValue();
      long val = ia.readLong("val");
      ia.readString("path");
      if (val != checkSum) {
         throw new IOException("CRC corruption");
      }
   }

   public static boolean isValidSnapshot(File file) throws IOException {
      if (file != null && Util.getZxidFromName(file.getName(), "snapshot") != -1L) {
         boolean isValid = false;
         switch (getStreamMode(file.getName())) {
            case GZIP:
               isValid = isValidGZipStream(file);
               break;
            case SNAPPY:
               isValid = isValidSnappyStream(file);
               break;
            case CHECKED:
            default:
               isValid = isValidCheckedStream(file);
         }

         return isValid;
      } else {
         return false;
      }
   }

   public static void setStreamMode(StreamMode mode) {
      streamMode = mode;
   }

   public static StreamMode getStreamMode() {
      return streamMode;
   }

   public static StreamMode getStreamMode(String fileName) {
      String[] splitSnapName = fileName.split("\\.");
      if (splitSnapName.length > 1) {
         String mode = splitSnapName[splitSnapName.length - 1];
         return SnapStream.StreamMode.fromString(mode);
      } else {
         return SnapStream.StreamMode.CHECKED;
      }
   }

   private static boolean isValidGZipStream(File f) throws IOException {
      byte[] byteArray = new byte[2];

      try {
         FileInputStream fis = new FileInputStream(f);

         boolean var10;
         label40: {
            boolean var6;
            try {
               if (2 != fis.read(byteArray, 0, 2)) {
                  LOG.error("Read incorrect number of bytes from {}", f.getName());
                  var10 = false;
                  break label40;
               }

               ByteBuffer bb = ByteBuffer.wrap(byteArray);
               byte[] magicHeader = new byte[2];
               bb.get(magicHeader, 0, 2);
               int magic = magicHeader[0] & 255 | magicHeader[1] << 8 & '\uff00';
               var6 = magic == 35615;
            } catch (Throwable var8) {
               try {
                  fis.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            fis.close();
            return var6;
         }

         fis.close();
         return var10;
      } catch (FileNotFoundException e) {
         LOG.error("Unable to open file {}", f.getName(), e);
         return false;
      }
   }

   private static boolean isValidSnappyStream(File f) throws IOException {
      byte[] byteArray = new byte[SnappyCodec.MAGIC_LEN];

      try {
         FileInputStream fis = new FileInputStream(f);

         boolean var9;
         label36: {
            boolean var5;
            try {
               if (SnappyCodec.MAGIC_LEN != fis.read(byteArray, 0, SnappyCodec.MAGIC_LEN)) {
                  LOG.error("Read incorrect number of bytes from {}", f.getName());
                  var9 = false;
                  break label36;
               }

               ByteBuffer bb = ByteBuffer.wrap(byteArray);
               byte[] magicHeader = new byte[SnappyCodec.MAGIC_LEN];
               bb.get(magicHeader, 0, SnappyCodec.MAGIC_LEN);
               var5 = Arrays.equals(magicHeader, SnappyCodec.getMagicHeader());
            } catch (Throwable var7) {
               try {
                  fis.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }

               throw var7;
            }

            fis.close();
            return var5;
         }

         fis.close();
         return var9;
      } catch (FileNotFoundException e) {
         LOG.error("Unable to open file {}", f.getName(), e);
         return false;
      }
   }

   private static boolean isValidCheckedStream(File f) throws IOException {
      RandomAccessFile raf = new RandomAccessFile(f, "r");

      boolean var11;
      label57: {
         boolean var12;
         label56: {
            boolean var8;
            label55: {
               try {
                  if (raf.length() < 10L) {
                     var11 = false;
                     break label57;
                  }

                  raf.seek(raf.length() - 5L);
                  byte[] bytes = new byte[5];

                  int readlen;
                  int l;
                  for(readlen = 0; readlen < 5 && (l = raf.read(bytes, readlen, bytes.length - readlen)) >= 0; readlen += l) {
                  }

                  if (readlen != bytes.length) {
                     LOG.info("Invalid snapshot {}. too short, len = {} bytes", f.getName(), readlen);
                     var12 = false;
                     break label56;
                  }

                  ByteBuffer bb = ByteBuffer.wrap(bytes);
                  int len = bb.getInt();
                  byte b = bb.get();
                  if (len != 1 || b != 47) {
                     LOG.info("Invalid snapshot {}. len = {}, byte = {}", new Object[]{f.getName(), len, b & 255});
                     var8 = false;
                     break label55;
                  }
               } catch (Throwable var10) {
                  try {
                     raf.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }

                  throw var10;
               }

               raf.close();
               return true;
            }

            raf.close();
            return var8;
         }

         raf.close();
         return var12;
      }

      raf.close();
      return var11;
   }

   static {
      streamMode = SnapStream.StreamMode.fromString(System.getProperty("zookeeper.snapshot.compression.method", SnapStream.StreamMode.DEFAULT_MODE.getName()));
      LOG.info("{} = {}", "zookeeper.snapshot.compression.method", streamMode);
   }

   public static enum StreamMode {
      GZIP("gz"),
      SNAPPY("snappy"),
      CHECKED("");

      public static final StreamMode DEFAULT_MODE = CHECKED;
      private String name;

      private StreamMode(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public String getFileExtension() {
         return this.name.isEmpty() ? "" : "." + this.name;
      }

      public static StreamMode fromString(String name) {
         for(StreamMode c : values()) {
            if (c.getName().compareToIgnoreCase(name) == 0) {
               return c;
            }
         }

         return DEFAULT_MODE;
      }
   }
}
