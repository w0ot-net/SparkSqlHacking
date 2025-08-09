package org.apache.spark.streaming.api.python;

import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q!\u0002\u0004\u0001\rIA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t]\u0001\u0011\t\u0011)A\u0005_!)!\u0007\u0001C\u0001g!)1\b\u0001C!y\tA\u0002+\u001f;i_:$&/\u00198tM>\u0014X.\u001a3E'R\u0014X-Y7\u000b\u0005\u001dA\u0011A\u00029zi\"|gN\u0003\u0002\n\u0015\u0005\u0019\u0011\r]5\u000b\u0005-a\u0011!C:ue\u0016\fW.\u001b8h\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7C\u0001\u0001\u0014!\t!R#D\u0001\u0007\u0013\t1bAA\u0007QsRDwN\u001c#TiJ,\u0017-\\\u0001\u0007a\u0006\u0014XM\u001c;\u0004\u0001A\u0012!D\t\t\u00047y\u0001S\"\u0001\u000f\u000b\u0005uQ\u0011a\u00023tiJ,\u0017-\\\u0005\u0003?q\u0011q\u0001R*ue\u0016\fW\u000e\u0005\u0002\"E1\u0001A!C\u0012\u0002\u0003\u0003\u0005\tQ!\u0001%\u0005\u0011yF%\r\u0019\u0012\u0005\u0015Z\u0003C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#a\u0002(pi\"Lgn\u001a\t\u0003M1J!!L\u0014\u0003\u0007\u0005s\u00170A\u0003qMVt7\r\u0005\u0002\u0015a%\u0011\u0011G\u0002\u0002\u0018!f$\bn\u001c8Ue\u0006t7OZ8s[\u001a+hn\u0019;j_:\fa\u0001P5oSRtDc\u0001\u001b6uA\u0011A\u0003\u0001\u0005\u0006/\r\u0001\rA\u000e\u0019\u0003oe\u00022a\u0007\u00109!\t\t\u0013\bB\u0005$k\u0005\u0005\t\u0011!B\u0001I!)af\u0001a\u0001_\u000591m\\7qkR,GCA\u001fM!\r1c\bQ\u0005\u0003\u007f\u001d\u0012aa\u00149uS>t\u0007cA!E\r6\t!I\u0003\u0002D\u0019\u0005\u0019!\u000f\u001a3\n\u0005\u0015\u0013%a\u0001*E\tB\u0019aeR%\n\u0005!;#!B!se\u0006L\bC\u0001\u0014K\u0013\tYuE\u0001\u0003CsR,\u0007\"B'\u0005\u0001\u0004q\u0015!\u0003<bY&$G+[7f!\ty\u0005+D\u0001\u000b\u0013\t\t&B\u0001\u0003US6,\u0007"
)
public class PythonTransformedDStream extends PythonDStream {
   private final DStream parent;

   public Option compute(final Time validTime) {
      Option rdd = this.parent.getOrCompute(validTime);
      return (Option)(rdd.isDefined() ? this.func().apply(rdd, validTime) : .MODULE$);
   }

   public PythonTransformedDStream(final DStream parent, final PythonTransformFunction pfunc) {
      super(parent, pfunc);
      this.parent = parent;
   }
}
