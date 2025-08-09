package org.apache.spark.util.io;

import io.netty.handler.stream.ChunkedStream;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.util.ByteArrayWritableChannel;
import org.apache.spark.storage.StorageUtils$;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dh!B\u0014)\u00011\u0012\u0004\u0002\u0003!\u0001\u0005\u0003\u0007I\u0011\u0001\"\t\u0011=\u0003!\u00111A\u0005\u0002AC\u0001B\u0016\u0001\u0003\u0002\u0003\u0006Ka\u0011\u0005\u0006/\u0002!\t\u0001\u0017\u0005\b9\u0002\u0011\r\u0011\"\u0003^\u0011\u0019\t\u0007\u0001)A\u0005=\"1!\r\u0001Q!\n\rDqA\u001a\u0001A\u0002\u0013%q\rC\u0004l\u0001\u0001\u0007I\u0011\u00027\t\r9\u0004\u0001\u0015)\u0003i\u0011\u0015y\u0007\u0001\"\u0001h\u0011\u00159\u0006\u0001\"\u0001q\u0011\u00159\u0006\u0001\"\u0001r\u0011\u0015!\b\u0001\"\u0001v\u0011\u0015q\b\u0001\"\u0011\u0000\u0011\u001d\tY\u0001\u0001C!\u0003\u001bAq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005u\u0002\u0001\"\u0001\u0002@!9\u0011\u0011\n\u0001\u0005\u0002\u0005-\u0003bBA'\u0001\u0011\u0005\u0011q\n\u0005\n\u00037\u0002\u0011\u0013!C\u0001\u0003;Bq!a\u001d\u0001\t\u0003\t)\bC\u0004\u0002x\u0001!\t!!\u001f\t\u000f\u0005e\u0003\u0001\"\u0001\u0002\u0006\u001eA\u0011q\u0011\u0015\t\u00021\nIIB\u0004(Q!\u0005A&a#\t\r][B\u0011AAM\u0011!\tYj\u0007b\u0001\n\u0013i\u0006bBAO7\u0001\u0006IA\u0018\u0005\t\u0003?[\"\u0019!C\u0005;\"9\u0011\u0011U\u000e!\u0002\u0013q\u0006bBAR7\u0011\u0005\u0011Q\u0015\u0005\b\u0003w[B\u0011AA_\u0011\u001d\tYl\u0007C\u0005\u0003\u0013Dq!!6\u001c\t\u0003\t9\u000eC\u0005\u0002^n\t\n\u0011\"\u0001\u0002`\"I\u00111]\u000e\u0002\u0002\u0013%\u0011Q\u001d\u0002\u0012\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014(BA\u0015+\u0003\tIwN\u0003\u0002,Y\u0005!Q\u000f^5m\u0015\tic&A\u0003ta\u0006\u00148N\u0003\u00020a\u00051\u0011\r]1dQ\u0016T\u0011!M\u0001\u0004_J<7c\u0001\u00014wA\u0011A'O\u0007\u0002k)\u0011agN\u0001\u0005Y\u0006twMC\u00019\u0003\u0011Q\u0017M^1\n\u0005i*$AB(cU\u0016\u001cG\u000f\u0005\u0002=}5\tQH\u0003\u0002*o%\u0011q(\u0010\u0002\u000f\u000bb$XM\u001d8bY&T\u0018M\u00197f\u0003\u0019\u0019\u0007.\u001e8lg\u000e\u0001Q#A\"\u0011\u0007\u0011;\u0015*D\u0001F\u0015\u00051\u0015!B:dC2\f\u0017B\u0001%F\u0005\u0015\t%O]1z!\tQU*D\u0001L\u0015\tau'A\u0002oS>L!AT&\u0003\u0015\tKH/\u001a\"vM\u001a,'/\u0001\u0006dQVt7n]0%KF$\"!\u0015+\u0011\u0005\u0011\u0013\u0016BA*F\u0005\u0011)f.\u001b;\t\u000fU\u0013\u0011\u0011!a\u0001\u0007\u0006\u0019\u0001\u0010J\u0019\u0002\u000f\rDWO\\6tA\u00051A(\u001b8jiz\"\"!W.\u0011\u0005i\u0003Q\"\u0001\u0015\t\u000b\u0001#\u0001\u0019A\"\u0002)\t,hMZ3s/JLG/Z\"ik:\\7+\u001b>f+\u0005q\u0006C\u0001#`\u0013\t\u0001WIA\u0002J]R\fQCY;gM\u0016\u0014xK]5uK\u000eCWO\\6TSj,\u0007%\u0001\u0005eSN\u0004xn]3e!\t!E-\u0003\u0002f\u000b\n9!i\\8mK\u0006t\u0017!B0tSj,W#\u00015\u0011\u0005\u0011K\u0017B\u00016F\u0005\u0011auN\\4\u0002\u0013}\u001b\u0018N_3`I\u0015\fHCA)n\u0011\u001d)\u0016\"!AA\u0002!\faaX:ju\u0016\u0004\u0013\u0001B:ju\u0016$\u0012!\u0017\u000b\u00033JDQa]\u0007A\u0002%\u000b!BY=uK\n+hMZ3s\u0003)9(/\u001b;f\rVdG.\u001f\u000b\u0003#ZDQa\u001e\bA\u0002a\fqa\u00195b]:,G\u000e\u0005\u0002zy6\t!P\u0003\u0002|\u0017\u0006A1\r[1o]\u0016d7/\u0003\u0002~u\n\u0019rK]5uC\ndWMQ=uK\u000eC\u0017M\u001c8fY\u0006iqO]5uK\u0016CH/\u001a:oC2$2!UA\u0001\u0011\u001d\t\u0019a\u0004a\u0001\u0003\u000b\t1a\\;u!\ra\u0014qA\u0005\u0004\u0003\u0013i$\u0001D(cU\u0016\u001cGoT;uaV$\u0018\u0001\u0004:fC\u0012,\u0005\u0010^3s]\u0006dGcA)\u0002\u0010!9\u0011\u0011\u0003\tA\u0002\u0005M\u0011AA5o!\ra\u0014QC\u0005\u0004\u0003/i$aC(cU\u0016\u001cG/\u00138qkR\fq\u0001^8OKR$\u00180\u0006\u0002\u0002\u001eA\u0019!,a\b\n\u0007\u0005\u0005\u0002FA\u000eDQVt7.\u001a3CsR,')\u001e4gKJ4\u0015\u000e\\3SK\u001eLwN\\\u0001\u000ei>tU\r\u001e;z\r>\u00148k\u001d7\u0016\u0005\u0005\u001d\u0002\u0003BA\u0015\u0003si!!a\u000b\u000b\t\u00055\u0012qF\u0001\u0007gR\u0014X-Y7\u000b\t\u0005E\u00121G\u0001\bQ\u0006tG\r\\3s\u0015\u0011\t)$a\u000e\u0002\u000b9,G\u000f^=\u000b\u0003%JA!a\u000f\u0002,\ti1\t[;oW\u0016$7\u000b\u001e:fC6\fq\u0001^8BeJ\f\u00170\u0006\u0002\u0002BA!AiRA\"!\r!\u0015QI\u0005\u0004\u0003\u000f*%\u0001\u0002\"zi\u0016\fA\u0002^8CsR,')\u001e4gKJ,\u0012!S\u0001\u000ei>Le\u000e];u'R\u0014X-Y7\u0015\t\u0005E\u0013q\u000b\t\u0004y\u0005M\u0013bAA+{\tY\u0011J\u001c9viN#(/Z1n\u0011!\tI&\u0006I\u0001\u0002\u0004\u0019\u0017a\u00023jgB|7/Z\u0001\u0018i>Le\u000e];u'R\u0014X-Y7%I\u00164\u0017-\u001e7uIE*\"!a\u0018+\u0007\r\f\tg\u000b\u0002\u0002dA!\u0011QMA8\u001b\t\t9G\u0003\u0003\u0002j\u0005-\u0014!C;oG\",7m[3e\u0015\r\ti'R\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA9\u0003O\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003%9W\r^\"ik:\\7\u000fF\u0001D\u0003\u0011\u0019w\u000e]=\u0015\u0007e\u000bY\bC\u0004\u0002~a\u0001\r!a \u0002\u0013\u0005dGn\\2bi>\u0014\b#\u0002#\u0002\u0002zK\u0015bAAB\u000b\nIa)\u001e8di&|g.\r\u000b\u0002#\u0006\t2\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\u0011\u0005i[2#B\u000e\u0002\u000e\u0006M\u0005c\u0001#\u0002\u0010&\u0019\u0011\u0011S#\u0003\r\u0005s\u0017PU3g!\ra\u0014QS\u0005\u0004\u0003/k$\u0001D*fe&\fG.\u001b>bE2,GCAAE\u0003E\u0019\u0005*\u0016(L?\n+fIR#S?NK%,R\u0001\u0013\u0007\"+fjS0C+\u001a3UIU0T\u0013j+\u0005%A\rN\u0013:KU*V'`\u0007\"+fjS0C+\u001a3UIU0T\u0013j+\u0015AG'J\u001d&kU+T0D\u0011Vs5j\u0018\"V\r\u001a+%kX*J5\u0016\u0003\u0013!\u00054s_6l\u0015M\\1hK\u0012\u0014UO\u001a4feR\u0019\u0011,a*\t\u000f\u0005%\u0016\u00051\u0001\u0002,\u0006!A-\u0019;b!\u0011\ti+a.\u000e\u0005\u0005=&\u0002BAY\u0003g\u000baAY;gM\u0016\u0014(bAA[Y\u00059a.\u001a;x_J\\\u0017\u0002BA]\u0003_\u0013Q\"T1oC\u001e,GMQ;gM\u0016\u0014\u0018\u0001\u00034s_64\u0015\u000e\\3\u0015\u0007e\u000by\fC\u0004\u0002B\n\u0002\r!a1\u0002\t\u0019LG.\u001a\t\u0004y\u0005\u0015\u0017bAAd{\t!a)\u001b7f)\u001dI\u00161ZAg\u0003#Dq!!1$\u0001\u0004\t\u0019\r\u0003\u0004\u0002P\u000e\u0002\r\u0001[\u0001\u0007_\u001a47/\u001a;\t\r\u0005M7\u00051\u0001i\u0003\u0019aWM\\4uQ\u00069Rm\u001d;j[\u0006$XMQ;gM\u0016\u00148\t[;oWNK'0\u001a\u000b\u0004=\u0006e\u0007\u0002CAnIA\u0005\t\u0019\u00015\u0002\u001b\u0015\u001cH/[7bi\u0016$7+\u001b>f\u0003\u0005*7\u000f^5nCR,')\u001e4gKJ\u001c\u0005.\u001e8l'&TX\r\n3fM\u0006,H\u000e\u001e\u00132+\t\t\tOK\u0002i\u0003C\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\r"
)
public class ChunkedByteBuffer implements Externalizable {
   private ByteBuffer[] chunks;
   private final int bufferWriteChunkSize;
   private boolean disposed;
   private long _size;

   public static long estimateBufferChunkSize$default$1() {
      return ChunkedByteBuffer$.MODULE$.estimateBufferChunkSize$default$1();
   }

   public static int estimateBufferChunkSize(final long estimatedSize) {
      return ChunkedByteBuffer$.MODULE$.estimateBufferChunkSize(estimatedSize);
   }

   public static ChunkedByteBuffer fromFile(final File file) {
      return ChunkedByteBuffer$.MODULE$.fromFile(file);
   }

   public static ChunkedByteBuffer fromManagedBuffer(final ManagedBuffer data) {
      return ChunkedByteBuffer$.MODULE$.fromManagedBuffer(data);
   }

   public ByteBuffer[] chunks() {
      return this.chunks;
   }

   public void chunks_$eq(final ByteBuffer[] x$1) {
      this.chunks = x$1;
   }

   private int bufferWriteChunkSize() {
      return this.bufferWriteChunkSize;
   }

   private long _size() {
      return this._size;
   }

   private void _size_$eq(final long x$1) {
      this._size = x$1;
   }

   public long size() {
      return this._size();
   }

   public void writeFully(final WritableByteChannel channel) {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getChunks()), (bytes) -> {
         $anonfun$writeFully$1(this, channel, bytes);
         return BoxedUnit.UNIT;
      });
   }

   public void writeExternal(final ObjectOutput out) {
      out.writeInt(this.chunks().length);
      ByteBuffer[] chunksCopy = this.getChunks();
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])chunksCopy), (buffer) -> {
         $anonfun$writeExternal$1(out, buffer);
         return BoxedUnit.UNIT;
      });
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])chunksCopy), (x$4) -> {
         $anonfun$writeExternal$2(out, x$4);
         return BoxedUnit.UNIT;
      });
   }

   public void readExternal(final ObjectInput in) {
      int chunksNum = in.readInt();
      Range indices = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), chunksNum);
      IndexedSeq chunksSize = indices.map((JFunction1.mcII.sp)(x$5) -> in.readInt());
      ByteBuffer[] chunks = new ByteBuffer[chunksNum];
      indices.foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         int chunkSize = BoxesRunTime.unboxToInt(chunksSize.apply(i));
         byte[] arr = new byte[chunkSize];
         in.readFully(arr, 0, chunkSize);
         chunks[i] = ByteBuffer.wrap(arr);
      });
      this.chunks_$eq(chunks);
      this._size_$eq(BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])chunks), (x$6) -> BoxesRunTime.boxToLong($anonfun$readExternal$3(x$6)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
   }

   public ChunkedByteBufferFileRegion toNetty() {
      return new ChunkedByteBufferFileRegion(this, this.bufferWriteChunkSize());
   }

   public ChunkedStream toNettyForSsl() {
      return new ChunkedStream(this.toInputStream(this.toInputStream$default$1()), this.bufferWriteChunkSize());
   }

   public byte[] toArray() {
      if (this.size() >= 2147483632L) {
         throw new UnsupportedOperationException("cannot call toArray because buffer size (" + this.size() + " bytes) exceeds maximum array size");
      } else {
         ByteArrayWritableChannel byteChannel = new ByteArrayWritableChannel((int)this.size());
         this.writeFully(byteChannel);
         byteChannel.close();
         return byteChannel.getData();
      }
   }

   public ByteBuffer toByteBuffer() {
      return this.chunks().length == 1 ? ((ByteBuffer).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()))).duplicate() : ByteBuffer.wrap(this.toArray());
   }

   public InputStream toInputStream(final boolean dispose) {
      return new ChunkedByteBufferInputStream(this, dispose);
   }

   public boolean toInputStream$default$1() {
      return false;
   }

   public ByteBuffer[] getChunks() {
      return (ByteBuffer[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()), (x$7) -> x$7.duplicate(), scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class));
   }

   public ChunkedByteBuffer copy(final Function1 allocator) {
      ByteBuffer[] copiedChunks = (ByteBuffer[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getChunks()), (chunk) -> {
         ByteBuffer newChunk = (ByteBuffer)allocator.apply(BoxesRunTime.boxToInteger(chunk.limit()));
         newChunk.put(chunk);
         newChunk.flip();
         return newChunk;
      }, scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class));
      return new ChunkedByteBuffer(copiedChunks);
   }

   public void dispose() {
      if (!this.disposed) {
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()), (buffer) -> {
            $anonfun$dispose$1(buffer);
            return BoxedUnit.UNIT;
         });
         this.disposed = true;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$3(final ByteBuffer x$1) {
      return x$1.position() == 0;
   }

   // $FF: synthetic method
   public static final long $anonfun$bufferWriteChunkSize$1(final SparkEnv x$2) {
      return BoxesRunTime.unboxToLong(x$2.conf().get(package$.MODULE$.BUFFER_WRITE_CHUNK_SIZE()));
   }

   // $FF: synthetic method
   public static final long $anonfun$_size$1(final ByteBuffer x$3) {
      return (long)x$3.limit();
   }

   // $FF: synthetic method
   public static final void $anonfun$writeFully$1(final ChunkedByteBuffer $this, final WritableByteChannel channel$1, final ByteBuffer bytes) {
      int originalLimit = bytes.limit();

      while(bytes.hasRemaining()) {
         int ioSize = Math.min(bytes.remaining(), $this.bufferWriteChunkSize());
         bytes.limit(bytes.position() + ioSize);
         channel$1.write(bytes);
         bytes.limit(originalLimit);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$writeExternal$1(final ObjectOutput out$1, final ByteBuffer buffer) {
      out$1.writeInt(buffer.limit());
   }

   // $FF: synthetic method
   public static final void $anonfun$writeExternal$2(final ObjectOutput out$1, final ByteBuffer x$4) {
      Utils$.MODULE$.writeByteBuffer(x$4, (DataOutput)out$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$readExternal$3(final ByteBuffer x$6) {
      return (long)x$6.limit();
   }

   // $FF: synthetic method
   public static final void $anonfun$dispose$1(final ByteBuffer buffer) {
      StorageUtils$.MODULE$.dispose(buffer);
   }

   public ChunkedByteBuffer(final ByteBuffer[] chunks) {
      this.chunks = chunks;
      super();
      scala.Predef..MODULE$.require(this.chunks() != null, () -> "chunks must not be null");
      scala.Predef..MODULE$.require(!.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()), (Object)null), () -> "chunks must not contain null");
      scala.Predef..MODULE$.require(.MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$new$3(x$1))), () -> "chunks' positions must be 0");
      this.bufferWriteChunkSize = (int)BoxesRunTime.unboxToLong(scala.Option..MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$2) -> BoxesRunTime.boxToLong($anonfun$bufferWriteChunkSize$1(x$2))).getOrElse((JFunction0.mcJ.sp)() -> BoxesRunTime.unboxToLong(package$.MODULE$.BUFFER_WRITE_CHUNK_SIZE().defaultValue().get())));
      this.disposed = false;
      this._size = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.chunks()), (x$3) -> BoxesRunTime.boxToLong($anonfun$_size$1(x$3)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public ChunkedByteBuffer() {
      this((ByteBuffer[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class)));
   }

   public ChunkedByteBuffer(final ByteBuffer byteBuffer) {
      this((ByteBuffer[])((Object[])(new ByteBuffer[]{byteBuffer})));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
