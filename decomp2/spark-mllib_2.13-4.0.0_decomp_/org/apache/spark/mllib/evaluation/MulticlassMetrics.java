package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u000e\u001d\u0001\u001dB\u0001B\f\u0001\u0003\u0002\u0003\u0006Ia\f\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0007\u0003\u0002!\tAH*\t\u0011%\u0004\u0001R1A\u0005\n)D\u0001b\u001e\u0001\t\u0006\u0004%I\u0001\u001f\u0005\tu\u0002A)\u0019!C\u0005w\"AA\u0010\u0001EC\u0002\u0013%\u0001\u0010\u0003\u0005~\u0001!\u0015\r\u0011\"\u0003y\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\ty\u0001\u0001C\u0001\u0003#Aq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005%\u0002\u0001\"\u0001\u0002,!9\u0011\u0011\u0007\u0001\u0005\u0002\u0005M\u0002bBA\u0019\u0001\u0011\u0005\u0011Q\b\u0005\n\u0003\u0007\u0002\u0001R1A\u0005\u0002mD\u0011\"a\u0013\u0001\u0011\u000b\u0007I\u0011A>\t\u0013\u0005=\u0003\u0001#b\u0001\n\u0003Y\b\"CA*\u0001!\u0015\r\u0011\"\u0001|\u0011%\t9\u0006\u0001EC\u0002\u0013\u00051\u0010C\u0004\u0002\\\u0001!\t!!\u0018\t\u0013\u0005m\u0003\u0001#b\u0001\n\u0003Y\bBCA3\u0001!\u0015\r\u0011\"\u0001\u0002h!I\u0011\u0011\u000f\u0001\t\u0006\u0004%\ta\u001f\u0005\b\u0003s\u0002A\u0011AA>\u0011%\t\u0019\tAI\u0001\n\u0003\t)IA\tNk2$\u0018n\u00197bgNlU\r\u001e:jGNT!!\b\u0010\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002 A\u0005)Q\u000e\u001c7jE*\u0011\u0011EI\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0011\na!\u00199bG\",'\"A\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001A\u0003CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#AB!osJ+g-A\nqe\u0016$\u0017n\u0019;j_:\fe\u000e\u001a'bE\u0016d7\u000f\r\u00021qA\u0019\u0011\u0007\u000e\u001c\u000e\u0003IR!a\r\u0011\u0002\u0007I$G-\u0003\u00026e\t\u0019!\u000b\u0012#\u0011\u0005]BD\u0002\u0001\u0003\ns\u0005\t\t\u0011!A\u0003\u0002i\u00121a\u0018\u00132#\tYd\b\u0005\u0002*y%\u0011QH\u000b\u0002\b\u001d>$\b.\u001b8h!\tIs(\u0003\u0002AU\t9\u0001K]8ek\u000e$\u0018A\u0002\u001fj]&$h\b\u0006\u0002D\u000bB\u0011A\tA\u0007\u00029!)aF\u0001a\u0001\rB\u0012q)\u0013\t\u0004cQB\u0005CA\u001cJ\t%IT)!A\u0001\u0002\u000b\u0005!\bK\u0002\u0003\u0017F\u0003\"\u0001T(\u000e\u00035S!A\u0014\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Q\u001b\n)1+\u001b8dK\u0006\n!+A\u00032]Er\u0003\u0007\u0006\u0002D)\")af\u0001a\u0001+B\u0011aK\u001a\b\u0003/\u000et!\u0001W1\u000f\u0005e\u0003gB\u0001.`\u001d\tYf,D\u0001]\u0015\tif%\u0001\u0004=e>|GOP\u0005\u0002K%\u00111\u0005J\u0005\u0003C\tJ!A\u0019\u0011\u0002\u0007M\fH.\u0003\u0002eK\u00069\u0001/Y2lC\u001e,'B\u00012!\u0013\t9\u0007NA\u0005ECR\fgI]1nK*\u0011A-Z\u0001\u000bG>tg-^:j_:\u001cX#A6\u0011\t1|\u0017\u000f^\u0007\u0002[*\u0011aNK\u0001\u000bG>dG.Z2uS>t\u0017B\u00019n\u0005\ri\u0015\r\u001d\t\u0005SI$H/\u0003\u0002tU\t1A+\u001e9mKJ\u0002\"!K;\n\u0005YT#A\u0002#pk\ndW-A\tmC\n,GnQ8v]R\u0014\u0015p\u00117bgN,\u0012!\u001f\t\u0005Y>$H/\u0001\u0006mC\n,GnQ8v]R,\u0012\u0001^\u0001\niB\u0014\u0015p\u00117bgN\f\u0011B\u001a9Cs\u000ec\u0017m]:\u0002\u001f\r|gNZ;tS>tW*\u0019;sSb,\"!!\u0001\u0011\t\u0005\r\u0011\u0011B\u0007\u0003\u0003\u000bQ1!a\u0002\u001f\u0003\u0019a\u0017N\\1mO&!\u00111BA\u0003\u0005\u0019i\u0015\r\u001e:jq\"\u001a\u0011bS)\u0002!Q\u0014X/\u001a)pg&$\u0018N^3SCR,Gc\u0001;\u0002\u0014!1\u0011Q\u0003\u0006A\u0002Q\fQ\u0001\\1cK2D3AC&R\u0003E1\u0017\r\\:f!>\u001c\u0018\u000e^5wKJ\u000bG/\u001a\u000b\u0004i\u0006u\u0001BBA\u000b\u0017\u0001\u0007A\u000fK\u0002\f\u0017F\u000b\u0011\u0002\u001d:fG&\u001c\u0018n\u001c8\u0015\u0007Q\f)\u0003\u0003\u0004\u0002\u00161\u0001\r\u0001\u001e\u0015\u0004\u0019-\u000b\u0016A\u0002:fG\u0006dG\u000eF\u0002u\u0003[Aa!!\u0006\u000e\u0001\u0004!\bfA\u0007L#\u0006Aa-T3bgV\u0014X\rF\u0003u\u0003k\t9\u0004\u0003\u0004\u0002\u00169\u0001\r\u0001\u001e\u0005\u0007\u0003sq\u0001\u0019\u0001;\u0002\t\t,G/\u0019\u0015\u0004\u001d-\u000bFc\u0001;\u0002@!1\u0011QC\bA\u0002QD3aD&R\u0003!\t7mY;sC\u000eL\b\u0006\u0002\tL\u0003\u000f\n#!!\u0013\u0002\u000bIr\u0003G\f\u0019\u00021],\u0017n\u001a5uK\u0012$&/^3Q_NLG/\u001b<f%\u0006$X\rK\u0002\u0012\u0017F\u000b\u0011d^3jO\"$X\r\u001a$bYN,\u0007k\\:ji&4XMU1uK\"\u001a!cS)\u0002\u001d],\u0017n\u001a5uK\u0012\u0014VmY1mY\"\u001a1cS)\u0002#],\u0017n\u001a5uK\u0012\u0004&/Z2jg&|g\u000eK\u0002\u0015\u0017F\u000b\u0001c^3jO\"$X\r\u001a$NK\u0006\u001cXO]3\u0015\u0007Q\fy\u0006\u0003\u0004\u0002:U\u0001\r\u0001\u001e\u0015\u0004+-\u000b\u0006f\u0001\fL#\u00061A.\u00192fYN,\"!!\u001b\u0011\t%\nY\u0007^\u0005\u0004\u0003[R#!B!se\u0006L\bfA\fL#\u0006Y\u0001.Y7nS:<Gj\\:tQ\u0011A2*!\u001e\"\u0005\u0005]\u0014!B\u001a/a9\u0002\u0014a\u00027pO2{7o\u001d\u000b\u0004i\u0006u\u0004\u0002CA@3A\u0005\t\u0019\u0001;\u0002\u0007\u0015\u00048\u000f\u000b\u0003\u001a\u0017\u0006U\u0014!\u00057pO2{7o\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0011\u0016\u0004i\u0006%5FAAF!\u0011\ti)!&\u000e\u0005\u0005=%\u0002BAI\u0003'\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00059S\u0013\u0002BAL\u0003\u001f\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2fQ\r\u00011*\u0015"
)
public class MulticlassMetrics {
   private Map confusions;
   private Map labelCountByClass;
   private double labelCount;
   private Map tpByClass;
   private Map fpByClass;
   private double accuracy;
   private double weightedTruePositiveRate;
   private double weightedFalsePositiveRate;
   private double weightedRecall;
   private double weightedPrecision;
   private double weightedFMeasure;
   private double[] labels;
   private double hammingLoss;
   private final RDD predictionAndLabels;
   private volatile int bitmap$0;

   private Map confusions$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.confusions = .MODULE$.rddToPairRDDFunctions(.MODULE$.rddToPairRDDFunctions(this.predictionAndLabels.map((x0$1) -> {
               if (x0$1 instanceof Tuple4 var3) {
                  Object prediction = var3._1();
                  Object label = var3._2();
                  Object weight = var3._3();
                  if (prediction instanceof Double) {
                     double var7 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var9 = BoxesRunTime.unboxToDouble(label);
                        if (weight instanceof Double) {
                           double var11 = BoxesRunTime.unboxToDouble(weight);
                           return new Tuple2(new Tuple2.mcDD.sp(var9, var7), BoxesRunTime.boxToDouble(var11));
                        }
                     }
                  }
               }

               if (x0$1 instanceof Tuple3 var13) {
                  Object prediction = var13._1();
                  Object label = var13._2();
                  Object weight = var13._3();
                  if (prediction instanceof Double) {
                     double var17 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var19 = BoxesRunTime.unboxToDouble(label);
                        if (weight instanceof Double) {
                           double var21 = BoxesRunTime.unboxToDouble(weight);
                           return new Tuple2(new Tuple2.mcDD.sp(var19, var17), BoxesRunTime.boxToDouble(var21));
                        }
                     }
                  }
               }

               if (x0$1 instanceof Tuple2 var23) {
                  Object prediction = var23._1();
                  Object label = var23._2();
                  if (prediction instanceof Double) {
                     double var26 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var28 = BoxesRunTime.unboxToDouble(label);
                        return new Tuple2(new Tuple2.mcDD.sp(var28, var26), BoxesRunTime.boxToDouble((double)1.0F));
                     }
                  }
               }

               throw new IllegalArgumentException("Expected tuples, got " + x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).reduceByKey((JFunction2.mcDDD.sp)(x$1, x$2) -> x$1 + x$2), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).collectAsMap();
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.confusions;
   }

   private Map confusions() {
      return (this.bitmap$0 & 1) == 0 ? this.confusions$lzycompute() : this.confusions;
   }

   private Map labelCountByClass$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            scala.collection.mutable.Map labelCountByClass = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
            this.confusions().iterator().foreach((x0$1) -> {
               $anonfun$labelCountByClass$1(labelCountByClass, x0$1);
               return BoxedUnit.UNIT;
            });
            this.labelCountByClass = labelCountByClass.toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.labelCountByClass;
   }

   private Map labelCountByClass() {
      return (this.bitmap$0 & 2) == 0 ? this.labelCountByClass$lzycompute() : this.labelCountByClass;
   }

   private double labelCount$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.labelCount = BoxesRunTime.unboxToDouble(this.labelCountByClass().values().sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.labelCount;
   }

   private double labelCount() {
      return (this.bitmap$0 & 4) == 0 ? this.labelCount$lzycompute() : this.labelCount;
   }

   private Map tpByClass$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            scala.collection.mutable.Map tpByClass = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
            this.confusions().iterator().foreach((x0$1) -> {
               $anonfun$tpByClass$1(tpByClass, x0$1);
               return BoxedUnit.UNIT;
            });
            this.tpByClass = tpByClass.toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.tpByClass;
   }

   private Map tpByClass() {
      return (this.bitmap$0 & 8) == 0 ? this.tpByClass$lzycompute() : this.tpByClass;
   }

   private Map fpByClass$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            scala.collection.mutable.Map fpByClass = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
            this.confusions().iterator().foreach((x0$1) -> {
               $anonfun$fpByClass$1(fpByClass, x0$1);
               return BoxedUnit.UNIT;
            });
            this.fpByClass = fpByClass.toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.fpByClass;
   }

   private Map fpByClass() {
      return (this.bitmap$0 & 16) == 0 ? this.fpByClass$lzycompute() : this.fpByClass;
   }

   public Matrix confusionMatrix() {
      int n = this.labels().length;
      double[] values = (double[])scala.Array..MODULE$.ofDim(n * n, scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < n; ++i) {
         for(int j = 0; j < n; ++j) {
            values[i + j * n] = BoxesRunTime.unboxToDouble(this.confusions().getOrElse(new Tuple2.mcDD.sp(this.labels()[i], this.labels()[j]), (JFunction0.mcD.sp)() -> (double)0.0F));
         }
      }

      return Matrices$.MODULE$.dense(n, n, values);
   }

   public double truePositiveRate(final double label) {
      return this.recall(label);
   }

   public double falsePositiveRate(final double label) {
      double fp = BoxesRunTime.unboxToDouble(this.fpByClass().getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcD.sp)() -> (double)0.0F));
      return fp / (this.labelCount() - BoxesRunTime.unboxToDouble(this.labelCountByClass().apply(BoxesRunTime.boxToDouble(label))));
   }

   public double precision(final double label) {
      double tp = BoxesRunTime.unboxToDouble(this.tpByClass().apply(BoxesRunTime.boxToDouble(label)));
      double fp = BoxesRunTime.unboxToDouble(this.fpByClass().getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcD.sp)() -> (double)0.0F));
      return tp + fp == (double)0 ? (double)0.0F : tp / (tp + fp);
   }

   public double recall(final double label) {
      return BoxesRunTime.unboxToDouble(this.tpByClass().apply(BoxesRunTime.boxToDouble(label))) / BoxesRunTime.unboxToDouble(this.labelCountByClass().apply(BoxesRunTime.boxToDouble(label)));
   }

   public double fMeasure(final double label, final double beta) {
      double p = this.precision(label);
      double r = this.recall(label);
      double betaSqrd = beta * beta;
      return p + r == (double)0 ? (double)0.0F : ((double)1 + betaSqrd) * p * r / (betaSqrd * p + r);
   }

   public double fMeasure(final double label) {
      return this.fMeasure(label, (double)1.0F);
   }

   private double accuracy$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.accuracy = BoxesRunTime.unboxToDouble(this.tpByClass().values().sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / this.labelCount();
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.accuracy;
   }

   public double accuracy() {
      return (this.bitmap$0 & 32) == 0 ? this.accuracy$lzycompute() : this.accuracy;
   }

   private double weightedTruePositiveRate$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.weightedTruePositiveRate = this.weightedRecall();
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weightedTruePositiveRate;
   }

   public double weightedTruePositiveRate() {
      return (this.bitmap$0 & 64) == 0 ? this.weightedTruePositiveRate$lzycompute() : this.weightedTruePositiveRate;
   }

   private double weightedFalsePositiveRate$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.weightedFalsePositiveRate = BoxesRunTime.unboxToDouble(((IterableOnceOps)this.labelCountByClass().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$weightedFalsePositiveRate$1(this, x0$1)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weightedFalsePositiveRate;
   }

   public double weightedFalsePositiveRate() {
      return (this.bitmap$0 & 128) == 0 ? this.weightedFalsePositiveRate$lzycompute() : this.weightedFalsePositiveRate;
   }

   private double weightedRecall$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.weightedRecall = BoxesRunTime.unboxToDouble(((IterableOnceOps)this.labelCountByClass().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$weightedRecall$1(this, x0$1)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weightedRecall;
   }

   public double weightedRecall() {
      return (this.bitmap$0 & 256) == 0 ? this.weightedRecall$lzycompute() : this.weightedRecall;
   }

   private double weightedPrecision$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.weightedPrecision = BoxesRunTime.unboxToDouble(((IterableOnceOps)this.labelCountByClass().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$weightedPrecision$1(this, x0$1)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weightedPrecision;
   }

   public double weightedPrecision() {
      return (this.bitmap$0 & 512) == 0 ? this.weightedPrecision$lzycompute() : this.weightedPrecision;
   }

   public double weightedFMeasure(final double beta) {
      return BoxesRunTime.unboxToDouble(((IterableOnceOps)this.labelCountByClass().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$weightedFMeasure$1(this, beta, x0$1)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   private double weightedFMeasure$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            this.weightedFMeasure = this.weightedFMeasure((double)1.0F);
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weightedFMeasure;
   }

   public double weightedFMeasure() {
      return (this.bitmap$0 & 1024) == 0 ? this.weightedFMeasure$lzycompute() : this.weightedFMeasure;
   }

   private double[] labels$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2048) == 0) {
            this.labels = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])this.tpByClass().keys().toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
            this.bitmap$0 |= 2048;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.labels;
   }

   public double[] labels() {
      return (this.bitmap$0 & 2048) == 0 ? this.labels$lzycompute() : this.labels;
   }

   private double hammingLoss$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4096) == 0) {
            DoubleRef numerator = DoubleRef.create((double)0.0F);
            DoubleRef denominator = DoubleRef.create((double)0.0F);
            this.confusions().iterator().foreach((x0$1) -> {
               $anonfun$hammingLoss$1(numerator, denominator, x0$1);
               return BoxedUnit.UNIT;
            });
            this.hammingLoss = numerator.elem / denominator.elem;
            this.bitmap$0 |= 4096;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.hammingLoss;
   }

   public double hammingLoss() {
      return (this.bitmap$0 & 4096) == 0 ? this.hammingLoss$lzycompute() : this.hammingLoss;
   }

   public double logLoss(final double eps) {
      scala.Predef..MODULE$.require(eps > (double)0 && eps < (double)0.5F, () -> "eps must be in range (0, 0.5), but got " + eps);
      double loss1 = -scala.math.package..MODULE$.log(eps);
      double loss2 = -scala.math.package..MODULE$.log1p(-eps);
      RDD qual$1 = this.predictionAndLabels.map((x0$1) -> {
         if (x0$1 instanceof Tuple4 var9) {
            Object label = var9._2();
            Object weight = var9._3();
            Object probability = var9._4();
            if (label instanceof Double) {
               double var13 = BoxesRunTime.unboxToDouble(label);
               if (weight instanceof Double) {
                  double var15 = BoxesRunTime.unboxToDouble(weight);
                  if (probability instanceof double[]) {
                     double[] var17 = (double[])probability;
                     scala.Predef..MODULE$.require((double)((int)var13) == var13 && var13 >= (double)0, () -> "Invalid label " + var13);
                     scala.Predef..MODULE$.require(var17 != null, () -> "probability of each class can not be null");
                     double p = var17[(int)var13];
                     double loss = p < eps ? loss1 : (p > (double)1 - eps ? loss2 : -scala.math.package..MODULE$.log(p));
                     return new Tuple2.mcDD.sp(loss * var15, var15);
                  }
               }
            }
         }

         throw new IllegalArgumentException("Invalid RDD value for MulticlassMetrics.logLoss. Expected quadruples, got " + x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      Function2 x$1 = (x0$2, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$2, x1$1);
         if (var3 != null) {
            Tuple2 var4 = (Tuple2)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var4 != null) {
               double l1 = var4._1$mcD$sp();
               double w1 = var4._2$mcD$sp();
               if (var5 != null) {
                  double l2 = var5._1$mcD$sp();
                  double w2 = var5._2$mcD$sp();
                  return new Tuple2.mcDD.sp(l1 + l2, w1 + w2);
               }
            }
         }

         throw new MatchError(var3);
      };
      int x$2 = qual$1.treeReduce$default$2();
      Tuple2 var9 = (Tuple2)qual$1.treeReduce(x$1, x$2);
      if (var9 != null) {
         double lossSum = var9._1$mcD$sp();
         double weightSum = var9._2$mcD$sp();
         Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(lossSum, weightSum);
         double lossSum = ((Tuple2)var8)._1$mcD$sp();
         double weightSum = ((Tuple2)var8)._2$mcD$sp();
         return lossSum / weightSum;
      } else {
         throw new MatchError(var9);
      }
   }

   public double logLoss$default$1() {
      return 1.0E-15;
   }

   // $FF: synthetic method
   public static final void $anonfun$labelCountByClass$1(final scala.collection.mutable.Map labelCountByClass$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var4 = (Tuple2)x0$1._1();
         double weight = x0$1._2$mcD$sp();
         if (var4 != null) {
            double label = var4._1$mcD$sp();
            double w = BoxesRunTime.unboxToDouble(labelCountByClass$1.getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcD.sp)() -> (double)0.0F));
            labelCountByClass$1.update(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(w + weight));
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$tpByClass$1(final scala.collection.mutable.Map tpByClass$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var4 = (Tuple2)x0$1._1();
         double weight = x0$1._2$mcD$sp();
         if (var4 != null) {
            double label = var4._1$mcD$sp();
            double prediction = var4._2$mcD$sp();
            double w = BoxesRunTime.unboxToDouble(tpByClass$1.getOrElse(BoxesRunTime.boxToDouble(label), (JFunction0.mcD.sp)() -> (double)0.0F));
            if (label == prediction) {
               tpByClass$1.update(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(w + weight));
               BoxedUnit var14 = BoxedUnit.UNIT;
               return;
            }

            if (w == (double)0.0F) {
               tpByClass$1.update(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(w));
               BoxedUnit var13 = BoxedUnit.UNIT;
               return;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$fpByClass$1(final scala.collection.mutable.Map fpByClass$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var4 = (Tuple2)x0$1._1();
         double weight = x0$1._2$mcD$sp();
         if (var4 != null) {
            double label = var4._1$mcD$sp();
            double prediction = var4._2$mcD$sp();
            double w = BoxesRunTime.unboxToDouble(fpByClass$1.getOrElse(BoxesRunTime.boxToDouble(prediction), (JFunction0.mcD.sp)() -> (double)0.0F));
            if (label != prediction) {
               fpByClass$1.update(BoxesRunTime.boxToDouble(prediction), BoxesRunTime.boxToDouble(w + weight));
               BoxedUnit var14 = BoxedUnit.UNIT;
               return;
            }

            if (w == (double)0.0F) {
               fpByClass$1.update(BoxesRunTime.boxToDouble(prediction), BoxesRunTime.boxToDouble(w));
               BoxedUnit var13 = BoxedUnit.UNIT;
               return;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$weightedFalsePositiveRate$1(final MulticlassMetrics $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double category = x0$1._1$mcD$sp();
         double count = x0$1._2$mcD$sp();
         return $this.falsePositiveRate(category) * count / $this.labelCount();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$weightedRecall$1(final MulticlassMetrics $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double category = x0$1._1$mcD$sp();
         double count = x0$1._2$mcD$sp();
         return $this.recall(category) * count / $this.labelCount();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$weightedPrecision$1(final MulticlassMetrics $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double category = x0$1._1$mcD$sp();
         double count = x0$1._2$mcD$sp();
         return $this.precision(category) * count / $this.labelCount();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$weightedFMeasure$1(final MulticlassMetrics $this, final double beta$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double category = x0$1._1$mcD$sp();
         double count = x0$1._2$mcD$sp();
         return $this.fMeasure(category, beta$1) * count / $this.labelCount();
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$hammingLoss$1(final DoubleRef numerator$1, final DoubleRef denominator$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var5 = (Tuple2)x0$1._1();
         double weight = x0$1._2$mcD$sp();
         if (var5 != null) {
            double label = var5._1$mcD$sp();
            double prediction = var5._2$mcD$sp();
            if (label != prediction) {
               numerator$1.elem += weight;
            }

            denominator$1.elem += weight;
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   public MulticlassMetrics(final RDD predictionAndLabels) {
      this.predictionAndLabels = predictionAndLabels;
   }

   public MulticlassMetrics(final Dataset predictionAndLabels) {
      this(predictionAndLabels.rdd().map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Tuple4 apply(final Row r) {
            int var2 = r.size();
            switch (var2) {
               case 2 -> {
                  return new Tuple4(BoxesRunTime.boxToDouble(r.getDouble(0)), BoxesRunTime.boxToDouble(r.getDouble(1)), BoxesRunTime.boxToDouble((double)1.0F), (Object)null);
               }
               case 3 -> {
                  return new Tuple4(BoxesRunTime.boxToDouble(r.getDouble(0)), BoxesRunTime.boxToDouble(r.getDouble(1)), BoxesRunTime.boxToDouble(r.getDouble(2)), (Object)null);
               }
               case 4 -> {
                  return new Tuple4(BoxesRunTime.boxToDouble(r.getDouble(0)), BoxesRunTime.boxToDouble(r.getDouble(1)), BoxesRunTime.boxToDouble(r.getDouble(2)), r.getSeq(3).toArray(scala.reflect.ClassTag..MODULE$.Double()));
               }
               default -> throw new IllegalArgumentException("Expected Row of tuples, got " + r);
            }
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
