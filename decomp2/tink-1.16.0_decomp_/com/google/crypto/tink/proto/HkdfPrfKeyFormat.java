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

public final class HkdfPrfKeyFormat extends GeneratedMessage implements HkdfPrfKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int PARAMS_FIELD_NUMBER = 1;
   private HkdfPrfParams params_;
   public static final int KEY_SIZE_FIELD_NUMBER = 2;
   private int keySize_;
   public static final int VERSION_FIELD_NUMBER = 3;
   private int version_;
   private byte memoizedIsInitialized;
   private static final HkdfPrfKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private HkdfPrfKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.keySize_ = 0;
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private HkdfPrfKeyFormat() {
      this.keySize_ = 0;
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(HkdfPrfKeyFormat.class, Builder.class);
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public HkdfPrfParams getParams() {
      return this.params_ == null ? HkdfPrfParams.getDefaultInstance() : this.params_;
   }

   public HkdfPrfParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? HkdfPrfParams.getDefaultInstance() : this.params_;
   }

   public int getKeySize() {
      return this.keySize_;
   }

   public int getVersion() {
      return this.version_;
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
         output.writeMessage(1, this.getParams());
      }

      if (this.keySize_ != 0) {
         output.writeUInt32(2, this.keySize_);
      }

      if (this.version_ != 0) {
         output.writeUInt32(3, this.version_);
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
            size += CodedOutputStream.computeMessageSize(1, this.getParams());
         }

         if (this.keySize_ != 0) {
            size += CodedOutputStream.computeUInt32Size(2, this.keySize_);
         }

         if (this.version_ != 0) {
            size += CodedOutputStream.computeUInt32Size(3, this.version_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof HkdfPrfKeyFormat)) {
         return super.equals(obj);
      } else {
         HkdfPrfKeyFormat other = (HkdfPrfKeyFormat)obj;
         if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
            return false;
         } else if (this.getKeySize() != other.getKeySize()) {
            return false;
         } else if (this.getVersion() != other.getVersion()) {
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
         if (this.hasParams()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getParams().hashCode();
         }

         hash = 37 * hash + 2;
         hash = 53 * hash + this.getKeySize();
         hash = 37 * hash + 3;
         hash = 53 * hash + this.getVersion();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static HkdfPrfKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data);
   }

   public static HkdfPrfKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data);
   }

   public static HkdfPrfKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data);
   }

   public static HkdfPrfKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfKeyFormat parseFrom(InputStream input) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HkdfPrfKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static HkdfPrfKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static HkdfPrfKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static HkdfPrfKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HkdfPrfKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(HkdfPrfKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static HkdfPrfKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public HkdfPrfKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HkdfPrfKeyFormat.class.getName());
      DEFAULT_INSTANCE = new HkdfPrfKeyFormat();
      PARSER = new AbstractParser() {
         public HkdfPrfKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = HkdfPrfKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements HkdfPrfKeyFormatOrBuilder {
      private int bitField0_;
      private HkdfPrfParams params_;
      private SingleFieldBuilder paramsBuilder_;
      private int keySize_;
      private int version_;

      public static final Descriptors.Descriptor getDescriptor() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(HkdfPrfKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (HkdfPrfKeyFormat.alwaysUseFieldBuilders) {
            this.getParamsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.keySize_ = 0;
         this.version_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfKeyFormat_descriptor;
      }

      public HkdfPrfKeyFormat getDefaultInstanceForType() {
         return HkdfPrfKeyFormat.getDefaultInstance();
      }

      public HkdfPrfKeyFormat build() {
         HkdfPrfKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public HkdfPrfKeyFormat buildPartial() {
         HkdfPrfKeyFormat result = new HkdfPrfKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(HkdfPrfKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (HkdfPrfParams)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.keySize_ = this.keySize_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.version_ = this.version_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof HkdfPrfKeyFormat) {
            return this.mergeFrom((HkdfPrfKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(HkdfPrfKeyFormat other) {
         if (other == HkdfPrfKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasParams()) {
               this.mergeParams(other.getParams());
            }

            if (other.getKeySize() != 0) {
               this.setKeySize(other.getKeySize());
            }

            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
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
                        input.readMessage(this.getParamsFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.keySize_ = input.readUInt32();
                        this.bitField0_ |= 2;
                        break;
                     case 24:
                        this.version_ = input.readUInt32();
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

      public boolean hasParams() {
         return (this.bitField0_ & 1) != 0;
      }

      public HkdfPrfParams getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? HkdfPrfParams.getDefaultInstance() : this.params_;
         } else {
            return (HkdfPrfParams)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(HkdfPrfParams value) {
         if (this.paramsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.params_ = value;
         } else {
            this.paramsBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setParams(HkdfPrfParams.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(HkdfPrfParams value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.params_ != null && this.params_ != HkdfPrfParams.getDefaultInstance()) {
               this.getParamsBuilder().mergeFrom(value);
            } else {
               this.params_ = value;
            }
         } else {
            this.paramsBuilder_.mergeFrom(value);
         }

         if (this.params_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearParams() {
         this.bitField0_ &= -2;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public HkdfPrfParams.Builder getParamsBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (HkdfPrfParams.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public HkdfPrfParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (HkdfPrfParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? HkdfPrfParams.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }

      public int getKeySize() {
         return this.keySize_;
      }

      public Builder setKeySize(int value) {
         this.keySize_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder clearKeySize() {
         this.bitField0_ &= -3;
         this.keySize_ = 0;
         this.onChanged();
         return this;
      }

      public int getVersion() {
         return this.version_;
      }

      public Builder setVersion(int value) {
         this.version_ = value;
         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder clearVersion() {
         this.bitField0_ &= -5;
         this.version_ = 0;
         this.onChanged();
         return this;
      }
   }
}
