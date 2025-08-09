package org.sparkproject.spark_core.protobuf;

public enum NullValue implements ProtocolMessageEnum {
   NULL_VALUE(0),
   UNRECOGNIZED(-1);

   public static final int NULL_VALUE_VALUE = 0;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final NullValue[] VALUES;
   private final int value;

   public final int getNumber() {
      if (this == UNRECOGNIZED) {
         throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
      } else {
         return this.value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static NullValue valueOf(int value) {
      return forNumber(value);
   }

   public static NullValue forNumber(int value) {
      switch (value) {
         case 0:
            return NULL_VALUE;
         default:
            return null;
      }
   }

   public static Internal.EnumLiteMap internalGetValueMap() {
      return internalValueMap;
   }

   public final Descriptors.EnumValueDescriptor getValueDescriptor() {
      if (this == UNRECOGNIZED) {
         throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
      } else {
         return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.ordinal());
      }
   }

   public final Descriptors.EnumDescriptor getDescriptorForType() {
      return getDescriptor();
   }

   public static final Descriptors.EnumDescriptor getDescriptor() {
      return (Descriptors.EnumDescriptor)StructProto.getDescriptor().getEnumTypes().get(0);
   }

   public static NullValue valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private NullValue(int value) {
      this.value = value;
   }

   // $FF: synthetic method
   private static NullValue[] $values() {
      return new NullValue[]{NULL_VALUE, UNRECOGNIZED};
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", NullValue.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public NullValue findValueByNumber(int number) {
            return NullValue.forNumber(number);
         }
      };
      VALUES = values();
   }
}
