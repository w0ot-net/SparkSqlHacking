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

public final class PrfBasedDeriverParams extends GeneratedMessage implements PrfBasedDeriverParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int DERIVED_KEY_TEMPLATE_FIELD_NUMBER = 1;
   private KeyTemplate derivedKeyTemplate_;
   private byte memoizedIsInitialized;
   private static final PrfBasedDeriverParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private PrfBasedDeriverParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private PrfBasedDeriverParams() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverParams_fieldAccessorTable.ensureFieldAccessorsInitialized(PrfBasedDeriverParams.class, Builder.class);
   }

   public boolean hasDerivedKeyTemplate() {
      return (this.bitField0_ & 1) != 0;
   }

   public KeyTemplate getDerivedKeyTemplate() {
      return this.derivedKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.derivedKeyTemplate_;
   }

   public KeyTemplateOrBuilder getDerivedKeyTemplateOrBuilder() {
      return this.derivedKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.derivedKeyTemplate_;
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
         output.writeMessage(1, this.getDerivedKeyTemplate());
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
            size += CodedOutputStream.computeMessageSize(1, this.getDerivedKeyTemplate());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof PrfBasedDeriverParams)) {
         return super.equals(obj);
      } else {
         PrfBasedDeriverParams other = (PrfBasedDeriverParams)obj;
         if (this.hasDerivedKeyTemplate() != other.hasDerivedKeyTemplate()) {
            return false;
         } else if (this.hasDerivedKeyTemplate() && !this.getDerivedKeyTemplate().equals(other.getDerivedKeyTemplate())) {
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
         if (this.hasDerivedKeyTemplate()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getDerivedKeyTemplate().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static PrfBasedDeriverParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data);
   }

   public static PrfBasedDeriverParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PrfBasedDeriverParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static PrfBasedDeriverParams parseFrom(InputStream input) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static PrfBasedDeriverParams parseDelimitedFrom(InputStream input) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static PrfBasedDeriverParams parseFrom(CodedInputStream input) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static PrfBasedDeriverParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (PrfBasedDeriverParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(PrfBasedDeriverParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static PrfBasedDeriverParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public PrfBasedDeriverParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", PrfBasedDeriverParams.class.getName());
      DEFAULT_INSTANCE = new PrfBasedDeriverParams();
      PARSER = new AbstractParser() {
         public PrfBasedDeriverParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = PrfBasedDeriverParams.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements PrfBasedDeriverParamsOrBuilder {
      private int bitField0_;
      private KeyTemplate derivedKeyTemplate_;
      private SingleFieldBuilder derivedKeyTemplateBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverParams_fieldAccessorTable.ensureFieldAccessorsInitialized(PrfBasedDeriverParams.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (PrfBasedDeriverParams.alwaysUseFieldBuilders) {
            this.getDerivedKeyTemplateFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.derivedKeyTemplate_ = null;
         if (this.derivedKeyTemplateBuilder_ != null) {
            this.derivedKeyTemplateBuilder_.dispose();
            this.derivedKeyTemplateBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return PrfBasedDeriver.internal_static_google_crypto_tink_PrfBasedDeriverParams_descriptor;
      }

      public PrfBasedDeriverParams getDefaultInstanceForType() {
         return PrfBasedDeriverParams.getDefaultInstance();
      }

      public PrfBasedDeriverParams build() {
         PrfBasedDeriverParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public PrfBasedDeriverParams buildPartial() {
         PrfBasedDeriverParams result = new PrfBasedDeriverParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(PrfBasedDeriverParams result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.derivedKeyTemplate_ = this.derivedKeyTemplateBuilder_ == null ? this.derivedKeyTemplate_ : (KeyTemplate)this.derivedKeyTemplateBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof PrfBasedDeriverParams) {
            return this.mergeFrom((PrfBasedDeriverParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(PrfBasedDeriverParams other) {
         if (other == PrfBasedDeriverParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasDerivedKeyTemplate()) {
               this.mergeDerivedKeyTemplate(other.getDerivedKeyTemplate());
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
                        input.readMessage(this.getDerivedKeyTemplateFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 1;
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

      public boolean hasDerivedKeyTemplate() {
         return (this.bitField0_ & 1) != 0;
      }

      public KeyTemplate getDerivedKeyTemplate() {
         if (this.derivedKeyTemplateBuilder_ == null) {
            return this.derivedKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.derivedKeyTemplate_;
         } else {
            return (KeyTemplate)this.derivedKeyTemplateBuilder_.getMessage();
         }
      }

      public Builder setDerivedKeyTemplate(KeyTemplate value) {
         if (this.derivedKeyTemplateBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.derivedKeyTemplate_ = value;
         } else {
            this.derivedKeyTemplateBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setDerivedKeyTemplate(KeyTemplate.Builder builderForValue) {
         if (this.derivedKeyTemplateBuilder_ == null) {
            this.derivedKeyTemplate_ = builderForValue.build();
         } else {
            this.derivedKeyTemplateBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeDerivedKeyTemplate(KeyTemplate value) {
         if (this.derivedKeyTemplateBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.derivedKeyTemplate_ != null && this.derivedKeyTemplate_ != KeyTemplate.getDefaultInstance()) {
               this.getDerivedKeyTemplateBuilder().mergeFrom(value);
            } else {
               this.derivedKeyTemplate_ = value;
            }
         } else {
            this.derivedKeyTemplateBuilder_.mergeFrom(value);
         }

         if (this.derivedKeyTemplate_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearDerivedKeyTemplate() {
         this.bitField0_ &= -2;
         this.derivedKeyTemplate_ = null;
         if (this.derivedKeyTemplateBuilder_ != null) {
            this.derivedKeyTemplateBuilder_.dispose();
            this.derivedKeyTemplateBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KeyTemplate.Builder getDerivedKeyTemplateBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (KeyTemplate.Builder)this.getDerivedKeyTemplateFieldBuilder().getBuilder();
      }

      public KeyTemplateOrBuilder getDerivedKeyTemplateOrBuilder() {
         if (this.derivedKeyTemplateBuilder_ != null) {
            return (KeyTemplateOrBuilder)this.derivedKeyTemplateBuilder_.getMessageOrBuilder();
         } else {
            return this.derivedKeyTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.derivedKeyTemplate_;
         }
      }

      private SingleFieldBuilder getDerivedKeyTemplateFieldBuilder() {
         if (this.derivedKeyTemplateBuilder_ == null) {
            this.derivedKeyTemplateBuilder_ = new SingleFieldBuilder(this.getDerivedKeyTemplate(), this.getParentForChildren(), this.isClean());
            this.derivedKeyTemplate_ = null;
         }

         return this.derivedKeyTemplateBuilder_;
      }
   }
}
