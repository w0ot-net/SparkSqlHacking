package spire.random;

import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.random.rng.SyncGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005y:QAC\u0006\t\u0002A1QAE\u0006\t\u0002MAQaF\u0001\u0005\u0002aAq!G\u0001C\u0002\u0013%!\u0004\u0003\u0004!\u0003\u0001\u0006Ia\u0007\u0005\u0006C\u0005!\tE\u0007\u0005\u0006E\u0005!\ta\t\u0005\u0006O\u0005!\t\u0005\u000b\u0005\u0006e\u0005!\ta\r\u0005\u0006s\u0005!\tAO\u0001\n\u000f2|'-\u00197S]\u001eT!\u0001D\u0007\u0002\rI\fg\u000eZ8n\u0015\u0005q\u0011!B:qSJ,7\u0001\u0001\t\u0003#\u0005i\u0011a\u0003\u0002\n\u000f2|'-\u00197S]\u001e\u001c\"!\u0001\u000b\u0011\u0005E)\u0012B\u0001\f\f\u0005IauN\\4CCN,GmR3oKJ\fGo\u001c:\u0002\rqJg.\u001b;?)\u0005\u0001\u0012a\u0001:oOV\t1\u0004\u0005\u0002\u001d=5\tQD\u0003\u0002\u001a\u0017%\u0011q$\b\u0002\u000e'ft7mR3oKJ\fGo\u001c:\u0002\tItw\rI\u0001\u0005gft7-\u0001\u0005d_BL\u0018J\\5u+\u0005!\u0003CA\t&\u0013\t13BA\u0005HK:,'/\u0019;pe\u0006aq-\u001a;TK\u0016$')\u001f;fgV\t\u0011\u0006E\u0002+[=j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003UAJ!!M\u0016\u0003\t\tKH/Z\u0001\rg\u0016$8+Z3e\u0005f$Xm\u001d\u000b\u0003i]\u0002\"AK\u001b\n\u0005YZ#\u0001B+oSRDQ\u0001\u000f\u0005A\u0002%\nQAY=uKN\f\u0001B\\3yi2{gn\u001a\u000b\u0002wA\u0011!\u0006P\u0005\u0003{-\u0012A\u0001T8oO\u0002"
)
public final class GlobalRng {
   public static long nextLong() {
      return GlobalRng$.MODULE$.nextLong();
   }

   public static void setSeedBytes(final byte[] bytes) {
      GlobalRng$.MODULE$.setSeedBytes(bytes);
   }

   public static byte[] getSeedBytes() {
      return GlobalRng$.MODULE$.getSeedBytes();
   }

   public static Generator copyInit() {
      return GlobalRng$.MODULE$.copyInit();
   }

   public static SyncGenerator sync() {
      return GlobalRng$.MODULE$.sync();
   }

   public static void fillBytes(final byte[] arr) {
      GlobalRng$.MODULE$.fillBytes(arr);
   }

   public static void fillShorts(final short[] arr) {
      GlobalRng$.MODULE$.fillShorts(arr);
   }

   public static void fillInts(final int[] arr) {
      GlobalRng$.MODULE$.fillInts(arr);
   }

   public static int nextInt() {
      return GlobalRng$.MODULE$.nextInt();
   }

   public static double[] generateGaussians(final int n, final double mean, final double stddev) {
      return GlobalRng$.MODULE$.generateGaussians(n, mean, stddev);
   }

   public static double[] generateGaussians(final int n) {
      return GlobalRng$.MODULE$.generateGaussians(n);
   }

   public static void fillGaussians(final double[] arr, final double mean, final double stddev) {
      GlobalRng$.MODULE$.fillGaussians(arr, mean, stddev);
   }

   public static void fillGaussians(final double[] arr) {
      GlobalRng$.MODULE$.fillGaussians(arr);
   }

   public static double nextGaussian(final double mean, final double stddev) {
      return GlobalRng$.MODULE$.nextGaussian(mean, stddev);
   }

   public static double nextGaussian() {
      return GlobalRng$.MODULE$.nextGaussian();
   }

   public static void shuffle(final Object as, final Generator gen) {
      GlobalRng$.MODULE$.shuffle(as, gen);
   }

   /** @deprecated */
   public static Object sampleFromTraversable(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return GlobalRng$.MODULE$.sampleFromTraversable(as, size, evidence$6, gen);
   }

   public static Object sampleFromIterable(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      return GlobalRng$.MODULE$.sampleFromIterable(as, size, evidence$5, gen);
   }

   public static Object sampleFromArray(final Object as, final int size, final ClassTag evidence$4, final Generator gen) {
      return GlobalRng$.MODULE$.sampleFromArray(as, size, evidence$4, gen);
   }

   public static Object chooseFromIterable(final Iterable as, final Generator gen) {
      return GlobalRng$.MODULE$.chooseFromIterable(as, gen);
   }

   public static Object chooseFromSeq(final Seq seq, final Generator gen) {
      return GlobalRng$.MODULE$.chooseFromSeq(seq, gen);
   }

   public static Object chooseFromArray(final Object arr, final Generator gen) {
      return GlobalRng$.MODULE$.chooseFromArray(arr, gen);
   }

   public static Object oneOf(final Seq as) {
      return GlobalRng$.MODULE$.oneOf(as);
   }

   public static void fillArray(final Object arr, final Dist evidence$3) {
      GlobalRng$.MODULE$.fillArray(arr, evidence$3);
   }

   public static Object generateArray(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      return GlobalRng$.MODULE$.generateArray(n, evidence$1, evidence$2);
   }

   public static byte[] generateBytes(final int n) {
      return GlobalRng$.MODULE$.generateBytes(n);
   }

   public static short[] generateShorts(final int n) {
      return GlobalRng$.MODULE$.generateShorts(n);
   }

   public static int[] generateInts(final int n) {
      return GlobalRng$.MODULE$.generateInts(n);
   }

   public static void fillLongs(final long[] arr) {
      GlobalRng$.MODULE$.fillLongs(arr);
   }

   public static long[] generateLongs(final int n) {
      return GlobalRng$.MODULE$.generateLongs(n);
   }

   public static double nextDouble(final double from, final double until) {
      return GlobalRng$.MODULE$.nextDouble(from, until);
   }

   public static double nextDouble(final double n) {
      return GlobalRng$.MODULE$.nextDouble(n);
   }

   public static double nextDouble() {
      return GlobalRng$.MODULE$.nextDouble();
   }

   public static float nextFloat(final float from, final float until) {
      return GlobalRng$.MODULE$.nextFloat(from, until);
   }

   public static float nextFloat(final float n) {
      return GlobalRng$.MODULE$.nextFloat(n);
   }

   public static float nextFloat() {
      return GlobalRng$.MODULE$.nextFloat();
   }

   public static boolean nextBoolean() {
      return GlobalRng$.MODULE$.nextBoolean();
   }

   public static long nextLong(final long from, final long to) {
      return GlobalRng$.MODULE$.nextLong(from, to);
   }

   public static long nextLong(final long n) {
      return GlobalRng$.MODULE$.nextLong(n);
   }

   public static int nextInt(final int from, final int to) {
      return GlobalRng$.MODULE$.nextInt(from, to);
   }

   public static int nextInt(final int n) {
      return GlobalRng$.MODULE$.nextInt(n);
   }

   public static int nextBits(final int n) {
      return GlobalRng$.MODULE$.nextBits(n);
   }

   public static Iterator iterator(final Dist next) {
      return GlobalRng$.MODULE$.iterator(next);
   }

   public static Object next(final Dist next) {
      return GlobalRng$.MODULE$.next(next);
   }

   public static Generator copy() {
      return GlobalRng$.MODULE$.copy();
   }
}
