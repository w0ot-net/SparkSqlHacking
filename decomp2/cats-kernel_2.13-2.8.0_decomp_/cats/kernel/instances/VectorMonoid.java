package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513AAB\u0004\u0001\u001d!)\u0011\u0007\u0001C\u0001e!)Q\u0007\u0001C\u0001m!)q\u0007\u0001C\u0001q!)Q\b\u0001C!}!)Q\t\u0001C!\r\naa+Z2u_JluN\\8jI*\u0011\u0001\"C\u0001\nS:\u001cH/\u00198dKNT!AC\u0006\u0002\r-,'O\\3m\u0015\u0005a\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\u0010QM\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\r9\u0002DG\u0007\u0002\u0013%\u0011\u0011$\u0003\u0002\u0007\u001b>tw.\u001b3\u0011\u0007m\u0019cE\u0004\u0002\u001dC9\u0011Q\u0004I\u0007\u0002=)\u0011q$D\u0001\u0007yI|w\u000e\u001e \n\u0003MI!A\t\n\u0002\u000fA\f7m[1hK&\u0011A%\n\u0002\u0007-\u0016\u001cGo\u001c:\u000b\u0005\t\u0012\u0002CA\u0014)\u0019\u0001!Q!\u000b\u0001C\u0002)\u0012\u0011!Q\t\u0003W9\u0002\"!\u0005\u0017\n\u00055\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#=J!\u0001\r\n\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002gA\u0019A\u0007\u0001\u0014\u000e\u0003\u001d\tQ!Z7qif,\u0012AG\u0001\bG>l'-\u001b8f)\rQ\u0012h\u000f\u0005\u0006u\r\u0001\rAG\u0001\u0002q\")Ah\u0001a\u00015\u0005\t\u00110\u0001\u0005d_6\u0014\u0017N\\3O)\rQr\b\u0011\u0005\u0006u\u0011\u0001\rA\u0007\u0005\u0006\u0003\u0012\u0001\rAQ\u0001\u0002]B\u0011\u0011cQ\u0005\u0003\tJ\u00111!\u00138u\u0003)\u0019w.\u001c2j]\u0016\fE\u000e\u001c\u000b\u00035\u001dCQ\u0001S\u0003A\u0002%\u000b!\u0001_:\u0011\u0007mQ%$\u0003\u0002LK\ta\u0011\n^3sC\ndWm\u00148dK\u0002"
)
public class VectorMonoid implements Monoid {
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

   public Vector empty() {
      return .MODULE$.Vector().empty();
   }

   public Vector combine(final Vector x, final Vector y) {
      return (Vector)x.$plus$plus(y);
   }

   public Vector combineN(final Vector x, final int n) {
      return (Vector)StaticMethods$.MODULE$.combineNIterable(.MODULE$.Vector().newBuilder(), x, n);
   }

   public Vector combineAll(final IterableOnce xs) {
      return (Vector)StaticMethods$.MODULE$.combineAllIterable(.MODULE$.Vector().newBuilder(), xs);
   }

   public VectorMonoid() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
