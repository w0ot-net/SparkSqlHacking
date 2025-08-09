package spire.math;

import algebra.ring.Field;
import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001C\t\u0013!\u0003\r\tA\u0005\f\t\u000b9\u0003A\u0011A(\t\u000bM\u0003a1\u0001+\t\u000bm\u0003a1\u0001/\t\u000b\u0001\u0004a1A1\t\u000b\u0015\u0004a1\u00014\t\u000b)\u0004a1A6\t\u000b=\u0004a1\u00019\t\u000bU\u0004a1\u0001<\t\u000bu\u0004A\u0011\t@\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u00033\u0001A\u0011IA\u000e\u0011\u001d\t9\u0003\u0001C!\u0003SAq!a\f\u0001\t\u0003\ty\u0001C\u0004\u00022\u0001!\t%a\r\u0003\u0013)+G/S:SS:<'BA\n\u0015\u0003\u0011i\u0017\r\u001e5\u000b\u0003U\tQa\u001d9je\u0016,\"aF\u001b\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?1zcB\u0001\u0011*\u001d\t\tsE\u0004\u0002#M5\t1E\u0003\u0002%K\u00051AH]8piz\u001a\u0001!C\u0001\u0016\u0013\tAC#A\u0004bY\u001e,'M]1\n\u0005)Z\u0013a\u00029bG.\fw-\u001a\u0006\u0003QQI!!\f\u0018\u0003\tIKgn\u001a\u0006\u0003U-\u00022\u0001M\u00194\u001b\u0005\u0011\u0012B\u0001\u001a\u0013\u0005\rQU\r\u001e\t\u0003iUb\u0001\u0001B\u00057\u0001\u0001\u0006\t\u0011!b\u0001o\t\tA+\u0005\u00029wA\u0011\u0011$O\u0005\u0003ui\u0011qAT8uQ&tw\r\u0005\u0002\u001ay%\u0011QH\u0007\u0002\u0004\u0003:L\b\u0006B\u001b@\u0005&\u0003\"!\u0007!\n\u0005\u0005S\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\"E\r\u0016s!!\u0007#\n\u0005\u0015S\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013H\u0011nq!A\t%\n\u0003m\tTa\t&L\u001b2s!!G&\n\u00051S\u0012A\u0002#pk\ndW-\r\u0003%\u000f\"[\u0012A\u0002\u0013j]&$H\u0005F\u0001Q!\tI\u0012+\u0003\u0002S5\t!QK\\5u\u0003\u0005\u0019W#A+\u0011\u0007YK6'D\u0001X\u0015\tA&$A\u0004sK\u001adWm\u0019;\n\u0005i;&\u0001C\"mCN\u001cH+Y4\u0002\u0003\u0011,\u0012!\u0018\t\u0003ayK!a\u0018\n\u0003\r)+G\u000fR5n\u0003\t)\u0017/F\u0001c!\ry2mM\u0005\u0003I:\u0012!!R9\u0002\u0003\u0019,\u0012a\u001a\t\u0004?!\u001c\u0014BA5/\u0005\u00151\u0015.\u001a7e\u0003\u0005\u0019X#\u00017\u0011\u0007}i7'\u0003\u0002o]\t11+[4oK\u0012\f\u0011\u0001^\u000b\u0002cB\u0019!o]\u001a\u000e\u0003-J!\u0001^\u0016\u0003\tQ\u0013\u0018nZ\u0001\u0002mV\tq\u000f\u0005\u0003sqj\u001c\u0014BA=,\u0005-1Vm\u0019;peN\u0003\u0018mY3\u0011\u0007eY8'\u0003\u0002}5\t)\u0011I\u001d:bs\u0006)Q.\u001b8vgR!qf`A\u0002\u0011\u0019\t\t!\u0003a\u0001_\u0005\t\u0011\r\u0003\u0004\u0002\u0006%\u0001\raL\u0001\u0002E\u00061a.Z4bi\u0016$2aLA\u0006\u0011\u0019\t\tA\u0003a\u0001_\u0005\u0019qN\\3\u0016\u0003=\nA\u0001\u001d7vgR)q&!\u0006\u0002\u0018!1\u0011\u0011\u0001\u0007A\u0002=Ba!!\u0002\r\u0001\u0004y\u0013a\u00019poR)q&!\b\u0002 !1\u0011\u0011A\u0007A\u0002=Bq!!\u0002\u000e\u0001\u0004\t\t\u0003E\u0002\u001a\u0003GI1!!\n\u001b\u0005\rIe\u000e^\u0001\u0006i&lWm\u001d\u000b\u0006_\u0005-\u0012Q\u0006\u0005\u0007\u0003\u0003q\u0001\u0019A\u0018\t\r\u0005\u0015a\u00021\u00010\u0003\u0011QXM]8\u0002\u000f\u0019\u0014x.\\%oiR\u0019q&!\u000e\t\u000f\u0005]\u0002\u00031\u0001\u0002\"\u0005\ta\u000e"
)
public interface JetIsRing extends Ring {
   ClassTag c();

   JetDim d();

   Eq eq();

   Field f();

   Signed s();

   Trig t();

   VectorSpace v();

   // $FF: synthetic method
   static Jet minus$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.minus(a, b);
   }

   default Jet minus(final Jet a, final Jet b) {
      return a.$minus(b, this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet negate$(final JetIsRing $this, final Jet a) {
      return $this.negate(a);
   }

   default Jet negate(final Jet a) {
      return a.unary_$minus(this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet one$(final JetIsRing $this) {
      return $this.one();
   }

   default Jet one() {
      return Jet$.MODULE$.one(this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Jet plus$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.plus(a, b);
   }

   default Jet plus(final Jet a, final Jet b) {
      return a.$plus(b, this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet pow$(final JetIsRing $this, final Jet a, final int b) {
      return $this.pow(a, b);
   }

   default Jet pow(final Jet a, final int b) {
      return a.pow(b, this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet times$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.times(a, b);
   }

   default Jet times(final Jet a, final Jet b) {
      return a.$times(b, this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet zero$(final JetIsRing $this) {
      return $this.zero();
   }

   default Jet zero() {
      return Jet$.MODULE$.zero(this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Jet fromInt$(final JetIsRing $this, final int n) {
      return $this.fromInt(n);
   }

   default Jet fromInt(final int n) {
      return Jet$.MODULE$.fromInt(n, this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Eq eq$mcD$sp$(final JetIsRing $this) {
      return $this.eq$mcD$sp();
   }

   default Eq eq$mcD$sp() {
      return this.eq();
   }

   // $FF: synthetic method
   static Eq eq$mcF$sp$(final JetIsRing $this) {
      return $this.eq$mcF$sp();
   }

   default Eq eq$mcF$sp() {
      return this.eq();
   }

   // $FF: synthetic method
   static Field f$mcD$sp$(final JetIsRing $this) {
      return $this.f$mcD$sp();
   }

   default Field f$mcD$sp() {
      return this.f();
   }

   // $FF: synthetic method
   static Field f$mcF$sp$(final JetIsRing $this) {
      return $this.f$mcF$sp();
   }

   default Field f$mcF$sp() {
      return this.f();
   }

   // $FF: synthetic method
   static Signed s$mcD$sp$(final JetIsRing $this) {
      return $this.s$mcD$sp();
   }

   default Signed s$mcD$sp() {
      return this.s();
   }

   // $FF: synthetic method
   static Signed s$mcF$sp$(final JetIsRing $this) {
      return $this.s$mcF$sp();
   }

   default Signed s$mcF$sp() {
      return this.s();
   }

   // $FF: synthetic method
   static Trig t$mcD$sp$(final JetIsRing $this) {
      return $this.t$mcD$sp();
   }

   default Trig t$mcD$sp() {
      return this.t();
   }

   // $FF: synthetic method
   static Trig t$mcF$sp$(final JetIsRing $this) {
      return $this.t$mcF$sp();
   }

   default Trig t$mcF$sp() {
      return this.t();
   }

   // $FF: synthetic method
   static VectorSpace v$mcD$sp$(final JetIsRing $this) {
      return $this.v$mcD$sp();
   }

   default VectorSpace v$mcD$sp() {
      return this.v();
   }

   // $FF: synthetic method
   static VectorSpace v$mcF$sp$(final JetIsRing $this) {
      return $this.v$mcF$sp();
   }

   default VectorSpace v$mcF$sp() {
      return this.v();
   }

   // $FF: synthetic method
   static Jet minus$mcD$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.minus$mcD$sp(a, b);
   }

   default Jet minus$mcD$sp(final Jet a, final Jet b) {
      return this.minus(a, b);
   }

   // $FF: synthetic method
   static Jet minus$mcF$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.minus$mcF$sp(a, b);
   }

   default Jet minus$mcF$sp(final Jet a, final Jet b) {
      return this.minus(a, b);
   }

   // $FF: synthetic method
   static Jet negate$mcD$sp$(final JetIsRing $this, final Jet a) {
      return $this.negate$mcD$sp(a);
   }

   default Jet negate$mcD$sp(final Jet a) {
      return this.negate(a);
   }

   // $FF: synthetic method
   static Jet negate$mcF$sp$(final JetIsRing $this, final Jet a) {
      return $this.negate$mcF$sp(a);
   }

   default Jet negate$mcF$sp(final Jet a) {
      return this.negate(a);
   }

   // $FF: synthetic method
   static Jet one$mcD$sp$(final JetIsRing $this) {
      return $this.one$mcD$sp();
   }

   default Jet one$mcD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Jet one$mcF$sp$(final JetIsRing $this) {
      return $this.one$mcF$sp();
   }

   default Jet one$mcF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Jet plus$mcD$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.plus$mcD$sp(a, b);
   }

   default Jet plus$mcD$sp(final Jet a, final Jet b) {
      return this.plus(a, b);
   }

   // $FF: synthetic method
   static Jet plus$mcF$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.plus$mcF$sp(a, b);
   }

   default Jet plus$mcF$sp(final Jet a, final Jet b) {
      return this.plus(a, b);
   }

   // $FF: synthetic method
   static Jet pow$mcD$sp$(final JetIsRing $this, final Jet a, final int b) {
      return $this.pow$mcD$sp(a, b);
   }

   default Jet pow$mcD$sp(final Jet a, final int b) {
      return this.pow(a, b);
   }

   // $FF: synthetic method
   static Jet pow$mcF$sp$(final JetIsRing $this, final Jet a, final int b) {
      return $this.pow$mcF$sp(a, b);
   }

   default Jet pow$mcF$sp(final Jet a, final int b) {
      return this.pow(a, b);
   }

   // $FF: synthetic method
   static Jet times$mcD$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.times$mcD$sp(a, b);
   }

   default Jet times$mcD$sp(final Jet a, final Jet b) {
      return this.times(a, b);
   }

   // $FF: synthetic method
   static Jet times$mcF$sp$(final JetIsRing $this, final Jet a, final Jet b) {
      return $this.times$mcF$sp(a, b);
   }

   default Jet times$mcF$sp(final Jet a, final Jet b) {
      return this.times(a, b);
   }

   // $FF: synthetic method
   static Jet zero$mcD$sp$(final JetIsRing $this) {
      return $this.zero$mcD$sp();
   }

   default Jet zero$mcD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Jet zero$mcF$sp$(final JetIsRing $this) {
      return $this.zero$mcF$sp();
   }

   default Jet zero$mcF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Jet fromInt$mcD$sp$(final JetIsRing $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default Jet fromInt$mcD$sp(final int n) {
      return this.fromInt(n);
   }

   // $FF: synthetic method
   static Jet fromInt$mcF$sp$(final JetIsRing $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default Jet fromInt$mcF$sp(final int n) {
      return this.fromInt(n);
   }

   static void $init$(final JetIsRing $this) {
   }
}
