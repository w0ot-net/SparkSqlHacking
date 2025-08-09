package com.google.crypto.tink.proto;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class EciesHkdfKemParams extends GeneratedMessage implements EciesHkdfKemParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int CURVE_TYPE_FIELD_NUMBER = 1;
   private int curveType_;
   public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 2;
   private int hkdfHashType_;
   public static final int HKDF_SALT_FIELD_NUMBER = 11;
   private ByteString hkdfSalt_;
   private byte memoizedIsInitialized;
   private static final EciesHkdfKemParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EciesHkdfKemParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.curveType_ = 0;
      this.hkdfHashType_ = 0;
      this.hkdfSalt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private EciesHkdfKemParams() {
      this.curveType_ = 0;
      this.hkdfHashType_ = 0;
      this.hkdfSalt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.curveType_ = 0;
      this.hkdfHashType_ = 0;
      this.hkdfSalt_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesHkdfKemParams_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesHkdfKemParams.class, Builder.class);
   }

   public int getCurveTypeValue() {
      return this.curveType_;
   }

   public EllipticCurveType getCurveType() {
      EllipticCurveType result = EllipticCurveType.forNumber(this.curveType_);
      return result == null ? EllipticCurveType.UNRECOGNIZED : result;
   }

   public int getHkdfHashTypeValue() {
      return this.hkdfHashType_;
   }

   public HashType getHkdfHashType() {
      HashType result = HashType.forNumber(this.hkdfHashType_);
      return result == null ? HashType.UNRECOGNIZED : result;
   }

   public ByteString getHkdfSalt() {
      return this.hkdfSalt_;
   }

   public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1) {
         return true;
      } else if (isInitialized == 0) {
         return false;
      } else {
         this.memoizedIsInitialized = 1;
         return true;
      }
   }

   public void writeTo(CodedOutputStream output) throws IOException {
      if (this.curveType_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
         output.writeEnum(1, this.curveType_);
      }

      if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
         output.writeEnum(2, this.hkdfHashType_);
      }

      if (!this.hkdfSalt_.isEmpty()) {
         output.writeBytes(11, this.hkdfSalt_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.curveType_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
            size += CodedOutputStream.computeEnumSize(1, this.curveType_);
         }

         if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            size += CodedOutputStream.computeEnumSize(2, this.hkdfHashType_);
         }

         if (!this.hkdfSalt_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(11, this.hkdfSalt_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EciesHkdfKemParams)) {
         return super.equals(obj);
      } else {
         EciesHkdfKemParams other = (EciesHkdfKemParams)obj;
         if (this.curveType_ != other.curveType_) {
            return false;
         } else if (this.hkdfHashType_ != other.hkdfHashType_) {
            return false;
         } else if (!this.getHkdfSalt().equals(other.getHkdfSalt())) {
            return false;
         } else {
            return this.getUnknownFields().equals(other.getUnknownFields());
         }
      }
   }

   public int hashCode() {
      if (this.memoizedHashCode != 0) {
         return this.memoizedHashCode;
      } else {
         int hash = 41;
         hash = 19 * hash + getDescriptor().hashCode();
         hash = 37 * hash + 1;
         hash = 53 * hash + this.curveType_;
         hash = 37 * hash + 2;
         hash = 53 * hash + this.hkdfHashType_;
         hash = 37 * hash + 11;
         hash = 53 * hash + this.getHkdfSalt().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EciesHkdfKemParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data);
   }

   public static EciesHkdfKemParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesHkdfKemParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data);
   }

   public static EciesHkdfKemParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesHkdfKemParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data);
   }

   public static EciesHkdfKemParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesHkdfKemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesHkdfKemParams parseFrom(InputStream input) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesHkdfKemParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesHkdfKemParams parseDelimitedFrom(InputStream input) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EciesHkdfKemParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesHkdfKemParams parseFrom(CodedInputStream input) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesHkdfKemParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesHkdfKemParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EciesHkdfKemParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EciesHkdfKemParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EciesHkdfKemParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesHkdfKemParams.class.getName());
      DEFAULT_INSTANCE = new EciesHkdfKemParams();
      PARSER = new AbstractParser() {
         public EciesHkdfKemParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EciesHkdfKemParams.newBuilder();

            try {
               builder.mergeFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
               throw e.setUnfinishedMessage(builder.buildPartial());
            } catch (UninitializedMessageException e) {
               throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
            } catch (IOException e) {
               throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
            }

            return builder.buildPartial();
         }
      };
   }

   public static final class Builder extends GeneratedMessage.Builder implements EciesHkdfKemParamsOrBuilder {
      private int bitField0_;
      private int curveType_;
      private int hkdfHashType_;
      private ByteString hkdfSalt_;

      public static final Descriptors.Descriptor getDescriptor() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesHkdfKemParams_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesHkdfKemParams.class, Builder.class);
      }

      private Builder() {
         this.curveType_ = 0;
         this.hkdfHashType_ = 0;
         this.hkdfSalt_ = ByteString.EMPTY;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.curveType_ = 0;
         this.hkdfHashType_ = 0;
         this.hkdfSalt_ = ByteString.EMPTY;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.curveType_ = 0;
         this.hkdfHashType_ = 0;
         this.hkdfSalt_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor;
      }

      public EciesHkdfKemParams getDefaultInstanceForType() {
         return EciesHkdfKemParams.getDefaultInstance();
      }

      public EciesHkdfKemParams build() {
         EciesHkdfKemParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EciesHkdfKemParams buildPartial() {
         EciesHkdfKemParams result = new EciesHkdfKemParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EciesHkdfKemParams result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.curveType_ = this.curveType_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.hkdfHashType_ = this.hkdfHashType_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.hkdfSalt_ = this.hkdfSalt_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EciesHkdfKemParams) {
            return this.mergeFrom((EciesHkdfKemParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EciesHkdfKemParams other) {
         if (other == EciesHkdfKemParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.curveType_ != 0) {
               this.setCurveTypeValue(other.getCurveTypeValue());
            }

            if (other.hkdfHashType_ != 0) {
               this.setHkdfHashTypeValue(other.getHkdfHashTypeValue());
            }

            if (other.getHkdfSalt() != ByteString.EMPTY) {
               this.setHkdfSalt(other.getHkdfSalt());
            }

            this.mergeUnknownFields(other.getUnknownFields());
            this.onChanged();
            return this;
         }
      }

      public final boolean isInitialized() {
         return true;
      }

      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         if (extensionRegistry == null) {
            throw new NullPointerException();
         } else {
            try {
               boolean done = false;

               while(!done) {
                  int tag = input.readTag();
                  switch (tag) {
                     case 0:
                        done = true;
                        break;
                     case 8:
                        this.curveType_ = input.readEnum();
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.hkdfHashType_ = input.readEnum();
                        this.bitField0_ |= 2;
                        break;
                     case 90:
                        this.hkdfSalt_ = input.readBytes();
                        this.bitField0_ |= 4;
                        break;
                     default:
                        if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                           done = true;
                        }
                  }
               }
            } catch (InvalidProtocolBufferException e) {
               throw e.unwrapIOException();
            } finally {
               this.onChanged();
            }

            return this;
         }
      }

      public int getCurveTypeValue() {
         return this.curveType_;
      }

      public Builder setCurveTypeValue(int value) {
         this.curveType_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public EllipticCurveType getCurveType() {
         EllipticCurveType result = EllipticCurveType.forNumber(this.curveType_);
         return result == null ? EllipticCurveType.UNRECOGNIZED : result;
      }

      public Builder setCurveType(EllipticCurveType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 1;
            this.curveType_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearCurveType() {
         this.bitField0_ &= -2;
         this.curveType_ = 0;
         this.onChanged();
         return this;
      }

      public int getHkdfHashTypeValue() {
         return this.hkdfHashType_;
      }

      public Builder setHkdfHashTypeValue(int value) {
         this.hkdfHashType_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public HashType getHkdfHashType() {
         HashType result = HashType.forNumber(this.hkdfHashType_);
         return result == null ? HashType.UNRECOGNIZED : result;
      }

      public Builder setHkdfHashType(HashType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 2;
            this.hkdfHashType_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearHkdfHashType() {
         this.bitField0_ &= -3;
         this.hkdfHashType_ = 0;
         this.onChanged();
         return this;
      }

      public ByteString getHkdfSalt() {
         return this.hkdfSalt_;
      }

      public Builder setHkdfSalt(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.hkdfSalt_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearHkdfSalt() {
         this.bitField0_ &= -5;
         this.hkdfSalt_ = EciesHkdfKemParams.getDefaultInstance().getHkdfSalt();
         this.onChanged();
         return this;
      }
   }
}
