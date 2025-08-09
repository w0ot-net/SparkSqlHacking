package cats.kernel.instances;

import cats.kernel.Eq;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0004MSN$X)\u001d\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0011QBJ\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016-ai\u0011aB\u0005\u0003/\u001d\u0011!!R9\u0011\u0007e\tCE\u0004\u0002\u001b?9\u00111DH\u0007\u00029)\u0011QdC\u0001\u0007yI|w\u000e\u001e \n\u0003EI!\u0001\t\t\u0002\u000fA\f7m[1hK&\u0011!e\t\u0002\u0005\u0019&\u001cHO\u0003\u0002!!A\u0011QE\n\u0007\u0001\t\u00159\u0003A1\u0001)\u0005\u0005\t\u0015CA\u0015-!\ty!&\u0003\u0002,!\t9aj\u001c;iS:<\u0007CA\b.\u0013\tq\u0003CA\u0002B]f\f!!\u001a<\u0011\u0007U1B%\u0001\u0004=S:LGO\u0010\u000b\u0002gQ\u0011AG\u000e\t\u0004k\u0001!S\"A\u0003\t\u000b=\u0012\u00019\u0001\u0019\u0002\u0007\u0015\fh\u000fF\u0002:yy\u0002\"a\u0004\u001e\n\u0005m\u0002\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006{\r\u0001\r\u0001G\u0001\u0003qNDQaP\u0002A\u0002a\t!!_:"
)
public class ListEq implements Eq {
   private final Eq ev;

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Eq.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Eq.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Eq.eqv$mcD$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Eq.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Eq.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Eq.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Eq.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.eqv$mcV$sp$(this, x, y);
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

   public boolean eqv(final List xs, final List ys) {
      return xs == ys || this.loop$3(xs, ys);
   }

   private final boolean loop$3(final List xs, final List ys) {
      boolean var4;
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (xs == null) {
               break;
            }
         } else if (var10000.equals(xs)) {
            break;
         }

         if (!(xs instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(xs);
         }

         scala.collection.immutable..colon.colon var8 = (scala.collection.immutable..colon.colon)xs;
         Object x = var8.head();
         List xs = var8.next$access$1();
         boolean var5;
         if (ys instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var12 = (scala.collection.immutable..colon.colon)ys;
            Object y = var12.head();
            List ys = var12.next$access$1();
            if (this.ev.eqv(x, y)) {
               ys = ys;
               xs = xs;
               continue;
            }

            var5 = false;
         } else {
            var10000 = .MODULE$.Nil();
            if (var10000 == null) {
               if (ys != null) {
                  throw new MatchError(ys);
               }
            } else if (!var10000.equals(ys)) {
               throw new MatchError(ys);
            }

            var5 = false;
         }

         var4 = var5;
         return var4;
      }

      var4 = ys.isEmpty();
      return var4;
   }

   public ListEq(final Eq ev) {
      this.ev = ev;
      Eq.$init$(this);
   }
}
