package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum JwtHmacAlgorithm implements ProtocolMessageEnum {
   HS_UNKNOWN(0),
   HS256(1),
   HS384(2),
   HS512(3),
   UNRECOGNIZED(-1);

   public static final int HS_UNKNOWN_VALUE = 0;
   public static final int HS256_VALUE = 1;
   public static final int HS384_VALUE = 2;
   public static final int HS512_VALUE = 3;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final JwtHmacAlgorithm[] VALUES;
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
   public static JwtHmacAlgorithm valueOf(int value) {
      return forNumber(value);
   }

   public static JwtHmacAlgorithm forNumber(int value) {
      switch (value) {
         case 0:
            return HS_UNKNOWN;
         case 1:
            return HS256;
         case 2:
            return HS384;
         case 3:
            return HS512;
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
      return (Descriptors.EnumDescriptor)JwtHmac.getDescriptor().getEnumTypes().get(0);
   }

   public static JwtHmacAlgorithm valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private JwtHmacAlgorithm(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtHmacAlgorithm.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public JwtHmacAlgorithm findValueByNumber(int number) {
            return JwtHmacAlgorithm.forNumber(number);
         }
      };
      VALUES = values();
   }
}
