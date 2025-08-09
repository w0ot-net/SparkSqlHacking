package org.apache.spark.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public final class SparkStreamUtils$ implements SparkStreamUtils {
   public static final SparkStreamUtils$ MODULE$ = new SparkStreamUtils$();

   static {
      SparkStreamUtils.$init$(MODULE$);
   }

   public long copyStream(final InputStream in, final OutputStream out, final boolean closeStreams, final boolean transferToEnabled) {
      return SparkStreamUtils.copyStream$(this, in, out, closeStreams, transferToEnabled);
   }

   public boolean copyStream$default$3() {
      return SparkStreamUtils.copyStream$default$3$(this);
   }

   public boolean copyStream$default$4() {
      return SparkStreamUtils.copyStream$default$4$(this);
   }

   public void copyFileStreamNIO(final FileChannel input, final WritableByteChannel output, final long startPosition, final long bytesToCopy) {
      SparkStreamUtils.copyFileStreamNIO$(this, input, output, startPosition, bytesToCopy);
   }

   private SparkStreamUtils$() {
   }
}
