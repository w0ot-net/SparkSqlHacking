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

public final class KmsEnvelopeAeadKeyFormat extends GeneratedMessage implements KmsEnvelopeAeadKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int KEK_URI_FIELD_NUMBER = 1;
   private volatile Object kekUri_;
   public static final int DEK_TEMPLATE_FIELD_NUMBER = 2;
   private KeyTemplate dekTemplate_;
   private byte memoizedIsInitialized;
   private static final KmsEnvelopeAeadKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private KmsEnvelopeAeadKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.kekUri_ = "";
      this.memoizedIsInitialized = -1;
   }

   private KmsEnvelopeAeadKeyFormat() {
      this.kekUri_ = "";
      this.memoizedIsInitialized = -1;
      this.kekUri_ = "";
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsEnvelopeAeadKeyFormat.class, Builder.class);
   }

   public String getKekUri() {
      Object ref = this.kekUri_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.kekUri_ = s;
         return s;
      }
   }

   public ByteString getKekUriBytes() {
      Object ref = this.kekUri_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.kekUri_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public boolean hasDekTemplate() {
      return (this.bitField0_ & 1) != 0;
   }

   public KeyTemplate getDekTemplate() {
      return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
   }

   public KeyTemplateOrBuilder getDekTemplateOrBuilder() {
      return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
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
      if (!GeneratedMessage.isStringEmpty(this.kekUri_)) {
         GeneratedMessage.writeString(output, 1, this.kekUri_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(2, this.getDekTemplate());
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessage.isStringEmpty(this.kekUri_)) {
            size += GeneratedMessage.computeStringSize(1, this.kekUri_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getDekTemplate());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof KmsEnvelopeAeadKeyFormat)) {
         return super.equals(obj);
      } else {
         KmsEnvelopeAeadKeyFormat other = (KmsEnvelopeAeadKeyFormat)obj;
         if (!this.getKekUri().equals(other.getKekUri())) {
            return false;
         } else if (this.hasDekTemplate() != other.hasDekTemplate()) {
            return false;
         } else if (this.hasDekTemplate() && !this.getDekTemplate().equals(other.getDekTemplate())) {
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
         hash = 53 * hash + this.getKekUri().hashCode();
         if (this.hasDekTemplate()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getDekTemplate().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream input) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(KmsEnvelopeAeadKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static KmsEnvelopeAeadKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public KmsEnvelopeAeadKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KmsEnvelopeAeadKeyFormat.class.getName());
      DEFAULT_INSTANCE = new KmsEnvelopeAeadKeyFormat();
      PARSER = new AbstractParser() {
         public KmsEnvelopeAeadKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = KmsEnvelopeAeadKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements KmsEnvelopeAeadKeyFormatOrBuilder {
      private int bitField0_;
      private Object kekUri_;
      private KeyTemplate dekTemplate_;
      private SingleFieldBuilder dekTemplateBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsEnvelopeAeadKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.kekUri_ = "";
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.kekUri_ = "";
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (KmsEnvelopeAeadKeyFormat.alwaysUseFieldBuilders) {
            this.getDekTemplateFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.kekUri_ = "";
         this.dekTemplate_ = null;
         if (this.dekTemplateBuilder_ != null) {
            this.dekTemplateBuilder_.dispose();
            this.dekTemplateBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor;
      }

      public KmsEnvelopeAeadKeyFormat getDefaultInstanceForType() {
         return KmsEnvelopeAeadKeyFormat.getDefaultInstance();
      }

      public KmsEnvelopeAeadKeyFormat build() {
         KmsEnvelopeAeadKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public KmsEnvelopeAeadKeyFormat buildPartial() {
         KmsEnvelopeAeadKeyFormat result = new KmsEnvelopeAeadKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(KmsEnvelopeAeadKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.kekUri_ = this.kekUri_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.dekTemplate_ = this.dekTemplateBuilder_ == null ? this.dekTemplate_ : (KeyTemplate)this.dekTemplateBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof KmsEnvelopeAeadKeyFormat) {
            return this.mergeFrom((KmsEnvelopeAeadKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(KmsEnvelopeAeadKeyFormat other) {
         if (other == KmsEnvelopeAeadKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getKekUri().isEmpty()) {
               this.kekUri_ = other.kekUri_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (other.hasDekTemplate()) {
               this.mergeDekTemplate(other.getDekTemplate());
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
                        this.kekUri_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage(this.getDekTemplateFieldBuilder().getBuilder(), extensionRegistry);
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

      public String getKekUri() {
         Object ref = this.kekUri_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.kekUri_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getKekUriBytes() {
         Object ref = this.kekUri_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.kekUri_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setKekUri(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.kekUri_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearKekUri() {
         this.kekUri_ = KmsEnvelopeAeadKeyFormat.getDefaultInstance().getKekUri();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setKekUriBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            KmsEnvelopeAeadKeyFormat.checkByteStringIsUtf8(value);
            this.kekUri_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public boolean hasDekTemplate() {
         return (this.bitField0_ & 2) != 0;
      }

      public KeyTemplate getDekTemplate() {
         if (this.dekTemplateBuilder_ == null) {
            return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
         } else {
            return (KeyTemplate)this.dekTemplateBuilder_.getMessage();
         }
      }

      public Builder setDekTemplate(KeyTemplate value) {
         if (this.dekTemplateBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.dekTemplate_ = value;
         } else {
            this.dekTemplateBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setDekTemplate(KeyTemplate.Builder builderForValue) {
         if (this.dekTemplateBuilder_ == null) {
            this.dekTemplate_ = builderForValue.build();
         } else {
            this.dekTemplateBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeDekTemplate(KeyTemplate value) {
         if (this.dekTemplateBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.dekTemplate_ != null && this.dekTemplate_ != KeyTemplate.getDefaultInstance()) {
               this.getDekTemplateBuilder().mergeFrom(value);
            } else {
               this.dekTemplate_ = value;
            }
         } else {
            this.dekTemplateBuilder_.mergeFrom(value);
         }

         if (this.dekTemplate_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearDekTemplate() {
         this.bitField0_ &= -3;
         this.dekTemplate_ = null;
         if (this.dekTemplateBuilder_ != null) {
            this.dekTemplateBuilder_.dispose();
            this.dekTemplateBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KeyTemplate.Builder getDekTemplateBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (KeyTemplate.Builder)this.getDekTemplateFieldBuilder().getBuilder();
      }

      public KeyTemplateOrBuilder getDekTemplateOrBuilder() {
         if (this.dekTemplateBuilder_ != null) {
            return (KeyTemplateOrBuilder)this.dekTemplateBuilder_.getMessageOrBuilder();
         } else {
            return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
         }
      }

      private SingleFieldBuilder getDekTemplateFieldBuilder() {
         if (this.dekTemplateBuilder_ == null) {
            this.dekTemplateBuilder_ = new SingleFieldBuilder(this.getDekTemplate(), this.getParentForChildren(), this.isClean());
            this.dekTemplate_ = null;
         }

         return this.dekTemplateBuilder_;
      }
   }
}
