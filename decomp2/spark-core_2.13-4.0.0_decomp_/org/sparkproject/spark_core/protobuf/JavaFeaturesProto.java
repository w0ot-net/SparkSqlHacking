package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class JavaFeaturesProto {
   public static final int JAVA_FIELD_NUMBER = 1001;
   public static final GeneratedMessage.GeneratedExtension java_;
   private static final Descriptors.Descriptor internal_static_pb_JavaFeatures_descriptor;
   private static final GeneratedMessage.FieldAccessorTable internal_static_pb_JavaFeatures_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private JavaFeaturesProto() {
   }

   public static void registerAllExtensions(ExtensionRegistryLite registry) {
      registry.add((ExtensionLite)java_);
   }

   public static void registerAllExtensions(ExtensionRegistry registry) {
      registerAllExtensions((ExtensionRegistryLite)registry);
   }

   public static Descriptors.FileDescriptor getDescriptor() {
      return descriptor;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", JavaFeaturesProto.class.getName());
      java_ = GeneratedMessage.newFileScopedGeneratedExtension(JavaFeatures.class, JavaFeaturesProto.JavaFeatures.getDefaultInstance());
      String[] descriptorData = new String[]{"\n#google/protobuf/java_features.proto\u0012\u0002pb\u001a google/protobuf/descriptor.proto\"\u009b\u0005\n\fJavaFeatures\u0012\u0090\u0002\n\u0012legacy_closed_enum\u0018\u0001 \u0001(\bBá\u0001\u0088\u0001\u0001\u0098\u0001\u0004\u0098\u0001\u0001¢\u0001\t\u0012\u0004true\u0018\u0084\u0007¢\u0001\n\u0012\u0005false\u0018ç\u0007²\u0001»\u0001\bè\u0007\u0010è\u0007\u001a²\u0001The legacy closed enum behavior in Java is deprecated and is scheduled to be removed in edition 2025.  See http://protobuf.dev/programming-guides/enum/#java for more information.R\u0010legacyClosedEnum\u0012¯\u0002\n\u000futf8_validation\u0018\u0002 \u0001(\u000e2\u001f.pb.JavaFeatures.Utf8ValidationBä\u0001\u0088\u0001\u0001\u0098\u0001\u0004\u0098\u0001\u0001¢\u0001\f\u0012\u0007DEFAULT\u0018\u0084\u0007²\u0001È\u0001\bè\u0007\u0010é\u0007\u001a¿\u0001The Java-specific utf8 validation feature is deprecated and is scheduled to be removed in edition 2025.  Utf8 validation behavior should use the global cross-language utf8_validation feature.R\u000eutf8Validation\"F\n\u000eUtf8Validation\u0012\u001b\n\u0017UTF8_VALIDATION_UNKNOWN\u0010\u0000\u0012\u000b\n\u0007DEFAULT\u0010\u0001\u0012\n\n\u0006VERIFY\u0010\u0002:B\n\u0004java\u0012\u001b.google.protobuf.FeatureSet\u0018é\u0007 \u0001(\u000b2\u0010.pb.JavaFeaturesR\u0004javaB(\n\u0013com.google.protobufB\u0011JavaFeaturesProto"};
      descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
      internal_static_pb_JavaFeatures_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_pb_JavaFeatures_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_pb_JavaFeatures_descriptor, new String[]{"LegacyClosedEnum", "Utf8Validation"});
      java_.internalInit((Descriptors.FieldDescriptor)descriptor.getExtensions().get(0));
      descriptor.resolveAllFeaturesImmutable();
      DescriptorProtos.getDescriptor();
   }

   public static final class JavaFeatures extends GeneratedMessage implements JavaFeaturesOrBuilder {
      private static final long serialVersionUID = 0L;
      private int bitField0_;
      public static final int LEGACY_CLOSED_ENUM_FIELD_NUMBER = 1;
      private boolean legacyClosedEnum_;
      public static final int UTF8_VALIDATION_FIELD_NUMBER = 2;
      private int utf8Validation_;
      private byte memoizedIsInitialized;
      private static final JavaFeatures DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private JavaFeatures(GeneratedMessage.Builder builder) {
         super(builder);
         this.legacyClosedEnum_ = false;
         this.utf8Validation_ = 0;
         this.memoizedIsInitialized = -1;
      }

      private JavaFeatures() {
         this.legacyClosedEnum_ = false;
         this.utf8Validation_ = 0;
         this.memoizedIsInitialized = -1;
         this.utf8Validation_ = 0;
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return JavaFeaturesProto.internal_static_pb_JavaFeatures_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return JavaFeaturesProto.internal_static_pb_JavaFeatures_fieldAccessorTable.ensureFieldAccessorsInitialized(JavaFeatures.class, Builder.class);
      }

      public boolean hasLegacyClosedEnum() {
         return (this.bitField0_ & 1) != 0;
      }

      public boolean getLegacyClosedEnum() {
         return this.legacyClosedEnum_;
      }

      public boolean hasUtf8Validation() {
         return (this.bitField0_ & 2) != 0;
      }

      public Utf8Validation getUtf8Validation() {
         Utf8Validation result = JavaFeaturesProto.JavaFeatures.Utf8Validation.forNumber(this.utf8Validation_);
         return result == null ? JavaFeaturesProto.JavaFeatures.Utf8Validation.UTF8_VALIDATION_UNKNOWN : result;
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
         if ((this.bitField0_ & 1) != 0) {
            output.writeBool(1, this.legacyClosedEnum_);
         }

         if ((this.bitField0_ & 2) != 0) {
            output.writeEnum(2, this.utf8Validation_);
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;
            if ((this.bitField0_ & 1) != 0) {
               size += CodedOutputStream.computeBoolSize(1, this.legacyClosedEnum_);
            }

            if ((this.bitField0_ & 2) != 0) {
               size += CodedOutputStream.computeEnumSize(2, this.utf8Validation_);
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof JavaFeatures)) {
            return super.equals(obj);
         } else {
            JavaFeatures other = (JavaFeatures)obj;
            if (this.hasLegacyClosedEnum() != other.hasLegacyClosedEnum()) {
               return false;
            } else if (this.hasLegacyClosedEnum() && this.getLegacyClosedEnum() != other.getLegacyClosedEnum()) {
               return false;
            } else if (this.hasUtf8Validation() != other.hasUtf8Validation()) {
               return false;
            } else if (this.hasUtf8Validation() && this.utf8Validation_ != other.utf8Validation_) {
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
            if (this.hasLegacyClosedEnum()) {
               hash = 37 * hash + 1;
               hash = 53 * hash + Internal.hashBoolean(this.getLegacyClosedEnum());
            }

            if (this.hasUtf8Validation()) {
               hash = 37 * hash + 2;
               hash = 53 * hash + this.utf8Validation_;
            }

            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static JavaFeatures parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data);
      }

      public static JavaFeatures parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data, extensionRegistry);
      }

      public static JavaFeatures parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data);
      }

      public static JavaFeatures parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data, extensionRegistry);
      }

      public static JavaFeatures parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data);
      }

      public static JavaFeatures parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (JavaFeatures)PARSER.parseFrom(data, extensionRegistry);
      }

      public static JavaFeatures parseFrom(InputStream input) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static JavaFeatures parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static JavaFeatures parseDelimitedFrom(InputStream input) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static JavaFeatures parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static JavaFeatures parseFrom(CodedInputStream input) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static JavaFeatures parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (JavaFeatures)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(JavaFeatures prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static JavaFeatures getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public JavaFeatures getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", JavaFeatures.class.getName());
         DEFAULT_INSTANCE = new JavaFeatures();
         PARSER = new AbstractParser() {
            public JavaFeatures parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = JavaFeaturesProto.JavaFeatures.newBuilder();

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

      public static enum Utf8Validation implements ProtocolMessageEnum {
         UTF8_VALIDATION_UNKNOWN(0),
         DEFAULT(1),
         VERIFY(2);

         public static final int UTF8_VALIDATION_UNKNOWN_VALUE = 0;
         public static final int DEFAULT_VALUE = 1;
         public static final int VERIFY_VALUE = 2;
         private static final Internal.EnumLiteMap internalValueMap;
         private static final Utf8Validation[] VALUES;
         private final int value;

         public final int getNumber() {
            return this.value;
         }

         /** @deprecated */
         @Deprecated
         public static Utf8Validation valueOf(int value) {
            return forNumber(value);
         }

         public static Utf8Validation forNumber(int value) {
            switch (value) {
               case 0:
                  return UTF8_VALIDATION_UNKNOWN;
               case 1:
                  return DEFAULT;
               case 2:
                  return VERIFY;
               default:
                  return null;
            }
         }

         public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
         }

         public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.ordinal());
         }

         public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
         }

         public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor)JavaFeaturesProto.JavaFeatures.getDescriptor().getEnumTypes().get(0);
         }

         public static Utf8Validation valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
               throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else {
               return VALUES[desc.getIndex()];
            }
         }

         private Utf8Validation(int value) {
            this.value = value;
         }

         // $FF: synthetic method
         private static Utf8Validation[] $values() {
            return new Utf8Validation[]{UTF8_VALIDATION_UNKNOWN, DEFAULT, VERIFY};
         }

         static {
            RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Utf8Validation.class.getName());
            internalValueMap = new Internal.EnumLiteMap() {
               public Utf8Validation findValueByNumber(int number) {
                  return JavaFeaturesProto.JavaFeatures.Utf8Validation.forNumber(number);
               }
            };
            VALUES = values();
         }
      }

      public static final class Builder extends GeneratedMessage.Builder implements JavaFeaturesOrBuilder {
         private int bitField0_;
         private boolean legacyClosedEnum_;
         private int utf8Validation_;

         public static final Descriptors.Descriptor getDescriptor() {
            return JavaFeaturesProto.internal_static_pb_JavaFeatures_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return JavaFeaturesProto.internal_static_pb_JavaFeatures_fieldAccessorTable.ensureFieldAccessorsInitialized(JavaFeatures.class, Builder.class);
         }

         private Builder() {
            this.utf8Validation_ = 0;
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.utf8Validation_ = 0;
         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.legacyClosedEnum_ = false;
            this.utf8Validation_ = 0;
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return JavaFeaturesProto.internal_static_pb_JavaFeatures_descriptor;
         }

         public JavaFeatures getDefaultInstanceForType() {
            return JavaFeaturesProto.JavaFeatures.getDefaultInstance();
         }

         public JavaFeatures build() {
            JavaFeatures result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public JavaFeatures buildPartial() {
            JavaFeatures result = new JavaFeatures(this);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartial0(JavaFeatures result) {
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((from_bitField0_ & 1) != 0) {
               result.legacyClosedEnum_ = this.legacyClosedEnum_;
               to_bitField0_ |= 1;
            }

            if ((from_bitField0_ & 2) != 0) {
               result.utf8Validation_ = this.utf8Validation_;
               to_bitField0_ |= 2;
            }

            result.bitField0_ = to_bitField0_;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof JavaFeatures) {
               return this.mergeFrom((JavaFeatures)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(JavaFeatures other) {
            if (other == JavaFeaturesProto.JavaFeatures.getDefaultInstance()) {
               return this;
            } else {
               if (other.hasLegacyClosedEnum()) {
                  this.setLegacyClosedEnum(other.getLegacyClosedEnum());
               }

               if (other.hasUtf8Validation()) {
                  this.setUtf8Validation(other.getUtf8Validation());
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
                           this.legacyClosedEnum_ = input.readBool();
                           this.bitField0_ |= 1;
                           break;
                        case 16:
                           int tmpRaw = input.readEnum();
                           Utf8Validation tmpValue = JavaFeaturesProto.JavaFeatures.Utf8Validation.forNumber(tmpRaw);
                           if (tmpValue == null) {
                              this.mergeUnknownVarintField(2, tmpRaw);
                           } else {
                              this.utf8Validation_ = tmpRaw;
                              this.bitField0_ |= 2;
                           }
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

         public boolean hasLegacyClosedEnum() {
            return (this.bitField0_ & 1) != 0;
         }

         public boolean getLegacyClosedEnum() {
            return this.legacyClosedEnum_;
         }

         public Builder setLegacyClosedEnum(boolean value) {
            this.legacyClosedEnum_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }

         public Builder clearLegacyClosedEnum() {
            this.bitField0_ &= -2;
            this.legacyClosedEnum_ = false;
            this.onChanged();
            return this;
         }

         public boolean hasUtf8Validation() {
            return (this.bitField0_ & 2) != 0;
         }

         public Utf8Validation getUtf8Validation() {
            Utf8Validation result = JavaFeaturesProto.JavaFeatures.Utf8Validation.forNumber(this.utf8Validation_);
            return result == null ? JavaFeaturesProto.JavaFeatures.Utf8Validation.UTF8_VALIDATION_UNKNOWN : result;
         }

         public Builder setUtf8Validation(Utf8Validation value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.bitField0_ |= 2;
               this.utf8Validation_ = value.getNumber();
               this.onChanged();
               return this;
            }
         }

         public Builder clearUtf8Validation() {
            this.bitField0_ &= -3;
            this.utf8Validation_ = 0;
            this.onChanged();
            return this;
         }
      }
   }

   public interface JavaFeaturesOrBuilder extends MessageOrBuilder {
      boolean hasLegacyClosedEnum();

      boolean getLegacyClosedEnum();

      boolean hasUtf8Validation();

      JavaFeatures.Utf8Validation getUtf8Validation();
   }
}
