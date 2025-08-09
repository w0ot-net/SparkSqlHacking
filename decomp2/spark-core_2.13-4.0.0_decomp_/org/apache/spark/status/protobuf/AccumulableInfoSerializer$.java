package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.status.api.v1.AccumulableInfo;
import scala.collection.mutable.ArrayBuffer;

public final class AccumulableInfoSerializer$ {
   public static final AccumulableInfoSerializer$ MODULE$ = new AccumulableInfoSerializer$();

   public StoreTypes.AccumulableInfo serialize(final AccumulableInfo input) {
      StoreTypes.AccumulableInfo.Builder builder = StoreTypes.AccumulableInfo.newBuilder().setId(input.id());
      Utils$.MODULE$.setStringField(input.name(), (value) -> builder.setName(value));
      Utils$.MODULE$.setStringField(input.value(), (value) -> builder.setValue(value));
      input.update().foreach((value) -> builder.setUpdate(value));
      return builder.build();
   }

   public ArrayBuffer deserialize(final List updates) {
      ArrayBuffer accumulatorUpdates = new ArrayBuffer(updates.size());
      updates.forEach((update) -> accumulatorUpdates.append(new AccumulableInfo(update.getId(), Utils$.MODULE$.getStringField(update.hasName(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(update.getName())), Utils$.MODULE$.getOptional(update.hasUpdate(), () -> update.getUpdate()), Utils$.MODULE$.getStringField(update.hasValue(), () -> update.getValue()))));
      return accumulatorUpdates;
   }

   private AccumulableInfoSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
