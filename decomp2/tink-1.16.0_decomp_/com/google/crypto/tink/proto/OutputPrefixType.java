package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum OutputPrefixType implements ProtocolMessageEnum {
   UNKNOWN_PREFIX(0),
   TINK(1),
   LEGACY(2),
   RAW(3),
   CRUNCHY(4),
   UNRECOGNIZED(-1);

   public static final int UNKNOWN_PREFIX_VALUE = 0;
   public static final int TINK_VALUE = 1;
   public static final int LEGACY_VALUE = 2;
   public static final int RAW_VALUE = 3;
   public static final int CRUNCHY_VALUE = 4;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final OutputPrefixType[] VALUES;
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
   public static OutputPrefixType valueOf(int value) {
      return forNumber(value);
   }

   public static OutputPrefixType forNumber(int value) {
      switch (value) {
         case 0:
            return UNKNOWN_PREFIX;
         case 1:
            return TINK;
         case 2:
            return LEGACY;
         case 3:
            return RAW;
         case 4:
            return CRUNCHY;
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
      return (Descriptors.EnumDescriptor)Tink.getDescriptor().getEnumTypes().get(1);
   }

   public static OutputPrefixType valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private OutputPrefixType(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", OutputPrefixType.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public OutputPrefixType findValueByNumber(int number) {
            return OutputPrefixType.forNumber(number);
         }
      };
      VALUES = values();
   }
}
