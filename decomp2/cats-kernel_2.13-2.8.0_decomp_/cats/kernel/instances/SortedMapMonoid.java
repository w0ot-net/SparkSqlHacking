package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedMap.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A!\u0002\u0004\u0001\u001b!A\u0001\u0007\u0001B\u0001B\u0003-\u0011\u0007\u0003\u00055\u0001\t\u0005\t\u0015a\u00036\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015q\u0004\u0001\"\u0001@\u0005=\u0019vN\u001d;fI6\u000b\u0007/T8o_&$'BA\u0004\t\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\n\u0015\u000511.\u001a:oK2T\u0011aC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00079)\"eE\u0002\u0001\u001f\u0011\u0002B\u0001E\t\u0014C5\ta!\u0003\u0002\u0013\r\t\u00112k\u001c:uK\u0012l\u0015\r]*f[&<'o\\;q!\t!R\u0003\u0004\u0001\u0005\u000bY\u0001!\u0019A\f\u0003\u0003-\u000b\"\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0011dH\u0005\u0003Ai\u00111!\u00118z!\t!\"\u0005B\u0003$\u0001\t\u0007qCA\u0001W!\r)c\u0005K\u0007\u0002\u0011%\u0011q\u0005\u0003\u0002\u0007\u001b>tw.\u001b3\u0011\t%r3#I\u0007\u0002U)\u00111\u0006L\u0001\nS6lW\u000f^1cY\u0016T!!\f\u000e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00020U\tI1k\u001c:uK\u0012l\u0015\r]\u0001\u0002-B\u0019QEM\u0011\n\u0005MB!!C*f[&<'o\\;q\u0003\u0005y\u0005cA\u00137'%\u0011q\u0007\u0003\u0002\u0006\u001fJ$WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\"2a\u000f\u001f>!\u0011\u0001\u0002aE\u0011\t\u000bA\u001a\u00019A\u0019\t\u000bQ\u001a\u00019A\u001b\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003!\u0002"
)
public class SortedMapMonoid extends SortedMapSemigroup implements Monoid {
   private final Order O;

   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty(final Object a, final Eq ev) {
      return Monoid.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public Object combineN(final Object a, final int n) {
      return Monoid.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Monoid.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Monoid.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Monoid.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Monoid.combineN$mcJ$sp$(this, a, n);
   }

   public Object combineAll(final IterableOnce as) {
      return Monoid.combineAll$(this, as);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid.combineAll$mcI$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
   }

   public Monoid reverse() {
      return Monoid.reverse$(this);
   }

   public Monoid reverse$mcD$sp() {
      return Monoid.reverse$mcD$sp$(this);
   }

   public Monoid reverse$mcF$sp() {
      return Monoid.reverse$mcF$sp$(this);
   }

   public Monoid reverse$mcI$sp() {
      return Monoid.reverse$mcI$sp$(this);
   }

   public Monoid reverse$mcJ$sp() {
      return Monoid.reverse$mcJ$sp$(this);
   }

   public SortedMap empty() {
      return (SortedMap).MODULE$.empty(this.O.toOrdering());
   }

   public SortedMapMonoid(final Semigroup V, final Order O) {
      super(V);
      this.O = O;
      Monoid.$init$(this);
   }
}
