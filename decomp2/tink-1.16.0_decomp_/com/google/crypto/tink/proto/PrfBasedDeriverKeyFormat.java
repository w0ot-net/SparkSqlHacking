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

public final class PrfBasedDeriverKeyFormat extends GeneratedMessage implements PrfBasedDeriverKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int PRF_KEY_TEMPLATE_FIELD_NUMBER = 1;
   private KeyTemplate prfKeyTemplate_;
   public static final int PARAMS_FIELD_NUMBER = 2;
   private PrfBasedDeriverParams params_;
   private byte memoizedIsInitialized;
   private static final PrfBasedDeriverKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private PrfBasedDeriverKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private PrfBasedDeriverKeyFormat() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(PrfBasedDeriverKeyFormat.class, Builder.class);
   }

   public boolean hasPrfKeyTemplate() {
      return (this.bitField0_ & 1) != 0;
   }

   public KeyTemplate getPrfKeyTemplate() {
      return this.prfKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.prfKeyTemplate_;
   }

   public KeyTemplateOrBuilder getPrfKeyTemplateOrBuilder() {
      return this.prfKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.prfKeyTemplate_;
   }

   public boolean hasParams() {
      return (this.bitField0_ & 2) != 0;
   }

   public PrfBasedDeriverParams getParams() {
      return this.params_ == null ? PrfBasedDeriverParams.getDefaultInstance() : this.params_;
   }

   public PrfBasedDeriverParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? PrfBasedDeriverParams.getDefaultInstance() : this.params_;
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
         output.writeMessage(1, this.getPrfKeyTemplate());
      }

      if ((this.bitField0_ & 2) != 0) {
         output.writeMessage(2, this.getParams());
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
            size += CodedOutputStream.computeMessageSize(1, this.getPrfKeyTemplate());
         }

         if ((this.bitField0_ & 2) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getParams());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof PrfBasedDeriverKeyFormat)) {
         return super.equals(obj);
      } else {
         PrfBasedDeriverKeyFormat other = (PrfBasedDeriverKeyFormat)obj;
         if (this.hasPrfKeyTemplate() != other.hasPrfKeyTemplate()) {
            return false;
         } else if (this.hasPrfKeyTemplate() && !this.getPrfKeyTemplate().equals(other.getPrfKeyTemplate())) {
            return false;
         } else if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
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
         if (this.hasPrfKeyTemplate()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getPrfKeyTemplate().hashCode();
         }

         if (this.hasParams()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getParams().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static PrfBasedDeriverKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(InputStream input) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static PrfBasedDeriverKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(PrfBasedDeriverKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static PrfBasedDeriverKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public PrfBasedDeriverKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", PrfBasedDeriverKeyFormat.class.getName());
      DEFAULT_INSTANCE = new PrfBasedDeriverKeyFormat();
      PARSER = new AbstractParser() {
         public PrfBasedDeriverKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = PrfBasedDeriverKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements PrfBasedDeriverKeyFormatOrBuilder {
      private int bitField0_;
      private KeyTemplate prfKeyTemplate_;
      private SingleFieldBuilder prfKeyTemplateBuilder_;
      private PrfBasedDeriverParams params_;
      private SingleFieldBuilder paramsBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(PrfBasedDeriverKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (PrfBasedDeriverKeyFormat.alwaysUseFieldBuilders) {
            this.getPrfKeyTemplateFieldBuilder();
            this.getParamsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.prfKeyTemplate_ = null;
         if (this.prfKeyTemplateBuilder_ != null) {
            this.prfKeyTemplateBuilder_.dispose();
            this.prfKeyTemplateBuilder_ = null;
         }

         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverKeyFormat_descriptor;
      }

      public PrfBasedDeriverKeyFormat getDefaultInstanceForType() {
         return PrfBasedDeriverKeyFormat.getDefaultInstance();
      }

      public PrfBasedDeriverKeyFormat build() {
         PrfBasedDeriverKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public PrfBasedDeriverKeyFormat buildPartial() {
         PrfBasedDeriverKeyFormat result = new PrfBasedDeriverKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(PrfBasedDeriverKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.prfKeyTemplate_ = this.prfKeyTemplateBuilder_ == null ? this.prfKeyTemplate_ : (KeyTemplate)this.prfKeyTemplateBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (PrfBasedDeriverParams)this.paramsBuilder_.build();
            to_bitField0_ |= 2;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof PrfBasedDeriverKeyFormat) {
            return this.mergeFrom((PrfBasedDeriverKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(PrfBasedDeriverKeyFormat other) {
         if (other == PrfBasedDeriverKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasPrfKeyTemplate()) {
               this.mergePrfKeyTemplate(other.getPrfKeyTemplate());
            }

            if (other.hasParams()) {
               this.mergeParams(other.getParams());
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
                        input.readMessage(this.getPrfKeyTemplateFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage(this.getParamsFieldBuilder().getBuilder(), extensionRegistry);
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

      public boolean hasPrfKeyTemplate() {
         return (this.bitField0_ & 1) != 0;
      }

      public KeyTemplate getPrfKeyTemplate() {
         if (this.prfKeyTemplateBuilder_ == null) {
            return this.prfKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.prfKeyTemplate_;
         } else {
            return (KeyTemplate)this.prfKeyTemplateBuilder_.getMessage();
         }
      }

      public Builder setPrfKeyTemplate(KeyTemplate value) {
         if (this.prfKeyTemplateBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.prfKeyTemplate_ = value;
         } else {
            this.prfKeyTemplateBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setPrfKeyTemplate(KeyTemplate.Builder builderForValue) {
         if (this.prfKeyTemplateBuilder_ == null) {
            this.prfKeyTemplate_ = builderForValue.build();
         } else {
            this.prfKeyTemplateBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergePrfKeyTemplate(KeyTemplate value) {
         if (this.prfKeyTemplateBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.prfKeyTemplate_ != null && this.prfKeyTemplate_ != KeyTemplate.getDefaultInstance()) {
               this.getPrfKeyTemplateBuilder().mergeFrom(value);
            } else {
               this.prfKeyTemplate_ = value;
            }
         } else {
            this.prfKeyTemplateBuilder_.mergeFrom(value);
         }

         if (this.prfKeyTemplate_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearPrfKeyTemplate() {
         this.bitField0_ &= -2;
         this.prfKeyTemplate_ = null;
         if (this.prfKeyTemplateBuilder_ != null) {
            this.prfKeyTemplateBuilder_.dispose();
            this.prfKeyTemplateBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KeyTemplate.Builder getPrfKeyTemplateBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (KeyTemplate.Builder)this.getPrfKeyTemplateFieldBuilder().getBuilder();
      }

      public KeyTemplateOrBuilder getPrfKeyTemplateOrBuilder() {
         if (this.prfKeyTemplateBuilder_ != null) {
            return (KeyTemplateOrBuilder)this.prfKeyTemplateBuilder_.getMessageOrBuilder();
         } else {
            return this.prfKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.prfKeyTemplate_;
         }
      }

      private SingleFieldBuilder getPrfKeyTemplateFieldBuilder() {
         if (this.prfKeyTemplateBuilder_ == null) {
            this.prfKeyTemplateBuilder_ = new SingleFieldBuilder(this.getPrfKeyTemplate(), this.getParentForChildren(), this.isClean());
            this.prfKeyTemplate_ = null;
         }

         return this.prfKeyTemplateBuilder_;
      }

      public boolean hasParams() {
         return (this.bitField0_ & 2) != 0;
      }

      public PrfBasedDeriverParams getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? PrfBasedDeriverParams.getDefaultInstance() : this.params_;
         } else {
            return (PrfBasedDeriverParams)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(PrfBasedDeriverParams value) {
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

      public Builder setParams(PrfBasedDeriverParams.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(PrfBasedDeriverParams value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.params_ != null && this.params_ != PrfBasedDeriverParams.getDefaultInstance()) {
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

      public PrfBasedDeriverParams.Builder getParamsBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (PrfBasedDeriverParams.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public PrfBasedDeriverParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (PrfBasedDeriverParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? PrfBasedDeriverParams.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }
   }
}
