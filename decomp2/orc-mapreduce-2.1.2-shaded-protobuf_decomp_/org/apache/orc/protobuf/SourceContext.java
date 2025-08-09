package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class SourceContext extends GeneratedMessageV3 implements SourceContextOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int FILE_NAME_FIELD_NUMBER = 1;
   private volatile Object fileName_;
   private byte memoizedIsInitialized;
   private static final SourceContext DEFAULT_INSTANCE = new SourceContext();
   private static final Parser PARSER = new AbstractParser() {
      public SourceContext parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = SourceContext.newBuilder();

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

   private SourceContext(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.fileName_ = "";
      this.memoizedIsInitialized = -1;
   }

   private SourceContext() {
      this.fileName_ = "";
      this.memoizedIsInitialized = -1;
      this.fileName_ = "";
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SourceContext();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor;
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SourceContextProto.internal_static_google_protobuf_SourceContext_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceContext.class, Builder.class);
   }

   public String getFileName() {
      Object ref = this.fileName_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.fileName_ = s;
         return s;
      }
   }

   public ByteString getFileNameBytes() {
      Object ref = this.fileName_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.fileName_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.fileName_)) {
         GeneratedMessageV3.writeString(output, 1, this.fileName_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessageV3.isStringEmpty(this.fileName_)) {
            size += GeneratedMessageV3.computeStringSize(1, this.fileName_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof SourceContext)) {
         return super.equals(obj);
      } else {
         SourceContext other = (SourceContext)obj;
         if (!this.getFileName().equals(other.getFileName())) {
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
         hash = 53 * hash + this.getFileName().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static SourceContext parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data);
   }

   public static SourceContext parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data, extensionRegistry);
   }

   public static SourceContext parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data);
   }

   public static SourceContext parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data, extensionRegistry);
   }

   public static SourceContext parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data);
   }

   public static SourceContext parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SourceContext)PARSER.parseFrom(data, extensionRegistry);
   }

   public static SourceContext parseFrom(InputStream input) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static SourceContext parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static SourceContext parseDelimitedFrom(InputStream input) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static SourceContext parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static SourceContext parseFrom(CodedInputStream input) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static SourceContext parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (SourceContext)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(SourceContext prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static SourceContext getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public SourceContext getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements SourceContextOrBuilder {
      private int bitField0_;
      private Object fileName_;

      public static final Descriptors.Descriptor getDescriptor() {
         return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor;
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return SourceContextProto.internal_static_google_protobuf_SourceContext_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceContext.class, Builder.class);
      }

      private Builder() {
         this.fileName_ = "";
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
         this.fileName_ = "";
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.fileName_ = "";
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor;
      }

      public SourceContext getDefaultInstanceForType() {
         return SourceContext.getDefaultInstance();
      }

      public SourceContext build() {
         SourceContext result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public SourceContext buildPartial() {
         SourceContext result = new SourceContext(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(SourceContext result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.fileName_ = this.fileName_;
         }

      }

      public Builder clone() {
         return (Builder)super.clone();
      }

      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.setField(field, value);
      }

      public Builder clearField(Descriptors.FieldDescriptor field) {
         return (Builder)super.clearField(field);
      }

      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
         return (Builder)super.clearOneof(oneof);
      }

      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         return (Builder)super.setRepeatedField(field, index, value);
      }

      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.addRepeatedField(field, value);
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof SourceContext) {
            return this.mergeFrom((SourceContext)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(SourceContext other) {
         if (other == SourceContext.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getFileName().isEmpty()) {
               this.fileName_ = other.fileName_;
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
                        this.fileName_ = input.readStringRequireUtf8();
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

      public String getFileName() {
         Object ref = this.fileName_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.fileName_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getFileNameBytes() {
         Object ref = this.fileName_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.fileName_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setFileName(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.fileName_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearFileName() {
         this.fileName_ = SourceContext.getDefaultInstance().getFileName();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setFileNameBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.fileName_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }
   }
}
