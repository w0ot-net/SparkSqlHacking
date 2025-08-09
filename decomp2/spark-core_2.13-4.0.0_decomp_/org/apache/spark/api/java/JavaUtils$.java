package org.apache.spark.api.java;

import scala.Option;
import scala.collection.Map;

public final class JavaUtils$ {
   public static final JavaUtils$ MODULE$ = new JavaUtils$();

   public Optional optionToOptional(final Option option) {
      return option.isDefined() ? Optional.of(option.get()) : Optional.empty();
   }

   public JavaUtils.SerializableMapWrapper mapAsSerializableJavaMap(final Map underlying) {
      return new JavaUtils.SerializableMapWrapper(underlying);
   }

   private JavaUtils$() {
   }
}
