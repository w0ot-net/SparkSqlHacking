package org.apache.spark.util.io;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.sparkproject.guava.primitives.UnsignedBytes;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q!\u0004\b\u0001%aA\u0001\u0002\t\u0001\u0003\u0002\u0004%\tA\t\u0005\tO\u0001\u0011\t\u0019!C\u0001Q!A\u0011\u0007\u0001B\u0001B\u0003&1\u0005\u0003\u00053\u0001\t\u0005\t\u0015!\u00034\u0011\u00151\u0004\u0001\"\u00018\u0011\u0019Y\u0004\u0001)Q\u0005y!1\u0001\n\u0001Q!\n\tCQ!\u0013\u0001\u0005B)CQA\u0014\u0001\u0005B)CQA\u0014\u0001\u0005B=CQ\u0001\u0018\u0001\u0005BuCQa\u0019\u0001\u0005B\u0011\u0014Ad\u00115v].,GMQ=uK\n+hMZ3s\u0013:\u0004X\u000f^*ue\u0016\fWN\u0003\u0002\u0010!\u0005\u0011\u0011n\u001c\u0006\u0003#I\tA!\u001e;jY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0005\u0002\u00013A\u0011!DH\u0007\u00027)\u0011q\u0002\b\u0006\u0002;\u0005!!.\u0019<b\u0013\ty2DA\u0006J]B,Ho\u0015;sK\u0006l\u0017!E2ik:\\W\r\u001a\"zi\u0016\u0014UO\u001a4fe\u000e\u0001Q#A\u0012\u0011\u0005\u0011*S\"\u0001\b\n\u0005\u0019r!!E\"ik:\\W\r\u001a\"zi\u0016\u0014UO\u001a4fe\u0006)2\r[;oW\u0016$')\u001f;f\u0005V4g-\u001a:`I\u0015\fHCA\u00150!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0011)f.\u001b;\t\u000fA\u0012\u0011\u0011!a\u0001G\u0005\u0019\u0001\u0010J\u0019\u0002%\rDWO\\6fI\nKH/\u001a\"vM\u001a,'\u000fI\u0001\bI&\u001c\bo\\:f!\tQC'\u0003\u00026W\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\bF\u00029si\u0002\"\u0001\n\u0001\t\u000b\u0001*\u0001\u0019A\u0012\t\u000bI*\u0001\u0019A\u001a\u0002\r\rDWO\\6t!\ri\u0004IQ\u0007\u0002})\u0011qhK\u0001\u000bG>dG.Z2uS>t\u0017BA!?\u0005!IE/\u001a:bi>\u0014\bCA\"G\u001b\u0005!%BA#\u001d\u0003\rq\u0017n\\\u0005\u0003\u000f\u0012\u0013!BQ=uK\n+hMZ3s\u00031\u0019WO\u001d:f]R\u001c\u0005.\u001e8l\u0003%\tg/Y5mC\ndW\rF\u0001L!\tQC*\u0003\u0002NW\t\u0019\u0011J\u001c;\u0002\tI,\u0017\r\u001a\u000b\u0005\u0017BC&\fC\u0003R\u0015\u0001\u0007!+\u0001\u0003eKN$\bc\u0001\u0016T+&\u0011Ak\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003UYK!aV\u0016\u0003\t\tKH/\u001a\u0005\u00063*\u0001\raS\u0001\u0007_\u001a47/\u001a;\t\u000bmS\u0001\u0019A&\u0002\r1,gn\u001a;i\u0003\u0011\u00198.\u001b9\u0015\u0005y\u000b\u0007C\u0001\u0016`\u0013\t\u00017F\u0001\u0003M_:<\u0007\"\u00022\f\u0001\u0004q\u0016!\u00022zi\u0016\u001c\u0018!B2m_N,G#A\u0015"
)
public class ChunkedByteBufferInputStream extends InputStream {
   private ChunkedByteBuffer chunkedByteBuffer;
   private final boolean dispose;
   private Iterator chunks;
   private ByteBuffer currentChunk;

   public ChunkedByteBuffer chunkedByteBuffer() {
      return this.chunkedByteBuffer;
   }

   public void chunkedByteBuffer_$eq(final ChunkedByteBuffer x$1) {
      this.chunkedByteBuffer = x$1;
   }

   public int available() {
      if (this.currentChunk != null && !this.currentChunk.hasRemaining() && this.chunks.hasNext()) {
         this.currentChunk = (ByteBuffer)this.chunks.next();
      }

      return this.currentChunk != null && this.currentChunk.hasRemaining() ? this.currentChunk.remaining() : 0;
   }

   public int read() {
      if (this.currentChunk != null && !this.currentChunk.hasRemaining() && this.chunks.hasNext()) {
         this.currentChunk = (ByteBuffer)this.chunks.next();
      }

      if (this.currentChunk != null && this.currentChunk.hasRemaining()) {
         return UnsignedBytes.toInt(this.currentChunk.get());
      } else {
         this.close();
         return -1;
      }
   }

   public int read(final byte[] dest, final int offset, final int length) {
      if (this.currentChunk != null && !this.currentChunk.hasRemaining() && this.chunks.hasNext()) {
         this.currentChunk = (ByteBuffer)this.chunks.next();
      }

      if (this.currentChunk != null && this.currentChunk.hasRemaining()) {
         int amountToGet = .MODULE$.min(this.currentChunk.remaining(), length);
         this.currentChunk.get(dest, offset, amountToGet);
         return amountToGet;
      } else {
         this.close();
         return -1;
      }
   }

   public long skip(final long bytes) {
      if (this.currentChunk != null) {
         int amountToSkip = (int).MODULE$.min(bytes, (long)this.currentChunk.remaining());
         this.currentChunk.position(this.currentChunk.position() + amountToSkip);
         if (this.currentChunk.remaining() == 0) {
            if (this.chunks.hasNext()) {
               this.currentChunk = (ByteBuffer)this.chunks.next();
            } else {
               this.close();
            }
         }

         return (long)amountToSkip;
      } else {
         return 0L;
      }
   }

   public void close() {
      if (this.chunkedByteBuffer() != null && this.dispose) {
         this.chunkedByteBuffer().dispose();
      }

      this.chunkedByteBuffer_$eq((ChunkedByteBuffer)null);
      this.chunks = null;
      this.currentChunk = null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$chunks$1(final ByteBuffer x$8) {
      return x$8.hasRemaining();
   }

   public ChunkedByteBufferInputStream(final ChunkedByteBuffer chunkedByteBuffer, final boolean dispose) {
      this.chunkedByteBuffer = chunkedByteBuffer;
      this.dispose = dispose;
      super();
      this.chunks = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunkedByteBuffer().getChunks()), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$chunks$1(x$8)))));
      this.currentChunk = this.chunks.hasNext() ? (ByteBuffer)this.chunks.next() : null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
