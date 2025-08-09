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

public final class HpkePrivateKey extends GeneratedMessage implements HpkePrivateKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
   private HpkePublicKey publicKey_;
   public static final int PRIVATE_KEY_FIELD_NUMBER = 3;
   private ByteString privateKey_;
   private byte memoizedIsInitialized;
   private static final HpkePrivateKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private HpkePrivateKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.privateKey_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private HpkePrivateKey() {
      this.version_ = 0;
      this.privateKey_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.privateKey_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Hpke.internal_static_google_crypto_tink_HpkePrivateKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Hpke.internal_static_google_crypto_tink_HpkePrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(HpkePrivateKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasPublicKey() {
      return (this.bitField0_ & 1) != 0;
   }

   public HpkePublicKey getPublicKey() {
      return this.publicKey_ == null ? HpkePublicKey.getDefaultInstance() : this.publicKey_;
   }

   public HpkePublicKeyOrBuilder getPublicKeyOrBuilder() {
      return this.publicKey_ == null ? HpkePublicKey.getDefaultInstance() : this.publicKey_;
   }

   public ByteString getPrivateKey() {
      return this.privateKey_;
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

      if (!this.privateKey_.isEmpty()) {
         output.writeBytes(3, this.privateKey_);
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

         if (!this.privateKey_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.privateKey_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof HpkePrivateKey)) {
         return super.equals(obj);
      } else {
         HpkePrivateKey other = (HpkePrivateKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasPublicKey() != other.hasPublicKey()) {
            return false;
         } else if (this.hasPublicKey() && !this.getPublicKey().equals(other.getPublicKey())) {
            return false;
         } else if (!this.getPrivateKey().equals(other.getPrivateKey())) {
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
         hash = 53 * hash + this.getPrivateKey().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static HpkePrivateKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data);
   }

   public static HpkePrivateKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePrivateKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data);
   }

   public static HpkePrivateKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePrivateKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data);
   }

   public static HpkePrivateKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePrivateKey parseFrom(InputStream input) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HpkePrivateKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static HpkePrivateKey parseDelimitedFrom(InputStream input) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static HpkePrivateKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static HpkePrivateKey parseFrom(CodedInputStream input) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HpkePrivateKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(HpkePrivateKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static HpkePrivateKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public HpkePrivateKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HpkePrivateKey.class.getName());
      DEFAULT_INSTANCE = new HpkePrivateKey();
      PARSER = new AbstractParser() {
         public HpkePrivateKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = HpkePrivateKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements HpkePrivateKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private HpkePublicKey publicKey_;
      private SingleFieldBuilder publicKeyBuilder_;
      private ByteString privateKey_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Hpke.internal_static_google_crypto_tink_HpkePrivateKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Hpke.internal_static_google_crypto_tink_HpkePrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(HpkePrivateKey.class, Builder.class);
      }

      private Builder() {
         this.privateKey_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.privateKey_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (HpkePrivateKey.alwaysUseFieldBuilders) {
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

         this.privateKey_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Hpke.internal_static_google_crypto_tink_HpkePrivateKey_descriptor;
      }

      public HpkePrivateKey getDefaultInstanceForType() {
         return HpkePrivateKey.getDefaultInstance();
      }

      public HpkePrivateKey build() {
         HpkePrivateKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public HpkePrivateKey buildPartial() {
         HpkePrivateKey result = new HpkePrivateKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(HpkePrivateKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.publicKey_ = this.publicKeyBuilder_ == null ? this.publicKey_ : (HpkePublicKey)this.publicKeyBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.privateKey_ = this.privateKey_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof HpkePrivateKey) {
            return this.mergeFrom((HpkePrivateKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(HpkePrivateKey other) {
         if (other == HpkePrivateKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasPublicKey()) {
               this.mergePublicKey(other.getPublicKey());
            }

            if (other.getPrivateKey() != ByteString.EMPTY) {
               this.setPrivateKey(other.getPrivateKey());
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
                        this.privateKey_ = input.readBytes();
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

      public HpkePublicKey getPublicKey() {
         if (this.publicKeyBuilder_ == null) {
            return this.publicKey_ == null ? HpkePublicKey.getDefaultInstance() : this.publicKey_;
         } else {
            return (HpkePublicKey)this.publicKeyBuilder_.getMessage();
         }
      }

      public Builder setPublicKey(HpkePublicKey value) {
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

      public Builder setPublicKey(HpkePublicKey.Builder builderForValue) {
         if (this.publicKeyBuilder_ == null) {
            this.publicKey_ = builderForValue.build();
         } else {
            this.publicKeyBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergePublicKey(HpkePublicKey value) {
         if (this.publicKeyBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.publicKey_ != null && this.publicKey_ != HpkePublicKey.getDefaultInstance()) {
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

      public HpkePublicKey.Builder getPublicKeyBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (HpkePublicKey.Builder)this.getPublicKeyFieldBuilder().getBuilder();
      }

      public HpkePublicKeyOrBuilder getPublicKeyOrBuilder() {
         if (this.publicKeyBuilder_ != null) {
            return (HpkePublicKeyOrBuilder)this.publicKeyBuilder_.getMessageOrBuilder();
         } else {
            return this.publicKey_ == null ? HpkePublicKey.getDefaultInstance() : this.publicKey_;
         }
      }

      private SingleFieldBuilder getPublicKeyFieldBuilder() {
         if (this.publicKeyBuilder_ == null) {
            this.publicKeyBuilder_ = new SingleFieldBuilder(this.getPublicKey(), this.getParentForChildren(), this.isClean());
            this.publicKey_ = null;
         }

         return this.publicKeyBuilder_;
      }

      public ByteString getPrivateKey() {
         return this.privateKey_;
      }

      public Builder setPrivateKey(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.privateKey_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearPrivateKey() {
         this.bitField0_ &= -5;
         this.privateKey_ = HpkePrivateKey.getDefaultInstance().getPrivateKey();
         this.onChanged();
         return this;
      }
   }
}
