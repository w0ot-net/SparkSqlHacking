package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.random.RandomGenerator;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ueaB\u000f\u001f!\u0003\r\t!\n\u0005\u0006s\u0001!\tA\u000f\u0005\u0006}\u00011\ta\u0010\u0005\u00063\u0002!\ta\u0010\u0005\u00065\u0002!\ta\u0017\u0005\u0006?\u0002!\ta\u0010\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006S\u0002!\tA\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\b\u0003\u001b\u0001A\u0011AA\b\u0011\u001d\t9\u0003\u0001C\u0001\u0003SAq!a\u000e\u0001\t\u0003\tI\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005E\u0003\u0001\"\u0001\u0002T!9\u0011q\u000b\u0001\u0005\u0002\u0005esaBA/=!\u0005\u0011q\f\u0004\u0007;yA\t!!\u0019\t\u000f\u0005%\u0004\u0003\"\u0001\u0002l\u001d9\u0011Q\u000e\t\t\u0002\u0005=daBA:!!\u0005\u0011Q\u000f\u0005\b\u0003S\u001aB\u0011AA<\u0011%\tIh\u0005b\u0001\n\u0007\tY\b\u0003\u0005\u0002~M\u0001\u000b\u0011BA2\u000f\u001d\ty\b\u0005E\u0001\u0003\u00033q!a!\u0011\u0011\u0003\t)\tC\u0004\u0002ja!\t!a\"\t\u0013\u0005e\u0004D1A\u0005\u0004\u0005m\u0004\u0002CA?1\u0001\u0006I!a\u0019\t\u0013\u0005%\u0005#!A\u0005\n\u0005-%\u0001\u0002*b]\u0012T!a\b\u0011\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t\t#%A\u0003ti\u0006$8OC\u0001$\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001\u0014C'\r\u0001q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u000592dBA\u00185\u001d\t\u00014'D\u00012\u0015\t\u0011D%\u0001\u0004=e>|GOP\u0005\u0002U%\u0011Q'K\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0004H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00026S\u00051A%\u001b8ji\u0012\"\u0012a\u000f\t\u0003QqJ!!P\u0015\u0003\tUs\u0017\u000e^\u0001\u0005IJ\fw\u000fF\u0001A!\t\t%\t\u0004\u0001\u0005\u0013\r\u0003\u0001\u0015!A\u0005\u0006\u0004!%!\u0001+\u0012\u0005\u0015C\u0005C\u0001\u0015G\u0013\t9\u0015FA\u0004O_RD\u0017N\\4\u0011\u0005!J\u0015B\u0001&*\u0005\r\te.\u001f\u0015\u0005\u00052{E\u000b\u0005\u0002)\u001b&\u0011a*\u000b\u0002\fgB,7-[1mSj,G-M\u0003$!F\u001b&K\u0004\u0002)#&\u0011!+K\u0001\u0004\u0013:$\u0018\u0007\u0002\u00130g)\nTaI+W1^s!\u0001\u000b,\n\u0005]K\u0013A\u0002#pk\ndW-\r\u0003%_MR\u0013aA4fi\u00069AM]1x\u001fB$H#\u0001/\u0011\u0007!j\u0006)\u0003\u0002_S\t1q\n\u001d;j_:\faa]1na2,GCA1e!\rq#\rQ\u0005\u0003Gb\u0012!\"\u00138eKb,GmU3r\u0011\u0015)g\u00011\u0001g\u0003\u0005q\u0007C\u0001\u0015h\u0013\tA\u0017FA\u0002J]R\fqa]1na2,7/F\u0001l!\rqC\u000eQ\u0005\u0003[b\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\u000eg\u0006l\u0007\u000f\\3t-\u0016\u001cGo\u001c:\u0016\u0005ALHcA9\u0002\nQ\u0011!\u000f \t\u0004gZDX\"\u0001;\u000b\u0005U\u0014\u0013A\u00027j]\u0006dw-\u0003\u0002xi\nYA)\u001a8tKZ+7\r^8s!\t\t\u0015\u0010B\u0003{\u0011\t\u00071PA\u0001V#\t\u0001\u0005\nC\u0003~\u0011\u0001\u000fa0A\u0001n!\u0011y\u0018Q\u0001=\u000e\u0005\u0005\u0005!bAA\u0002S\u00059!/\u001a4mK\u000e$\u0018\u0002BA\u0004\u0003\u0003\u0011\u0001b\u00117bgN$\u0016m\u001a\u0005\u0007\u0003\u0017A\u0001\u0019\u00014\u0002\tML'0Z\u0001\bM2\fG/T1q+\u0011\t\t\"!\u0007\u0015\t\u0005M\u0011Q\u0004\t\u0006\u0003+\u0001\u0011qC\u0007\u0002=A\u0019\u0011)!\u0007\u0005\r\u0005m\u0011B1\u0001E\u0005\u0005)\u0005bBA\u0010\u0013\u0001\u0007\u0011\u0011E\u0001\u0002MB1\u0001&a\tA\u0003'I1!!\n*\u0005%1UO\\2uS>t\u0017'A\u0002nCB,B!a\u000b\u00022Q!\u0011QFA\u001a!\u0015\t)\u0002AA\u0018!\r\t\u0015\u0011\u0007\u0003\u0007\u00037Q!\u0019\u0001#\t\u000f\u0005}!\u00021\u0001\u00026A1\u0001&a\tA\u0003_\tqAZ8sK\u0006\u001c\u0007\u000eF\u0002<\u0003wAq!a\b\f\u0001\u0004\ti\u0004E\u0003)\u0003G\u00015(\u0001\u0004gS2$XM\u001d\u000b\u0005\u0003\u0007\n)\u0005\u0005\u0003\u0002\u0016\u0001\u0001\u0005bBA$\u0019\u0001\u0007\u0011\u0011J\u0001\u0002aB1\u0001&a\tA\u0003\u0017\u00022\u0001KA'\u0013\r\ty%\u000b\u0002\b\u0005>|G.Z1o\u0003)9\u0018\u000e\u001e5GS2$XM\u001d\u000b\u0005\u0003\u0007\n)\u0006C\u0004\u0002H5\u0001\r!!\u0013\u0002\u0013\r|g\u000eZ5uS>tG\u0003BA\"\u00037Bq!a\u0012\u000f\u0001\u0004\tI%\u0001\u0003SC:$\u0007cAA\u000b!M\u0019\u0001#a\u0019\u0011\t\u0005U\u0011QM\u0005\u0004\u0003Or\"!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q\u0011\u0011qL\u0001\r-\u0006\u0014\u0018.\u00192mKN+W\r\u001a\t\u0004\u0003c\u001aR\"\u0001\t\u0003\u0019Y\u000b'/[1cY\u0016\u001cV-\u001a3\u0014\u0005M9CCAA8\u0003%\u0011\u0018M\u001c3CCNL7/\u0006\u0002\u0002d\u0005Q!/\u00198e\u0005\u0006\u001c\u0018n\u001d\u0011\u0002\u0013\u0019K\u00070\u001a3TK\u0016$\u0007cAA91\tIa)\u001b=fIN+W\rZ\n\u00031\u001d\"\"!!!\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0005\u0003BAH\u00033k!!!%\u000b\t\u0005M\u0015QS\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0018\u0006!!.\u0019<b\u0013\u0011\tY*!%\u0003\r=\u0013'.Z2u\u0001"
)
public interface Rand extends Serializable {
   static Rand subsetsOfSize(final IndexedSeq set, final int n) {
      return Rand$.MODULE$.subsetsOfSize(set, n);
   }

   static Rand permutation(final int n) {
      return Rand$.MODULE$.permutation(n);
   }

   static Rand gaussian(final double m, final double s) {
      return Rand$.MODULE$.gaussian(m, s);
   }

   static Rand gaussian() {
      return Rand$.MODULE$.gaussian();
   }

   static Rand randLong(final long n, final long m) {
      return Rand$.MODULE$.randLong(n, m);
   }

   static Rand randLong(final long n) {
      return Rand$.MODULE$.randLong(n);
   }

   static Rand randLong() {
      return Rand$.MODULE$.randLong();
   }

   static Rand randInt(final int n, final int m) {
      return Rand$.MODULE$.randInt(n, m);
   }

   static Rand randInt(final int n) {
      return Rand$.MODULE$.randInt(n);
   }

   static Rand randInt() {
      return Rand$.MODULE$.randInt();
   }

   static Rand uniform() {
      return Rand$.MODULE$.uniform();
   }

   static Rand promote(final Tuple4 t) {
      return Rand$.MODULE$.promote(t);
   }

   static Rand promote(final Tuple3 t) {
      return Rand$.MODULE$.promote(t);
   }

   static Rand promote(final Tuple2 t) {
      return Rand$.MODULE$.promote(t);
   }

   static Rand promote(final Seq col) {
      return Rand$.MODULE$.promote(col);
   }

   static Rand fromBody(final Function0 f) {
      return Rand$.MODULE$.fromBody(f);
   }

   static Rand always(final Object t) {
      return Rand$.MODULE$.always(t);
   }

   static Rand choose(final Seq c) {
      return Rand$.MODULE$.choose(c);
   }

   static Rand choose(final Iterable c) {
      return Rand$.MODULE$.choose(c);
   }

   static RandomGenerator generator() {
      return Rand$.MODULE$.generator();
   }

   Object draw();

   // $FF: synthetic method
   static Object get$(final Rand $this) {
      return $this.get();
   }

   default Object get() {
      return this.draw();
   }

   // $FF: synthetic method
   static Option drawOpt$(final Rand $this) {
      return $this.drawOpt();
   }

   default Option drawOpt() {
      return new Some(this.draw());
   }

   // $FF: synthetic method
   static Object sample$(final Rand $this) {
      return $this.sample();
   }

   default Object sample() {
      return this.get();
   }

   // $FF: synthetic method
   static IndexedSeq sample$(final Rand $this, final int n) {
      return $this.sample(n);
   }

   default IndexedSeq sample(final int n) {
      return (IndexedSeq).MODULE$.IndexedSeq().fill(n, () -> this.draw());
   }

   // $FF: synthetic method
   static Iterator samples$(final Rand $this) {
      return $this.samples();
   }

   default Iterator samples() {
      return .MODULE$.Iterator().continually(() -> this.draw());
   }

   // $FF: synthetic method
   static DenseVector samplesVector$(final Rand $this, final int size, final ClassTag m) {
      return $this.samplesVector(size, m);
   }

   default DenseVector samplesVector(final int size, final ClassTag m) {
      DenseVector result = new DenseVector(m.newArray(size));
      int index$macro$2 = 0;

      for(int limit$macro$4 = size; index$macro$2 < limit$macro$4; ++index$macro$2) {
         result.update(index$macro$2, this.draw());
      }

      return result;
   }

   // $FF: synthetic method
   static Rand flatMap$(final Rand $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Rand flatMap(final Function1 f) {
      return new FlatMappedRand(this, f);
   }

   // $FF: synthetic method
   static Rand map$(final Rand $this, final Function1 f) {
      return $this.map(f);
   }

   default Rand map(final Function1 f) {
      return new MappedRand(this, f);
   }

   // $FF: synthetic method
   static void foreach$(final Rand $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      f.apply(this.get());
   }

   // $FF: synthetic method
   static Rand filter$(final Rand $this, final Function1 p) {
      return $this.filter(p);
   }

   default Rand filter(final Function1 p) {
      return this.condition(p);
   }

   // $FF: synthetic method
   static Rand withFilter$(final Rand $this, final Function1 p) {
      return $this.withFilter(p);
   }

   default Rand withFilter(final Function1 p) {
      return this.condition(p);
   }

   // $FF: synthetic method
   static Rand condition$(final Rand $this, final Function1 p) {
      return $this.condition(p);
   }

   default Rand condition(final Function1 p) {
      return new SinglePredicateRand(this, p);
   }

   // $FF: synthetic method
   static double draw$mcD$sp$(final Rand $this) {
      return $this.draw$mcD$sp();
   }

   default double draw$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.draw());
   }

   // $FF: synthetic method
   static int draw$mcI$sp$(final Rand $this) {
      return $this.draw$mcI$sp();
   }

   default int draw$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.draw());
   }

   // $FF: synthetic method
   static double get$mcD$sp$(final Rand $this) {
      return $this.get$mcD$sp();
   }

   default double get$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.get());
   }

   // $FF: synthetic method
   static int get$mcI$sp$(final Rand $this) {
      return $this.get$mcI$sp();
   }

   default int get$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.get());
   }

   // $FF: synthetic method
   static double sample$mcD$sp$(final Rand $this) {
      return $this.sample$mcD$sp();
   }

   default double sample$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.sample());
   }

   // $FF: synthetic method
   static int sample$mcI$sp$(final Rand $this) {
      return $this.sample$mcI$sp();
   }

   default int sample$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.sample());
   }

   // $FF: synthetic method
   static DenseVector samplesVector$mcD$sp$(final Rand $this, final int size, final ClassTag m) {
      return $this.samplesVector$mcD$sp(size, m);
   }

   default DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return this.samplesVector(size, m);
   }

   // $FF: synthetic method
   static DenseVector samplesVector$mcI$sp$(final Rand $this, final int size, final ClassTag m) {
      return $this.samplesVector$mcI$sp(size, m);
   }

   default DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return this.samplesVector(size, m);
   }

   // $FF: synthetic method
   static Rand flatMap$mcD$sp$(final Rand $this, final Function1 f) {
      return $this.flatMap$mcD$sp(f);
   }

   default Rand flatMap$mcD$sp(final Function1 f) {
      return this.flatMap(f);
   }

   // $FF: synthetic method
   static Rand flatMap$mcI$sp$(final Rand $this, final Function1 f) {
      return $this.flatMap$mcI$sp(f);
   }

   default Rand flatMap$mcI$sp(final Function1 f) {
      return this.flatMap(f);
   }

   // $FF: synthetic method
   static Rand map$mcD$sp$(final Rand $this, final Function1 f) {
      return $this.map$mcD$sp(f);
   }

   default Rand map$mcD$sp(final Function1 f) {
      return this.map(f);
   }

   // $FF: synthetic method
   static Rand map$mcI$sp$(final Rand $this, final Function1 f) {
      return $this.map$mcI$sp(f);
   }

   default Rand map$mcI$sp(final Function1 f) {
      return this.map(f);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final Rand $this, final Function1 f) {
      $this.foreach$mcD$sp(f);
   }

   default void foreach$mcD$sp(final Function1 f) {
      this.foreach(f);
   }

   // $FF: synthetic method
   static void foreach$mcI$sp$(final Rand $this, final Function1 f) {
      $this.foreach$mcI$sp(f);
   }

   default void foreach$mcI$sp(final Function1 f) {
      this.foreach(f);
   }

   // $FF: synthetic method
   static Rand filter$mcD$sp$(final Rand $this, final Function1 p) {
      return $this.filter$mcD$sp(p);
   }

   default Rand filter$mcD$sp(final Function1 p) {
      return this.filter(p);
   }

   // $FF: synthetic method
   static Rand filter$mcI$sp$(final Rand $this, final Function1 p) {
      return $this.filter$mcI$sp(p);
   }

   default Rand filter$mcI$sp(final Function1 p) {
      return this.filter(p);
   }

   // $FF: synthetic method
   static Rand withFilter$mcD$sp$(final Rand $this, final Function1 p) {
      return $this.withFilter$mcD$sp(p);
   }

   default Rand withFilter$mcD$sp(final Function1 p) {
      return this.withFilter(p);
   }

   // $FF: synthetic method
   static Rand withFilter$mcI$sp$(final Rand $this, final Function1 p) {
      return $this.withFilter$mcI$sp(p);
   }

   default Rand withFilter$mcI$sp(final Function1 p) {
      return this.withFilter(p);
   }

   // $FF: synthetic method
   static Rand condition$mcD$sp$(final Rand $this, final Function1 p) {
      return $this.condition$mcD$sp(p);
   }

   default Rand condition$mcD$sp(final Function1 p) {
      return this.condition(p);
   }

   // $FF: synthetic method
   static Rand condition$mcI$sp$(final Rand $this, final Function1 p) {
      return $this.condition$mcI$sp(p);
   }

   default Rand condition$mcI$sp(final Function1 p) {
      return this.condition(p);
   }

   static void $init$(final Rand $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class VariableSeed$ {
      public static final VariableSeed$ MODULE$ = new VariableSeed$();
      private static final RandBasis randBasis;

      static {
         randBasis = Rand$.MODULE$;
      }

      public RandBasis randBasis() {
         return randBasis;
      }
   }

   public static class FixedSeed$ {
      public static final FixedSeed$ MODULE$ = new FixedSeed$();
      private static final RandBasis randBasis;

      static {
         randBasis = RandBasis$.MODULE$.mt0();
      }

      public RandBasis randBasis() {
         return randBasis;
      }
   }
}
