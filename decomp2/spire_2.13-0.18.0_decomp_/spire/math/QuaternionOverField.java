package spire.math;

import algebra.ring.DivisionRing;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.FieldAssociativeAlgebra;
import spire.algebra.Involution;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001C\t\u0013!\u0003\r\tA\u0005\f\t\u000b!\u0003A\u0011A%\t\u000b5\u0003a1\u0001(\t\u000bI\u0003a1A*\t\u000b]\u0003A\u0011\u0001-\t\u000b\u0001\u0004A\u0011I1\t\u000b\u0011\u0004A\u0011I3\t\u000b)\u0004A\u0011A6\t\u000b5\u0004A\u0011\u00018\t\u000b=\u0004A\u0011\u00019\t\u000bM\u0004A\u0011\t;\t\u000bi\u0004A\u0011I>\t\u000by\u0004A\u0011\u00018\t\r}\u0004A\u0011AA\u0001\u0011\u001d\t9\u0001\u0001C\u0001\u0003\u0013Aq!!\u0005\u0001\t\u0003\t\u0019\u0002C\u0004\u0002\u001a\u0001!\t!a\u0007\u0003'E+\u0018\r^3s]&|gn\u0014<fe\u001aKW\r\u001c3\u000b\u0005M!\u0012\u0001B7bi\"T\u0011!F\u0001\u0006gBL'/Z\u000b\u0003/U\u001ab\u0001\u0001\r\u001f}\u0005+\u0005CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g\rE\u0002 Y=r!\u0001I\u0015\u000f\u0005\u0005:cB\u0001\u0012'\u001b\u0005\u0019#B\u0001\u0013&\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u000b\n\u0005!\"\u0012aB1mO\u0016\u0014'/Y\u0005\u0003U-\nq\u0001]1dW\u0006<WM\u0003\u0002))%\u0011QF\f\u0002\u0003\u000bFT!AK\u0016\u0011\u0007A\n4'D\u0001\u0013\u0013\t\u0011$C\u0001\u0006Rk\u0006$XM\u001d8j_:\u0004\"\u0001N\u001b\r\u0001\u0011)a\u0007\u0001b\u0001o\t\t\u0011)\u0005\u00029wA\u0011\u0011$O\u0005\u0003ui\u0011qAT8uQ&tw\r\u0005\u0002\u001ay%\u0011QH\u0007\u0002\u0004\u0003:L\bcA\u0010@_%\u0011\u0001I\f\u0002\r\t&4\u0018n]5p]JKgn\u001a\t\u0005\u0005\u000e{3'D\u0001,\u0013\t!5FA\fGS\u0016dG-Q:t_\u000eL\u0017\r^5wK\u0006cw-\u001a2sCB\u0019!IR\u0018\n\u0005\u001d[#AC%om>dW\u000f^5p]\u00061A%\u001b8ji\u0012\"\u0012A\u0013\t\u00033-K!\u0001\u0014\u000e\u0003\tUs\u0017\u000e^\u0001\u0002_V\tq\nE\u0002 !NJ!!\u0015\u0018\u0003\u000b=\u0013H-\u001a:\u0002\u0003M,\u0012\u0001\u0016\t\u0004?U\u001b\u0014B\u0001,/\u0005\u0019\u0019\u0016n\u001a8fI\u0006\u0019Q-\u001d<\u0015\u0007ecf\f\u0005\u0002\u001a5&\u00111L\u0007\u0002\b\u0005>|G.Z1o\u0011\u0015iF\u00011\u00010\u0003\u0005A\b\"B0\u0005\u0001\u0004y\u0013!A=\u0002\t9,\u0017O\u001e\u000b\u00043\n\u001c\u0007\"B/\u0006\u0001\u0004y\u0003\"B0\u0006\u0001\u0004y\u0013!B7j]V\u001cHcA\u0018gQ\")qM\u0002a\u0001_\u0005\t\u0011\rC\u0003j\r\u0001\u0007q&A\u0001c\u0003\u0019qWmZ1uKR\u0011q\u0006\u001c\u0005\u0006O\u001e\u0001\raL\u0001\u0004_:,W#A\u0018\u0002\tAdWo\u001d\u000b\u0004_E\u0014\b\"B4\n\u0001\u0004y\u0003\"B5\n\u0001\u0004y\u0013a\u00019poR\u0019q&\u001e<\t\u000b\u001dT\u0001\u0019A\u0018\t\u000b%T\u0001\u0019A<\u0011\u0005eA\u0018BA=\u001b\u0005\rIe\u000e^\u0001\u0006i&lWm\u001d\u000b\u0004_ql\b\"B4\f\u0001\u0004y\u0003\"B5\f\u0001\u0004y\u0013\u0001\u0002>fe>\f1\u0001Z5w)\u0015y\u00131AA\u0003\u0011\u00159W\u00021\u00010\u0011\u0015IW\u00021\u00010\u0003\u0019!\u0018.\\3tYR)q&a\u0003\u0002\u000e!)qM\u0004a\u0001g!1\u0011q\u0002\bA\u0002=\n\u0011!]\u0001\u0004I>$H#B\u001a\u0002\u0016\u0005]\u0001\"B/\u0010\u0001\u0004y\u0003\"B0\u0010\u0001\u0004y\u0013aB1eU>Lg\u000e\u001e\u000b\u0004_\u0005u\u0001\"B4\u0011\u0001\u0004y\u0003"
)
public interface QuaternionOverField extends Eq, DivisionRing, FieldAssociativeAlgebra, Involution {
   Order o();

   Signed s();

   // $FF: synthetic method
   static boolean eqv$(final QuaternionOverField $this, final Quaternion x, final Quaternion y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Quaternion x, final Quaternion y) {
      return BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final QuaternionOverField $this, final Quaternion x, final Quaternion y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Quaternion x, final Quaternion y) {
      return !BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static Quaternion minus$(final QuaternionOverField $this, final Quaternion a, final Quaternion b) {
      return $this.minus(a, b);
   }

   default Quaternion minus(final Quaternion a, final Quaternion b) {
      return a.$minus((Quaternion)b, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion negate$(final QuaternionOverField $this, final Quaternion a) {
      return $this.negate(a);
   }

   default Quaternion negate(final Quaternion a) {
      return a.unary_$minus(this.scalar());
   }

   // $FF: synthetic method
   static Quaternion one$(final QuaternionOverField $this) {
      return $this.one();
   }

   default Quaternion one() {
      return Quaternion$.MODULE$.one(this.scalar());
   }

   // $FF: synthetic method
   static Quaternion plus$(final QuaternionOverField $this, final Quaternion a, final Quaternion b) {
      return $this.plus(a, b);
   }

   default Quaternion plus(final Quaternion a, final Quaternion b) {
      return a.$plus((Quaternion)b, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion pow$(final QuaternionOverField $this, final Quaternion a, final int b) {
      return $this.pow(a, b);
   }

   default Quaternion pow(final Quaternion a, final int b) {
      return a.pow(b, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion times$(final QuaternionOverField $this, final Quaternion a, final Quaternion b) {
      return $this.times(a, b);
   }

   default Quaternion times(final Quaternion a, final Quaternion b) {
      return a.$times((Quaternion)b, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion zero$(final QuaternionOverField $this) {
      return $this.zero();
   }

   default Quaternion zero() {
      return Quaternion$.MODULE$.zero(this.scalar());
   }

   // $FF: synthetic method
   static Quaternion div$(final QuaternionOverField $this, final Quaternion a, final Quaternion b) {
      return $this.div(a, b);
   }

   default Quaternion div(final Quaternion a, final Quaternion b) {
      return a.$div(b, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion timesl$(final QuaternionOverField $this, final Object a, final Quaternion q) {
      return $this.timesl(a, q);
   }

   default Quaternion timesl(final Object a, final Quaternion q) {
      return q.$times((Object)a, this.scalar());
   }

   // $FF: synthetic method
   static Object dot$(final QuaternionOverField $this, final Quaternion x, final Quaternion y) {
      return $this.dot(x, y);
   }

   default Object dot(final Quaternion x, final Quaternion y) {
      return x.dot(y, this.scalar());
   }

   // $FF: synthetic method
   static Quaternion adjoint$(final QuaternionOverField $this, final Quaternion a) {
      return $this.adjoint(a);
   }

   default Quaternion adjoint(final Quaternion a) {
      return a.conjugate(this.scalar());
   }

   static void $init$(final QuaternionOverField $this) {
   }
}
