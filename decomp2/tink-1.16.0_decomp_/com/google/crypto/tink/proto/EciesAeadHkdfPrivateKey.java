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

public final class EciesAeadHkdfPrivateKey extends GeneratedMessage implements EciesAeadHkdfPrivateKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
   private EciesAeadHkdfPublicKey publicKey_;
   public static final int KEY_VALUE_FIELD_NUMBER = 3;
   private ByteString keyValue_;
   private byte memoizedIsInitialized;
   private static final EciesAeadHkdfPrivateKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EciesAeadHkdfPrivateKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private EciesAeadHkdfPrivateKey() {
      this.version_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.keyValue_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfPrivateKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasPublicKey() {
      return (this.bitField0_ & 1) != 0;
   }

   public EciesAeadHkdfPublicKey getPublicKey() {
      return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
   }

   public EciesAeadHkdfPublicKeyOrBuilder getPublicKeyOrBuilder() {
      return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
   }

   public ByteString getKeyValue() {
      return this.keyValue_;
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
         output.writeMessage(2, this.getPublicKey());
      }

      if (!this.keyValue_.isEmpty()) {
         output.writeBytes(3, this.keyValue_);
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
            size += CodedOutputStream.computeMessageSize(2, this.getPublicKey());
         }

         if (!this.keyValue_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.keyValue_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EciesAeadHkdfPrivateKey)) {
         return super.equals(obj);
      } else {
         EciesAeadHkdfPrivateKey other = (EciesAeadHkdfPrivateKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasPublicKey() != other.hasPublicKey()) {
            return false;
         } else if (this.hasPublicKey() && !this.getPublicKey().equals(other.getPublicKey())) {
            return false;
         } else if (!this.getKeyValue().equals(other.getKeyValue())) {
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
         if (this.hasPublicKey()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getPublicKey().hashCode();
         }

         hash = 37 * hash + 3;
         hash = 53 * hash + this.getKeyValue().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EciesAeadHkdfPrivateKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream input) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EciesAeadHkdfPrivateKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EciesAeadHkdfPrivateKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EciesAeadHkdfPrivateKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesAeadHkdfPrivateKey.class.getName());
      DEFAULT_INSTANCE = new EciesAeadHkdfPrivateKey();
      PARSER = new AbstractParser() {
         public EciesAeadHkdfPrivateKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EciesAeadHkdfPrivateKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EciesAeadHkdfPrivateKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private EciesAeadHkdfPublicKey publicKey_;
      private SingleFieldBuilder publicKeyBuilder_;
      private ByteString keyValue_;

      public static final Descriptors.Descriptor getDescriptor() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfPrivateKey.class, Builder.class);
      }

      private Builder() {
         this.keyValue_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.keyValue_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (EciesAeadHkdfPrivateKey.alwaysUseFieldBuilders) {
            this.getPublicKeyFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.publicKey_ = null;
         if (this.publicKeyBuilder_ != null) {
            this.publicKeyBuilder_.dispose();
            this.publicKeyBuilder_ = null;
         }

         this.keyValue_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor;
      }

      public EciesAeadHkdfPrivateKey getDefaultInstanceForType() {
         return EciesAeadHkdfPrivateKey.getDefaultInstance();
      }

      public EciesAeadHkdfPrivateKey build() {
         EciesAeadHkdfPrivateKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EciesAeadHkdfPrivateKey buildPartial() {
         EciesAeadHkdfPrivateKey result = new EciesAeadHkdfPrivateKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EciesAeadHkdfPrivateKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.publicKey_ = this.publicKeyBuilder_ == null ? this.publicKey_ : (EciesAeadHkdfPublicKey)this.publicKeyBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.keyValue_ = this.keyValue_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EciesAeadHkdfPrivateKey) {
            return this.mergeFrom((EciesAeadHkdfPrivateKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EciesAeadHkdfPrivateKey other) {
         if (other == EciesAeadHkdfPrivateKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasPublicKey()) {
               this.mergePublicKey(other.getPublicKey());
            }

            if (other.getKeyValue() != ByteString.EMPTY) {
               this.setKeyValue(other.getKeyValue());
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
                        input.readMessage(this.getPublicKeyFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
                        break;
                     case 26:
                        this.keyValue_ = input.readBytes();
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

      public boolean hasPublicKey() {
         return (this.bitField0_ & 2) != 0;
      }

      public EciesAeadHkdfPublicKey getPublicKey() {
         if (this.publicKeyBuilder_ == null) {
            return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
         } else {
            return (EciesAeadHkdfPublicKey)this.publicKeyBuilder_.getMessage();
         }
      }

      public Builder setPublicKey(EciesAeadHkdfPublicKey value) {
         if (this.publicKeyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.publicKey_ = value;
         } else {
            this.publicKeyBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setPublicKey(EciesAeadHkdfPublicKey.Builder builderForValue) {
         if (this.publicKeyBuilder_ == null) {
            this.publicKey_ = builderForValue.build();
         } else {
            this.publicKeyBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergePublicKey(EciesAeadHkdfPublicKey value) {
         if (this.publicKeyBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.publicKey_ != null && this.publicKey_ != EciesAeadHkdfPublicKey.getDefaultInstance()) {
               this.getPublicKeyBuilder().mergeFrom(value);
            } else {
               this.publicKey_ = value;
            }
         } else {
            this.publicKeyBuilder_.mergeFrom(value);
         }

         if (this.publicKey_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearPublicKey() {
         this.bitField0_ &= -3;
         this.publicKey_ = null;
         if (this.publicKeyBuilder_ != null) {
            this.publicKeyBuilder_.dispose();
            this.publicKeyBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public EciesAeadHkdfPublicKey.Builder getPublicKeyBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (EciesAeadHkdfPublicKey.Builder)this.getPublicKeyFieldBuilder().getBuilder();
      }

      public EciesAeadHkdfPublicKeyOrBuilder getPublicKeyOrBuilder() {
         if (this.publicKeyBuilder_ != null) {
            return (EciesAeadHkdfPublicKeyOrBuilder)this.publicKeyBuilder_.getMessageOrBuilder();
         } else {
            return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
         }
      }

      private SingleFieldBuilder getPublicKeyFieldBuilder() {
         if (this.publicKeyBuilder_ == null) {
            this.publicKeyBuilder_ = new SingleFieldBuilder(this.getPublicKey(), this.getParentForChildren(), this.isClean());
            this.publicKey_ = null;
         }

         return this.publicKeyBuilder_;
      }

      public ByteString getKeyValue() {
         return this.keyValue_;
      }

      public Builder setKeyValue(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.keyValue_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearKeyValue() {
         this.bitField0_ &= -5;
         this.keyValue_ = EciesAeadHkdfPrivateKey.getDefaultInstance().getKeyValue();
         this.onChanged();
         return this;
      }
   }
}
