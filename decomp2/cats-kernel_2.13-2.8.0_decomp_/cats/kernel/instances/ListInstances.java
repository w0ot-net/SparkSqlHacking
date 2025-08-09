package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003>\u0001\u0011\raHA\u0007MSN$\u0018J\\:uC:\u001cWm\u001d\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0004\u0001M\u0019\u0001!D\n\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\t!R#D\u0001\u0006\u0013\t1RA\u0001\bMSN$\u0018J\\:uC:\u001cWm]\u0019\u0002\r\u0011Jg.\u001b;%)\u0005I\u0002C\u0001\b\u001b\u0013\tYrB\u0001\u0003V]&$\u0018!G2biN\\UM\u001d8fYN#Hm\u0014:eKJ4uN\u001d'jgR,\"AH\u0019\u0015\u0005}Q\u0004c\u0001\u0011\"G5\tq!\u0003\u0002#\u000f\t)qJ\u001d3feB\u0019A\u0005L\u0018\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\f\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002,\u001f\u00059\u0001/Y2lC\u001e,\u0017BA\u0017/\u0005\u0011a\u0015n\u001d;\u000b\u0005-z\u0001C\u0001\u00192\u0019\u0001!QA\r\u0002C\u0002M\u0012\u0011!Q\t\u0003i]\u0002\"AD\u001b\n\u0005Yz!a\u0002(pi\"Lgn\u001a\t\u0003\u001daJ!!O\b\u0003\u0007\u0005s\u0017\u0010C\u0004<\u0005\u0005\u0005\t9\u0001\u001f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002!C=\n!dY1ug.+'O\\3m'R$Wj\u001c8pS\u00124uN\u001d'jgR,\"aP#\u0016\u0003\u0001\u00032\u0001I!D\u0013\t\u0011uA\u0001\u0004N_:|\u0017\u000e\u001a\t\u0004I1\"\u0005C\u0001\u0019F\t\u0015\u00114A1\u00014Q\t\u0001q\t\u0005\u0002I\u001f:\u0011\u0011\n\u0014\b\u0003A)K!aS\u0004\u0002\r\r|W\u000e]1u\u0013\tie*\u0001\u000btG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003\u0017\u001eI!\u0001U)\u0003eM,\b\u000f\u001d:fgN,f.^:fI&k\u0007o\u001c:u/\u0006\u0014h.\u001b8h\r>\u00148kY1mCZ+'o]5p]N\u0003XmY5gS\u000eT!!\u0014("
)
public interface ListInstances extends ListInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForList$(final ListInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForList(evidence$1);
   }

   default Order catsKernelStdOrderForList(final Order evidence$1) {
      return new ListOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForList$(final ListInstances $this) {
      return $this.catsKernelStdMonoidForList();
   }

   default Monoid catsKernelStdMonoidForList() {
      return new ListMonoid();
   }

   static void $init$(final ListInstances $this) {
   }
}
