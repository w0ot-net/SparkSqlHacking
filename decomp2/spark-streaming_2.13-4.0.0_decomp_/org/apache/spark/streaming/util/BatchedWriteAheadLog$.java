package org.apache.spark.streaming.util;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.Utils.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;

public final class BatchedWriteAheadLog$ {
   public static final BatchedWriteAheadLog$ MODULE$ = new BatchedWriteAheadLog$();

   public ByteBuffer aggregate(final Seq records) {
      return ByteBuffer.wrap(.MODULE$.serialize(((IterableOnceOps)records.map((record) -> JavaUtils.bufferToArray(record.data()))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)))));
   }

   public ByteBuffer[] deaggregate(final ByteBuffer buffer) {
      int prevPosition = buffer.position();

      ByteBuffer[] var10000;
      try {
         var10000 = (ByteBuffer[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.deserialize(JavaUtils.bufferToArray(buffer))), (x$1) -> ByteBuffer.wrap(x$1), scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class));
      } catch (ClassCastException var3) {
         buffer.position(prevPosition);
         var10000 = (ByteBuffer[])((Object[])(new ByteBuffer[]{buffer}));
      }

      return var10000;
   }

   private BatchedWriteAheadLog$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
