package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Enum extends GeneratedMessageV3 implements EnumOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int NAME_FIELD_NUMBER = 1;
   private volatile Object name_;
   public static final int ENUMVALUE_FIELD_NUMBER = 2;
   private List enumvalue_;
   public static final int OPTIONS_FIELD_NUMBER = 3;
   private List options_;
   public static final int SOURCE_CONTEXT_FIELD_NUMBER = 4;
   private SourceContext sourceContext_;
   public static final int SYNTAX_FIELD_NUMBER = 5;
   private int syntax_;
   public static final int EDITION_FIELD_NUMBER = 6;
   private volatile Object edition_;
   private byte memoizedIsInitialized;
   private static final Enum DEFAULT_INSTANCE = new Enum();
   private static final Parser PARSER = new AbstractParser() {
      public Enum parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = Enum.newBuilder();

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

   private Enum(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.name_ = "";
      this.syntax_ = 0;
      this.edition_ = "";
      this.memoizedIsInitialized = -1;
   }

   private Enum() {
      this.name_ = "";
      this.syntax_ = 0;
      this.edition_ = "";
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.enumvalue_ = Collections.emptyList();
      this.options_ = Collections.emptyList();
      this.syntax_ = 0;
      this.edition_ = "";
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new Enum();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Enum_descriptor;
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized(Enum.class, Builder.class);
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

   public List getEnumvalueList() {
      return this.enumvalue_;
   }

   public List getEnumvalueOrBuilderList() {
      return this.enumvalue_;
   }

   public int getEnumvalueCount() {
      return this.enumvalue_.size();
   }

   public EnumValue getEnumvalue(int index) {
      return (EnumValue)this.enumvalue_.get(index);
   }

   public EnumValueOrBuilder getEnumvalueOrBuilder(int index) {
      return (EnumValueOrBuilder)this.enumvalue_.get(index);
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
      if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
         GeneratedMessageV3.writeString(output, 1, this.name_);
      }

      for(int i = 0; i < this.enumvalue_.size(); ++i) {
         output.writeMessage(2, (MessageLite)this.enumvalue_.get(i));
      }

      for(int i = 0; i < this.options_.size(); ++i) {
         output.writeMessage(3, (MessageLite)this.options_.get(i));
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(4, this.getSourceContext());
      }

      if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
         output.writeEnum(5, this.syntax_);
      }

      if (!GeneratedMessageV3.isStringEmpty(this.edition_)) {
         GeneratedMessageV3.writeString(output, 6, this.edition_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
            size += GeneratedMessageV3.computeStringSize(1, this.name_);
         }

         for(int i = 0; i < this.enumvalue_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.enumvalue_.get(i));
         }

         for(int i = 0; i < this.options_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(3, (MessageLite)this.options_.get(i));
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(4, this.getSourceContext());
         }

         if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            size += CodedOutputStream.computeEnumSize(5, this.syntax_);
         }

         if (!GeneratedMessageV3.isStringEmpty(this.edition_)) {
            size += GeneratedMessageV3.computeStringSize(6, this.edition_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Enum)) {
         return super.equals(obj);
      } else {
         Enum other = (Enum)obj;
         if (!this.getName().equals(other.getName())) {
            return false;
         } else if (!this.getEnumvalueList().equals(other.getEnumvalueList())) {
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
         if (this.getEnumvalueCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getEnumvalueList().hashCode();
         }

         if (this.getOptionsCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getOptionsList().hashCode();
         }

         if (this.hasSourceContext()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getSourceContext().hashCode();
         }

         hash = 37 * hash + 5;
         hash = 53 * hash + this.syntax_;
         hash = 37 * hash + 6;
         hash = 53 * hash + this.getEdition().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Enum parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data);
   }

   public static Enum parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Enum parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data);
   }

   public static Enum parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Enum parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data);
   }

   public static Enum parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Enum)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Enum parseFrom(InputStream input) throws IOException {
      return (Enum)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Enum parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Enum)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Enum parseDelimitedFrom(InputStream input) throws IOException {
      return (Enum)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static Enum parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Enum)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Enum parseFrom(CodedInputStream input) throws IOException {
      return (Enum)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Enum parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Enum)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Enum prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Enum getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Enum getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements EnumOrBuilder {
      private int bitField0_;
      private Object name_;
      private List enumvalue_;
      private RepeatedFieldBuilderV3 enumvalueBuilder_;
      private List options_;
      private RepeatedFieldBuilderV3 optionsBuilder_;
      private SourceContext sourceContext_;
      private SingleFieldBuilderV3 sourceContextBuilder_;
      private int syntax_;
      private Object edition_;

      public static final Descriptors.Descriptor getDescriptor() {
         return TypeProto.internal_static_google_protobuf_Enum_descriptor;
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return TypeProto.internal_static_google_protobuf_Enum_fieldAccessorTable.ensureFieldAccessorsInitialized(Enum.class, Builder.class);
      }

      private Builder() {
         this.name_ = "";
         this.enumvalue_ = Collections.emptyList();
         this.options_ = Collections.emptyList();
         this.syntax_ = 0;
         this.edition_ = "";
         this.maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
         this.name_ = "";
         this.enumvalue_ = Collections.emptyList();
         this.options_ = Collections.emptyList();
         this.syntax_ = 0;
         this.edition_ = "";
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            this.getEnumvalueFieldBuilder();
            this.getOptionsFieldBuilder();
            this.getSourceContextFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.name_ = "";
         if (this.enumvalueBuilder_ == null) {
            this.enumvalue_ = Collections.emptyList();
         } else {
            this.enumvalue_ = null;
            this.enumvalueBuilder_.clear();
         }

         this.bitField0_ &= -3;
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
         } else {
            this.options_ = null;
            this.optionsBuilder_.clear();
         }

         this.bitField0_ &= -5;
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
         return TypeProto.internal_static_google_protobuf_Enum_descriptor;
      }

      public Enum getDefaultInstanceForType() {
         return Enum.getDefaultInstance();
      }

      public Enum build() {
         Enum result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Enum buildPartial() {
         Enum result = new Enum(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(Enum result) {
         if (this.enumvalueBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0) {
               this.enumvalue_ = Collections.unmodifiableList(this.enumvalue_);
               this.bitField0_ &= -3;
            }

            result.enumvalue_ = this.enumvalue_;
         } else {
            result.enumvalue_ = this.enumvalueBuilder_.build();
         }

         if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 0) {
               this.options_ = Collections.unmodifiableList(this.options_);
               this.bitField0_ &= -5;
            }

            result.options_ = this.options_;
         } else {
            result.options_ = this.optionsBuilder_.build();
         }

      }

      private void buildPartial0(Enum result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.name_ = this.name_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 8) != 0) {
            result.sourceContext_ = this.sourceContextBuilder_ == null ? this.sourceContext_ : (SourceContext)this.sourceContextBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 16) != 0) {
            result.syntax_ = this.syntax_;
         }

         if ((from_bitField0_ & 32) != 0) {
            result.edition_ = this.edition_;
         }

         result.bitField0_ = to_bitField0_;
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
         if (other instanceof Enum) {
            return this.mergeFrom((Enum)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Enum other) {
         if (other == Enum.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (this.enumvalueBuilder_ == null) {
               if (!other.enumvalue_.isEmpty()) {
                  if (this.enumvalue_.isEmpty()) {
                     this.enumvalue_ = other.enumvalue_;
                     this.bitField0_ &= -3;
                  } else {
                     this.ensureEnumvalueIsMutable();
                     this.enumvalue_.addAll(other.enumvalue_);
                  }

                  this.onChanged();
               }
            } else if (!other.enumvalue_.isEmpty()) {
               if (this.enumvalueBuilder_.isEmpty()) {
                  this.enumvalueBuilder_.dispose();
                  this.enumvalueBuilder_ = null;
                  this.enumvalue_ = other.enumvalue_;
                  this.bitField0_ &= -3;
                  this.enumvalueBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? this.getEnumvalueFieldBuilder() : null;
               } else {
                  this.enumvalueBuilder_.addAllMessages(other.enumvalue_);
               }
            }

            if (this.optionsBuilder_ == null) {
               if (!other.options_.isEmpty()) {
                  if (this.options_.isEmpty()) {
                     this.options_ = other.options_;
                     this.bitField0_ &= -5;
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
                  this.bitField0_ &= -5;
                  this.optionsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? this.getOptionsFieldBuilder() : null;
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
               this.bitField0_ |= 32;
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
                        EnumValue m = (EnumValue)input.readMessage(EnumValue.parser(), extensionRegistry);
                        if (this.enumvalueBuilder_ == null) {
                           this.ensureEnumvalueIsMutable();
                           this.enumvalue_.add(m);
                        } else {
                           this.enumvalueBuilder_.addMessage(m);
                        }
                        break;
                     case 26:
                        Option m = (Option)input.readMessage(Option.parser(), extensionRegistry);
                        if (this.optionsBuilder_ == null) {
                           this.ensureOptionsIsMutable();
                           this.options_.add(m);
                        } else {
                           this.optionsBuilder_.addMessage(m);
                        }
                        break;
                     case 34:
                        input.readMessage((MessageLite.Builder)this.getSourceContextFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 8;
                        break;
                     case 40:
                        this.syntax_ = input.readEnum();
                        this.bitField0_ |= 16;
                        break;
                     case 50:
                        this.edition_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 32;
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
         this.name_ = Enum.getDefaultInstance().getName();
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

      private void ensureEnumvalueIsMutable() {
         if ((this.bitField0_ & 2) == 0) {
            this.enumvalue_ = new ArrayList(this.enumvalue_);
            this.bitField0_ |= 2;
         }

      }

      public List getEnumvalueList() {
         return this.enumvalueBuilder_ == null ? Collections.unmodifiableList(this.enumvalue_) : this.enumvalueBuilder_.getMessageList();
      }

      public int getEnumvalueCount() {
         return this.enumvalueBuilder_ == null ? this.enumvalue_.size() : this.enumvalueBuilder_.getCount();
      }

      public EnumValue getEnumvalue(int index) {
         return this.enumvalueBuilder_ == null ? (EnumValue)this.enumvalue_.get(index) : (EnumValue)this.enumvalueBuilder_.getMessage(index);
      }

      public Builder setEnumvalue(int index, EnumValue value) {
         if (this.enumvalueBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureEnumvalueIsMutable();
            this.enumvalue_.set(index, value);
            this.onChanged();
         } else {
            this.enumvalueBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setEnumvalue(int index, EnumValue.Builder builderForValue) {
         if (this.enumvalueBuilder_ == null) {
            this.ensureEnumvalueIsMutable();
            this.enumvalue_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.enumvalueBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addEnumvalue(EnumValue value) {
         if (this.enumvalueBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureEnumvalueIsMutable();
            this.enumvalue_.add(value);
            this.onChanged();
         } else {
            this.enumvalueBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addEnumvalue(int index, EnumValue value) {
         if (this.enumvalueBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureEnumvalueIsMutable();
            this.enumvalue_.add(index, value);
            this.onChanged();
         } else {
            this.enumvalueBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addEnumvalue(EnumValue.Builder builderForValue) {
         if (this.enumvalueBuilder_ == null) {
            this.ensureEnumvalueIsMutable();
            this.enumvalue_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.enumvalueBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addEnumvalue(int index, EnumValue.Builder builderForValue) {
         if (this.enumvalueBuilder_ == null) {
            this.ensureEnumvalueIsMutable();
            this.enumvalue_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.enumvalueBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllEnumvalue(Iterable values) {
         if (this.enumvalueBuilder_ == null) {
            this.ensureEnumvalueIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.enumvalue_);
            this.onChanged();
         } else {
            this.enumvalueBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearEnumvalue() {
         if (this.enumvalueBuilder_ == null) {
            this.enumvalue_ = Collections.emptyList();
            this.bitField0_ &= -3;
            this.onChanged();
         } else {
            this.enumvalueBuilder_.clear();
         }

         return this;
      }

      public Builder removeEnumvalue(int index) {
         if (this.enumvalueBuilder_ == null) {
            this.ensureEnumvalueIsMutable();
            this.enumvalue_.remove(index);
            this.onChanged();
         } else {
            this.enumvalueBuilder_.remove(index);
         }

         return this;
      }

      public EnumValue.Builder getEnumvalueBuilder(int index) {
         return (EnumValue.Builder)this.getEnumvalueFieldBuilder().getBuilder(index);
      }

      public EnumValueOrBuilder getEnumvalueOrBuilder(int index) {
         return this.enumvalueBuilder_ == null ? (EnumValueOrBuilder)this.enumvalue_.get(index) : (EnumValueOrBuilder)this.enumvalueBuilder_.getMessageOrBuilder(index);
      }

      public List getEnumvalueOrBuilderList() {
         return this.enumvalueBuilder_ != null ? this.enumvalueBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.enumvalue_);
      }

      public EnumValue.Builder addEnumvalueBuilder() {
         return (EnumValue.Builder)this.getEnumvalueFieldBuilder().addBuilder(EnumValue.getDefaultInstance());
      }

      public EnumValue.Builder addEnumvalueBuilder(int index) {
         return (EnumValue.Builder)this.getEnumvalueFieldBuilder().addBuilder(index, EnumValue.getDefaultInstance());
      }

      public List getEnumvalueBuilderList() {
         return this.getEnumvalueFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilderV3 getEnumvalueFieldBuilder() {
         if (this.enumvalueBuilder_ == null) {
            this.enumvalueBuilder_ = new RepeatedFieldBuilderV3(this.enumvalue_, (this.bitField0_ & 2) != 0, this.getParentForChildren(), this.isClean());
            this.enumvalue_ = null;
         }

         return this.enumvalueBuilder_;
      }

      private void ensureOptionsIsMutable() {
         if ((this.bitField0_ & 4) == 0) {
            this.options_ = new ArrayList(this.options_);
            this.bitField0_ |= 4;
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
            this.bitField0_ &= -5;
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

      private RepeatedFieldBuilderV3 getOptionsFieldBuilder() {
         if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new RepeatedFieldBuilderV3(this.options_, (this.bitField0_ & 4) != 0, this.getParentForChildren(), this.isClean());
            this.options_ = null;
         }

         return this.optionsBuilder_;
      }

      public boolean hasSourceContext() {
         return (this.bitField0_ & 8) != 0;
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

         this.bitField0_ |= 8;
         this.onChanged();
         return this;
      }

      public Builder setSourceContext(SourceContext.Builder builderForValue) {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContext_ = builderForValue.build();
         } else {
            this.sourceContextBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 8;
         this.onChanged();
         return this;
      }

      public Builder mergeSourceContext(SourceContext value) {
         if (this.sourceContextBuilder_ == null) {
            if ((this.bitField0_ & 8) != 0 && this.sourceContext_ != null && this.sourceContext_ != SourceContext.getDefaultInstance()) {
               this.getSourceContextBuilder().mergeFrom(value);
            } else {
               this.sourceContext_ = value;
            }
         } else {
            this.sourceContextBuilder_.mergeFrom(value);
         }

         if (this.sourceContext_ != null) {
            this.bitField0_ |= 8;
            this.onChanged();
         }

         return this;
      }

      public Builder clearSourceContext() {
         this.bitField0_ &= -9;
         this.sourceContext_ = null;
         if (this.sourceContextBuilder_ != null) {
            this.sourceContextBuilder_.dispose();
            this.sourceContextBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public SourceContext.Builder getSourceContextBuilder() {
         this.bitField0_ |= 8;
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

      private SingleFieldBuilderV3 getSourceContextFieldBuilder() {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContextBuilder_ = new SingleFieldBuilderV3(this.getSourceContext(), this.getParentForChildren(), this.isClean());
            this.sourceContext_ = null;
         }

         return this.sourceContextBuilder_;
      }

      public int getSyntaxValue() {
         return this.syntax_;
      }

      public Builder setSyntaxValue(int value) {
         this.syntax_ = value;
         this.bitField0_ |= 16;
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
            this.bitField0_ |= 16;
            this.syntax_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearSyntax() {
         this.bitField0_ &= -17;
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
            this.bitField0_ |= 32;
            this.onChanged();
            return this;
         }
      }

      public Builder clearEdition() {
         this.edition_ = Enum.getDefaultInstance().getEdition();
         this.bitField0_ &= -33;
         this.onChanged();
         return this;
      }

      public Builder setEditionBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.edition_ = value;
            this.bitField0_ |= 32;
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
