package org.apache.spark.scheduler;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.SparkEnv$;
import org.apache.spark.metrics.ExecutorMetricType$;
import org.apache.spark.serializer.SerializerHelper$;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ue!B\f\u0019\u0001i\u0001\u0003\u0002\u0003!\u0001\u0005\u0003\u0007I\u0011A!\t\u0011%\u0003!\u00111A\u0005\u0002)C\u0001\u0002\u0015\u0001\u0003\u0002\u0003\u0006KA\u0011\u0005\t#\u0002\u0011\t\u0019!C\u0001%\"A1\u000e\u0001BA\u0002\u0013\u0005A\u000e\u0003\u0005h\u0001\t\u0005\t\u0015)\u0003T\u0011!q\u0007A!a\u0001\n\u0003y\u0007\u0002\u0003<\u0001\u0005\u0003\u0007I\u0011A<\t\u0011e\u0004!\u0011!Q!\nADQA\u001f\u0001\u0005\u0002mD\u0011\"a\u0004\u0001\u0001\u0004%I!!\u0005\t\u0013\u0005e\u0001\u00011A\u0005\n\u0005m\u0001\u0002CA\u0010\u0001\u0001\u0006K!a\u0005\t\u0017\u0005\u0005\u0002\u00011AA\u0002\u0013%\u00111\u0005\u0005\f\u0003K\u0001\u0001\u0019!a\u0001\n\u0013\t9\u0003\u0003\u0006\u0002,\u0001\u0001\r\u0011!Q!\n1BaA\u001f\u0001\u0005\u0002\u00055\u0002B\u0002>\u0001\t\u0003\t\u0019\u0006C\u0004\u0002V\u0001!\t%a\u0016\t\u000f\u0005\r\u0004\u0001\"\u0011\u0002f!9\u0011\u0011\u000f\u0001\u0005\u0002\u0005M\u0004\"CAC\u0001E\u0005I\u0011AAD\u0005A!\u0015N]3diR\u000b7o\u001b*fgVdGO\u0003\u0002\u001a5\u0005I1o\u00195fIVdWM\u001d\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sOV\u0011\u0011EL\n\u0005\u0001\tB\u0003\b\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VM\u001a\t\u0004S)bS\"\u0001\r\n\u0005-B\"A\u0003+bg.\u0014Vm];miB\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00012\u0005\u0005!6\u0001A\t\u0003eU\u0002\"aI\u001a\n\u0005Q\"#a\u0002(pi\"Lgn\u001a\t\u0003GYJ!a\u000e\u0013\u0003\u0007\u0005s\u0017\u0010\u0005\u0002:}5\t!H\u0003\u0002<y\u0005\u0011\u0011n\u001c\u0006\u0002{\u0005!!.\u0019<b\u0013\ty$H\u0001\bFqR,'O\\1mSj\f'\r\\3\u0002\u001fY\fG.^3CsR,')\u001e4gKJ,\u0012A\u0011\t\u0003\u0007\u001ek\u0011\u0001\u0012\u0006\u0003w\u0015S!A\u0012\u000e\u0002\tU$\u0018\u000e\\\u0005\u0003\u0011\u0012\u0013\u0011c\u00115v].,GMQ=uK\n+hMZ3s\u0003M1\u0018\r\\;f\u0005f$XMQ;gM\u0016\u0014x\fJ3r)\tYe\n\u0005\u0002$\u0019&\u0011Q\n\n\u0002\u0005+:LG\u000fC\u0004P\u0005\u0005\u0005\t\u0019\u0001\"\u0002\u0007a$\u0013'\u0001\twC2,XMQ=uK\n+hMZ3sA\u0005a\u0011mY2v[V\u0003H-\u0019;fgV\t1\u000bE\u0002U9~s!!\u0016.\u000f\u0005YKV\"A,\u000b\u0005a\u0003\u0014A\u0002\u001fs_>$h(C\u0001&\u0013\tYF%A\u0004qC\u000e\\\u0017mZ3\n\u0005us&aA*fc*\u00111\f\n\u0019\u0004A\u0016L\u0007\u0003B1cI\"l\u0011!R\u0005\u0003G\u0016\u0013Q\"Q2dk6,H.\u0019;peZ\u0013\u0004CA\u0017f\t%1g!!A\u0001\u0002\u000b\u0005\u0011GA\u0002`IE\nQ\"Y2dk6,\u0006\u000fZ1uKN\u0004\u0003CA\u0017j\t%Qg!!A\u0001\u0002\u000b\u0005\u0011GA\u0002`II\n\u0001#Y2dk6,\u0006\u000fZ1uKN|F%Z9\u0015\u0005-k\u0007bB(\u0006\u0003\u0003\u0005\raU\u0001\f[\u0016$(/[2QK\u0006\\7/F\u0001q!\r\u0019\u0013o]\u0005\u0003e\u0012\u0012Q!\u0011:sCf\u0004\"a\t;\n\u0005U$#\u0001\u0002'p]\u001e\fq\"\\3ue&\u001c\u0007+Z1lg~#S-\u001d\u000b\u0003\u0017bDqa\u0014\u0005\u0002\u0002\u0003\u0007\u0001/\u0001\u0007nKR\u0014\u0018n\u0019)fC.\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0006yvt\u0018Q\u0002\t\u0004S\u0001a\u0003\"\u0002!\u000b\u0001\u0004\u0011\u0005\"B)\u000b\u0001\u0004y\b\u0003\u0002+]\u0003\u0003\u0001d!a\u0001\u0002\b\u0005-\u0001CB1c\u0003\u000b\tI\u0001E\u0002.\u0003\u000f!\u0011B\u001a@\u0002\u0002\u0003\u0005)\u0011A\u0019\u0011\u00075\nY\u0001B\u0005k}\u0006\u0005\t\u0011!B\u0001c!)aN\u0003a\u0001a\u00069b/\u00197vK>\u0013'.Z2u\t\u0016\u001cXM]5bY&TX\rZ\u000b\u0003\u0003'\u00012aIA\u000b\u0013\r\t9\u0002\n\u0002\b\u0005>|G.Z1o\u0003m1\u0018\r\\;f\u001f\nTWm\u0019;EKN,'/[1mSj,Gm\u0018\u0013fcR\u00191*!\b\t\u0011=c\u0011\u0011!a\u0001\u0003'\t\u0001D^1mk\u0016|%M[3di\u0012+7/\u001a:jC2L'0\u001a3!\u0003-1\u0018\r\\;f\u001f\nTWm\u0019;\u0016\u00031\nqB^1mk\u0016|%M[3di~#S-\u001d\u000b\u0004\u0017\u0006%\u0002bB(\u0010\u0003\u0003\u0005\r\u0001L\u0001\rm\u0006dW/Z(cU\u0016\u001cG\u000f\t\u000b\by\u0006=\u0012QHA)\u0011\u0019\u0001\u0015\u00031\u0001\u00022A!\u00111GA\u001d\u001b\t\t)DC\u0002\u00028q\n1A\\5p\u0013\u0011\tY$!\u000e\u0003\u0015\tKH/\u001a\"vM\u001a,'\u000f\u0003\u0004R#\u0001\u0007\u0011q\b\t\u0005)r\u000b\t\u0005\r\u0004\u0002D\u0005\u001d\u0013Q\n\t\u0007C\n\f)%a\u0013\u0011\u00075\n9\u0005B\u0006\u0002J\u0005u\u0012\u0011!A\u0001\u0006\u0003\t$aA0%gA\u0019Q&!\u0014\u0005\u0017\u0005=\u0013QHA\u0001\u0002\u0003\u0015\t!\r\u0002\u0004?\u0012\"\u0004\"\u00028\u0012\u0001\u0004\u0001H#\u0001?\u0002\u001b]\u0014\u0018\u000e^3FqR,'O\\1m)\rY\u0015\u0011\f\u0005\b\u00037\u001a\u0002\u0019AA/\u0003\ryW\u000f\u001e\t\u0004s\u0005}\u0013bAA1u\taqJ\u00196fGR|U\u000f\u001e9vi\u0006a!/Z1e\u000bb$XM\u001d8bYR\u00191*a\u001a\t\u000f\u0005%D\u00031\u0001\u0002l\u0005\u0011\u0011N\u001c\t\u0004s\u00055\u0014bAA8u\tYqJ\u00196fGRLe\u000e];u\u0003\u00151\u0018\r\\;f)\ra\u0013Q\u000f\u0005\n\u0003o*\u0002\u0013!a\u0001\u0003s\n\u0011B]3tk2$8+\u001a:\u0011\t\u0005m\u0014\u0011Q\u0007\u0003\u0003{R1!a \u001b\u0003)\u0019XM]5bY&TXM]\u0005\u0005\u0003\u0007\u000biH\u0001\nTKJL\u0017\r\\5{KJLen\u001d;b]\u000e,\u0017a\u0004<bYV,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005%%\u0006BA=\u0003\u0017[#!!$\u0011\t\u0005=\u0015\u0011T\u0007\u0003\u0003#SA!a%\u0002\u0016\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003/#\u0013AC1o]>$\u0018\r^5p]&!\u00111TAI\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class DirectTaskResult implements TaskResult, Externalizable {
   private ChunkedByteBuffer valueByteBuffer;
   private Seq accumUpdates;
   private long[] metricPeaks;
   private boolean valueObjectDeserialized;
   private Object valueObject;

   public ChunkedByteBuffer valueByteBuffer() {
      return this.valueByteBuffer;
   }

   public void valueByteBuffer_$eq(final ChunkedByteBuffer x$1) {
      this.valueByteBuffer = x$1;
   }

   public Seq accumUpdates() {
      return this.accumUpdates;
   }

   public void accumUpdates_$eq(final Seq x$1) {
      this.accumUpdates = x$1;
   }

   public long[] metricPeaks() {
      return this.metricPeaks;
   }

   public void metricPeaks_$eq(final long[] x$1) {
      this.metricPeaks = x$1;
   }

   private boolean valueObjectDeserialized() {
      return this.valueObjectDeserialized;
   }

   private void valueObjectDeserialized_$eq(final boolean x$1) {
      this.valueObjectDeserialized = x$1;
   }

   private Object valueObject() {
      return this.valueObject;
   }

   private void valueObject_$eq(final Object x$1) {
      this.valueObject = x$1;
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.valueByteBuffer().writeExternal(out);
         out.writeInt(this.accumUpdates().size());
         this.accumUpdates().foreach((x$1) -> {
            $anonfun$writeExternal$2(out, x$1);
            return BoxedUnit.UNIT;
         });
         out.writeInt(this.metricPeaks().length);
         .MODULE$.foreach$extension(scala.Predef..MODULE$.longArrayOps(this.metricPeaks()), (JFunction1.mcVJ.sp)(x$1) -> out.writeLong(x$1));
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.valueByteBuffer_$eq(new ChunkedByteBuffer());
         this.valueByteBuffer().readExternal(in);
         int numUpdates = in.readInt();
         if (numUpdates == 0) {
            this.accumUpdates_$eq((Seq)scala.package..MODULE$.Seq().empty());
         } else {
            ArrayBuffer _accumUpdates = new ArrayBuffer();
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numUpdates).foreach((i) -> $anonfun$readExternal$2(_accumUpdates, in, BoxesRunTime.unboxToInt(i)));
            this.accumUpdates_$eq(_accumUpdates.toSeq());
         }

         int numMetrics = in.readInt();
         if (numMetrics == 0) {
            this.metricPeaks_$eq((long[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Long()));
         } else {
            this.metricPeaks_$eq(new long[numMetrics]);
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numMetrics).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.metricPeaks()[i] = in.readLong());
         }

         this.valueObjectDeserialized_$eq(false);
      });
   }

   public Object value(final SerializerInstance resultSer) {
      if (this.valueObjectDeserialized()) {
         return this.valueObject();
      } else {
         SerializerInstance ser = resultSer == null ? SparkEnv$.MODULE$.get().serializer().newInstance() : resultSer;
         this.valueObject_$eq(SerializerHelper$.MODULE$.deserializeFromChunkedBuffer(ser, this.valueByteBuffer(), scala.reflect.ClassTag..MODULE$.Nothing()));
         this.valueObjectDeserialized_$eq(true);
         return this.valueObject();
      }
   }

   public SerializerInstance value$default$1() {
      return null;
   }

   // $FF: synthetic method
   public static final void $anonfun$writeExternal$2(final ObjectOutput out$1, final Object x$1) {
      out$1.writeObject(x$1);
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$readExternal$2(final ArrayBuffer _accumUpdates$1, final ObjectInput in$1, final int i) {
      return (ArrayBuffer)_accumUpdates$1.$plus$eq((AccumulatorV2)in$1.readObject());
   }

   public DirectTaskResult(final ChunkedByteBuffer valueByteBuffer, final Seq accumUpdates, final long[] metricPeaks) {
      this.valueByteBuffer = valueByteBuffer;
      this.accumUpdates = accumUpdates;
      this.metricPeaks = metricPeaks;
      super();
      this.valueObjectDeserialized = false;
   }

   public DirectTaskResult(final ByteBuffer valueByteBuffer, final Seq accumUpdates, final long[] metricPeaks) {
      this(new ChunkedByteBuffer((ByteBuffer[])((Object[])(new ByteBuffer[]{valueByteBuffer}))), accumUpdates, metricPeaks);
   }

   public DirectTaskResult() {
      this((ChunkedByteBuffer)null, (Seq)scala.collection.immutable.Nil..MODULE$, new long[ExecutorMetricType$.MODULE$.numMetrics()]);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
