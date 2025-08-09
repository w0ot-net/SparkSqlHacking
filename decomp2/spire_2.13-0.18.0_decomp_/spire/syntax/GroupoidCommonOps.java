package spire.syntax;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Groupoid;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3AAB\u0004\u0003\u0019!AA\u0003\u0001B\u0001B\u0003%Q\u0003\u0003\u0005!\u0001\t\u0005\t\u0015a\u0003\"\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u0015\u0011\u0004\u0001\"\u00014\u0005E9%o\\;q_&$7i\\7n_:|\u0005o\u001d\u0006\u0003\u0011%\taa]=oi\u0006D(\"\u0001\u0006\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011QbF\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0017a\u00017igB\u0011ac\u0006\u0007\u0001\t\u0015A\u0002A1\u0001\u001a\u0005\u0005\t\u0015C\u0001\u000e\u001e!\ty1$\u0003\u0002\u001d!\t9aj\u001c;iS:<\u0007CA\b\u001f\u0013\ty\u0002CA\u0002B]f\f!!\u001a<\u0011\u0007\t:S#D\u0001$\u0015\t!S%A\u0004qCJ$\u0018.\u00197\u000b\u0005\u0019J\u0011aB1mO\u0016\u0014'/Y\u0005\u0003Q\r\u0012\u0001b\u0012:pkB|\u0017\u000eZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-zCC\u0001\u0017/!\ri\u0003!F\u0007\u0002\u000f!)\u0001e\u0001a\u0002C!)Ac\u0001a\u0001+\u00059\u0011N\u001c<feN,W#A\u000b\u0002\t%\u001c\u0018\n\u001a\u000b\u0003i]\u0002\"aD\u001b\n\u0005Y\u0002\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006q\u0015\u0001\u001d!O\u0001\u0004KZ\f\u0004c\u0001\u001eE+9\u00111H\u0011\b\u0003y\u0005s!!\u0010!\u000e\u0003yR!aP\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0011B\u0001\u0014\n\u0013\t\u0019U%A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00153%AA#r\u0015\t\u0019U\u0005"
)
public final class GroupoidCommonOps {
   private final Object lhs;
   private final Groupoid ev;

   public Object inverse() {
      return this.ev.inverse(this.lhs);
   }

   public boolean isId(final Eq ev1) {
      return this.ev.isId(this.lhs, ev1);
   }

   public GroupoidCommonOps(final Object lhs, final Groupoid ev) {
      this.lhs = lhs;
      this.ev = ev;
   }
}
