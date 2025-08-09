package breeze.linalg.support;

import breeze.math.Complex;
import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tecaB\u0016-!\u0003\r\na\r\u0005\u0006w\u00011\t\u0001\u0010\u0005\b\u0003\u001b\u0001a\u0011AA\b\u000f\u001d\t9\u0002\fE\u0001\u000331aa\u000b\u0017\t\u0002\u0005u\u0001bBA\u0010\t\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003G!A\u0011AA\u0013\r\u0019\t9\u0004\u0002\u0001\u0002:!Q\u0011\u0011P\u0004\u0003\u0004\u0003\u0006Y!a\u001f\t\u000f\u0005}q\u0001\"\u0001\u0002\b\"11h\u0002C\u0001\u0003#Cq!!\u0004\b\t\u0003\nY\nC\u0004\u0002$\u0012!\u0019!!*\b\u000f\u0005uF\u0001c\u0001\u0002@\u001a9\u0011\u0011\u0019\u0003\t\u0002\u0005\r\u0007bBA\u0010\u001d\u0011\u0005\u0011qY\u0004\b\u0003\u0013$\u00012AAf\r\u001d\ti\r\u0002E\u0001\u0003\u001fDq!a\b\u0012\t\u0003\tInB\u0004\u0002\\\u0012A\u0019!!8\u0007\u000f\u0005}G\u0001#\u0001\u0002b\"9\u0011q\u0004\u000b\u0005\u0002\u0005-xaBAw\t!\r\u0011q\u001e\u0004\b\u0003c$\u0001\u0012AAz\u0011\u001d\tyb\u0006C\u0001\u0003{<q!a@\u0005\u0011\u0007\u0011\tAB\u0004\u0003\u0004\u0011A\tA!\u0002\t\u000f\u0005}!\u0004\"\u0001\u0003\u0010\u001d9!\u0011\u0003\u0003\t\u0004\tMaa\u0002B\u000b\t!\u0005!q\u0003\u0005\b\u0003?iB\u0011\u0001B\u0014\u000f\u001d\u0011I\u0003\u0002E\u0002\u0005W1qA!\f\u0005\u0011\u0003\u0011y\u0003C\u0004\u0002 \u0001\"\tAa\r\b\u000f\tUB\u0001c\u0001\u00038\u00199!\u0011\b\u0003\t\u0002\tm\u0002bBA\u0010G\u0011\u0005!qH\u0004\b\u0005\u0003\"\u00012\u0001B\"\r\u001d\u0011)\u0005\u0002E\u0001\u0005\u000fBq!a\b'\t\u0003\u0011YeB\u0004\u0003N\u0011A\u0019Aa\u0014\u0007\u000f\tEC\u0001#\u0001\u0003T!9\u0011qD\u0015\u0005\u0002\t]#AE\"b]jK\u0007/T1q\u0017\u0016Lh+\u00197vKNT!!\f\u0018\u0002\u000fM,\b\u000f]8si*\u0011q\u0006M\u0001\u0007Y&t\u0017\r\\4\u000b\u0003E\naA\u0019:fKj,7\u0001A\u000b\u0007i-+fm_ \u0014\u0005\u0001)\u0004C\u0001\u001c:\u001b\u00059$\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i:$AB!osJ+g-A\u0002nCB$B!\u0010%N\u001fB\u0011ah\u0010\u0007\u0001\t\u0019\u0001\u0005\u0001\"b\u0001\u0003\n\u0011Ak\\\t\u0003\u0005\u0016\u0003\"AN\"\n\u0005\u0011;$a\u0002(pi\"Lgn\u001a\t\u0003m\u0019K!aR\u001c\u0003\u0007\u0005s\u0017\u0010C\u0003J\u0003\u0001\u0007!*\u0001\u0003ge>l\u0007C\u0001 L\t\u0015a\u0005A1\u0001B\u0005\u00111%o\\7\t\u000b9\u000b\u0001\u0019\u0001&\u0002\u000b\u0019\u0014x.\u001c\u001a\t\u000bA\u000b\u0001\u0019A)\u0002\u0005\u0019t\u0007C\u0002\u001cS)\u0016,'0\u0003\u0002To\tIa)\u001e8di&|gn\r\t\u0003}U#\u0011B\u0016\u0001!\u0002\u0003\u0005)\u0019A!\u0003\u0003-C3!\u0016-\\!\t1\u0014,\u0003\u0002[o\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019C,X0_\u001d\t1T,\u0003\u0002_o\u0005\u0019\u0011J\u001c;2\t\u0011\u0002G\r\u000f\b\u0003C\u0012l\u0011A\u0019\u0006\u0003GJ\na\u0001\u0010:p_Rt\u0014\"\u0001\u001d\u0011\u0005y2G!C4\u0001A\u0003\u0005\tQ1\u0001B\u0005\u00051\u0006F\u00024YS:\u0004X/M\u0003$U.lGN\u0004\u00027W&\u0011AnN\u0001\u0007\t>,(\r\\32\t\u0011\u0002G\rO\u0019\u0006GqkvNX\u0019\u0005I\u0001$\u0007(M\u0003$cJ$8O\u0004\u00027e&\u00111oN\u0001\u0006\r2|\u0017\r^\u0019\u0005I\u0001$\u0007(M\u0003$m^L\bP\u0004\u00027o&\u0011\u0001pN\u0001\u0005\u0019>tw-\r\u0003%A\u0012D\u0004C\u0001 |\t%a\b\u0001)A\u0001\u0002\u000b\u0007\u0011I\u0001\u0002S-\"J1\u0010\u0017@\u0002\u0002\u0005\u0015\u0011\u0011B\u0019\u0006G)\\w\u0010\\\u0019\u0005I\u0001$\u0007(\r\u0004$9v\u000b\u0019AX\u0019\u0005I\u0001$\u0007(\r\u0004$cJ\f9a]\u0019\u0005I\u0001$\u0007(\r\u0004$m^\fY\u0001_\u0019\u0005I\u0001$\u0007(A\u0005nCB\f5\r^5wKR9Q(!\u0005\u0002\u0014\u0005U\u0001\"B%\u0003\u0001\u0004Q\u0005\"\u0002(\u0003\u0001\u0004Q\u0005\"\u0002)\u0003\u0001\u0004\t\u0016AE\"b]jK\u0007/T1q\u0017\u0016Lh+\u00197vKN\u00042!a\u0007\u0005\u001b\u0005a3C\u0001\u00036\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0011D\u0001\u000eG\u0006t',\u001b9NCB\u001cV\r\u001c4\u0016\t\u0005\u001d\u0012QF\u000b\u0003\u0003S\u0001R\"a\u0007\u0001\u0003W\t\t$a\u000b\u0002,\u0005-\u0002c\u0001 \u0002.\u00111\u0011q\u0006\u0004C\u0002\u0005\u0013\u0011a\u0015\t\u0004m\u0005M\u0012bAA\u001bo\t!QK\\5u\u0005\u001dy\u0005/\u0011:sCf,b!a\u000f\u0002H\u0005\r4\u0003B\u00046\u0003{\u0001R\"a\u0007\u0001\u0003\u007f\tY&!\u0012\u0002b\u0005]\u0004#\u0002\u001c\u0002B\u0005\u0015\u0013bAA\"o\t)\u0011I\u001d:bsB\u0019a(a\u0012\u0005\u0013\u001d<\u0001\u0015!A\u0001\u0006\u0004\t\u0005fCA$1\u0006-\u0013qJA*\u0003/\nda\t6l\u0003\u001bb\u0017\u0007\u0002\u0013aIb\nda\t/^\u0003#r\u0016\u0007\u0002\u0013aIb\ndaI9s\u0003+\u001a\u0018\u0007\u0002\u0013aIb\nda\t<x\u00033B\u0018\u0007\u0002\u0013aIb\u00022ANA/\u0013\r\tyf\u000e\u0002\u0004\u0013:$\bc\u0001 \u0002d\u0011IAp\u0002Q\u0001\u0002\u0003\u0015\r!\u0011\u0015\f\u0003GB\u0016qMA6\u0003_\n\u0019(\r\u0004$U.\fI\u0007\\\u0019\u0005I\u0001$\u0007(\r\u0004$9v\u000biGX\u0019\u0005I\u0001$\u0007(\r\u0004$cJ\f\th]\u0019\u0005I\u0001$\u0007(\r\u0004$m^\f)\b_\u0019\u0005I\u0001$\u0007\bE\u00037\u0003\u0003\n\t'\u0001\u0006fm&$WM\\2fIE\u0002b!! \u0002\u0004\u0006\u0005TBAA@\u0015\r\t\tiN\u0001\be\u00164G.Z2u\u0013\u0011\t))a \u0003\u0011\rc\u0017m]:UC\u001e$\"!!#\u0015\t\u0005-\u0015q\u0012\t\b\u0003\u001b;\u0011QIA1\u001b\u0005!\u0001bBA=\u0013\u0001\u000f\u00111\u0010\u000b\t\u0003o\n\u0019*!&\u0002\u0018\"1\u0011J\u0003a\u0001\u0003\u007fAaA\u0014\u0006A\u0002\u0005}\u0002B\u0002)\u000b\u0001\u0004\tI\n\u0005\u00067%\u0006m\u0013QIA#\u0003C\"\u0002\"a\u001e\u0002\u001e\u0006}\u0015\u0011\u0015\u0005\u0007\u0013.\u0001\r!a\u0010\t\r9[\u0001\u0019AA \u0011\u0019\u00016\u00021\u0001\u0002\u001a\u00069q\u000e]!se\u0006LXCBAT\u0003[\u000b\u0019\f\u0006\u0003\u0002*\u0006]\u0006cBAG\u000f\u0005-\u0016\u0011\u0017\t\u0004}\u00055F!C4\rA\u0003\u0005\tQ1\u0001BQ\r\ti\u000b\u0017\t\u0004}\u0005MF!\u0003?\rA\u0003\u0005\tQ1\u0001BQ\r\t\u0019\f\u0017\u0005\n\u0003sc\u0011\u0011!a\u0002\u0003w\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\ti(a!\u00022\u0006Iq\n]!se\u0006L\u0018*\u0013\t\u0004\u0003\u001bs!!C(q\u0003J\u0014\u0018-_%J'\rq\u0011Q\u0019\t\b\u0003\u001b;\u00111LA.)\t\ty,A\u0005Pa\u0006\u0013(/Y=T'B\u0019\u0011QR\t\u0003\u0013=\u0003\u0018I\u001d:bsN\u001b6cA\t\u0002RB9\u0011QR\u0004\u0002T\u0006M\u0007c\u0001\u001c\u0002V&\u0019\u0011q[\u001c\u0003\u000bMCwN\u001d;\u0015\u0005\u0005-\u0017!C(q\u0003J\u0014\u0018-\u001f'M!\r\ti\t\u0006\u0002\n\u001fB\f%O]1z\u00192\u001b2\u0001FAr!\u001d\tiiBAs\u0003K\u00042ANAt\u0013\r\tIo\u000e\u0002\u0005\u0019>tw\r\u0006\u0002\u0002^\u0006Iq\n]!se\u0006LhI\u0012\t\u0004\u0003\u001b;\"!C(q\u0003J\u0014\u0018-\u001f$G'\r9\u0012Q\u001f\t\b\u0003\u001b;\u0011q_A|!\r1\u0014\u0011`\u0005\u0004\u0003w<$!\u0002$m_\u0006$HCAAx\u0003%y\u0005/\u0011:sCf$E\tE\u0002\u0002\u000ej\u0011\u0011b\u00149BeJ\f\u0017\u0010\u0012#\u0014\u0007i\u00119\u0001E\u0004\u0002\u000e\u001e\u0011IA!\u0003\u0011\u0007Y\u0012Y!C\u0002\u0003\u000e]\u0012a\u0001R8vE2,GC\u0001B\u0001\u0003%y\u0005/\u0011:sCf\u001c5\tE\u0002\u0002\u000ev\u0011\u0011b\u00149BeJ\f\u0017pQ\"\u0014\u0007u\u0011I\u0002E\u0004\u0002\u000e\u001e\u0011YBa\u0007\u0011\t\tu!1E\u0007\u0003\u0005?Q1A!\t1\u0003\u0011i\u0017\r\u001e5\n\t\t\u0015\"q\u0004\u0002\b\u0007>l\u0007\u000f\\3y)\t\u0011\u0019\"A\u0005Pa\u0006\u0013(/Y=J\tB\u0019\u0011Q\u0012\u0011\u0003\u0013=\u0003\u0018I\u001d:bs&#5c\u0001\u0011\u00032A9\u0011QR\u0004\u0002\\\t%AC\u0001B\u0016\u0003%y\u0005/\u0011:sCf\u001cF\tE\u0002\u0002\u000e\u000e\u0012\u0011b\u00149BeJ\f\u0017p\u0015#\u0014\u0007\r\u0012i\u0004E\u0004\u0002\u000e\u001e\t\u0019N!\u0003\u0015\u0005\t]\u0012!C(q\u0003J\u0014\u0018-\u001f'E!\r\tiI\n\u0002\n\u001fB\f%O]1z\u0019\u0012\u001b2A\nB%!\u001d\tiiBAs\u0005\u0013!\"Aa\u0011\u0002\u0013=\u0003\u0018I\u001d:bs\u001a#\u0005cAAGS\tIq\n]!se\u0006Lh\tR\n\u0004S\tU\u0003cBAG\u000f\u0005](\u0011\u0002\u000b\u0003\u0005\u001f\u0002"
)
public interface CanZipMapKeyValues {
   static OpArray opArray(final ClassTag evidence$2) {
      return CanZipMapKeyValues$.MODULE$.opArray(evidence$2);
   }

   static CanZipMapKeyValues canZipMapSelf() {
      return CanZipMapKeyValues$.MODULE$.canZipMapSelf();
   }

   Object map(final Object from, final Object from2, final Function3 fn);

   Object mapActive(final Object from, final Object from2, final Function3 fn);

   public static class OpArray implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1;

      public Object map(final Object from, final Object from2, final Function3 fn) {
         .MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(from) == scala.runtime.ScalaRunTime..MODULE$.array_length(from2), () -> "Array lengths don't match!");
         Object arr = this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(from));
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), scala.runtime.ScalaRunTime..MODULE$.array_length(from)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.runtime.ScalaRunTime..MODULE$.array_update(arr, i, fn.apply(BoxesRunTime.boxToInteger(i), scala.runtime.ScalaRunTime..MODULE$.array_apply(from, i), scala.runtime.ScalaRunTime..MODULE$.array_apply(from2, i))));
         return arr;
      }

      public Object mapActive(final Object from, final Object from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public double[] map$mcDD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcID$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcIF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcII$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] map$mcDJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (double[])this.map(from, from2, fn);
      }

      public float[] map$mcFJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (float[])this.map(from, from2, fn);
      }

      public int[] map$mcIJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (int[])this.map(from, from2, fn);
      }

      public long[] map$mcJJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (long[])this.map(from, from2, fn);
      }

      public double[] mapActive$mcDD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (double[])this.mapActive(from, from2, fn);
      }

      public float[] mapActive$mcFD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (float[])this.mapActive(from, from2, fn);
      }

      public int[] mapActive$mcID$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (int[])this.mapActive(from, from2, fn);
      }

      public long[] mapActive$mcJD$sp(final double[] from, final double[] from2, final Function3 fn) {
         return (long[])this.mapActive(from, from2, fn);
      }

      public double[] mapActive$mcDF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (double[])this.mapActive(from, from2, fn);
      }

      public float[] mapActive$mcFF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (float[])this.mapActive(from, from2, fn);
      }

      public int[] mapActive$mcIF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (int[])this.mapActive(from, from2, fn);
      }

      public long[] mapActive$mcJF$sp(final float[] from, final float[] from2, final Function3 fn) {
         return (long[])this.mapActive(from, from2, fn);
      }

      public double[] mapActive$mcDI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (double[])this.mapActive(from, from2, fn);
      }

      public float[] mapActive$mcFI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (float[])this.mapActive(from, from2, fn);
      }

      public int[] mapActive$mcII$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (int[])this.mapActive(from, from2, fn);
      }

      public long[] mapActive$mcJI$sp(final int[] from, final int[] from2, final Function3 fn) {
         return (long[])this.mapActive(from, from2, fn);
      }

      public double[] mapActive$mcDJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (double[])this.mapActive(from, from2, fn);
      }

      public float[] mapActive$mcFJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (float[])this.mapActive(from, from2, fn);
      }

      public int[] mapActive$mcIJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (int[])this.mapActive(from, from2, fn);
      }

      public long[] mapActive$mcJJ$sp(final long[] from, final long[] from2, final Function3 fn) {
         return (long[])this.mapActive(from, from2, fn);
      }

      public OpArray(final ClassTag evidence$1) {
         this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1 = evidence$1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class OpArrayII$ extends CanZipMapKeyValues$OpArray$mcII$sp {
      public static final OpArrayII$ MODULE$ = new OpArrayII$();

      public OpArrayII$() {
         super(scala.reflect.ClassTag..MODULE$.Int());
      }
   }

   public static class OpArraySS$ extends OpArray {
      public static final OpArraySS$ MODULE$ = new OpArraySS$();

      public OpArraySS$() {
         super(scala.reflect.ClassTag..MODULE$.Short());
      }
   }

   public static class OpArrayLL$ extends CanZipMapKeyValues$OpArray$mcJJ$sp {
      public static final OpArrayLL$ MODULE$ = new OpArrayLL$();

      public OpArrayLL$() {
         super(scala.reflect.ClassTag..MODULE$.Long());
      }
   }

   public static class OpArrayFF$ extends CanZipMapKeyValues$OpArray$mcFF$sp {
      public static final OpArrayFF$ MODULE$ = new OpArrayFF$();

      public OpArrayFF$() {
         super(scala.reflect.ClassTag..MODULE$.Float());
      }
   }

   public static class OpArrayDD$ extends CanZipMapKeyValues$OpArray$mcDD$sp {
      public static final OpArrayDD$ MODULE$ = new OpArrayDD$();

      public OpArrayDD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayCC$ extends OpArray {
      public static final OpArrayCC$ MODULE$ = new OpArrayCC$();

      public OpArrayCC$() {
         super(scala.reflect.ClassTag..MODULE$.apply(Complex.class));
      }
   }

   public static class OpArrayID$ extends CanZipMapKeyValues$OpArray$mcDI$sp {
      public static final OpArrayID$ MODULE$ = new OpArrayID$();

      public OpArrayID$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArraySD$ extends OpArray {
      public static final OpArraySD$ MODULE$ = new OpArraySD$();

      public OpArraySD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayLD$ extends CanZipMapKeyValues$OpArray$mcDJ$sp {
      public static final OpArrayLD$ MODULE$ = new OpArrayLD$();

      public OpArrayLD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayFD$ extends CanZipMapKeyValues$OpArray$mcDF$sp {
      public static final OpArrayFD$ MODULE$ = new OpArrayFD$();

      public OpArrayFD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }
}
