package breeze.stats.distributions;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0019\u0005!\u0004C\u0003,\u0001\u0011\u0005AFA\u0004EK:\u001c\u0018\u000e^=\u000b\u0005\u00199\u0011!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002\t\u0013\u0005)1\u000f^1ug*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ti!e\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\f\u0011\u0005=9\u0012B\u0001\r\u0011\u0005\u0011)f.\u001b;\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005mq\u0002CA\b\u001d\u0013\ti\u0002C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006?\t\u0001\r\u0001I\u0001\u0002qB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019\u0003A1\u0001%\u0005\u0005!\u0016CA\u0013)!\tya%\u0003\u0002(!\t9aj\u001c;iS:<\u0007CA\b*\u0013\tQ\u0003CA\u0002B]f\f\u0001\u0002\\8h\u0003B\u0004H.\u001f\u000b\u000375BQaH\u0002A\u0002\u0001\u0002"
)
public interface Density {
   double apply(final Object x);

   // $FF: synthetic method
   static double logApply$(final Density $this, final Object x) {
      return $this.logApply(x);
   }

   default double logApply(final Object x) {
      return this.apply(x);
   }

   static void $init$(final Density $this) {
   }
}
