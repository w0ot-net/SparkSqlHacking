package spire.math;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u00011\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0005\u0006%\u0002!\te\u0015\u0005\u0006-\u0002!\te\u0016\u0005\u00065\u0002!\te\u0017\u0005\u0006=\u0002!\te\u0018\u0002\u0015'\u000e\fG.Y(sI\u0016\u0014\u0018N\\4Xe\u0006\u0004\b/\u001a:\u000b\u0005-a\u0011\u0001B7bi\"T\u0011!D\u0001\u0006gBL'/Z\u000b\u0003\u001fy\u00192\u0001\u0001\t\u0019!\t\tb#D\u0001\u0013\u0015\t\u0019B#\u0001\u0003mC:<'\"A\u000b\u0002\t)\fg/Y\u0005\u0003/I\u0011aa\u00142kK\u000e$\bcA\r\u001b95\t!\"\u0003\u0002\u001c\u0015\tQ2kY1mC>\u0013H-\u001a:j]\u001e<&/\u00199qKJ\u001cu.\u001c9biB\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001\"\u0005\u0005\t5\u0001A\t\u0003E!\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012qAT8uQ&tw\r\u0005\u0002$S%\u0011!\u0006\n\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u0001.!\t\u0019c&\u0003\u00020I\t!QK\\5u\u0003\u0015y'\u000fZ3s+\u0005\u0011\u0004cA\u001a@99\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0011BA\u001e\r\u0003\u001d\tGnZ3ce\u0006L!!\u0010 \u0002\u000fA\f7m[1hK*\u00111\bD\u0005\u0003\u0001\u0006\u0013Qa\u0014:eKJT!!\u0010 \u0002\u000f\r|W\u000e]1sKR\u0019AiR%\u0011\u0005\r*\u0015B\u0001$%\u0005\rIe\u000e\u001e\u0005\u0006\u0011\u000e\u0001\r\u0001H\u0001\u0002q\")!j\u0001a\u00019\u0005\t\u00110A\u0003fcVLg\u000fF\u0002N!F\u0003\"a\t(\n\u0005=##a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0011\u0012\u0001\r\u0001\b\u0005\u0006\u0015\u0012\u0001\r\u0001H\u0001\u0003OR$2!\u0014+V\u0011\u0015AU\u00011\u0001\u001d\u0011\u0015QU\u00011\u0001\u001d\u0003\u00119G/Z9\u0015\u00075C\u0016\fC\u0003I\r\u0001\u0007A\u0004C\u0003K\r\u0001\u0007A$\u0001\u0002miR\u0019Q\nX/\t\u000b!;\u0001\u0019\u0001\u000f\t\u000b);\u0001\u0019\u0001\u000f\u0002\t1$X-\u001d\u000b\u0004\u001b\u0002\f\u0007\"\u0002%\t\u0001\u0004a\u0002\"\u0002&\t\u0001\u0004a\u0002"
)
public interface ScalaOrderingWrapper extends ScalaOrderingWrapperCompat {
   Order order();

   // $FF: synthetic method
   static int compare$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.compare(x, y);
   }

   default int compare(final Object x, final Object y) {
      return this.order().compare(x, y);
   }

   // $FF: synthetic method
   static boolean equiv$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final Object x, final Object y) {
      return this.order().eqv(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Object x, final Object y) {
      return this.order().gt(x, y);
   }

   // $FF: synthetic method
   static boolean gteq$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.gteq(x, y);
   }

   default boolean gteq(final Object x, final Object y) {
      return this.order().gteqv(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Object x, final Object y) {
      return this.order().lt(x, y);
   }

   // $FF: synthetic method
   static boolean lteq$(final ScalaOrderingWrapper $this, final Object x, final Object y) {
      return $this.lteq(x, y);
   }

   default boolean lteq(final Object x, final Object y) {
      return this.order().lteqv(x, y);
   }

   static void $init$(final ScalaOrderingWrapper $this) {
   }
}
