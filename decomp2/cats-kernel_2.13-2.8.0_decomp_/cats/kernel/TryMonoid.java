package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005=2A\u0001B\u0003\u0005\u0015!Aq\u0005\u0001B\u0001B\u0003%\u0001\u0006C\u0003*\u0001\u0011\u0005!\u0006C\u0003.\u0001\u0011\u0005aFA\u0005UefluN\\8jI*\u0011aaB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003!\tAaY1ug\u000e\u0001QCA\u0006\u0013'\r\u0001AB\b\t\u0004\u001b9\u0001R\"A\u0003\n\u0005=)!\u0001\u0004+ssN+W.[4s_V\u0004\bCA\t\u0013\u0019\u0001!Qa\u0005\u0001C\u0002Q\u0011\u0011!Q\t\u0003+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011qAT8uQ&tw\r\u0005\u0002\u00179%\u0011Qd\u0006\u0002\u0004\u0003:L\bcA\u0007 C%\u0011\u0001%\u0002\u0002\u0007\u001b>tw.\u001b3\u0011\u0007\t*\u0003#D\u0001$\u0015\t!s#\u0001\u0003vi&d\u0017B\u0001\u0014$\u0005\r!&/_\u0001\u0002\u0003B\u0019Qb\b\t\u0002\rqJg.\u001b;?)\tYC\u0006E\u0002\u000e\u0001AAQa\n\u0002A\u0002!\nQ!Z7qif,\u0012!\t"
)
public class TryMonoid extends TrySemigroup implements Monoid {
   private final Monoid A;

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

   public Try empty() {
      return new Success(this.A.empty());
   }

   public TryMonoid(final Monoid A) {
      super(A);
      this.A = A;
      Monoid.$init$(this);
   }
}
