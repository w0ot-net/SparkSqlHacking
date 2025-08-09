package org.apache.orc.protobuf;

public abstract class Extension extends ExtensionLite {
   public abstract Message getMessageDefaultInstance();

   public abstract Descriptors.FieldDescriptor getDescriptor();

   final boolean isLite() {
      return false;
   }

   protected abstract ExtensionType getExtensionType();

   public MessageType getMessageType() {
      return Extension.MessageType.PROTO2;
   }

   protected abstract Object fromReflectionType(Object value);

   protected abstract Object singularFromReflectionType(Object value);

   protected abstract Object toReflectionType(Object value);

   protected abstract Object singularToReflectionType(Object value);

   protected static enum ExtensionType {
      IMMUTABLE,
      MUTABLE,
      PROTO1;
   }

   public static enum MessageType {
      PROTO1,
      PROTO2;
   }
}
