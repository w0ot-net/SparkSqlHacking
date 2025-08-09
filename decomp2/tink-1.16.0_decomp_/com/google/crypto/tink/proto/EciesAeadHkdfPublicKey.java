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
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class EciesAeadHkdfPublicKey extends GeneratedMessage implements EciesAeadHkdfPublicKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PARAMS_FIELD_NUMBER = 2;
   private EciesAeadHkdfParams params_;
   public static final int X_FIELD_NUMBER = 3;
   private ByteString x_;
   public static final int Y_FIELD_NUMBER = 4;
   private ByteString y_;
   private byte memoizedIsInitialized;
   private static final EciesAeadHkdfPublicKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EciesAeadHkdfPublicKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.x_ = ByteString.EMPTY;
      this.y_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private EciesAeadHkdfPublicKey() {
      this.version_ = 0;
      this.x_ = ByteString.EMPTY;
      this.y_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.x_ = ByteString.EMPTY;
      this.y_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfPublicKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public EciesAeadHkdfParams getParams() {
      return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
   }

   public EciesAeadHkdfParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
   }

   public ByteString getX() {
      return this.x_;
   }

   public ByteString getY() {
      return this.y_;
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
      if (this.version_ != 0) {
         output.writeUInt32(1, this.version_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(2, this.getParams());
      }

      if (!this.x_.isEmpty()) {
         output.writeBytes(3, this.x_);
      }

      if (!this.y_.isEmpty()) {
         output.writeBytes(4, this.y_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.version_ != 0) {
            size += CodedOutputStream.computeUInt32Size(1, this.version_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getParams());
         }

         if (!this.x_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.x_);
         }

         if (!this.y_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(4, this.y_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EciesAeadHkdfPublicKey)) {
         return super.equals(obj);
      } else {
         EciesAeadHkdfPublicKey other = (EciesAeadHkdfPublicKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
            return false;
         } else if (!this.getX().equals(other.getX())) {
            return false;
         } else if (!this.getY().equals(other.getY())) {
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
         hash = 53 * hash + this.getVersion();
         if (this.hasParams()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getParams().hashCode();
         }

         hash = 37 * hash + 3;
         hash = 53 * hash + this.getX().hashCode();
         hash = 37 * hash + 4;
         hash = 53 * hash + this.getY().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EciesAeadHkdfPublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPublicKey parseFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfPublicKey parseDelimitedFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfPublicKey parseFrom(CodedInputStream input) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EciesAeadHkdfPublicKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EciesAeadHkdfPublicKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EciesAeadHkdfPublicKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesAeadHkdfPublicKey.class.getName());
      DEFAULT_INSTANCE = new EciesAeadHkdfPublicKey();
      PARSER = new AbstractParser() {
         public EciesAeadHkdfPublicKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EciesAeadHkdfPublicKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EciesAeadHkdfPublicKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private EciesAeadHkdfParams params_;
      private SingleFieldBuilder paramsBuilder_;
      private ByteString x_;
      private ByteString y_;

      public static final Descriptors.Descriptor getDescriptor() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfPublicKey.class, Builder.class);
      }

      private Builder() {
         this.x_ = ByteString.EMPTY;
         this.y_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.x_ = ByteString.EMPTY;
         this.y_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (EciesAeadHkdfPublicKey.alwaysUseFieldBuilders) {
            this.getParamsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.x_ = ByteString.EMPTY;
         this.y_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor;
      }

      public EciesAeadHkdfPublicKey getDefaultInstanceForType() {
         return EciesAeadHkdfPublicKey.getDefaultInstance();
      }

      public EciesAeadHkdfPublicKey build() {
         EciesAeadHkdfPublicKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EciesAeadHkdfPublicKey buildPartial() {
         EciesAeadHkdfPublicKey result = new EciesAeadHkdfPublicKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EciesAeadHkdfPublicKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (EciesAeadHkdfParams)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.x_ = this.x_;
         }

         if ((from_bitField0_ & 8) != 0) {
            result.y_ = this.y_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EciesAeadHkdfPublicKey) {
            return this.mergeFrom((EciesAeadHkdfPublicKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EciesAeadHkdfPublicKey other) {
         if (other == EciesAeadHkdfPublicKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasParams()) {
               this.mergeParams(other.getParams());
            }

            if (other.getX() != ByteString.EMPTY) {
               this.setX(other.getX());
            }

            if (other.getY() != ByteString.EMPTY) {
               this.setY(other.getY());
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
                        this.version_ = input.readUInt32();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage(this.getParamsFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
                        break;
                     case 26:
                        this.x_ = input.readBytes();
                        this.bitField0_ |= 4;
                        break;
                     case 34:
                        this.y_ = input.readBytes();
                        this.bitField0_ |= 8;
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

      public int getVersion() {
         return this.version_;
      }

      public Builder setVersion(int value) {
         this.version_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearVersion() {
         this.bitField0_ &= -2;
         this.version_ = 0;
         this.onChanged();
         return this;
      }

      public boolean hasParams() {
         return (this.bitField0_ & 2) != 0;
      }

      public EciesAeadHkdfParams getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
         } else {
            return (EciesAeadHkdfParams)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(EciesAeadHkdfParams value) {
         if (this.paramsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.params_ = value;
         } else {
            this.paramsBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setParams(EciesAeadHkdfParams.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(EciesAeadHkdfParams value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.params_ != null && this.params_ != EciesAeadHkdfParams.getDefaultInstance()) {
               this.getParamsBuilder().mergeFrom(value);
            } else {
               this.params_ = value;
            }
         } else {
            this.paramsBuilder_.mergeFrom(value);
         }

         if (this.params_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearParams() {
         this.bitField0_ &= -3;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public EciesAeadHkdfParams.Builder getParamsBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (EciesAeadHkdfParams.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public EciesAeadHkdfParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (EciesAeadHkdfParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }

      public ByteString getX() {
         return this.x_;
      }

      public Builder setX(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.x_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearX() {
         this.bitField0_ &= -5;
         this.x_ = EciesAeadHkdfPublicKey.getDefaultInstance().getX();
         this.onChanged();
         return this;
      }

      public ByteString getY() {
         return this.y_;
      }

      public Builder setY(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.y_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public Builder clearY() {
         this.bitField0_ &= -9;
         this.y_ = EciesAeadHkdfPublicKey.getDefaultInstance().getY();
         this.onChanged();
         return this;
      }
   }
}
