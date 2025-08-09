package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.LazyList;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513AAB\u0004\u0001\u001d!)\u0011\u0007\u0001C\u0001e!)Q\u0007\u0001C\u0001m!)q\u0007\u0001C\u0001q!)Q\b\u0001C!}!)Q\t\u0001C!\r\nqA*\u0019>z\u0019&\u001cH/T8o_&$'B\u0001\u0005\n\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\u000b\u0017\u000511.\u001a:oK2T\u0011\u0001D\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005=A3c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u00042a\u0006\r\u001b\u001b\u0005I\u0011BA\r\n\u0005\u0019iuN\\8jIB\u00191d\t\u0014\u000f\u0005q\tcBA\u000f!\u001b\u0005q\"BA\u0010\u000e\u0003\u0019a$o\\8u}%\t1#\u0003\u0002#%\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0013&\u0005!a\u0015M_=MSN$(B\u0001\u0012\u0013!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u0002!\u0019\u0001\u0016\u0003\u0003\u0005\u000b\"a\u000b\u0018\u0011\u0005Ea\u0013BA\u0017\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u0018\n\u0005A\u0012\"aA!os\u00061A(\u001b8jiz\"\u0012a\r\t\u0004i\u00011S\"A\u0004\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003i\tqaY8nE&tW\rF\u0002\u001bsmBQAO\u0002A\u0002i\t\u0011\u0001\u001f\u0005\u0006y\r\u0001\rAG\u0001\u0002s\u0006A1m\\7cS:,g\nF\u0002\u001b\u007f\u0001CQA\u000f\u0003A\u0002iAQ!\u0011\u0003A\u0002\t\u000b\u0011A\u001c\t\u0003#\rK!\u0001\u0012\n\u0003\u0007%sG/\u0001\u0006d_6\u0014\u0017N\\3BY2$\"AG$\t\u000b!+\u0001\u0019A%\u0002\u0005a\u001c\bcA\u000eK5%\u00111*\n\u0002\r\u0013R,'/\u00192mK>s7-\u001a"
)
public class LazyListMonoid implements Monoid {
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

   public double combine$mcD$sp(final double x, final double y) {
      return Semigroup.combine$mcD$sp$(this, x, y);
   }

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public Semigroup intercalate(final Object middle) {
      return Semigroup.intercalate$(this, middle);
   }

   public Semigroup intercalate$mcD$sp(final double middle) {
      return Semigroup.intercalate$mcD$sp$(this, middle);
   }

   public Semigroup intercalate$mcF$sp(final float middle) {
      return Semigroup.intercalate$mcF$sp$(this, middle);
   }

   public Semigroup intercalate$mcI$sp(final int middle) {
      return Semigroup.intercalate$mcI$sp$(this, middle);
   }

   public Semigroup intercalate$mcJ$sp(final long middle) {
      return Semigroup.intercalate$mcJ$sp$(this, middle);
   }

   public LazyList empty() {
      return .MODULE$.LazyList().empty();
   }

   public LazyList combine(final LazyList x, final LazyList y) {
      return (LazyList)x.$plus$plus(y);
   }

   public LazyList combineN(final LazyList x, final int n) {
      return (LazyList)StaticMethods$.MODULE$.combineNIterable(.MODULE$.LazyList().newBuilder(), x, n);
   }

   public LazyList combineAll(final IterableOnce xs) {
      return (LazyList)StaticMethods$.MODULE$.combineAllIterable(.MODULE$.LazyList().newBuilder(), xs);
   }

   public LazyListMonoid() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
