package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Objects;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.FeatureType$;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005m3Aa\u0003\u0007\u0001/!A!\u0005\u0001BC\u0002\u0013\u00053\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003%\u0011!A\u0003A!b\u0001\n\u0003I\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\r9\u0002A\u0011\u0001\b0\u0011\u0019\u0019\u0004\u0001\"\u0011\u000fi!11\u0007\u0001C!\u001d\u0001CQ\u0001\u0013\u0001\u0005B%CQa\u0014\u0001\u0005BACa!\u0015\u0001\u0005B1\u0011&aD\"p]RLg.^8vgN\u0003H.\u001b;\u000b\u00055q\u0011\u0001\u0002;sK\u0016T!a\u0004\t\u0002\u00055d'BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ty\u0002%D\u0001\r\u0013\t\tCBA\u0003Ta2LG/\u0001\u0007gK\u0006$XO]3J]\u0012,\u00070F\u0001%!\tIR%\u0003\u0002'5\t\u0019\u0011J\u001c;\u0002\u001b\u0019,\u0017\r^;sK&sG-\u001a=!\u0003%!\bN]3tQ>dG-F\u0001+!\tI2&\u0003\u0002-5\t1Ai\\;cY\u0016\f!\u0002\u001e5sKNDw\u000e\u001c3!\u0003\u0019a\u0014N\\5u}Q\u0019\u0001'\r\u001a\u0011\u0005}\u0001\u0001\"\u0002\u0012\u0006\u0001\u0004!\u0003\"\u0002\u0015\u0006\u0001\u0004Q\u0013\u0001D:i_VdGmR8MK\u001a$HCA\u001b9!\tIb'\u0003\u000285\t9!i\\8mK\u0006t\u0007\"B\u001d\u0007\u0001\u0004Q\u0014\u0001\u00034fCR,(/Z:\u0011\u0005mrT\"\u0001\u001f\u000b\u0005ur\u0011A\u00027j]\u0006dw-\u0003\u0002@y\t1a+Z2u_J$2!N!D\u0011\u0015\u0011u\u00011\u0001%\u00035\u0011\u0017N\u001c8fI\u001a+\u0017\r^;sK\")Ai\u0002a\u0001\u000b\u000611\u000f\u001d7jiN\u00042!\u0007$\u001f\u0013\t9%DA\u0003BeJ\f\u00170\u0001\u0004fcV\fGn\u001d\u000b\u0003k)CQa\u0013\u0005A\u00021\u000b\u0011a\u001c\t\u000335K!A\u0014\u000e\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f)\u0005!\u0013!\u0002;p\u001f2$W#A*\u0011\u0005QSV\"A+\u000b\u0005Y;\u0016!B7pI\u0016d'BA\u0007Y\u0015\tI\u0006#A\u0003nY2L'-\u0003\u0002\"+\u0002"
)
public class ContinuousSplit implements Split {
   private final int featureIndex;
   private final double threshold;

   public int featureIndex() {
      return this.featureIndex;
   }

   public double threshold() {
      return this.threshold;
   }

   public boolean shouldGoLeft(final Vector features) {
      return features.apply(this.featureIndex()) <= this.threshold();
   }

   public boolean shouldGoLeft(final int binnedFeature, final Split[] splits) {
      if (binnedFeature == splits.length) {
         return false;
      } else {
         double featureValueUpperBound = ((ContinuousSplit)splits[binnedFeature]).threshold();
         return featureValueUpperBound <= this.threshold();
      }
   }

   public boolean equals(final Object o) {
      if (!(o instanceof ContinuousSplit var4)) {
         return false;
      } else {
         return this.featureIndex() == var4.featureIndex() && this.threshold() == var4.threshold();
      }
   }

   public int hashCode() {
      Seq state = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{(double)this.featureIndex(), this.threshold()}));
      return BoxesRunTime.unboxToInt(((IterableOnceOps)state.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$hashCode$3(x$1)))).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(a, b) -> 31 * a + b));
   }

   public org.apache.spark.mllib.tree.model.Split toOld() {
      return new org.apache.spark.mllib.tree.model.Split(this.featureIndex(), this.threshold(), FeatureType$.MODULE$.Continuous(), .MODULE$.List().empty());
   }

   // $FF: synthetic method
   public static final int $anonfun$hashCode$3(final Object x$1) {
      return Objects.hashCode(x$1);
   }

   public ContinuousSplit(final int featureIndex, final double threshold) {
      this.featureIndex = featureIndex;
      this.threshold = threshold;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
