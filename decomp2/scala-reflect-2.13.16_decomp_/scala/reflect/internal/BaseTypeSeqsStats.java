package scala.reflect.internal;

import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;

@ScalaSignature(
   bytes = "\u0006\u0005!2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005A\"\t\u0005\u0006#\u0001!\tA\u0005\u0005\b-\u0001\u0011\r\u0011\"\u0001\u0018\u0011\u001d\u0001\u0003A1A\u0005\u0002]\u0011\u0011CQ1tKRK\b/Z*fcN\u001cF/\u0019;t\u0015\t1q!\u0001\u0005j]R,'O\\1m\u0015\tA\u0011\"A\u0004sK\u001adWm\u0019;\u000b\u0003)\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011abD\u0007\u0002\u0013%\u0011\u0001#\u0003\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\b\u0015\u0013\t)\u0012B\u0001\u0003V]&$\u0018\u0001\u00052bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8u+\u0005A\u0002CA\r\u001b\u001b\u0005\u0001\u0011BA\u000e\u001d\u0005\u001d\u0019u.\u001e8uKJL!!\b\u0010\u0003\u0015M#\u0018\r^5ti&\u001c7O\u0003\u0002 \u000b\u0005!Q\u000f^5m\u0003M\u0011\u0017m]3UsB,7+Z9MK:$v\u000e^1m%\r\u0011CE\n\u0004\u0005G\u0001\u0001\u0011E\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002&\u00015\tQ\u0001\u0005\u0002(95\ta\u0004"
)
public interface BaseTypeSeqsStats {
   void scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqLenTotal_$eq(final Statistics.Counter x$1);

   Statistics.Counter baseTypeSeqCount();

   Statistics.Counter baseTypeSeqLenTotal();

   static void $init$(final BaseTypeSeqsStats $this) {
      $this.scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqCount_$eq(((Statistics)$this).newCounter("#base type seqs", .MODULE$));
      $this.scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqLenTotal_$eq(((Statistics)$this).newRelCounter("avg base type seq length", $this.baseTypeSeqCount()));
   }
}
