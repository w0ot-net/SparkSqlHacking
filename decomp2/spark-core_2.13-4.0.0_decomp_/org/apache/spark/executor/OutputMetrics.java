package org.apache.spark.executor;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.LongAccumulator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u000553AAC\u0006\u0001)!1q\u0005\u0001C\u0001\u001b!B\u0001b\u000b\u0001C\u0002\u0013\u00051\u0002\f\u0005\u0007g\u0001\u0001\u000b\u0011B\u0017\t\u0011Q\u0002!\u0019!C\u0001\u00171Ba!\u000e\u0001!\u0002\u0013i\u0003\"\u0002\u001c\u0001\t\u00039\u0004\"B\u001e\u0001\t\u00039\u0004B\u0002\u001f\u0001\t\u0003iQ\b\u0003\u0004D\u0001\u0011\u0005Q\u0002\u0012\u0002\u000e\u001fV$\b/\u001e;NKR\u0014\u0018nY:\u000b\u00051i\u0011\u0001C3yK\u000e,Ho\u001c:\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007C\u0001\u000f%\u001d\ti\"E\u0004\u0002\u001fC5\tqD\u0003\u0002!'\u00051AH]8pizJ\u0011\u0001G\u0005\u0003G]\tq\u0001]1dW\u0006<W-\u0003\u0002&M\ta1+\u001a:jC2L'0\u00192mK*\u00111eF\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%\u0002\"A\u000b\u0001\u000e\u0003-\tQb\u00182zi\u0016\u001cxK]5ui\u0016tW#A\u0017\u0011\u00059\nT\"A\u0018\u000b\u0005Aj\u0011\u0001B;uS2L!AM\u0018\u0003\u001f1{gnZ!dGVlW\u000f\\1u_J\fab\u00182zi\u0016\u001cxK]5ui\u0016t\u0007%A\b`e\u0016\u001cwN\u001d3t/JLG\u000f^3o\u0003Ay&/Z2pe\u0012\u001cxK]5ui\u0016t\u0007%\u0001\u0007csR,7o\u0016:jiR,g.F\u00019!\t1\u0012(\u0003\u0002;/\t!Aj\u001c8h\u00039\u0011XmY8sIN<&/\u001b;uK:\fqb]3u\u0005f$Xm],sSR$XM\u001c\u000b\u0003}\u0005\u0003\"AF \n\u0005\u0001;\"\u0001B+oSRDQA\u0011\u0005A\u0002a\n\u0011A^\u0001\u0012g\u0016$(+Z2pe\u0012\u001cxK]5ui\u0016tGC\u0001 F\u0011\u0015\u0011\u0015\u00021\u00019Q\t\u0001q\t\u0005\u0002I\u00176\t\u0011J\u0003\u0002K\u001b\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051K%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class OutputMetrics implements Serializable {
   private final LongAccumulator _bytesWritten = new LongAccumulator();
   private final LongAccumulator _recordsWritten = new LongAccumulator();

   public LongAccumulator _bytesWritten() {
      return this._bytesWritten;
   }

   public LongAccumulator _recordsWritten() {
      return this._recordsWritten;
   }

   public long bytesWritten() {
      return this._bytesWritten().sum();
   }

   public long recordsWritten() {
      return this._recordsWritten().sum();
   }

   public void setBytesWritten(final long v) {
      this._bytesWritten().setValue(v);
   }

   public void setRecordsWritten(final long v) {
      this._recordsWritten().setValue(v);
   }
}
