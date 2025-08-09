package org.apache.spark.mllib.tree.impurity;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3Q!\u0004\b\u0001)iA\u0011b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0016\t\u0011-\u0002!\u00111A\u0005\u00021B\u0001\u0002\r\u0001\u0003\u0002\u0004%\t!\r\u0005\to\u0001\u0011\t\u0011)Q\u0005[!)\u0001\b\u0001C\u0001s!)Q\b\u0001C\u0001}!)q\b\u0001C\u0001\u0001\")\u0011\t\u0001C\u0001\u0005\")1\t\u0001C\u0001\u0005\")A\t\u0001C!\u000b\")\u0001\n\u0001C!\u0013\"YQ\u000b\u0001I\u0001\u0004\u0003\u0005I\u0011\u0002,+\u000599\u0015N\\5DC2\u001cW\u000f\\1u_JT!a\u0004\t\u0002\u0011%l\u0007/\u001e:jifT!!\u0005\n\u0002\tQ\u0014X-\u001a\u0006\u0003'Q\tQ!\u001c7mS\nT!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\n\u0003\u0001m\u0001\"\u0001H\u000f\u000e\u00039I!A\b\b\u0003%%k\u0007/\u001e:jif\u001c\u0015\r\\2vY\u0006$xN]\u0001\u0006gR\fGo]\u0002\u0001!\r\u0011SeJ\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t)\u0011I\u001d:bsB\u0011!\u0005K\u0005\u0003S\r\u0012a\u0001R8vE2,\u0017BA\u0010\u001e\u0003!\u0011\u0018m^\"pk:$X#A\u0017\u0011\u0005\tr\u0013BA\u0018$\u0005\u0011auN\\4\u0002\u0019I\fwoQ8v]R|F%Z9\u0015\u0005I*\u0004C\u0001\u00124\u0013\t!4E\u0001\u0003V]&$\bb\u0002\u001c\u0004\u0003\u0003\u0005\r!L\u0001\u0004q\u0012\n\u0014!\u0003:bo\u000e{WO\u001c;!\u0003\u0019a\u0014N\\5u}Q\u0019!h\u000f\u001f\u0011\u0005q\u0001\u0001\"B\u0010\u0006\u0001\u0004\t\u0003\"B\u0016\u0006\u0001\u0004i\u0013\u0001B2paf,\u0012AO\u0001\nG\u0006d7-\u001e7bi\u0016$\u0012aJ\u0001\u0006G>,h\u000e^\u000b\u0002O\u00059\u0001O]3eS\u000e$\u0018\u0001\u00029s_\n$\"a\n$\t\u000b\u001dS\u0001\u0019A\u0014\u0002\u000b1\f'-\u001a7\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u0013\t\u0003\u0017Js!\u0001\u0014)\u0011\u00055\u001bS\"\u0001(\u000b\u0005=\u0003\u0013A\u0002\u001fs_>$h(\u0003\u0002RG\u00051\u0001K]3eK\u001aL!a\u0015+\u0003\rM#(/\u001b8h\u0015\t\t6%A\u0006tkB,'\u000fJ:uCR\u001cX#A\u0011"
)
public class GiniCalculator extends ImpurityCalculator {
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

   public GiniCalculator copy() {
      return new GiniCalculator((double[])super.stats().clone(), this.rawCount());
   }

   public double calculate() {
      return Gini$.MODULE$.calculate(super.stats(), BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(super.stats()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)));
   }

   public double count() {
      return BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(super.stats()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   public double predict() {
      return this.count() == (double)0 ? (double)0.0F : (double)this.indexOfLargestArrayElement(super.stats());
   }

   public double prob(final double label) {
      int lbl = (int)label;
      .MODULE$.require(lbl < super.stats().length, () -> "GiniCalculator.prob given invalid label: " + lbl + " (should be < " + this.super$stats().length);
      .MODULE$.require(lbl >= 0, () -> "GiniImpurity does not support negative labels");
      double cnt = this.count();
      return cnt == (double)0 ? (double)0.0F : super.stats()[lbl] / cnt;
   }

   public String toString() {
      ArraySeq.ofDouble var10000 = .MODULE$.wrapDoubleArray(super.stats());
      return "GiniCalculator(stats = [" + var10000.mkString(", ") + "])";
   }

   public GiniCalculator(final double[] stats, final long rawCount) {
      this.rawCount = rawCount;
      super(stats);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
