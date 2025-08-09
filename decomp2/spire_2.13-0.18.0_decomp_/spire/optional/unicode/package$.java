package spire.optional.unicode;

import algebra.lattice.Bool;
import algebra.lattice.Heyting;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.MeetSemilattice;
import algebra.ring.AdditiveMonoid;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.immutable.Set;
import spire.algebra.NRoot;
import spire.math.Complex;
import spire.math.Complex$;
import spire.math.Natural$;
import spire.math.Quaternion;
import spire.math.Quaternion$;
import spire.math.Rational$;
import spire.math.Real;
import spire.math.Real$;
import spire.math.SafeLong$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final Real$ ℝ;
   private static final Rational$ ℚ;
   private static final SafeLong$ ℤ;
   private static final Natural$ ℕ;
   private static final Real ⅇ;
   private static final Real π;
   private static final Real φ;
   private static final Complex ⅈ;
   private static final Quaternion ⅉ;

   static {
      ℝ = Real$.MODULE$;
      ℚ = Rational$.MODULE$;
      ℤ = SafeLong$.MODULE$;
      ℕ = Natural$.MODULE$;
      ⅇ = Real$.MODULE$.e();
      π = Real$.MODULE$.pi();
      φ = Real$.MODULE$.phi();
      ⅈ = Complex$.MODULE$.i(Real$.MODULE$.algebra());
      ⅉ = Quaternion$.MODULE$.j(Real$.MODULE$.algebra());
   }

   public Real$ ℝ() {
      return ℝ;
   }

   public Rational$ ℚ() {
      return ℚ;
   }

   public SafeLong$ ℤ() {
      return ℤ;
   }

   public Natural$ ℕ() {
      return ℕ;
   }

   public Real ⅇ() {
      return ⅇ;
   }

   public Real π() {
      return π;
   }

   public Real φ() {
      return φ;
   }

   public Complex ⅈ() {
      return ⅈ;
   }

   public Quaternion ⅉ() {
      return ⅉ;
   }

   public Object $u22A4(final Heyting ev) {
      return ev.one();
   }

   public Object $u22A5(final Heyting ev) {
      return ev.zero();
   }

   public Object $u00AC(final Object a, final Heyting ev) {
      return ev.complement(a);
   }

   public Object $u221A(final Object a, final NRoot ev) {
      return ev.sqrt(a);
   }

   public Object $u221B(final Object a, final NRoot ev) {
      return ev.nroot(a, 3);
   }

   public Object $u221C(final Object a, final NRoot ev) {
      return ev.nroot(a, 4);
   }

   public Object Σ(final Iterable as, final AdditiveMonoid ev) {
      return as.foldLeft(ev.zero(), (x, y) -> ev.plus(x, y));
   }

   public Object Π(final Iterable as, final MultiplicativeMonoid ev) {
      return as.foldLeft(ev.one(), (x, y) -> ev.times(x, y));
   }

   public package.TimesOp TimesOp(final Object lhs, final MultiplicativeSemigroup ev) {
      return new package.TimesOp(lhs, ev);
   }

   public package.EqOps EqOps(final Object lhs, final Eq ev) {
      return new package.EqOps(lhs, ev);
   }

   public package.PartialOrderOps PartialOrderOps(final Object lhs, final PartialOrder ev) {
      return new package.PartialOrderOps(lhs, ev);
   }

   public package.MeetSemilatticeOps MeetSemilatticeOps(final Object lhs, final MeetSemilattice ev) {
      return new package.MeetSemilatticeOps(lhs, ev);
   }

   public package.JoinSemilatticeOps JoinSemilatticeOps(final Object lhs, final JoinSemilattice ev) {
      return new package.JoinSemilatticeOps(lhs, ev);
   }

   public package.HeytingOps HeytingOps(final Object lhs, final Heyting ev) {
      return new package.HeytingOps(lhs, ev);
   }

   public package.BoolOps BoolOps(final Object lhs, final Bool ev) {
      return new package.BoolOps(lhs, ev);
   }

   public Set SymbolicSetOps(final Set lhs) {
      return lhs;
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
