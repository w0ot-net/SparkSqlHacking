package breeze.integrate.quasimontecarlo;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.Iterator;
import scala.io.Source.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.RichLong;
import scala.runtime.java8.JFunction1;

public final class Halton$ {
   public static final Halton$ MODULE$ = new Halton$();
   private static int[] PRIMES;
   private static int[] EA_PERMS;
   private static final int HALTON_MAX_DIMENSION = 1229;
   private static volatile byte bitmap$0;

   public int HALTON_MAX_DIMENSION() {
      return HALTON_MAX_DIMENSION;
   }

   private int[] readClasspathFileToIntArray(final String filename) {
      InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream(filename);
      Iterator lines = .MODULE$.fromInputStream(fileStream, scala.io.Codec..MODULE$.fallbackSystemCodec()).getLines();
      Iterator nums = lines.flatMap((x) -> scala.Predef..MODULE$.wrapRefArray((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(x), ','))).map((x) -> x.replaceAll("\\s+", ""));
      return (int[])nums.map((x) -> BoxesRunTime.boxToInteger($anonfun$readClasspathFileToIntArray$3(x))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   private int[] PRIMES$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            PRIMES = this.readClasspathFileToIntArray("primes.txt");
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return PRIMES;
   }

   public int[] PRIMES() {
      return (byte)(bitmap$0 & 1) == 0 ? this.PRIMES$lzycompute() : PRIMES;
   }

   private int[] EA_PERMS$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            EA_PERMS = this.readClasspathFileToIntArray("quasimontecarlo_halton_ea_perms.txt");
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return EA_PERMS;
   }

   public int[] EA_PERMS() {
      return (byte)(bitmap$0 & 2) == 0 ? this.EA_PERMS$lzycompute() : EA_PERMS;
   }

   public double integrate(final Function1 func, final int dimension, final long numSamples) {
      BaseUniformHaltonGenerator gen = new BaseUniformHaltonGenerator(dimension);
      DoubleRef result = DoubleRef.create((double)0.0F);
      (new RichLong(scala.Predef..MODULE$.longWrapper(0L))).until(BoxesRunTime.boxToLong(numSamples)).foreach$mVc$sp((JFunction1.mcVJ.sp)(x$1) -> {
         result.elem += BoxesRunTime.unboxToDouble(func.apply(gen.getNextUnsafe()));
         BoxedUnit var5 = BoxedUnit.UNIT;
      });
      return result.elem / (double)numSamples;
   }

   // $FF: synthetic method
   public static final int $anonfun$readClasspathFileToIntArray$3(final String x) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x));
   }

   private Halton$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
