package breeze.stats.distributions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.math3.random.MersenneTwister;
import scala.runtime.ModuleSerializationProxy;

public final class RandBasis$ implements Serializable {
   public static final RandBasis$ MODULE$ = new RandBasis$();

   public RandBasis systemSeed() {
      return new RandBasis(new ThreadLocalRandomGenerator(() -> new MersenneTwister()));
   }

   public RandBasis mt0() {
      return this.withSeed(0);
   }

   public RandBasis withSeed(final int seed) {
      AtomicInteger var2 = new AtomicInteger(seed);
      return new RandBasis(new ThreadLocalRandomGenerator(() -> new MersenneTwister(var2.getAndIncrement())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandBasis$.class);
   }

   private RandBasis$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
