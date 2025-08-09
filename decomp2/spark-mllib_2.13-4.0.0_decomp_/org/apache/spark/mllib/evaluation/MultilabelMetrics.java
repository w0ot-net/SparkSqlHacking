package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.Function2;
import scala.Tuple2;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\u000f\u001e\u0001!B\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0007\u007f\u0001!\taH'\t\u000f\r\u0004!\u0019!C\u0005I\"1\u0001\u000e\u0001Q\u0001\n\u0015Dq!\u001b\u0001C\u0002\u0013\u0005!\u000e\u0003\u0004m\u0001\u0001\u0006I\u0001\u0010\u0005\b]\u0002\u0011\r\u0011\"\u0001k\u0011\u0019\u0001\b\u0001)A\u0005y!9!\u000f\u0001b\u0001\n\u0003Q\u0007B\u0002;\u0001A\u0003%A\bC\u0004w\u0001\t\u0007I\u0011\u00016\t\ra\u0004\u0001\u0015!\u0003=\u0011\u001dQ\bA1A\u0005\u0002)Da\u0001 \u0001!\u0002\u0013a\u0004b\u0002@\u0001\u0005\u0004%\tA\u001b\u0005\b\u0003\u0003\u0001\u0001\u0015!\u0003=\u0011\u00191\b\u0001\"\u0001\u0002\u0006!1!\u0010\u0001C\u0001\u0003\u001bAaA \u0001\u0005\u0002\u0005M\u0001BCA\r\u0001!\u0015\r\u0011\"\u0003\u0002\u001c!Q\u00111\u0005\u0001\t\u0006\u0004%I!a\u0007\t\u0015\u0005\u0015\u0002\u0001#b\u0001\n\u0013\tY\u0002C\u0005\u0002(\u0001A)\u0019!C\u0001U\"I\u00111\u0006\u0001\t\u0006\u0004%\tA\u001b\u0005\n\u0003_\u0001\u0001R1A\u0005\u0002)D!\"a\r\u0001\u0011\u000b\u0007I\u0011AA\u001b\u0005EiU\u000f\u001c;jY\u0006\u0014W\r\\'fiJL7m\u001d\u0006\u0003=}\t!\"\u001a<bYV\fG/[8o\u0015\t\u0001\u0013%A\u0003nY2L'M\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0015\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g\u0003M\u0001(/\u001a3jGRLwN\\!oI2\u000b'-\u001a7t!\r\tDGN\u0007\u0002e)\u00111'I\u0001\u0004e\u0012$\u0017BA\u001b3\u0005\r\u0011F\t\u0012\t\u0005U]J\u0014(\u0003\u00029W\t1A+\u001e9mKJ\u00022A\u000b\u001e=\u0013\tY4FA\u0003BeJ\f\u0017\u0010\u0005\u0002+{%\u0011ah\u000b\u0002\u0007\t>,(\r\\3\u0002\rqJg.\u001b;?)\t\t5\t\u0005\u0002C\u00015\tQ\u0004C\u00030\u0005\u0001\u0007\u0001\u0007K\u0002\u0003\u000b.\u0003\"AR%\u000e\u0003\u001dS!\u0001S\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002K\u000f\n)1+\u001b8dK\u0006\nA*A\u00032]Ir\u0003\u0007\u0006\u0002B\u001d\")qf\u0001a\u0001\u001fB\u0011\u0001\u000b\u0019\b\u0003#vs!AU.\u000f\u0005MSfB\u0001+Z\u001d\t)\u0006,D\u0001W\u0015\t9v%\u0001\u0004=e>|GOP\u0005\u0002M%\u0011A%J\u0005\u0003E\rJ!\u0001X\u0011\u0002\u0007M\fH.\u0003\u0002_?\u00069\u0001/Y2lC\u001e,'B\u0001/\"\u0013\t\t'MA\u0005ECR\fgI]1nK*\u0011alX\u0001\bgVlW.\u0019:z+\u0005)\u0007C\u0001\"g\u0013\t9WD\u0001\u000bNk2$\u0018\u000e\\1cK2\u001cV/\\7be&TXM]\u0001\tgVlW.\u0019:zA\u0005q1/\u001e2tKR\f5mY;sC\u000eLX#\u0001\u001f)\u0007\u0019)5*A\btk\n\u001cX\r^!dGV\u0014\u0018mY=!Q\r9QiS\u0001\tC\u000e\u001cWO]1ds\"\u001a\u0001\"R&\u0002\u0013\u0005\u001c7-\u001e:bGf\u0004\u0003fA\u0005F\u0017\u0006Y\u0001.Y7nS:<Gj\\:tQ\rQQiS\u0001\rQ\u0006lW.\u001b8h\u0019>\u001c8\u000f\t\u0015\u0004\u0017\u0015[\u0015!\u00039sK\u000eL7/[8oQ\raQiS\u0001\u000baJ,7-[:j_:\u0004\u0003fA\u0007F\u0017\u00061!/Z2bY2D3AD#L\u0003\u001d\u0011XmY1mY\u0002B3aD#L\u0003%1\u0017'T3bgV\u0014X\rK\u0002\u0011\u000b.\u000b!BZ\u0019NK\u0006\u001cXO]3!Q\r\tRi\u0013\u000b\u0004y\u0005\u001d\u0001BBA\u0005%\u0001\u0007A(A\u0003mC\n,G\u000eK\u0002\u0013\u000b.#2\u0001PA\b\u0011\u0019\tIa\u0005a\u0001y!\u001a1#R&\u0015\u0007q\n)\u0002\u0003\u0004\u0002\nQ\u0001\r\u0001\u0010\u0015\u0004)\u0015[\u0015!B:v[R\u0003XCAA\u000f!\rQ\u0013qD\u0005\u0004\u0003CY#\u0001\u0002'p]\u001e\f!b];n\rB\u001cE.Y:t\u0003)\u0019X/\u001c$o\u00072\f7o]\u0001\u000f[&\u001c'o\u001c)sK\u000eL7/[8oQ\rARiS\u0001\f[&\u001c'o\u001c*fG\u0006dG\u000eK\u0002\u001a\u000b.\u000ba\"\\5de>4\u0015'T3bgV\u0014X\rK\u0002\u001b\u000b.\u000ba\u0001\\1cK2\u001cX#A\u001d)\u0007m)5\nK\u0002\u0001\u000b.\u0003"
)
public class MultilabelMetrics {
   private long sumTp;
   private long sumFpClass;
   private long sumFnClass;
   private double microPrecision;
   private double microRecall;
   private double microF1Measure;
   private double[] labels;
   private final MultilabelSummarizer summary;
   private final double subsetAccuracy;
   private final double accuracy;
   private final double hammingLoss;
   private final double precision;
   private final double recall;
   private final double f1Measure;
   private volatile byte bitmap$0;

   private MultilabelSummarizer summary() {
      return this.summary;
   }

   public double subsetAccuracy() {
      return this.subsetAccuracy;
   }

   public double accuracy() {
      return this.accuracy;
   }

   public double hammingLoss() {
      return this.hammingLoss;
   }

   public double precision() {
      return this.precision;
   }

   public double recall() {
      return this.recall;
   }

   public double f1Measure() {
      return this.f1Measure;
   }

   public double precision(final double label) {
      long tp = BoxesRunTime.unboxToLong(this.summary().tpPerClass().apply(BoxesRunTime.boxToDouble(label)));
      long fp = BoxesRunTime.unboxToLong(this.summary().fpPerClass().getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcJ.sp)() -> 0L));
      return tp + fp == 0L ? (double)0.0F : (double)tp / (double)(tp + fp);
   }

   public double recall(final double label) {
      long tp = BoxesRunTime.unboxToLong(this.summary().tpPerClass().apply(BoxesRunTime.boxToDouble(label)));
      long fn = BoxesRunTime.unboxToLong(this.summary().fnPerClass().getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcJ.sp)() -> 0L));
      return tp + fn == 0L ? (double)0.0F : (double)tp / (double)(tp + fn);
   }

   public double f1Measure(final double label) {
      double p = this.precision(label);
      double r = this.recall(label);
      return p + r == (double)0 ? (double)0.0F : (double)2 * p * r / (p + r);
   }

   private long sumTp$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.sumTp = BoxesRunTime.unboxToLong(this.summary().tpPerClass().values().sum(.MODULE$));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sumTp;
   }

   private long sumTp() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.sumTp$lzycompute() : this.sumTp;
   }

   private long sumFpClass$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.sumFpClass = BoxesRunTime.unboxToLong(this.summary().fpPerClass().values().sum(.MODULE$));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sumFpClass;
   }

   private long sumFpClass() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.sumFpClass$lzycompute() : this.sumFpClass;
   }

   private long sumFnClass$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.sumFnClass = BoxesRunTime.unboxToLong(this.summary().fnPerClass().values().sum(.MODULE$));
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sumFnClass;
   }

   private long sumFnClass() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.sumFnClass$lzycompute() : this.sumFnClass;
   }

   private double microPrecision$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.microPrecision = (double)this.sumTp() / (double)(this.sumTp() + this.sumFpClass());
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.microPrecision;
   }

   public double microPrecision() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.microPrecision$lzycompute() : this.microPrecision;
   }

   private double microRecall$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.microRecall = (double)this.sumTp() / (double)(this.sumTp() + this.sumFnClass());
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.microRecall;
   }

   public double microRecall() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.microRecall$lzycompute() : this.microRecall;
   }

   private double microF1Measure$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.microF1Measure = (double)2.0F * (double)this.sumTp() / (double)(2L * this.sumTp() + this.sumFnClass() + this.sumFpClass());
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.microF1Measure;
   }

   public double microF1Measure() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.microF1Measure$lzycompute() : this.microF1Measure;
   }

   private double[] labels$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 64) == 0) {
            this.labels = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])this.summary().tpPerClass().keys().toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
            this.bitmap$0 = (byte)(this.bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.labels;
   }

   public double[] labels() {
      return (byte)(this.bitmap$0 & 64) == 0 ? this.labels$lzycompute() : this.labels;
   }

   public MultilabelMetrics(final RDD predictionAndLabels) {
      MultilabelSummarizer x$1 = new MultilabelSummarizer();
      Function2 x$2 = (summary, sample) -> summary.add((double[])sample._1(), (double[])sample._2());
      Function2 x$3 = (sum1, sum2) -> sum1.merge(sum2);
      int x$4 = predictionAndLabels.treeAggregate$default$4(x$1);
      this.summary = (MultilabelSummarizer)predictionAndLabels.treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(MultilabelSummarizer.class));
      this.subsetAccuracy = this.summary().subsetAccuracy();
      this.accuracy = this.summary().accuracy();
      this.hammingLoss = this.summary().hammingLoss();
      this.precision = this.summary().precision();
      this.recall = this.summary().recall();
      this.f1Measure = this.summary().f1Measure();
   }

   public MultilabelMetrics(final Dataset predictionAndLabels) {
      this(predictionAndLabels.rdd().map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Tuple2 apply(final Row r) {
            return new Tuple2(r.getSeq(0).toArray(scala.reflect.ClassTag..MODULE$.Double()), r.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.Double()));
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
