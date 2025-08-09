package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum HpkeKdf implements ProtocolMessageEnum {
   KDF_UNKNOWN(0),
   HKDF_SHA256(1),
   HKDF_SHA384(2),
   HKDF_SHA512(3),
   UNRECOGNIZED(-1);

   public static final int KDF_UNKNOWN_VALUE = 0;
   public static final int HKDF_SHA256_VALUE = 1;
   public static final int HKDF_SHA384_VALUE = 2;
   public static final int HKDF_SHA512_VALUE = 3;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final HpkeKdf[] VALUES;
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
   public static HpkeKdf valueOf(int value) {
      return forNumber(value);
   }

   public static HpkeKdf forNumber(int value) {
      switch (value) {
         case 0:
            return KDF_UNKNOWN;
         case 1:
            return HKDF_SHA256;
         case 2:
            return HKDF_SHA384;
         case 3:
            return HKDF_SHA512;
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
      return (Descriptors.EnumDescriptor)Hpke.getDescriptor().getEnumTypes().get(1);
   }

   public static HpkeKdf valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private HpkeKdf(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HpkeKdf.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public HpkeKdf findValueByNumber(int number) {
            return HpkeKdf.forNumber(number);
         }
      };
      VALUES = values();
   }
}
