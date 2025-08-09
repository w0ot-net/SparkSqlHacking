package org.xerial.snappy;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SnappyCodec {
   static final byte[] MAGIC_HEADER = new byte[]{-126, 83, 78, 65, 80, 80, 89, 0};
   public static final int MAGIC_LEN;
   public static final int HEADER_SIZE;
   public static final int MAGIC_HEADER_HEAD;
   public static final int DEFAULT_VERSION = 1;
   public static final int MINIMUM_COMPATIBLE_VERSION = 1;
   public static final SnappyCodec currentHeader;
   public final byte[] magic;
   public final int version;
   public final int compatibleVersion;
   private final byte[] headerArray;

   private SnappyCodec(byte[] var1, int var2, int var3) {
      this.magic = var1;
      this.version = var2;
      this.compatibleVersion = var3;
      ByteArrayOutputStream var4 = new ByteArrayOutputStream(HEADER_SIZE);
      DataOutputStream var5 = new DataOutputStream(var4);

      try {
         var5.write(var1, 0, MAGIC_LEN);
         var5.writeInt(var2);
         var5.writeInt(var3);
         var5.close();
      } catch (IOException var7) {
         throw new RuntimeException(var7);
      }

      this.headerArray = var4.toByteArray();
   }

   public static byte[] getMagicHeader() {
      return (byte[])MAGIC_HEADER.clone();
   }

   public String toString() {
      return String.format("version:%d, compatible version:%d", this.version, this.compatibleVersion);
   }

   public static int headerSize() {
      return HEADER_SIZE;
   }

   public int writeHeader(byte[] var1, int var2) {
      System.arraycopy(this.headerArray, 0, var1, var2, this.headerArray.length);
      return this.headerArray.length;
   }

   public int writeHeader(OutputStream var1) throws IOException {
      var1.write(this.headerArray, 0, this.headerArray.length);
      return this.headerArray.length;
   }

   public boolean isValidMagicHeader() {
      return Arrays.equals(MAGIC_HEADER, this.magic);
   }

   public static boolean hasMagicHeaderPrefix(byte[] var0) {
      int var1 = Math.min(MAGIC_LEN, var0.length);

      for(int var2 = 0; var2 < var1; ++var2) {
         if (var0[var2] != MAGIC_HEADER[var2]) {
            return false;
         }
      }

      return true;
   }

   public static SnappyCodec readHeader(InputStream var0) throws IOException {
      DataInputStream var1 = new DataInputStream(var0);
      byte[] var2 = new byte[MAGIC_LEN];
      var1.readFully(var2, 0, MAGIC_LEN);
      int var3 = var1.readInt();
      int var4 = var1.readInt();
      return new SnappyCodec(var2, var3, var4);
   }

   static {
      MAGIC_LEN = MAGIC_HEADER.length;
      HEADER_SIZE = MAGIC_LEN + 8;
      MAGIC_HEADER_HEAD = SnappyOutputStream.readInt(MAGIC_HEADER, 0);

      assert MAGIC_HEADER_HEAD < 0;

      currentHeader = new SnappyCodec(MAGIC_HEADER, 1, 1);
   }
}
