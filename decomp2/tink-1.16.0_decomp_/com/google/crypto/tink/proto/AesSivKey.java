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

public final class AesSivKey extends GeneratedMessage implements AesSivKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int KEY_VALUE_FIELD_NUMBER = 2;
   private ByteString keyValue_;
   private byte memoizedIsInitialized;
   private static final AesSivKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private AesSivKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private AesSivKey() {
      this.version_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.keyValue_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return AesSiv.internal_static_google_crypto_tink_AesSivKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return AesSiv.internal_static_google_crypto_tink_AesSivKey_fieldAccessorTable.ensureFieldAccessorsInitialized(AesSivKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
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

      if (!this.keyValue_.isEmpty()) {
         output.writeBytes(2, this.keyValue_);
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

         if (!this.keyValue_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(2, this.keyValue_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof AesSivKey)) {
         return super.equals(obj);
      } else {
         AesSivKey other = (AesSivKey)obj;
         if (this.getVersion() != other.getVersion()) {
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
         hash = 37 * hash + 2;
         hash = 53 * hash + this.getKeyValue().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static AesSivKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data);
   }

   public static AesSivKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesSivKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data);
   }

   public static AesSivKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesSivKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data);
   }

   public static AesSivKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesSivKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesSivKey parseFrom(InputStream input) throws IOException {
      return (AesSivKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesSivKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesSivKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesSivKey parseDelimitedFrom(InputStream input) throws IOException {
      return (AesSivKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static AesSivKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesSivKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesSivKey parseFrom(CodedInputStream input) throws IOException {
      return (AesSivKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesSivKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesSivKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(AesSivKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static AesSivKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public AesSivKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesSivKey.class.getName());
      DEFAULT_INSTANCE = new AesSivKey();
      PARSER = new AbstractParser() {
         public AesSivKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = AesSivKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements AesSivKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private ByteString keyValue_;

      public static final Descriptors.Descriptor getDescriptor() {
         return AesSiv.internal_static_google_crypto_tink_AesSivKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return AesSiv.internal_static_google_crypto_tink_AesSivKey_fieldAccessorTable.ensureFieldAccessorsInitialized(AesSivKey.class, Builder.class);
      }

      private Builder() {
         this.keyValue_ = ByteString.EMPTY;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.keyValue_ = ByteString.EMPTY;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.keyValue_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return AesSiv.internal_static_google_crypto_tink_AesSivKey_descriptor;
      }

      public AesSivKey getDefaultInstanceForType() {
         return AesSivKey.getDefaultInstance();
      }

      public AesSivKey build() {
         AesSivKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public AesSivKey buildPartial() {
         AesSivKey result = new AesSivKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(AesSivKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.keyValue_ = this.keyValue_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof AesSivKey) {
            return this.mergeFrom((AesSivKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(AesSivKey other) {
         if (other == AesSivKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
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
                        this.keyValue_ = input.readBytes();
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

      public ByteString getKeyValue() {
         return this.keyValue_;
      }

      public Builder setKeyValue(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.keyValue_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }
      }

      public Builder clearKeyValue() {
         this.bitField0_ &= -3;
         this.keyValue_ = AesSivKey.getDefaultInstance().getKeyValue();
         this.onChanged();
         return this;
      }
   }
}
