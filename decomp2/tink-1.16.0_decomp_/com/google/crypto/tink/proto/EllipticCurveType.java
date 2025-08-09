package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum EllipticCurveType implements ProtocolMessageEnum {
   UNKNOWN_CURVE(0),
   NIST_P256(2),
   NIST_P384(3),
   NIST_P521(4),
   CURVE25519(5),
   UNRECOGNIZED(-1);

   public static final int UNKNOWN_CURVE_VALUE = 0;
   public static final int NIST_P256_VALUE = 2;
   public static final int NIST_P384_VALUE = 3;
   public static final int NIST_P521_VALUE = 4;
   public static final int CURVE25519_VALUE = 5;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final EllipticCurveType[] VALUES;
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
   public static EllipticCurveType valueOf(int value) {
      return forNumber(value);
   }

   public static EllipticCurveType forNumber(int value) {
      switch (value) {
         case 0:
            return UNKNOWN_CURVE;
         case 1:
         default:
            return null;
         case 2:
            return NIST_P256;
         case 3:
            return NIST_P384;
         case 4:
            return NIST_P521;
         case 5:
            return CURVE25519;
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
      return (Descriptors.EnumDescriptor)Common.getDescriptor().getEnumTypes().get(0);
   }

   public static EllipticCurveType valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private EllipticCurveType(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EllipticCurveType.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public EllipticCurveType findValueByNumber(int number) {
            return EllipticCurveType.forNumber(number);
         }
      };
      VALUES = values();
   }
}
