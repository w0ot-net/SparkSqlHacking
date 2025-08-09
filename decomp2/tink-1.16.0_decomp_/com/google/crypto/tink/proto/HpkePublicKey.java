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

public final class HpkePublicKey extends GeneratedMessage implements HpkePublicKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PARAMS_FIELD_NUMBER = 2;
   private HpkeParams params_;
   public static final int PUBLIC_KEY_FIELD_NUMBER = 3;
   private ByteString publicKey_;
   private byte memoizedIsInitialized;
   private static final HpkePublicKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private HpkePublicKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.publicKey_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private HpkePublicKey() {
      this.version_ = 0;
      this.publicKey_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.publicKey_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Hpke.internal_static_google_crypto_tink_HpkePublicKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Hpke.internal_static_google_crypto_tink_HpkePublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(HpkePublicKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public HpkeParams getParams() {
      return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
   }

   public HpkeParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
   }

   public ByteString getPublicKey() {
      return this.publicKey_;
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

      if (!this.publicKey_.isEmpty()) {
         output.writeBytes(3, this.publicKey_);
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

         if (!this.publicKey_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.publicKey_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof HpkePublicKey)) {
         return super.equals(obj);
      } else {
         HpkePublicKey other = (HpkePublicKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
            return false;
         } else if (!this.getPublicKey().equals(other.getPublicKey())) {
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
         hash = 53 * hash + this.getPublicKey().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static HpkePublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data);
   }

   public static HpkePublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data);
   }

   public static HpkePublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data);
   }

   public static HpkePublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HpkePublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HpkePublicKey parseFrom(InputStream input) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HpkePublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static HpkePublicKey parseDelimitedFrom(InputStream input) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static HpkePublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static HpkePublicKey parseFrom(CodedInputStream input) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HpkePublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HpkePublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(HpkePublicKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static HpkePublicKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public HpkePublicKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HpkePublicKey.class.getName());
      DEFAULT_INSTANCE = new HpkePublicKey();
      PARSER = new AbstractParser() {
         public HpkePublicKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = HpkePublicKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements HpkePublicKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private HpkeParams params_;
      private SingleFieldBuilder paramsBuilder_;
      private ByteString publicKey_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Hpke.internal_static_google_crypto_tink_HpkePublicKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Hpke.internal_static_google_crypto_tink_HpkePublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(HpkePublicKey.class, Builder.class);
      }

      private Builder() {
         this.publicKey_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.publicKey_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (HpkePublicKey.alwaysUseFieldBuilders) {
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

         this.publicKey_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Hpke.internal_static_google_crypto_tink_HpkePublicKey_descriptor;
      }

      public HpkePublicKey getDefaultInstanceForType() {
         return HpkePublicKey.getDefaultInstance();
      }

      public HpkePublicKey build() {
         HpkePublicKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public HpkePublicKey buildPartial() {
         HpkePublicKey result = new HpkePublicKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(HpkePublicKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (HpkeParams)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.publicKey_ = this.publicKey_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof HpkePublicKey) {
            return this.mergeFrom((HpkePublicKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(HpkePublicKey other) {
         if (other == HpkePublicKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasParams()) {
               this.mergeParams(other.getParams());
            }

            if (other.getPublicKey() != ByteString.EMPTY) {
               this.setPublicKey(other.getPublicKey());
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
                        this.publicKey_ = input.readBytes();
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

      public boolean hasParams() {
         return (this.bitField0_ & 2) != 0;
      }

      public HpkeParams getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
         } else {
            return (HpkeParams)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(HpkeParams value) {
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

      public Builder setParams(HpkeParams.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(HpkeParams value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.params_ != null && this.params_ != HpkeParams.getDefaultInstance()) {
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

      public HpkeParams.Builder getParamsBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (HpkeParams.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public HpkeParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (HpkeParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? HpkeParams.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }

      public ByteString getPublicKey() {
         return this.publicKey_;
      }

      public Builder setPublicKey(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.publicKey_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearPublicKey() {
         this.bitField0_ &= -5;
         this.publicKey_ = HpkePublicKey.getDefaultInstance().getPublicKey();
         this.onChanged();
         return this;
      }
   }
}
