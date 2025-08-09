package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.random.RandomGenerator;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005c\u0001\u0002\u0012$\u0001)B\u0001\"\u0010\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005\u007f!)a\n\u0001C\u0001\u001f\")1\u000b\u0001C\u0001)\")1\u000b\u0001C\u0001S\")!\u000f\u0001C\u0001g\")!\u0010\u0001C\u0001w\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\u0006\u0001\u0011\u0005\u0011q\u0006\u0005\b\u0003\u0017\u0001A\u0011AA(\u0011\u001d\tY\u0001\u0001C\u0001\u0003gB\u0011\"!(\u0001\u0005\u0004%\t!a(\t\u0011\u0005%\u0006\u0001)A\u0005\u0003CC\u0011\"a+\u0001\u0005\u0004%\t!!,\t\u0011\u0005]\u0006\u0001)A\u0005\u0003_Cq!a+\u0001\t\u0003\tI\fC\u0004\u0002,\u0002!\t!a0\t\u0013\u0005\u001d\u0007A1A\u0005\u0002\u0005%\u0007\u0002CAj\u0001\u0001\u0006I!a3\t\u000f\u0005\u001d\u0007\u0001\"\u0001\u0002V\"9\u0011q\u0019\u0001\u0005\u0002\u0005e\u0007\"CAp\u0001\t\u0007I\u0011AAP\u0011!\t\t\u000f\u0001Q\u0001\n\u0005\u0005\u0006bBAp\u0001\u0011\u0005\u00111\u001d\u0005\b\u0003W\u0004A\u0011AAw\u0011\u001d\tI\u0010\u0001C\u0001\u0003w<qA!\u0004$\u0011\u0003\u0011yA\u0002\u0004#G!\u0005!\u0011\u0003\u0005\u0007\u001dr!\tA!\t\t\u000f\t\rB\u0004\"\u0001\u0003&!9!q\u0005\u000f\u0005\u0002\t\u0015\u0002b\u0002B\u00159\u0011\u0005!1\u0006\u0005\n\u0005ca\u0012\u0011!C\u0005\u0005g\u0011\u0011BU1oI\n\u000b7/[:\u000b\u0005\u0011*\u0013!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002'O\u0005)1\u000f^1ug*\t\u0001&\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u00011&\r\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1\u0014&\u0001\u0004=e>|GOP\u0005\u0002]%\u0011\u0011(L\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:[\u0005Iq-\u001a8fe\u0006$xN]\u000b\u0002\u007fA\u0011\u0001iS\u0007\u0002\u0003*\u0011!iQ\u0001\u0007e\u0006tGm\\7\u000b\u0005\u0011+\u0015!B7bi\"\u001c$B\u0001$H\u0003\u001d\u0019w.\\7p]NT!\u0001S%\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0015aA8sO&\u0011A*\u0011\u0002\u0010%\u0006tGm\\7HK:,'/\u0019;pe\u0006Qq-\u001a8fe\u0006$xN\u001d\u0011\u0002\rqJg.\u001b;?)\t\u0001&\u000b\u0005\u0002R\u00015\t1\u0005C\u0003>\u0007\u0001\u0007q(\u0001\u0004dQ>|7/Z\u000b\u0003+n#\"A\u00163\u0011\u0007E;\u0016,\u0003\u0002YG\t!!+\u00198e!\tQ6\f\u0004\u0001\u0005\u000bq#!\u0019A/\u0003\u0003Q\u000b\"AX1\u0011\u00051z\u0016B\u00011.\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\f2\n\u0005\rl#aA!os\")Q\r\u0002a\u0001M\u0006\t1\rE\u00023OfK!\u0001\u001b\u001f\u0003\u0011%#XM]1cY\u0016,\"A[7\u0015\u0005-t\u0007cA)XYB\u0011!,\u001c\u0003\u00069\u0016\u0011\r!\u0018\u0005\u0006K\u0016\u0001\ra\u001c\t\u0004eAd\u0017BA9=\u0005\r\u0019V-]\u0001\u0007C2<\u0018-_:\u0016\u0005Q<HCA;y!\r\tvK\u001e\t\u00035^$Q\u0001\u0018\u0004C\u0002uCQ!\u001f\u0004A\u0002Y\f\u0011\u0001^\u0001\tMJ|WNQ8esV\u0011Ap \u000b\u0004{\u0006\u0005\u0001cA)X}B\u0011!l \u0003\u00069\u001e\u0011\r!\u0018\u0005\t\u0003\u00079A\u00111\u0001\u0002\u0006\u0005\ta\r\u0005\u0003-\u0003\u000fq\u0018bAA\u0005[\tAAHY=oC6,g(A\u0004qe>lw\u000e^3\u0016\t\u0005=\u00111\u0005\u000b\u0005\u0003#\t9\u0003\u0005\u0003R/\u0006M\u0001CBA\u000b\u0003?\t\t#\u0004\u0002\u0002\u0018)!\u0011\u0011DA\u000e\u0003%IW.\\;uC\ndWMC\u0002\u0002\u001e5\n!bY8mY\u0016\u001cG/[8o\u0013\r\t\u0018q\u0003\t\u00045\u0006\rBABA\u0013\u0011\t\u0007QLA\u0001V\u0011\u001d\tI\u0003\u0003a\u0001\u0003W\t1aY8m!\u0011\u0011\u0004/!\f\u0011\tE;\u0016\u0011E\u000b\u0007\u0003c\ti$a\u0011\u0015\t\u0005M\u0012q\t\t\u0005#^\u000b)\u0004E\u0004-\u0003o\tY$!\u0011\n\u0007\u0005eRF\u0001\u0004UkBdWM\r\t\u00045\u0006uBABA \u0013\t\u0007QL\u0001\u0002UcA\u0019!,a\u0011\u0005\r\u0005\u0015\u0013B1\u0001^\u0005\t!&\u0007\u0003\u0004z\u0013\u0001\u0007\u0011\u0011\n\t\bY\u0005]\u00121JA'!\u0011\tv+a\u000f\u0011\tE;\u0016\u0011I\u000b\t\u0003#\ni&!\u0019\u0002fQ!\u00111KA5!\u0011\tv+!\u0016\u0011\u00131\n9&a\u0017\u0002`\u0005\r\u0014bAA-[\t1A+\u001e9mKN\u00022AWA/\t\u0019\tyD\u0003b\u0001;B\u0019!,!\u0019\u0005\r\u0005\u0015#B1\u0001^!\rQ\u0016Q\r\u0003\u0007\u0003OR!\u0019A/\u0003\u0005Q\u001b\u0004BB=\u000b\u0001\u0004\tY\u0007E\u0005-\u0003/\ni'a\u001c\u0002rA!\u0011kVA.!\u0011\tv+a\u0018\u0011\tE;\u00161M\u000b\u000b\u0003k\n\t)!\"\u0002\n\u00065E\u0003BA<\u0003#\u0003B!U,\u0002zAYA&a\u001f\u0002\u0000\u0005\r\u0015qQAF\u0013\r\ti(\f\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u0007i\u000b\t\t\u0002\u0004\u0002@-\u0011\r!\u0018\t\u00045\u0006\u0015EABA#\u0017\t\u0007Q\fE\u0002[\u0003\u0013#a!a\u001a\f\u0005\u0004i\u0006c\u0001.\u0002\u000e\u00121\u0011qR\u0006C\u0002u\u0013!\u0001\u0016\u001b\t\re\\\u0001\u0019AAJ!-a\u00131PAK\u0003/\u000bI*a'\u0011\tE;\u0016q\u0010\t\u0005#^\u000b\u0019\t\u0005\u0003R/\u0006\u001d\u0005\u0003B)X\u0003\u0017\u000bq!\u001e8jM>\u0014X.\u0006\u0002\u0002\"B!\u0011kVAR!\ra\u0013QU\u0005\u0004\u0003Ok#A\u0002#pk\ndW-\u0001\u0005v]&4wN]7!\u0003\u001d\u0011\u0018M\u001c3J]R,\"!a,\u0011\tE;\u0016\u0011\u0017\t\u0004Y\u0005M\u0016bAA[[\t\u0019\u0011J\u001c;\u0002\u0011I\fg\u000eZ%oi\u0002\"B!a,\u0002<\"9\u0011Q\u0018\tA\u0002\u0005E\u0016!\u00018\u0015\r\u0005=\u0016\u0011YAb\u0011\u001d\ti,\u0005a\u0001\u0003cCq!!2\u0012\u0001\u0004\t\t,A\u0001n\u0003!\u0011\u0018M\u001c3M_:<WCAAf!\u0011\tv+!4\u0011\u00071\ny-C\u0002\u0002R6\u0012A\u0001T8oO\u0006I!/\u00198e\u0019>tw\r\t\u000b\u0005\u0003\u0017\f9\u000eC\u0004\u0002>R\u0001\r!!4\u0015\r\u0005-\u00171\\Ao\u0011\u001d\ti,\u0006a\u0001\u0003\u001bDq!!2\u0016\u0001\u0004\ti-\u0001\u0005hCV\u001c8/[1o\u0003%9\u0017-^:tS\u0006t\u0007\u0005\u0006\u0004\u0002\"\u0006\u0015\u0018q\u001d\u0005\b\u0003\u000bD\u0002\u0019AAR\u0011\u001d\tI\u000f\u0007a\u0001\u0003G\u000b\u0011a]\u0001\fa\u0016\u0014X.\u001e;bi&|g\u000e\u0006\u0003\u0002p\u0006]\b\u0003B)X\u0003c\u0004RAMAz\u0003cK1!!>=\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\b\u0003{K\u0002\u0019AAY\u00035\u0019XOY:fiN|emU5{KV!\u0011Q B\u0003)\u0019\tyPa\u0002\u0003\fA!\u0011k\u0016B\u0001!\u0015\u0011\u00141\u001fB\u0002!\rQ&Q\u0001\u0003\u00069j\u0011\r!\u0018\u0005\b\u0005\u0013Q\u0002\u0019\u0001B\u0001\u0003\r\u0019X\r\u001e\u0005\b\u0003{S\u0002\u0019AAY\u0003%\u0011\u0016M\u001c3CCNL7\u000f\u0005\u0002R9M!Ad\u000bB\n!\u0011\u0011)Ba\b\u000e\u0005\t]!\u0002\u0002B\r\u00057\t!![8\u000b\u0005\tu\u0011\u0001\u00026bm\u0006L1a\u000fB\f)\t\u0011y!\u0001\u0006tsN$X-\\*fK\u0012,\u0012\u0001U\u0001\u0004[R\u0004\u0014\u0001C<ji\"\u001cV-\u001a3\u0015\u0007A\u0013i\u0003C\u0004\u00030\u0001\u0002\r!!-\u0002\tM,W\rZ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005k\u0001BAa\u000e\u0003>5\u0011!\u0011\b\u0006\u0005\u0005w\u0011Y\"\u0001\u0003mC:<\u0017\u0002\u0002B \u0005s\u0011aa\u00142kK\u000e$\b"
)
public class RandBasis implements Serializable {
   private final RandomGenerator generator;
   private final Rand uniform;
   private final Rand randInt;
   private final Rand randLong;
   private final Rand gaussian;

   public static RandBasis withSeed(final int seed) {
      return RandBasis$.MODULE$.withSeed(seed);
   }

   public static RandBasis mt0() {
      return RandBasis$.MODULE$.mt0();
   }

   public static RandBasis systemSeed() {
      return RandBasis$.MODULE$.systemSeed();
   }

   public RandomGenerator generator() {
      return this.generator;
   }

   public Rand choose(final Iterable c) {
      return new Rand(c) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final Iterable c$1;

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

         public Object draw() {
            double sz = this.$outer.uniform().draw$mcD$sp() * (double)this.c$1.size();
            Iterator elems = this.c$1.iterator();
            int i = 1;

            Object e;
            for(e = elems.next(); (double)i < sz; ++i) {
               e = elems.next();
            }

            return e;
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.c$1 = c$1;
               Rand.$init$(this);
            }
         }
      };
   }

   public Rand choose(final Seq c) {
      return this.randInt(c.size()).map$mcI$sp((x$1) -> $anonfun$choose$1(c, BoxesRunTime.unboxToInt(x$1)));
   }

   public Rand always(final Object t) {
      return new Rand(t) {
         private final Object t$1;

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

         public Object draw() {
            return this.t$1;
         }

         public {
            this.t$1 = t$1;
            Rand.$init$(this);
         }
      };
   }

   public Rand fromBody(final Function0 f) {
      return new Rand(f) {
         private final Function0 f$3;

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

         public Object draw() {
            return this.f$3.apply();
         }

         public {
            this.f$3 = f$3;
            Rand.$init$(this);
         }
      };
   }

   public Rand promote(final Seq col) {
      return this.fromBody(() -> (Seq)col.map((x$2) -> x$2.draw()));
   }

   public Rand promote(final Tuple2 t) {
      return this.fromBody(() -> new Tuple2(((Rand)t._1()).draw(), ((Rand)t._2()).draw()));
   }

   public Rand promote(final Tuple3 t) {
      return this.fromBody(() -> new Tuple3(((Rand)t._1()).draw(), ((Rand)t._2()).draw(), ((Rand)t._3()).draw()));
   }

   public Rand promote(final Tuple4 t) {
      return this.fromBody(() -> new Tuple4(((Rand)t._1()).draw(), ((Rand)t._2()).draw(), ((Rand)t._3()).draw(), ((Rand)t._4()).draw()));
   }

   public Rand uniform() {
      return this.uniform;
   }

   public Rand randInt() {
      return this.randInt;
   }

   public Rand randInt(final int n) {
      return new Rand$mcI$sp(n) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final int n$1;

         public int get() {
            return Rand$mcI$sp.get$(this);
         }

         public int get$mcI$sp() {
            return Rand$mcI$sp.get$mcI$sp$(this);
         }

         public int sample() {
            return Rand$mcI$sp.sample$(this);
         }

         public int sample$mcI$sp() {
            return Rand$mcI$sp.sample$mcI$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcI$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.flatMap$mcI$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcI$sp.map$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.map$mcI$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcI$sp.foreach$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand$mcI$sp.foreach$mcI$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcI$sp.filter$(this, p);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.filter$mcI$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcI$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.withFilter$mcI$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcI$sp.condition$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.condition$mcI$sp$(this, p);
         }

         public double draw$mcD$sp() {
            return Rand.draw$mcD$sp$(this);
         }

         public double get$mcD$sp() {
            return Rand.get$mcD$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
         }

         public double sample$mcD$sp() {
            return Rand.sample$mcD$sp$(this);
         }

         public IndexedSeq sample(final int n) {
            return Rand.sample$(this, n);
         }

         public Iterator samples() {
            return Rand.samples$(this);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand.flatMap$mcD$sp$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand.map$mcD$sp$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand.foreach$mcD$sp$(this, f);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand.filter$mcD$sp$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand.withFilter$mcD$sp$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand.condition$mcD$sp$(this, p);
         }

         public int draw() {
            return this.draw$mcI$sp();
         }

         public int draw$mcI$sp() {
            return this.$outer.generator().nextInt(this.n$1);
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.n$1 = n$1;
               Rand.$init$(this);
            }
         }
      };
   }

   public Rand randInt(final int n, final int m) {
      return new Rand$mcI$sp(m, n) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final int m$1;
         private final int n$2;

         public int get() {
            return Rand$mcI$sp.get$(this);
         }

         public int get$mcI$sp() {
            return Rand$mcI$sp.get$mcI$sp$(this);
         }

         public int sample() {
            return Rand$mcI$sp.sample$(this);
         }

         public int sample$mcI$sp() {
            return Rand$mcI$sp.sample$mcI$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcI$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.flatMap$mcI$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcI$sp.map$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.map$mcI$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcI$sp.foreach$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand$mcI$sp.foreach$mcI$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcI$sp.filter$(this, p);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.filter$mcI$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcI$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.withFilter$mcI$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcI$sp.condition$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.condition$mcI$sp$(this, p);
         }

         public double draw$mcD$sp() {
            return Rand.draw$mcD$sp$(this);
         }

         public double get$mcD$sp() {
            return Rand.get$mcD$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
         }

         public double sample$mcD$sp() {
            return Rand.sample$mcD$sp$(this);
         }

         public IndexedSeq sample(final int n) {
            return Rand.sample$(this, n);
         }

         public Iterator samples() {
            return Rand.samples$(this);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand.flatMap$mcD$sp$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand.map$mcD$sp$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand.foreach$mcD$sp$(this, f);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand.filter$mcD$sp$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand.withFilter$mcD$sp$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand.condition$mcD$sp$(this, p);
         }

         public int draw() {
            return this.draw$mcI$sp();
         }

         public int draw$mcI$sp() {
            return this.$outer.generator().nextInt(this.m$1 - this.n$2) + this.n$2;
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.m$1 = m$1;
               this.n$2 = n$2;
               Rand.$init$(this);
            }
         }
      };
   }

   public Rand randLong() {
      return this.randLong;
   }

   public Rand randLong(final long n) {
      return new Rand(n) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final long n$3;

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

         public long draw() {
            long maxVal = Long.MAX_VALUE - Long.MAX_VALUE % this.n$3 - 1L;

            long value;
            for(value = this.$outer.generator().nextLong() & Long.MAX_VALUE; value > maxVal; value = this.$outer.generator().nextLong() & Long.MAX_VALUE) {
            }

            return value % this.n$3;
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.n$3 = n$3;
               Rand.$init$(this);
               .MODULE$.require(n$3 > 0L);
            }
         }
      };
   }

   public Rand randLong(final long n, final long m) {
      return new Rand(m, n) {
         private final Rand inner;
         private final long n$4;

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

         private Rand inner() {
            return this.inner;
         }

         public long draw() {
            return BoxesRunTime.unboxToLong(this.inner().draw()) + this.n$4;
         }

         public {
            this.n$4 = n$4;
            Rand.$init$(this);
            this.inner = RandBasis.this.randLong(m$2 - n$4);
         }
      };
   }

   public Rand gaussian() {
      return this.gaussian;
   }

   public Rand gaussian(final double m, final double s) {
      return new Rand$mcD$sp(m, s) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final double m$3;
         private final double s$1;

         public double get() {
            return Rand$mcD$sp.get$(this);
         }

         public double get$mcD$sp() {
            return Rand$mcD$sp.get$mcD$sp$(this);
         }

         public double sample() {
            return Rand$mcD$sp.sample$(this);
         }

         public double sample$mcD$sp() {
            return Rand$mcD$sp.sample$mcD$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcD$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.flatMap$mcD$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcD$sp.map$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.map$mcD$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcD$sp.foreach$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand$mcD$sp.foreach$mcD$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcD$sp.filter$(this, p);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.filter$mcD$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcD$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.withFilter$mcD$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcD$sp.condition$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.condition$mcD$sp$(this, p);
         }

         public int draw$mcI$sp() {
            return Rand.draw$mcI$sp$(this);
         }

         public int get$mcI$sp() {
            return Rand.get$mcI$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
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

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand.flatMap$mcI$sp$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand.map$mcI$sp$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand.foreach$mcI$sp$(this, f);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand.filter$mcI$sp$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand.withFilter$mcI$sp$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand.condition$mcI$sp$(this, p);
         }

         public double draw() {
            return this.draw$mcD$sp();
         }

         public double draw$mcD$sp() {
            return this.m$3 + this.s$1 * this.$outer.gaussian().draw$mcD$sp();
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.m$3 = m$3;
               this.s$1 = s$1;
               Rand.$init$(this);
            }
         }
      };
   }

   public Rand permutation(final int n) {
      return new Rand(n) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final int n$5;

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

         public IndexedSeq draw() {
            ArrayBuffer arr = new ArrayBuffer();
            arr.$plus$plus$eq(scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.n$5));
            int i = this.n$5;

            while(i > 1) {
               int k = this.$outer.generator().nextInt(i);
               --i;
               int tmp = BoxesRunTime.unboxToInt(arr.apply(i));
               arr.update(i, arr.apply(k));
               arr.update(k, BoxesRunTime.boxToInteger(tmp));
            }

            return arr.toIndexedSeq();
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.n$5 = n$5;
               Rand.$init$(this);
            }
         }
      };
   }

   public Rand subsetsOfSize(final IndexedSeq set, final int n) {
      return new Rand(set, n) {
         // $FF: synthetic field
         private final RandBasis $outer;
         private final IndexedSeq set$1;
         private final int n$6;

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

         public IndexedSeq draw() {
            int[] arr = scala.Array..MODULE$.range(0, this.set$1.size());

            for(int i = 0; i < scala.runtime.RichInt..MODULE$.min$extension(.MODULE$.intWrapper(this.n$6), this.set$1.size()); ++i) {
               int k = this.$outer.generator().nextInt(this.set$1.size() - i) + i;
               int temp = arr[i];
               arr[i] = arr[k];
               arr[k] = temp;
            }

            return (IndexedSeq)scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.take$extension(.MODULE$.intArrayOps(arr), this.n$6))).map(this.set$1);
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               this.set$1 = set$1;
               this.n$6 = n$6;
               Rand.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   public static final Object $anonfun$choose$1(final Seq c$2, final int x$1) {
      return c$2.apply(x$1);
   }

   public RandBasis(final RandomGenerator generator) {
      this.generator = generator;
      this.uniform = new Rand$mcD$sp() {
         // $FF: synthetic field
         private final RandBasis $outer;

         public double get() {
            return Rand$mcD$sp.get$(this);
         }

         public double get$mcD$sp() {
            return Rand$mcD$sp.get$mcD$sp$(this);
         }

         public double sample() {
            return Rand$mcD$sp.sample$(this);
         }

         public double sample$mcD$sp() {
            return Rand$mcD$sp.sample$mcD$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcD$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.flatMap$mcD$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcD$sp.map$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.map$mcD$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcD$sp.foreach$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand$mcD$sp.foreach$mcD$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcD$sp.filter$(this, p);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.filter$mcD$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcD$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.withFilter$mcD$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcD$sp.condition$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.condition$mcD$sp$(this, p);
         }

         public int draw$mcI$sp() {
            return Rand.draw$mcI$sp$(this);
         }

         public int get$mcI$sp() {
            return Rand.get$mcI$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
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

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand.flatMap$mcI$sp$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand.map$mcI$sp$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand.foreach$mcI$sp$(this, f);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand.filter$mcI$sp$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand.withFilter$mcI$sp$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand.condition$mcI$sp$(this, p);
         }

         public double draw() {
            return this.draw$mcD$sp();
         }

         public double draw$mcD$sp() {
            return this.$outer.generator().nextDouble();
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               Rand.$init$(this);
            }
         }
      };
      this.randInt = new Rand$mcI$sp() {
         // $FF: synthetic field
         private final RandBasis $outer;

         public int get() {
            return Rand$mcI$sp.get$(this);
         }

         public int get$mcI$sp() {
            return Rand$mcI$sp.get$mcI$sp$(this);
         }

         public int sample() {
            return Rand$mcI$sp.sample$(this);
         }

         public int sample$mcI$sp() {
            return Rand$mcI$sp.sample$mcI$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand$mcI$sp.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcI$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.flatMap$mcI$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcI$sp.map$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand$mcI$sp.map$mcI$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcI$sp.foreach$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand$mcI$sp.foreach$mcI$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcI$sp.filter$(this, p);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.filter$mcI$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcI$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.withFilter$mcI$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcI$sp.condition$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand$mcI$sp.condition$mcI$sp$(this, p);
         }

         public double draw$mcD$sp() {
            return Rand.draw$mcD$sp$(this);
         }

         public double get$mcD$sp() {
            return Rand.get$mcD$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
         }

         public double sample$mcD$sp() {
            return Rand.sample$mcD$sp$(this);
         }

         public IndexedSeq sample(final int n) {
            return Rand.sample$(this, n);
         }

         public Iterator samples() {
            return Rand.samples$(this);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand.flatMap$mcD$sp$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand.map$mcD$sp$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand.foreach$mcD$sp$(this, f);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand.filter$mcD$sp$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand.withFilter$mcD$sp$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand.condition$mcD$sp$(this, p);
         }

         public int draw() {
            return this.draw$mcI$sp();
         }

         public int draw$mcI$sp() {
            return this.$outer.generator().nextInt() & Integer.MAX_VALUE;
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               Rand.$init$(this);
            }
         }
      };
      this.randLong = new Rand() {
         // $FF: synthetic field
         private final RandBasis $outer;

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

         public long draw() {
            return this.$outer.generator().nextLong() & Long.MAX_VALUE;
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               Rand.$init$(this);
            }
         }
      };
      this.gaussian = new Rand$mcD$sp() {
         // $FF: synthetic field
         private final RandBasis $outer;

         public double get() {
            return Rand$mcD$sp.get$(this);
         }

         public double get$mcD$sp() {
            return Rand$mcD$sp.get$mcD$sp$(this);
         }

         public double sample() {
            return Rand$mcD$sp.sample$(this);
         }

         public double sample$mcD$sp() {
            return Rand$mcD$sp.sample$mcD$sp$(this);
         }

         public DenseVector samplesVector(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$(this, size, m);
         }

         public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
            return Rand$mcD$sp.samplesVector$mcD$sp$(this, size, m);
         }

         public Rand flatMap(final Function1 f) {
            return Rand$mcD$sp.flatMap$(this, f);
         }

         public Rand flatMap$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.flatMap$mcD$sp$(this, f);
         }

         public Rand map(final Function1 f) {
            return Rand$mcD$sp.map$(this, f);
         }

         public Rand map$mcD$sp(final Function1 f) {
            return Rand$mcD$sp.map$mcD$sp$(this, f);
         }

         public void foreach(final Function1 f) {
            Rand$mcD$sp.foreach$(this, f);
         }

         public void foreach$mcD$sp(final Function1 f) {
            Rand$mcD$sp.foreach$mcD$sp$(this, f);
         }

         public Rand filter(final Function1 p) {
            return Rand$mcD$sp.filter$(this, p);
         }

         public Rand filter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.filter$mcD$sp$(this, p);
         }

         public Rand withFilter(final Function1 p) {
            return Rand$mcD$sp.withFilter$(this, p);
         }

         public Rand withFilter$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.withFilter$mcD$sp$(this, p);
         }

         public Rand condition(final Function1 p) {
            return Rand$mcD$sp.condition$(this, p);
         }

         public Rand condition$mcD$sp(final Function1 p) {
            return Rand$mcD$sp.condition$mcD$sp$(this, p);
         }

         public int draw$mcI$sp() {
            return Rand.draw$mcI$sp$(this);
         }

         public int get$mcI$sp() {
            return Rand.get$mcI$sp$(this);
         }

         public Option drawOpt() {
            return Rand.drawOpt$(this);
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

         public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
            return Rand.samplesVector$mcI$sp$(this, size, m);
         }

         public Rand flatMap$mcI$sp(final Function1 f) {
            return Rand.flatMap$mcI$sp$(this, f);
         }

         public Rand map$mcI$sp(final Function1 f) {
            return Rand.map$mcI$sp$(this, f);
         }

         public void foreach$mcI$sp(final Function1 f) {
            Rand.foreach$mcI$sp$(this, f);
         }

         public Rand filter$mcI$sp(final Function1 p) {
            return Rand.filter$mcI$sp$(this, p);
         }

         public Rand withFilter$mcI$sp(final Function1 p) {
            return Rand.withFilter$mcI$sp$(this, p);
         }

         public Rand condition$mcI$sp(final Function1 p) {
            return Rand.condition$mcI$sp$(this, p);
         }

         public double draw() {
            return this.draw$mcD$sp();
         }

         public double draw$mcD$sp() {
            return this.$outer.generator().nextGaussian();
         }

         public {
            if (RandBasis.this == null) {
               throw null;
            } else {
               this.$outer = RandBasis.this;
               Rand.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
