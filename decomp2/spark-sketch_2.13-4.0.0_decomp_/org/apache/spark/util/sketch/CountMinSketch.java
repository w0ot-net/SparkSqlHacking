package org.apache.spark.util.sketch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class CountMinSketch {
   public abstract double relativeError();

   public abstract double confidence();

   public abstract int depth();

   public abstract int width();

   public abstract long totalCount();

   public abstract void add(Object var1);

   public abstract void add(Object var1, long var2);

   public abstract void addLong(long var1);

   public abstract void addLong(long var1, long var3);

   public abstract void addString(String var1);

   public abstract void addString(String var1, long var2);

   public abstract void addBinary(byte[] var1);

   public abstract void addBinary(byte[] var1, long var2);

   public abstract long estimateCount(Object var1);

   public abstract CountMinSketch mergeInPlace(CountMinSketch var1) throws IncompatibleMergeException;

   public abstract void writeTo(OutputStream var1) throws IOException;

   public abstract byte[] toByteArray() throws IOException;

   public static CountMinSketch readFrom(InputStream in) throws IOException {
      return CountMinSketchImpl.readFrom(in);
   }

   public static CountMinSketch readFrom(byte[] bytes) throws IOException {
      InputStream in = new ByteArrayInputStream(bytes);

      CountMinSketch var2;
      try {
         var2 = readFrom(in);
      } catch (Throwable var5) {
         try {
            in.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      in.close();
      return var2;
   }

   public static CountMinSketch create(int depth, int width, int seed) {
      return new CountMinSketchImpl(depth, width, seed);
   }

   public static CountMinSketch create(double eps, double confidence, int seed) {
      return new CountMinSketchImpl(eps, confidence, seed);
   }

   public static enum Version {
      V1(1);

      private final int versionNumber;

      private Version(int versionNumber) {
         this.versionNumber = versionNumber;
      }

      int getVersionNumber() {
         return this.versionNumber;
      }

      // $FF: synthetic method
      private static Version[] $values() {
         return new Version[]{V1};
      }
   }
}
