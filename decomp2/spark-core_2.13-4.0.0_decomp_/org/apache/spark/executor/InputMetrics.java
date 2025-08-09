package org.apache.spark.executor;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.LongAccumulator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005U3A\u0001D\u0007\u0001-!1\u0011\u0006\u0001C\u0001\u001f)B\u0001\"\f\u0001C\u0002\u0013\u0005QB\f\u0005\u0007k\u0001\u0001\u000b\u0011B\u0018\t\u0011Y\u0002!\u0019!C\u0001\u001b9Baa\u000e\u0001!\u0002\u0013y\u0003\"\u0002\u001d\u0001\t\u0003I\u0004\"B\u001f\u0001\t\u0003I\u0004B\u0002 \u0001\t\u0003yq\b\u0003\u0004F\u0001\u0011\u0005qB\u0012\u0005\u0007\u0011\u0002!\taD%\t\r-\u0003A\u0011A\bM\u00051Ie\u000e];u\u001b\u0016$(/[2t\u0015\tqq\"\u0001\u0005fq\u0016\u001cW\u000f^8s\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0004\u0001]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0002\u001fM9\u0011q\u0004\n\b\u0003A\rj\u0011!\t\u0006\u0003EU\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000e\n\u0005\u0015J\u0012a\u00029bG.\fw-Z\u0005\u0003O!\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!J\r\u0002\rqJg.\u001b;?)\u0005Y\u0003C\u0001\u0017\u0001\u001b\u0005i\u0011AC0csR,7OU3bIV\tq\u0006\u0005\u00021g5\t\u0011G\u0003\u00023\u001f\u0005!Q\u000f^5m\u0013\t!\u0014GA\bM_:<\u0017iY2v[Vd\u0017\r^8s\u0003-y&-\u001f;fgJ+\u0017\r\u001a\u0011\u0002\u0019}\u0013XmY8sIN\u0014V-\u00193\u0002\u001b}\u0013XmY8sIN\u0014V-\u00193!\u0003%\u0011\u0017\u0010^3t%\u0016\fG-F\u0001;!\tA2(\u0003\u0002=3\t!Aj\u001c8h\u0003-\u0011XmY8sIN\u0014V-\u00193\u0002\u0019%t7MQ=uKN\u0014V-\u00193\u0015\u0005\u0001\u001b\u0005C\u0001\rB\u0013\t\u0011\u0015D\u0001\u0003V]&$\b\"\u0002#\t\u0001\u0004Q\u0014!\u0001<\u0002\u001d%t7MU3d_J$7OU3bIR\u0011\u0001i\u0012\u0005\u0006\t&\u0001\rAO\u0001\rg\u0016$()\u001f;fgJ+\u0017\r\u001a\u000b\u0003\u0001*CQ\u0001\u0012\u0006A\u0002i\nab]3u%\u0016\u001cwN\u001d3t%\u0016\fG\r\u0006\u0002A\u001b\")Ai\u0003a\u0001u!\u0012\u0001a\u0014\t\u0003!Nk\u0011!\u0015\u0006\u0003%>\t!\"\u00198o_R\fG/[8o\u0013\t!\u0016K\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public class InputMetrics implements Serializable {
   private final LongAccumulator _bytesRead = new LongAccumulator();
   private final LongAccumulator _recordsRead = new LongAccumulator();

   public LongAccumulator _bytesRead() {
      return this._bytesRead;
   }

   public LongAccumulator _recordsRead() {
      return this._recordsRead;
   }

   public long bytesRead() {
      return this._bytesRead().sum();
   }

   public long recordsRead() {
      return this._recordsRead().sum();
   }

   public void incBytesRead(final long v) {
      this._bytesRead().add(v);
   }

   public void incRecordsRead(final long v) {
      this._recordsRead().add(v);
   }

   public void setBytesRead(final long v) {
      this._bytesRead().setValue(v);
   }

   public void setRecordsRead(final long v) {
      this._recordsRead().setValue(v);
   }
}
