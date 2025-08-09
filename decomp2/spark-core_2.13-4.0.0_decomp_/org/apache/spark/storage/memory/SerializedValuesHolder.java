package org.apache.spark.storage.memory;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import scala.Function1;
import scala.MatchError;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001\u0002\n\u0014\tyA\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005w!Aa\b\u0001B\u0001B\u0003%q\b\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003G\u0011!Y\u0005A!A!\u0002\u0013a\u0005\"\u0002*\u0001\t\u0003\u0019\u0006b\u0002.\u0001\u0005\u0004%\ta\u0017\u0005\u0007O\u0002\u0001\u000b\u0011\u0002/\t\u000f!\u0004!\u0019!C\u0001S\"1Q\u000e\u0001Q\u0001\n)DqA\u001c\u0001C\u0002\u0013\u0005q\u000e\u0003\u0004y\u0001\u0001\u0006I\u0001\u001d\u0005\bs\u0002\u0011\r\u0011\"\u0001{\u0011\u0019q\b\u0001)A\u0005w\"1q\u0010\u0001C!\u0003\u0003Aq!!\u0004\u0001\t\u0003\ny\u0001C\u0004\u0002\u0018\u0001!\t%!\u0007\u0003-M+'/[1mSj,GMV1mk\u0016\u001c\bj\u001c7eKJT!\u0001F\u000b\u0002\r5,Wn\u001c:z\u0015\t1r#A\u0004ti>\u0014\u0018mZ3\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001!\u0006\u0002 YM\u0019\u0001\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g!\r9\u0003FK\u0007\u0002'%\u0011\u0011f\u0005\u0002\r-\u0006dW/Z:I_2$WM\u001d\t\u0003W1b\u0001\u0001B\u0003.\u0001\t\u0007aFA\u0001U#\ty#\u0007\u0005\u0002\"a%\u0011\u0011G\t\u0002\b\u001d>$\b.\u001b8h!\t\t3'\u0003\u00025E\t\u0019\u0011I\\=\u0002\u000f\tdwnY6JIB\u0011q\u0007O\u0007\u0002+%\u0011\u0011(\u0006\u0002\b\u00052|7m[%e\u0003%\u0019\u0007.\u001e8l'&TX\r\u0005\u0002\"y%\u0011QH\t\u0002\u0004\u0013:$\u0018\u0001C2mCN\u001cH+Y4\u0011\u0007\u0001\u001b%&D\u0001B\u0015\t\u0011%%A\u0004sK\u001adWm\u0019;\n\u0005\u0011\u000b%\u0001C\"mCN\u001cH+Y4\u0002\u00155,Wn\u001c:z\u001b>$W\r\u0005\u0002H\u00136\t\u0001J\u0003\u0002\u0015/%\u0011!\n\u0013\u0002\u000b\u001b\u0016lwN]=N_\u0012,\u0017!E:fe&\fG.\u001b>fe6\u000bg.Y4feB\u0011Q\nU\u0007\u0002\u001d*\u0011qjF\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018BA)O\u0005E\u0019VM]5bY&TXM]'b]\u0006<WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\rQ+fk\u0016-Z!\r9\u0003A\u000b\u0005\u0006k\u0019\u0001\rA\u000e\u0005\u0006u\u0019\u0001\ra\u000f\u0005\u0006}\u0019\u0001\ra\u0010\u0005\u0006\u000b\u001a\u0001\rA\u0012\u0005\u0006\u0017\u001a\u0001\r\u0001T\u0001\nC2dwnY1u_J,\u0012\u0001\u0018\t\u0005Cu[t,\u0003\u0002_E\tIa)\u001e8di&|g.\r\t\u0003A\u0016l\u0011!\u0019\u0006\u0003E\u000e\f1A\\5p\u0015\u0005!\u0017\u0001\u00026bm\u0006L!AZ1\u0003\u0015\tKH/\u001a\"vM\u001a,'/\u0001\u0006bY2|7-\u0019;pe\u0002\n!C]3eSJ,7\r^1cY\u0016\u001cFO]3b[V\t!\u000e\u0005\u0002(W&\u0011An\u0005\u0002\u0019%\u0016$\u0017N]3di\u0006\u0014G.Z(viB,Ho\u0015;sK\u0006l\u0017a\u0005:fI&\u0014Xm\u0019;bE2,7\u000b\u001e:fC6\u0004\u0013\u0001\u00022c_N,\u0012\u0001\u001d\t\u0003cZl\u0011A\u001d\u0006\u0003gR\f!![8\u000b\u0005U<\u0012\u0001B;uS2L!a\u001e:\u0003;\rCWO\\6fI\nKH/\u001a\"vM\u001a,'oT;uaV$8\u000b\u001e:fC6\fQA\u00192pg\u0002\n1c]3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6,\u0012a\u001f\t\u0003\u001brL!! (\u0003'M+'/[1mSj\fG/[8o'R\u0014X-Y7\u0002)M,'/[1mSj\fG/[8o'R\u0014X-Y7!\u0003)\u0019Ho\u001c:f-\u0006dW/\u001a\u000b\u0005\u0003\u0007\tI\u0001E\u0002\"\u0003\u000bI1!a\u0002#\u0005\u0011)f.\u001b;\t\r\u0005-q\u00021\u0001+\u0003\u00151\u0018\r\\;f\u00035)7\u000f^5nCR,GmU5{KR\u0011\u0011\u0011\u0003\t\u0004C\u0005M\u0011bAA\u000bE\t!Aj\u001c8h\u0003)9W\r\u001e\"vS2$WM\u001d\u000b\u0003\u00037\u0001BaJA\u000fU%\u0019\u0011qD\n\u0003%5+Wn\u001c:z\u000b:$(/\u001f\"vS2$WM\u001d"
)
public class SerializedValuesHolder implements ValuesHolder {
   public final ClassTag org$apache$spark$storage$memory$SerializedValuesHolder$$classTag;
   public final MemoryMode org$apache$spark$storage$memory$SerializedValuesHolder$$memoryMode;
   private final Function1 allocator;
   private final RedirectableOutputStream redirectableStream;
   private final ChunkedByteBufferOutputStream bbos;
   private final SerializationStream serializationStream;

   public Function1 allocator() {
      return this.allocator;
   }

   public RedirectableOutputStream redirectableStream() {
      return this.redirectableStream;
   }

   public ChunkedByteBufferOutputStream bbos() {
      return this.bbos;
   }

   public SerializationStream serializationStream() {
      return this.serializationStream;
   }

   public void storeValue(final Object value) {
      this.serializationStream().writeObject(value, this.org$apache$spark$storage$memory$SerializedValuesHolder$$classTag);
   }

   public long estimatedSize() {
      return this.bbos().size();
   }

   public MemoryEntryBuilder getBuilder() {
      return new MemoryEntryBuilder() {
         // $FF: synthetic field
         private final SerializedValuesHolder $outer;

         public long preciseSize() {
            return this.$outer.bbos().size();
         }

         public MemoryEntry build() {
            return new SerializedMemoryEntry(this.$outer.bbos().toChunkedByteBuffer(), this.$outer.org$apache$spark$storage$memory$SerializedValuesHolder$$memoryMode, this.$outer.org$apache$spark$storage$memory$SerializedValuesHolder$$classTag);
         }

         public {
            if (SerializedValuesHolder.this == null) {
               throw null;
            } else {
               this.$outer = SerializedValuesHolder.this;
               SerializedValuesHolder.this.serializationStream().close();
            }
         }
      };
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$allocator$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$allocator$2(final int x$1) {
      return Platform.allocateDirectBuffer(x$1);
   }

   public SerializedValuesHolder(final BlockId blockId, final int chunkSize, final ClassTag classTag, final MemoryMode memoryMode, final SerializerManager serializerManager) {
      this.org$apache$spark$storage$memory$SerializedValuesHolder$$classTag = classTag;
      this.org$apache$spark$storage$memory$SerializedValuesHolder$$memoryMode = memoryMode;
      Function1 var10001;
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         var10001 = (x$1) -> $anonfun$allocator$1(BoxesRunTime.unboxToInt(x$1));
      } else {
         if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
            throw new MatchError(memoryMode);
         }

         var10001 = (x$1) -> $anonfun$allocator$2(BoxesRunTime.unboxToInt(x$1));
      }

      this.allocator = var10001;
      this.redirectableStream = new RedirectableOutputStream();
      this.bbos = new ChunkedByteBufferOutputStream(chunkSize, this.allocator());
      this.redirectableStream().setOutputStream(this.bbos());
      boolean autoPick = !(blockId instanceof StreamBlockId);
      SerializerInstance ser = serializerManager.getSerializer(classTag, autoPick).newInstance();
      this.serializationStream = ser.serializeStream(serializerManager.wrapForCompression(blockId, (OutputStream)this.redirectableStream()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
