package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3Q\u0001C\u0005\u0001\u0017EA\u0001\u0002\u0007\u0001\u0003\u0006\u0004%\tA\u0007\u0005\tG\u0001\u0011\t\u0011)A\u00057!)A\u0005\u0001C\u0001K!)\u0011\u0006\u0001C\u0005U!)A\n\u0001C\u0005\u001b\"9Q\u000b\u0001b\u0001\n\u0003Q\u0003B\u0002,\u0001A\u0003%1F\u0001\fFq\u0016\u001cW\u000f^8s'VlW.\u0019:z/J\f\u0007\u000f]3s\u0015\tQ1\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\t%tgm\\\u0002\u0001+\u0005Y\u0002C\u0001\u000f\"\u001b\u0005i\"B\u0001\u0010 \u0003\t1\u0018G\u0003\u0002!\u0013\u0005\u0019\u0011\r]5\n\u0005\tj\"aD#yK\u000e,Ho\u001c:Tk6l\u0017M]=\u0002\u000b%tgm\u001c\u0011\u0002\rqJg.\u001b;?)\t1\u0003\u0006\u0005\u0002(\u00015\t\u0011\u0002C\u0003\u0019\u0007\u0001\u00071$\u0001\u0002jIV\t1\u0006\u0005\u0002-g9\u0011Q&\r\t\u0003]Qi\u0011a\f\u0006\u0003ae\ta\u0001\u0010:p_Rt\u0014B\u0001\u001a\u0015\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005I\"\u0002F\u0001\u00038!\tA\u0014)D\u0001:\u0015\tQ4(\u0001\u0006b]:|G/\u0019;j_:T!\u0001P\u001f\u0002\u000f)\f7m[:p]*\u0011ahP\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001Q\u0001\u0004G>l\u0017B\u0001\":\u0005)Q5o\u001c8JO:|'/\u001a\u0015\u0003\t\u0011\u0003\"!\u0012&\u000e\u0003\u0019S!a\u0012%\u0002\u000f-48\u000f^8sK*\u0011\u0011jC\u0001\u0005kRLG.\u0003\u0002L\r\n91JV%oI\u0016D\u0018AB1di&4X-F\u0001O!\t\u0019r*\u0003\u0002Q)\t9!i\\8mK\u0006t\u0007FA\u00038Q\u0011)Ai\u0015+\u0002\u000bY\fG.^3\"\u00031\u000bA\u0001[8ti\u0006)\u0001n\\:uA!\u0012qa\u000e\u0015\u0005\u000f\u0011\u001b\u0016,I\u0001V\u0001"
)
public class ExecutorSummaryWrapper {
   private final ExecutorSummary info;
   @JsonIgnore
   @KVIndex("host")
   private final String host;

   public ExecutorSummary info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex
   private String id() {
      return this.info().id();
   }

   @JsonIgnore
   @KVIndex("active")
   private boolean active() {
      return this.info().isActive();
   }

   public String host() {
      return this.host;
   }

   public ExecutorSummaryWrapper(final ExecutorSummary info) {
      this.info = info;
      this.host = (String)Utils$.MODULE$.parseHostPort(info.hostPort())._1();
   }
}
