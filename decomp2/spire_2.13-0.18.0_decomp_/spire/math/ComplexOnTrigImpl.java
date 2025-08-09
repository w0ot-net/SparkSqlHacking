package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.Involution;
import spire.algebra.LeftModule;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma!B\u0007\u000f\u00019\u0011\u0002\u0002\u0003&\u0001\u0005\u000b\u0007I1A&\t\u0011a\u0003!\u0011!Q\u0001\n1C\u0001\"\u0017\u0001\u0003\u0006\u0004%\u0019A\u0017\u0005\t?\u0002\u0011\t\u0011)A\u00057\"A\u0001\r\u0001BC\u0002\u0013\r\u0011\r\u0003\u0005f\u0001\t\u0005\t\u0015!\u0003c\u0011!1\u0007A!b\u0001\n\u00079\u0007\u0002C6\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u00111\u0004!Q1A\u0005\u00045D\u0001\"\u001d\u0001\u0003\u0002\u0003\u0006IA\u001c\u0005\u0006e\u0002!\ta\u001d\u0005\u0006w\u0002!\t\u0005 \u0002\u0012\u0007>l\u0007\u000f\\3y\u001f:$&/[4J[Bd'BA\b\u0011\u0003\u0011i\u0017\r\u001e5\u000b\u0003E\tQa\u001d9je\u0016,\"a\u0005\u0011\u0014\r\u0001!\"$\u0010!D!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00191\u0004\b\u0010\u000e\u00039I!!\b\b\u0003\u001d\r{W\u000e\u001d7fq>sg)[3mIB\u0011q\u0004\t\u0007\u0001\t%\t\u0003\u0001)A\u0001\u0002\u000b\u00071EA\u0001B\u0007\u0001\t\"\u0001J\u0014\u0011\u0005U)\u0013B\u0001\u0014\u0017\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0006\u0015\n\u0005%2\"aA!os\"\"\u0001e\u000b\u00189!\t)B&\u0003\u0002.-\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019s\u0006\r\u001a2\u001d\t)\u0002'\u0003\u00022-\u0005)a\t\\8biF\"AeM\u001c\u0018\u001d\t!t'D\u00016\u0015\t1$%\u0001\u0004=e>|GOP\u0005\u0002/E*1%\u000f\u001e=w9\u0011QCO\u0005\u0003wY\ta\u0001R8vE2,\u0017\u0007\u0002\u00134o]\u00012a\u0007 \u001f\u0013\tydBA\u0007D_6\u0004H.\u001a=P]R\u0013\u0018n\u001a\t\u00047\u0005s\u0012B\u0001\"\u000f\u00059\u0019u.\u001c9mKbL5O\u0014*p_R\u0004\"\u0001R$\u000f\u0005M*\u0015B\u0001$\u0017\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001S%\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u00193\u0012AB:dC2\f'/F\u0001M!\riUK\b\b\u0003\u001dNs!aT)\u000f\u0005Q\u0002\u0016\"A\t\n\u0005I\u0003\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\rRS!A\u0015\t\n\u0005Y;&!\u0002$jK2$'B\u0001$U\u0003\u001d\u00198-\u00197be\u0002\nQA\u001c:p_R,\u0012a\u0017\t\u00049vsR\"\u0001+\n\u0005y#&!\u0002(S_>$\u0018A\u00028s_>$\b%A\u0003pe\u0012,'/F\u0001c!\ri5MH\u0005\u0003I^\u0013Qa\u0014:eKJ\faa\u001c:eKJ\u0004\u0013\u0001\u0002;sS\u001e,\u0012\u0001\u001b\t\u00049&t\u0012B\u00016U\u0005\u0011!&/[4\u0002\u000bQ\u0014\u0018n\u001a\u0011\u0002\rMLwM\\3e+\u0005q\u0007cA'p=%\u0011\u0001o\u0016\u0002\u0007'&<g.\u001a3\u0002\u000fMLwM\\3eA\u00051A(\u001b8jiz\"\u0012\u0001\u001e\u000b\u0007kZ<\b0\u001f>\u0011\u0007m\u0001a\u0004C\u0003K\u0017\u0001\u000fA\nC\u0003Z\u0017\u0001\u000f1\fC\u0003a\u0017\u0001\u000f!\rC\u0003g\u0017\u0001\u000f\u0001\u000eC\u0003m\u0017\u0001\u000fa.A\u0002q_^$R!`A\u0001\u0003\u000b\u00012a\u0007@\u001f\u0013\tyhBA\u0004D_6\u0004H.\u001a=\t\r\u0005\rA\u00021\u0001~\u0003\u0005\t\u0007bBA\u0004\u0019\u0001\u0007\u0011\u0011B\u0001\u0002EB\u0019Q#a\u0003\n\u0007\u00055aCA\u0002J]RDs\u0001AA\t\u0003/\tI\u0002E\u0002\u0016\u0003'I1!!\u0006\u0017\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0001"
)
public class ComplexOnTrigImpl implements ComplexOnField, ComplexOnTrig, ComplexIsNRoot {
   private static final long serialVersionUID = 1L;
   public final Field scalar;
   public final NRoot nroot;
   public final Order order;
   public final Trig trig;
   public final Signed signed;

   public Complex nroot(final Complex a, final int k) {
      return ComplexIsNRoot.nroot$(this, a, k);
   }

   public Complex sqrt(final Complex a) {
      return ComplexIsNRoot.sqrt$(this, a);
   }

   public Complex fpow(final Complex a, final Complex b) {
      return ComplexIsNRoot.fpow$(this, a, b);
   }

   public double nroot$mcD$sp(final double a, final int n) {
      return NRoot.nroot$mcD$sp$(this, a, n);
   }

   public float nroot$mcF$sp(final float a, final int n) {
      return NRoot.nroot$mcF$sp$(this, a, n);
   }

   public int nroot$mcI$sp(final int a, final int n) {
      return NRoot.nroot$mcI$sp$(this, a, n);
   }

   public long nroot$mcJ$sp(final long a, final int n) {
      return NRoot.nroot$mcJ$sp$(this, a, n);
   }

   public double sqrt$mcD$sp(final double a) {
      return NRoot.sqrt$mcD$sp$(this, a);
   }

   public float sqrt$mcF$sp(final float a) {
      return NRoot.sqrt$mcF$sp$(this, a);
   }

   public int sqrt$mcI$sp(final int a) {
      return NRoot.sqrt$mcI$sp$(this, a);
   }

   public long sqrt$mcJ$sp(final long a) {
      return NRoot.sqrt$mcJ$sp$(this, a);
   }

   public double fpow$mcD$sp(final double a, final double b) {
      return NRoot.fpow$mcD$sp$(this, a, b);
   }

   public float fpow$mcF$sp(final float a, final float b) {
      return NRoot.fpow$mcF$sp$(this, a, b);
   }

   public int fpow$mcI$sp(final int a, final int b) {
      return NRoot.fpow$mcI$sp$(this, a, b);
   }

   public long fpow$mcJ$sp(final long a, final long b) {
      return NRoot.fpow$mcJ$sp$(this, a, b);
   }

   public Complex e() {
      return ComplexOnTrig.e$(this);
   }

   public Complex e$mcD$sp() {
      return ComplexOnTrig.e$mcD$sp$(this);
   }

   public Complex e$mcF$sp() {
      return ComplexOnTrig.e$mcF$sp$(this);
   }

   public Complex pi() {
      return ComplexOnTrig.pi$(this);
   }

   public Complex pi$mcD$sp() {
      return ComplexOnTrig.pi$mcD$sp$(this);
   }

   public Complex pi$mcF$sp() {
      return ComplexOnTrig.pi$mcF$sp$(this);
   }

   public Complex exp(final Complex a) {
      return ComplexOnTrig.exp$(this, a);
   }

   public Complex exp$mcD$sp(final Complex a) {
      return ComplexOnTrig.exp$mcD$sp$(this, a);
   }

   public Complex exp$mcF$sp(final Complex a) {
      return ComplexOnTrig.exp$mcF$sp$(this, a);
   }

   public Complex expm1(final Complex a) {
      return ComplexOnTrig.expm1$(this, a);
   }

   public Complex expm1$mcD$sp(final Complex a) {
      return ComplexOnTrig.expm1$mcD$sp$(this, a);
   }

   public Complex expm1$mcF$sp(final Complex a) {
      return ComplexOnTrig.expm1$mcF$sp$(this, a);
   }

   public Complex log(final Complex a) {
      return ComplexOnTrig.log$(this, a);
   }

   public Complex log$mcD$sp(final Complex a) {
      return ComplexOnTrig.log$mcD$sp$(this, a);
   }

   public Complex log$mcF$sp(final Complex a) {
      return ComplexOnTrig.log$mcF$sp$(this, a);
   }

   public Complex log1p(final Complex a) {
      return ComplexOnTrig.log1p$(this, a);
   }

   public Complex log1p$mcD$sp(final Complex a) {
      return ComplexOnTrig.log1p$mcD$sp$(this, a);
   }

   public Complex log1p$mcF$sp(final Complex a) {
      return ComplexOnTrig.log1p$mcF$sp$(this, a);
   }

   public Complex sin(final Complex a) {
      return ComplexOnTrig.sin$(this, a);
   }

   public Complex sin$mcD$sp(final Complex a) {
      return ComplexOnTrig.sin$mcD$sp$(this, a);
   }

   public Complex sin$mcF$sp(final Complex a) {
      return ComplexOnTrig.sin$mcF$sp$(this, a);
   }

   public Complex cos(final Complex a) {
      return ComplexOnTrig.cos$(this, a);
   }

   public Complex cos$mcD$sp(final Complex a) {
      return ComplexOnTrig.cos$mcD$sp$(this, a);
   }

   public Complex cos$mcF$sp(final Complex a) {
      return ComplexOnTrig.cos$mcF$sp$(this, a);
   }

   public Complex tan(final Complex a) {
      return ComplexOnTrig.tan$(this, a);
   }

   public Complex tan$mcD$sp(final Complex a) {
      return ComplexOnTrig.tan$mcD$sp$(this, a);
   }

   public Complex tan$mcF$sp(final Complex a) {
      return ComplexOnTrig.tan$mcF$sp$(this, a);
   }

   public Complex asin(final Complex a) {
      return ComplexOnTrig.asin$(this, a);
   }

   public Complex asin$mcD$sp(final Complex a) {
      return ComplexOnTrig.asin$mcD$sp$(this, a);
   }

   public Complex asin$mcF$sp(final Complex a) {
      return ComplexOnTrig.asin$mcF$sp$(this, a);
   }

   public Complex acos(final Complex a) {
      return ComplexOnTrig.acos$(this, a);
   }

   public Complex acos$mcD$sp(final Complex a) {
      return ComplexOnTrig.acos$mcD$sp$(this, a);
   }

   public Complex acos$mcF$sp(final Complex a) {
      return ComplexOnTrig.acos$mcF$sp$(this, a);
   }

   public Complex atan(final Complex a) {
      return ComplexOnTrig.atan$(this, a);
   }

   public Complex atan$mcD$sp(final Complex a) {
      return ComplexOnTrig.atan$mcD$sp$(this, a);
   }

   public Complex atan$mcF$sp(final Complex a) {
      return ComplexOnTrig.atan$mcF$sp$(this, a);
   }

   public Complex atan2(final Complex y, final Complex x) {
      return ComplexOnTrig.atan2$(this, y, x);
   }

   public Complex atan2$mcD$sp(final Complex y, final Complex x) {
      return ComplexOnTrig.atan2$mcD$sp$(this, y, x);
   }

   public Complex atan2$mcF$sp(final Complex y, final Complex x) {
      return ComplexOnTrig.atan2$mcF$sp$(this, y, x);
   }

   public Complex sinh(final Complex x) {
      return ComplexOnTrig.sinh$(this, x);
   }

   public Complex sinh$mcD$sp(final Complex x) {
      return ComplexOnTrig.sinh$mcD$sp$(this, x);
   }

   public Complex sinh$mcF$sp(final Complex x) {
      return ComplexOnTrig.sinh$mcF$sp$(this, x);
   }

   public Complex cosh(final Complex x) {
      return ComplexOnTrig.cosh$(this, x);
   }

   public Complex cosh$mcD$sp(final Complex x) {
      return ComplexOnTrig.cosh$mcD$sp$(this, x);
   }

   public Complex cosh$mcF$sp(final Complex x) {
      return ComplexOnTrig.cosh$mcF$sp$(this, x);
   }

   public Complex tanh(final Complex x) {
      return ComplexOnTrig.tanh$(this, x);
   }

   public Complex tanh$mcD$sp(final Complex x) {
      return ComplexOnTrig.tanh$mcD$sp$(this, x);
   }

   public Complex tanh$mcF$sp(final Complex x) {
      return ComplexOnTrig.tanh$mcF$sp$(this, x);
   }

   public Complex toRadians(final Complex a) {
      return ComplexOnTrig.toRadians$(this, a);
   }

   public Complex toRadians$mcD$sp(final Complex a) {
      return ComplexOnTrig.toRadians$mcD$sp$(this, a);
   }

   public Complex toRadians$mcF$sp(final Complex a) {
      return ComplexOnTrig.toRadians$mcF$sp$(this, a);
   }

   public Complex toDegrees(final Complex a) {
      return ComplexOnTrig.toDegrees$(this, a);
   }

   public Complex toDegrees$mcD$sp(final Complex a) {
      return ComplexOnTrig.toDegrees$mcD$sp$(this, a);
   }

   public Complex toDegrees$mcF$sp(final Complex a) {
      return ComplexOnTrig.toDegrees$mcF$sp$(this, a);
   }

   public double exp$mcD$sp(final double a) {
      return Trig.exp$mcD$sp$(this, a);
   }

   public float exp$mcF$sp(final float a) {
      return Trig.exp$mcF$sp$(this, a);
   }

   public double expm1$mcD$sp(final double a) {
      return Trig.expm1$mcD$sp$(this, a);
   }

   public float expm1$mcF$sp(final float a) {
      return Trig.expm1$mcF$sp$(this, a);
   }

   public double log$mcD$sp(final double a) {
      return Trig.log$mcD$sp$(this, a);
   }

   public float log$mcF$sp(final float a) {
      return Trig.log$mcF$sp$(this, a);
   }

   public double log1p$mcD$sp(final double a) {
      return Trig.log1p$mcD$sp$(this, a);
   }

   public float log1p$mcF$sp(final float a) {
      return Trig.log1p$mcF$sp$(this, a);
   }

   public double sin$mcD$sp(final double a) {
      return Trig.sin$mcD$sp$(this, a);
   }

   public float sin$mcF$sp(final float a) {
      return Trig.sin$mcF$sp$(this, a);
   }

   public double cos$mcD$sp(final double a) {
      return Trig.cos$mcD$sp$(this, a);
   }

   public float cos$mcF$sp(final float a) {
      return Trig.cos$mcF$sp$(this, a);
   }

   public double tan$mcD$sp(final double a) {
      return Trig.tan$mcD$sp$(this, a);
   }

   public float tan$mcF$sp(final float a) {
      return Trig.tan$mcF$sp$(this, a);
   }

   public double asin$mcD$sp(final double a) {
      return Trig.asin$mcD$sp$(this, a);
   }

   public float asin$mcF$sp(final float a) {
      return Trig.asin$mcF$sp$(this, a);
   }

   public double acos$mcD$sp(final double a) {
      return Trig.acos$mcD$sp$(this, a);
   }

   public float acos$mcF$sp(final float a) {
      return Trig.acos$mcF$sp$(this, a);
   }

   public double atan$mcD$sp(final double a) {
      return Trig.atan$mcD$sp$(this, a);
   }

   public float atan$mcF$sp(final float a) {
      return Trig.atan$mcF$sp$(this, a);
   }

   public double atan2$mcD$sp(final double y, final double x) {
      return Trig.atan2$mcD$sp$(this, y, x);
   }

   public float atan2$mcF$sp(final float y, final float x) {
      return Trig.atan2$mcF$sp$(this, y, x);
   }

   public double sinh$mcD$sp(final double x) {
      return Trig.sinh$mcD$sp$(this, x);
   }

   public float sinh$mcF$sp(final float x) {
      return Trig.sinh$mcF$sp$(this, x);
   }

   public double cosh$mcD$sp(final double x) {
      return Trig.cosh$mcD$sp$(this, x);
   }

   public float cosh$mcF$sp(final float x) {
      return Trig.cosh$mcF$sp$(this, x);
   }

   public double tanh$mcD$sp(final double x) {
      return Trig.tanh$mcD$sp$(this, x);
   }

   public float tanh$mcF$sp(final float x) {
      return Trig.tanh$mcF$sp$(this, x);
   }

   public double toRadians$mcD$sp(final double a) {
      return Trig.toRadians$mcD$sp$(this, a);
   }

   public float toRadians$mcF$sp(final float a) {
      return Trig.toRadians$mcF$sp$(this, a);
   }

   public double toDegrees$mcD$sp(final double a) {
      return Trig.toDegrees$mcD$sp$(this, a);
   }

   public float toDegrees$mcF$sp(final float a) {
      return Trig.toDegrees$mcF$sp$(this, a);
   }

   public Complex fromDouble(final double n) {
      return ComplexOnField.fromDouble$(this, n);
   }

   public Complex fromDouble$mcD$sp(final double n) {
      return ComplexOnField.fromDouble$mcD$sp$(this, n);
   }

   public Complex fromDouble$mcF$sp(final double n) {
      return ComplexOnField.fromDouble$mcF$sp$(this, n);
   }

   public Complex div(final Complex a, final Complex b) {
      return ComplexOnField.div$(this, a, b);
   }

   public Complex div$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnField.div$mcD$sp$(this, a, b);
   }

   public Complex div$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnField.div$mcF$sp$(this, a, b);
   }

   public Field scalar$mcI$sp() {
      return VectorSpace.scalar$mcI$sp$(this);
   }

   public Field scalar$mcJ$sp() {
      return VectorSpace.scalar$mcJ$sp$(this);
   }

   public Object divr(final Object v, final Object f) {
      return VectorSpace.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace.divr$mcD$sp$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace.divr$mcF$sp$(this, v, f);
   }

   public Object divr$mcI$sp(final Object v, final int f) {
      return VectorSpace.divr$mcI$sp$(this, v, f);
   }

   public Object divr$mcJ$sp(final Object v, final long f) {
      return VectorSpace.divr$mcJ$sp$(this, v, f);
   }

   public Object gcd(final Object a, final Object b, final Eq eqA) {
      return Field.gcd$(this, a, b, eqA);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.gcd$mcD$sp$(this, a, b, eqA);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.gcd$mcF$sp$(this, a, b, eqA);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.gcd$mcI$sp$(this, a, b, eqA);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.gcd$mcJ$sp$(this, a, b, eqA);
   }

   public Object lcm(final Object a, final Object b, final Eq eqA) {
      return Field.lcm$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.lcm$mcD$sp$(this, a, b, eqA);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.lcm$mcF$sp$(this, a, b, eqA);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.lcm$mcI$sp$(this, a, b, eqA);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.lcm$mcJ$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final Object a) {
      return Field.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return Field.euclideanFunction$mcD$sp$(this, a);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return Field.euclideanFunction$mcF$sp$(this, a);
   }

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return Field.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return Field.euclideanFunction$mcJ$sp$(this, a);
   }

   public Object equot(final Object a, final Object b) {
      return Field.equot$(this, a, b);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return Field.equot$mcD$sp$(this, a, b);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return Field.equot$mcF$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return Field.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return Field.equot$mcJ$sp$(this, a, b);
   }

   public Object emod(final Object a, final Object b) {
      return Field.emod$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return Field.emod$mcD$sp$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return Field.emod$mcF$sp$(this, a, b);
   }

   public int emod$mcI$sp(final int a, final int b) {
      return Field.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return Field.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod(final Object a, final Object b) {
      return Field.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return Field.equotmod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return Field.equotmod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
   }

   public int fromDouble$mcI$sp(final double a) {
      return Field.fromDouble$mcI$sp$(this, a);
   }

   public long fromDouble$mcJ$sp(final double a) {
      return Field.fromDouble$mcJ$sp$(this, a);
   }

   public CommutativeGroup multiplicative() {
      return MultiplicativeCommutativeGroup.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
   }

   public CommutativeGroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
   }

   public CommutativeGroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeGroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
   }

   public byte fromDouble$mcB$sp(final double a) {
      return DivisionRing.fromDouble$mcB$sp$(this, a);
   }

   public short fromDouble$mcS$sp(final double a) {
      return DivisionRing.fromDouble$mcS$sp$(this, a);
   }

   public Object reciprocal(final Object x) {
      return MultiplicativeGroup.reciprocal$(this, x);
   }

   public double reciprocal$mcD$sp(final double x) {
      return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
   }

   public float reciprocal$mcF$sp(final float x) {
      return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
   }

   public int reciprocal$mcI$sp(final int x) {
      return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
   }

   public long reciprocal$mcJ$sp(final long x) {
      return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
   }

   public double div$mcD$sp(final double x, final double y) {
      return MultiplicativeGroup.div$mcD$sp$(this, x, y);
   }

   public float div$mcF$sp(final float x, final float y) {
      return MultiplicativeGroup.div$mcF$sp$(this, x, y);
   }

   public int div$mcI$sp(final int x, final int y) {
      return MultiplicativeGroup.div$mcI$sp$(this, x, y);
   }

   public long div$mcJ$sp(final long x, final long y) {
      return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
   }

   public Complex minus(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$(this, a, b);
   }

   public Complex minus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$mcD$sp$(this, a, b);
   }

   public Complex minus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$mcF$sp$(this, a, b);
   }

   public Complex negate(final Complex a) {
      return ComplexOnCRing.negate$(this, a);
   }

   public Complex negate$mcD$sp(final Complex a) {
      return ComplexOnCRing.negate$mcD$sp$(this, a);
   }

   public Complex negate$mcF$sp(final Complex a) {
      return ComplexOnCRing.negate$mcF$sp$(this, a);
   }

   public Complex one() {
      return ComplexOnCRing.one$(this);
   }

   public Complex one$mcD$sp() {
      return ComplexOnCRing.one$mcD$sp$(this);
   }

   public Complex one$mcF$sp() {
      return ComplexOnCRing.one$mcF$sp$(this);
   }

   public Complex plus(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$(this, a, b);
   }

   public Complex plus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$mcD$sp$(this, a, b);
   }

   public Complex plus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$mcF$sp$(this, a, b);
   }

   public Complex times(final Complex a, final Complex b) {
      return ComplexOnCRing.times$(this, a, b);
   }

   public Complex times$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.times$mcD$sp$(this, a, b);
   }

   public Complex times$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.times$mcF$sp$(this, a, b);
   }

   public Complex timesl(final Object a, final Complex v) {
      return ComplexOnCRing.timesl$(this, a, v);
   }

   public Complex timesl$mcD$sp(final double a, final Complex v) {
      return ComplexOnCRing.timesl$mcD$sp$(this, a, v);
   }

   public Complex timesl$mcF$sp(final float a, final Complex v) {
      return ComplexOnCRing.timesl$mcF$sp$(this, a, v);
   }

   public Complex zero() {
      return ComplexOnCRing.zero$(this);
   }

   public Complex zero$mcD$sp() {
      return ComplexOnCRing.zero$mcD$sp$(this);
   }

   public Complex zero$mcF$sp() {
      return ComplexOnCRing.zero$mcF$sp$(this);
   }

   public Complex adjoint(final Complex a) {
      return ComplexOnCRing.adjoint$(this, a);
   }

   public Complex adjoint$mcD$sp(final Complex a) {
      return ComplexOnCRing.adjoint$mcD$sp$(this, a);
   }

   public Complex adjoint$mcF$sp(final Complex a) {
      return ComplexOnCRing.adjoint$mcF$sp$(this, a);
   }

   public Complex fromInt(final int n) {
      return ComplexOnCRing.fromInt$(this, n);
   }

   public Complex fromInt$mcD$sp(final int n) {
      return ComplexOnCRing.fromInt$mcD$sp$(this, n);
   }

   public Complex fromInt$mcF$sp(final int n) {
      return ComplexOnCRing.fromInt$mcF$sp$(this, n);
   }

   public double adjoint$mcD$sp(final double a) {
      return Involution.adjoint$mcD$sp$(this, a);
   }

   public float adjoint$mcF$sp(final float a) {
      return Involution.adjoint$mcF$sp$(this, a);
   }

   public int adjoint$mcI$sp(final int a) {
      return Involution.adjoint$mcI$sp$(this, a);
   }

   public long adjoint$mcJ$sp(final long a) {
      return Involution.adjoint$mcJ$sp$(this, a);
   }

   public Object timesr(final Object v, final Object r) {
      return CModule.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule.timesr$mcD$sp$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule.timesr$mcF$sp$(this, v, r);
   }

   public Object timesr$mcI$sp(final Object v, final int r) {
      return CModule.timesr$mcI$sp$(this, v, r);
   }

   public Object timesr$mcJ$sp(final Object v, final long r) {
      return CModule.timesr$mcJ$sp$(this, v, r);
   }

   public Object timesl$mcD$sp(final double r, final Object v) {
      return LeftModule.timesl$mcD$sp$(this, r, v);
   }

   public Object timesl$mcF$sp(final float r, final Object v) {
      return LeftModule.timesl$mcF$sp$(this, r, v);
   }

   public Object timesl$mcI$sp(final int r, final Object v) {
      return LeftModule.timesl$mcI$sp$(this, r, v);
   }

   public Object timesl$mcJ$sp(final long r, final Object v) {
      return LeftModule.timesl$mcJ$sp$(this, r, v);
   }

   public int fromInt$mcI$sp(final int n) {
      return Ring.fromInt$mcI$sp$(this, n);
   }

   public long fromInt$mcJ$sp(final int n) {
      return Ring.fromInt$mcJ$sp$(this, n);
   }

   public Object fromBigInt(final BigInt n) {
      return Ring.fromBigInt$(this, n);
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return Ring.fromBigInt$mcD$sp$(this, n);
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return Ring.fromBigInt$mcF$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return Ring.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup.additive$mcI$sp$(this);
   }

   public CommutativeGroup additive$mcJ$sp() {
      return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
   }

   public double negate$mcD$sp(final double x) {
      return AdditiveGroup.negate$mcD$sp$(this, x);
   }

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveGroup.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
   }

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne(final Object a, final Eq ev) {
      return MultiplicativeMonoid.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public Object product(final IterableOnce as) {
      return MultiplicativeMonoid.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcF$sp$(this, as);
   }

   public int product$mcI$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcI$sp$(this, as);
   }

   public long product$mcJ$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcJ$sp$(this, as);
   }

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeMonoid.tryProduct$(this, as);
   }

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public Object sum(final IterableOnce as) {
      return AdditiveMonoid.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public Field scalar() {
      return this.scalar;
   }

   public NRoot nroot() {
      return this.nroot;
   }

   public Order order() {
      return this.order;
   }

   public Trig trig() {
      return this.trig;
   }

   public Signed signed() {
      return this.signed;
   }

   public Complex pow(final Complex a, final int b) {
      return a.pow(b, this.scalar(), this.nroot(), this.order(), this.signed(), this.trig());
   }

   public Field scalar$mcD$sp() {
      return this.scalar();
   }

   public Field scalar$mcF$sp() {
      return this.scalar();
   }

   public NRoot nroot$mcD$sp() {
      return this.nroot();
   }

   public NRoot nroot$mcF$sp() {
      return this.nroot();
   }

   public Order order$mcD$sp() {
      return this.order();
   }

   public Order order$mcF$sp() {
      return this.order();
   }

   public Trig trig$mcD$sp() {
      return this.trig();
   }

   public Trig trig$mcF$sp() {
      return this.trig();
   }

   public Signed signed$mcD$sp() {
      return this.signed();
   }

   public Signed signed$mcF$sp() {
      return this.signed();
   }

   public Complex pow$mcD$sp(final Complex a, final int b) {
      return this.pow(a, b);
   }

   public Complex pow$mcF$sp(final Complex a, final int b) {
      return this.pow(a, b);
   }

   public boolean specInstance$() {
      return false;
   }

   public ComplexOnTrigImpl(final Field scalar, final NRoot nroot, final Order order, final Trig trig, final Signed signed) {
      this.scalar = scalar;
      this.nroot = nroot;
      this.order = order;
      this.trig = trig;
      this.signed = signed;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      Ring.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
      MultiplicativeCommutativeMonoid.$init$(this);
      CModule.$init$(this);
      ComplexOnCRing.$init$(this);
      EuclideanRing.$init$(this);
      MultiplicativeGroup.$init$(this);
      DivisionRing.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      Field.$init$(this);
      VectorSpace.$init$(this);
      ComplexOnField.$init$(this);
      ComplexOnTrig.$init$(this);
      NRoot.$init$(this);
      ComplexIsNRoot.$init$(this);
   }
}
