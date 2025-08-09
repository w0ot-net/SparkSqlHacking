package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}3A!\u0002\u0004\u0001\u0017!A1\t\u0001B\u0002B\u0003-A\t\u0003\u0005F\u0001\t\u0005\t\u0015a\u0003G\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0005-\u0019V-\u001d,fGR|'/R9\u000b\u0005\u001dA\u0011aA:uI*\t\u0011\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00071\u0011Te\u0005\u0003\u0001\u001bMY\u0004C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015A\rr!!F\u000f\u000f\u0005YYbBA\f\u001b\u001b\u0005A\"BA\r\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002\u001d\u0011\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0010 \u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\b\u0005\n\u0005\u0005\u0012#AA#r\u0015\tqr\u0004\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#AA*B#\tA3\u0006\u0005\u0002\u000fS%\u0011!f\u0004\u0002\b\u001d>$\b.\u001b8h!\u0015as&\r\u001d$\u001b\u0005i#B\u0001\u0018\u0010\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003a5\u0012aaU3r\u001fB\u001c\bC\u0001\u00133\t\u0015\u0019\u0004A1\u00015\u0005\u0005\t\u0015C\u0001\u00156!\tqa'\u0003\u00028\u001f\t\u0019\u0011I\\=\u0011\u00051J\u0014B\u0001\u001e.\u0005\r\u0019V-\u001d\t\u0003y\u0001s!!P \u000f\u0005]q\u0014\"\u0001\t\n\u0005yy\u0011BA!C\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tqr\"A\u0006fm&$WM\\2fIE\n\u0004c\u0001\u000b!c\u000511oY1mCJ\u00042\u0001F$2\u0013\tA%E\u0001\bBI\u0012LG/\u001b<f\u001b>tw.\u001b3\u0002\rqJg.\u001b;?)\u0005YEc\u0001'O\u001fB!Q\nA\u0019$\u001b\u00051\u0001\"B\"\u0004\u0001\b!\u0005\"B#\u0004\u0001\b1\u0015aA3rmR\u0019!+V,\u0011\u00059\u0019\u0016B\u0001+\u0010\u0005\u001d\u0011un\u001c7fC:DQA\u0016\u0003A\u0002\r\n\u0011\u0001\u001f\u0005\u00061\u0012\u0001\raI\u0001\u0002s\"\"\u0001AW/_!\tq1,\u0003\u0002]\u001f\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0001\u0001"
)
public class SeqVectorEq implements Eq {
   private static final long serialVersionUID = 0L;
   private final Eq evidence$11;
   private final AdditiveMonoid scalar;

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Eq.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Eq.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Eq.eqv$mcD$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Eq.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Eq.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Eq.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Eq.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.eqv$mcV$sp$(this, x, y);
   }

   public boolean neqv(final Object x, final Object y) {
      return Eq.neqv$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Eq.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Eq.neqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Eq.neqv$mcD$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return Eq.neqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return Eq.neqv$mcI$sp$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return Eq.neqv$mcJ$sp$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return Eq.neqv$mcS$sp$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.neqv$mcV$sp$(this, x, y);
   }

   public boolean eqv(final SeqOps x, final SeqOps y) {
      return SeqSupport$.MODULE$.forall((SeqOps)x, (SeqOps)y, (x$5, x$6) -> BoxesRunTime.boxToBoolean($anonfun$eqv$2(this, x$5, x$6)), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$eqv$3(this, x$7)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$2(final SeqVectorEq $this, final Object x$5, final Object x$6) {
      return spire.algebra.package$.MODULE$.Eq().apply($this.evidence$11).eqv(x$5, x$6);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$3(final SeqVectorEq $this, final Object x$7) {
      return spire.algebra.package$.MODULE$.Eq().apply($this.evidence$11).eqv(x$7, $this.scalar.zero());
   }

   public SeqVectorEq(final Eq evidence$11, final AdditiveMonoid scalar) {
      this.evidence$11 = evidence$11;
      this.scalar = scalar;
      Eq.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
