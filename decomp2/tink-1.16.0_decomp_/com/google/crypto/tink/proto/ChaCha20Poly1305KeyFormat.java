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

public final class ChaCha20Poly1305KeyFormat extends GeneratedMessage implements ChaCha20Poly1305KeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private byte memoizedIsInitialized;
   private static final ChaCha20Poly1305KeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private ChaCha20Poly1305KeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private ChaCha20Poly1305KeyFormat() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Chacha20Poly1305.internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Chacha20Poly1305.internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(ChaCha20Poly1305KeyFormat.class, Builder.class);
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
      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ChaCha20Poly1305KeyFormat)) {
         return super.equals(obj);
      } else {
         ChaCha20Poly1305KeyFormat other = (ChaCha20Poly1305KeyFormat)obj;
         return this.getUnknownFields().equals(other.getUnknownFields());
      }
   }

   public int hashCode() {
      if (this.memoizedHashCode != 0) {
         return this.memoizedHashCode;
      } else {
         int hash = 41;
         hash = 19 * hash + getDescriptor().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ChaCha20Poly1305KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(InputStream input) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ChaCha20Poly1305KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(ChaCha20Poly1305KeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static ChaCha20Poly1305KeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public ChaCha20Poly1305KeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", ChaCha20Poly1305KeyFormat.class.getName());
      DEFAULT_INSTANCE = new ChaCha20Poly1305KeyFormat();
      PARSER = new AbstractParser() {
         public ChaCha20Poly1305KeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = ChaCha20Poly1305KeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements ChaCha20Poly1305KeyFormatOrBuilder {
      public static final Descriptors.Descriptor getDescriptor() {
         return Chacha20Poly1305.internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Chacha20Poly1305.internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(ChaCha20Poly1305KeyFormat.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Chacha20Poly1305.internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor;
      }

      public ChaCha20Poly1305KeyFormat getDefaultInstanceForType() {
         return ChaCha20Poly1305KeyFormat.getDefaultInstance();
      }

      public ChaCha20Poly1305KeyFormat build() {
         ChaCha20Poly1305KeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public ChaCha20Poly1305KeyFormat buildPartial() {
         ChaCha20Poly1305KeyFormat result = new ChaCha20Poly1305KeyFormat(this);
         this.onBuilt();
         return result;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof ChaCha20Poly1305KeyFormat) {
            return this.mergeFrom((ChaCha20Poly1305KeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(ChaCha20Poly1305KeyFormat other) {
         if (other == ChaCha20Poly1305KeyFormat.getDefaultInstance()) {
            return this;
         } else {
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
   }
}
