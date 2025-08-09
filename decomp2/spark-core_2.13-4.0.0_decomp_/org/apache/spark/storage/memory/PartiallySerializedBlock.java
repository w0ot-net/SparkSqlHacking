package org.apache.spark.storage.memory;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.SparkException.;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import org.sparkproject.guava.io.ByteStreams;
import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc!B\f\u0019\u0001i\u0011\u0003\u0002\u0003\u0016\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011A\u0002!\u0011!Q\u0001\nEB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\ty\u0001\u0011)\u0019!C\u0005{!A\u0011\t\u0001B\u0001B\u0003%a\b\u0003\u0005C\u0001\t\u0015\r\u0011\"\u0003D\u0011!9\u0005A!A!\u0002\u0013!\u0005\u0002\u0003%\u0001\u0005\u000b\u0007I\u0011A%\t\u00115\u0003!\u0011!Q\u0001\n)C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0014\u0005\t)\u0002\u0011\t\u0011)A\u0005+\"AQ\f\u0001B\u0001B\u0003%a\f\u0003\u0005v\u0001\t\u0005\t\u0015!\u0003w\u0011\u0015a\b\u0001\"\u0001~\u0011)\t\u0019\u0002\u0001EC\u0002\u0013%\u0011Q\u0003\u0005\t\u0003;\u0001A\u0011\u0001\u000e\u0002\u0016!A\u0011q\u0004\u0001!B\u0013\t\t\u0003\u0003\u0005\u0002(\u0001\u0001\u000b\u0015BA\u0011\u0011\u001d\tI\u0003\u0001C\u0005\u0003WAq!a\r\u0001\t\u0003\tY\u0003C\u0004\u00026\u0001!\t!a\u000e\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N\tA\u0002+\u0019:uS\u0006dG._*fe&\fG.\u001b>fI\ncwnY6\u000b\u0005eQ\u0012AB7f[>\u0014\u0018P\u0003\u0002\u001c9\u000591\u000f^8sC\u001e,'BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0016\u0005\rb7C\u0001\u0001%!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fM\u0006YQ.Z7pef\u001cFo\u001c:f\u0007\u0001\u0001\"!\f\u0018\u000e\u0003aI!a\f\r\u0003\u00175+Wn\u001c:z'R|'/Z\u0001\u0012g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\bC\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u001d\u0003)\u0019XM]5bY&TXM]\u0005\u0003mM\u0012\u0011cU3sS\u0006d\u0017N_3s\u001b\u0006t\u0017mZ3s\u0003\u001d\u0011Gn\\2l\u0013\u0012\u0004\"!\u000f\u001e\u000e\u0003iI!a\u000f\u000e\u0003\u000f\tcwnY6JI\u0006\u00192/\u001a:jC2L'0\u0019;j_:\u001cFO]3b[V\ta\b\u0005\u00023\u007f%\u0011\u0001i\r\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8TiJ,\u0017-\\\u0001\u0015g\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8TiJ,\u0017-\u001c\u0011\u00021I,G-\u001b:fGR\f'\r\\3PkR\u0004X\u000f^*ue\u0016\fW.F\u0001E!\tiS)\u0003\u0002G1\tA\"+\u001a3je\u0016\u001cG/\u00192mK>+H\u000f];u'R\u0014X-Y7\u00023I,G-\u001b:fGR\f'\r\\3PkR\u0004X\u000f^*ue\u0016\fW\u000eI\u0001\rk:\u0014x\u000e\u001c7NK6|'/_\u000b\u0002\u0015B\u0011QeS\u0005\u0003\u0019\u001a\u0012A\u0001T8oO\u0006iQO\u001c:pY2lU-\\8ss\u0002\n!\"\\3n_JLXj\u001c3f!\t\u0001&+D\u0001R\u0015\tIB$\u0003\u0002T#\nQQ*Z7peflu\u000eZ3\u0002\t\t\u0014wn\u001d\t\u0003-nk\u0011a\u0016\u0006\u00031f\u000b!![8\u000b\u0005ic\u0012\u0001B;uS2L!\u0001X,\u0003;\rCWO\\6fI\nKH/\u001a\"vM\u001a,'oT;uaV$8\u000b\u001e:fC6\fAA]3tiB\u0019ql\u001a6\u000f\u0005\u0001,gBA1e\u001b\u0005\u0011'BA2,\u0003\u0019a$o\\8u}%\tq%\u0003\u0002gM\u00059\u0001/Y2lC\u001e,\u0017B\u00015j\u0005!IE/\u001a:bi>\u0014(B\u00014'!\tYG\u000e\u0004\u0001\u0005\u000b5\u0004!\u0019\u00018\u0003\u0003Q\u000b\"a\u001c:\u0011\u0005\u0015\u0002\u0018BA9'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!J:\n\u0005Q4#aA!os\u0006A1\r\\1tgR\u000bw\rE\u0002xu*l\u0011\u0001\u001f\u0006\u0003s\u001a\nqA]3gY\u0016\u001cG/\u0003\u0002|q\nA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0015}~\f\t!a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001b\ty!!\u0005\u0011\u00075\u0002!\u000eC\u0003+\u001d\u0001\u0007A\u0006C\u00031\u001d\u0001\u0007\u0011\u0007C\u00038\u001d\u0001\u0007\u0001\bC\u0003=\u001d\u0001\u0007a\bC\u0003C\u001d\u0001\u0007A\tC\u0003I\u001d\u0001\u0007!\nC\u0003O\u001d\u0001\u0007q\nC\u0003U\u001d\u0001\u0007Q\u000bC\u0003^\u001d\u0001\u0007a\fC\u0003v\u001d\u0001\u0007a/\u0001\bv]J|G\u000e\\3e\u0005V4g-\u001a:\u0016\u0005\u0005]\u0001c\u0001,\u0002\u001a%\u0019\u00111D,\u0003#\rCWO\\6fI\nKH/\u001a\"vM\u001a,'/\u0001\u000fhKR,fN]8mY\u0016$7\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\u0002\u0013\u0011L7oY1sI\u0016$\u0007cA\u0013\u0002$%\u0019\u0011Q\u0005\u0014\u0003\u000f\t{w\u000e\\3b]\u0006A1m\u001c8tk6,G-\u0001\u0011wKJLg-\u001f(pi\u000e{gn];nK\u0012\fe\u000e\u001a(pi\u0012K7oY1sI\u0016$GCAA\u0017!\r)\u0013qF\u0005\u0004\u0003c1#\u0001B+oSR\fq\u0001Z5tG\u0006\u0014H-A\u000bgS:L7\u000f[,sSRLgn\u001a+p'R\u0014X-Y7\u0015\t\u00055\u0012\u0011\b\u0005\b\u0003w)\u0002\u0019AA\u001f\u0003\ty7\u000f\u0005\u0003\u0002@\u0005\u001dSBAA!\u0015\rA\u00161\t\u0006\u0003\u0003\u000b\nAA[1wC&!\u0011\u0011JA!\u00051yU\u000f\u001e9viN#(/Z1n\u000391\u0018\r\\;fg&#XM]1u_J,\"!a\u0014\u0011\t5\n\tF[\u0005\u0004\u0003'B\"!\u0007)beRL\u0017\r\u001c7z+:\u0014x\u000e\u001c7fI&#XM]1u_J\u0004"
)
public class PartiallySerializedBlock {
   private ChunkedByteBuffer unrolledBuffer;
   private final MemoryStore memoryStore;
   private final SerializerManager serializerManager;
   private final BlockId blockId;
   private final SerializationStream serializationStream;
   private final RedirectableOutputStream redirectableOutputStream;
   private final long unrollMemory;
   private final MemoryMode memoryMode;
   private final ChunkedByteBufferOutputStream bbos;
   private final Iterator rest;
   private final ClassTag classTag;
   private boolean discarded;
   private boolean consumed;
   private volatile boolean bitmap$0;

   private SerializationStream serializationStream() {
      return this.serializationStream;
   }

   private RedirectableOutputStream redirectableOutputStream() {
      return this.redirectableOutputStream;
   }

   public long unrollMemory() {
      return this.unrollMemory;
   }

   private ChunkedByteBuffer unrolledBuffer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.bbos.close();
            this.unrolledBuffer = this.bbos.toChunkedByteBuffer();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.unrolledBuffer;
   }

   private ChunkedByteBuffer unrolledBuffer() {
      return !this.bitmap$0 ? this.unrolledBuffer$lzycompute() : this.unrolledBuffer;
   }

   public ChunkedByteBuffer getUnrolledChunkedByteBuffer() {
      return this.unrolledBuffer();
   }

   private void verifyNotConsumedAndNotDiscarded() {
      if (this.consumed) {
         throw .MODULE$.internalError("Can only call one of finishWritingToStream() or valuesIterator() and can only call once.", "STORAGE");
      } else if (this.discarded) {
         throw .MODULE$.internalError("Cannot call methods on a discarded PartiallySerializedBlock", "STORAGE");
      }
   }

   public void discard() {
      if (!this.discarded) {
         try {
            this.redirectableOutputStream().setOutputStream(ByteStreams.nullOutputStream());
            this.serializationStream().close();
         } finally {
            this.discarded = true;
            this.unrolledBuffer().dispose();
            this.memoryStore.releaseUnrollMemoryForThisTask(this.memoryMode, this.unrollMemory());
         }

      }
   }

   public void finishWritingToStream(final OutputStream os) {
      this.verifyNotConsumedAndNotDiscarded();
      this.consumed = true;
      ByteStreams.copy(this.unrolledBuffer().toInputStream(true), os);
      this.memoryStore.releaseUnrollMemoryForThisTask(this.memoryMode, this.unrollMemory());
      this.redirectableOutputStream().setOutputStream(os);

      while(this.rest.hasNext()) {
         this.serializationStream().writeObject(this.rest.next(), this.classTag);
      }

      this.serializationStream().close();
   }

   public PartiallyUnrolledIterator valuesIterator() {
      this.verifyNotConsumedAndNotDiscarded();
      this.consumed = true;
      this.serializationStream().close();
      Iterator unrolledIter = this.serializerManager.dataDeserializeStream(this.blockId, this.unrolledBuffer().toInputStream(true), this.classTag);
      return new PartiallyUnrolledIterator(this.memoryStore, this.memoryMode, this.unrollMemory(), unrolledIter, this.rest);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$4(final PartiallySerializedBlock $this, final TaskContext x$6) {
      $this.unrolledBuffer().dispose();
   }

   public PartiallySerializedBlock(final MemoryStore memoryStore, final SerializerManager serializerManager, final BlockId blockId, final SerializationStream serializationStream, final RedirectableOutputStream redirectableOutputStream, final long unrollMemory, final MemoryMode memoryMode, final ChunkedByteBufferOutputStream bbos, final Iterator rest, final ClassTag classTag) {
      this.memoryStore = memoryStore;
      this.serializerManager = serializerManager;
      this.blockId = blockId;
      this.serializationStream = serializationStream;
      this.redirectableOutputStream = redirectableOutputStream;
      this.unrollMemory = unrollMemory;
      this.memoryMode = memoryMode;
      this.bbos = bbos;
      this.rest = rest;
      this.classTag = classTag;
      scala.Option..MODULE$.apply(TaskContext$.MODULE$.get()).foreach((taskContext) -> taskContext.addTaskCompletionListener((Function1)((x$6) -> {
            $anonfun$new$4(this, x$6);
            return BoxedUnit.UNIT;
         })));
      this.discarded = false;
      this.consumed = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
