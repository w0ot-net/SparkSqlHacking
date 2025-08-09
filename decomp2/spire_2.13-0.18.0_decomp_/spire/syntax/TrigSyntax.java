package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\u0006Ue&<7+\u001f8uCbT!!\u0002\u0004\u0002\rMLh\u000e^1y\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0003\u0001)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\tY1#\u0003\u0002\u0015\u0019\t!QK\\5u\u0003\u001d!(/[4PaN,\"aF\u0010\u0015\u0005a\u0001DCA\r)!\rQ2$H\u0007\u0002\t%\u0011A\u0004\u0002\u0002\b)JLwm\u00149t!\tqr\u0004\u0004\u0001\u0005\u000b\u0001\u0012!\u0019A\u0011\u0003\u0003\u0005\u000b\"AI\u0013\u0011\u0005-\u0019\u0013B\u0001\u0013\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0003\u0014\n\u0005\u001db!aA!os\"9\u0011FAA\u0001\u0002\bQ\u0013aC3wS\u0012,gnY3%ce\u00022a\u000b\u0018\u001e\u001b\u0005a#BA\u0017\u0007\u0003\u001d\tGnZ3ce\u0006L!a\f\u0017\u0003\tQ\u0013\u0018n\u001a\u0005\u0006c\t\u0001\r!H\u0001\u0002C\u0002"
)
public interface TrigSyntax {
   // $FF: synthetic method
   static TrigOps trigOps$(final TrigSyntax $this, final Object a, final Trig evidence$19) {
      return $this.trigOps(a, evidence$19);
   }

   default TrigOps trigOps(final Object a, final Trig evidence$19) {
      return new TrigOps(a, evidence$19);
   }

   static void $init$(final TrigSyntax $this) {
   }
}
