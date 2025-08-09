package org.apache.spark.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.commons.io.IOUtils;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.util.LimitedInputStream;
import org.apache.spark.storage.EncryptedManagedBuffer;
import org.apache.spark.util.Utils$;
import org.sparkproject.guava.io.ByteStreams;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class ChunkedByteBuffer$ implements Serializable {
   public static final ChunkedByteBuffer$ MODULE$ = new ChunkedByteBuffer$();
   private static final int CHUNK_BUFFER_SIZE = 1048576;
   private static final int MINIMUM_CHUNK_BUFFER_SIZE = 1024;

   private int CHUNK_BUFFER_SIZE() {
      return CHUNK_BUFFER_SIZE;
   }

   private int MINIMUM_CHUNK_BUFFER_SIZE() {
      return MINIMUM_CHUNK_BUFFER_SIZE;
   }

   public ChunkedByteBuffer fromManagedBuffer(final ManagedBuffer data) {
      if (data instanceof FileSegmentManagedBuffer var4) {
         return this.fromFile(var4.getFile(), var4.getOffset(), var4.getLength());
      } else if (data instanceof EncryptedManagedBuffer var5) {
         return var5.blockData().toChunkedByteBuffer((x$1) -> $anonfun$fromManagedBuffer$1(BoxesRunTime.unboxToInt(x$1)));
      } else {
         return new ChunkedByteBuffer(data.nioByteBuffer());
      }
   }

   public ChunkedByteBuffer fromFile(final File file) {
      return this.fromFile(file, 0L, file.length());
   }

   private ChunkedByteBuffer fromFile(final File file, final long offset, final long length) {
      FileInputStream is = new FileInputStream(file);
      ByteStreams.skipFully(is, offset);
      LimitedInputStream in = new LimitedInputStream(is, length);
      int chunkSize = (int).MODULE$.min(2147483632L, length);
      ChunkedByteBufferOutputStream out = new ChunkedByteBufferOutputStream(chunkSize, (x$1) -> $anonfun$fromFile$1(BoxesRunTime.unboxToInt(x$1)));
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcI.sp)() -> IOUtils.copy(in, out), (JFunction0.mcV.sp)() -> {
         in.close();
         out.close();
      });
      return out.toChunkedByteBuffer();
   }

   public int estimateBufferChunkSize(final long estimatedSize) {
      return estimatedSize < 0L ? this.CHUNK_BUFFER_SIZE() : Math.max((int)Math.min(estimatedSize, (long)this.CHUNK_BUFFER_SIZE()), this.MINIMUM_CHUNK_BUFFER_SIZE());
   }

   public long estimateBufferChunkSize$default$1() {
      return -1L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChunkedByteBuffer$.class);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$fromManagedBuffer$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$fromFile$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   private ChunkedByteBuffer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
