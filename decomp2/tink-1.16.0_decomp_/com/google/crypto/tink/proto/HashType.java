package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum HashType implements ProtocolMessageEnum {
   UNKNOWN_HASH(0),
   SHA1(1),
   SHA384(2),
   SHA256(3),
   SHA512(4),
   SHA224(5),
   UNRECOGNIZED(-1);

   public static final int UNKNOWN_HASH_VALUE = 0;
   public static final int SHA1_VALUE = 1;
   public static final int SHA384_VALUE = 2;
   public static final int SHA256_VALUE = 3;
   public static final int SHA512_VALUE = 4;
   public static final int SHA224_VALUE = 5;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final HashType[] VALUES;
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
   public static HashType valueOf(int value) {
      return forNumber(value);
   }

   public static HashType forNumber(int value) {
      switch (value) {
         case 0:
            return UNKNOWN_HASH;
         case 1:
            return SHA1;
         case 2:
            return SHA384;
         case 3:
            return SHA256;
         case 4:
            return SHA512;
         case 5:
            return SHA224;
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
      return (Descriptors.EnumDescriptor)Common.getDescriptor().getEnumTypes().get(2);
   }

   public static HashType valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private HashType(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HashType.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public HashType findValueByNumber(int number) {
            return HashType.forNumber(number);
         }
      };
      VALUES = values();
   }
}
