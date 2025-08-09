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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class KmsAeadKeyFormat extends GeneratedMessage implements KmsAeadKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int KEY_URI_FIELD_NUMBER = 1;
   private volatile Object keyUri_;
   private byte memoizedIsInitialized;
   private static final KmsAeadKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private KmsAeadKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.keyUri_ = "";
      this.memoizedIsInitialized = -1;
   }

   private KmsAeadKeyFormat() {
      this.keyUri_ = "";
      this.memoizedIsInitialized = -1;
      this.keyUri_ = "";
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return KmsAead.internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return KmsAead.internal_static_google_crypto_tink_KmsAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsAeadKeyFormat.class, Builder.class);
   }

   public String getKeyUri() {
      Object ref = this.keyUri_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.keyUri_ = s;
         return s;
      }
   }

   public ByteString getKeyUriBytes() {
      Object ref = this.keyUri_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.keyUri_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
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
      if (!GeneratedMessage.isStringEmpty(this.keyUri_)) {
         GeneratedMessage.writeString(output, 1, this.keyUri_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessage.isStringEmpty(this.keyUri_)) {
            size += GeneratedMessage.computeStringSize(1, this.keyUri_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof KmsAeadKeyFormat)) {
         return super.equals(obj);
      } else {
         KmsAeadKeyFormat other = (KmsAeadKeyFormat)obj;
         if (!this.getKeyUri().equals(other.getKeyUri())) {
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
         hash = 53 * hash + this.getKeyUri().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static KmsAeadKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsAeadKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsAeadKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsAeadKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsAeadKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data);
   }

   public static KmsAeadKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsAeadKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsAeadKeyFormat parseFrom(InputStream input) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsAeadKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsAeadKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static KmsAeadKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsAeadKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsAeadKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsAeadKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(KmsAeadKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static KmsAeadKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public KmsAeadKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KmsAeadKeyFormat.class.getName());
      DEFAULT_INSTANCE = new KmsAeadKeyFormat();
      PARSER = new AbstractParser() {
         public KmsAeadKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = KmsAeadKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements KmsAeadKeyFormatOrBuilder {
      private int bitField0_;
      private Object keyUri_;

      public static final Descriptors.Descriptor getDescriptor() {
         return KmsAead.internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return KmsAead.internal_static_google_crypto_tink_KmsAeadKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsAeadKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.keyUri_ = "";
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.keyUri_ = "";
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.keyUri_ = "";
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return KmsAead.internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor;
      }

      public KmsAeadKeyFormat getDefaultInstanceForType() {
         return KmsAeadKeyFormat.getDefaultInstance();
      }

      public KmsAeadKeyFormat build() {
         KmsAeadKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public KmsAeadKeyFormat buildPartial() {
         KmsAeadKeyFormat result = new KmsAeadKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(KmsAeadKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.keyUri_ = this.keyUri_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof KmsAeadKeyFormat) {
            return this.mergeFrom((KmsAeadKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(KmsAeadKeyFormat other) {
         if (other == KmsAeadKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getKeyUri().isEmpty()) {
               this.keyUri_ = other.keyUri_;
               this.bitField0_ |= 1;
               this.onChanged();
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
                        this.keyUri_ = input.readStringRequireUtf8();
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

      public String getKeyUri() {
         Object ref = this.keyUri_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.keyUri_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getKeyUriBytes() {
         Object ref = this.keyUri_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.keyUri_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setKeyUri(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.keyUri_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearKeyUri() {
         this.keyUri_ = KmsAeadKeyFormat.getDefaultInstance().getKeyUri();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setKeyUriBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            KmsAeadKeyFormat.checkByteStringIsUtf8(value);
            this.keyUri_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }
   }
}
