package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005s\u0006C\u00038\u0001\u0011\u0005\u0003\bC\u0003<\u0001\u0011\u0005C\bC\u0003@\u0001\u0011\u0005\u0003\tC\u0003D\u0001\u0011\u0005C\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u0005AJ\u0001\u0006TQ>\u0014Ho\u0014:eKJT!a\u0003\u0007\u0002\u0007M$HMC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0019qc\t\u0014\u000f\u0005a\u0001cBA\r\u001f\u001d\tQR$D\u0001\u001c\u0015\tab\"\u0001\u0004=e>|GOP\u0005\u0002\u001b%\u0011q\u0004D\u0001\bC2<WM\u0019:b\u0013\t\t#%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005}a\u0011B\u0001\u0013&\u0005\u0015y%\u000fZ3s\u0015\t\t#\u0005\u0005\u0002\u0012O%\u0011\u0001F\u0005\u0002\u0006'\"|'\u000f^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003-\u0002\"!\u0005\u0017\n\u00055\u0012\"\u0001B+oSR\f1!Z9w)\r\u00014'\u000e\t\u0003#EJ!A\r\n\u0003\u000f\t{w\u000e\\3b]\")AG\u0001a\u0001M\u0005\t\u0001\u0010C\u00037\u0005\u0001\u0007a%A\u0001z\u0003\u0011qW-\u001d<\u0015\u0007AJ$\bC\u00035\u0007\u0001\u0007a\u0005C\u00037\u0007\u0001\u0007a%\u0001\u0002hiR\u0019\u0001'\u0010 \t\u000bQ\"\u0001\u0019\u0001\u0014\t\u000bY\"\u0001\u0019\u0001\u0014\u0002\u000b\u001d$X-\u001d<\u0015\u0007A\n%\tC\u00035\u000b\u0001\u0007a\u0005C\u00037\u000b\u0001\u0007a%\u0001\u0002miR\u0019\u0001'\u0012$\t\u000bQ2\u0001\u0019\u0001\u0014\t\u000bY2\u0001\u0019\u0001\u0014\u0002\u000b1$X-\u001d<\u0015\u0007AJ%\nC\u00035\u000f\u0001\u0007a\u0005C\u00037\u000f\u0001\u0007a%A\u0004d_6\u0004\u0018M]3\u0015\u00075\u0003\u0016\u000b\u0005\u0002\u0012\u001d&\u0011qJ\u0005\u0002\u0004\u0013:$\b\"\u0002\u001b\t\u0001\u00041\u0003\"\u0002\u001c\t\u0001\u00041\u0003"
)
public interface ShortOrder extends Order.mcS.sp {
   // $FF: synthetic method
   static boolean eqv$(final ShortOrder $this, final short x, final short y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final short x, final short y) {
      return this.eqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final ShortOrder $this, final short x, final short y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final short x, final short y) {
      return this.neqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final ShortOrder $this, final short x, final short y) {
      return $this.gt(x, y);
   }

   default boolean gt(final short x, final short y) {
      return this.gt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final ShortOrder $this, final short x, final short y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final short x, final short y) {
      return this.gteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final ShortOrder $this, final short x, final short y) {
      return $this.lt(x, y);
   }

   default boolean lt(final short x, final short y) {
      return this.lt$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final ShortOrder $this, final short x, final short y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final short x, final short y) {
      return this.lteqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final ShortOrder $this, final short x, final short y) {
      return $this.compare(x, y);
   }

   default int compare(final short x, final short y) {
      return this.compare$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.neqv$mcS$sp(x, y);
   }

   default boolean neqv$mcS$sp(final short x, final short y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.gt$mcS$sp(x, y);
   }

   default boolean gt$mcS$sp(final short x, final short y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.gteqv$mcS$sp(x, y);
   }

   default boolean gteqv$mcS$sp(final short x, final short y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.lt$mcS$sp(x, y);
   }

   default boolean lt$mcS$sp(final short x, final short y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean lteqv$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.lteqv$mcS$sp(x, y);
   }

   default boolean lteqv$mcS$sp(final short x, final short y) {
      return x <= y;
   }

   // $FF: synthetic method
   static int compare$mcS$sp$(final ShortOrder $this, final short x, final short y) {
      return $this.compare$mcS$sp(x, y);
   }

   default int compare$mcS$sp(final short x, final short y) {
      return Integer.signum(x - y);
   }

   static void $init$(final ShortOrder $this) {
   }
}
