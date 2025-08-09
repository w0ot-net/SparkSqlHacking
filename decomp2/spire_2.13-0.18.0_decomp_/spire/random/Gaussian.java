package spire.random;

import java.math.MathContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014qa\u0002\u0005\u0011\u0002G\u0005Q\u0002C\u0003\u0016\u0001\u0019\u0005acB\u0003;\u0011!\u00051HB\u0003\b\u0011!\u0005A\bC\u0003D\u0007\u0011\u0005A\tC\u0003\u0016\u0007\u0011\u0015Q\tC\u0003\u0016\u0007\u0011\u0005QK\u0001\u0005HCV\u001c8/[1o\u0015\tI!\"\u0001\u0004sC:$w.\u001c\u0006\u0002\u0017\u0005)1\u000f]5sK\u000e\u0001QC\u0001\b\u001e'\t\u0001q\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012CA\u0002B]f\fQ!\u00199qYf$2a\u0006\u001c9!\rA\u0012dG\u0007\u0002\u0011%\u0011!\u0004\u0003\u0002\u0005\t&\u001cH\u000f\u0005\u0002\u001d;1\u0001A!\u0003\u0010\u0001A\u0003\u0005\tQ1\u0001 \u0005\u0005\t\u0015C\u0001\u0011\u0010!\t\u0001\u0012%\u0003\u0002##\t9aj\u001c;iS:<\u0007\u0006B\u000f%OE\u0002\"\u0001E\u0013\n\u0005\u0019\n\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u0015*W)r!\u0001E\u0015\n\u0005)\n\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013-aIq!!\f\u0019\u000e\u00039R!a\f\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0012'B\u00123gU\"dB\u0001\t4\u0013\t!\u0014#\u0001\u0004E_V\u0014G.Z\u0019\u0005I1\u0002$\u0003C\u00038\u0003\u0001\u00071$\u0001\u0003nK\u0006t\u0007\"B\u001d\u0002\u0001\u0004Y\u0012AB:uI\u0012+g/\u0001\u0005HCV\u001c8/[1o!\tA2aE\u0002\u0004{\u0001\u0003\"\u0001\u0005 \n\u0005}\n\"AB!osJ+g\r\u0005\u0002\u0019\u0003&\u0011!\t\u0003\u0002\u0012\u000f\u0006,8o]5b]&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001<+\t1\u0015\n\u0006\u0002H\u001fB\u0019\u0001\u0004\u0001%\u0011\u0005qIE!\u0003\u0010\u0006A\u0003\u0005\tQ1\u0001 Q\u0011IEeS'2\u000b\rB\u0013\u0006\u0014\u00162\t\u0011b\u0003GE\u0019\u0006GI\u001ad\nN\u0019\u0005I1\u0002$\u0003C\u0003Q\u000b\u0001\u000fq)A\u0001hQ\t)!\u000b\u0005\u0002\u0011'&\u0011A+\u0005\u0002\u0007S:d\u0017N\\3\u0016\u0005YSFcA,_?R\u0011\u0001\f\u0018\t\u00041eI\u0006C\u0001\u000f[\t%qb\u0001)A\u0001\u0002\u000b\u0007q\u0004\u000b\u0002[I!)\u0001K\u0002a\u0002;B\u0019\u0001\u0004A-\t\u000b]2\u0001\u0019A-\t\u000be2\u0001\u0019A-"
)
public interface Gaussian {
   static MathContext bigDecimal$default$1() {
      return Gaussian$.MODULE$.bigDecimal$default$1();
   }

   static Gaussian bigDecimal(final MathContext mc) {
      return Gaussian$.MODULE$.bigDecimal(mc);
   }

   static Gaussian double() {
      return Gaussian$.MODULE$.double();
   }

   static Gaussian float() {
      return Gaussian$.MODULE$.float();
   }

   Dist apply(final Object mean, final Object stdDev);

   // $FF: synthetic method
   static Dist apply$mcD$sp$(final Gaussian $this, final double mean, final double stdDev) {
      return $this.apply$mcD$sp(mean, stdDev);
   }

   default Dist apply$mcD$sp(final double mean, final double stdDev) {
      return this.apply(BoxesRunTime.boxToDouble(mean), BoxesRunTime.boxToDouble(stdDev));
   }

   // $FF: synthetic method
   static Dist apply$mcF$sp$(final Gaussian $this, final float mean, final float stdDev) {
      return $this.apply$mcF$sp(mean, stdDev);
   }

   default Dist apply$mcF$sp(final float mean, final float stdDev) {
      return this.apply(BoxesRunTime.boxToFloat(mean), BoxesRunTime.boxToFloat(stdDev));
   }
}
