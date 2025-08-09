package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum KeyStatusType implements ProtocolMessageEnum {
   UNKNOWN_STATUS(0),
   ENABLED(1),
   DISABLED(2),
   DESTROYED(3),
   UNRECOGNIZED(-1);

   public static final int UNKNOWN_STATUS_VALUE = 0;
   public static final int ENABLED_VALUE = 1;
   public static final int DISABLED_VALUE = 2;
   public static final int DESTROYED_VALUE = 3;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final KeyStatusType[] VALUES;
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
   public static KeyStatusType valueOf(int value) {
      return forNumber(value);
   }

   public static KeyStatusType forNumber(int value) {
      switch (value) {
         case 0:
            return UNKNOWN_STATUS;
         case 1:
            return ENABLED;
         case 2:
            return DISABLED;
         case 3:
            return DESTROYED;
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
      return (Descriptors.EnumDescriptor)Tink.getDescriptor().getEnumTypes().get(0);
   }

   public static KeyStatusType valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private KeyStatusType(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KeyStatusType.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public KeyStatusType findValueByNumber(int number) {
            return KeyStatusType.forNumber(number);
         }
      };
      VALUES = values();
   }
}
