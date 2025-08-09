package org.apache.spark.status.protobuf;

import org.apache.spark.status.AppSummary;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b=AQA\b\u0001\u0005\u0002\u0001BQA\t\u0001\u0005B\rBQ\u0001\f\u0001\u0005B5\u0012A#\u00119q'VlW.\u0019:z'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0004\b\u0003!\u0001(o\u001c;pEV4'B\u0001\u0005\n\u0003\u0019\u0019H/\u0019;vg*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xmE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007cA\f\u001955\tQ!\u0003\u0002\u001a\u000b\ti\u0001K]8u_\n,hmU3s\t\u0016\u0004\"a\u0007\u000f\u000e\u0003\u001dI!!H\u0004\u0003\u0015\u0005\u0003\boU;n[\u0006\u0014\u00180\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0003CA\f\u0001\u0003%\u0019XM]5bY&TX\r\u0006\u0002%UA\u0019\u0011#J\u0014\n\u0005\u0019\u0012\"!B!se\u0006L\bCA\t)\u0013\tI#C\u0001\u0003CsR,\u0007\"B\u0016\u0003\u0001\u0004Q\u0012!B5oaV$\u0018a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$\"A\u0007\u0018\t\u000b=\u001a\u0001\u0019\u0001\u0013\u0002\u000b\tLH/Z:"
)
public class AppSummarySerializer implements ProtobufSerDe {
   public byte[] serialize(final AppSummary input) {
      StoreTypes.AppSummary.Builder builder = StoreTypes.AppSummary.newBuilder().setNumCompletedJobs(input.numCompletedJobs()).setNumCompletedStages(input.numCompletedStages());
      return builder.build().toByteArray();
   }

   public AppSummary deserialize(final byte[] bytes) {
      StoreTypes.AppSummary summary = StoreTypes.AppSummary.parseFrom(bytes);
      return new AppSummary(summary.getNumCompletedJobs(), summary.getNumCompletedStages());
   }
}
