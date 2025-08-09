package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.StreamBlockData;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b=AQA\b\u0001\u0005\u0002\u0001BQA\t\u0001\u0005B\rBQ\u0001\f\u0001\u0005B5\u0012\u0011d\u0015;sK\u0006l'\t\\8dW\u0012\u000bG/Y*fe&\fG.\u001b>fe*\u0011aaB\u0001\taJ|Go\u001c2vM*\u0011\u0001\"C\u0001\u0007gR\fG/^:\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0019q\u0003\u0007\u000e\u000e\u0003\u0015I!!G\u0003\u0003\u001bA\u0013x\u000e^8ck\u001a\u001cVM\u001d#f!\tYB$D\u0001\b\u0013\tirAA\bTiJ,\u0017-\u001c\"m_\u000e\\G)\u0019;b\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0011\u0011\u0005]\u0001\u0011!C:fe&\fG.\u001b>f)\t!#\u0006E\u0002\u0012K\u001dJ!A\n\n\u0003\u000b\u0005\u0013(/Y=\u0011\u0005EA\u0013BA\u0015\u0013\u0005\u0011\u0011\u0015\u0010^3\t\u000b-\u0012\u0001\u0019\u0001\u000e\u0002\t\u0011\fG/Y\u0001\fI\u0016\u001cXM]5bY&TX\r\u0006\u0002\u001b]!)qf\u0001a\u0001I\u0005)!-\u001f;fg\u0002"
)
public class StreamBlockDataSerializer implements ProtobufSerDe {
   public byte[] serialize(final StreamBlockData data) {
      StoreTypes.StreamBlockData.Builder builder = StoreTypes.StreamBlockData.newBuilder();
      Utils$.MODULE$.setStringField(data.name(), (value) -> builder.setName(value));
      Utils$.MODULE$.setStringField(data.executorId(), (value) -> builder.setExecutorId(value));
      Utils$.MODULE$.setStringField(data.hostPort(), (value) -> builder.setHostPort(value));
      Utils$.MODULE$.setStringField(data.storageLevel(), (value) -> builder.setStorageLevel(value));
      builder.setUseMemory(data.useMemory()).setUseDisk(data.useDisk()).setDeserialized(data.deserialized()).setMemSize(data.memSize()).setDiskSize(data.diskSize());
      return builder.build().toByteArray();
   }

   public StreamBlockData deserialize(final byte[] bytes) {
      StoreTypes.StreamBlockData binary = StoreTypes.StreamBlockData.parseFrom(bytes);
      return new StreamBlockData(Utils$.MODULE$.getStringField(binary.hasName(), () -> binary.getName()), Utils$.MODULE$.getStringField(binary.hasExecutorId(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getExecutorId())), Utils$.MODULE$.getStringField(binary.hasHostPort(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getHostPort())), Utils$.MODULE$.getStringField(binary.hasStorageLevel(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getStorageLevel())), binary.getUseMemory(), binary.getUseDisk(), binary.getDeserialized(), binary.getMemSize(), binary.getDiskSize());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
