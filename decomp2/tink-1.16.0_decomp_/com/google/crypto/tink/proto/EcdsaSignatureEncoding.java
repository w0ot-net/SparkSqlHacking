package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public enum EcdsaSignatureEncoding implements ProtocolMessageEnum {
   UNKNOWN_ENCODING(0),
   IEEE_P1363(1),
   DER(2),
   UNRECOGNIZED(-1);

   public static final int UNKNOWN_ENCODING_VALUE = 0;
   public static final int IEEE_P1363_VALUE = 1;
   public static final int DER_VALUE = 2;
   private static final Internal.EnumLiteMap internalValueMap;
   private static final EcdsaSignatureEncoding[] VALUES;
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
   public static EcdsaSignatureEncoding valueOf(int value) {
      return forNumber(value);
   }

   public static EcdsaSignatureEncoding forNumber(int value) {
      switch (value) {
         case 0:
            return UNKNOWN_ENCODING;
         case 1:
            return IEEE_P1363;
         case 2:
            return DER;
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
      return (Descriptors.EnumDescriptor)Ecdsa.getDescriptor().getEnumTypes().get(0);
   }

   public static EcdsaSignatureEncoding valueOf(Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
         throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
         return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
      }
   }

   private EcdsaSignatureEncoding(int value) {
      this.value = value;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EcdsaSignatureEncoding.class.getName());
      internalValueMap = new Internal.EnumLiteMap() {
         public EcdsaSignatureEncoding findValueByNumber(int number) {
            return EcdsaSignatureEncoding.forNumber(number);
         }
      };
      VALUES = values();
   }
}
