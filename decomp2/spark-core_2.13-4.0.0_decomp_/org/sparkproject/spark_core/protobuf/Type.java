package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Type extends GeneratedMessage implements TypeOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int NAME_FIELD_NUMBER = 1;
   private volatile Object name_;
   public static final int FIELDS_FIELD_NUMBER = 2;
   private List fields_;
   public static final int ONEOFS_FIELD_NUMBER = 3;
   private LazyStringArrayList oneofs_;
   public static final int OPTIONS_FIELD_NUMBER = 4;
   private List options_;
   public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
   private SourceContext sourceContext_;
   public static final int SYNTAX_FIELD_NUMBER = 6;
   private int syntax_;
   public static final int EDITION_FIELD_NUMBER = 7;
   private volatile Object edition_;
   private byte memoizedIsInitialized;
   private static final Type DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Type(GeneratedMessage.Builder builder) {
      super(builder);
      this.name_ = "";
      this.oneofs_ = LazyStringArrayList.emptyList();
      this.syntax_ = 0;
      this.edition_ = "";
      this.memoizedIsInitialized = -1;
   }

   private Type() {
      this.name_ = "";
      this.oneofs_ = LazyStringArrayList.emptyList();
      this.syntax_ = 0;
      this.edition_ = "";
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.fields_ = Collections.emptyList();
      this.oneofs_ = LazyStringArrayList.emptyList();
      this.options_ = Collections.emptyList();
      this.syntax_ = 0;
      this.edition_ = "";
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Type_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
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

   public List getFieldsList() {
      return this.fields_;
   }

   public List getFieldsOrBuilderList() {
      return this.fields_;
   }

   public int getFieldsCount() {
      return this.fields_.size();
   }

   public Field getFields(int index) {
      return (Field)this.fields_.get(index);
   }

   public FieldOrBuilder getFieldsOrBuilder(int index) {
      return (FieldOrBuilder)this.fields_.get(index);
   }

   public ProtocolStringList getOneofsList() {
      return this.oneofs_;
   }

   public int getOneofsCount() {
      return this.oneofs_.size();
   }

   public String getOneofs(int index) {
      return this.oneofs_.get(index);
   }

   public ByteString getOneofsBytes(int index) {
      return this.oneofs_.getByteString(index);
   }

   public List getOptionsList() {
      return this.options_;
   }

   public List getOptionsOrBuilderList() {
      return this.options_;
   }

   public int getOptionsCount() {
      return this.options_.size();
   }

   public Option getOptions(int index) {
      return (Option)this.options_.get(index);
   }

   public OptionOrBuilder getOptionsOrBuilder(int index) {
      return (OptionOrBuilder)this.options_.get(index);
   }

   public boolean hasSourceContext() {
      return (this.bitField0_ & 1) != 0;
   }

   public SourceContext getSourceContext() {
      return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
   }

   public SourceContextOrBuilder getSourceContextOrBuilder() {
      return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
   }

   public int getSyntaxValue() {
      return this.syntax_;
   }

   public Syntax getSyntax() {
      Syntax result = Syntax.forNumber(this.syntax_);
      return result == null ? Syntax.UNRECOGNIZED : result;
   }

   public String getEdition() {
      Object ref = this.edition_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.edition_ = s;
         return s;
      }
   }

   public ByteString getEditionBytes() {
      Object ref = this.edition_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.edition_ = b;
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

      for(int i = 0; i < this.fields_.size(); ++i) {
         output.writeMessage(2, (MessageLite)this.fields_.get(i));
      }

      for(int i = 0; i < this.oneofs_.size(); ++i) {
         GeneratedMessage.writeString(output, 3, this.oneofs_.getRaw(i));
      }

      for(int i = 0; i < this.options_.size(); ++i) {
         output.writeMessage(4, (MessageLite)this.options_.get(i));
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(5, this.getSourceContext());
      }

      if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
         output.writeEnum(6, this.syntax_);
      }

      if (!GeneratedMessage.isStringEmpty(this.edition_)) {
         GeneratedMessage.writeString(output, 7, this.edition_);
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

         for(int i = 0; i < this.fields_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.fields_.get(i));
         }

         int dataSize = 0;

         for(int i = 0; i < this.oneofs_.size(); ++i) {
            dataSize += computeStringSizeNoTag(this.oneofs_.getRaw(i));
         }

         size += dataSize;
         size += 1 * this.getOneofsList().size();

         for(int i = 0; i < this.options_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(4, (MessageLite)this.options_.get(i));
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(5, this.getSourceContext());
         }

         if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            size += CodedOutputStream.computeEnumSize(6, this.syntax_);
         }

         if (!GeneratedMessage.isStringEmpty(this.edition_)) {
            size += GeneratedMessage.computeStringSize(7, this.edition_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Type)) {
         return super.equals(obj);
      } else {
         Type other = (Type)obj;
         if (!this.getName().equals(other.getName())) {
            return false;
         } else if (!this.getFieldsList().equals(other.getFieldsList())) {
            return false;
         } else if (!this.getOneofsList().equals(other.getOneofsList())) {
            return false;
         } else if (!this.getOptionsList().equals(other.getOptionsList())) {
            return false;
         } else if (this.hasSourceContext() != other.hasSourceContext()) {
            return false;
         } else if (this.hasSourceContext() && !this.getSourceContext().equals(other.getSourceContext())) {
            return false;
         } else if (this.syntax_ != other.syntax_) {
            return false;
         } else if (!this.getEdition().equals(other.getEdition())) {
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
         if (this.getFieldsCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getFieldsList().hashCode();
         }

         if (this.getOneofsCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getOneofsList().hashCode();
         }

         if (this.getOptionsCount() > 0) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getOptionsList().hashCode();
         }

         if (this.hasSourceContext()) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getSourceContext().hashCode();
         }

         hash = 37 * hash + 6;
         hash = 53 * hash + this.syntax_;
         hash = 37 * hash + 7;
         hash = 53 * hash + this.getEdition().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Type parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data);
   }

   public static Type parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Type parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data);
   }

   public static Type parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Type parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data);
   }

   public static Type parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Type)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Type parseFrom(InputStream input) throws IOException {
      return (Type)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Type parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Type)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Type parseDelimitedFrom(InputStream input) throws IOException {
      return (Type)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Type parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Type)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Type parseFrom(CodedInputStream input) throws IOException {
      return (Type)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Type parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Type)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Type prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Type getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Type getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Type.class.getName());
      DEFAULT_INSTANCE = new Type();
      PARSER = new AbstractParser() {
         public Type parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Type.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements TypeOrBuilder {
      private int bitField0_;
      private Object name_;
      private List fields_;
      private RepeatedFieldBuilder fieldsBuilder_;
      private LazyStringArrayList oneofs_;
      private List options_;
      private RepeatedFieldBuilder optionsBuilder_;
      private SourceContext sourceContext_;
      private SingleFieldBuilder sourceContextBuilder_;
      private int syntax_;
      private Object edition_;

      public static final Descriptors.Descriptor getDescriptor() {
         return TypeProto.internal_static_google_protobuf_Type_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
      }

      private Builder() {
         this.name_ = "";
         this.fields_ = Collections.emptyList();
         this.oneofs_ = LazyStringArrayList.emptyList();
         this.options_ = Collections.emptyList();
         this.syntax_ = 0;
         this.edition_ = "";
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.name_ = "";
         this.fields_ = Collections.emptyList();
         this.oneofs_ = LazyStringArrayList.emptyList();
         this.options_ = Collections.emptyList();
         this.syntax_ = 0;
         this.edition_ = "";
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (GeneratedMessage.alwaysUseFieldBuilders) {
            this.getFieldsFieldBuilder();
            this.getOptionsFieldBuilder();
            this.getSourceContextFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.name_ = "";
         if (this.fieldsBuilder_ == null) {
            this.fields_ = Collections.emptyList();
         } else {
            this.fields_ = null;
            this.fieldsBuilder_.clear();
         }

         this.bitField0_ &= -3;
         this.oneofs_ = LazyStringArrayList.emptyList();
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
         } else {
            this.options_ = null;
            this.optionsBuilder_.clear();
         }

         this.bitField0_ &= -9;
         this.sourceContext_ = null;
         if (this.sourceContextBuilder_ != null) {
            this.sourceContextBuilder_.dispose();
            this.sourceContextBuilder_ = null;
         }

         this.syntax_ = 0;
         this.edition_ = "";
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return TypeProto.internal_static_google_protobuf_Type_descriptor;
      }

      public Type getDefaultInstanceForType() {
         return Type.getDefaultInstance();
      }

      public Type build() {
         Type result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Type buildPartial() {
         Type result = new Type(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(Type result) {
         if (this.fieldsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0) {
               this.fields_ = Collections.unmodifiableList(this.fields_);
               this.bitField0_ &= -3;
            }

            result.fields_ = this.fields_;
         } else {
            result.fields_ = this.fieldsBuilder_.build();
         }

         if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 8) != 0) {
               this.options_ = Collections.unmodifiableList(this.options_);
               this.bitField0_ &= -9;
            }

            result.options_ = this.options_;
         } else {
            result.options_ = this.optionsBuilder_.build();
         }

      }

      private void buildPartial0(Type result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.name_ = this.name_;
         }

         if ((from_bitField0_ & 4) != 0) {
            this.oneofs_.makeImmutable();
            result.oneofs_ = this.oneofs_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 16) != 0) {
            result.sourceContext_ = this.sourceContextBuilder_ == null ? this.sourceContext_ : (SourceContext)this.sourceContextBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 32) != 0) {
            result.syntax_ = this.syntax_;
         }

         if ((from_bitField0_ & 64) != 0) {
            result.edition_ = this.edition_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Type) {
            return this.mergeFrom((Type)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Type other) {
         if (other == Type.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (this.fieldsBuilder_ == null) {
               if (!other.fields_.isEmpty()) {
                  if (this.fields_.isEmpty()) {
                     this.fields_ = other.fields_;
                     this.bitField0_ &= -3;
                  } else {
                     this.ensureFieldsIsMutable();
                     this.fields_.addAll(other.fields_);
                  }

                  this.onChanged();
               }
            } else if (!other.fields_.isEmpty()) {
               if (this.fieldsBuilder_.isEmpty()) {
                  this.fieldsBuilder_.dispose();
                  this.fieldsBuilder_ = null;
                  this.fields_ = other.fields_;
                  this.bitField0_ &= -3;
                  this.fieldsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getFieldsFieldBuilder() : null;
               } else {
                  this.fieldsBuilder_.addAllMessages(other.fields_);
               }
            }

            if (!other.oneofs_.isEmpty()) {
               if (this.oneofs_.isEmpty()) {
                  this.oneofs_ = other.oneofs_;
                  this.bitField0_ |= 4;
               } else {
                  this.ensureOneofsIsMutable();
                  this.oneofs_.addAll(other.oneofs_);
               }

               this.onChanged();
            }

            if (this.optionsBuilder_ == null) {
               if (!other.options_.isEmpty()) {
                  if (this.options_.isEmpty()) {
                     this.options_ = other.options_;
                     this.bitField0_ &= -9;
                  } else {
                     this.ensureOptionsIsMutable();
                     this.options_.addAll(other.options_);
                  }

                  this.onChanged();
               }
            } else if (!other.options_.isEmpty()) {
               if (this.optionsBuilder_.isEmpty()) {
                  this.optionsBuilder_.dispose();
                  this.optionsBuilder_ = null;
                  this.options_ = other.options_;
                  this.bitField0_ &= -9;
                  this.optionsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getOptionsFieldBuilder() : null;
               } else {
                  this.optionsBuilder_.addAllMessages(other.options_);
               }
            }

            if (other.hasSourceContext()) {
               this.mergeSourceContext(other.getSourceContext());
            }

            if (other.syntax_ != 0) {
               this.setSyntaxValue(other.getSyntaxValue());
            }

            if (!other.getEdition().isEmpty()) {
               this.edition_ = other.edition_;
               this.bitField0_ |= 64;
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
                        Field m = (Field)input.readMessage(Field.parser(), extensionRegistry);
                        if (this.fieldsBuilder_ == null) {
                           this.ensureFieldsIsMutable();
                           this.fields_.add(m);
                        } else {
                           this.fieldsBuilder_.addMessage(m);
                        }
                        break;
                     case 26:
                        String s = input.readStringRequireUtf8();
                        this.ensureOneofsIsMutable();
                        this.oneofs_.add(s);
                        break;
                     case 34:
                        Option m = (Option)input.readMessage(Option.parser(), extensionRegistry);
                        if (this.optionsBuilder_ == null) {
                           this.ensureOptionsIsMutable();
                           this.options_.add(m);
                        } else {
                           this.optionsBuilder_.addMessage(m);
                        }
                        break;
                     case 42:
                        input.readMessage((MessageLite.Builder)this.getSourceContextFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 16;
                        break;
                     case 48:
                        this.syntax_ = input.readEnum();
                        this.bitField0_ |= 32;
                        break;
                     case 58:
                        this.edition_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 64;
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
         this.name_ = Type.getDefaultInstance().getName();
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

      private void ensureFieldsIsMutable() {
         if ((this.bitField0_ & 2) == 0) {
            this.fields_ = new ArrayList(this.fields_);
            this.bitField0_ |= 2;
         }

      }

      public List getFieldsList() {
         return this.fieldsBuilder_ == null ? Collections.unmodifiableList(this.fields_) : this.fieldsBuilder_.getMessageList();
      }

      public int getFieldsCount() {
         return this.fieldsBuilder_ == null ? this.fields_.size() : this.fieldsBuilder_.getCount();
      }

      public Field getFields(int index) {
         return this.fieldsBuilder_ == null ? (Field)this.fields_.get(index) : (Field)this.fieldsBuilder_.getMessage(index);
      }

      public Builder setFields(int index, Field value) {
         if (this.fieldsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureFieldsIsMutable();
            this.fields_.set(index, value);
            this.onChanged();
         } else {
            this.fieldsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setFields(int index, Field.Builder builderForValue) {
         if (this.fieldsBuilder_ == null) {
            this.ensureFieldsIsMutable();
            this.fields_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.fieldsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addFields(Field value) {
         if (this.fieldsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureFieldsIsMutable();
            this.fields_.add(value);
            this.onChanged();
         } else {
            this.fieldsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addFields(int index, Field value) {
         if (this.fieldsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureFieldsIsMutable();
            this.fields_.add(index, value);
            this.onChanged();
         } else {
            this.fieldsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addFields(Field.Builder builderForValue) {
         if (this.fieldsBuilder_ == null) {
            this.ensureFieldsIsMutable();
            this.fields_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.fieldsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addFields(int index, Field.Builder builderForValue) {
         if (this.fieldsBuilder_ == null) {
            this.ensureFieldsIsMutable();
            this.fields_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.fieldsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllFields(Iterable values) {
         if (this.fieldsBuilder_ == null) {
            this.ensureFieldsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.fields_);
            this.onChanged();
         } else {
            this.fieldsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearFields() {
         if (this.fieldsBuilder_ == null) {
            this.fields_ = Collections.emptyList();
            this.bitField0_ &= -3;
            this.onChanged();
         } else {
            this.fieldsBuilder_.clear();
         }

         return this;
      }

      public Builder removeFields(int index) {
         if (this.fieldsBuilder_ == null) {
            this.ensureFieldsIsMutable();
            this.fields_.remove(index);
            this.onChanged();
         } else {
            this.fieldsBuilder_.remove(index);
         }

         return this;
      }

      public Field.Builder getFieldsBuilder(int index) {
         return (Field.Builder)this.getFieldsFieldBuilder().getBuilder(index);
      }

      public FieldOrBuilder getFieldsOrBuilder(int index) {
         return this.fieldsBuilder_ == null ? (FieldOrBuilder)this.fields_.get(index) : (FieldOrBuilder)this.fieldsBuilder_.getMessageOrBuilder(index);
      }

      public List getFieldsOrBuilderList() {
         return this.fieldsBuilder_ != null ? this.fieldsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.fields_);
      }

      public Field.Builder addFieldsBuilder() {
         return (Field.Builder)this.getFieldsFieldBuilder().addBuilder(Field.getDefaultInstance());
      }

      public Field.Builder addFieldsBuilder(int index) {
         return (Field.Builder)this.getFieldsFieldBuilder().addBuilder(index, Field.getDefaultInstance());
      }

      public List getFieldsBuilderList() {
         return this.getFieldsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getFieldsFieldBuilder() {
         if (this.fieldsBuilder_ == null) {
            this.fieldsBuilder_ = new RepeatedFieldBuilder(this.fields_, (this.bitField0_ & 2) != 0, this.getParentForChildren(), this.isClean());
            this.fields_ = null;
         }

         return this.fieldsBuilder_;
      }

      private void ensureOneofsIsMutable() {
         if (!this.oneofs_.isModifiable()) {
            this.oneofs_ = new LazyStringArrayList(this.oneofs_);
         }

         this.bitField0_ |= 4;
      }

      public ProtocolStringList getOneofsList() {
         this.oneofs_.makeImmutable();
         return this.oneofs_;
      }

      public int getOneofsCount() {
         return this.oneofs_.size();
      }

      public String getOneofs(int index) {
         return this.oneofs_.get(index);
      }

      public ByteString getOneofsBytes(int index) {
         return this.oneofs_.getByteString(index);
      }

      public Builder setOneofs(int index, String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.ensureOneofsIsMutable();
            this.oneofs_.set(index, value);
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder addOneofs(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.ensureOneofsIsMutable();
            this.oneofs_.add(value);
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder addAllOneofs(Iterable values) {
         this.ensureOneofsIsMutable();
         AbstractMessageLite.Builder.addAll(values, (List)this.oneofs_);
         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder clearOneofs() {
         this.oneofs_ = LazyStringArrayList.emptyList();
         this.bitField0_ &= -5;
         this.onChanged();
         return this;
      }

      public Builder addOneofsBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.ensureOneofsIsMutable();
            this.oneofs_.add(value);
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      private void ensureOptionsIsMutable() {
         if ((this.bitField0_ & 8) == 0) {
            this.options_ = new ArrayList(this.options_);
            this.bitField0_ |= 8;
         }

      }

      public List getOptionsList() {
         return this.optionsBuilder_ == null ? Collections.unmodifiableList(this.options_) : this.optionsBuilder_.getMessageList();
      }

      public int getOptionsCount() {
         return this.optionsBuilder_ == null ? this.options_.size() : this.optionsBuilder_.getCount();
      }

      public Option getOptions(int index) {
         return this.optionsBuilder_ == null ? (Option)this.options_.get(index) : (Option)this.optionsBuilder_.getMessage(index);
      }

      public Builder setOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.set(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addOptions(Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllOptions(Iterable values) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.options_);
            this.onChanged();
         } else {
            this.optionsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearOptions() {
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
            this.bitField0_ &= -9;
            this.onChanged();
         } else {
            this.optionsBuilder_.clear();
         }

         return this;
      }

      public Builder removeOptions(int index) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.remove(index);
            this.onChanged();
         } else {
            this.optionsBuilder_.remove(index);
         }

         return this;
      }

      public Option.Builder getOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().getBuilder(index);
      }

      public OptionOrBuilder getOptionsOrBuilder(int index) {
         return this.optionsBuilder_ == null ? (OptionOrBuilder)this.options_.get(index) : (OptionOrBuilder)this.optionsBuilder_.getMessageOrBuilder(index);
      }

      public List getOptionsOrBuilderList() {
         return this.optionsBuilder_ != null ? this.optionsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.options_);
      }

      public Option.Builder addOptionsBuilder() {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
      }

      public Option.Builder addOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(index, Option.getDefaultInstance());
      }

      public List getOptionsBuilderList() {
         return this.getOptionsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getOptionsFieldBuilder() {
         if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new RepeatedFieldBuilder(this.options_, (this.bitField0_ & 8) != 0, this.getParentForChildren(), this.isClean());
            this.options_ = null;
         }

         return this.optionsBuilder_;
      }

      public boolean hasSourceContext() {
         return (this.bitField0_ & 16) != 0;
      }

      public SourceContext getSourceContext() {
         if (this.sourceContextBuilder_ == null) {
            return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
         } else {
            return (SourceContext)this.sourceContextBuilder_.getMessage();
         }
      }

      public Builder setSourceContext(SourceContext value) {
         if (this.sourceContextBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.sourceContext_ = value;
         } else {
            this.sourceContextBuilder_.setMessage(value);
         }

         this.bitField0_ |= 16;
         this.onChanged();
         return this;
      }

      public Builder setSourceContext(SourceContext.Builder builderForValue) {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContext_ = builderForValue.build();
         } else {
            this.sourceContextBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 16;
         this.onChanged();
         return this;
      }

      public Builder mergeSourceContext(SourceContext value) {
         if (this.sourceContextBuilder_ == null) {
            if ((this.bitField0_ & 16) != 0 && this.sourceContext_ != null && this.sourceContext_ != SourceContext.getDefaultInstance()) {
               this.getSourceContextBuilder().mergeFrom(value);
            } else {
               this.sourceContext_ = value;
            }
         } else {
            this.sourceContextBuilder_.mergeFrom(value);
         }

         if (this.sourceContext_ != null) {
            this.bitField0_ |= 16;
            this.onChanged();
         }

         return this;
      }

      public Builder clearSourceContext() {
         this.bitField0_ &= -17;
         this.sourceContext_ = null;
         if (this.sourceContextBuilder_ != null) {
            this.sourceContextBuilder_.dispose();
            this.sourceContextBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public SourceContext.Builder getSourceContextBuilder() {
         this.bitField0_ |= 16;
         this.onChanged();
         return (SourceContext.Builder)this.getSourceContextFieldBuilder().getBuilder();
      }

      public SourceContextOrBuilder getSourceContextOrBuilder() {
         if (this.sourceContextBuilder_ != null) {
            return (SourceContextOrBuilder)this.sourceContextBuilder_.getMessageOrBuilder();
         } else {
            return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
         }
      }

      private SingleFieldBuilder getSourceContextFieldBuilder() {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContextBuilder_ = new SingleFieldBuilder(this.getSourceContext(), this.getParentForChildren(), this.isClean());
            this.sourceContext_ = null;
         }

         return this.sourceContextBuilder_;
      }

      public int getSyntaxValue() {
         return this.syntax_;
      }

      public Builder setSyntaxValue(int value) {
         this.syntax_ = value;
         this.bitField0_ |= 32;
         this.onChanged();
         return this;
      }

      public Syntax getSyntax() {
         Syntax result = Syntax.forNumber(this.syntax_);
         return result == null ? Syntax.UNRECOGNIZED : result;
      }

      public Builder setSyntax(Syntax value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 32;
            this.syntax_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearSyntax() {
         this.bitField0_ &= -33;
         this.syntax_ = 0;
         this.onChanged();
         return this;
      }

      public String getEdition() {
         Object ref = this.edition_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.edition_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getEditionBytes() {
         Object ref = this.edition_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.edition_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setEdition(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.edition_ = value;
            this.bitField0_ |= 64;
            this.onChanged();
            return this;
         }
      }

      public Builder clearEdition() {
         this.edition_ = Type.getDefaultInstance().getEdition();
         this.bitField0_ &= -65;
         this.onChanged();
         return this;
      }

      public Builder setEditionBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.edition_ = value;
            this.bitField0_ |= 64;
            this.onChanged();
            return this;
         }
      }
   }
}
