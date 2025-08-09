package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.MatchError;
import scala.Option;
import scala.collection.IterableOnce;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0004\u0005\u0006/\u0001!\t!\u0007\u0005\u0006;\u0001!\u0019A\b\u0005\u0006\u0003\u0002!\u0019A\u0011\u0005\u0006#\u0002!\u0019A\u0015\u0002\u0011\u000b&$\b.\u001a:J]N$\u0018M\\2fgBR!a\u0002\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0005\u000b\u0003\u0019YWM\u001d8fY*\t1\"\u0001\u0003dCR\u001c8c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003\u0019I!A\u0006\u0004\u0003!\u0015KG\u000f[3s\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003i\u0001\"AD\u000e\n\u0005qy!\u0001B+oSR\f!dY1ug\u0012\u000bG/Y*f[&<'o\\;q\r>\u0014X)\u001b;iKJ,2a\b\u001a=)\t\u0001c\bE\u0002\"E\u0011j\u0011\u0001C\u0005\u0003G!\u0011\u0011bU3nS\u001e\u0014x.\u001e9\u0011\t\u0015j\u0003g\u000f\b\u0003M-r!a\n\u0016\u000e\u0003!R!!\u000b\r\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012B\u0001\u0017\u0010\u0003\u001d\u0001\u0018mY6bO\u0016L!AL\u0018\u0003\r\u0015KG\u000f[3s\u0015\tas\u0002\u0005\u00022e1\u0001A!B\u001a\u0003\u0005\u0004!$!A!\u0012\u0005UB\u0004C\u0001\b7\u0013\t9tBA\u0004O_RD\u0017N\\4\u0011\u00059I\u0014B\u0001\u001e\u0010\u0005\r\te.\u001f\t\u0003cq\"Q!\u0010\u0002C\u0002Q\u0012\u0011A\u0011\u0005\u0006\u007f\t\u0001\u001d\u0001Q\u0001\u0002\u0005B\u0019\u0011EI\u001e\u00029\r\fGo]*uIB\u000b'\u000f^5bY>\u0013H-\u001a:G_J,\u0015\u000e\u001e5feV\u00191)S&\u0015\u0007\u0011cu\nE\u0002\"\u000b\u001eK!A\u0012\u0005\u0003\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0011\t\u0015j\u0003J\u0013\t\u0003c%#QaM\u0002C\u0002Q\u0002\"!M&\u0005\u000bu\u001a!\u0019\u0001\u001b\t\u000b5\u001b\u00019\u0001(\u0002\u0003\u0005\u00032!I#I\u0011\u0015y4\u0001q\u0001Q!\r\tSIS\u0001\u0015G\u0006$8o\u0015;e\u0011\u0006\u001c\bNR8s\u000b&$\b.\u001a:\u0016\u0007MK6\fF\u0002U9z\u00032!I+X\u0013\t1\u0006B\u0001\u0003ICND\u0007\u0003B\u0013.1j\u0003\"!M-\u0005\u000bM\"!\u0019\u0001\u001b\u0011\u0005EZF!B\u001f\u0005\u0005\u0004!\u0004\"B'\u0005\u0001\bi\u0006cA\u0011V1\")q\b\u0002a\u0002?B\u0019\u0011%\u0016."
)
public interface EitherInstances0 extends EitherInstances1 {
   // $FF: synthetic method
   static Semigroup catsDataSemigroupForEither$(final EitherInstances0 $this, final Semigroup B) {
      return $this.catsDataSemigroupForEither(B);
   }

   default Semigroup catsDataSemigroupForEither(final Semigroup B) {
      return new Semigroup(B) {
         private final Semigroup B$3;

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

         public Either combine(final Either x, final Either y) {
            Object var3;
            if (x instanceof Left) {
               Left var6 = (Left)x;
               var3 = var6;
            } else {
               if (!(x instanceof Right)) {
                  throw new MatchError(x);
               }

               Right var7 = (Right)x;
               Object xx = var7.value();
               Object var4;
               if (y instanceof Left) {
                  Left var10 = (Left)y;
                  var4 = var10;
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  Right var11 = (Right)y;
                  Object yy = var11.value();
                  var4 = .MODULE$.Right().apply(this.B$3.combine(xx, yy));
               }

               var3 = var4;
            }

            return (Either)var3;
         }

         public {
            this.B$3 = B$3;
            Semigroup.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder catsStdPartialOrderForEither$(final EitherInstances0 $this, final PartialOrder A, final PartialOrder B) {
      return $this.catsStdPartialOrderForEither(A, B);
   }

   default PartialOrder catsStdPartialOrderForEither(final PartialOrder A, final PartialOrder B) {
      return new PartialOrder(A, B) {
         private final PartialOrder A$2;
         private final PartialOrder B$4;

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialCompare$mcV$sp$(this, x, y);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return PartialOrder.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return PartialOrder.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return PartialOrder.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return PartialOrder.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return PartialOrder.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return PartialOrder.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.eqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return PartialOrder.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return PartialOrder.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return PartialOrder.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return PartialOrder.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return PartialOrder.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return PartialOrder.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return PartialOrder.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return PartialOrder.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return PartialOrder.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return PartialOrder.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return PartialOrder.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return PartialOrder.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return PartialOrder.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return PartialOrder.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return PartialOrder.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return PartialOrder.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gt$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Either x, final Either y) {
            double var3;
            if (x instanceof Left) {
               Left var10 = (Left)x;
               Object xx = var10.value();
               double var7;
               if (y instanceof Left) {
                  Left var13 = (Left)y;
                  Object yy = var13.value();
                  var7 = this.A$2.partialCompare(xx, yy);
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  var7 = (double)-1.0F;
               }

               var3 = var7;
            } else {
               if (!(x instanceof Right)) {
                  throw new MatchError(x);
               }

               Right var15 = (Right)x;
               Object xx = var15.value();
               double var5;
               if (y instanceof Left) {
                  var5 = (double)1.0F;
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  Right var18 = (Right)y;
                  Object yy = var18.value();
                  var5 = this.B$4.partialCompare(xx, yy);
               }

               var3 = var5;
            }

            return var3;
         }

         public {
            this.A$2 = A$2;
            this.B$4 = B$4;
            Eq.$init$(this);
            PartialOrder.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Hash catsStdHashForEither$(final EitherInstances0 $this, final Hash A, final Hash B) {
      return $this.catsStdHashForEither(A, B);
   }

   default Hash catsStdHashForEither(final Hash A, final Hash B) {
      return new EitherHash(A, B);
   }

   static void $init$(final EitherInstances0 $this) {
   }
}
