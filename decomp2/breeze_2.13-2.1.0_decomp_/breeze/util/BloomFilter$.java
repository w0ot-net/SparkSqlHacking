package breeze.util;

import java.io.Serializable;
import scala.MatchError;
import scala.Tuple2;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class BloomFilter$ implements Serializable {
   public static final BloomFilter$ MODULE$ = new BloomFilter$();

   public Tuple2 optimalSize(final double expectedNumItems, final double falsePositiveRate) {
      double m = .MODULE$.ceil(-(expectedNumItems * .MODULE$.log(falsePositiveRate)) / .MODULE$.log(.MODULE$.pow((double)2.0F, .MODULE$.log((double)2.0F))));
      long k = .MODULE$.round(.MODULE$.log((double)2.0F) * m / expectedNumItems);
      return new Tuple2.mcII.sp((int)m, (int)k);
   }

   public BloomFilter optimallySized(final double expectedNumItems, final double falsePositiveRate) {
      Tuple2 var7 = this.optimalSize(expectedNumItems, falsePositiveRate);
      if (var7 != null) {
         int buckets = var7._1$mcI$sp();
         int funs = var7._2$mcI$sp();
         Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(buckets, funs);
         int buckets = ((Tuple2)var5)._1$mcI$sp();
         int funs = ((Tuple2)var5)._2$mcI$sp();
         return new BloomFilter(buckets, funs);
      } else {
         throw new MatchError(var7);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BloomFilter$.class);
   }

   private BloomFilter$() {
   }
}
