package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.PoolData;
import scala.collection.IterableOnceOps;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b=AQA\b\u0001\u0005\u0002\u0001BQA\t\u0001\u0005B\rBQ\u0001\f\u0001\u0005B5\u0012!\u0003U8pY\u0012\u000bG/Y*fe&\fG.\u001b>fe*\u0011aaB\u0001\taJ|Go\u001c2vM*\u0011\u0001\"C\u0001\u0007gR\fG/^:\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0019q\u0003\u0007\u000e\u000e\u0003\u0015I!!G\u0003\u0003\u001bA\u0013x\u000e^8ck\u001a\u001cVM\u001d#f!\tYB$D\u0001\b\u0013\tirA\u0001\u0005Q_>dG)\u0019;b\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0011\u0011\u0005]\u0001\u0011!C:fe&\fG.\u001b>f)\t!#\u0006E\u0002\u0012K\u001dJ!A\n\n\u0003\u000b\u0005\u0013(/Y=\u0011\u0005EA\u0013BA\u0015\u0013\u0005\u0011\u0011\u0015\u0010^3\t\u000b-\u0012\u0001\u0019\u0001\u000e\u0002\u000b%t\u0007/\u001e;\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u000359BQaL\u0002A\u0002\u0011\nQAY=uKN\u0004"
)
public class PoolDataSerializer implements ProtobufSerDe {
   public byte[] serialize(final PoolData input) {
      StoreTypes.PoolData.Builder builder = StoreTypes.PoolData.newBuilder();
      Utils$.MODULE$.setStringField(input.name(), (value) -> builder.setName(value));
      input.stageIds().foreach((id) -> $anonfun$serialize$2(builder, BoxesRunTime.unboxToInt(id)));
      return builder.build().toByteArray();
   }

   public PoolData deserialize(final byte[] bytes) {
      StoreTypes.PoolData poolData = StoreTypes.PoolData.parseFrom(bytes);
      return new PoolData(Utils$.MODULE$.getStringField(poolData.hasName(), () -> poolData.getName()), ((IterableOnceOps).MODULE$.ListHasAsScala(poolData.getStageIdsList()).asScala().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$deserialize$2(x$1)))).toSet());
   }

   // $FF: synthetic method
   public static final StoreTypes.PoolData.Builder $anonfun$serialize$2(final StoreTypes.PoolData.Builder builder$1, final int id) {
      return builder$1.addStageIds((long)id);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserialize$2(final Long x$1) {
      return (int)scala.Predef..MODULE$.Long2long(x$1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
