package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.std.ArrayVectorEq;
import spire.std.ArrayVectorOrder;
import spire.std.MapVectorEq;
import spire.std.SeqVectorEq;
import spire.std.SeqVectorOrder;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rq!B\u0003\u0007\u0011\u0003Ya!B\u0007\u0007\u0011\u0003q\u0001\"\u0002\r\u0002\t\u0003I\u0002\"\u0002\u000e\u0002\t\u0007Y\u0002\"B,\u0002\t\u0007A\u0016a\u0003<fGR|'o\u0014:eKJT!a\u0002\u0005\u0002\u0011=\u0004H/[8oC2T\u0011!C\u0001\u0006gBL'/Z\u0002\u0001!\ta\u0011!D\u0001\u0007\u0005-1Xm\u0019;pe>\u0013H-\u001a:\u0014\u0007\u0005yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0019YI!a\u0006\u0004\u0003\u001dY+7\r^8s\u001fJ$WM\u001d'po\u00061A(\u001b8jiz\"\u0012aC\u0001\tg\u0016\fxJ\u001d3feV\u0019A$J\u0018\u0015\u0007uy\u0014\u000b\u0005\u0003\u001fC\rrS\"A\u0010\u000b\u0005\u0001B\u0011aA:uI&\u0011!e\b\u0002\u000f'\u0016\fh+Z2u_J|%\u000fZ3s!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u001a!\u0019A\u0014\u0003\u0003\u0005\u000b\"\u0001K\u0016\u0011\u0005AI\u0013B\u0001\u0016\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0005\u0017\n\u00055\n\"aA!osB\u0019AeL\u0012\u0005\u000bA\u001a!\u0019A\u0019\u0003\u0005\r\u001bUC\u0001\u001a;#\tA3\u0007E\u00035oeZd(D\u00016\u0015\t1\u0014#\u0001\u0006d_2dWm\u0019;j_:L!\u0001O\u001b\u0003\rM+\u0017o\u00149t!\t!#\bB\u0003'_\t\u0007q\u0005\u0005\u00025y%\u0011Q(\u000e\u0002\u0004'\u0016\f\bc\u0001\u00130s!)\u0001i\u0001a\u0002\u0003\u0006\u0011\u0011\t\r\t\u0004\u0005:\u001bcBA\"L\u001d\t!\u0015J\u0004\u0002F\u00116\taI\u0003\u0002H\u0015\u00051AH]8pizJ\u0011!C\u0005\u0003\u0015\"\tq!\u00197hK\n\u0014\u0018-\u0003\u0002M\u001b\u00069\u0001/Y2lC\u001e,'B\u0001&\t\u0013\ty\u0005KA\u0003Pe\u0012,'O\u0003\u0002M\u001b\")!k\u0001a\u0002'\u00061Qn\u001c3vY\u0016\u0004B\u0001V+/G5\tQ*\u0003\u0002W\u001b\n91)T8ek2,\u0017AC1se\u0006LxJ\u001d3feV\u0011\u0011L\u0018\u000b\u00045fd\bc\u0001\u0010\\;&\u0011Al\b\u0002\u0011\u0003J\u0014\u0018-\u001f,fGR|'o\u0014:eKJ\u0004\"\u0001\n0\u0005\u0013\u0019\"\u0001\u0015!A\u0001\u0006\u00049\u0003F\u00020aG*|G\u000f\u0005\u0002\u0011C&\u0011!-\u0005\u0002\fgB,7-[1mSj,G-M\u0003$I\u0016<gM\u0004\u0002\u0011K&\u0011a-E\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013iSJq!!R5\n\u0003I\tTaI6m]6t!\u0001\u00057\n\u00055\f\u0012\u0001\u0002'p]\u001e\fD\u0001\n5j%E*1\u0005]9te:\u0011\u0001#]\u0005\u0003eF\tQA\u00127pCR\fD\u0001\n5j%E*1%\u001e<yo:\u0011\u0001C^\u0005\u0003oF\ta\u0001R8vE2,\u0017\u0007\u0002\u0013iSJAQA\u001f\u0003A\u0004m\f!!\u001a<\u0011\u0007\tsU\fC\u0003S\t\u0001\u000fQ\u0010\u0005\u0003U+zl\u0006c\u0001\t\u0000;&\u0019\u0011\u0011A\t\u0003\u000b\u0005\u0013(/Y="
)
public final class vectorOrder {
   public static ArrayVectorOrder arrayOrder(final Order ev, final CModule module) {
      return vectorOrder$.MODULE$.arrayOrder(ev, module);
   }

   public static SeqVectorOrder seqOrder(final Order A0, final CModule module) {
      return vectorOrder$.MODULE$.seqOrder(A0, module);
   }

   public static MapVectorEq mapEq(final Eq V0, final CModule module) {
      return vectorOrder$.MODULE$.mapEq(V0, module);
   }

   public static ArrayVectorEq arrayEq(final Eq ev, final CModule module) {
      return vectorOrder$.MODULE$.arrayEq(ev, module);
   }

   public static SeqVectorEq seqEq(final Eq A0, final CModule module) {
      return vectorOrder$.MODULE$.seqEq(A0, module);
   }
}
