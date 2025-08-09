package spire.std;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeSemiring;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005}3Aa\u0002\u0005\u0001\u001b!A1\t\u0001BC\u0002\u0013\rA\t\u0003\u0005G\u0001\t\u0005\t\u0015!\u0003F\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015)\u0006\u0001\"\u0001W\u00051i\u0015\r]\"TK6L'/\u001b8h\u0015\tI!\"A\u0002ti\u0012T\u0011aC\u0001\u0006gBL'/Z\u0002\u0001+\rqq&O\n\u0005\u0001=)2\b\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0004-\t*cBA\f \u001d\tARD\u0004\u0002\u001a95\t!D\u0003\u0002\u001c\u0019\u00051AH]8pizJ\u0011aC\u0005\u0003=)\tq!\u00197hK\n\u0014\u0018-\u0003\u0002!C\u00059\u0001/Y2lC\u001e,'B\u0001\u0010\u000b\u0013\t\u0019CEA\u0005D'\u0016l\u0017N]5oO*\u0011\u0001%\t\t\u0005M)j\u0003H\u0004\u0002(QA\u0011\u0011$E\u0005\u0003SE\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\ri\u0015\r\u001d\u0006\u0003SE\u0001\"AL\u0018\r\u0001\u0011)\u0001\u0007\u0001b\u0001c\t\t1*\u0005\u00023kA\u0011\u0001cM\u0005\u0003iE\u0011qAT8uQ&tw\r\u0005\u0002\u0011m%\u0011q'\u0005\u0002\u0004\u0003:L\bC\u0001\u0018:\t\u0015Q\u0004A1\u00012\u0005\u00051\u0006C\u0001\u001fA\u001d\titH\u0004\u0002\u001a}%\t!#\u0003\u0002!#%\u0011\u0011I\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003AE\taa]2bY\u0006\u0014X#A#\u0011\u0007Y\u0011\u0003(A\u0004tG\u0006d\u0017M\u001d\u0011\u0002\rqJg.\u001b;?)\u0005IEC\u0001&M!\u0011Y\u0005!\f\u001d\u000e\u0003!AQaQ\u0002A\u0004\u0015\u000bAA_3s_V\tQ%\u0001\u0003qYV\u001cHcA\u0013R'\")!+\u0002a\u0001K\u0005\t\u0001\u0010C\u0003U\u000b\u0001\u0007Q%A\u0001z\u0003\u0015!\u0018.\\3t)\r)s\u000b\u0017\u0005\u0006%\u001a\u0001\r!\n\u0005\u0006)\u001a\u0001\r!\n\u0015\u0005\u0001ikf\f\u0005\u0002\u00117&\u0011A,\u0005\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class MapCSemiring implements CommutativeSemiring {
   private static final long serialVersionUID = 0L;
   private final CommutativeSemiring scalar;

   public CommutativeSemigroup multiplicative() {
      return MultiplicativeCommutativeSemigroup.multiplicative$(this);
   }

   public CommutativeSemigroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcD$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcF$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeSemigroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeSemigroup.multiplicative$mcJ$sp$(this);
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

   public Object pow(final Object a, final int n) {
      return MultiplicativeSemigroup.pow$(this, a, n);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
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

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeSemigroup.tryProduct$(this, as);
   }

   public CommutativeMonoid additive() {
      return AdditiveCommutativeMonoid.additive$(this);
   }

   public CommutativeMonoid additive$mcD$sp() {
      return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
   }

   public CommutativeMonoid additive$mcF$sp() {
      return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
   }

   public CommutativeMonoid additive$mcI$sp() {
      return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
   }

   public CommutativeMonoid additive$mcJ$sp() {
      return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
   }

   public double zero$mcD$sp() {
      return AdditiveMonoid.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return AdditiveMonoid.zero$mcF$sp$(this);
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

   public Object sumN(final Object a, final int n) {
      return AdditiveMonoid.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
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

   public CommutativeSemiring scalar() {
      return this.scalar;
   }

   public Map zero() {
      return .MODULE$.Map().empty();
   }

   public Map plus(final Map x, final Map y) {
      ObjectRef xx = ObjectRef.create(x);
      Map yy = y;
      if (x.size() < y.size()) {
         xx.elem = y;
         yy = x;
      }

      return (Map)yy.foldLeft((Map)xx.elem, (z, kv) -> (Map)z.updated(kv._1(), ((Map)xx.elem).get(kv._1()).map((u) -> this.scalar().plus(u, kv._2())).getOrElse(() -> kv._2())));
   }

   public Map times(final Map x, final Map y) {
      ObjectRef xx = ObjectRef.create(x);
      Map yy = y;
      ObjectRef f = ObjectRef.create((Function2)(xxx, yx) -> this.scalar().times(xxx, yx));
      if (x.size() < y.size()) {
         xx.elem = y;
         yy = x;
         f.elem = (xxx, yx) -> this.scalar().times(yx, xxx);
      }

      return (Map)yy.foldLeft(this.zero(), (z, kv) -> (Map)((Map)xx.elem).get(kv._1()).map((u) -> (Map)z.updated(kv._1(), ((Function2)f.elem).apply(u, kv._2()))).getOrElse(() -> z));
   }

   public MapCSemiring(final CommutativeSemiring scalar) {
      this.scalar = scalar;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
