package cats.kernel.instances;

import cats.kernel.Hash;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153A!\u0002\u0004\u0001\u001b!AA\u0007\u0001B\u0001B\u0003-Q\u0007\u0003\u00057\u0001\t\u0005\t\u0015a\u00038\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015q\u0004\u0001\"\u0001@\u0005))\u0015\u000e\u001e5fe\"\u000b7\u000f\u001b\u0006\u0003\u000f!\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005%Q\u0011AB6fe:,GNC\u0001\f\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0019a\"\u0006\u0012\u0014\u0007\u0001yA\u0005\u0005\u0003\u0011#M\tS\"\u0001\u0004\n\u0005I1!\u0001C#ji\",'/R9\u0011\u0005Q)B\u0002\u0001\u0003\u0006-\u0001\u0011\ra\u0006\u0002\u0002\u0003F\u0011\u0001D\b\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\b\u001d>$\b.\u001b8h!\tIr$\u0003\u0002!5\t\u0019\u0011I\\=\u0011\u0005Q\u0011C!B\u0012\u0001\u0005\u00049\"!\u0001\"\u0011\u0007\u00152\u0003&D\u0001\t\u0013\t9\u0003B\u0001\u0003ICND\u0007\u0003B\u00152'\u0005r!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055b\u0011A\u0002\u001fs_>$h(C\u0001\u001c\u0013\t\u0001$$A\u0004qC\u000e\\\u0017mZ3\n\u0005I\u001a$AB#ji\",'O\u0003\u000215\u0005\t\u0011\tE\u0002&MM\t\u0011A\u0011\t\u0004K\u0019\n\u0013A\u0002\u001fj]&$h\bF\u0001;)\rYD(\u0010\t\u0005!\u0001\u0019\u0012\u0005C\u00035\u0007\u0001\u000fQ\u0007C\u00037\u0007\u0001\u000fq'\u0001\u0003iCNDGC\u0001!D!\tI\u0012)\u0003\u0002C5\t\u0019\u0011J\u001c;\t\u000b\u0011#\u0001\u0019\u0001\u0015\u0002\u0003a\u0004"
)
public class EitherHash extends EitherEq implements Hash {
   private final Hash A;
   private final Hash B;

   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcD$sp(final double x) {
      return Hash.hash$mcD$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public int hash(final Either x) {
      int var2;
      if (x instanceof Left) {
         Left var4 = (Left)x;
         Object xx = var4.value();
         var2 = StaticMethods$.MODULE$.product1HashWithPrefix(this.A.hash(xx), "Left");
      } else {
         if (!(x instanceof Right)) {
            throw new MatchError(x);
         }

         Right var6 = (Right)x;
         Object xx = var6.value();
         var2 = StaticMethods$.MODULE$.product1HashWithPrefix(this.B.hash(xx), "Right");
      }

      return var2;
   }

   public EitherHash(final Hash A, final Hash B) {
      super(A, B);
      this.A = A;
      this.B = B;
   }
}
