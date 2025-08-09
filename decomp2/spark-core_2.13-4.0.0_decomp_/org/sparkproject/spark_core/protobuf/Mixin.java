package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Mixin extends GeneratedMessage implements MixinOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int NAME_FIELD_NUMBER = 1;
   private volatile Object name_;
   public static final int ROOT_FIELD_NUMBER = 2;
   private volatile Object root_;
   private byte memoizedIsInitialized;
   private static final Mixin DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Mixin(GeneratedMessage.Builder builder) {
      super(builder);
      this.name_ = "";
      this.root_ = "";
      this.memoizedIsInitialized = -1;
   }

   private Mixin() {
      this.name_ = "";
      this.root_ = "";
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.root_ = "";
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable.ensureFieldAccessorsInitialized(Mixin.class, Builder.class);
   }

   public String getName() {
      Object ref = this.name_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.name_ = s;
         return s;
      }
   }

   public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.name_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public String getRoot() {
      Object ref = this.root_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.root_ = s;
         return s;
      }
   }

   public ByteString getRootBytes() {
      Object ref = this.root_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.root_ = b;
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
      if (!GeneratedMessage.isStringEmpty(this.name_)) {
         GeneratedMessage.writeString(output, 1, this.name_);
      }

      if (!GeneratedMessage.isStringEmpty(this.root_)) {
         GeneratedMessage.writeString(output, 2, this.root_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessage.isStringEmpty(this.name_)) {
            size += GeneratedMessage.computeStringSize(1, this.name_);
         }

         if (!GeneratedMessage.isStringEmpty(this.root_)) {
            size += GeneratedMessage.computeStringSize(2, this.root_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Mixin)) {
         return super.equals(obj);
      } else {
         Mixin other = (Mixin)obj;
         if (!this.getName().equals(other.getName())) {
            return false;
         } else if (!this.getRoot().equals(other.getRoot())) {
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
         hash = 53 * hash + this.getName().hashCode();
         hash = 37 * hash + 2;
         hash = 53 * hash + this.getRoot().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Mixin parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data);
   }

   public static Mixin parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Mixin parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data);
   }

   public static Mixin parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Mixin parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data);
   }

   public static Mixin parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Mixin)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Mixin parseFrom(InputStream input) throws IOException {
      return (Mixin)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Mixin parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Mixin)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Mixin parseDelimitedFrom(InputStream input) throws IOException {
      return (Mixin)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Mixin parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Mixin)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Mixin parseFrom(CodedInputStream input) throws IOException {
      return (Mixin)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Mixin parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Mixin)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Mixin prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Mixin getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Mixin getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Mixin.class.getName());
      DEFAULT_INSTANCE = new Mixin();
      PARSER = new AbstractParser() {
         public Mixin parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Mixin.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements MixinOrBuilder {
      private int bitField0_;
      private Object name_;
      private Object root_;

      public static final Descriptors.Descriptor getDescriptor() {
         return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable.ensureFieldAccessorsInitialized(Mixin.class, Builder.class);
      }

      private Builder() {
         this.name_ = "";
         this.root_ = "";
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.name_ = "";
         this.root_ = "";
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.name_ = "";
         this.root_ = "";
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
      }

      public Mixin getDefaultInstanceForType() {
         return Mixin.getDefaultInstance();
      }

      public Mixin build() {
         Mixin result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Mixin buildPartial() {
         Mixin result = new Mixin(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(Mixin result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.name_ = this.name_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.root_ = this.root_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Mixin) {
            return this.mergeFrom((Mixin)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Mixin other) {
         if (other == Mixin.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (!other.getRoot().isEmpty()) {
               this.root_ = other.root_;
               this.bitField0_ |= 2;
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
                        this.name_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        this.root_ = input.readStringRequireUtf8();
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

      public String getName() {
         Object ref = this.name_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.name_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getNameBytes() {
         Object ref = this.name_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setName(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.name_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearName() {
         this.name_ = Mixin.getDefaultInstance().getName();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setNameBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public String getRoot() {
         Object ref = this.root_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.root_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getRootBytes() {
         Object ref = this.root_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.root_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setRoot(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.root_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }
      }

      public Builder clearRoot() {
         this.root_ = Mixin.getDefaultInstance().getRoot();
         this.bitField0_ &= -3;
         this.onChanged();
         return this;
      }

      public Builder setRootBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.root_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }
      }
   }
}
