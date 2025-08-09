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

public final class XChaCha20Poly1305KeyFormat extends GeneratedMessage implements XChaCha20Poly1305KeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   private byte memoizedIsInitialized;
   private static final XChaCha20Poly1305KeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private XChaCha20Poly1305KeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private XChaCha20Poly1305KeyFormat() {
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Xchacha20Poly1305.internal_static_google_crypto_tink_XChaCha20Poly1305KeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Xchacha20Poly1305.internal_static_google_crypto_tink_XChaCha20Poly1305KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(XChaCha20Poly1305KeyFormat.class, Builder.class);
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
      if (this.version_ != 0) {
         output.writeUInt32(1, this.version_);
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

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof XChaCha20Poly1305KeyFormat)) {
         return super.equals(obj);
      } else {
         XChaCha20Poly1305KeyFormat other = (XChaCha20Poly1305KeyFormat)obj;
         if (this.getVersion() != other.getVersion()) {
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
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (XChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(InputStream input) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static XChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static XChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static XChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (XChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(XChaCha20Poly1305KeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static XChaCha20Poly1305KeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public XChaCha20Poly1305KeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", XChaCha20Poly1305KeyFormat.class.getName());
      DEFAULT_INSTANCE = new XChaCha20Poly1305KeyFormat();
      PARSER = new AbstractParser() {
         public XChaCha20Poly1305KeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = XChaCha20Poly1305KeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements XChaCha20Poly1305KeyFormatOrBuilder {
      private int bitField0_;
      private int version_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Xchacha20Poly1305.internal_static_google_crypto_tink_XChaCha20Poly1305KeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Xchacha20Poly1305.internal_static_google_crypto_tink_XChaCha20Poly1305KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(XChaCha20Poly1305KeyFormat.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Xchacha20Poly1305.internal_static_google_crypto_tink_XChaCha20Poly1305KeyFormat_descriptor;
      }

      public XChaCha20Poly1305KeyFormat getDefaultInstanceForType() {
         return XChaCha20Poly1305KeyFormat.getDefaultInstance();
      }

      public XChaCha20Poly1305KeyFormat build() {
         XChaCha20Poly1305KeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public XChaCha20Poly1305KeyFormat buildPartial() {
         XChaCha20Poly1305KeyFormat result = new XChaCha20Poly1305KeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(XChaCha20Poly1305KeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof XChaCha20Poly1305KeyFormat) {
            return this.mergeFrom((XChaCha20Poly1305KeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(XChaCha20Poly1305KeyFormat other) {
         if (other == XChaCha20Poly1305KeyFormat.getDefaultInstance()) {
            return this;
         } else {
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
                     case 8:
                        this.version_ = input.readUInt32();
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
   }
}
