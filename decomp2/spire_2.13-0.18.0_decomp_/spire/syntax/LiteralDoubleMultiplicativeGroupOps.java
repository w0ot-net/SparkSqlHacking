package spire.syntax;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%Cq!\u0014\u0001\u0002\u0002\u0013\u0005cjB\u0004U\u001d\u0005\u0005\t\u0012A+\u0007\u000f5q\u0011\u0011!E\u0001-\")\u0001\u0005\u0003C\u00015\")1\f\u0003C\u00039\"9q\rCA\u0001\n\u000bA\u0007b\u00026\t\u0003\u0003%)a\u001b\u0002$\u0019&$XM]1m\t>,(\r\\3Nk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3He>,\bo\u00149t\u0015\ty\u0001#\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002#\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f,bY\u0006\u0019A\u000e[:\u0016\u0003q\u0001\"!F\u000f\n\u0005y1\"A\u0002#pk\ndW-\u0001\u0003mQN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002#IA\u00111\u0005A\u0007\u0002\u001d!)!d\u0001a\u00019\u0005!A\u0005Z5w+\t93\u0006\u0006\u0002)\rR\u0011\u0011\u0006\u000e\t\u0003U-b\u0001\u0001B\u0003-\t\t\u0007QFA\u0001B#\tq\u0013\u0007\u0005\u0002\u0016_%\u0011\u0001G\u0006\u0002\b\u001d>$\b.\u001b8h!\t)\"'\u0003\u00024-\t\u0019\u0011I\\=\t\u000bU\"\u00019\u0001\u001c\u0002\u0005\u00154\bcA\u001cDS9\u0011\u0001\b\u0011\b\u0003syr!AO\u001f\u000e\u0003mR!\u0001\u0010\n\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA \u0011\u0003\u001d\tGnZ3ce\u0006L!!\u0011\"\u0002\u000fA\f7m[1hK*\u0011q\bE\u0005\u0003\t\u0016\u0013QAR5fY\u0012T!!\u0011\"\t\u000b\u001d#\u0001\u0019A\u0015\u0002\u0007ID7/\u0001\u0005iCND7i\u001c3f)\u0005Q\u0005CA\u000bL\u0013\taeCA\u0002J]R\fa!Z9vC2\u001cHCA(S!\t)\u0002+\u0003\u0002R-\t9!i\\8mK\u0006t\u0007bB*\u0007\u0003\u0003\u0005\r!M\u0001\u0004q\u0012\n\u0014a\t'ji\u0016\u0014\u0018\r\u001c#pk\ndW-T;mi&\u0004H.[2bi&4Xm\u0012:pkB|\u0005o\u001d\t\u0003G!\u0019\"\u0001C,\u0011\u0005UA\u0016BA-\u0017\u0005\u0019\te.\u001f*fMR\tQ+\u0001\b%I&4H%\u001a=uK:\u001c\u0018n\u001c8\u0016\u0005u\u000bGC\u00010f)\tyF\r\u0006\u0002aEB\u0011!&\u0019\u0003\u0006Y)\u0011\r!\f\u0005\u0006k)\u0001\u001da\u0019\t\u0004o\r\u0003\u0007\"B$\u000b\u0001\u0004\u0001\u0007\"\u00024\u000b\u0001\u0004\u0011\u0013!\u0002\u0013uQ&\u001c\u0018A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$\"!S5\t\u000b\u0019\\\u0001\u0019\u0001\u0012\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tGC\u00017o)\tyU\u000eC\u0004T\u0019\u0005\u0005\t\u0019A\u0019\t\u000b\u0019d\u0001\u0019\u0001\u0012"
)
public final class LiteralDoubleMultiplicativeGroupOps {
   private final double lhs;

   public static boolean equals$extension(final double $this, final Object x$1) {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final double $this) {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $div$extension(final double $this, final Object rhs, final Field ev) {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.$div$extension($this, rhs, ev);
   }

   public double lhs() {
      return this.lhs;
   }

   public Object $div(final Object rhs, final Field ev) {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.$div$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralDoubleMultiplicativeGroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralDoubleMultiplicativeGroupOps(final double lhs) {
      this.lhs = lhs;
   }
}
