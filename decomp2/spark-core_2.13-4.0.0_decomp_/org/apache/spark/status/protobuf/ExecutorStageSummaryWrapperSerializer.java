package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.ExecutorStageSummaryWrapper;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b=AQA\b\u0001\u0005\u0002\u0001BQA\t\u0001\u0005B\rBQ\u0001\f\u0001\u0005\u00025\u0012Q%\u0012=fGV$xN]*uC\u001e,7+^7nCJLxK]1qa\u0016\u00148+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011\u0001\u00039s_R|'-\u001e4\u000b\u0005!I\u0011AB:uCR,8O\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]A\"$D\u0001\u0006\u0013\tIRAA\u0007Qe>$xNY;g'\u0016\u0014H)\u001a\t\u00037qi\u0011aB\u0005\u0003;\u001d\u00111$\u0012=fGV$xN]*uC\u001e,7+^7nCJLxK]1qa\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u0005\u0002\"a\u0006\u0001\u0002\u0013M,'/[1mSj,GC\u0001\u0013+!\r\tReJ\u0005\u0003MI\u0011Q!\u0011:sCf\u0004\"!\u0005\u0015\n\u0005%\u0012\"\u0001\u0002\"zi\u0016DQa\u000b\u0002A\u0002i\tQ!\u001b8qkR\f1\u0002Z3tKJL\u0017\r\\5{KR\u0011!D\f\u0005\u0006_\r\u0001\r\u0001J\u0001\u0006Ef$Xm\u001d"
)
public class ExecutorStageSummaryWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final ExecutorStageSummaryWrapper input) {
      StoreTypes.ExecutorStageSummary info = ExecutorStageSummarySerializer$.MODULE$.serialize(input.info());
      StoreTypes.ExecutorStageSummaryWrapper.Builder builder = StoreTypes.ExecutorStageSummaryWrapper.newBuilder().setStageId((long)input.stageId()).setStageAttemptId(input.stageAttemptId()).setInfo(info);
      Utils$.MODULE$.setStringField(input.executorId(), (value) -> builder.setExecutorId(value));
      return builder.build().toByteArray();
   }

   public ExecutorStageSummaryWrapper deserialize(final byte[] bytes) {
      StoreTypes.ExecutorStageSummaryWrapper binary = StoreTypes.ExecutorStageSummaryWrapper.parseFrom(bytes);
      ExecutorStageSummary info = ExecutorStageSummarySerializer$.MODULE$.deserialize(binary.getInfo());
      return new ExecutorStageSummaryWrapper((int)binary.getStageId(), binary.getStageAttemptId(), Utils$.MODULE$.getStringField(binary.hasExecutorId(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getExecutorId())), info);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
