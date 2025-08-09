package cats.kernel;

import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005M2A\u0001B\u0003\u0005\u0015!Aq\u0005\u0001B\u0001B\u0003%\u0001\u0006C\u0003*\u0001\u0011\u0005!\u0006C\u0003.\u0001\u0011\u0005aF\u0001\u0007Uef\u001cV-\\5he>,\bO\u0003\u0002\u0007\u000f\u000511.\u001a:oK2T\u0011\u0001C\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005-q2c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\u000b\u0017\u001b\u0005)\u0011BA\u000b\u0006\u0005%\u0019V-\\5he>,\b\u000fE\u0002\u00185qi\u0011\u0001\u0007\u0006\u000339\tA!\u001e;jY&\u00111\u0004\u0007\u0002\u0004)JL\bCA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"!\u0004\u0012\n\u0005\rr!a\u0002(pi\"Lgn\u001a\t\u0003\u001b\u0015J!A\n\b\u0003\u0007\u0005s\u00170A\u0001B!\r\u0019B\u0003H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-b\u0003cA\n\u00019!)qE\u0001a\u0001Q\u000591m\\7cS:,Gc\u0001\f0c!)\u0001g\u0001a\u0001-\u0005\t\u0001\u0010C\u00033\u0007\u0001\u0007a#A\u0001z\u0001"
)
public class TrySemigroup implements Semigroup {
   private final Semigroup A;

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

   public Object combineN(final Object a, final int n) {
      return Semigroup.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Semigroup.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Semigroup.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Semigroup.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Semigroup.combineN$mcJ$sp$(this, a, n);
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

   public Option combineAllOption(final IterableOnce as) {
      return Semigroup.combineAllOption$(this, as);
   }

   public Semigroup reverse() {
      return Semigroup.reverse$(this);
   }

   public Semigroup reverse$mcD$sp() {
      return Semigroup.reverse$mcD$sp$(this);
   }

   public Semigroup reverse$mcF$sp() {
      return Semigroup.reverse$mcF$sp$(this);
   }

   public Semigroup reverse$mcI$sp() {
      return Semigroup.reverse$mcI$sp$(this);
   }

   public Semigroup reverse$mcJ$sp() {
      return Semigroup.reverse$mcJ$sp$(this);
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

   public Try combine(final Try x, final Try y) {
      Tuple2 var4 = new Tuple2(x, y);
      Object var3;
      if (var4 != null) {
         Try var5 = (Try)var4._1();
         Try var6 = (Try)var4._2();
         if (var5 instanceof Success) {
            Success var7 = (Success)var5;
            Object xv = var7.value();
            if (var6 instanceof Success) {
               Success var9 = (Success)var6;
               Object yv = var9.value();
               var3 = new Success(this.A.combine(xv, yv));
               return (Try)var3;
            }
         }
      }

      if (var4 != null) {
         Try f = (Try)var4._1();
         if (f instanceof Failure) {
            Failure var12 = (Failure)f;
            var3 = var12;
            return (Try)var3;
         }
      }

      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         Try f = (Try)var4._2();
         var3 = f;
         return (Try)var3;
      }
   }

   public TrySemigroup(final Semigroup A) {
      this.A = A;
      Semigroup.$init$(this);
   }
}
