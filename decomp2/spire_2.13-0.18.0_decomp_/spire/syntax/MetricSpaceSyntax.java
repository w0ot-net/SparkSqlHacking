package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\tNKR\u0014\u0018nY*qC\u000e,7+\u001f8uCbT!!\u0002\u0004\u0002\rMLh\u000e^1y\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tA!\u0003\u0002\u0014\t\t\tb+Z2u_J\u001c\u0006/Y2f'ftG/\u0019=\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\u0006\u0018\u0013\tABB\u0001\u0003V]&$\u0018AD7fiJL7m\u00159bG\u0016|\u0005o]\u000b\u00037\u0005\"\"\u0001\b\u0016\u0011\u0007Eir$\u0003\u0002\u001f\t\tqQ*\u001a;sS\u000e\u001c\u0006/Y2f\u001fB\u001c\bC\u0001\u0011\"\u0019\u0001!QA\t\u0002C\u0002\r\u0012\u0011AV\t\u0003I\u001d\u0002\"aC\u0013\n\u0005\u0019b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017!J!!\u000b\u0007\u0003\u0007\u0005s\u0017\u0010C\u0003,\u0005\u0001\u0007q$A\u0001w\u0001"
)
public interface MetricSpaceSyntax extends VectorSpaceSyntax {
   // $FF: synthetic method
   static MetricSpaceOps metricSpaceOps$(final MetricSpaceSyntax $this, final Object v) {
      return $this.metricSpaceOps(v);
   }

   default MetricSpaceOps metricSpaceOps(final Object v) {
      return new MetricSpaceOps(v);
   }

   static void $init$(final MetricSpaceSyntax $this) {
   }
}
