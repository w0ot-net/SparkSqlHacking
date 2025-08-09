package org.apache.spark.status.protobuf;

import org.apache.spark.status.SpeculationStageSummaryWrapper;
import org.apache.spark.status.api.v1.SpeculationStageSummary;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3QAB\u0004\u0001\u000fEAQ\u0001\t\u0001\u0005\u0002\tBQ\u0001\n\u0001\u0005B\u0015BQA\f\u0001\u0005\u0002=BQA\r\u0001\u0005\nMBQ\u0001\u0012\u0001\u0005\n\u0015\u0013\u0001f\u00159fGVd\u0017\r^5p]N#\u0018mZ3Tk6l\u0017M]=Xe\u0006\u0004\b/\u001a:TKJL\u0017\r\\5{KJT!\u0001C\u0005\u0002\u0011A\u0014x\u000e^8ck\u001aT!AC\u0006\u0002\rM$\u0018\r^;t\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u00042!\u0007\u000e\u001d\u001b\u00059\u0011BA\u000e\b\u00055\u0001&o\u001c;pEV47+\u001a:EKB\u0011QDH\u0007\u0002\u0013%\u0011q$\u0003\u0002\u001f'B,7-\u001e7bi&|gn\u0015;bO\u0016\u001cV/\\7bef<&/\u00199qKJ\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002GA\u0011\u0011\u0004A\u0001\ng\u0016\u0014\u0018.\u00197ju\u0016$\"A\n\u0017\u0011\u0007M9\u0013&\u0003\u0002))\t)\u0011I\u001d:bsB\u00111CK\u0005\u0003WQ\u0011AAQ=uK\")QF\u0001a\u00019\u0005\t1/A\u0006eKN,'/[1mSj,GC\u0001\u000f1\u0011\u0015\t4\u00011\u0001'\u0003\u0015\u0011\u0017\u0010^3t\u0003\u0001\u001aXM]5bY&TXm\u00159fGVd\u0017\r^5p]N#\u0018mZ3Tk6l\u0017M]=\u0015\u0005QZ\u0004CA\u001b9\u001d\tIb'\u0003\u00028\u000f\u0005Q1\u000b^8sKRK\b/Z:\n\u0005eR$aF*qK\u000e,H.\u0019;j_:\u001cF/Y4f'VlW.\u0019:z\u0015\t9t\u0001C\u0003=\t\u0001\u0007Q(A\u0004tk6l\u0017M]=\u0011\u0005y\u001aU\"A \u000b\u0005\u0001\u000b\u0015A\u0001<2\u0015\t\u0011\u0015\"A\u0002ba&L!!O \u0002E\u0011,7/\u001a:jC2L'0Z*qK\u000e,H.\u0019;j_:\u001cF/Y4f'VlW.\u0019:z)\tid\tC\u0003H\u000b\u0001\u0007A'\u0001\u0003j]\u001a|\u0007"
)
public class SpeculationStageSummaryWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final SpeculationStageSummaryWrapper s) {
      StoreTypes.SpeculationStageSummary summary = this.serializeSpeculationStageSummary(s.info());
      StoreTypes.SpeculationStageSummaryWrapper.Builder builder = StoreTypes.SpeculationStageSummaryWrapper.newBuilder();
      builder.setStageId((long)s.stageId());
      builder.setStageAttemptId(s.stageAttemptId());
      builder.setInfo(summary);
      return builder.build().toByteArray();
   }

   public SpeculationStageSummaryWrapper deserialize(final byte[] bytes) {
      StoreTypes.SpeculationStageSummaryWrapper wrapper = StoreTypes.SpeculationStageSummaryWrapper.parseFrom(bytes);
      return new SpeculationStageSummaryWrapper((int)wrapper.getStageId(), wrapper.getStageAttemptId(), this.deserializeSpeculationStageSummary(wrapper.getInfo()));
   }

   private StoreTypes.SpeculationStageSummary serializeSpeculationStageSummary(final SpeculationStageSummary summary) {
      StoreTypes.SpeculationStageSummary.Builder summaryBuilder = StoreTypes.SpeculationStageSummary.newBuilder();
      summaryBuilder.setNumTasks(summary.numTasks());
      summaryBuilder.setNumActiveTasks(summary.numActiveTasks());
      summaryBuilder.setNumCompletedTasks(summary.numCompletedTasks());
      summaryBuilder.setNumFailedTasks(summary.numFailedTasks());
      summaryBuilder.setNumKilledTasks(summary.numKilledTasks());
      return summaryBuilder.build();
   }

   private SpeculationStageSummary deserializeSpeculationStageSummary(final StoreTypes.SpeculationStageSummary info) {
      return new SpeculationStageSummary(info.getNumTasks(), info.getNumActiveTasks(), info.getNumCompletedTasks(), info.getNumFailedTasks(), info.getNumKilledTasks());
   }
}
