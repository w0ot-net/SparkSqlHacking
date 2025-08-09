package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum JwtEcdsaAlgorithm implements ProtocolMessageEnum {
   ES_UNKNOWN(0),
   ES256(1),
   ES384(2),
   ES512(3),
   UNRECOGNIZED(-1);

   public static final int ES_UNKNOWN_VALUE = 0;
   public static final int ES256_VALUE = 1;
   public static final int ES384_VALUE = 2;
   public static final int ES512_VALUE = 3;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final JwtEcdsaAlgorithm[] VALUES;
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
   public static JwtEcdsaAlgorithm valueOf(int value) {
      return forNumber(value);
   }

   public static JwtEcdsaAlgorithm forNumber(int value) {
      switch (value) {
         case 0:
            return ES_UNKNOWN;
         case 1:
            return ES256;
         case 2:
            return ES384;
         case 3:
            return ES512;
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
      return (Descriptors.EnumDescriptor)JwtEcdsa.getDescriptor().getEnumTypes().get(0);
   }

   public static JwtEcdsaAlgorithm valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private JwtEcdsaAlgorithm(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtEcdsaAlgorithm.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public JwtEcdsaAlgorithm findValueByNumber(int number) {
            return JwtEcdsaAlgorithm.forNumber(number);
         }
      };
      VALUES = values();
   }
}
