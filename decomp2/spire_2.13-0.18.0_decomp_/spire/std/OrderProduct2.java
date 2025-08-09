package spire.std;

import cats.kernel.Order;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u00069\u0002!\t!\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006a\u0002!\t%\u001d\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r\u001e\u001a\u000b\u0005!I\u0011aA:uI*\t!\"A\u0003ta&\u0014X-F\u0002\rS5\u001bB\u0001A\u0007\u00141B\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u0011%\u001d\t)bD\u0004\u0002\u001799\u0011qcG\u0007\u00021)\u0011\u0011DG\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t!\"\u0003\u0002\u001e\u0013\u00059\u0011\r\\4fEJ\f\u0017BA\u0010!\u0003\u001d\u0001\u0018mY6bO\u0016T!!H\u0005\n\u0005\t\u001a#!B(sI\u0016\u0014(BA\u0010!!\u0011qQe\n'\n\u0005\u0019z!A\u0002+va2,'\u0007\u0005\u0002)S1\u0001A!\u0003\u0016\u0001A\u0003\u0005\tQ1\u0001,\u0005\u0005\t\u0015C\u0001\u00170!\tqQ&\u0003\u0002/\u001f\t9aj\u001c;iS:<\u0007C\u0001\b1\u0013\t\ttBA\u0002B]fDc!K\u001a7{\t;\u0005C\u0001\b5\u0013\t)tBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00128qiJdB\u0001\b9\u0013\tIt\"A\u0002J]R\fD\u0001J\u001e=!9\u0011q\u0003P\u0005\u0002!E*1EP B\u0001:\u0011abP\u0005\u0003\u0001>\tA\u0001T8oOF\"Ae\u000f\u001f\u0011c\u0015\u00193\t\u0012$F\u001d\tqA)\u0003\u0002F\u001f\u0005)a\t\\8biF\"Ae\u000f\u001f\u0011c\u0015\u0019\u0003*S&K\u001d\tq\u0011*\u0003\u0002K\u001f\u00051Ai\\;cY\u0016\fD\u0001J\u001e=!A\u0011\u0001&\u0014\u0003\n\u001d\u0002\u0001\u000b\u0011!AC\u0002-\u0012\u0011A\u0011\u0015\u0007\u001bN\u0002&\u000b\u0016,2\u000b\r:\u0004(U\u001d2\t\u0011ZD\bE\u0019\u0006Gyz4\u000bQ\u0019\u0005Imb\u0004#M\u0003$\u0007\u0012+V)\r\u0003%wq\u0002\u0012'B\u0012I\u0013^S\u0015\u0007\u0002\u0013<yA\u0001B!\u0017.(\u00196\tq!\u0003\u0002\\\u000f\tQQ)\u001d)s_\u0012,8\r\u001e\u001a\u0002\r\u0011Jg.\u001b;%)\u0005q\u0006C\u0001\b`\u0013\t\u0001wB\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\rE\u0002\u0015C\u001d\n!b\u001d;sk\u000e$XO]33+\u00051\u0007c\u0001\u000b\"\u0019\u000691m\\7qCJ,GcA5m]B\u0011aB[\u0005\u0003W>\u00111!\u00138u\u0011\u0015iG\u00011\u0001%\u0003\tA\b\u0007C\u0003p\t\u0001\u0007A%\u0001\u0002yc\u0005\u0019Q-\u001d<\u0015\u0007I,h\u000f\u0005\u0002\u000fg&\u0011Ao\u0004\u0002\b\u0005>|G.Z1o\u0011\u0015iW\u00011\u0001%\u0011\u0015yW\u00011\u0001%\u0001"
)
public interface OrderProduct2 extends Order, EqProduct2 {
   Order structure1();

   Order structure2();

   // $FF: synthetic method
   static int compare$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1().compare(x0._1(), x1._1());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2().compare(x0._2(), x1._2());
         var10000 = cmp != 0 ? cmp : 0;
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1) == 0;
   }

   // $FF: synthetic method
   static Order structure1$mcD$sp$(final OrderProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Order structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Order structure1$mcF$sp$(final OrderProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Order structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Order structure1$mcI$sp$(final OrderProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Order structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Order structure1$mcJ$sp$(final OrderProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Order structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Order structure2$mcD$sp$(final OrderProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Order structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Order structure2$mcF$sp$(final OrderProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Order structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Order structure2$mcI$sp$(final OrderProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Order structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Order structure2$mcJ$sp$(final OrderProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Order structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static int compare$mcDD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDD$sp(x0, x1);
   }

   default int compare$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDF$sp(x0, x1);
   }

   default int compare$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDI$sp(x0, x1);
   }

   default int compare$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDJ$sp(x0, x1);
   }

   default int compare$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcFD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcFD$sp(x0, x1);
   }

   default int compare$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcFF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcFF$sp(x0, x1);
   }

   default int compare$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcFI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcFI$sp(x0, x1);
   }

   default int compare$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcFJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcFJ$sp(x0, x1);
   }

   default int compare$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcID$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcID$sp(x0, x1);
   }

   default int compare$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcIF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcIF$sp(x0, x1);
   }

   default int compare$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcII$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcII$sp(x0, x1);
   }

   default int compare$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcIJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcIJ$sp(x0, x1);
   }

   default int compare$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcJD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcJD$sp(x0, x1);
   }

   default int compare$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcJF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcJF$sp(x0, x1);
   }

   default int compare$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcJI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcJI$sp(x0, x1);
   }

   default int compare$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcJJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcJJ$sp(x0, x1);
   }

   default int compare$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDD$sp(x0, x1);
   }

   default boolean eqv$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDF$sp(x0, x1);
   }

   default boolean eqv$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDI$sp(x0, x1);
   }

   default boolean eqv$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDJ$sp(x0, x1);
   }

   default boolean eqv$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFD$sp(x0, x1);
   }

   default boolean eqv$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFF$sp(x0, x1);
   }

   default boolean eqv$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFI$sp(x0, x1);
   }

   default boolean eqv$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFJ$sp(x0, x1);
   }

   default boolean eqv$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcID$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcID$sp(x0, x1);
   }

   default boolean eqv$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcIF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcIF$sp(x0, x1);
   }

   default boolean eqv$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcII$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcII$sp(x0, x1);
   }

   default boolean eqv$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcIJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcIJ$sp(x0, x1);
   }

   default boolean eqv$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJD$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJD$sp(x0, x1);
   }

   default boolean eqv$mcJD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJF$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJF$sp(x0, x1);
   }

   default boolean eqv$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJI$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJI$sp(x0, x1);
   }

   default boolean eqv$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJJ$sp$(final OrderProduct2 $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJJ$sp(x0, x1);
   }

   default boolean eqv$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv(x0, x1);
   }

   static void $init$(final OrderProduct2 $this) {
   }
}
