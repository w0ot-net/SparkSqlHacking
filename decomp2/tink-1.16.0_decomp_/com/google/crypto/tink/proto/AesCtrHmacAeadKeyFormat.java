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

public final class AesCtrHmacAeadKeyFormat extends GeneratedMessage implements AesCtrHmacAeadKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int AES_CTR_KEY_FORMAT_FIELD_NUMBER = 1;
   private AesCtrKeyFormat aesCtrKeyFormat_;
   public static final int HMAC_KEY_FORMAT_FIELD_NUMBER = 2;
   private HmacKeyFormat hmacKeyFormat_;
   private byte memoizedIsInitialized;
   private static final AesCtrHmacAeadKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private AesCtrHmacAeadKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private AesCtrHmacAeadKeyFormat() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(AesCtrHmacAeadKeyFormat.class, Builder.class);
   }

   public boolean hasAesCtrKeyFormat() {
      return (this.bitField0_ & 1) != 0;
   }

   public AesCtrKeyFormat getAesCtrKeyFormat() {
      return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
   }

   public AesCtrKeyFormatOrBuilder getAesCtrKeyFormatOrBuilder() {
      return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
   }

   public boolean hasHmacKeyFormat() {
      return (this.bitField0_ & 2) != 0;
   }

   public HmacKeyFormat getHmacKeyFormat() {
      return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
   }

   public HmacKeyFormatOrBuilder getHmacKeyFormatOrBuilder() {
      return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
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
      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(1, this.getAesCtrKeyFormat());
      }

      if ((this.bitField0_ & 2) != 0) {
         output.writeMessage(2, this.getHmacKeyFormat());
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(1, this.getAesCtrKeyFormat());
         }

         if ((this.bitField0_ & 2) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getHmacKeyFormat());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof AesCtrHmacAeadKeyFormat)) {
         return super.equals(obj);
      } else {
         AesCtrHmacAeadKeyFormat other = (AesCtrHmacAeadKeyFormat)obj;
         if (this.hasAesCtrKeyFormat() != other.hasAesCtrKeyFormat()) {
            return false;
         } else if (this.hasAesCtrKeyFormat() && !this.getAesCtrKeyFormat().equals(other.getAesCtrKeyFormat())) {
            return false;
         } else if (this.hasHmacKeyFormat() != other.hasHmacKeyFormat()) {
            return false;
         } else if (this.hasHmacKeyFormat() && !this.getHmacKeyFormat().equals(other.getHmacKeyFormat())) {
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
         if (this.hasAesCtrKeyFormat()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getAesCtrKeyFormat().hashCode();
         }

         if (this.hasHmacKeyFormat()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getHmacKeyFormat().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesCtrHmacAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(InputStream input) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesCtrHmacAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(AesCtrHmacAeadKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static AesCtrHmacAeadKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public AesCtrHmacAeadKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesCtrHmacAeadKeyFormat.class.getName());
      DEFAULT_INSTANCE = new AesCtrHmacAeadKeyFormat();
      PARSER = new AbstractParser() {
         public AesCtrHmacAeadKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = AesCtrHmacAeadKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements AesCtrHmacAeadKeyFormatOrBuilder {
      private int bitField0_;
      private AesCtrKeyFormat aesCtrKeyFormat_;
      private SingleFieldBuilder aesCtrKeyFormatBuilder_;
      private HmacKeyFormat hmacKeyFormat_;
      private SingleFieldBuilder hmacKeyFormatBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(AesCtrHmacAeadKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (AesCtrHmacAeadKeyFormat.alwaysUseFieldBuilders) {
            this.getAesCtrKeyFormatFieldBuilder();
            this.getHmacKeyFormatFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.aesCtrKeyFormat_ = null;
         if (this.aesCtrKeyFormatBuilder_ != null) {
            this.aesCtrKeyFormatBuilder_.dispose();
            this.aesCtrKeyFormatBuilder_ = null;
         }

         this.hmacKeyFormat_ = null;
         if (this.hmacKeyFormatBuilder_ != null) {
            this.hmacKeyFormatBuilder_.dispose();
            this.hmacKeyFormatBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return AesCtrHmacAead.internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor;
      }

      public AesCtrHmacAeadKeyFormat getDefaultInstanceForType() {
         return AesCtrHmacAeadKeyFormat.getDefaultInstance();
      }

      public AesCtrHmacAeadKeyFormat build() {
         AesCtrHmacAeadKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public AesCtrHmacAeadKeyFormat buildPartial() {
         AesCtrHmacAeadKeyFormat result = new AesCtrHmacAeadKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(AesCtrHmacAeadKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.aesCtrKeyFormat_ = this.aesCtrKeyFormatBuilder_ == null ? this.aesCtrKeyFormat_ : (AesCtrKeyFormat)this.aesCtrKeyFormatBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.hmacKeyFormat_ = this.hmacKeyFormatBuilder_ == null ? this.hmacKeyFormat_ : (HmacKeyFormat)this.hmacKeyFormatBuilder_.build();
            to_bitField0_ |= 2;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof AesCtrHmacAeadKeyFormat) {
            return this.mergeFrom((AesCtrHmacAeadKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(AesCtrHmacAeadKeyFormat other) {
         if (other == AesCtrHmacAeadKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasAesCtrKeyFormat()) {
               this.mergeAesCtrKeyFormat(other.getAesCtrKeyFormat());
            }

            if (other.hasHmacKeyFormat()) {
               this.mergeHmacKeyFormat(other.getHmacKeyFormat());
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
                     case 10:
                        input.readMessage(this.getAesCtrKeyFormatFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage(this.getHmacKeyFormatFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
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

      public boolean hasAesCtrKeyFormat() {
         return (this.bitField0_ & 1) != 0;
      }

      public AesCtrKeyFormat getAesCtrKeyFormat() {
         if (this.aesCtrKeyFormatBuilder_ == null) {
            return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
         } else {
            return (AesCtrKeyFormat)this.aesCtrKeyFormatBuilder_.getMessage();
         }
      }

      public Builder setAesCtrKeyFormat(AesCtrKeyFormat value) {
         if (this.aesCtrKeyFormatBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.aesCtrKeyFormat_ = value;
         } else {
            this.aesCtrKeyFormatBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setAesCtrKeyFormat(AesCtrKeyFormat.Builder builderForValue) {
         if (this.aesCtrKeyFormatBuilder_ == null) {
            this.aesCtrKeyFormat_ = builderForValue.build();
         } else {
            this.aesCtrKeyFormatBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeAesCtrKeyFormat(AesCtrKeyFormat value) {
         if (this.aesCtrKeyFormatBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.aesCtrKeyFormat_ != null && this.aesCtrKeyFormat_ != AesCtrKeyFormat.getDefaultInstance()) {
               this.getAesCtrKeyFormatBuilder().mergeFrom(value);
            } else {
               this.aesCtrKeyFormat_ = value;
            }
         } else {
            this.aesCtrKeyFormatBuilder_.mergeFrom(value);
         }

         if (this.aesCtrKeyFormat_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearAesCtrKeyFormat() {
         this.bitField0_ &= -2;
         this.aesCtrKeyFormat_ = null;
         if (this.aesCtrKeyFormatBuilder_ != null) {
            this.aesCtrKeyFormatBuilder_.dispose();
            this.aesCtrKeyFormatBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public AesCtrKeyFormat.Builder getAesCtrKeyFormatBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (AesCtrKeyFormat.Builder)this.getAesCtrKeyFormatFieldBuilder().getBuilder();
      }

      public AesCtrKeyFormatOrBuilder getAesCtrKeyFormatOrBuilder() {
         if (this.aesCtrKeyFormatBuilder_ != null) {
            return (AesCtrKeyFormatOrBuilder)this.aesCtrKeyFormatBuilder_.getMessageOrBuilder();
         } else {
            return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
         }
      }

      private SingleFieldBuilder getAesCtrKeyFormatFieldBuilder() {
         if (this.aesCtrKeyFormatBuilder_ == null) {
            this.aesCtrKeyFormatBuilder_ = new SingleFieldBuilder(this.getAesCtrKeyFormat(), this.getParentForChildren(), this.isClean());
            this.aesCtrKeyFormat_ = null;
         }

         return this.aesCtrKeyFormatBuilder_;
      }

      public boolean hasHmacKeyFormat() {
         return (this.bitField0_ & 2) != 0;
      }

      public HmacKeyFormat getHmacKeyFormat() {
         if (this.hmacKeyFormatBuilder_ == null) {
            return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
         } else {
            return (HmacKeyFormat)this.hmacKeyFormatBuilder_.getMessage();
         }
      }

      public Builder setHmacKeyFormat(HmacKeyFormat value) {
         if (this.hmacKeyFormatBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.hmacKeyFormat_ = value;
         } else {
            this.hmacKeyFormatBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setHmacKeyFormat(HmacKeyFormat.Builder builderForValue) {
         if (this.hmacKeyFormatBuilder_ == null) {
            this.hmacKeyFormat_ = builderForValue.build();
         } else {
            this.hmacKeyFormatBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeHmacKeyFormat(HmacKeyFormat value) {
         if (this.hmacKeyFormatBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.hmacKeyFormat_ != null && this.hmacKeyFormat_ != HmacKeyFormat.getDefaultInstance()) {
               this.getHmacKeyFormatBuilder().mergeFrom(value);
            } else {
               this.hmacKeyFormat_ = value;
            }
         } else {
            this.hmacKeyFormatBuilder_.mergeFrom(value);
         }

         if (this.hmacKeyFormat_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearHmacKeyFormat() {
         this.bitField0_ &= -3;
         this.hmacKeyFormat_ = null;
         if (this.hmacKeyFormatBuilder_ != null) {
            this.hmacKeyFormatBuilder_.dispose();
            this.hmacKeyFormatBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public HmacKeyFormat.Builder getHmacKeyFormatBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (HmacKeyFormat.Builder)this.getHmacKeyFormatFieldBuilder().getBuilder();
      }

      public HmacKeyFormatOrBuilder getHmacKeyFormatOrBuilder() {
         if (this.hmacKeyFormatBuilder_ != null) {
            return (HmacKeyFormatOrBuilder)this.hmacKeyFormatBuilder_.getMessageOrBuilder();
         } else {
            return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
         }
      }

      private SingleFieldBuilder getHmacKeyFormatFieldBuilder() {
         if (this.hmacKeyFormatBuilder_ == null) {
            this.hmacKeyFormatBuilder_ = new SingleFieldBuilder(this.getHmacKeyFormat(), this.getParentForChildren(), this.isClean());
            this.hmacKeyFormat_ = null;
         }

         return this.hmacKeyFormatBuilder_;
      }
   }
}
