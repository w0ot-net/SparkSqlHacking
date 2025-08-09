package org.apache.spark.mllib.tree.impurity;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3Q!\u0004\b\u0001)iA\u0011b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0016\t\u0011-\u0002!\u00111A\u0005\u00021B\u0001\u0002\r\u0001\u0003\u0002\u0004%\t!\r\u0005\to\u0001\u0011\t\u0011)Q\u0005[!)\u0001\b\u0001C\u0001s!)Q\b\u0001C\u0001}!)q\b\u0001C\u0001\u0001\")\u0011\t\u0001C\u0001\u0005\")1\t\u0001C\u0001\u0005\")A\t\u0001C!\u000b\")\u0001\n\u0001C!\u0013\"YQ\u000b\u0001I\u0001\u0004\u0003\u0005I\u0011\u0002,+\u0005E)e\u000e\u001e:paf\u001c\u0015\r\\2vY\u0006$xN\u001d\u0006\u0003\u001fA\t\u0001\"[7qkJLG/\u001f\u0006\u0003#I\tA\u0001\u001e:fK*\u00111\u0003F\u0001\u0006[2d\u0017N\u0019\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sON\u0011\u0001a\u0007\t\u00039ui\u0011AD\u0005\u0003=9\u0011!#S7qkJLG/_\"bY\u000e,H.\u0019;pe\u0006)1\u000f^1ug\u000e\u0001\u0001c\u0001\u0012&O5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13EA\u0003BeJ\f\u0017\u0010\u0005\u0002#Q%\u0011\u0011f\t\u0002\u0007\t>,(\r\\3\n\u0005}i\u0012\u0001\u0003:bo\u000e{WO\u001c;\u0016\u00035\u0002\"A\t\u0018\n\u0005=\u001a#\u0001\u0002'p]\u001e\fAB]1x\u0007>,h\u000e^0%KF$\"AM\u001b\u0011\u0005\t\u001a\u0014B\u0001\u001b$\u0005\u0011)f.\u001b;\t\u000fY\u001a\u0011\u0011!a\u0001[\u0005\u0019\u0001\u0010J\u0019\u0002\u0013I\fwoQ8v]R\u0004\u0013A\u0002\u001fj]&$h\bF\u0002;wq\u0002\"\u0001\b\u0001\t\u000b})\u0001\u0019A\u0011\t\u000b-*\u0001\u0019A\u0017\u0002\t\r|\u0007/_\u000b\u0002u\u0005I1-\u00197dk2\fG/\u001a\u000b\u0002O\u0005)1m\\;oiV\tq%A\u0004qe\u0016$\u0017n\u0019;\u0002\tA\u0014xN\u0019\u000b\u0003O\u0019CQa\u0012\u0006A\u0002\u001d\nQ\u0001\\1cK2\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0015B\u00111J\u0015\b\u0003\u0019B\u0003\"!T\u0012\u000e\u00039S!a\u0014\u0011\u0002\rq\u0012xn\u001c;?\u0013\t\t6%\u0001\u0004Qe\u0016$WMZ\u0005\u0003'R\u0013aa\u0015;sS:<'BA)$\u0003-\u0019X\u000f]3sIM$\u0018\r^:\u0016\u0003\u0005\u0002"
)
public class EntropyCalculator extends ImpurityCalculator {
   private long rawCount;

   // $FF: synthetic method
   private double[] super$stats() {
      return super.stats();
   }

   public long rawCount() {
      return this.rawCount;
   }

   public void rawCount_$eq(final long x$1) {
      this.rawCount = x$1;
   }

   public EntropyCalculator copy() {
      return new EntropyCalculator((double[])super.stats().clone(), this.rawCount());
   }

   public double calculate() {
      return Entropy$.MODULE$.calculate(super.stats(), BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(super.stats()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)));
   }

   public double count() {
      return BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(super.stats()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public double predict() {
      return this.count() == (double)0 ? (double)0.0F : (double)this.indexOfLargestArrayElement(super.stats());
   }

   public double prob(final double label) {
      int lbl = (int)label;
      .MODULE$.require(lbl < super.stats().length, () -> "EntropyCalculator.prob given invalid label: " + lbl + " (should be < " + this.super$stats().length);
      .MODULE$.require(lbl >= 0, () -> "Entropy does not support negative labels");
      double cnt = this.count();
      return cnt == (double)0 ? (double)0.0F : super.stats()[lbl] / cnt;
   }

   public String toString() {
      ArraySeq.ofDouble var10000 = .MODULE$.wrapDoubleArray(super.stats());
      return "EntropyCalculator(stats = [" + var10000.mkString(", ") + "])";
   }

   public EntropyCalculator(final double[] stats, final long rawCount) {
      this.rawCount = rawCount;
      super(stats);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
