package org.apache.spark.streaming.api.python;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\u0006\r\u00011A\u0002\u0002\u0003(\u0001\u0005\u0003\u0007I\u0011A(\t\u0011Q\u0003!\u00111A\u0005\u0002UC\u0001b\u0017\u0001\u0003\u0002\u0003\u0006K\u0001\u0015\u0005\u0006A\u0002!\t!\u0019\u0005\u0006I\u0002!\t!\u001a\u0005\u0006I\u0002!\t\u0001\u001f\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\tI\u0003\u0001C\u0005\u0003WAq!!\u0011\u0001\t\u0013\t\u0019\u0005C\u0004\u0002V\u0001!I!a\u0016\u0003#Q\u0013\u0018M\\:g_Jlg)\u001e8di&|gN\u0003\u0002\u000e\u001d\u00051\u0001/\u001f;i_:T!a\u0004\t\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0012%\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sON\u0019\u0001!G\u0011\u0011\u0005iyR\"A\u000e\u000b\u0005qi\u0012\u0001\u00027b]\u001eT\u0011AH\u0001\u0005U\u00064\u0018-\u0003\u0002!7\t1qJ\u00196fGR\u0004RAI\u0014*\u0007\u001ek\u0011a\t\u0006\u0003I\u0015\n\u0001BZ;oGRLwN\u001c\u0006\u0003=\u0019R!a\u0004\n\n\u0005!\u001a#!\u0003$v]\u000e$\u0018n\u001c83!\rQSfL\u0007\u0002W)\u0011A&H\u0001\u0005kRLG.\u0003\u0002/W\t!A*[:ua\t\u0001d\u0007E\u00022eQj\u0011!J\u0005\u0003g\u0015\u0012qAS1wCJ#E\t\u0005\u00026m1\u0001A!C\u001c\u0001\u0003\u0003\u0005\tQ!\u0001:\u0005\ryFEM\u0002\u0001#\tQ\u0004\t\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDHA\u0004O_RD\u0017N\\4\u0011\u0005m\n\u0015B\u0001\"=\u0005\r\te.\u001f\t\u0003\t\u0016k\u0011\u0001E\u0005\u0003\rB\u0011A\u0001V5nKB\u0019\u0011G\r%\u0011\u0007mJ5*\u0003\u0002Ky\t)\u0011I\u001d:bsB\u00111\bT\u0005\u0003\u001br\u0012AAQ=uK\u0006)\u0001OZ;oGV\t\u0001\u000b\u0005\u0002R%6\tA\"\u0003\u0002T\u0019\t9\u0002+\u001f;i_:$&/\u00198tM>\u0014XNR;oGRLwN\\\u0001\na\u001a,hnY0%KF$\"AV-\u0011\u0005m:\u0016B\u0001-=\u0005\u0011)f.\u001b;\t\u000fi\u0013\u0011\u0011!a\u0001!\u0006\u0019\u0001\u0010J\u0019\u0002\rA4WO\\2!Q\t\u0019Q\f\u0005\u0002<=&\u0011q\f\u0010\u0002\niJ\fgn]5f]R\fa\u0001P5oSRtDC\u00012d!\t\t\u0006\u0001C\u0003O\t\u0001\u0007\u0001+A\u0003baBd\u0017\u0010F\u0002g_Z\u00042aO4j\u0013\tAGH\u0001\u0004PaRLwN\u001c\t\u0004U6DU\"A6\u000b\u00051\u0014\u0012a\u0001:eI&\u0011an\u001b\u0002\u0004%\u0012#\u0005\"\u00027\u0006\u0001\u0004\u0001\bcA\u001ehcB\u0012!\u000f\u001e\t\u0004U6\u001c\bCA\u001bu\t%)x.!A\u0001\u0002\u000b\u0005\u0011HA\u0002`IMBQa^\u0003A\u0002\r\u000bA\u0001^5nKR1a-_A\u0001\u0003#AQ\u0001\u001c\u0004A\u0002i\u00042aO4|a\tah\u0010E\u0002k[v\u0004\"!\u000e@\u0005\u0013}L\u0018\u0011!A\u0001\u0006\u0003I$aA0%i!9\u00111\u0001\u0004A\u0002\u0005\u0015\u0011\u0001\u0002:eIJ\u0002BaO4\u0002\bA\"\u0011\u0011BA\u0007!\u0011QW.a\u0003\u0011\u0007U\ni\u0001B\u0006\u0002\u0010\u0005\u0005\u0011\u0011!A\u0001\u0006\u0003I$aA0%k!)qO\u0002a\u0001\u0007\u0006!1-\u00197m)\u00159\u0015qCA\u0014\u0011\u001d\tIb\u0002a\u0001\u00037\tAA\u001d3egB!!&LA\u000fa\u0011\ty\"a\t\u0011\tE\u0012\u0014\u0011\u0005\t\u0004k\u0005\rBaCA\u0013\u0003/\t\t\u0011!A\u0003\u0002e\u00121a\u0018\u00137\u0011\u00159x\u00011\u0001D\u0003m\u0019\u0017\r\u001c7QsRDwN\u001c+sC:\u001chm\u001c:n\rVt7\r^5p]R)q)!\f\u00026!1q\u000f\u0003a\u0001\u0003_\u00012aOA\u0019\u0013\r\t\u0019\u0004\u0010\u0002\u0005\u0019>tw\rC\u0004\u0002\u001a!\u0001\r!a\u000e1\t\u0005e\u0012Q\b\t\u0005U5\nY\u0004E\u00026\u0003{!1\"a\u0010\u00026\u0005\u0005\t\u0011!B\u0001s\t\u0019q\fJ\u001c\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0004-\u0006\u0015\u0003bBA$\u0013\u0001\u0007\u0011\u0011J\u0001\u0004_V$\b\u0003BA&\u0003#j!!!\u0014\u000b\u0007\u0005=S$\u0001\u0002j_&!\u00111KA'\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7\u0002\u0015I,\u0017\rZ(cU\u0016\u001cG\u000fF\u0002W\u00033Bq!a\u0017\u000b\u0001\u0004\ti&\u0001\u0002j]B!\u00111JA0\u0013\u0011\t\t'!\u0014\u0003#=\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW\u000e"
)
public class TransformFunction implements Function2 {
   private transient PythonTransformFunction pfunc;

   public PythonTransformFunction pfunc() {
      return this.pfunc;
   }

   public void pfunc_$eq(final PythonTransformFunction x$1) {
      this.pfunc = x$1;
   }

   public Option apply(final Option rdd, final Time time) {
      List rdds = .MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon((JavaRDD)rdd.map((x$1) -> org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(x$1, scala.reflect.ClassTag..MODULE$.apply(Object.class))).orNull(scala..less.colon.less..MODULE$.refl()), scala.collection.immutable.Nil..MODULE$)).asJava();
      return scala.Option..MODULE$.apply(this.callPythonTransformFunction(time.milliseconds(), rdds)).map((x$2) -> x$2.rdd());
   }

   public Option apply(final Option rdd, final Option rdd2, final Time time) {
      List rdds = .MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon((JavaRDD)rdd.map((x$3) -> org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(x$3, scala.reflect.ClassTag..MODULE$.apply(Object.class))).orNull(scala..less.colon.less..MODULE$.refl()), new scala.collection.immutable..colon.colon((JavaRDD)rdd2.map((x$4) -> org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(x$4, scala.reflect.ClassTag..MODULE$.apply(Object.class))).orNull(scala..less.colon.less..MODULE$.refl()), scala.collection.immutable.Nil..MODULE$))).asJava();
      return scala.Option..MODULE$.apply(this.callPythonTransformFunction(time.milliseconds(), rdds)).map((x$5) -> x$5.rdd());
   }

   public JavaRDD call(final List rdds, final Time time) {
      return this.callPythonTransformFunction(time.milliseconds(), rdds);
   }

   private JavaRDD callPythonTransformFunction(final long time, final List rdds) {
      JavaRDD resultRDD = this.pfunc().call(time, rdds);
      String failure = this.pfunc().getLastFailure();
      if (failure != null) {
         throw new SparkException("An exception was raised by Python:\n" + failure);
      } else {
         return resultRDD;
      }
   }

   private void writeObject(final ObjectOutputStream out) {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         byte[] bytes = PythonTransformFunctionSerializer$.MODULE$.serialize(this.pfunc());
         out.writeInt(bytes.length);
         out.write(bytes);
      });
   }

   private void readObject(final ObjectInputStream in) {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         int length = in.readInt();
         byte[] bytes = new byte[length];
         in.readFully(bytes);
         this.pfunc_$eq(PythonTransformFunctionSerializer$.MODULE$.deserialize(bytes));
      });
   }

   public TransformFunction(final PythonTransformFunction pfunc) {
      this.pfunc = pfunc;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
