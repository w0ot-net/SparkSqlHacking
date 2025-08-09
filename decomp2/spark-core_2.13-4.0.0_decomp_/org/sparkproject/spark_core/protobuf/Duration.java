package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Duration extends GeneratedMessage implements DurationOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int SECONDS_FIELD_NUMBER = 1;
   private long seconds_;
   public static final int NANOS_FIELD_NUMBER = 2;
   private int nanos_;
   private byte memoizedIsInitialized;
   private static final Duration DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Duration(GeneratedMessage.Builder builder) {
      super(builder);
      this.seconds_ = 0L;
      this.nanos_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private Duration() {
      this.seconds_ = 0L;
      this.nanos_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return DurationProto.internal_static_google_protobuf_Duration_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return DurationProto.internal_static_google_protobuf_Duration_fieldAccessorTable.ensureFieldAccessorsInitialized(Duration.class, Builder.class);
   }

   public long getSeconds() {
      return this.seconds_;
   }

   public int getNanos() {
      return this.nanos_;
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
      if (this.seconds_ != 0L) {
         output.writeInt64(1, this.seconds_);
      }

      if (this.nanos_ != 0) {
         output.writeInt32(2, this.nanos_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.seconds_ != 0L) {
            size += CodedOutputStream.computeInt64Size(1, this.seconds_);
         }

         if (this.nanos_ != 0) {
            size += CodedOutputStream.computeInt32Size(2, this.nanos_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Duration)) {
         return super.equals(obj);
      } else {
         Duration other = (Duration)obj;
         if (this.getSeconds() != other.getSeconds()) {
            return false;
         } else if (this.getNanos() != other.getNanos()) {
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
         hash = 53 * hash + Internal.hashLong(this.getSeconds());
         hash = 37 * hash + 2;
         hash = 53 * hash + this.getNanos();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Duration parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data);
   }

   public static Duration parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Duration parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data);
   }

   public static Duration parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Duration parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data);
   }

   public static Duration parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Duration)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Duration parseFrom(InputStream input) throws IOException {
      return (Duration)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Duration parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Duration)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Duration parseDelimitedFrom(InputStream input) throws IOException {
      return (Duration)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Duration parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Duration)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Duration parseFrom(CodedInputStream input) throws IOException {
      return (Duration)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Duration parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Duration)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Duration prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Duration getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Duration getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Duration.class.getName());
      DEFAULT_INSTANCE = new Duration();
      PARSER = new AbstractParser() {
         public Duration parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Duration.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements DurationOrBuilder {
      private int bitField0_;
      private long seconds_;
      private int nanos_;

      public static final Descriptors.Descriptor getDescriptor() {
         return DurationProto.internal_static_google_protobuf_Duration_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return DurationProto.internal_static_google_protobuf_Duration_fieldAccessorTable.ensureFieldAccessorsInitialized(Duration.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.seconds_ = 0L;
         this.nanos_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return DurationProto.internal_static_google_protobuf_Duration_descriptor;
      }

      public Duration getDefaultInstanceForType() {
         return Duration.getDefaultInstance();
      }

      public Duration build() {
         Duration result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Duration buildPartial() {
         Duration result = new Duration(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(Duration result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.seconds_ = this.seconds_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.nanos_ = this.nanos_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Duration) {
            return this.mergeFrom((Duration)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Duration other) {
         if (other == Duration.getDefaultInstance()) {
            return this;
         } else {
            if (other.getSeconds() != 0L) {
               this.setSeconds(other.getSeconds());
            }

            if (other.getNanos() != 0) {
               this.setNanos(other.getNanos());
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
                        this.seconds_ = input.readInt64();
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.nanos_ = input.readInt32();
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

      public long getSeconds() {
         return this.seconds_;
      }

      public Builder setSeconds(long value) {
         this.seconds_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearSeconds() {
         this.bitField0_ &= -2;
         this.seconds_ = 0L;
         this.onChanged();
         return this;
      }

      public int getNanos() {
         return this.nanos_;
      }

      public Builder setNanos(int value) {
         this.nanos_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder clearNanos() {
         this.bitField0_ &= -3;
         this.nanos_ = 0;
         this.onChanged();
         return this;
      }
   }
}
