package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Empty extends GeneratedMessage implements EmptyOrBuilder {
   private static final long serialVersionUID = 0L;
   private byte memoizedIsInitialized;
   private static final Empty DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Empty(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private Empty() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EmptyProto.internal_static_google_protobuf_Empty_fieldAccessorTable.ensureFieldAccessorsInitialized(Empty.class, Builder.class);
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
      } else if (!(obj instanceof Empty)) {
         return super.equals(obj);
      } else {
         Empty other = (Empty)obj;
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

   public static Empty parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data);
   }

   public static Empty parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Empty parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data);
   }

   public static Empty parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Empty parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data);
   }

   public static Empty parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Empty)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Empty parseFrom(InputStream input) throws IOException {
      return (Empty)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Empty parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Empty)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Empty parseDelimitedFrom(InputStream input) throws IOException {
      return (Empty)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Empty parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Empty)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Empty parseFrom(CodedInputStream input) throws IOException {
      return (Empty)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Empty parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Empty)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Empty prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Empty getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Empty getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Empty.class.getName());
      DEFAULT_INSTANCE = new Empty();
      PARSER = new AbstractParser() {
         public Empty parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Empty.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EmptyOrBuilder {
      public static final Descriptors.Descriptor getDescriptor() {
         return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EmptyProto.internal_static_google_protobuf_Empty_fieldAccessorTable.ensureFieldAccessorsInitialized(Empty.class, Builder.class);
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
         return EmptyProto.internal_static_google_protobuf_Empty_descriptor;
      }

      public Empty getDefaultInstanceForType() {
         return Empty.getDefaultInstance();
      }

      public Empty build() {
         Empty result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Empty buildPartial() {
         Empty result = new Empty(this);
         this.onBuilt();
         return result;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Empty) {
            return this.mergeFrom((Empty)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Empty other) {
         if (other == Empty.getDefaultInstance()) {
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
