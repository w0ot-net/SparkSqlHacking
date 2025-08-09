package org.apache.spark.sql.catalyst.util;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:Q!\u0003\u0006\t\u0002]1Q!\u0007\u0006\t\u0002iAQ!I\u0001\u0005\u0002\t*AaI\u0001\u0001I!9\u0001&\u0001b\u0001\n\u0003I\u0003B\u0002\u0016\u0002A\u0003%A\u0005C\u0004,\u0003\t\u0007I\u0011A\u0015\t\r1\n\u0001\u0015!\u0003%\u0011\u001di\u0013!!A\u0005\n9\nA#\u00138uKJ4\u0018\r\\*ue&twm\u0015;zY\u0016\u001c(BA\u0006\r\u0003\u0011)H/\u001b7\u000b\u00055q\u0011\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005=\u0001\u0012aA:rY*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005a\tQ\"\u0001\u0006\u0003)%sG/\u001a:wC2\u001cFO]5oON#\u0018\u0010\\3t'\t\t1\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SDA\u0006F]VlWM]1uS>t\u0017A\u0002\u001fj]&$h\bF\u0001\u0018\u00055Ie\u000e^3sm\u0006d7\u000b^=mKB\u0011QEJ\u0007\u0002\u0003%\u0011qe\b\u0002\u0006-\u0006dW/Z\u0001\u000b\u0003:\u001b\u0016jX*U32+U#\u0001\u0013\u0002\u0017\u0005s5+S0T)fcU\tI\u0001\u000b\u0011&3ViX*U32+\u0015a\u0003%J-\u0016{6\u000bV-M\u000b\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\f\t\u0003aUj\u0011!\r\u0006\u0003eM\nA\u0001\\1oO*\tA'\u0001\u0003kCZ\f\u0017B\u0001\u001c2\u0005\u0019y%M[3di\u0002"
)
public final class IntervalStringStyles {
   public static Enumeration.Value HIVE_STYLE() {
      return IntervalStringStyles$.MODULE$.HIVE_STYLE();
   }

   public static Enumeration.Value ANSI_STYLE() {
      return IntervalStringStyles$.MODULE$.ANSI_STYLE();
   }

   public static Enumeration.ValueSet ValueSet() {
      return IntervalStringStyles$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return IntervalStringStyles$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return IntervalStringStyles$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return IntervalStringStyles$.MODULE$.apply(x);
   }

   public static int maxId() {
      return IntervalStringStyles$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return IntervalStringStyles$.MODULE$.values();
   }

   public static String toString() {
      return IntervalStringStyles$.MODULE$.toString();
   }
}
