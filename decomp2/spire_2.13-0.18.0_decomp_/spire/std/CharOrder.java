package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005s\u0006C\u00038\u0001\u0011\u0005\u0003\bC\u0003<\u0001\u0011\u0005C\bC\u0003@\u0001\u0011\u0005\u0003\tC\u0003D\u0001\u0011\u0005C\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u0005AJA\u0005DQ\u0006\u0014xJ\u001d3fe*\u00111\u0002D\u0001\u0004gR$'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\r92E\n\b\u00031\u0001r!!\u0007\u0010\u000f\u0005iiR\"A\u000e\u000b\u0005qq\u0011A\u0002\u001fs_>$h(C\u0001\u000e\u0013\tyB\"A\u0004bY\u001e,'M]1\n\u0005\u0005\u0012\u0013a\u00029bG.\fw-\u001a\u0006\u0003?1I!\u0001J\u0013\u0003\u000b=\u0013H-\u001a:\u000b\u0005\u0005\u0012\u0003CA\t(\u0013\tA#C\u0001\u0003DQ\u0006\u0014\u0018A\u0002\u0013j]&$H\u0005F\u0001,!\t\tB&\u0003\u0002.%\t!QK\\5u\u0003\r)\u0017O\u001e\u000b\u0004aM*\u0004CA\t2\u0013\t\u0011$CA\u0004C_>dW-\u00198\t\u000bQ\u0012\u0001\u0019\u0001\u0014\u0002\u0003aDQA\u000e\u0002A\u0002\u0019\n\u0011!_\u0001\u0005]\u0016\fh\u000fF\u00021siBQ\u0001N\u0002A\u0002\u0019BQAN\u0002A\u0002\u0019\n!a\u001a;\u0015\u0007Ajd\bC\u00035\t\u0001\u0007a\u0005C\u00037\t\u0001\u0007a%A\u0003hi\u0016\fh\u000fF\u00021\u0003\nCQ\u0001N\u0003A\u0002\u0019BQAN\u0003A\u0002\u0019\n!\u0001\u001c;\u0015\u0007A*e\tC\u00035\r\u0001\u0007a\u0005C\u00037\r\u0001\u0007a%A\u0003mi\u0016\fh\u000fF\u00021\u0013*CQ\u0001N\u0004A\u0002\u0019BQAN\u0004A\u0002\u0019\nqaY8na\u0006\u0014X\rF\u0002N!F\u0003\"!\u0005(\n\u0005=\u0013\"aA%oi\")A\u0007\u0003a\u0001M!)a\u0007\u0003a\u0001M\u0001"
)
public interface CharOrder extends Order.mcC.sp {
   // $FF: synthetic method
   static boolean eqv$(final CharOrder $this, final char x, final char y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final char x, final char y) {
      return this.eqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final CharOrder $this, final char x, final char y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final char x, final char y) {
      return this.neqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final CharOrder $this, final char x, final char y) {
      return $this.gt(x, y);
   }

   default boolean gt(final char x, final char y) {
      return this.gt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final CharOrder $this, final char x, final char y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final char x, final char y) {
      return this.gteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final CharOrder $this, final char x, final char y) {
      return $this.lt(x, y);
   }

   default boolean lt(final char x, final char y) {
      return this.lt$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final CharOrder $this, final char x, final char y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final char x, final char y) {
      return this.lteqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final CharOrder $this, final char x, final char y) {
      return $this.compare(x, y);
   }

   default int compare(final char x, final char y) {
      return this.compare$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.neqv$mcC$sp(x, y);
   }

   default boolean neqv$mcC$sp(final char x, final char y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.gt$mcC$sp(x, y);
   }

   default boolean gt$mcC$sp(final char x, final char y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.gteqv$mcC$sp(x, y);
   }

   default boolean gteqv$mcC$sp(final char x, final char y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.lt$mcC$sp(x, y);
   }

   default boolean lt$mcC$sp(final char x, final char y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean lteqv$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.lteqv$mcC$sp(x, y);
   }

   default boolean lteqv$mcC$sp(final char x, final char y) {
      return x <= y;
   }

   // $FF: synthetic method
   static int compare$mcC$sp$(final CharOrder $this, final char x, final char y) {
      return $this.compare$mcC$sp(x, y);
   }

   default int compare$mcC$sp(final char x, final char y) {
      return x < y ? -1 : (x > y ? 1 : 0);
   }

   static void $init$(final CharOrder $this) {
   }
}
