package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public final class OptionalSerializers {
   public static void addDefaultSerializers(Kryo kryo) {
      if (Util.isClassAvailable("java.util.Optional")) {
         kryo.addDefaultSerializer(Optional.class, (Serializer)(new OptionalSerializer()));
      }

      if (Util.isClassAvailable("java.util.OptionalInt")) {
         kryo.addDefaultSerializer(OptionalInt.class, (Serializer)(new OptionalIntSerializer()));
      }

      if (Util.isClassAvailable("java.util.OptionalLong")) {
         kryo.addDefaultSerializer(OptionalLong.class, (Serializer)(new OptionalLongSerializer()));
      }

      if (Util.isClassAvailable("java.util.OptionalDouble")) {
         kryo.addDefaultSerializer(OptionalDouble.class, (Serializer)(new OptionalDoubleSerializer()));
      }

   }

   private static class OptionalSerializer extends Serializer {
      private OptionalSerializer() {
         this.setAcceptsNull(false);
      }

      public void write(Kryo kryo, Output output, Optional object) {
         Object nullable = object.isPresent() ? object.get() : null;
         kryo.writeClassAndObject(output, nullable);
      }

      public Optional read(Kryo kryo, Input input, Class type) {
         return Optional.ofNullable(kryo.readClassAndObject(input));
      }

      public Optional copy(Kryo kryo, Optional original) {
         return original.isPresent() ? Optional.of(kryo.copy(original.get())) : original;
      }
   }

   private static class OptionalIntSerializer extends Serializer {
      private OptionalIntSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, OptionalInt object) {
         output.writeBoolean(object.isPresent());
         if (object.isPresent()) {
            output.writeInt(object.getAsInt());
         }

      }

      public OptionalInt read(Kryo kryo, Input input, Class type) {
         boolean present = input.readBoolean();
         return present ? OptionalInt.of(input.readInt()) : OptionalInt.empty();
      }
   }

   private static class OptionalLongSerializer extends Serializer {
      private OptionalLongSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, OptionalLong object) {
         output.writeBoolean(object.isPresent());
         if (object.isPresent()) {
            output.writeLong(object.getAsLong());
         }

      }

      public OptionalLong read(Kryo kryo, Input input, Class type) {
         boolean present = input.readBoolean();
         return present ? OptionalLong.of(input.readLong()) : OptionalLong.empty();
      }
   }

   private static class OptionalDoubleSerializer extends Serializer {
      private OptionalDoubleSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output output, OptionalDouble object) {
         output.writeBoolean(object.isPresent());
         if (object.isPresent()) {
            output.writeDouble(object.getAsDouble());
         }

      }

      public OptionalDouble read(Kryo kryo, Input input, Class type) {
         boolean present = input.readBoolean();
         return present ? OptionalDouble.of(input.readDouble()) : OptionalDouble.empty();
      }
   }
}
