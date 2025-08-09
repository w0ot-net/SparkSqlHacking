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

public final class AesCtrHmacAeadKey extends GeneratedMessage implements AesCtrHmacAeadKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int AES_CTR_KEY_FIELD_NUMBER = 2;
   private AesCtrKey aesCtrKey_;
   public static final int HMAC_KEY_FIELD_NUMBER = 3;
   private HmacKey hmacKey_;
   private byte memoizedIsInitialized;
   private static final AesCtrHmacAeadKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private AesCtrHmacAeadKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private AesCtrHmacAeadKey() {
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKey_fieldAccessorTable.ensureFieldAccessorsInitialized(AesCtrHmacAeadKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasAesCtrKey() {
      return (this.bitField0_ & 1) != 0;
   }

   public AesCtrKey getAesCtrKey() {
      return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
   }

   public AesCtrKeyOrBuilder getAesCtrKeyOrBuilder() {
      return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
   }

   public boolean hasHmacKey() {
      return (this.bitField0_ & 2) != 0;
   }

   public HmacKey getHmacKey() {
      return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
   }

   public HmacKeyOrBuilder getHmacKeyOrBuilder() {
      return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
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
         output.writeMessage(2, this.getAesCtrKey());
      }

      if ((this.bitField0_ & 2) != 0) {
         output.writeMessage(3, this.getHmacKey());
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
            size += CodedOutputStream.computeMessageSize(2, this.getAesCtrKey());
         }

         if ((this.bitField0_ & 2) != 0) {
            size += CodedOutputStream.computeMessageSize(3, this.getHmacKey());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof AesCtrHmacAeadKey)) {
         return super.equals(obj);
      } else {
         AesCtrHmacAeadKey other = (AesCtrHmacAeadKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasAesCtrKey() != other.hasAesCtrKey()) {
            return false;
         } else if (this.hasAesCtrKey() && !this.getAesCtrKey().equals(other.getAesCtrKey())) {
            return false;
         } else if (this.hasHmacKey() != other.hasHmacKey()) {
            return false;
         } else if (this.hasHmacKey() && !this.getHmacKey().equals(other.getHmacKey())) {
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
         if (this.hasAesCtrKey()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getAesCtrKey().hashCode();
         }

         if (this.hasHmacKey()) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getHmacKey().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static AesCtrHmacAeadKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKey parseFrom(InputStream input) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream input) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesCtrHmacAeadKey parseFrom(CodedInputStream input) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(AesCtrHmacAeadKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static AesCtrHmacAeadKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public AesCtrHmacAeadKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesCtrHmacAeadKey.class.getName());
      DEFAULT_INSTANCE = new AesCtrHmacAeadKey();
      PARSER = new AbstractParser() {
         public AesCtrHmacAeadKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = AesCtrHmacAeadKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements AesCtrHmacAeadKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private AesCtrKey aesCtrKey_;
      private SingleFieldBuilder aesCtrKeyBuilder_;
      private HmacKey hmacKey_;
      private SingleFieldBuilder hmacKeyBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKey_fieldAccessorTable.ensureFieldAccessorsInitialized(AesCtrHmacAeadKey.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (AesCtrHmacAeadKey.alwaysUseFieldBuilders) {
            this.getAesCtrKeyFieldBuilder();
            this.getHmacKeyFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.aesCtrKey_ = null;
         if (this.aesCtrKeyBuilder_ != null) {
            this.aesCtrKeyBuilder_.dispose();
            this.aesCtrKeyBuilder_ = null;
         }

         this.hmacKey_ = null;
         if (this.hmacKeyBuilder_ != null) {
            this.hmacKeyBuilder_.dispose();
            this.hmacKeyBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor;
      }

      public AesCtrHmacAeadKey getDefaultInstanceForType() {
         return AesCtrHmacAeadKey.getDefaultInstance();
      }

      public AesCtrHmacAeadKey build() {
         AesCtrHmacAeadKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public AesCtrHmacAeadKey buildPartial() {
         AesCtrHmacAeadKey result = new AesCtrHmacAeadKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(AesCtrHmacAeadKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.aesCtrKey_ = this.aesCtrKeyBuilder_ == null ? this.aesCtrKey_ : (AesCtrKey)this.aesCtrKeyBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.hmacKey_ = this.hmacKeyBuilder_ == null ? this.hmacKey_ : (HmacKey)this.hmacKeyBuilder_.build();
            to_bitField0_ |= 2;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof AesCtrHmacAeadKey) {
            return this.mergeFrom((AesCtrHmacAeadKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(AesCtrHmacAeadKey other) {
         if (other == AesCtrHmacAeadKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasAesCtrKey()) {
               this.mergeAesCtrKey(other.getAesCtrKey());
            }

            if (other.hasHmacKey()) {
               this.mergeHmacKey(other.getHmacKey());
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
                        input.readMessage(this.getAesCtrKeyFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
                        break;
                     case 26:
                        input.readMessage(this.getHmacKeyFieldBuilder().getBuilder(), extensionRegistry);
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

      public boolean hasAesCtrKey() {
         return (this.bitField0_ & 2) != 0;
      }

      public AesCtrKey getAesCtrKey() {
         if (this.aesCtrKeyBuilder_ == null) {
            return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
         } else {
            return (AesCtrKey)this.aesCtrKeyBuilder_.getMessage();
         }
      }

      public Builder setAesCtrKey(AesCtrKey value) {
         if (this.aesCtrKeyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.aesCtrKey_ = value;
         } else {
            this.aesCtrKeyBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setAesCtrKey(AesCtrKey.Builder builderForValue) {
         if (this.aesCtrKeyBuilder_ == null) {
            this.aesCtrKey_ = builderForValue.build();
         } else {
            this.aesCtrKeyBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeAesCtrKey(AesCtrKey value) {
         if (this.aesCtrKeyBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.aesCtrKey_ != null && this.aesCtrKey_ != AesCtrKey.getDefaultInstance()) {
               this.getAesCtrKeyBuilder().mergeFrom(value);
            } else {
               this.aesCtrKey_ = value;
            }
         } else {
            this.aesCtrKeyBuilder_.mergeFrom(value);
         }

         if (this.aesCtrKey_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearAesCtrKey() {
         this.bitField0_ &= -3;
         this.aesCtrKey_ = null;
         if (this.aesCtrKeyBuilder_ != null) {
            this.aesCtrKeyBuilder_.dispose();
            this.aesCtrKeyBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public AesCtrKey.Builder getAesCtrKeyBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (AesCtrKey.Builder)this.getAesCtrKeyFieldBuilder().getBuilder();
      }

      public AesCtrKeyOrBuilder getAesCtrKeyOrBuilder() {
         if (this.aesCtrKeyBuilder_ != null) {
            return (AesCtrKeyOrBuilder)this.aesCtrKeyBuilder_.getMessageOrBuilder();
         } else {
            return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
         }
      }

      private SingleFieldBuilder getAesCtrKeyFieldBuilder() {
         if (this.aesCtrKeyBuilder_ == null) {
            this.aesCtrKeyBuilder_ = new SingleFieldBuilder(this.getAesCtrKey(), this.getParentForChildren(), this.isClean());
            this.aesCtrKey_ = null;
         }

         return this.aesCtrKeyBuilder_;
      }

      public boolean hasHmacKey() {
         return (this.bitField0_ & 4) != 0;
      }

      public HmacKey getHmacKey() {
         if (this.hmacKeyBuilder_ == null) {
            return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
         } else {
            return (HmacKey)this.hmacKeyBuilder_.getMessage();
         }
      }

      public Builder setHmacKey(HmacKey value) {
         if (this.hmacKeyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.hmacKey_ = value;
         } else {
            this.hmacKeyBuilder_.setMessage(value);
         }

         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder setHmacKey(HmacKey.Builder builderForValue) {
         if (this.hmacKeyBuilder_ == null) {
            this.hmacKey_ = builderForValue.build();
         } else {
            this.hmacKeyBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder mergeHmacKey(HmacKey value) {
         if (this.hmacKeyBuilder_ == null) {
            if ((this.bitField0_ & 4) != 0 && this.hmacKey_ != null && this.hmacKey_ != HmacKey.getDefaultInstance()) {
               this.getHmacKeyBuilder().mergeFrom(value);
            } else {
               this.hmacKey_ = value;
            }
         } else {
            this.hmacKeyBuilder_.mergeFrom(value);
         }

         if (this.hmacKey_ != null) {
            this.bitField0_ |= 4;
            this.onChanged();
         }

         return this;
      }

      public Builder clearHmacKey() {
         this.bitField0_ &= -5;
         this.hmacKey_ = null;
         if (this.hmacKeyBuilder_ != null) {
            this.hmacKeyBuilder_.dispose();
            this.hmacKeyBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public HmacKey.Builder getHmacKeyBuilder() {
         this.bitField0_ |= 4;
         this.onChanged();
         return (HmacKey.Builder)this.getHmacKeyFieldBuilder().getBuilder();
      }

      public HmacKeyOrBuilder getHmacKeyOrBuilder() {
         if (this.hmacKeyBuilder_ != null) {
            return (HmacKeyOrBuilder)this.hmacKeyBuilder_.getMessageOrBuilder();
         } else {
            return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
         }
      }

      private SingleFieldBuilder getHmacKeyFieldBuilder() {
         if (this.hmacKeyBuilder_ == null) {
            this.hmacKeyBuilder_ = new SingleFieldBuilder(this.getHmacKey(), this.getParentForChildren(), this.isClean());
            this.hmacKey_ = null;
         }

         return this.hmacKeyBuilder_;
      }
   }
}
