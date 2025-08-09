package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.math.package.;
import scala.util.Random;

public final class FlatHashTable$ {
   public static final FlatHashTable$ MODULE$ = new FlatHashTable$();

   public final ThreadLocal seedGenerator() {
      return new ThreadLocal() {
         public Random initialValue() {
            return new Random();
         }
      };
   }

   public int defaultLoadFactor() {
      return 450;
   }

   public final int loadFactorDenum() {
      return 1000;
   }

   public int sizeForThreshold(final int size, final int _loadFactor) {
      return .MODULE$.max(32, (int)((long)size * (long)this.loadFactorDenum() / (long)_loadFactor));
   }

   public int newThreshold(final int _loadFactor, final int size) {
      scala.Predef..MODULE$.assert(_loadFactor < this.loadFactorDenum() / 2, () -> "loadFactor too large; must be < 0.5");
      return (int)((long)size * (long)_loadFactor / (long)this.loadFactorDenum());
   }

   private FlatHashTable$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
