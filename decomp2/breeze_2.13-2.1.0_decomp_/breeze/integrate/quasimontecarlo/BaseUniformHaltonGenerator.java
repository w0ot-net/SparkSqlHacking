package breeze.integrate.quasimontecarlo;

import breeze.linalg.shuffle$;
import breeze.stats.distributions.RandBasis;
import breeze.stats.distributions.RandBasis$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001B\u0013'\u00015B\u0001\u0002\u000f\u0001\u0003\u0006\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0011)A\u0005u!)a\b\u0001C\u0001\u007f!9!\t\u0001b\u0001\n\u0013\u0019\u0005BB$\u0001A\u0003%A\tC\u0004I\u0001\u0001\u0007I\u0011B%\t\u000f5\u0003\u0001\u0019!C\u0005\u001d\"1A\u000b\u0001Q!\n)Cq!\u0016\u0001C\u0002\u0013%a\u000b\u0003\u0004x\u0001\u0001\u0006Ia\u0016\u0005\bq\u0002\u0011\r\u0011\"\u0001z\u0011\u0019a\b\u0001)A\u0005u\"9Q\u0010\u0001b\u0001\n\u0013q\bbBA\u0004\u0001\u0001\u0006Ia \u0005\t\u0003\u0013\u0001\u0001\u0019!C\u0005\u0013\"I\u00111\u0002\u0001A\u0002\u0013%\u0011Q\u0002\u0005\b\u0003#\u0001\u0001\u0015)\u0003K\u0011\u0019\t\u0019\u0002\u0001C\u0001\u0013\"1\u0011Q\u0003\u0001\u0005\u0002y4AA\u0017\u0001\u00057\"AA\f\u0006B\u0001B\u0003%!\bC\u0003?)\u0011\u0005Q\fC\u0004`)\u0001\u0007I\u0011B\"\t\u000f\u0001$\u0002\u0019!C\u0005C\"11\r\u0006Q!\n\u0011Cq\u0001\u001a\u000bA\u0002\u0013%\u0011\bC\u0004f)\u0001\u0007I\u0011\u00024\t\r!$\u0002\u0015)\u0003;\u0011\u0015IG\u0003\"\u0001k\u0011\u0015iG\u0003\"\u0001o\u0011\u0015yG\u0003\"\u0001q\u0011\u0015\u0019H\u0003\"\u0001u\u000f%\t9\u0002AA\u0001\u0012\u0013\tIB\u0002\u0005[\u0001\u0005\u0005\t\u0012BA\u000e\u0011\u0019q$\u0005\"\u0001\u0002\u001e!I\u0011q\u0004\u0012\u0012\u0002\u0013\u0005\u0011\u0011\u0005\u0002\u001b\u0005\u0006\u001cX-\u00168jM>\u0014X\u000eS1mi>tw)\u001a8fe\u0006$xN\u001d\u0006\u0003O!\nq\"];bg&lwN\u001c;fG\u0006\u0014Hn\u001c\u0006\u0003S)\n\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0003-\naA\u0019:fKj,7\u0001A\n\u0004\u00019\"\u0004CA\u00183\u001b\u0005\u0001$\"A\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0002$AB!osJ+g\r\u0005\u00026m5\ta%\u0003\u00028M\tA\u0012+^1tS6{g\u000e^3DCJdwnR3oKJ\fGo\u001c:\u0002\u0013\u0011LW.\u001a8tS>tW#\u0001\u001e\u0011\u0005=Z\u0014B\u0001\u001f1\u0005\rIe\u000e^\u0001\u000bI&lWM\\:j_:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002A\u0003B\u0011Q\u0007\u0001\u0005\u0006q\r\u0001\rAO\u0001\u0006E\u0006\u001cXm]\u000b\u0002\tB\u0019q&\u0012\u001e\n\u0005\u0019\u0003$!B!se\u0006L\u0018A\u00022bg\u0016\u001c\b%A\u0003d_VtG/F\u0001K!\ty3*\u0003\u0002Ma\t!Aj\u001c8h\u0003%\u0019w.\u001e8u?\u0012*\u0017\u000f\u0006\u0002P%B\u0011q\u0006U\u0005\u0003#B\u0012A!\u00168ji\"91kBA\u0001\u0002\u0004Q\u0015a\u0001=%c\u000511m\\;oi\u0002\n\u0001bY8v]R,'o]\u000b\u0002/B\u0019q&\u0012-\u0011\u0005e#R\"\u0001\u0001\u0003!Us'm\u001c=fI&sGOV3di>\u00148C\u0001\u000b/\u0003-Ig.\u001b;jC2\u001c\u0016N_3\u0015\u0005as\u0006b\u0002/\u0017!\u0003\u0005\rAO\u0001\bgR|'/Y4f\u0003-\u0019Ho\u001c:bO\u0016|F%Z9\u0015\u0005=\u0013\u0007bB*\u0019\u0003\u0003\u0005\r\u0001R\u0001\tgR|'/Y4fA\u0005Q\u0011m\u0019;vC2\u001c\u0016N_3\u0002\u001d\u0005\u001cG/^1m'&TXm\u0018\u0013fcR\u0011qj\u001a\u0005\b'n\t\t\u00111\u0001;\u0003-\t7\r^;bYNK'0\u001a\u0011\u0002\u0007\u0005$G\r\u0006\u0002PW\")A.\ba\u0001u\u0005\t\u00010\u0001\u0003tSj,G#\u0001\u001e\u0002\u0007\u001d,G\u000f\u0006\u0002;c\")!o\ba\u0001u\u0005\t\u0011.A\u0002tKR$2aT;w\u0011\u0015\u0011\b\u00051\u0001;\u0011\u0015a\u0007\u00051\u0001;\u0003%\u0019w.\u001e8uKJ\u001c\b%\u0001\u0007qKJlW\u000f^1uS>t7/F\u0001{!\rySi\u001f\t\u0004_\u0015S\u0015!\u00049fe6,H/\u0019;j_:\u001c\b%\u0001\u0007dkJ\u0014XM\u001c;WC2,X-F\u0001\u0000!\u0011yS)!\u0001\u0011\u0007=\n\u0019!C\u0002\u0002\u0006A\u0012a\u0001R8vE2,\u0017!D2veJ,g\u000e\u001e,bYV,\u0007%\u0001\bhK:,'/\u0019;fI\u000e{WO\u001c;\u0002%\u001d,g.\u001a:bi\u0016$7i\\;oi~#S-\u001d\u000b\u0004\u001f\u0006=\u0001bB*\u0011\u0003\u0003\u0005\rAS\u0001\u0010O\u0016tWM]1uK\u0012\u001cu.\u001e8uA\u0005aa.^7HK:,'/\u0019;fI\u0006iq-\u001a;OKb$XK\\:bM\u0016\f\u0001#\u00168c_b,G-\u00138u-\u0016\u001cGo\u001c:\u0011\u0005e\u00133C\u0001\u0012/)\t\tI\"A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u000b\u0003\u0003GQ3AOA\u0013W\t\t9\u0003\u0005\u0003\u0002*\u0005MRBAA\u0016\u0015\u0011\ti#a\f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0019a\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005U\u00121\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class BaseUniformHaltonGenerator implements QuasiMonteCarloGenerator {
   private volatile UnboxedIntVector$ UnboxedIntVector$module;
   private final int dimension;
   private final int[] bases;
   private long count;
   private final UnboxedIntVector[] counters;
   private final long[][] permutations;
   private final double[] currentValue;
   private long generatedCount;

   public double[] getNext() {
      return QuasiMonteCarloGenerator.getNext$(this);
   }

   public void getNextInto(final double[] to) {
      QuasiMonteCarloGenerator.getNextInto$(this, to);
   }

   private UnboxedIntVector$ UnboxedIntVector() {
      if (this.UnboxedIntVector$module == null) {
         this.UnboxedIntVector$lzycompute$1();
      }

      return this.UnboxedIntVector$module;
   }

   public int dimension() {
      return this.dimension;
   }

   private int[] bases() {
      return this.bases;
   }

   private long count() {
      return this.count;
   }

   private void count_$eq(final long x$1) {
      this.count = x$1;
   }

   private UnboxedIntVector[] counters() {
      return this.counters;
   }

   public long[][] permutations() {
      return this.permutations;
   }

   private double[] currentValue() {
      return this.currentValue;
   }

   private long generatedCount() {
      return this.generatedCount;
   }

   private void generatedCount_$eq(final long x$1) {
      this.generatedCount = x$1;
   }

   public long numGenerated() {
      return this.generatedCount();
   }

   public double[] getNextUnsafe() {
      int index$macro$7 = 0;

      for(int limit$macro$9 = this.dimension(); index$macro$7 < limit$macro$9; ++index$macro$7) {
         int lIndex;
         for(lIndex = 0; lIndex < this.counters()[index$macro$7].size() && this.counters()[index$macro$7].get(lIndex) == this.bases()[index$macro$7] - 1; ++lIndex) {
            this.counters()[index$macro$7].set(lIndex, 0);
         }

         if (lIndex == this.counters()[index$macro$7].size()) {
            this.counters()[index$macro$7].add(1);
         } else {
            this.counters()[index$macro$7].set(lIndex, this.counters()[index$macro$7].get(lIndex) + 1);
         }

         int lCountSizeI = this.counters()[index$macro$7].size();
         long lBasesPow = (long)this.bases()[index$macro$7];
         double lValue = (double)this.permutations()[index$macro$7][this.counters()[index$macro$7].get(lCountSizeI - 1)];
         int index$macro$2 = lCountSizeI;

         for(int limit$macro$4 = 0; index$macro$2 > limit$macro$4; --index$macro$2) {
            lValue += (double)(this.permutations()[index$macro$7][this.counters()[index$macro$7].get(index$macro$2 - 1)] * lBasesPow);
            lBasesPow *= (long)this.bases()[index$macro$7];
         }

         this.currentValue()[index$macro$7] = lValue / (double)lBasesPow;
      }

      this.generatedCount_$eq(this.generatedCount() + 1L);
      return this.currentValue();
   }

   private final void UnboxedIntVector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnboxedIntVector$module == null) {
            this.UnboxedIntVector$module = new UnboxedIntVector$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final long[] $anonfun$permutations$1(final RandBasis rand$1, final int i) {
      long[] vv = new long[Halton$.MODULE$.PRIMES()[i]];
      int index$macro$2 = 0;

      for(int limit$macro$4 = Halton$.MODULE$.PRIMES()[i]; index$macro$2 < limit$macro$4; ++index$macro$2) {
         vv[index$macro$2] = (long)index$macro$2;
      }

      shuffle$.MODULE$.apply(vv, shuffle$.MODULE$.implShuffle_Arr_eq_Arr(.MODULE$.Long(), rand$1));
      return vv;
   }

   public BaseUniformHaltonGenerator(final int dimension) {
      this.dimension = dimension;
      QuasiMonteCarloGenerator.$init$(this);
      this.bases = Arrays.copyOfRange(Halton$.MODULE$.PRIMES(), 0, dimension);
      this.count = 0L;
      this.counters = (UnboxedIntVector[])scala.package..MODULE$.List().fill(dimension, () -> this.new UnboxedIntVector(16)).toArray(.MODULE$.apply(UnboxedIntVector.class));
      RandBasis rand = RandBasis$.MODULE$.mt0();
      this.permutations = (long[][])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), dimension).map((i) -> $anonfun$permutations$1(rand, BoxesRunTime.unboxToInt(i))).toArray(.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)));
      this.currentValue = new double[dimension];
      this.generatedCount = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class UnboxedIntVector$ {
      public int $lessinit$greater$default$1() {
         return 256;
      }

      public UnboxedIntVector$() {
      }
   }

   private class UnboxedIntVector {
      private int[] storage;
      private int actualSize;
      // $FF: synthetic field
      public final BaseUniformHaltonGenerator $outer;

      private int[] storage() {
         return this.storage;
      }

      private void storage_$eq(final int[] x$1) {
         this.storage = x$1;
      }

      private int actualSize() {
         return this.actualSize;
      }

      private void actualSize_$eq(final int x$1) {
         this.actualSize = x$1;
      }

      public void add(final int x) {
         if (this.actualSize() == scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.intArrayOps(this.storage()))) {
            int[] oldStorage = this.storage();
            this.storage_$eq(new int[scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.intArrayOps(oldStorage)) * 2]);
         }

         this.storage()[this.actualSize()] = x;
         this.actualSize_$eq(this.actualSize() + 1);
      }

      public int size() {
         return this.actualSize();
      }

      public int get(final int i) {
         return this.storage()[i];
      }

      public void set(final int i, final int x) {
         this.storage()[i] = x;
      }

      // $FF: synthetic method
      public BaseUniformHaltonGenerator breeze$integrate$quasimontecarlo$BaseUniformHaltonGenerator$UnboxedIntVector$$$outer() {
         return this.$outer;
      }

      public UnboxedIntVector(final int initialSize) {
         if (BaseUniformHaltonGenerator.this == null) {
            throw null;
         } else {
            this.$outer = BaseUniformHaltonGenerator.this;
            super();
            this.storage = new int[initialSize];
            this.actualSize = 0;
         }
      }
   }
}
