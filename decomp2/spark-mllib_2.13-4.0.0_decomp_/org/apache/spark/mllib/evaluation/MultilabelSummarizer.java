package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c!B\u0015+\u0001)\"\u0004\"\u0002%\u0001\t\u0003I\u0005b\u0002'\u0001\u0001\u0004%I!\u0014\u0005\b#\u0002\u0001\r\u0011\"\u0003S\u0011\u0019A\u0006\u0001)Q\u0005\u001d\"9\u0011\f\u0001b\u0001\n\u0013Q\u0006B\u00024\u0001A\u0003%1\fC\u0004h\u0001\u0001\u0007I\u0011B'\t\u000f!\u0004\u0001\u0019!C\u0005S\"11\u000e\u0001Q!\n9Cq\u0001\u001c\u0001A\u0002\u0013%Q\u000eC\u0004o\u0001\u0001\u0007I\u0011B8\t\rE\u0004\u0001\u0015)\u0003d\u0011\u001d\u0011\b\u00011A\u0005\n5Cqa\u001d\u0001A\u0002\u0013%A\u000f\u0003\u0004w\u0001\u0001\u0006KA\u0014\u0005\bo\u0002\u0001\r\u0011\"\u0003n\u0011\u001dA\b\u00011A\u0005\neDaa\u001f\u0001!B\u0013\u0019\u0007b\u0002?\u0001\u0001\u0004%I!\u001c\u0005\b{\u0002\u0001\r\u0011\"\u0003\u007f\u0011\u001d\t\t\u0001\u0001Q!\n\rD\u0001\"a\u0001\u0001\u0001\u0004%I!\u001c\u0005\n\u0003\u000b\u0001\u0001\u0019!C\u0005\u0003\u000fAq!a\u0003\u0001A\u0003&1\rC\u0005\u0002\u000e\u0001\u0011\r\u0011\"\u0001\u0002\u0010!A\u0011q\u0003\u0001!\u0002\u0013\t\t\u0002C\u0005\u0002\u001a\u0001\u0011\r\u0011\"\u0001\u0002\u0010!A\u00111\u0004\u0001!\u0002\u0013\t\t\u0002C\u0005\u0002\u001e\u0001\u0011\r\u0011\"\u0001\u0002\u0010!A\u0011q\u0004\u0001!\u0002\u0013\t\t\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005U\u0002\u0001\"\u0001\u00028!1\u0011Q\b\u0001\u0005\u00025Ca!a\u0010\u0001\t\u0003i\u0005BBA!\u0001\u0011\u0005Q\u000e\u0003\u0004\u0002D\u0001!\t!\u001c\u0005\u0007\u0003\u000b\u0002A\u0011A7\t\r\u0005\u001d\u0003\u0001\"\u0001n\u0011\u0019\tI\u0005\u0001C\u0001[\"1\u00111\n\u0001\u0005\u00025\u0014A#T;mi&d\u0017MY3m'VlW.\u0019:ju\u0016\u0014(BA\u0016-\u0003))g/\u00197vCRLwN\u001c\u0006\u0003[9\nQ!\u001c7mS\nT!a\f\u0019\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0012\u0014AB1qC\u000eDWMC\u00014\u0003\ry'oZ\n\u0004\u0001UZ\u0004C\u0001\u001c:\u001b\u00059$\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i:$AB!osJ+g\r\u0005\u0002=\u000b:\u0011Qh\u0011\b\u0003}\tk\u0011a\u0010\u0006\u0003\u0001\u0006\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002q%\u0011AiN\u0001\ba\u0006\u001c7.Y4f\u0013\t1uI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Eo\u00051A(\u001b8jiz\"\u0012A\u0013\t\u0003\u0017\u0002i\u0011AK\u0001\u0007I>\u001c7I\u001c;\u0016\u00039\u0003\"AN(\n\u0005A;$\u0001\u0002'p]\u001e\f!\u0002Z8d\u0007:$x\fJ3r)\t\u0019f\u000b\u0005\u00027)&\u0011Qk\u000e\u0002\u0005+:LG\u000fC\u0004X\u0007\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0013'A\u0004e_\u000e\u001ce\u000e\u001e\u0011\u0002\u00111\f'-\u001a7TKR,\u0012a\u0017\t\u00049\u0006\u001cW\"A/\u000b\u0005y{\u0016aB7vi\u0006\u0014G.\u001a\u0006\u0003A^\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0011WLA\u0002TKR\u0004\"A\u000e3\n\u0005\u0015<$A\u0002#pk\ndW-A\u0005mC\n,GnU3uA\u0005\t2/\u001e2tKR\f5mY;sC\u000eL8I\u001c;\u0002+M,(m]3u\u0003\u000e\u001cWO]1ds\u000esGo\u0018\u0013fcR\u00111K\u001b\u0005\b/\"\t\t\u00111\u0001O\u0003I\u0019XOY:fi\u0006\u001b7-\u001e:bGf\u001ce\u000e\u001e\u0011\u0002\u0017\u0005\u001c7-\u001e:bGf\u001cV/\\\u000b\u0002G\u0006y\u0011mY2ve\u0006\u001c\u0017pU;n?\u0012*\u0017\u000f\u0006\u0002Ta\"9qkCA\u0001\u0002\u0004\u0019\u0017\u0001D1dGV\u0014\u0018mY=Tk6\u0004\u0013A\u00045b[6Lgn\u001a'pgN\u001cV/\\\u0001\u0013Q\u0006lW.\u001b8h\u0019>\u001c8oU;n?\u0012*\u0017\u000f\u0006\u0002Tk\"9qKDA\u0001\u0002\u0004q\u0015a\u00045b[6Lgn\u001a'pgN\u001cV/\u001c\u0011\u0002\u0019A\u0014XmY5tS>t7+^7\u0002!A\u0014XmY5tS>t7+^7`I\u0015\fHCA*{\u0011\u001d9\u0016#!AA\u0002\r\fQ\u0002\u001d:fG&\u001c\u0018n\u001c8Tk6\u0004\u0013!\u0003:fG\u0006dGnU;n\u00035\u0011XmY1mYN+Xn\u0018\u0013fcR\u00111k \u0005\b/R\t\t\u00111\u0001d\u0003)\u0011XmY1mYN+X\u000eI\u0001\rMFjU-Y:ve\u0016\u001cV/\\\u0001\u0011MFjU-Y:ve\u0016\u001cV/\\0%KF$2aUA\u0005\u0011\u001d9v#!AA\u0002\r\fQBZ\u0019NK\u0006\u001cXO]3Tk6\u0004\u0013A\u0003;q!\u0016\u00148\t\\1tgV\u0011\u0011\u0011\u0003\t\u00069\u0006M1MT\u0005\u0004\u0003+i&aA'ba\u0006YA\u000f\u001d)fe\u000ec\u0017m]:!\u0003)1\u0007\u000fU3s\u00072\f7o]\u0001\fMB\u0004VM]\"mCN\u001c\b%\u0001\u0006g]B+'o\u00117bgN\f1B\u001a8QKJ\u001cE.Y:tA\u0005\u0019\u0011\r\u001a3\u0015\r\u0005\u0015\u0012qEA\u0019\u001b\u0005\u0001\u0001bBA\u0015?\u0001\u0007\u00111F\u0001\faJ,G-[2uS>t7\u000f\u0005\u00037\u0003[\u0019\u0017bAA\u0018o\t)\u0011I\u001d:bs\"9\u00111G\u0010A\u0002\u0005-\u0012A\u00027bE\u0016d7/A\u0003nKJ<W\r\u0006\u0003\u0002&\u0005e\u0002BBA\u001eA\u0001\u0007!*A\u0003pi\",'/A\u0004ok6$unY:\u0002\u00139,X\u000eT1cK2\u001c\u0018AD:vEN,G/Q2dkJ\f7-_\u0001\tC\u000e\u001cWO]1ds\u0006Y\u0001.Y7nS:<Gj\\:t\u0003%\u0001(/Z2jg&|g.\u0001\u0004sK\u000e\fG\u000e\\\u0001\nMFjU-Y:ve\u0016\u0004"
)
public class MultilabelSummarizer implements Serializable {
   private long docCnt = 0L;
   private final Set labelSet;
   private long subsetAccuracyCnt;
   private double accuracySum;
   private long hammingLossSum;
   private double precisionSum;
   private double recallSum;
   private double f1MeasureSum;
   private final Map tpPerClass;
   private final Map fpPerClass;
   private final Map fnPerClass;

   private long docCnt() {
      return this.docCnt;
   }

   private void docCnt_$eq(final long x$1) {
      this.docCnt = x$1;
   }

   private Set labelSet() {
      return this.labelSet;
   }

   private long subsetAccuracyCnt() {
      return this.subsetAccuracyCnt;
   }

   private void subsetAccuracyCnt_$eq(final long x$1) {
      this.subsetAccuracyCnt = x$1;
   }

   private double accuracySum() {
      return this.accuracySum;
   }

   private void accuracySum_$eq(final double x$1) {
      this.accuracySum = x$1;
   }

   private long hammingLossSum() {
      return this.hammingLossSum;
   }

   private void hammingLossSum_$eq(final long x$1) {
      this.hammingLossSum = x$1;
   }

   private double precisionSum() {
      return this.precisionSum;
   }

   private void precisionSum_$eq(final double x$1) {
      this.precisionSum = x$1;
   }

   private double recallSum() {
      return this.recallSum;
   }

   private void recallSum_$eq(final double x$1) {
      this.recallSum = x$1;
   }

   private double f1MeasureSum() {
      return this.f1MeasureSum;
   }

   private void f1MeasureSum_$eq(final double x$1) {
      this.f1MeasureSum = x$1;
   }

   public Map tpPerClass() {
      return this.tpPerClass;
   }

   public Map fpPerClass() {
      return this.fpPerClass;
   }

   public Map fnPerClass() {
      return this.fnPerClass;
   }

   public MultilabelSummarizer add(final double[] predictions, final double[] labels) {
      double[] intersection = (double[]).MODULE$.intersect$extension(scala.Predef..MODULE$.doubleArrayOps(predictions), scala.Predef..MODULE$.wrapDoubleArray(labels));
      this.docCnt_$eq(this.docCnt() + 1L);
      this.labelSet().$plus$plus$eq(scala.Predef..MODULE$.wrapDoubleArray(labels));
      if (Arrays.equals(predictions, labels)) {
         this.subsetAccuracyCnt_$eq(this.subsetAccuracyCnt() + 1L);
      }

      this.accuracySum_$eq(this.accuracySum() + (double)intersection.length / (double)(labels.length + predictions.length - intersection.length));
      this.hammingLossSum_$eq(this.hammingLossSum() + (long)(labels.length + predictions.length - 2 * intersection.length));
      if (predictions.length > 0) {
         this.precisionSum_$eq(this.precisionSum() + (double)intersection.length / (double)predictions.length);
      }

      this.recallSum_$eq(this.recallSum() + (double)intersection.length / (double)labels.length);
      this.f1MeasureSum_$eq(this.f1MeasureSum() + (double)2.0F * (double)intersection.length / (double)(predictions.length + labels.length));
      .MODULE$.foreach$extension(scala.Predef..MODULE$.doubleArrayOps(intersection), (JFunction1.mcVD.sp)(k) -> {
         long v = BoxesRunTime.unboxToLong(this.tpPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         this.tpPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v + 1L));
      });
      .MODULE$.foreach$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.diff$extension(scala.Predef..MODULE$.doubleArrayOps(predictions), scala.Predef..MODULE$.wrapDoubleArray(labels))), (JFunction1.mcVD.sp)(k) -> {
         long v = BoxesRunTime.unboxToLong(this.fpPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         this.fpPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v + 1L));
      });
      .MODULE$.foreach$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.diff$extension(scala.Predef..MODULE$.doubleArrayOps(labels), scala.Predef..MODULE$.wrapDoubleArray(predictions))), (JFunction1.mcVD.sp)(k) -> {
         long v = BoxesRunTime.unboxToLong(this.fnPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         this.fnPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v + 1L));
      });
      return this;
   }

   public MultilabelSummarizer merge(final MultilabelSummarizer other) {
      if (other.docCnt() > 0L) {
         this.docCnt_$eq(this.docCnt() + other.docCnt());
         this.labelSet().$plus$plus$eq(other.labelSet());
         this.subsetAccuracyCnt_$eq(this.subsetAccuracyCnt() + other.subsetAccuracyCnt());
         this.accuracySum_$eq(this.accuracySum() + other.accuracySum());
         this.hammingLossSum_$eq(this.hammingLossSum() + other.hammingLossSum());
         this.precisionSum_$eq(this.precisionSum() + other.precisionSum());
         this.recallSum_$eq(this.recallSum() + other.recallSum());
         this.f1MeasureSum_$eq(this.f1MeasureSum() + other.f1MeasureSum());
         other.tpPerClass().foreach((x0$1) -> {
            $anonfun$merge$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
         other.fpPerClass().foreach((x0$2) -> {
            $anonfun$merge$3(this, x0$2);
            return BoxedUnit.UNIT;
         });
         other.fnPerClass().foreach((x0$3) -> {
            $anonfun$merge$5(this, x0$3);
            return BoxedUnit.UNIT;
         });
      }

      return this;
   }

   public long numDocs() {
      return this.docCnt();
   }

   public long numLabels() {
      return (long)this.labelSet().size();
   }

   public double subsetAccuracy() {
      return (double)this.subsetAccuracyCnt() / (double)this.numDocs();
   }

   public double accuracy() {
      return this.accuracySum() / (double)this.numDocs();
   }

   public double hammingLoss() {
      return (double)this.hammingLossSum() / (double)this.numDocs() / (double)this.numLabels();
   }

   public double precision() {
      return this.precisionSum() / (double)this.numDocs();
   }

   public double recall() {
      return this.recallSum() / (double)this.numDocs();
   }

   public double f1Measure() {
      return this.f1MeasureSum() / (double)this.numDocs();
   }

   // $FF: synthetic method
   public static final void $anonfun$merge$1(final MultilabelSummarizer $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double k = x0$1._1$mcD$sp();
         long v1 = x0$1._2$mcJ$sp();
         long v0 = BoxesRunTime.unboxToLong($this.tpPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         $this.tpPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v0 + v1));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$merge$3(final MultilabelSummarizer $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         double k = x0$2._1$mcD$sp();
         long v1 = x0$2._2$mcJ$sp();
         long v0 = BoxesRunTime.unboxToLong($this.fpPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         $this.fpPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v0 + v1));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$merge$5(final MultilabelSummarizer $this, final Tuple2 x0$3) {
      if (x0$3 != null) {
         double k = x0$3._1$mcD$sp();
         long v1 = x0$3._2$mcJ$sp();
         long v0 = BoxesRunTime.unboxToLong($this.fnPerClass().getOrElse(BoxesRunTime.boxToDouble(k), (JFunction0.mcJ.sp)() -> 0L));
         $this.fnPerClass().update(BoxesRunTime.boxToDouble(k), BoxesRunTime.boxToLong(v0 + v1));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$3);
      }
   }

   public MultilabelSummarizer() {
      this.labelSet = (Set)scala.collection.mutable.Set..MODULE$.empty();
      this.subsetAccuracyCnt = 0L;
      this.accuracySum = (double)0.0F;
      this.hammingLossSum = 0L;
      this.precisionSum = (double)0.0F;
      this.recallSum = (double)0.0F;
      this.f1MeasureSum = (double)0.0F;
      this.tpPerClass = (Map)scala.collection.mutable.Map..MODULE$.empty();
      this.fpPerClass = (Map)scala.collection.mutable.Map..MODULE$.empty();
      this.fnPerClass = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
