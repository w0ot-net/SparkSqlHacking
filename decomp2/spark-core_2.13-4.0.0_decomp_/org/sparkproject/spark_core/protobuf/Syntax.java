package org.sparkproject.spark_core.protobuf;

public enum Syntax implements ProtocolMessageEnum {
   SYNTAX_PROTO2(0),
   SYNTAX_PROTO3(1),
   SYNTAX_EDITIONS(2),
   UNRECOGNIZED(-1);

   public static final int SYNTAX_PROTO2_VALUE = 0;
   public static final int SYNTAX_PROTO3_VALUE = 1;
   public static final int SYNTAX_EDITIONS_VALUE = 2;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final Syntax[] VALUES;
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
   public static Syntax valueOf(int value) {
      return forNumber(value);
   }

   public static Syntax forNumber(int value) {
      switch (value) {
         case 0:
            return SYNTAX_PROTO2;
         case 1:
            return SYNTAX_PROTO3;
         case 2:
            return SYNTAX_EDITIONS;
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
      return (Descriptors.EnumDescriptor)TypeProto.getDescriptor().getEnumTypes().get(0);
   }

   public static Syntax valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private Syntax(int value) {
      this.value = value;
   }

   // $FF: synthetic method
   private static Syntax[] $values() {
      return new Syntax[]{SYNTAX_PROTO2, SYNTAX_PROTO3, SYNTAX_EDITIONS, UNRECOGNIZED};
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Syntax.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public Syntax findValueByNumber(int number) {
            return Syntax.forNumber(number);
         }
      };
      VALUES = values();
   }
}
