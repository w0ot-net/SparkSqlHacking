package org.apache.spark.streaming.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaDStream$;
import org.apache.spark.streaming.dstream.DStream;
import scala.Option;
import scala.Some;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma!\u0002\u0007\u000e\u00015I\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011a\u0002!\u0011!Q\u0001\neB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\b#\u0002\u0011\r\u0011\"\u0001S\u0011\u00191\u0006\u0001)A\u0005'\")q\u000b\u0001C!1\")!\u000e\u0001C!W\")\u0001\u000f\u0001C!c\"I\u0011\u0011\u0001\u0001C\u0002\u0013\u0005\u00111\u0001\u0005\t\u0003#\u0001\u0001\u0015!\u0003\u0002\u0006\tI\u0002+\u001f;i_:$&/\u00198tM>\u0014X.\u001a33\tN#(/Z1n\u0015\tqq\"\u0001\u0004qsRDwN\u001c\u0006\u0003!E\t1!\u00199j\u0015\t\u00112#A\u0005tiJ,\u0017-\\5oO*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0005\u0002\u00015A\u00191D\b\u0011\u000e\u0003qQ!!H\t\u0002\u000f\u0011\u001cHO]3b[&\u0011q\u0004\b\u0002\b\tN#(/Z1n!\r\tCEJ\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t)\u0011I\u001d:bsB\u0011\u0011eJ\u0005\u0003Q\t\u0012AAQ=uK\u00061\u0001/\u0019:f]R\u001c\u0001\u0001\r\u0002-_A\u00191DH\u0017\u0011\u00059zC\u0002\u0001\u0003\na\u0005\t\t\u0011!A\u0003\u0002E\u0012Aa\u0018\u00132cE\u0011!'\u000e\t\u0003CMJ!\u0001\u000e\u0012\u0003\u000f9{G\u000f[5oOB\u0011\u0011EN\u0005\u0003o\t\u00121!\u00118z\u0003\u001d\u0001\u0018M]3oiJ\u0002$A\u000f\u001f\u0011\u0007mq2\b\u0005\u0002/y\u0011IQHAA\u0001\u0002\u0003\u0015\t!\r\u0002\u0005?\u0012\n$'A\u0003qMVt7\r\u0005\u0002A\u00036\tQ\"\u0003\u0002C\u001b\t9\u0002+\u001f;i_:$&/\u00198tM>\u0014XNR;oGRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u001535\n\u0015\t\u0003\u0001\u0002AQ!\u000b\u0003A\u0002\u001d\u0003$\u0001\u0013&\u0011\u0007mq\u0012\n\u0005\u0002/\u0015\u0012I\u0001GRA\u0001\u0002\u0003\u0015\t!\r\u0005\u0006q\u0011\u0001\r\u0001\u0014\u0019\u0003\u001b>\u00032a\u0007\u0010O!\tqs\nB\u0005>\u0017\u0006\u0005\t\u0011!B\u0001c!)a\b\u0002a\u0001\u007f\u0005!a-\u001e8d+\u0005\u0019\u0006C\u0001!U\u0013\t)VBA\tUe\u0006t7OZ8s[\u001a+hn\u0019;j_:\fQAZ;oG\u0002\nA\u0002Z3qK:$WM\\2jKN,\u0012!\u0017\t\u00045\n,gBA.a\u001d\tav,D\u0001^\u0015\tq&&\u0001\u0004=e>|GOP\u0005\u0002G%\u0011\u0011MI\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019GM\u0001\u0003MSN$(BA1#a\t1\u0007\u000eE\u0002\u001c=\u001d\u0004\"A\f5\u0005\u0013%<\u0011\u0011!A\u0001\u0006\u0003\t$\u0001B0%cM\nQb\u001d7jI\u0016$UO]1uS>tW#\u00017\u0011\u00055tW\"A\t\n\u0005=\f\"\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR\u0011!o\u001f\t\u0004CM,\u0018B\u0001;#\u0005\u0019y\u0005\u000f^5p]B\u0019a/\u001f\u0011\u000e\u0003]T!\u0001_\n\u0002\u0007I$G-\u0003\u0002{o\n\u0019!\u000b\u0012#\t\u000bqL\u0001\u0019A?\u0002\u0013Y\fG.\u001b3US6,\u0007CA7\u007f\u0013\ty\u0018C\u0001\u0003US6,\u0017!D1t\u0015\u00064\u0018\rR*ue\u0016\fW.\u0006\u0002\u0002\u0006A)\u0011qAA\u0007A5\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u0017y\u0011\u0001\u00026bm\u0006LA!a\u0004\u0002\n\tY!*\u0019<b\tN#(/Z1n\u00039\t7OS1wC\u0012\u001bFO]3b[\u0002\u0002"
)
public class PythonTransformed2DStream extends DStream {
   private final DStream parent;
   private final DStream parent2;
   private final TransformFunction func;
   private final JavaDStream asJavaDStream;

   public TransformFunction func() {
      return this.func;
   }

   public List dependencies() {
      return new .colon.colon(this.parent, new .colon.colon(this.parent2, scala.collection.immutable.Nil..MODULE$));
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      RDD empty = this.ssc().sparkContext().emptyRDD(scala.reflect.ClassTag..MODULE$.Nothing());
      RDD rdd1 = (RDD)this.parent.getOrCompute(validTime).getOrElse(() -> empty);
      RDD rdd2 = (RDD)this.parent2.getOrCompute(validTime).getOrElse(() -> empty);
      return this.func().apply(new Some(rdd1), new Some(rdd2), validTime);
   }

   public JavaDStream asJavaDStream() {
      return this.asJavaDStream;
   }

   public PythonTransformed2DStream(final DStream parent, final DStream parent2, final PythonTransformFunction pfunc) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      this.parent = parent;
      this.parent2 = parent2;
      this.func = new TransformFunction(pfunc);
      this.asJavaDStream = JavaDStream$.MODULE$.fromDStream(this, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
