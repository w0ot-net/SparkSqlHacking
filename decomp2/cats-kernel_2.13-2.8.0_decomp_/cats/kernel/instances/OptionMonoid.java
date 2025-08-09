package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2A!\u0002\u0004\u0001\u001b!Aq\u0005\u0001B\u0001B\u0003-\u0001\u0006C\u0003,\u0001\u0011\u0005A\u0006C\u00032\u0001\u0011\u0005!\u0007C\u00034\u0001\u0011\u0005AG\u0001\u0007PaRLwN\\'p]>LGM\u0003\u0002\b\u0011\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0013)\taa[3s]\u0016d'\"A\u0006\u0002\t\r\fGo]\u0002\u0001+\tqadE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f\u001835\t\u0001\"\u0003\u0002\u0019\u0011\t1Qj\u001c8pS\u0012\u00042\u0001\u0005\u000e\u001d\u0013\tY\u0012C\u0001\u0004PaRLwN\u001c\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0011E%\u00111%\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001R%\u0003\u0002'#\t\u0019\u0011I\\=\u0002\u0003\u0005\u00032AF\u0015\u001d\u0013\tQ\u0003BA\u0005TK6LwM]8va\u00061A(\u001b8jiz\"\u0012!\f\u000b\u0003]A\u00022a\f\u0001\u001d\u001b\u00051\u0001\"B\u0014\u0003\u0001\bA\u0013!B3naRLX#A\r\u0002\u000f\r|WNY5oKR\u0019\u0011$N\u001c\t\u000bY\"\u0001\u0019A\r\u0002\u0003aDQ\u0001\u000f\u0003A\u0002e\t\u0011!\u001f"
)
public class OptionMonoid implements Monoid {
   private final Semigroup A;

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

   public Option empty() {
      return .MODULE$;
   }

   public Option combine(final Option x, final Option y) {
      Object var3;
      if (.MODULE$.equals(x)) {
         var3 = y;
      } else {
         if (!(x instanceof Some)) {
            throw new MatchError(x);
         }

         Some var6 = (Some)x;
         Object a = var6.value();
         Object var4;
         if (.MODULE$.equals(y)) {
            var4 = x;
         } else {
            if (!(y instanceof Some)) {
               throw new MatchError(y);
            }

            Some var9 = (Some)y;
            Object b = var9.value();
            var4 = new Some(this.A.combine(a, b));
         }

         var3 = var4;
      }

      return (Option)var3;
   }

   public OptionMonoid(final Semigroup A) {
      this.A = A;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
