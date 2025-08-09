package breeze.stats.random;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.stats.distributions.Rand;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153A!\u0003\u0006\u0001#!Aq\u0005\u0001B\u0001B\u0003%\u0001\u0006C\u0003,\u0001\u0011\u0005A\u0006C\u00041\u0001\u0001\u0007I\u0011B\u0019\t\u000fI\u0002\u0001\u0019!C\u0005g!1\u0011\b\u0001Q!\n!BqA\u000f\u0001C\u0002\u0013\u00051\b\u0003\u0004C\u0001\u0001\u0006I\u0001\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0002\u000f\u0011\u0006dGo\u001c8TKF,XM\\2f\u0015\tYA\"\u0001\u0004sC:$w.\u001c\u0006\u0003\u001b9\tQa\u001d;biNT\u0011aD\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rIBDH\u0007\u00025)\u00111\u0004D\u0001\u000eI&\u001cHO]5ckRLwN\\:\n\u0005uQ\"\u0001\u0002*b]\u0012\u00042a\b\u0012%\u001b\u0005\u0001#BA\u0011\u000f\u0003\u0019a\u0017N\\1mO&\u00111\u0005\t\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u0014K%\u0011a\u0005\u0006\u0002\u0007\t>,(\r\\3\u0002\u0007\u0011LW\u000e\u0005\u0002\u0014S%\u0011!\u0006\u0006\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\b\u0006\u0002._A\u0011a\u0006A\u0007\u0002\u0015!)qE\u0001a\u0001Q\u0005)1m\\;oiV\t\u0001&A\u0005d_VtGo\u0018\u0013fcR\u0011Ag\u000e\t\u0003'UJ!A\u000e\u000b\u0003\tUs\u0017\u000e\u001e\u0005\bq\u0011\t\t\u00111\u0001)\u0003\rAH%M\u0001\u0007G>,h\u000e\u001e\u0011\u0002\rA\u0014\u0018.\\3t+\u0005a\u0004cA\n>\u007f%\u0011a\b\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003'\u0001K!!\u0011\u000b\u0003\t1{gnZ\u0001\baJLW.Z:!\u0003\u0011!'/Y<\u0015\u0003y\u0001"
)
public class HaltonSequence implements Rand {
   private int count;
   private final long[] primes;

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   private int count() {
      return this.count;
   }

   private void count_$eq(final int x$1) {
      this.count = x$1;
   }

   public long[] primes() {
      return this.primes;
   }

   public DenseVector draw() {
      this.count_$eq(this.count() + 1);
      double[] arr = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.longArrayOps(this.primes()), (JFunction1.mcDJ.sp)(prime) -> {
         double h = (double)0.0F;
         double f = (double)1.0F;

         for(long k = (long)this.count(); k > 0L; k /= prime) {
            f /= (double)prime;
            h += (double)(k % prime) * f;
         }

         return h % (double)1.0F;
      }, scala.reflect.ClassTag..MODULE$.Double());
      return new DenseVector$mcD$sp(arr);
   }

   public HaltonSequence(final int dim) {
      Rand.$init$(this);
      scala.Predef..MODULE$.require(dim > 0, () -> "dim must be positive!");
      this.count = 0;
      this.primes = (long[])scala.Array..MODULE$.iterate(BoxesRunTime.boxToLong(2L), dim, (JFunction1.mcJJ.sp)(last) -> (new BigInteger(Long.toString(last))).nextProbablePrime().longValue(), scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
