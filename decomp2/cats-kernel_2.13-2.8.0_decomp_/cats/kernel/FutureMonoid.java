package cats.kernel;

import scala.Option;
import scala.collection.IterableOnce;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2A!\u0002\u0004\u0005\u0017!A\u0001\u0006\u0001B\u0001B\u0003%\u0011\u0006\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003,\u0011\u0015q\u0003\u0001\"\u00010\u0011\u0015\u0019\u0004\u0001\"\u00015\u000511U\u000f^;sK6{gn\\5e\u0015\t9\u0001\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0013\u0005!1-\u0019;t\u0007\u0001)\"\u0001D\n\u0014\u0007\u0001iq\u0004E\u0002\u000f\u001fEi\u0011AB\u0005\u0003!\u0019\u0011qBR;ukJ,7+Z7jOJ|W\u000f\u001d\t\u0003%Ma\u0001\u0001B\u0003\u0015\u0001\t\u0007QCA\u0001B#\t1B\u0004\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002DA\u0004O_RD\u0017N\\4\u0011\u0005]i\u0012B\u0001\u0010\u0019\u0005\r\te.\u001f\t\u0004\u001d\u0001\u0012\u0013BA\u0011\u0007\u0005\u0019iuN\\8jIB\u00191EJ\t\u000e\u0003\u0011R!!\n\r\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002(I\t1a)\u001e;ve\u0016\f\u0011!\u0011\t\u0004\u001d\u0001\n\u0012AA3d!\t\u0019C&\u0003\u0002.I\t\u0001R\t_3dkRLwN\\\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007A\n$\u0007E\u0002\u000f\u0001EAQ\u0001K\u0002A\u0002%BQAK\u0002A\u0002-\nQ!Z7qif,\u0012A\t"
)
public class FutureMonoid extends FutureSemigroup implements Monoid {
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

   public Future empty() {
      return .MODULE$.successful(this.A.empty());
   }

   public FutureMonoid(final Monoid A, final ExecutionContext ec) {
      super(A, ec);
      this.A = A;
      Monoid.$init$(this);
   }
}
