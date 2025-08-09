package org.apache.spark.storage;

import org.apache.spark.TaskContext;
import org.apache.spark.util.TaskCompletionListener;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013Aa\u0002\u0005\u0005#!A\u0001\u0005\u0001BA\u0002\u0013\u0005\u0011\u0005\u0003\u0005'\u0001\t\u0005\r\u0011\"\u0001(\u0011!\u0001\u0004A!A!B\u0013\u0011\u0003\"B\u0019\u0001\t\u0003\u0011\u0004\"B\u001b\u0001\t\u00032\u0004\"B\u001f\u0001\t\u0003q$AH*ik\u001a4G.\u001a$fi\u000eD7i\\7qY\u0016$\u0018n\u001c8MSN$XM\\3s\u0015\tI!\"A\u0004ti>\u0014\u0018mZ3\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001%i\u0001\"a\u0005\r\u000e\u0003QQ!!\u0006\f\u0002\t1\fgn\u001a\u0006\u0002/\u0005!!.\u0019<b\u0013\tIBC\u0001\u0004PE*,7\r\u001e\t\u00037yi\u0011\u0001\b\u0006\u0003;)\tA!\u001e;jY&\u0011q\u0004\b\u0002\u0017)\u0006\u001c8nQ8na2,G/[8o\u0019&\u001cH/\u001a8fe\u0006!A-\u0019;b+\u0005\u0011\u0003CA\u0012%\u001b\u0005A\u0011BA\u0013\t\u0005m\u0019\u0006.\u001e4gY\u0016\u0014En\\2l\r\u0016$8\r[3s\u0013R,'/\u0019;pe\u0006AA-\u0019;b?\u0012*\u0017\u000f\u0006\u0002)]A\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t!QK\\5u\u0011\u001dy#!!AA\u0002\t\n1\u0001\u001f\u00132\u0003\u0015!\u0017\r^1!\u0003\u0019a\u0014N\\5u}Q\u00111\u0007\u000e\t\u0003G\u0001AQ\u0001\t\u0003A\u0002\t\n\u0001c\u001c8UCN\\7i\\7qY\u0016$\u0018n\u001c8\u0015\u0005!:\u0004\"\u0002\u001d\u0006\u0001\u0004I\u0014aB2p]R,\u0007\u0010\u001e\t\u0003umj\u0011AC\u0005\u0003y)\u00111\u0002V1tW\u000e{g\u000e^3yi\u0006QqN\\\"p[BdW\r^3\u0015\u0005!z\u0004\"\u0002\u001d\u0007\u0001\u0004I\u0004"
)
public class ShuffleFetchCompletionListener implements TaskCompletionListener {
   private ShuffleBlockFetcherIterator data;

   public ShuffleBlockFetcherIterator data() {
      return this.data;
   }

   public void data_$eq(final ShuffleBlockFetcherIterator x$1) {
      this.data = x$1;
   }

   public void onTaskCompletion(final TaskContext context) {
      if (this.data() != null) {
         this.data().cleanup();
         this.data_$eq((ShuffleBlockFetcherIterator)null);
      }
   }

   public void onComplete(final TaskContext context) {
      this.onTaskCompletion(context);
   }

   public ShuffleFetchCompletionListener(final ShuffleBlockFetcherIterator data) {
      this.data = data;
      super();
   }
}
