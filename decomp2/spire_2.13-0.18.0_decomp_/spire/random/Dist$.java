package spire.random;

import algebra.ring.AdditiveMonoid;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeRng;
import algebra.ring.CommutativeSemiring;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.Rig;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Fractional;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import scala.util.Either;
import spire.algebra.CModule;
import spire.algebra.InnerProductSpace;
import spire.algebra.IsReal;
import spire.algebra.NormedVectorSpace;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.algebra.package$;
import spire.math.Complex;
import spire.math.Interval$;
import spire.math.Natural;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.SafeLong$;
import spire.math.UByte;
import spire.math.UByte$;
import spire.math.UInt;
import spire.math.UInt$;
import spire.math.ULong;
import spire.math.ULong$;
import spire.math.UShort;
import spire.math.UShort$;

public final class Dist$ implements DistInstances9 {
   public static final Dist$ MODULE$ = new Dist$();
   private static final Dist unit;
   private static final Dist boolean;
   private static final Dist byte;
   private static final Dist short;
   private static final Dist char;
   private static final Dist int;
   private static final Dist float;
   private static final Dist long;
   private static final Dist double;
   private static final Dist ubyte;
   private static final Dist ushort;
   private static final Dist uint;
   private static final Dist ulong;

   static {
      DistInstances0.$init$(MODULE$);
      DistInstances1.$init$(MODULE$);
      DistInstances2.$init$(MODULE$);
      DistInstances3.$init$(MODULE$);
      DistInstances4.$init$(MODULE$);
      DistInstances5.$init$(MODULE$);
      DistInstances6.$init$(MODULE$);
      DistInstances7.$init$(MODULE$);
      DistInstances8.$init$(MODULE$);
      DistInstances9.$init$(MODULE$);
      unit = new DistFromGen$mcV$sp((g) -> {
         $anonfun$unit$1(g);
         return BoxedUnit.UNIT;
      });
      boolean = new DistFromGen$mcZ$sp((x$2) -> BoxesRunTime.boxToBoolean($anonfun$boolean$1(x$2)));
      byte = new DistFromGen$mcB$sp((x$3) -> BoxesRunTime.boxToByte($anonfun$byte$1(x$3)));
      short = new DistFromGen$mcS$sp((x$4) -> BoxesRunTime.boxToShort($anonfun$short$1(x$4)));
      char = new DistFromGen$mcC$sp((x$5) -> BoxesRunTime.boxToCharacter($anonfun$char$1(x$5)));
      int = new DistFromGen$mcI$sp((x$6) -> BoxesRunTime.boxToInteger($anonfun$int$1(x$6)));
      float = new DistFromGen$mcF$sp((x$7) -> BoxesRunTime.boxToFloat($anonfun$float$1(x$7)));
      long = new DistFromGen$mcJ$sp((x$8) -> BoxesRunTime.boxToLong($anonfun$long$1(x$8)));
      double = new DistFromGen$mcD$sp((x$9) -> BoxesRunTime.boxToDouble($anonfun$double$1(x$9)));
      ubyte = new DistFromGen((g) -> new UByte($anonfun$ubyte$1(g)));
      ushort = new DistFromGen((g) -> new UShort($anonfun$ushort$1(g)));
      uint = new DistFromGen((g) -> new UInt($anonfun$uint$1(g)));
      ulong = new DistFromGen((g) -> new ULong($anonfun$ulong$1(g)));
   }

   public InnerProductSpace InnerProductSpace(final Eq ev1, final InnerProductSpace ev2) {
      return DistInstances9.InnerProductSpace$(this, ev1, ev2);
   }

   public NormedVectorSpace NormedVectorSpace(final Eq ev1, final NormedVectorSpace ev2) {
      return DistInstances8.NormedVectorSpace$(this, ev1, ev2);
   }

   public VectorSpace vectorSpace(final Eq ev1, final VectorSpace ev2) {
      return DistInstances7.vectorSpace$(this, ev1, ev2);
   }

   public CModule module(final CModule ev2) {
      return DistInstances6.module$(this, ev2);
   }

   public Field field(final Eq ev1, final Field ev2) {
      return DistInstances5.field$(this, ev1, ev2);
   }

   public EuclideanRing euclideanRing(final Eq ev1, final EuclideanRing ev2) {
      return DistInstances4.euclideanRing$(this, ev1, ev2);
   }

   public GCDRing gcdRing(final Eq ev1, final GCDRing ev2) {
      return DistInstances3.gcdRing$(this, ev1, ev2);
   }

   public CommutativeRing cRing(final CommutativeRing ev) {
      return DistInstances2.cRing$(this, ev);
   }

   public CommutativeRig rig(final CommutativeRig ev) {
      return DistInstances1.rig$(this, ev);
   }

   public CommutativeRng rng(final CommutativeRng ev) {
      return DistInstances1.rng$(this, ev);
   }

   public CommutativeSemiring cSemiring(final CommutativeSemiring ev) {
      return DistInstances0.cSemiring$(this, ev);
   }

   public final Dist apply(final Dist na) {
      return na;
   }

   public final Dist apply(final Function1 f, final Dist na) {
      return na.map(f);
   }

   public final Dist apply(final Function2 f, final Dist na, final Dist nb) {
      return na.zipWith(nb, f);
   }

   public final Dist gen(final Function1 f) {
      return new DistFromGen((g) -> f.apply(g));
   }

   public Dist uniform(final Object low, final Object high, final Uniform evidence$1) {
      return Uniform$.MODULE$.apply(evidence$1).apply(low, high);
   }

   public Dist gaussian(final Object mean, final Object stdDev, final Gaussian evidence$2) {
      return Gaussian$.MODULE$.apply(evidence$2).apply(mean, stdDev);
   }

   public Dist reduce(final Seq ns, final Function2 f) {
      return new DistFromGen((g) -> ((IterableOnceOps)ns.map((x$1) -> x$1.apply(g))).reduceLeft(f));
   }

   public Dist fromBytes(final int n, final Function1 f) {
      return new DistFromGen((g) -> f.apply(g.generateBytes(n)));
   }

   public Dist fromInts(final int n, final Function1 f) {
      return new DistFromGen((g) -> f.apply(g.generateInts(n)));
   }

   public Dist fromLongs(final int n, final Function1 f) {
      return new DistFromGen((g) -> f.apply(g.generateLongs(n)));
   }

   public Dist mix(final Seq ds) {
      return this.oneOf(ds, .MODULE$.apply(Dist.class)).flatMap((x) -> (Dist)scala.Predef..MODULE$.identity(x));
   }

   public Dist weightedMix(final Seq tpls) {
      Dist[] ds = new Dist[tpls.length()];
      double[] ws = new double[tpls.length()];
      IntRef i = IntRef.create(0);
      DoubleRef total = DoubleRef.create((double)0.0F);
      tpls.foreach((x0$1) -> {
         $anonfun$weightedMix$1(total, ws, i, ds, x0$1);
         return BoxedUnit.UNIT;
      });
      return new DistFromGen((g) -> {
         double w = g.nextDouble(total.elem);

         int i;
         for(i = 0; ws[i] < w; ++i) {
         }

         return ds[i].apply(g);
      });
   }

   public Dist unit() {
      return unit;
   }

   public Dist boolean() {
      return boolean;
   }

   public Dist byte() {
      return byte;
   }

   public Dist short() {
      return short;
   }

   public Dist char() {
      return char;
   }

   public Dist int() {
      return int;
   }

   public Dist float() {
      return float;
   }

   public Dist long() {
      return long;
   }

   public Dist double() {
      return double;
   }

   public Dist ubyte() {
      return ubyte;
   }

   public Dist ushort() {
      return ushort;
   }

   public Dist uint() {
      return uint;
   }

   public Dist ulong() {
      return ulong;
   }

   public Dist complex(final Fractional evidence$3, final Trig evidence$4, final IsReal evidence$5, final Dist evidence$6) {
      return this.apply((x$10, x$11) -> new Complex(x$10, x$11), evidence$6, evidence$6);
   }

   public Dist interval(final AdditiveMonoid evidence$7, final Dist evidence$8, final Order evidence$9) {
      return this.apply((x, y) -> package$.MODULE$.Order().apply(evidence$9).lt(x, y) ? Interval$.MODULE$.apply(x, y, evidence$9) : Interval$.MODULE$.apply(y, x, evidence$9), evidence$8, evidence$8);
   }

   public Dist option(final Dist no, final Dist na) {
      return new DistFromGen((g) -> (Option)(no.apply$mcZ$sp(g) ? new Some(na.apply(g)) : scala.None..MODULE$));
   }

   public Dist either(final Dist no, final Dist na, final Dist nb) {
      return new DistFromGen((g) -> (Either)(no.apply$mcZ$sp(g) ? scala.package..MODULE$.Right().apply(nb.apply(g)) : scala.package..MODULE$.Left().apply(na.apply(g))));
   }

   public Dist tuple2(final Dist evidence$10, final Dist evidence$11) {
      return this.apply((x$12, x$13) -> new Tuple2(x$12, x$13), evidence$10, evidence$11);
   }

   public Dist intrange(final int from, final int to) {
      int d = to - from + 1;
      return new DistFromGen$mcI$sp((x$14) -> BoxesRunTime.boxToInteger($anonfun$intrange$1(d, from, x$14)));
   }

   public Dist natural(final int maxDigits) {
      return new Dist(maxDigits) {
         private final int maxDigits$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private Natural loop(final Generator g, final int i, final int size, final Natural n) {
            while(i < size) {
               int var10001 = i + 1;
               n = new Natural.Digit(((UInt)g.next(Dist$.MODULE$.uint())).signed(), n);
               size = size;
               i = var10001;
               g = g;
            }

            return n;
         }

         public Natural apply(final Generator gen) {
            return this.loop(gen, 1, gen.nextInt(this.maxDigits$1) + 1, new Natural.End(((UInt)gen.next(Dist$.MODULE$.uint())).signed()));
         }

         public {
            this.maxDigits$1 = maxDigits$1;
            Dist.$init$(this);
         }
      };
   }

   public Dist safelong(final int maxBytes) {
      if (maxBytes <= 0) {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("need positive maxBytes, got %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(maxBytes)})));
      } else {
         Object var10000;
         if (maxBytes < 8) {
            int n = (8 - maxBytes) * 8;
            var10000 = new DistFromGen((g) -> SafeLong$.MODULE$.apply(g.nextLong() >>> n));
         } else {
            var10000 = maxBytes == 8 ? new DistFromGen((g) -> SafeLong$.MODULE$.apply(g.nextLong())) : this.bigint(maxBytes).map((x$15) -> SafeLong$.MODULE$.apply(x$15));
         }

         return (Dist)var10000;
      }
   }

   public Dist bigint(final int maxBytes) {
      return new Dist(maxBytes) {
         private final int maxBytes$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         public BigInt apply(final Generator gen) {
            return scala.package..MODULE$.BigInt().apply(gen.generateBytes(gen.nextInt(this.maxBytes$1) + 1));
         }

         public {
            this.maxBytes$1 = maxBytes$1;
            Dist.$init$(this);
         }
      };
   }

   public Dist bigdecimal(final int maxBytes, final int maxScale) {
      return new Dist(maxBytes, maxScale) {
         private final Dist nb;
         private final int maxScale$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private Dist nb() {
            return this.nb;
         }

         public BigDecimal apply(final Generator gen) {
            return scala.package..MODULE$.BigDecimal().apply((BigInt)this.nb().apply(gen), gen.nextInt(this.maxScale$1) + 1);
         }

         public {
            this.maxScale$1 = maxScale$1;
            Dist.$init$(this);
            this.nb = Dist$.MODULE$.bigint(maxBytes$2);
         }
      };
   }

   public Dist rational(final Dist next) {
      return this.apply((x$16, x$17) -> Rational$.MODULE$.apply(x$16, x$17), next, next.filter((x$18) -> BoxesRunTime.boxToBoolean($anonfun$rational$2(x$18))));
   }

   public Dist longrational() {
      return this.apply((x$19, x$20) -> $anonfun$longrational$1(BoxesRunTime.unboxToLong(x$19), BoxesRunTime.unboxToLong(x$20)), this.apply(this.long()), this.apply(this.long()).filter$mcJ$sp((JFunction1.mcZJ.sp)(x$21) -> x$21 != 0L));
   }

   public Dist bigrational(final int maxBytes) {
      return this.rational(this.bigint(maxBytes));
   }

   public Dist constant(final Object a) {
      return new DistFromGen((g) -> a);
   }

   public Dist always(final Object a) {
      return new DistFromGen((g) -> a);
   }

   public Dist array(final int minSize, final int maxSize, final Dist evidence$12, final ClassTag evidence$13) {
      return new Dist(maxSize, minSize, evidence$12, evidence$13) {
         private final int d;
         private final int minSize$1;
         private final Dist evidence$12$1;
         private final ClassTag evidence$13$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private int d() {
            return this.d;
         }

         public Object apply(final Generator gen) {
            return gen.generateArray(gen.nextInt(this.d()) + this.minSize$1, this.evidence$12$1, this.evidence$13$1);
         }

         public {
            this.minSize$1 = minSize$1;
            this.evidence$12$1 = evidence$12$1;
            this.evidence$13$1 = evidence$13$1;
            Dist.$init$(this);
            this.d = maxSize$1 - minSize$1 + 1;
         }
      };
   }

   public Dist list(final int minSize, final int maxSize, final Dist evidence$14) {
      return new Dist(maxSize, minSize, evidence$14) {
         private final int d;
         private final int minSize$2;
         private final Dist evidence$14$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private int d() {
            return this.d;
         }

         private List loop(final Generator g, final int n, final List sofar) {
            while(n > 0) {
               int var10001 = n - 1;
               Object var5 = g.next(this.evidence$14$1);
               sofar = sofar.$colon$colon(var5);
               n = var10001;
               g = g;
            }

            return sofar;
         }

         public List apply(final Generator gen) {
            return this.loop(gen, gen.nextInt(this.d()) + this.minSize$2, scala.package..MODULE$.Nil());
         }

         public {
            this.minSize$2 = minSize$2;
            this.evidence$14$1 = evidence$14$1;
            Dist.$init$(this);
            this.d = maxSize$2 - minSize$2 + 1;
         }
      };
   }

   public Dist set(final int minInputs, final int maxInputs, final Dist evidence$15) {
      return this.list(minInputs, maxInputs, evidence$15).map((x$22) -> x$22.toSet());
   }

   public Dist map(final int minInputs, final int maxInputs, final Dist evidence$16, final Dist evidence$17) {
      return this.list(minInputs, maxInputs, this.tuple2(evidence$16, evidence$17)).map((x$23) -> x$23.toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Dist oneOf(final Seq as, final ClassTag evidence$18) {
      return new Dist(as, evidence$18) {
         private final Object arr;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private Object arr() {
            return this.arr;
         }

         public Object apply(final Generator gen) {
            return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.arr(), gen.nextInt(scala.runtime.ScalaRunTime..MODULE$.array_length(this.arr())));
         }

         public {
            Dist.$init$(this);
            this.arr = as$1.toArray(evidence$18$1);
         }
      };
   }

   public Dist cycleOf(final Seq as, final ClassTag evidence$19) {
      return new Dist(as, evidence$19) {
         private final Object arr;
         private int i;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.apply$mcZ$sp$(this, gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.apply$mcB$sp$(this, gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.apply$mcC$sp$(this, gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.apply$mcD$sp$(this, gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.apply$mcF$sp$(this, gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.apply$mcI$sp$(this, gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.apply$mcJ$sp$(this, gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.apply$mcS$sp$(this, gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.apply$mcV$sp$(this, gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.fill$(this, gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.fill$mcZ$sp$(this, gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.fill$mcB$sp$(this, gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.fill$mcC$sp$(this, gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.fill$mcD$sp$(this, gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.fill$mcF$sp$(this, gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.fill$mcI$sp$(this, gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.fill$mcJ$sp$(this, gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.fill$mcS$sp$(this, gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.fill$mcV$sp$(this, gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.map$(this, f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.map$mcZ$sp$(this, f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.map$mcB$sp$(this, f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.map$mcC$sp$(this, f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.map$mcD$sp$(this, f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.map$mcF$sp$(this, f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.map$mcI$sp$(this, f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.map$mcJ$sp$(this, f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.map$mcS$sp$(this, f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.map$mcV$sp$(this, f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.flatMap$(this, f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.flatMap$mcZ$sp$(this, f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.flatMap$mcB$sp$(this, f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.flatMap$mcC$sp$(this, f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.flatMap$mcD$sp$(this, f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.flatMap$mcF$sp$(this, f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.flatMap$mcI$sp$(this, f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.flatMap$mcJ$sp$(this, f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.flatMap$mcS$sp$(this, f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.flatMap$mcV$sp$(this, f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.filter$(this, pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.filter$mcZ$sp$(this, pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.filter$mcB$sp$(this, pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.filter$mcC$sp$(this, pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.filter$mcD$sp$(this, pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.filter$mcF$sp$(this, pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.filter$mcI$sp$(this, pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.filter$mcJ$sp$(this, pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.filter$mcS$sp$(this, pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.filter$mcV$sp$(this, pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.given$(this, pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.given$mcZ$sp$(this, pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.given$mcB$sp$(this, pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.given$mcC$sp$(this, pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.given$mcD$sp$(this, pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.given$mcF$sp$(this, pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.given$mcI$sp$(this, pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.given$mcJ$sp$(this, pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.given$mcS$sp$(this, pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.given$mcV$sp$(this, pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.until$(this, pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.until$mcZ$sp$(this, pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.until$mcB$sp$(this, pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.until$mcC$sp$(this, pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.until$mcD$sp$(this, pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.until$mcF$sp$(this, pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.until$mcI$sp$(this, pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.until$mcJ$sp$(this, pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.until$mcS$sp$(this, pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.until$mcV$sp$(this, pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.foldn$(this, init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcZ$sp$(this, init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcB$sp$(this, init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcC$sp$(this, init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcD$sp$(this, init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcF$sp$(this, init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcI$sp$(this, init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcJ$sp$(this, init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcS$sp$(this, init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.foldn$mcV$sp$(this, init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$(this, init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcZ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcB$sp$(this, init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcC$sp$(this, init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcD$sp$(this, init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcF$sp$(this, init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcI$sp$(this, init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcJ$sp$(this, init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcS$sp$(this, init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.unfold$mcV$sp$(this, init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.pack$(this, n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcZ$sp$(this, n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcB$sp$(this, n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcC$sp$(this, n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcD$sp$(this, n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcF$sp$(this, n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcI$sp$(this, n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcJ$sp$(this, n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcS$sp$(this, n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.pack$mcV$sp$(this, n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.repeat$(this, n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.iterate$(this, n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcZ$sp$(this, n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.iterate$mcB$sp$(this, n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.iterate$mcC$sp$(this, n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.iterate$mcD$sp$(this, n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.iterate$mcF$sp$(this, n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.iterate$mcI$sp$(this, n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.iterate$mcJ$sp$(this, n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.iterate$mcS$sp$(this, n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.iterate$mcV$sp$(this, n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$(this, pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcZ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcB$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcC$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcD$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcF$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcI$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcJ$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcS$sp$(this, pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.iterateUntil$mcV$sp$(this, pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.zip$(this, that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.zip$mcZ$sp$(this, that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.zip$mcB$sp$(this, that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.zip$mcC$sp$(this, that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.zip$mcD$sp$(this, that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.zip$mcF$sp$(this, that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.zip$mcI$sp$(this, that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.zip$mcJ$sp$(this, that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.zip$mcS$sp$(this, that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.zip$mcV$sp$(this, that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.zipWith$(this, that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcZ$sp$(this, that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcB$sp$(this, that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcC$sp$(this, that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcD$sp$(this, that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcF$sp$(this, that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcI$sp$(this, that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcJ$sp$(this, that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcS$sp$(this, that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.zipWith$mcV$sp$(this, that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.toIterator$(this, gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.toLazyList$(this, gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.toStream$(this, gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.sample$(this, n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$(this, pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcZ$sp$(this, pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcB$sp$(this, pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcC$sp$(this, pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcD$sp$(this, pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcF$sp$(this, pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcI$sp$(this, pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcJ$sp$(this, pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcS$sp$(this, pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.count$mcV$sp$(this, pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$(this, pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcZ$sp$(this, pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcB$sp$(this, pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcC$sp$(this, pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcD$sp$(this, pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcF$sp$(this, pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcI$sp$(this, pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcJ$sp$(this, pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcS$sp$(this, pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.pr$mcV$sp$(this, pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$(this, n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcZ$sp$(this, n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcB$sp$(this, n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcC$sp$(this, n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcD$sp$(this, n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcF$sp$(this, n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcI$sp$(this, n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcJ$sp$(this, n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.sum$mcS$sp$(this, n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.sum$mcV$sp$(this, n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.ev$(this, n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcZ$sp$(this, n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcB$sp$(this, n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcC$sp$(this, n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcD$sp$(this, n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcF$sp$(this, n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcI$sp$(this, n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcJ$sp$(this, n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.ev$mcS$sp$(this, n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.ev$mcV$sp$(this, n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.histogram$(this, n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.rawHistogram$(this, n, gen);
         }

         private Object arr() {
            return this.arr;
         }

         private int i() {
            return this.i;
         }

         private void i_$eq(final int x$1) {
            this.i = x$1;
         }

         public Object apply(final Generator gen) {
            Object a = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.arr(), this.i());
            this.i_$eq((this.i() + 1) % scala.runtime.ScalaRunTime..MODULE$.array_length(this.arr()));
            return a;
         }

         public {
            Dist.$init$(this);
            this.arr = as$2.toArray(evidence$19$1);
            this.i = 0;
         }
      };
   }

   public DistFromGen gaussianFromDouble(final Field evidence$20) {
      return new DistFromGen((g) -> package$.MODULE$.Field().apply(evidence$20).fromDouble(g.nextGaussian()));
   }

   // $FF: synthetic method
   public static final void $anonfun$weightedMix$1(final DoubleRef total$1, final double[] ws$1, final IntRef i$1, final Dist[] ds$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double w = x0$1._1$mcD$sp();
         Dist d = (Dist)x0$1._2();
         total$1.elem += w;
         ws$1[i$1.elem] = total$1.elem;
         ds$1[i$1.elem] = d;
         ++i$1.elem;
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$unit$1(final Generator g) {
   }

   // $FF: synthetic method
   public static final boolean $anonfun$boolean$1(final Generator x$2) {
      return x$2.nextBoolean();
   }

   // $FF: synthetic method
   public static final byte $anonfun$byte$1(final Generator x$3) {
      return (byte)x$3.nextInt();
   }

   // $FF: synthetic method
   public static final short $anonfun$short$1(final Generator x$4) {
      return (short)x$4.nextInt();
   }

   // $FF: synthetic method
   public static final char $anonfun$char$1(final Generator x$5) {
      return (char)x$5.nextInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$int$1(final Generator x$6) {
      return x$6.nextInt();
   }

   // $FF: synthetic method
   public static final float $anonfun$float$1(final Generator x$7) {
      return x$7.nextFloat();
   }

   // $FF: synthetic method
   public static final long $anonfun$long$1(final Generator x$8) {
      return x$8.nextLong();
   }

   // $FF: synthetic method
   public static final double $anonfun$double$1(final Generator x$9) {
      return x$9.nextDouble();
   }

   // $FF: synthetic method
   public static final byte $anonfun$ubyte$1(final Generator g) {
      return UByte$.MODULE$.apply(g.nextInt());
   }

   // $FF: synthetic method
   public static final char $anonfun$ushort$1(final Generator g) {
      return UShort$.MODULE$.apply(g.nextInt());
   }

   // $FF: synthetic method
   public static final int $anonfun$uint$1(final Generator g) {
      return UInt$.MODULE$.apply(g.nextInt());
   }

   // $FF: synthetic method
   public static final long $anonfun$ulong$1(final Generator g) {
      return ULong$.MODULE$.apply(g.nextLong());
   }

   // $FF: synthetic method
   public static final int $anonfun$intrange$1(final int d$1, final int from$1, final Generator x$14) {
      return x$14.nextInt(d$1) + from$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$rational$2(final BigInt x$18) {
      return !BoxesRunTime.equalsNumObject(x$18, BoxesRunTime.boxToInteger(0));
   }

   // $FF: synthetic method
   public static final Rational $anonfun$longrational$1(final long x$19, final long x$20) {
      return Rational$.MODULE$.apply(x$19, x$20);
   }

   private Dist$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
