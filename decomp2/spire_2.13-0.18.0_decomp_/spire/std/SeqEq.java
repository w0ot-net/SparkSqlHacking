package spire.std;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3A\u0001B\u0003\u0001\u0015!A!\t\u0001B\u0002B\u0003-1\tC\u0003E\u0001\u0011\u0005Q\tC\u0003K\u0001\u0011\u00051JA\u0003TKF,\u0015O\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001!F\u0002\fc\u0011\u001aB\u0001\u0001\u0007\u0013uA\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042aE\u0010#\u001d\t!BD\u0004\u0002\u001659\u0011a#G\u0007\u0002/)\u0011\u0001$C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!aG\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QDH\u0001\ba\u0006\u001c7.Y4f\u0015\tYr!\u0003\u0002!C\t\u0011Q)\u001d\u0006\u0003;y\u0001\"a\t\u0013\r\u0001\u0011)Q\u0005\u0001b\u0001M\t\u00111+Q\t\u0003O)\u0002\"!\u0004\u0015\n\u0005%r!a\u0002(pi\"Lgn\u001a\t\u0006W9\u0002tGI\u0007\u0002Y)\u0011QFD\u0001\u000bG>dG.Z2uS>t\u0017BA\u0018-\u0005\u0019\u0019V-](qgB\u00111%\r\u0003\u0006e\u0001\u0011\ra\r\u0002\u0002\u0003F\u0011q\u0005\u000e\t\u0003\u001bUJ!A\u000e\b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002,q%\u0011\u0011\b\f\u0002\u0004'\u0016\f\bCA\u001e@\u001d\tadH\u0004\u0002\u0017{%\tq\"\u0003\u0002\u001e\u001d%\u0011\u0001)\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003;9\t!\"\u001a<jI\u0016t7-\u001a\u0013:!\r\u0019r\u0004M\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0019#\"aR%\u0011\t!\u0003\u0001GI\u0007\u0002\u000b!)!I\u0001a\u0002\u0007\u0006\u0019Q-\u001d<\u0015\u00071{\u0015\u000b\u0005\u0002\u000e\u001b&\u0011aJ\u0004\u0002\b\u0005>|G.Z1o\u0011\u0015\u00016\u00011\u0001#\u0003\u0005A\b\"\u0002*\u0004\u0001\u0004\u0011\u0013!A=)\t\u0001!v\u000b\u0017\t\u0003\u001bUK!A\u0016\b\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001"
)
public class SeqEq implements Eq {
   private static final long serialVersionUID = 0L;
   private final Eq evidence$9;

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
      Function2 x$3 = (x$3x, x$4x) -> BoxesRunTime.boxToBoolean($anonfun$eqv$1(this, x$3x, x$4x));
      Function1 x$4 = SeqSupport$.MODULE$.forall$default$4(x, y);
      return SeqSupport$.MODULE$.forall(x, y, x$3, x$4);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$1(final SeqEq $this, final Object x$3, final Object x$4) {
      return spire.algebra.package$.MODULE$.Eq().apply($this.evidence$9).eqv(x$3, x$4);
   }

   public SeqEq(final Eq evidence$9) {
      this.evidence$9 = evidence$9;
      Eq.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
