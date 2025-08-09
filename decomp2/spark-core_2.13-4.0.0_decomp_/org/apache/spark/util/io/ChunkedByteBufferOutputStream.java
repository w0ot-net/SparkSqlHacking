package org.apache.spark.util.io;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.storage.StorageUtils$;
import scala.Function1;
import scala.Predef.;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Q!\u0005\n\u0001-qAA\u0002\n\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\n\u0019BA\u0002\f\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\n5BQA\u000e\u0001\u0005\u0002]BaA\u0010\u0001!B\u0013y\u0004\u0002\u0004\"\u0001\t\u0003\u0005)\u0011!b\u0001\n\u0013\u0019\u0005\"\u0003'\u0001\u0005\u0003\u0005\t\u0015!\u0003E\u00111i\u0005\u0001\"A\u0001\u0006\u0003\u0005\t\u0015)\u0003'\u00111q\u0005\u0001\"A\u0001\u0006\u0003\u0005\t\u0015)\u0003'\u0011\u0019y\u0005\u0001)Q\u0005!\"11\u000b\u0001Q!\n}BQ\u0001\u0016\u0001\u0005\u0002UCQA\u0016\u0001\u0005B]CQa\u0017\u0001\u0005BqCQa\u0017\u0001\u0005B}CQ\u0001\u001c\u0001\u0005\n]CQ!\u001d\u0001\u0005\u0002I\u0014Qd\u00115v].,GMQ=uK\n+hMZ3s\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0006\u0003'Q\t!![8\u000b\u0005U1\u0012\u0001B;uS2T!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\n\u0003\u0001u\u0001\"A\b\u0012\u000e\u0003}Q!a\u0005\u0011\u000b\u0003\u0005\nAA[1wC&\u00111e\b\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001B_J<G%\u00199bG\",Ge\u001d9be.$S\u000f^5mI%|Ge\u00115v].,GMQ=uK\n+hMZ3s\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0013%G\",hn[*ju\u0016\u001c\u0001\u0001\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0002J]R\f\u0011i\u001c:hI\u0005\u0004\u0018m\u00195fIM\u0004\u0018M]6%kRLG\u000eJ5pI\rCWO\\6fI\nKH/\u001a\"vM\u001a,'oT;uaV$8\u000b\u001e:fC6$C%\u00197m_\u000e\fGo\u001c:\u0011\t\u001drc\u0005M\u0005\u0003_!\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005E\"T\"\u0001\u001a\u000b\u0005M\u0002\u0013a\u00018j_&\u0011QG\r\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u00029uq\u0002\"!\u000f\u0001\u000e\u0003IAQaO\u0002A\u0002\u0019\n\u0011b\u00195v].\u001c\u0016N_3\t\u000bu\u001a\u0001\u0019A\u0017\u0002\u0013\u0005dGn\\2bi>\u0014\u0018\u0001\b;p\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014x+Y:DC2dW\r\u001a\t\u0003O\u0001K!!\u0011\u0015\u0003\u000f\t{w\u000e\\3b]\u0006qtN]4%CB\f7\r[3%gB\f'o\u001b\u0013vi&dG%[8%\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014x*\u001e;qkR\u001cFO]3b[\u0012\"3\r[;oWN,\u0012\u0001\u0012\t\u0004\u000b*\u0003T\"\u0001$\u000b\u0005\u001dC\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\u0013\"\n!bY8mY\u0016\u001cG/[8o\u0013\tYeIA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\u0018aP8sO\u0012\n\u0007/Y2iK\u0012\u001a\b/\u0019:lIU$\u0018\u000e\u001c\u0013j_\u0012\u001a\u0005.\u001e8lK\u0012\u0014\u0015\u0010^3Ck\u001a4WM](viB,Ho\u0015;sK\u0006lG\u0005J2ik:\\7\u000fI\u0001G_J<G%\u00199bG\",Ge\u001d9be.$S\u000f^5mI%|Ge\u00115v].,GMQ=uK\n+hMZ3s\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0013%Y\u0006\u001cHo\u00115v].Le\u000eZ3y\u0003\u0001{'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ;uS2$\u0013n\u001c\u0013DQVt7.\u001a3CsR,')\u001e4gKJ|U\u000f\u001e9viN#(/Z1nI\u0011\u0002xn]5uS>t\u0017!B0tSj,\u0007CA\u0014R\u0013\t\u0011\u0006F\u0001\u0003M_:<\u0017AB2m_N,G-\u0001\u0003tSj,W#\u0001)\u0002\u000b\rdwn]3\u0015\u0003a\u0003\"aJ-\n\u0005iC#\u0001B+oSR\fQa\u001e:ji\u0016$\"\u0001W/\t\u000byk\u0001\u0019\u0001\u0014\u0002\u0003\t$B\u0001\u00171iU\")\u0011M\u0004a\u0001E\u0006)!-\u001f;fgB\u0019qeY3\n\u0005\u0011D#!B!se\u0006L\bCA\u0014g\u0013\t9\u0007F\u0001\u0003CsR,\u0007\"B5\u000f\u0001\u00041\u0013aA8gM\")1N\u0004a\u0001M\u0005\u0019A.\u001a8\u00021\u0005dGn\\2bi\u0016tUm^\"ik:\\\u0017J\u001a(fK\u0012,G\r\u000b\u0002\u0010]B\u0011qe\\\u0005\u0003a\"\u0012a!\u001b8mS:,\u0017a\u0005;p\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014X#A:\u0011\u0005e\"\u0018BA;\u0013\u0005E\u0019\u0005.\u001e8lK\u0012\u0014\u0015\u0010^3Ck\u001a4WM\u001d"
)
public class ChunkedByteBufferOutputStream extends OutputStream {
   public final int org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize;
   public final Function1 org$apache$spark$util$io$ChunkedByteBufferOutputStream$$allocator;
   private boolean toChunkedByteBufferWasCalled;
   private final ArrayBuffer org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks;
   public int org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex;
   public int org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position;
   private long _size;
   private boolean closed;

   public ArrayBuffer org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks() {
      return this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks;
   }

   public long size() {
      return this._size;
   }

   public void close() {
      if (!this.closed) {
         super.close();
         this.closed = true;
      }
   }

   public void write(final int b) {
      .MODULE$.require(!this.closed, () -> "cannot write to a closed ChunkedByteBufferOutputStream");
      this.allocateNewChunkIfNeeded();
      ((ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex)).put((byte)b);
      ++this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position;
      ++this._size;
   }

   public void write(final byte[] bytes, final int off, final int len) {
      .MODULE$.require(!this.closed, () -> "cannot write to a closed ChunkedByteBufferOutputStream");

      int thisBatch;
      for(int written = 0; written < len; this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position += thisBatch) {
         this.allocateNewChunkIfNeeded();
         thisBatch = scala.math.package..MODULE$.min(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize - this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position, len - written);
         ((ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex)).put(bytes, written + off, thisBatch);
         written += thisBatch;
      }

      this._size += (long)len;
   }

   private void allocateNewChunkIfNeeded() {
      if (this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position == this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize) {
         this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().$plus$eq(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$allocator.apply(BoxesRunTime.boxToInteger(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize)));
         ++this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex;
         this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position = 0;
      }
   }

   public ChunkedByteBuffer toChunkedByteBuffer() {
      .MODULE$.require(this.closed, () -> "cannot call toChunkedByteBuffer() unless close() has been called");
      .MODULE$.require(!this.toChunkedByteBufferWasCalled, () -> "toChunkedByteBuffer() can only be called once");
      this.toChunkedByteBufferWasCalled = true;
      if (this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex == -1) {
         return new ChunkedByteBuffer((ByteBuffer[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class)));
      } else {
         ByteBuffer[] ret = new ByteBuffer[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().size()];
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().size() - 1).foreach((i) -> $anonfun$toChunkedByteBuffer$3(this, ret, BoxesRunTime.unboxToInt(i)));
         if (this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position == this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize) {
            ret[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex] = (ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex);
            ret[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex].flip();
         } else {
            ret[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex] = (ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$allocator.apply(BoxesRunTime.boxToInteger(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position));
            ((ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex)).flip();
            ret[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex].put((ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex));
            ret[this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex].flip();
            StorageUtils$.MODULE$.dispose((ByteBuffer)this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return new ChunkedByteBuffer(ret);
      }
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$toChunkedByteBuffer$3(final ChunkedByteBufferOutputStream $this, final ByteBuffer[] ret$1, final int i) {
      ret$1[i] = (ByteBuffer)$this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks().apply(i);
      return ret$1[i].flip();
   }

   public ChunkedByteBufferOutputStream(final int chunkSize, final Function1 allocator) {
      this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunkSize = chunkSize;
      this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$allocator = allocator;
      this.toChunkedByteBufferWasCalled = false;
      this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$chunks = new ArrayBuffer();
      this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$lastChunkIndex = -1;
      this.org$apache$spark$util$io$ChunkedByteBufferOutputStream$$position = chunkSize;
      this._size = 0L;
      this.closed = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
