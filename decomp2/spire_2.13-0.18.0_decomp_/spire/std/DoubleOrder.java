package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0013\u0007C\u0003:\u0001\u0011\u0005#\bC\u0003>\u0001\u0011\u0005c\bC\u0003B\u0001\u0011\u0005#\tC\u0003F\u0001\u0011\u0005c\tC\u0003J\u0001\u0011\u0005#\nC\u0003N\u0001\u0011\u0005c\nC\u0003R\u0001\u0011\u0005#\u000bC\u0003V\u0001\u0011\u0005aKA\u0006E_V\u0014G.Z(sI\u0016\u0014(BA\u0007\u000f\u0003\r\u0019H\u000f\u001a\u0006\u0002\u001f\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u00042!G\u0013)\u001d\tQ\"E\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011a\u0004E\u0001\u0007yI|w\u000e\u001e \n\u0003=I!!\t\b\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\tc\"\u0003\u0002'O\t)qJ\u001d3fe*\u00111\u0005\n\t\u0003'%J!A\u000b\u000b\u0003\r\u0011{WO\u00197f\u0003\u0019!\u0013N\\5uIQ\tQ\u0006\u0005\u0002\u0014]%\u0011q\u0006\u0006\u0002\u0005+:LG/A\u0002fcZ$2AM\u001b8!\t\u00192'\u0003\u00025)\t9!i\\8mK\u0006t\u0007\"\u0002\u001c\u0003\u0001\u0004A\u0013!\u0001=\t\u000ba\u0012\u0001\u0019\u0001\u0015\u0002\u0003e\fAA\\3rmR\u0019!g\u000f\u001f\t\u000bY\u001a\u0001\u0019\u0001\u0015\t\u000ba\u001a\u0001\u0019\u0001\u0015\u0002\u0005\u001d$Hc\u0001\u001a@\u0001\")a\u0007\u0002a\u0001Q!)\u0001\b\u0002a\u0001Q\u0005)q\r^3rmR\u0019!g\u0011#\t\u000bY*\u0001\u0019\u0001\u0015\t\u000ba*\u0001\u0019\u0001\u0015\u0002\u00051$Hc\u0001\u001aH\u0011\")aG\u0002a\u0001Q!)\u0001H\u0002a\u0001Q\u0005)A\u000e^3rmR\u0019!g\u0013'\t\u000bY:\u0001\u0019\u0001\u0015\t\u000ba:\u0001\u0019\u0001\u0015\u0002\u00075Lg\u000eF\u0002)\u001fBCQA\u000e\u0005A\u0002!BQ\u0001\u000f\u0005A\u0002!\n1!\\1y)\rA3\u000b\u0016\u0005\u0006m%\u0001\r\u0001\u000b\u0005\u0006q%\u0001\r\u0001K\u0001\bG>l\u0007/\u0019:f)\r9&l\u0017\t\u0003'aK!!\u0017\u000b\u0003\u0007%sG\u000fC\u00037\u0015\u0001\u0007\u0001\u0006C\u00039\u0015\u0001\u0007\u0001\u0006"
)
public interface DoubleOrder extends Order.mcD.sp {
   // $FF: synthetic method
   static boolean eqv$(final DoubleOrder $this, final double x, final double y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final double x, final double y) {
      return this.eqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final DoubleOrder $this, final double x, final double y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final double x, final double y) {
      return this.neqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final DoubleOrder $this, final double x, final double y) {
      return $this.gt(x, y);
   }

   default boolean gt(final double x, final double y) {
      return this.gt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final DoubleOrder $this, final double x, final double y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final double x, final double y) {
      return this.gteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final DoubleOrder $this, final double x, final double y) {
      return $this.lt(x, y);
   }

   default boolean lt(final double x, final double y) {
      return this.lt$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final DoubleOrder $this, final double x, final double y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final double x, final double y) {
      return this.lteqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double min$(final DoubleOrder $this, final double x, final double y) {
      return $this.min(x, y);
   }

   default double min(final double x, final double y) {
      return this.min$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double max$(final DoubleOrder $this, final double x, final double y) {
      return $this.max(x, y);
   }

   default double max(final double x, final double y) {
      return this.max$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final DoubleOrder $this, final double x, final double y) {
      return $this.compare(x, y);
   }

   default int compare(final double x, final double y) {
      return this.compare$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.neqv$mcD$sp(x, y);
   }

   default boolean neqv$mcD$sp(final double x, final double y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.gt$mcD$sp(x, y);
   }

   default boolean gt$mcD$sp(final double x, final double y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.gteqv$mcD$sp(x, y);
   }

   default boolean gteqv$mcD$sp(final double x, final double y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.lt$mcD$sp(x, y);
   }

   default boolean lt$mcD$sp(final double x, final double y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean lteqv$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.lteqv$mcD$sp(x, y);
   }

   default boolean lteqv$mcD$sp(final double x, final double y) {
      return x <= y;
   }

   // $FF: synthetic method
   static double min$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.min$mcD$sp(x, y);
   }

   default double min$mcD$sp(final double x, final double y) {
      return Math.min(x, y);
   }

   // $FF: synthetic method
   static double max$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.max$mcD$sp(x, y);
   }

   default double max$mcD$sp(final double x, final double y) {
      return Math.max(x, y);
   }

   // $FF: synthetic method
   static int compare$mcD$sp$(final DoubleOrder $this, final double x, final double y) {
      return $this.compare$mcD$sp(x, y);
   }

   default int compare$mcD$sp(final double x, final double y) {
      return Double.compare(x, y);
   }

   static void $init$(final DoubleOrder $this) {
   }
}
