package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;QAB\u0004\t\u0002Q1QAF\u0004\t\u0002]AQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005B\u0011Ba!N\u0001\u0005B51\u0004bB\u001d\u0002\u0003\u0003%IAO\u0001\r'F,\u0018M]3e\u000bJ\u0014xN\u001d\u0006\u0003\u0011%\tA\u0001\\8tg*\u0011!bC\u0001\u0005iJ,WM\u0003\u0002\r\u001b\u0005)Q\u000e\u001c7jE*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005U\tQ\"A\u0004\u0003\u0019M\u000bX/\u0019:fI\u0016\u0013(o\u001c:\u0014\u0007\u0005Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003+}I!\u0001I\u0004\u0003\t1{7o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\t\u0001b\u001a:bI&,g\u000e\u001e\u000b\u0004K!R\u0003CA\r'\u0013\t9#D\u0001\u0004E_V\u0014G.\u001a\u0005\u0006S\r\u0001\r!J\u0001\u000baJ,G-[2uS>t\u0007\"B\u0016\u0004\u0001\u0004)\u0013!\u00027bE\u0016d\u0007fA\u0002.gA\u0011a&M\u0007\u0002_)\u0011\u0001'D\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u001a0\u0005\u0015\u0019\u0016N\\2fC\u0005!\u0014!B\u0019/e9\u0002\u0014\u0001D2p[B,H/Z#se>\u0014HcA\u00138q!)\u0011\u0006\u0002a\u0001K!)1\u0006\u0002a\u0001K\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t1\b\u0005\u0002=\u00036\tQH\u0003\u0002?\u007f\u0005!A.\u00198h\u0015\u0005\u0001\u0015\u0001\u00026bm\u0006L!AQ\u001f\u0003\r=\u0013'.Z2uQ\r\tQf\r\u0015\u0004\u00015\u001a\u0004"
)
public final class SquaredError {
   public static double gradient(final double prediction, final double label) {
      return SquaredError$.MODULE$.gradient(prediction, label);
   }

   public static double computeError(final TreeEnsembleModel model, final RDD data) {
      return SquaredError$.MODULE$.computeError(model, data);
   }
}
