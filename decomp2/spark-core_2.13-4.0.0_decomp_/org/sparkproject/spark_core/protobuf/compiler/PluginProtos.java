package org.sparkproject.spark_core.protobuf.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.sparkproject.spark_core.protobuf.AbstractMessage;
import org.sparkproject.spark_core.protobuf.AbstractMessageLite;
import org.sparkproject.spark_core.protobuf.AbstractParser;
import org.sparkproject.spark_core.protobuf.ByteString;
import org.sparkproject.spark_core.protobuf.CodedInputStream;
import org.sparkproject.spark_core.protobuf.CodedOutputStream;
import org.sparkproject.spark_core.protobuf.DescriptorProtos;
import org.sparkproject.spark_core.protobuf.Descriptors;
import org.sparkproject.spark_core.protobuf.ExtensionRegistry;
import org.sparkproject.spark_core.protobuf.ExtensionRegistryLite;
import org.sparkproject.spark_core.protobuf.GeneratedMessage;
import org.sparkproject.spark_core.protobuf.Internal;
import org.sparkproject.spark_core.protobuf.InvalidProtocolBufferException;
import org.sparkproject.spark_core.protobuf.LazyStringArrayList;
import org.sparkproject.spark_core.protobuf.Message;
import org.sparkproject.spark_core.protobuf.MessageLite;
import org.sparkproject.spark_core.protobuf.MessageOrBuilder;
import org.sparkproject.spark_core.protobuf.Parser;
import org.sparkproject.spark_core.protobuf.ProtocolMessageEnum;
import org.sparkproject.spark_core.protobuf.ProtocolStringList;
import org.sparkproject.spark_core.protobuf.RepeatedFieldBuilder;
import org.sparkproject.spark_core.protobuf.RuntimeVersion;
import org.sparkproject.spark_core.protobuf.SingleFieldBuilder;
import org.sparkproject.spark_core.protobuf.UninitializedMessageException;

public final class PluginProtos {
   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_Version_descriptor;
   private static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_compiler_Version_fieldAccessorTable;
   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
   private static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable;
   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
   private static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable;
   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
   private static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private PluginProtos() {
   }

   public static void registerAllExtensions(ExtensionRegistryLite registry) {
   }

   public static void registerAllExtensions(ExtensionRegistry registry) {
      registerAllExtensions((ExtensionRegistryLite)registry);
   }

   public static Descriptors.FileDescriptor getDescriptor() {
      return descriptor;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", PluginProtos.class.getName());
      String[] descriptorData = new String[]{"\n%google/protobuf/compiler/plugin.proto\u0012\u0018google.protobuf.compiler\u001a google/protobuf/descriptor.proto\"c\n\u0007Version\u0012\u0014\n\u0005major\u0018\u0001 \u0001(\u0005R\u0005major\u0012\u0014\n\u0005minor\u0018\u0002 \u0001(\u0005R\u0005minor\u0012\u0014\n\u0005patch\u0018\u0003 \u0001(\u0005R\u0005patch\u0012\u0016\n\u0006suffix\u0018\u0004 \u0001(\tR\u0006suffix\"Ï\u0002\n\u0014CodeGeneratorRequest\u0012(\n\u0010file_to_generate\u0018\u0001 \u0003(\tR\u000efileToGenerate\u0012\u001c\n\tparameter\u0018\u0002 \u0001(\tR\tparameter\u0012C\n\nproto_file\u0018\u000f \u0003(\u000b2$.google.protobuf.FileDescriptorProtoR\tprotoFile\u0012\\\n\u0017source_file_descriptors\u0018\u0011 \u0003(\u000b2$.google.protobuf.FileDescriptorProtoR\u0015sourceFileDescriptors\u0012L\n\u0010compiler_version\u0018\u0003 \u0001(\u000b2!.google.protobuf.compiler.VersionR\u000fcompilerVersion\"\u0085\u0004\n\u0015CodeGeneratorResponse\u0012\u0014\n\u0005error\u0018\u0001 \u0001(\tR\u0005error\u0012-\n\u0012supported_features\u0018\u0002 \u0001(\u0004R\u0011supportedFeatures\u0012'\n\u000fminimum_edition\u0018\u0003 \u0001(\u0005R\u000eminimumEdition\u0012'\n\u000fmaximum_edition\u0018\u0004 \u0001(\u0005R\u000emaximumEdition\u0012H\n\u0004file\u0018\u000f \u0003(\u000b24.google.protobuf.compiler.CodeGeneratorResponse.FileR\u0004file\u001a±\u0001\n\u0004File\u0012\u0012\n\u0004name\u0018\u0001 \u0001(\tR\u0004name\u0012'\n\u000finsertion_point\u0018\u0002 \u0001(\tR\u000einsertionPoint\u0012\u0018\n\u0007content\u0018\u000f \u0001(\tR\u0007content\u0012R\n\u0013generated_code_info\u0018\u0010 \u0001(\u000b2\".google.protobuf.GeneratedCodeInfoR\u0011generatedCodeInfo\"W\n\u0007Feature\u0012\u0010\n\fFEATURE_NONE\u0010\u0000\u0012\u001b\n\u0017FEATURE_PROTO3_OPTIONAL\u0010\u0001\u0012\u001d\n\u0019FEATURE_SUPPORTS_EDITIONS\u0010\u0002Br\n\u001ccom.google.protobuf.compilerB\fPluginProtosZ)google.golang.org/protobuf/types/pluginpbª\u0002\u0018Google.Protobuf.Compiler"};
      descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
      internal_static_google_protobuf_compiler_Version_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_protobuf_compiler_Version_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_compiler_Version_descriptor, new String[]{"Major", "Minor", "Patch", "Suffix"});
      internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor, new String[]{"FileToGenerate", "Parameter", "ProtoFile", "SourceFileDescriptors", "CompilerVersion"});
      internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor, new String[]{"Error", "SupportedFeatures", "MinimumEdition", "MaximumEdition", "File"});
      internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor = (Descriptors.Descriptor)internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor.getNestedTypes().get(0);
      internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor, new String[]{"Name", "InsertionPoint", "Content", "GeneratedCodeInfo"});
      descriptor.resolveAllFeaturesImmutable();
      DescriptorProtos.getDescriptor();
   }

   public static final class Version extends GeneratedMessage implements VersionOrBuilder {
      private static final long serialVersionUID = 0L;
      private int bitField0_;
      public static final int MAJOR_FIELD_NUMBER = 1;
      private int major_;
      public static final int MINOR_FIELD_NUMBER = 2;
      private int minor_;
      public static final int PATCH_FIELD_NUMBER = 3;
      private int patch_;
      public static final int SUFFIX_FIELD_NUMBER = 4;
      private volatile Object suffix_;
      private byte memoizedIsInitialized;
      private static final Version DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private Version(GeneratedMessage.Builder builder) {
         super(builder);
         this.major_ = 0;
         this.minor_ = 0;
         this.patch_ = 0;
         this.suffix_ = "";
         this.memoizedIsInitialized = -1;
      }

      private Version() {
         this.major_ = 0;
         this.minor_ = 0;
         this.patch_ = 0;
         this.suffix_ = "";
         this.memoizedIsInitialized = -1;
         this.suffix_ = "";
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
      }

      public boolean hasMajor() {
         return (this.bitField0_ & 1) != 0;
      }

      public int getMajor() {
         return this.major_;
      }

      public boolean hasMinor() {
         return (this.bitField0_ & 2) != 0;
      }

      public int getMinor() {
         return this.minor_;
      }

      public boolean hasPatch() {
         return (this.bitField0_ & 4) != 0;
      }

      public int getPatch() {
         return this.patch_;
      }

      public boolean hasSuffix() {
         return (this.bitField0_ & 8) != 0;
      }

      public String getSuffix() {
         Object ref = this.suffix_;
         if (ref instanceof String) {
            return (String)ref;
         } else {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
               this.suffix_ = s;
            }

            return s;
         }
      }

      public ByteString getSuffixBytes() {
         Object ref = this.suffix_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.suffix_ = b;
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
         if ((this.bitField0_ & 1) != 0) {
            output.writeInt32(1, this.major_);
         }

         if ((this.bitField0_ & 2) != 0) {
            output.writeInt32(2, this.minor_);
         }

         if ((this.bitField0_ & 4) != 0) {
            output.writeInt32(3, this.patch_);
         }

         if ((this.bitField0_ & 8) != 0) {
            GeneratedMessage.writeString(output, 4, this.suffix_);
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
               size += CodedOutputStream.computeInt32Size(1, this.major_);
            }

            if ((this.bitField0_ & 2) != 0) {
               size += CodedOutputStream.computeInt32Size(2, this.minor_);
            }

            if ((this.bitField0_ & 4) != 0) {
               size += CodedOutputStream.computeInt32Size(3, this.patch_);
            }

            if ((this.bitField0_ & 8) != 0) {
               size += GeneratedMessage.computeStringSize(4, this.suffix_);
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof Version)) {
            return super.equals(obj);
         } else {
            Version other = (Version)obj;
            if (this.hasMajor() != other.hasMajor()) {
               return false;
            } else if (this.hasMajor() && this.getMajor() != other.getMajor()) {
               return false;
            } else if (this.hasMinor() != other.hasMinor()) {
               return false;
            } else if (this.hasMinor() && this.getMinor() != other.getMinor()) {
               return false;
            } else if (this.hasPatch() != other.hasPatch()) {
               return false;
            } else if (this.hasPatch() && this.getPatch() != other.getPatch()) {
               return false;
            } else if (this.hasSuffix() != other.hasSuffix()) {
               return false;
            } else if (this.hasSuffix() && !this.getSuffix().equals(other.getSuffix())) {
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
            if (this.hasMajor()) {
               hash = 37 * hash + 1;
               hash = 53 * hash + this.getMajor();
            }

            if (this.hasMinor()) {
               hash = 37 * hash + 2;
               hash = 53 * hash + this.getMinor();
            }

            if (this.hasPatch()) {
               hash = 37 * hash + 3;
               hash = 53 * hash + this.getPatch();
            }

            if (this.hasSuffix()) {
               hash = 37 * hash + 4;
               hash = 53 * hash + this.getSuffix().hashCode();
            }

            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static Version parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data);
      }

      public static Version parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data);
      }

      public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data);
      }

      public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Version)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Version parseFrom(InputStream input) throws IOException {
         return (Version)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Version)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static Version parseDelimitedFrom(InputStream input) throws IOException {
         return (Version)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Version)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static Version parseFrom(CodedInputStream input) throws IOException {
         return (Version)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Version)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(Version prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static Version getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public Version getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Version.class.getName());
         DEFAULT_INSTANCE = new Version();
         PARSER = new AbstractParser() {
            public Version parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = PluginProtos.Version.newBuilder();

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

      public static final class Builder extends GeneratedMessage.Builder implements VersionOrBuilder {
         private int bitField0_;
         private int major_;
         private int minor_;
         private int patch_;
         private Object suffix_;

         public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
         }

         private Builder() {
            this.suffix_ = "";
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.suffix_ = "";
         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.major_ = 0;
            this.minor_ = 0;
            this.patch_ = 0;
            this.suffix_ = "";
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
         }

         public Version getDefaultInstanceForType() {
            return PluginProtos.Version.getDefaultInstance();
         }

         public Version build() {
            Version result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public Version buildPartial() {
            Version result = new Version(this);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartial0(Version result) {
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((from_bitField0_ & 1) != 0) {
               result.major_ = this.major_;
               to_bitField0_ |= 1;
            }

            if ((from_bitField0_ & 2) != 0) {
               result.minor_ = this.minor_;
               to_bitField0_ |= 2;
            }

            if ((from_bitField0_ & 4) != 0) {
               result.patch_ = this.patch_;
               to_bitField0_ |= 4;
            }

            if ((from_bitField0_ & 8) != 0) {
               result.suffix_ = this.suffix_;
               to_bitField0_ |= 8;
            }

            result.bitField0_ = to_bitField0_;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof Version) {
               return this.mergeFrom((Version)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(Version other) {
            if (other == PluginProtos.Version.getDefaultInstance()) {
               return this;
            } else {
               if (other.hasMajor()) {
                  this.setMajor(other.getMajor());
               }

               if (other.hasMinor()) {
                  this.setMinor(other.getMinor());
               }

               if (other.hasPatch()) {
                  this.setPatch(other.getPatch());
               }

               if (other.hasSuffix()) {
                  this.suffix_ = other.suffix_;
                  this.bitField0_ |= 8;
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
                        case 8:
                           this.major_ = input.readInt32();
                           this.bitField0_ |= 1;
                           break;
                        case 16:
                           this.minor_ = input.readInt32();
                           this.bitField0_ |= 2;
                           break;
                        case 24:
                           this.patch_ = input.readInt32();
                           this.bitField0_ |= 4;
                           break;
                        case 34:
                           this.suffix_ = input.readBytes();
                           this.bitField0_ |= 8;
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

         public boolean hasMajor() {
            return (this.bitField0_ & 1) != 0;
         }

         public int getMajor() {
            return this.major_;
         }

         public Builder setMajor(int value) {
            this.major_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }

         public Builder clearMajor() {
            this.bitField0_ &= -2;
            this.major_ = 0;
            this.onChanged();
            return this;
         }

         public boolean hasMinor() {
            return (this.bitField0_ & 2) != 0;
         }

         public int getMinor() {
            return this.minor_;
         }

         public Builder setMinor(int value) {
            this.minor_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }

         public Builder clearMinor() {
            this.bitField0_ &= -3;
            this.minor_ = 0;
            this.onChanged();
            return this;
         }

         public boolean hasPatch() {
            return (this.bitField0_ & 4) != 0;
         }

         public int getPatch() {
            return this.patch_;
         }

         public Builder setPatch(int value) {
            this.patch_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }

         public Builder clearPatch() {
            this.bitField0_ &= -5;
            this.patch_ = 0;
            this.onChanged();
            return this;
         }

         public boolean hasSuffix() {
            return (this.bitField0_ & 8) != 0;
         }

         public String getSuffix() {
            Object ref = this.suffix_;
            if (!(ref instanceof String)) {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.suffix_ = s;
               }

               return s;
            } else {
               return (String)ref;
            }
         }

         public ByteString getSuffixBytes() {
            Object ref = this.suffix_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.suffix_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public Builder setSuffix(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.suffix_ = value;
               this.bitField0_ |= 8;
               this.onChanged();
               return this;
            }
         }

         public Builder clearSuffix() {
            this.suffix_ = PluginProtos.Version.getDefaultInstance().getSuffix();
            this.bitField0_ &= -9;
            this.onChanged();
            return this;
         }

         public Builder setSuffixBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.suffix_ = value;
               this.bitField0_ |= 8;
               this.onChanged();
               return this;
            }
         }
      }
   }

   public static final class CodeGeneratorRequest extends GeneratedMessage implements CodeGeneratorRequestOrBuilder {
      private static final long serialVersionUID = 0L;
      private int bitField0_;
      public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
      private LazyStringArrayList fileToGenerate_;
      public static final int PARAMETER_FIELD_NUMBER = 2;
      private volatile Object parameter_;
      public static final int PROTO_FILE_FIELD_NUMBER = 15;
      private List protoFile_;
      public static final int SOURCE_FILE_DESCRIPTORS_FIELD_NUMBER = 17;
      private List sourceFileDescriptors_;
      public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
      private Version compilerVersion_;
      private byte memoizedIsInitialized;
      private static final CodeGeneratorRequest DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private CodeGeneratorRequest(GeneratedMessage.Builder builder) {
         super(builder);
         this.fileToGenerate_ = LazyStringArrayList.emptyList();
         this.parameter_ = "";
         this.memoizedIsInitialized = -1;
      }

      private CodeGeneratorRequest() {
         this.fileToGenerate_ = LazyStringArrayList.emptyList();
         this.parameter_ = "";
         this.memoizedIsInitialized = -1;
         this.fileToGenerate_ = LazyStringArrayList.emptyList();
         this.parameter_ = "";
         this.protoFile_ = Collections.emptyList();
         this.sourceFileDescriptors_ = Collections.emptyList();
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
      }

      public ProtocolStringList getFileToGenerateList() {
         return this.fileToGenerate_;
      }

      public int getFileToGenerateCount() {
         return this.fileToGenerate_.size();
      }

      public String getFileToGenerate(int index) {
         return this.fileToGenerate_.get(index);
      }

      public ByteString getFileToGenerateBytes(int index) {
         return this.fileToGenerate_.getByteString(index);
      }

      public boolean hasParameter() {
         return (this.bitField0_ & 1) != 0;
      }

      public String getParameter() {
         Object ref = this.parameter_;
         if (ref instanceof String) {
            return (String)ref;
         } else {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
               this.parameter_ = s;
            }

            return s;
         }
      }

      public ByteString getParameterBytes() {
         Object ref = this.parameter_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.parameter_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public List getProtoFileList() {
         return this.protoFile_;
      }

      public List getProtoFileOrBuilderList() {
         return this.protoFile_;
      }

      public int getProtoFileCount() {
         return this.protoFile_.size();
      }

      public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
         return (DescriptorProtos.FileDescriptorProto)this.protoFile_.get(index);
      }

      public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
         return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index);
      }

      public List getSourceFileDescriptorsList() {
         return this.sourceFileDescriptors_;
      }

      public List getSourceFileDescriptorsOrBuilderList() {
         return this.sourceFileDescriptors_;
      }

      public int getSourceFileDescriptorsCount() {
         return this.sourceFileDescriptors_.size();
      }

      public DescriptorProtos.FileDescriptorProto getSourceFileDescriptors(int index) {
         return (DescriptorProtos.FileDescriptorProto)this.sourceFileDescriptors_.get(index);
      }

      public DescriptorProtos.FileDescriptorProtoOrBuilder getSourceFileDescriptorsOrBuilder(int index) {
         return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.sourceFileDescriptors_.get(index);
      }

      public boolean hasCompilerVersion() {
         return (this.bitField0_ & 2) != 0;
      }

      public Version getCompilerVersion() {
         return this.compilerVersion_ == null ? PluginProtos.Version.getDefaultInstance() : this.compilerVersion_;
      }

      public VersionOrBuilder getCompilerVersionOrBuilder() {
         return this.compilerVersion_ == null ? PluginProtos.Version.getDefaultInstance() : this.compilerVersion_;
      }

      public final boolean isInitialized() {
         byte isInitialized = this.memoizedIsInitialized;
         if (isInitialized == 1) {
            return true;
         } else if (isInitialized == 0) {
            return false;
         } else {
            for(int i = 0; i < this.getProtoFileCount(); ++i) {
               if (!this.getProtoFile(i).isInitialized()) {
                  this.memoizedIsInitialized = 0;
                  return false;
               }
            }

            for(int i = 0; i < this.getSourceFileDescriptorsCount(); ++i) {
               if (!this.getSourceFileDescriptors(i).isInitialized()) {
                  this.memoizedIsInitialized = 0;
                  return false;
               }
            }

            this.memoizedIsInitialized = 1;
            return true;
         }
      }

      public void writeTo(CodedOutputStream output) throws IOException {
         for(int i = 0; i < this.fileToGenerate_.size(); ++i) {
            GeneratedMessage.writeString(output, 1, this.fileToGenerate_.getRaw(i));
         }

         if ((this.bitField0_ & 1) != 0) {
            GeneratedMessage.writeString(output, 2, this.parameter_);
         }

         if ((this.bitField0_ & 2) != 0) {
            output.writeMessage(3, this.getCompilerVersion());
         }

         for(int i = 0; i < this.protoFile_.size(); ++i) {
            output.writeMessage(15, (MessageLite)this.protoFile_.get(i));
         }

         for(int i = 0; i < this.sourceFileDescriptors_.size(); ++i) {
            output.writeMessage(17, (MessageLite)this.sourceFileDescriptors_.get(i));
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;
            int dataSize = 0;

            for(int i = 0; i < this.fileToGenerate_.size(); ++i) {
               dataSize += computeStringSizeNoTag(this.fileToGenerate_.getRaw(i));
            }

            size += dataSize;
            size += 1 * this.getFileToGenerateList().size();
            if ((this.bitField0_ & 1) != 0) {
               size += GeneratedMessage.computeStringSize(2, this.parameter_);
            }

            if ((this.bitField0_ & 2) != 0) {
               size += CodedOutputStream.computeMessageSize(3, this.getCompilerVersion());
            }

            for(int i = 0; i < this.protoFile_.size(); ++i) {
               size += CodedOutputStream.computeMessageSize(15, (MessageLite)this.protoFile_.get(i));
            }

            for(int i = 0; i < this.sourceFileDescriptors_.size(); ++i) {
               size += CodedOutputStream.computeMessageSize(17, (MessageLite)this.sourceFileDescriptors_.get(i));
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof CodeGeneratorRequest)) {
            return super.equals(obj);
         } else {
            CodeGeneratorRequest other = (CodeGeneratorRequest)obj;
            if (!this.getFileToGenerateList().equals(other.getFileToGenerateList())) {
               return false;
            } else if (this.hasParameter() != other.hasParameter()) {
               return false;
            } else if (this.hasParameter() && !this.getParameter().equals(other.getParameter())) {
               return false;
            } else if (!this.getProtoFileList().equals(other.getProtoFileList())) {
               return false;
            } else if (!this.getSourceFileDescriptorsList().equals(other.getSourceFileDescriptorsList())) {
               return false;
            } else if (this.hasCompilerVersion() != other.hasCompilerVersion()) {
               return false;
            } else if (this.hasCompilerVersion() && !this.getCompilerVersion().equals(other.getCompilerVersion())) {
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
            if (this.getFileToGenerateCount() > 0) {
               hash = 37 * hash + 1;
               hash = 53 * hash + this.getFileToGenerateList().hashCode();
            }

            if (this.hasParameter()) {
               hash = 37 * hash + 2;
               hash = 53 * hash + this.getParameter().hashCode();
            }

            if (this.getProtoFileCount() > 0) {
               hash = 37 * hash + 15;
               hash = 53 * hash + this.getProtoFileList().hashCode();
            }

            if (this.getSourceFileDescriptorsCount() > 0) {
               hash = 37 * hash + 17;
               hash = 53 * hash + this.getSourceFileDescriptorsList().hashCode();
            }

            if (this.hasCompilerVersion()) {
               hash = 37 * hash + 3;
               hash = 53 * hash + this.getCompilerVersion().hashCode();
            }

            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static CodeGeneratorRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data);
      }

      public static CodeGeneratorRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data);
      }

      public static CodeGeneratorRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data);
      }

      public static CodeGeneratorRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorRequest parseFrom(InputStream input) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CodeGeneratorRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static CodeGeneratorRequest parseDelimitedFrom(InputStream input) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static CodeGeneratorRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static CodeGeneratorRequest parseFrom(CodedInputStream input) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CodeGeneratorRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorRequest)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(CodeGeneratorRequest prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static CodeGeneratorRequest getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public CodeGeneratorRequest getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", CodeGeneratorRequest.class.getName());
         DEFAULT_INSTANCE = new CodeGeneratorRequest();
         PARSER = new AbstractParser() {
            public CodeGeneratorRequest parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = PluginProtos.CodeGeneratorRequest.newBuilder();

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

      public static final class Builder extends GeneratedMessage.Builder implements CodeGeneratorRequestOrBuilder {
         private int bitField0_;
         private LazyStringArrayList fileToGenerate_;
         private Object parameter_;
         private List protoFile_;
         private RepeatedFieldBuilder protoFileBuilder_;
         private List sourceFileDescriptors_;
         private RepeatedFieldBuilder sourceFileDescriptorsBuilder_;
         private Version compilerVersion_;
         private SingleFieldBuilder compilerVersionBuilder_;

         public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
         }

         private Builder() {
            this.fileToGenerate_ = LazyStringArrayList.emptyList();
            this.parameter_ = "";
            this.protoFile_ = Collections.emptyList();
            this.sourceFileDescriptors_ = Collections.emptyList();
            this.maybeForceBuilderInitialization();
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.fileToGenerate_ = LazyStringArrayList.emptyList();
            this.parameter_ = "";
            this.protoFile_ = Collections.emptyList();
            this.sourceFileDescriptors_ = Collections.emptyList();
            this.maybeForceBuilderInitialization();
         }

         private void maybeForceBuilderInitialization() {
            if (PluginProtos.CodeGeneratorRequest.alwaysUseFieldBuilders) {
               this.getProtoFileFieldBuilder();
               this.getSourceFileDescriptorsFieldBuilder();
               this.getCompilerVersionFieldBuilder();
            }

         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.fileToGenerate_ = LazyStringArrayList.emptyList();
            this.parameter_ = "";
            if (this.protoFileBuilder_ == null) {
               this.protoFile_ = Collections.emptyList();
            } else {
               this.protoFile_ = null;
               this.protoFileBuilder_.clear();
            }

            this.bitField0_ &= -5;
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.sourceFileDescriptors_ = Collections.emptyList();
            } else {
               this.sourceFileDescriptors_ = null;
               this.sourceFileDescriptorsBuilder_.clear();
            }

            this.bitField0_ &= -9;
            this.compilerVersion_ = null;
            if (this.compilerVersionBuilder_ != null) {
               this.compilerVersionBuilder_.dispose();
               this.compilerVersionBuilder_ = null;
            }

            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
         }

         public CodeGeneratorRequest getDefaultInstanceForType() {
            return PluginProtos.CodeGeneratorRequest.getDefaultInstance();
         }

         public CodeGeneratorRequest build() {
            CodeGeneratorRequest result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public CodeGeneratorRequest buildPartial() {
            CodeGeneratorRequest result = new CodeGeneratorRequest(this);
            this.buildPartialRepeatedFields(result);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartialRepeatedFields(CodeGeneratorRequest result) {
            if (this.protoFileBuilder_ == null) {
               if ((this.bitField0_ & 4) != 0) {
                  this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                  this.bitField0_ &= -5;
               }

               result.protoFile_ = this.protoFile_;
            } else {
               result.protoFile_ = this.protoFileBuilder_.build();
            }

            if (this.sourceFileDescriptorsBuilder_ == null) {
               if ((this.bitField0_ & 8) != 0) {
                  this.sourceFileDescriptors_ = Collections.unmodifiableList(this.sourceFileDescriptors_);
                  this.bitField0_ &= -9;
               }

               result.sourceFileDescriptors_ = this.sourceFileDescriptors_;
            } else {
               result.sourceFileDescriptors_ = this.sourceFileDescriptorsBuilder_.build();
            }

         }

         private void buildPartial0(CodeGeneratorRequest result) {
            int from_bitField0_ = this.bitField0_;
            if ((from_bitField0_ & 1) != 0) {
               this.fileToGenerate_.makeImmutable();
               result.fileToGenerate_ = this.fileToGenerate_;
            }

            int to_bitField0_ = 0;
            if ((from_bitField0_ & 2) != 0) {
               result.parameter_ = this.parameter_;
               to_bitField0_ |= 1;
            }

            if ((from_bitField0_ & 16) != 0) {
               result.compilerVersion_ = this.compilerVersionBuilder_ == null ? this.compilerVersion_ : (Version)this.compilerVersionBuilder_.build();
               to_bitField0_ |= 2;
            }

            result.bitField0_ = to_bitField0_;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof CodeGeneratorRequest) {
               return this.mergeFrom((CodeGeneratorRequest)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(CodeGeneratorRequest other) {
            if (other == PluginProtos.CodeGeneratorRequest.getDefaultInstance()) {
               return this;
            } else {
               if (!other.fileToGenerate_.isEmpty()) {
                  if (this.fileToGenerate_.isEmpty()) {
                     this.fileToGenerate_ = other.fileToGenerate_;
                     this.bitField0_ |= 1;
                  } else {
                     this.ensureFileToGenerateIsMutable();
                     this.fileToGenerate_.addAll(other.fileToGenerate_);
                  }

                  this.onChanged();
               }

               if (other.hasParameter()) {
                  this.parameter_ = other.parameter_;
                  this.bitField0_ |= 2;
                  this.onChanged();
               }

               if (this.protoFileBuilder_ == null) {
                  if (!other.protoFile_.isEmpty()) {
                     if (this.protoFile_.isEmpty()) {
                        this.protoFile_ = other.protoFile_;
                        this.bitField0_ &= -5;
                     } else {
                        this.ensureProtoFileIsMutable();
                        this.protoFile_.addAll(other.protoFile_);
                     }

                     this.onChanged();
                  }
               } else if (!other.protoFile_.isEmpty()) {
                  if (this.protoFileBuilder_.isEmpty()) {
                     this.protoFileBuilder_.dispose();
                     this.protoFileBuilder_ = null;
                     this.protoFile_ = other.protoFile_;
                     this.bitField0_ &= -5;
                     this.protoFileBuilder_ = PluginProtos.CodeGeneratorRequest.alwaysUseFieldBuilders ? this.getProtoFileFieldBuilder() : null;
                  } else {
                     this.protoFileBuilder_.addAllMessages(other.protoFile_);
                  }
               }

               if (this.sourceFileDescriptorsBuilder_ == null) {
                  if (!other.sourceFileDescriptors_.isEmpty()) {
                     if (this.sourceFileDescriptors_.isEmpty()) {
                        this.sourceFileDescriptors_ = other.sourceFileDescriptors_;
                        this.bitField0_ &= -9;
                     } else {
                        this.ensureSourceFileDescriptorsIsMutable();
                        this.sourceFileDescriptors_.addAll(other.sourceFileDescriptors_);
                     }

                     this.onChanged();
                  }
               } else if (!other.sourceFileDescriptors_.isEmpty()) {
                  if (this.sourceFileDescriptorsBuilder_.isEmpty()) {
                     this.sourceFileDescriptorsBuilder_.dispose();
                     this.sourceFileDescriptorsBuilder_ = null;
                     this.sourceFileDescriptors_ = other.sourceFileDescriptors_;
                     this.bitField0_ &= -9;
                     this.sourceFileDescriptorsBuilder_ = PluginProtos.CodeGeneratorRequest.alwaysUseFieldBuilders ? this.getSourceFileDescriptorsFieldBuilder() : null;
                  } else {
                     this.sourceFileDescriptorsBuilder_.addAllMessages(other.sourceFileDescriptors_);
                  }
               }

               if (other.hasCompilerVersion()) {
                  this.mergeCompilerVersion(other.getCompilerVersion());
               }

               this.mergeUnknownFields(other.getUnknownFields());
               this.onChanged();
               return this;
            }
         }

         public final boolean isInitialized() {
            for(int i = 0; i < this.getProtoFileCount(); ++i) {
               if (!this.getProtoFile(i).isInitialized()) {
                  return false;
               }
            }

            for(int i = 0; i < this.getSourceFileDescriptorsCount(); ++i) {
               if (!this.getSourceFileDescriptors(i).isInitialized()) {
                  return false;
               }
            }

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
                           ByteString bs = input.readBytes();
                           this.ensureFileToGenerateIsMutable();
                           this.fileToGenerate_.add(bs);
                           break;
                        case 18:
                           this.parameter_ = input.readBytes();
                           this.bitField0_ |= 2;
                           break;
                        case 26:
                           input.readMessage((MessageLite.Builder)this.getCompilerVersionFieldBuilder().getBuilder(), extensionRegistry);
                           this.bitField0_ |= 16;
                           break;
                        case 122:
                           DescriptorProtos.FileDescriptorProto m = (DescriptorProtos.FileDescriptorProto)input.readMessage(DescriptorProtos.FileDescriptorProto.parser(), extensionRegistry);
                           if (this.protoFileBuilder_ == null) {
                              this.ensureProtoFileIsMutable();
                              this.protoFile_.add(m);
                           } else {
                              this.protoFileBuilder_.addMessage(m);
                           }
                           break;
                        case 138:
                           DescriptorProtos.FileDescriptorProto m = (DescriptorProtos.FileDescriptorProto)input.readMessage(DescriptorProtos.FileDescriptorProto.parser(), extensionRegistry);
                           if (this.sourceFileDescriptorsBuilder_ == null) {
                              this.ensureSourceFileDescriptorsIsMutable();
                              this.sourceFileDescriptors_.add(m);
                           } else {
                              this.sourceFileDescriptorsBuilder_.addMessage(m);
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

         private void ensureFileToGenerateIsMutable() {
            if (!this.fileToGenerate_.isModifiable()) {
               this.fileToGenerate_ = new LazyStringArrayList(this.fileToGenerate_);
            }

            this.bitField0_ |= 1;
         }

         public ProtocolStringList getFileToGenerateList() {
            this.fileToGenerate_.makeImmutable();
            return this.fileToGenerate_;
         }

         public int getFileToGenerateCount() {
            return this.fileToGenerate_.size();
         }

         public String getFileToGenerate(int index) {
            return this.fileToGenerate_.get(index);
         }

         public ByteString getFileToGenerateBytes(int index) {
            return this.fileToGenerate_.getByteString(index);
         }

         public Builder setFileToGenerate(int index, String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.ensureFileToGenerateIsMutable();
               this.fileToGenerate_.set(index, value);
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public Builder addFileToGenerate(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.ensureFileToGenerateIsMutable();
               this.fileToGenerate_.add(value);
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public Builder addAllFileToGenerate(Iterable values) {
            this.ensureFileToGenerateIsMutable();
            AbstractMessageLite.Builder.addAll(values, (List)this.fileToGenerate_);
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }

         public Builder clearFileToGenerate() {
            this.fileToGenerate_ = LazyStringArrayList.emptyList();
            this.bitField0_ &= -2;
            this.onChanged();
            return this;
         }

         public Builder addFileToGenerateBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.ensureFileToGenerateIsMutable();
               this.fileToGenerate_.add(value);
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public boolean hasParameter() {
            return (this.bitField0_ & 2) != 0;
         }

         public String getParameter() {
            Object ref = this.parameter_;
            if (!(ref instanceof String)) {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.parameter_ = s;
               }

               return s;
            } else {
               return (String)ref;
            }
         }

         public ByteString getParameterBytes() {
            Object ref = this.parameter_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.parameter_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public Builder setParameter(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.parameter_ = value;
               this.bitField0_ |= 2;
               this.onChanged();
               return this;
            }
         }

         public Builder clearParameter() {
            this.parameter_ = PluginProtos.CodeGeneratorRequest.getDefaultInstance().getParameter();
            this.bitField0_ &= -3;
            this.onChanged();
            return this;
         }

         public Builder setParameterBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.parameter_ = value;
               this.bitField0_ |= 2;
               this.onChanged();
               return this;
            }
         }

         private void ensureProtoFileIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
               this.protoFile_ = new ArrayList(this.protoFile_);
               this.bitField0_ |= 4;
            }

         }

         public List getProtoFileList() {
            return this.protoFileBuilder_ == null ? Collections.unmodifiableList(this.protoFile_) : this.protoFileBuilder_.getMessageList();
         }

         public int getProtoFileCount() {
            return this.protoFileBuilder_ == null ? this.protoFile_.size() : this.protoFileBuilder_.getCount();
         }

         public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
            return this.protoFileBuilder_ == null ? (DescriptorProtos.FileDescriptorProto)this.protoFile_.get(index) : (DescriptorProtos.FileDescriptorProto)this.protoFileBuilder_.getMessage(index);
         }

         public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
            if (this.protoFileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureProtoFileIsMutable();
               this.protoFile_.set(index, value);
               this.onChanged();
            } else {
               this.protoFileBuilder_.setMessage(index, value);
            }

            return this;
         }

         public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.protoFileBuilder_ == null) {
               this.ensureProtoFileIsMutable();
               this.protoFile_.set(index, builderForValue.build());
               this.onChanged();
            } else {
               this.protoFileBuilder_.setMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addProtoFile(DescriptorProtos.FileDescriptorProto value) {
            if (this.protoFileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureProtoFileIsMutable();
               this.protoFile_.add(value);
               this.onChanged();
            } else {
               this.protoFileBuilder_.addMessage(value);
            }

            return this;
         }

         public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
            if (this.protoFileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureProtoFileIsMutable();
               this.protoFile_.add(index, value);
               this.onChanged();
            } else {
               this.protoFileBuilder_.addMessage(index, value);
            }

            return this;
         }

         public Builder addProtoFile(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.protoFileBuilder_ == null) {
               this.ensureProtoFileIsMutable();
               this.protoFile_.add(builderForValue.build());
               this.onChanged();
            } else {
               this.protoFileBuilder_.addMessage(builderForValue.build());
            }

            return this;
         }

         public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.protoFileBuilder_ == null) {
               this.ensureProtoFileIsMutable();
               this.protoFile_.add(index, builderForValue.build());
               this.onChanged();
            } else {
               this.protoFileBuilder_.addMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addAllProtoFile(Iterable values) {
            if (this.protoFileBuilder_ == null) {
               this.ensureProtoFileIsMutable();
               AbstractMessageLite.Builder.addAll(values, this.protoFile_);
               this.onChanged();
            } else {
               this.protoFileBuilder_.addAllMessages(values);
            }

            return this;
         }

         public Builder clearProtoFile() {
            if (this.protoFileBuilder_ == null) {
               this.protoFile_ = Collections.emptyList();
               this.bitField0_ &= -5;
               this.onChanged();
            } else {
               this.protoFileBuilder_.clear();
            }

            return this;
         }

         public Builder removeProtoFile(int index) {
            if (this.protoFileBuilder_ == null) {
               this.ensureProtoFileIsMutable();
               this.protoFile_.remove(index);
               this.onChanged();
            } else {
               this.protoFileBuilder_.remove(index);
            }

            return this;
         }

         public DescriptorProtos.FileDescriptorProto.Builder getProtoFileBuilder(int index) {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getProtoFileFieldBuilder().getBuilder(index);
         }

         public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
            return this.protoFileBuilder_ == null ? (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index) : (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFileBuilder_.getMessageOrBuilder(index);
         }

         public List getProtoFileOrBuilderList() {
            return this.protoFileBuilder_ != null ? this.protoFileBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.protoFile_);
         }

         public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder() {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getProtoFileFieldBuilder().addBuilder(DescriptorProtos.FileDescriptorProto.getDefaultInstance());
         }

         public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder(int index) {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getProtoFileFieldBuilder().addBuilder(index, DescriptorProtos.FileDescriptorProto.getDefaultInstance());
         }

         public List getProtoFileBuilderList() {
            return this.getProtoFileFieldBuilder().getBuilderList();
         }

         private RepeatedFieldBuilder getProtoFileFieldBuilder() {
            if (this.protoFileBuilder_ == null) {
               this.protoFileBuilder_ = new RepeatedFieldBuilder(this.protoFile_, (this.bitField0_ & 4) != 0, this.getParentForChildren(), this.isClean());
               this.protoFile_ = null;
            }

            return this.protoFileBuilder_;
         }

         private void ensureSourceFileDescriptorsIsMutable() {
            if ((this.bitField0_ & 8) == 0) {
               this.sourceFileDescriptors_ = new ArrayList(this.sourceFileDescriptors_);
               this.bitField0_ |= 8;
            }

         }

         public List getSourceFileDescriptorsList() {
            return this.sourceFileDescriptorsBuilder_ == null ? Collections.unmodifiableList(this.sourceFileDescriptors_) : this.sourceFileDescriptorsBuilder_.getMessageList();
         }

         public int getSourceFileDescriptorsCount() {
            return this.sourceFileDescriptorsBuilder_ == null ? this.sourceFileDescriptors_.size() : this.sourceFileDescriptorsBuilder_.getCount();
         }

         public DescriptorProtos.FileDescriptorProto getSourceFileDescriptors(int index) {
            return this.sourceFileDescriptorsBuilder_ == null ? (DescriptorProtos.FileDescriptorProto)this.sourceFileDescriptors_.get(index) : (DescriptorProtos.FileDescriptorProto)this.sourceFileDescriptorsBuilder_.getMessage(index);
         }

         public Builder setSourceFileDescriptors(int index, DescriptorProtos.FileDescriptorProto value) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.set(index, value);
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.setMessage(index, value);
            }

            return this;
         }

         public Builder setSourceFileDescriptors(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.set(index, builderForValue.build());
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.setMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addSourceFileDescriptors(DescriptorProtos.FileDescriptorProto value) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.add(value);
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.addMessage(value);
            }

            return this;
         }

         public Builder addSourceFileDescriptors(int index, DescriptorProtos.FileDescriptorProto value) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.add(index, value);
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.addMessage(index, value);
            }

            return this;
         }

         public Builder addSourceFileDescriptors(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.add(builderForValue.build());
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.addMessage(builderForValue.build());
            }

            return this;
         }

         public Builder addSourceFileDescriptors(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.add(index, builderForValue.build());
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.addMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addAllSourceFileDescriptors(Iterable values) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.ensureSourceFileDescriptorsIsMutable();
               AbstractMessageLite.Builder.addAll(values, this.sourceFileDescriptors_);
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.addAllMessages(values);
            }

            return this;
         }

         public Builder clearSourceFileDescriptors() {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.sourceFileDescriptors_ = Collections.emptyList();
               this.bitField0_ &= -9;
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.clear();
            }

            return this;
         }

         public Builder removeSourceFileDescriptors(int index) {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.ensureSourceFileDescriptorsIsMutable();
               this.sourceFileDescriptors_.remove(index);
               this.onChanged();
            } else {
               this.sourceFileDescriptorsBuilder_.remove(index);
            }

            return this;
         }

         public DescriptorProtos.FileDescriptorProto.Builder getSourceFileDescriptorsBuilder(int index) {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getSourceFileDescriptorsFieldBuilder().getBuilder(index);
         }

         public DescriptorProtos.FileDescriptorProtoOrBuilder getSourceFileDescriptorsOrBuilder(int index) {
            return this.sourceFileDescriptorsBuilder_ == null ? (DescriptorProtos.FileDescriptorProtoOrBuilder)this.sourceFileDescriptors_.get(index) : (DescriptorProtos.FileDescriptorProtoOrBuilder)this.sourceFileDescriptorsBuilder_.getMessageOrBuilder(index);
         }

         public List getSourceFileDescriptorsOrBuilderList() {
            return this.sourceFileDescriptorsBuilder_ != null ? this.sourceFileDescriptorsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.sourceFileDescriptors_);
         }

         public DescriptorProtos.FileDescriptorProto.Builder addSourceFileDescriptorsBuilder() {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getSourceFileDescriptorsFieldBuilder().addBuilder(DescriptorProtos.FileDescriptorProto.getDefaultInstance());
         }

         public DescriptorProtos.FileDescriptorProto.Builder addSourceFileDescriptorsBuilder(int index) {
            return (DescriptorProtos.FileDescriptorProto.Builder)this.getSourceFileDescriptorsFieldBuilder().addBuilder(index, DescriptorProtos.FileDescriptorProto.getDefaultInstance());
         }

         public List getSourceFileDescriptorsBuilderList() {
            return this.getSourceFileDescriptorsFieldBuilder().getBuilderList();
         }

         private RepeatedFieldBuilder getSourceFileDescriptorsFieldBuilder() {
            if (this.sourceFileDescriptorsBuilder_ == null) {
               this.sourceFileDescriptorsBuilder_ = new RepeatedFieldBuilder(this.sourceFileDescriptors_, (this.bitField0_ & 8) != 0, this.getParentForChildren(), this.isClean());
               this.sourceFileDescriptors_ = null;
            }

            return this.sourceFileDescriptorsBuilder_;
         }

         public boolean hasCompilerVersion() {
            return (this.bitField0_ & 16) != 0;
         }

         public Version getCompilerVersion() {
            if (this.compilerVersionBuilder_ == null) {
               return this.compilerVersion_ == null ? PluginProtos.Version.getDefaultInstance() : this.compilerVersion_;
            } else {
               return (Version)this.compilerVersionBuilder_.getMessage();
            }
         }

         public Builder setCompilerVersion(Version value) {
            if (this.compilerVersionBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.compilerVersion_ = value;
            } else {
               this.compilerVersionBuilder_.setMessage(value);
            }

            this.bitField0_ |= 16;
            this.onChanged();
            return this;
         }

         public Builder setCompilerVersion(Version.Builder builderForValue) {
            if (this.compilerVersionBuilder_ == null) {
               this.compilerVersion_ = builderForValue.build();
            } else {
               this.compilerVersionBuilder_.setMessage(builderForValue.build());
            }

            this.bitField0_ |= 16;
            this.onChanged();
            return this;
         }

         public Builder mergeCompilerVersion(Version value) {
            if (this.compilerVersionBuilder_ == null) {
               if ((this.bitField0_ & 16) != 0 && this.compilerVersion_ != null && this.compilerVersion_ != PluginProtos.Version.getDefaultInstance()) {
                  this.getCompilerVersionBuilder().mergeFrom(value);
               } else {
                  this.compilerVersion_ = value;
               }
            } else {
               this.compilerVersionBuilder_.mergeFrom(value);
            }

            if (this.compilerVersion_ != null) {
               this.bitField0_ |= 16;
               this.onChanged();
            }

            return this;
         }

         public Builder clearCompilerVersion() {
            this.bitField0_ &= -17;
            this.compilerVersion_ = null;
            if (this.compilerVersionBuilder_ != null) {
               this.compilerVersionBuilder_.dispose();
               this.compilerVersionBuilder_ = null;
            }

            this.onChanged();
            return this;
         }

         public Version.Builder getCompilerVersionBuilder() {
            this.bitField0_ |= 16;
            this.onChanged();
            return (Version.Builder)this.getCompilerVersionFieldBuilder().getBuilder();
         }

         public VersionOrBuilder getCompilerVersionOrBuilder() {
            if (this.compilerVersionBuilder_ != null) {
               return (VersionOrBuilder)this.compilerVersionBuilder_.getMessageOrBuilder();
            } else {
               return this.compilerVersion_ == null ? PluginProtos.Version.getDefaultInstance() : this.compilerVersion_;
            }
         }

         private SingleFieldBuilder getCompilerVersionFieldBuilder() {
            if (this.compilerVersionBuilder_ == null) {
               this.compilerVersionBuilder_ = new SingleFieldBuilder(this.getCompilerVersion(), this.getParentForChildren(), this.isClean());
               this.compilerVersion_ = null;
            }

            return this.compilerVersionBuilder_;
         }
      }
   }

   public static final class CodeGeneratorResponse extends GeneratedMessage implements CodeGeneratorResponseOrBuilder {
      private static final long serialVersionUID = 0L;
      private int bitField0_;
      public static final int ERROR_FIELD_NUMBER = 1;
      private volatile Object error_;
      public static final int SUPPORTED_FEATURES_FIELD_NUMBER = 2;
      private long supportedFeatures_;
      public static final int MINIMUM_EDITION_FIELD_NUMBER = 3;
      private int minimumEdition_;
      public static final int MAXIMUM_EDITION_FIELD_NUMBER = 4;
      private int maximumEdition_;
      public static final int FILE_FIELD_NUMBER = 15;
      private List file_;
      private byte memoizedIsInitialized;
      private static final CodeGeneratorResponse DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private CodeGeneratorResponse(GeneratedMessage.Builder builder) {
         super(builder);
         this.error_ = "";
         this.supportedFeatures_ = 0L;
         this.minimumEdition_ = 0;
         this.maximumEdition_ = 0;
         this.memoizedIsInitialized = -1;
      }

      private CodeGeneratorResponse() {
         this.error_ = "";
         this.supportedFeatures_ = 0L;
         this.minimumEdition_ = 0;
         this.maximumEdition_ = 0;
         this.memoizedIsInitialized = -1;
         this.error_ = "";
         this.file_ = Collections.emptyList();
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
      }

      public boolean hasError() {
         return (this.bitField0_ & 1) != 0;
      }

      public String getError() {
         Object ref = this.error_;
         if (ref instanceof String) {
            return (String)ref;
         } else {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
               this.error_ = s;
            }

            return s;
         }
      }

      public ByteString getErrorBytes() {
         Object ref = this.error_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.error_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public boolean hasSupportedFeatures() {
         return (this.bitField0_ & 2) != 0;
      }

      public long getSupportedFeatures() {
         return this.supportedFeatures_;
      }

      public boolean hasMinimumEdition() {
         return (this.bitField0_ & 4) != 0;
      }

      public int getMinimumEdition() {
         return this.minimumEdition_;
      }

      public boolean hasMaximumEdition() {
         return (this.bitField0_ & 8) != 0;
      }

      public int getMaximumEdition() {
         return this.maximumEdition_;
      }

      public List getFileList() {
         return this.file_;
      }

      public List getFileOrBuilderList() {
         return this.file_;
      }

      public int getFileCount() {
         return this.file_.size();
      }

      public File getFile(int index) {
         return (File)this.file_.get(index);
      }

      public FileOrBuilder getFileOrBuilder(int index) {
         return (FileOrBuilder)this.file_.get(index);
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
            GeneratedMessage.writeString(output, 1, this.error_);
         }

         if ((this.bitField0_ & 2) != 0) {
            output.writeUInt64(2, this.supportedFeatures_);
         }

         if ((this.bitField0_ & 4) != 0) {
            output.writeInt32(3, this.minimumEdition_);
         }

         if ((this.bitField0_ & 8) != 0) {
            output.writeInt32(4, this.maximumEdition_);
         }

         for(int i = 0; i < this.file_.size(); ++i) {
            output.writeMessage(15, (MessageLite)this.file_.get(i));
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
               size += GeneratedMessage.computeStringSize(1, this.error_);
            }

            if ((this.bitField0_ & 2) != 0) {
               size += CodedOutputStream.computeUInt64Size(2, this.supportedFeatures_);
            }

            if ((this.bitField0_ & 4) != 0) {
               size += CodedOutputStream.computeInt32Size(3, this.minimumEdition_);
            }

            if ((this.bitField0_ & 8) != 0) {
               size += CodedOutputStream.computeInt32Size(4, this.maximumEdition_);
            }

            for(int i = 0; i < this.file_.size(); ++i) {
               size += CodedOutputStream.computeMessageSize(15, (MessageLite)this.file_.get(i));
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof CodeGeneratorResponse)) {
            return super.equals(obj);
         } else {
            CodeGeneratorResponse other = (CodeGeneratorResponse)obj;
            if (this.hasError() != other.hasError()) {
               return false;
            } else if (this.hasError() && !this.getError().equals(other.getError())) {
               return false;
            } else if (this.hasSupportedFeatures() != other.hasSupportedFeatures()) {
               return false;
            } else if (this.hasSupportedFeatures() && this.getSupportedFeatures() != other.getSupportedFeatures()) {
               return false;
            } else if (this.hasMinimumEdition() != other.hasMinimumEdition()) {
               return false;
            } else if (this.hasMinimumEdition() && this.getMinimumEdition() != other.getMinimumEdition()) {
               return false;
            } else if (this.hasMaximumEdition() != other.hasMaximumEdition()) {
               return false;
            } else if (this.hasMaximumEdition() && this.getMaximumEdition() != other.getMaximumEdition()) {
               return false;
            } else if (!this.getFileList().equals(other.getFileList())) {
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
            if (this.hasError()) {
               hash = 37 * hash + 1;
               hash = 53 * hash + this.getError().hashCode();
            }

            if (this.hasSupportedFeatures()) {
               hash = 37 * hash + 2;
               hash = 53 * hash + Internal.hashLong(this.getSupportedFeatures());
            }

            if (this.hasMinimumEdition()) {
               hash = 37 * hash + 3;
               hash = 53 * hash + this.getMinimumEdition();
            }

            if (this.hasMaximumEdition()) {
               hash = 37 * hash + 4;
               hash = 53 * hash + this.getMaximumEdition();
            }

            if (this.getFileCount() > 0) {
               hash = 37 * hash + 15;
               hash = 53 * hash + this.getFileList().hashCode();
            }

            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static CodeGeneratorResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data);
      }

      public static CodeGeneratorResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data);
      }

      public static CodeGeneratorResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data);
      }

      public static CodeGeneratorResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CodeGeneratorResponse parseFrom(InputStream input) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CodeGeneratorResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static CodeGeneratorResponse parseDelimitedFrom(InputStream input) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static CodeGeneratorResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static CodeGeneratorResponse parseFrom(CodedInputStream input) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CodeGeneratorResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CodeGeneratorResponse)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(CodeGeneratorResponse prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static CodeGeneratorResponse getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public CodeGeneratorResponse getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", CodeGeneratorResponse.class.getName());
         DEFAULT_INSTANCE = new CodeGeneratorResponse();
         PARSER = new AbstractParser() {
            public CodeGeneratorResponse parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = PluginProtos.CodeGeneratorResponse.newBuilder();

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

      public static enum Feature implements ProtocolMessageEnum {
         FEATURE_NONE(0),
         FEATURE_PROTO3_OPTIONAL(1),
         FEATURE_SUPPORTS_EDITIONS(2);

         public static final int FEATURE_NONE_VALUE = 0;
         public static final int FEATURE_PROTO3_OPTIONAL_VALUE = 1;
         public static final int FEATURE_SUPPORTS_EDITIONS_VALUE = 2;
         private static final Internal.EnumLiteMap internalValueMap;
         private static final Feature[] VALUES;
         private final int value;

         public final int getNumber() {
            return this.value;
         }

         /** @deprecated */
         @Deprecated
         public static Feature valueOf(int value) {
            return forNumber(value);
         }

         public static Feature forNumber(int value) {
            switch (value) {
               case 0:
                  return FEATURE_NONE;
               case 1:
                  return FEATURE_PROTO3_OPTIONAL;
               case 2:
                  return FEATURE_SUPPORTS_EDITIONS;
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
            return (Descriptors.EnumDescriptor)PluginProtos.CodeGeneratorResponse.getDescriptor().getEnumTypes().get(0);
         }

         public static Feature valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
               throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else {
               return VALUES[desc.getIndex()];
            }
         }

         private Feature(int value) {
            this.value = value;
         }

         // $FF: synthetic method
         private static Feature[] $values() {
            return new Feature[]{FEATURE_NONE, FEATURE_PROTO3_OPTIONAL, FEATURE_SUPPORTS_EDITIONS};
         }

         static {
            RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Feature.class.getName());
            internalValueMap = new Internal.EnumLiteMap() {
               public Feature findValueByNumber(int number) {
                  return PluginProtos.CodeGeneratorResponse.Feature.forNumber(number);
               }
            };
            VALUES = values();
         }
      }

      public static final class File extends GeneratedMessage implements FileOrBuilder {
         private static final long serialVersionUID = 0L;
         private int bitField0_;
         public static final int NAME_FIELD_NUMBER = 1;
         private volatile Object name_;
         public static final int INSERTION_POINT_FIELD_NUMBER = 2;
         private volatile Object insertionPoint_;
         public static final int CONTENT_FIELD_NUMBER = 15;
         private volatile Object content_;
         public static final int GENERATED_CODE_INFO_FIELD_NUMBER = 16;
         private DescriptorProtos.GeneratedCodeInfo generatedCodeInfo_;
         private byte memoizedIsInitialized;
         private static final File DEFAULT_INSTANCE;
         private static final Parser PARSER;

         private File(GeneratedMessage.Builder builder) {
            super(builder);
            this.name_ = "";
            this.insertionPoint_ = "";
            this.content_ = "";
            this.memoizedIsInitialized = -1;
         }

         private File() {
            this.name_ = "";
            this.insertionPoint_ = "";
            this.content_ = "";
            this.memoizedIsInitialized = -1;
            this.name_ = "";
            this.insertionPoint_ = "";
            this.content_ = "";
         }

         public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
         }

         public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
         }

         public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
               return (String)ref;
            } else {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.name_ = s;
               }

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

         public boolean hasInsertionPoint() {
            return (this.bitField0_ & 2) != 0;
         }

         public String getInsertionPoint() {
            Object ref = this.insertionPoint_;
            if (ref instanceof String) {
               return (String)ref;
            } else {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.insertionPoint_ = s;
               }

               return s;
            }
         }

         public ByteString getInsertionPointBytes() {
            Object ref = this.insertionPoint_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.insertionPoint_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public boolean hasContent() {
            return (this.bitField0_ & 4) != 0;
         }

         public String getContent() {
            Object ref = this.content_;
            if (ref instanceof String) {
               return (String)ref;
            } else {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.content_ = s;
               }

               return s;
            }
         }

         public ByteString getContentBytes() {
            Object ref = this.content_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.content_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public boolean hasGeneratedCodeInfo() {
            return (this.bitField0_ & 8) != 0;
         }

         public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() {
            return this.generatedCodeInfo_ == null ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_;
         }

         public DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder() {
            return this.generatedCodeInfo_ == null ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_;
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
               GeneratedMessage.writeString(output, 1, this.name_);
            }

            if ((this.bitField0_ & 2) != 0) {
               GeneratedMessage.writeString(output, 2, this.insertionPoint_);
            }

            if ((this.bitField0_ & 4) != 0) {
               GeneratedMessage.writeString(output, 15, this.content_);
            }

            if ((this.bitField0_ & 8) != 0) {
               output.writeMessage(16, this.getGeneratedCodeInfo());
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
                  size += GeneratedMessage.computeStringSize(1, this.name_);
               }

               if ((this.bitField0_ & 2) != 0) {
                  size += GeneratedMessage.computeStringSize(2, this.insertionPoint_);
               }

               if ((this.bitField0_ & 4) != 0) {
                  size += GeneratedMessage.computeStringSize(15, this.content_);
               }

               if ((this.bitField0_ & 8) != 0) {
                  size += CodedOutputStream.computeMessageSize(16, this.getGeneratedCodeInfo());
               }

               size += this.getUnknownFields().getSerializedSize();
               this.memoizedSize = size;
               return size;
            }
         }

         public boolean equals(final Object obj) {
            if (obj == this) {
               return true;
            } else if (!(obj instanceof File)) {
               return super.equals(obj);
            } else {
               File other = (File)obj;
               if (this.hasName() != other.hasName()) {
                  return false;
               } else if (this.hasName() && !this.getName().equals(other.getName())) {
                  return false;
               } else if (this.hasInsertionPoint() != other.hasInsertionPoint()) {
                  return false;
               } else if (this.hasInsertionPoint() && !this.getInsertionPoint().equals(other.getInsertionPoint())) {
                  return false;
               } else if (this.hasContent() != other.hasContent()) {
                  return false;
               } else if (this.hasContent() && !this.getContent().equals(other.getContent())) {
                  return false;
               } else if (this.hasGeneratedCodeInfo() != other.hasGeneratedCodeInfo()) {
                  return false;
               } else if (this.hasGeneratedCodeInfo() && !this.getGeneratedCodeInfo().equals(other.getGeneratedCodeInfo())) {
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
               if (this.hasName()) {
                  hash = 37 * hash + 1;
                  hash = 53 * hash + this.getName().hashCode();
               }

               if (this.hasInsertionPoint()) {
                  hash = 37 * hash + 2;
                  hash = 53 * hash + this.getInsertionPoint().hashCode();
               }

               if (this.hasContent()) {
                  hash = 37 * hash + 15;
                  hash = 53 * hash + this.getContent().hashCode();
               }

               if (this.hasGeneratedCodeInfo()) {
                  hash = 37 * hash + 16;
                  hash = 53 * hash + this.getGeneratedCodeInfo().hashCode();
               }

               hash = 29 * hash + this.getUnknownFields().hashCode();
               this.memoizedHashCode = hash;
               return hash;
            }
         }

         public static File parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data);
         }

         public static File parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data, extensionRegistry);
         }

         public static File parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data);
         }

         public static File parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data, extensionRegistry);
         }

         public static File parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data);
         }

         public static File parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (File)PARSER.parseFrom(data, extensionRegistry);
         }

         public static File parseFrom(InputStream input) throws IOException {
            return (File)GeneratedMessage.parseWithIOException(PARSER, input);
         }

         public static File parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (File)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
         }

         public static File parseDelimitedFrom(InputStream input) throws IOException {
            return (File)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
         }

         public static File parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (File)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
         }

         public static File parseFrom(CodedInputStream input) throws IOException {
            return (File)GeneratedMessage.parseWithIOException(PARSER, input);
         }

         public static File parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (File)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
         }

         public Builder newBuilderForType() {
            return newBuilder();
         }

         public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
         }

         public static Builder newBuilder(File prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
         }

         public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
         }

         protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
         }

         public static File getDefaultInstance() {
            return DEFAULT_INSTANCE;
         }

         public static Parser parser() {
            return PARSER;
         }

         public Parser getParserForType() {
            return PARSER;
         }

         public File getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
         }

         static {
            RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", File.class.getName());
            DEFAULT_INSTANCE = new File();
            PARSER = new AbstractParser() {
               public File parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                  Builder builder = PluginProtos.CodeGeneratorResponse.File.newBuilder();

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

         public static final class Builder extends GeneratedMessage.Builder implements FileOrBuilder {
            private int bitField0_;
            private Object name_;
            private Object insertionPoint_;
            private Object content_;
            private DescriptorProtos.GeneratedCodeInfo generatedCodeInfo_;
            private SingleFieldBuilder generatedCodeInfoBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
               return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
            }

            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
               return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class);
            }

            private Builder() {
               this.name_ = "";
               this.insertionPoint_ = "";
               this.content_ = "";
               this.maybeForceBuilderInitialization();
            }

            private Builder(AbstractMessage.BuilderParent parent) {
               super(parent);
               this.name_ = "";
               this.insertionPoint_ = "";
               this.content_ = "";
               this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
               if (PluginProtos.CodeGeneratorResponse.File.alwaysUseFieldBuilders) {
                  this.getGeneratedCodeInfoFieldBuilder();
               }

            }

            public Builder clear() {
               super.clear();
               this.bitField0_ = 0;
               this.name_ = "";
               this.insertionPoint_ = "";
               this.content_ = "";
               this.generatedCodeInfo_ = null;
               if (this.generatedCodeInfoBuilder_ != null) {
                  this.generatedCodeInfoBuilder_.dispose();
                  this.generatedCodeInfoBuilder_ = null;
               }

               return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
               return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
            }

            public File getDefaultInstanceForType() {
               return PluginProtos.CodeGeneratorResponse.File.getDefaultInstance();
            }

            public File build() {
               File result = this.buildPartial();
               if (!result.isInitialized()) {
                  throw newUninitializedMessageException(result);
               } else {
                  return result;
               }
            }

            public File buildPartial() {
               File result = new File(this);
               if (this.bitField0_ != 0) {
                  this.buildPartial0(result);
               }

               this.onBuilt();
               return result;
            }

            private void buildPartial0(File result) {
               int from_bitField0_ = this.bitField0_;
               int to_bitField0_ = 0;
               if ((from_bitField0_ & 1) != 0) {
                  result.name_ = this.name_;
                  to_bitField0_ |= 1;
               }

               if ((from_bitField0_ & 2) != 0) {
                  result.insertionPoint_ = this.insertionPoint_;
                  to_bitField0_ |= 2;
               }

               if ((from_bitField0_ & 4) != 0) {
                  result.content_ = this.content_;
                  to_bitField0_ |= 4;
               }

               if ((from_bitField0_ & 8) != 0) {
                  result.generatedCodeInfo_ = this.generatedCodeInfoBuilder_ == null ? this.generatedCodeInfo_ : (DescriptorProtos.GeneratedCodeInfo)this.generatedCodeInfoBuilder_.build();
                  to_bitField0_ |= 8;
               }

               result.bitField0_ = to_bitField0_;
            }

            public Builder mergeFrom(Message other) {
               if (other instanceof File) {
                  return this.mergeFrom((File)other);
               } else {
                  super.mergeFrom(other);
                  return this;
               }
            }

            public Builder mergeFrom(File other) {
               if (other == PluginProtos.CodeGeneratorResponse.File.getDefaultInstance()) {
                  return this;
               } else {
                  if (other.hasName()) {
                     this.name_ = other.name_;
                     this.bitField0_ |= 1;
                     this.onChanged();
                  }

                  if (other.hasInsertionPoint()) {
                     this.insertionPoint_ = other.insertionPoint_;
                     this.bitField0_ |= 2;
                     this.onChanged();
                  }

                  if (other.hasContent()) {
                     this.content_ = other.content_;
                     this.bitField0_ |= 4;
                     this.onChanged();
                  }

                  if (other.hasGeneratedCodeInfo()) {
                     this.mergeGeneratedCodeInfo(other.getGeneratedCodeInfo());
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
                              this.name_ = input.readBytes();
                              this.bitField0_ |= 1;
                              break;
                           case 18:
                              this.insertionPoint_ = input.readBytes();
                              this.bitField0_ |= 2;
                              break;
                           case 122:
                              this.content_ = input.readBytes();
                              this.bitField0_ |= 4;
                              break;
                           case 130:
                              input.readMessage((MessageLite.Builder)this.getGeneratedCodeInfoFieldBuilder().getBuilder(), extensionRegistry);
                              this.bitField0_ |= 8;
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

            public boolean hasName() {
               return (this.bitField0_ & 1) != 0;
            }

            public String getName() {
               Object ref = this.name_;
               if (!(ref instanceof String)) {
                  ByteString bs = (ByteString)ref;
                  String s = bs.toStringUtf8();
                  if (bs.isValidUtf8()) {
                     this.name_ = s;
                  }

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
               this.name_ = PluginProtos.CodeGeneratorResponse.File.getDefaultInstance().getName();
               this.bitField0_ &= -2;
               this.onChanged();
               return this;
            }

            public Builder setNameBytes(ByteString value) {
               if (value == null) {
                  throw new NullPointerException();
               } else {
                  this.name_ = value;
                  this.bitField0_ |= 1;
                  this.onChanged();
                  return this;
               }
            }

            public boolean hasInsertionPoint() {
               return (this.bitField0_ & 2) != 0;
            }

            public String getInsertionPoint() {
               Object ref = this.insertionPoint_;
               if (!(ref instanceof String)) {
                  ByteString bs = (ByteString)ref;
                  String s = bs.toStringUtf8();
                  if (bs.isValidUtf8()) {
                     this.insertionPoint_ = s;
                  }

                  return s;
               } else {
                  return (String)ref;
               }
            }

            public ByteString getInsertionPointBytes() {
               Object ref = this.insertionPoint_;
               if (ref instanceof String) {
                  ByteString b = ByteString.copyFromUtf8((String)ref);
                  this.insertionPoint_ = b;
                  return b;
               } else {
                  return (ByteString)ref;
               }
            }

            public Builder setInsertionPoint(String value) {
               if (value == null) {
                  throw new NullPointerException();
               } else {
                  this.insertionPoint_ = value;
                  this.bitField0_ |= 2;
                  this.onChanged();
                  return this;
               }
            }

            public Builder clearInsertionPoint() {
               this.insertionPoint_ = PluginProtos.CodeGeneratorResponse.File.getDefaultInstance().getInsertionPoint();
               this.bitField0_ &= -3;
               this.onChanged();
               return this;
            }

            public Builder setInsertionPointBytes(ByteString value) {
               if (value == null) {
                  throw new NullPointerException();
               } else {
                  this.insertionPoint_ = value;
                  this.bitField0_ |= 2;
                  this.onChanged();
                  return this;
               }
            }

            public boolean hasContent() {
               return (this.bitField0_ & 4) != 0;
            }

            public String getContent() {
               Object ref = this.content_;
               if (!(ref instanceof String)) {
                  ByteString bs = (ByteString)ref;
                  String s = bs.toStringUtf8();
                  if (bs.isValidUtf8()) {
                     this.content_ = s;
                  }

                  return s;
               } else {
                  return (String)ref;
               }
            }

            public ByteString getContentBytes() {
               Object ref = this.content_;
               if (ref instanceof String) {
                  ByteString b = ByteString.copyFromUtf8((String)ref);
                  this.content_ = b;
                  return b;
               } else {
                  return (ByteString)ref;
               }
            }

            public Builder setContent(String value) {
               if (value == null) {
                  throw new NullPointerException();
               } else {
                  this.content_ = value;
                  this.bitField0_ |= 4;
                  this.onChanged();
                  return this;
               }
            }

            public Builder clearContent() {
               this.content_ = PluginProtos.CodeGeneratorResponse.File.getDefaultInstance().getContent();
               this.bitField0_ &= -5;
               this.onChanged();
               return this;
            }

            public Builder setContentBytes(ByteString value) {
               if (value == null) {
                  throw new NullPointerException();
               } else {
                  this.content_ = value;
                  this.bitField0_ |= 4;
                  this.onChanged();
                  return this;
               }
            }

            public boolean hasGeneratedCodeInfo() {
               return (this.bitField0_ & 8) != 0;
            }

            public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() {
               if (this.generatedCodeInfoBuilder_ == null) {
                  return this.generatedCodeInfo_ == null ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_;
               } else {
                  return (DescriptorProtos.GeneratedCodeInfo)this.generatedCodeInfoBuilder_.getMessage();
               }
            }

            public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
               if (this.generatedCodeInfoBuilder_ == null) {
                  if (value == null) {
                     throw new NullPointerException();
                  }

                  this.generatedCodeInfo_ = value;
               } else {
                  this.generatedCodeInfoBuilder_.setMessage(value);
               }

               this.bitField0_ |= 8;
               this.onChanged();
               return this;
            }

            public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo.Builder builderForValue) {
               if (this.generatedCodeInfoBuilder_ == null) {
                  this.generatedCodeInfo_ = builderForValue.build();
               } else {
                  this.generatedCodeInfoBuilder_.setMessage(builderForValue.build());
               }

               this.bitField0_ |= 8;
               this.onChanged();
               return this;
            }

            public Builder mergeGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) {
               if (this.generatedCodeInfoBuilder_ == null) {
                  if ((this.bitField0_ & 8) != 0 && this.generatedCodeInfo_ != null && this.generatedCodeInfo_ != DescriptorProtos.GeneratedCodeInfo.getDefaultInstance()) {
                     this.getGeneratedCodeInfoBuilder().mergeFrom(value);
                  } else {
                     this.generatedCodeInfo_ = value;
                  }
               } else {
                  this.generatedCodeInfoBuilder_.mergeFrom(value);
               }

               if (this.generatedCodeInfo_ != null) {
                  this.bitField0_ |= 8;
                  this.onChanged();
               }

               return this;
            }

            public Builder clearGeneratedCodeInfo() {
               this.bitField0_ &= -9;
               this.generatedCodeInfo_ = null;
               if (this.generatedCodeInfoBuilder_ != null) {
                  this.generatedCodeInfoBuilder_.dispose();
                  this.generatedCodeInfoBuilder_ = null;
               }

               this.onChanged();
               return this;
            }

            public DescriptorProtos.GeneratedCodeInfo.Builder getGeneratedCodeInfoBuilder() {
               this.bitField0_ |= 8;
               this.onChanged();
               return (DescriptorProtos.GeneratedCodeInfo.Builder)this.getGeneratedCodeInfoFieldBuilder().getBuilder();
            }

            public DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder() {
               if (this.generatedCodeInfoBuilder_ != null) {
                  return (DescriptorProtos.GeneratedCodeInfoOrBuilder)this.generatedCodeInfoBuilder_.getMessageOrBuilder();
               } else {
                  return this.generatedCodeInfo_ == null ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_;
               }
            }

            private SingleFieldBuilder getGeneratedCodeInfoFieldBuilder() {
               if (this.generatedCodeInfoBuilder_ == null) {
                  this.generatedCodeInfoBuilder_ = new SingleFieldBuilder(this.getGeneratedCodeInfo(), this.getParentForChildren(), this.isClean());
                  this.generatedCodeInfo_ = null;
               }

               return this.generatedCodeInfoBuilder_;
            }
         }
      }

      public static final class Builder extends GeneratedMessage.Builder implements CodeGeneratorResponseOrBuilder {
         private int bitField0_;
         private Object error_;
         private long supportedFeatures_;
         private int minimumEdition_;
         private int maximumEdition_;
         private List file_;
         private RepeatedFieldBuilder fileBuilder_;

         public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
         }

         private Builder() {
            this.error_ = "";
            this.file_ = Collections.emptyList();
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.error_ = "";
            this.file_ = Collections.emptyList();
         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.error_ = "";
            this.supportedFeatures_ = 0L;
            this.minimumEdition_ = 0;
            this.maximumEdition_ = 0;
            if (this.fileBuilder_ == null) {
               this.file_ = Collections.emptyList();
            } else {
               this.file_ = null;
               this.fileBuilder_.clear();
            }

            this.bitField0_ &= -17;
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
         }

         public CodeGeneratorResponse getDefaultInstanceForType() {
            return PluginProtos.CodeGeneratorResponse.getDefaultInstance();
         }

         public CodeGeneratorResponse build() {
            CodeGeneratorResponse result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public CodeGeneratorResponse buildPartial() {
            CodeGeneratorResponse result = new CodeGeneratorResponse(this);
            this.buildPartialRepeatedFields(result);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartialRepeatedFields(CodeGeneratorResponse result) {
            if (this.fileBuilder_ == null) {
               if ((this.bitField0_ & 16) != 0) {
                  this.file_ = Collections.unmodifiableList(this.file_);
                  this.bitField0_ &= -17;
               }

               result.file_ = this.file_;
            } else {
               result.file_ = this.fileBuilder_.build();
            }

         }

         private void buildPartial0(CodeGeneratorResponse result) {
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((from_bitField0_ & 1) != 0) {
               result.error_ = this.error_;
               to_bitField0_ |= 1;
            }

            if ((from_bitField0_ & 2) != 0) {
               result.supportedFeatures_ = this.supportedFeatures_;
               to_bitField0_ |= 2;
            }

            if ((from_bitField0_ & 4) != 0) {
               result.minimumEdition_ = this.minimumEdition_;
               to_bitField0_ |= 4;
            }

            if ((from_bitField0_ & 8) != 0) {
               result.maximumEdition_ = this.maximumEdition_;
               to_bitField0_ |= 8;
            }

            result.bitField0_ = to_bitField0_;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof CodeGeneratorResponse) {
               return this.mergeFrom((CodeGeneratorResponse)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(CodeGeneratorResponse other) {
            if (other == PluginProtos.CodeGeneratorResponse.getDefaultInstance()) {
               return this;
            } else {
               if (other.hasError()) {
                  this.error_ = other.error_;
                  this.bitField0_ |= 1;
                  this.onChanged();
               }

               if (other.hasSupportedFeatures()) {
                  this.setSupportedFeatures(other.getSupportedFeatures());
               }

               if (other.hasMinimumEdition()) {
                  this.setMinimumEdition(other.getMinimumEdition());
               }

               if (other.hasMaximumEdition()) {
                  this.setMaximumEdition(other.getMaximumEdition());
               }

               if (this.fileBuilder_ == null) {
                  if (!other.file_.isEmpty()) {
                     if (this.file_.isEmpty()) {
                        this.file_ = other.file_;
                        this.bitField0_ &= -17;
                     } else {
                        this.ensureFileIsMutable();
                        this.file_.addAll(other.file_);
                     }

                     this.onChanged();
                  }
               } else if (!other.file_.isEmpty()) {
                  if (this.fileBuilder_.isEmpty()) {
                     this.fileBuilder_.dispose();
                     this.fileBuilder_ = null;
                     this.file_ = other.file_;
                     this.bitField0_ &= -17;
                     this.fileBuilder_ = PluginProtos.CodeGeneratorResponse.alwaysUseFieldBuilders ? this.getFileFieldBuilder() : null;
                  } else {
                     this.fileBuilder_.addAllMessages(other.file_);
                  }
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
                           this.error_ = input.readBytes();
                           this.bitField0_ |= 1;
                           break;
                        case 16:
                           this.supportedFeatures_ = input.readUInt64();
                           this.bitField0_ |= 2;
                           break;
                        case 24:
                           this.minimumEdition_ = input.readInt32();
                           this.bitField0_ |= 4;
                           break;
                        case 32:
                           this.maximumEdition_ = input.readInt32();
                           this.bitField0_ |= 8;
                           break;
                        case 122:
                           File m = (File)input.readMessage(PluginProtos.CodeGeneratorResponse.File.parser(), extensionRegistry);
                           if (this.fileBuilder_ == null) {
                              this.ensureFileIsMutable();
                              this.file_.add(m);
                           } else {
                              this.fileBuilder_.addMessage(m);
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

         public boolean hasError() {
            return (this.bitField0_ & 1) != 0;
         }

         public String getError() {
            Object ref = this.error_;
            if (!(ref instanceof String)) {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               if (bs.isValidUtf8()) {
                  this.error_ = s;
               }

               return s;
            } else {
               return (String)ref;
            }
         }

         public ByteString getErrorBytes() {
            Object ref = this.error_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.error_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public Builder setError(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.error_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public Builder clearError() {
            this.error_ = PluginProtos.CodeGeneratorResponse.getDefaultInstance().getError();
            this.bitField0_ &= -2;
            this.onChanged();
            return this;
         }

         public Builder setErrorBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.error_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public boolean hasSupportedFeatures() {
            return (this.bitField0_ & 2) != 0;
         }

         public long getSupportedFeatures() {
            return this.supportedFeatures_;
         }

         public Builder setSupportedFeatures(long value) {
            this.supportedFeatures_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }

         public Builder clearSupportedFeatures() {
            this.bitField0_ &= -3;
            this.supportedFeatures_ = 0L;
            this.onChanged();
            return this;
         }

         public boolean hasMinimumEdition() {
            return (this.bitField0_ & 4) != 0;
         }

         public int getMinimumEdition() {
            return this.minimumEdition_;
         }

         public Builder setMinimumEdition(int value) {
            this.minimumEdition_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }

         public Builder clearMinimumEdition() {
            this.bitField0_ &= -5;
            this.minimumEdition_ = 0;
            this.onChanged();
            return this;
         }

         public boolean hasMaximumEdition() {
            return (this.bitField0_ & 8) != 0;
         }

         public int getMaximumEdition() {
            return this.maximumEdition_;
         }

         public Builder setMaximumEdition(int value) {
            this.maximumEdition_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }

         public Builder clearMaximumEdition() {
            this.bitField0_ &= -9;
            this.maximumEdition_ = 0;
            this.onChanged();
            return this;
         }

         private void ensureFileIsMutable() {
            if ((this.bitField0_ & 16) == 0) {
               this.file_ = new ArrayList(this.file_);
               this.bitField0_ |= 16;
            }

         }

         public List getFileList() {
            return this.fileBuilder_ == null ? Collections.unmodifiableList(this.file_) : this.fileBuilder_.getMessageList();
         }

         public int getFileCount() {
            return this.fileBuilder_ == null ? this.file_.size() : this.fileBuilder_.getCount();
         }

         public File getFile(int index) {
            return this.fileBuilder_ == null ? (File)this.file_.get(index) : (File)this.fileBuilder_.getMessage(index);
         }

         public Builder setFile(int index, File value) {
            if (this.fileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureFileIsMutable();
               this.file_.set(index, value);
               this.onChanged();
            } else {
               this.fileBuilder_.setMessage(index, value);
            }

            return this;
         }

         public Builder setFile(int index, File.Builder builderForValue) {
            if (this.fileBuilder_ == null) {
               this.ensureFileIsMutable();
               this.file_.set(index, builderForValue.build());
               this.onChanged();
            } else {
               this.fileBuilder_.setMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addFile(File value) {
            if (this.fileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureFileIsMutable();
               this.file_.add(value);
               this.onChanged();
            } else {
               this.fileBuilder_.addMessage(value);
            }

            return this;
         }

         public Builder addFile(int index, File value) {
            if (this.fileBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureFileIsMutable();
               this.file_.add(index, value);
               this.onChanged();
            } else {
               this.fileBuilder_.addMessage(index, value);
            }

            return this;
         }

         public Builder addFile(File.Builder builderForValue) {
            if (this.fileBuilder_ == null) {
               this.ensureFileIsMutable();
               this.file_.add(builderForValue.build());
               this.onChanged();
            } else {
               this.fileBuilder_.addMessage(builderForValue.build());
            }

            return this;
         }

         public Builder addFile(int index, File.Builder builderForValue) {
            if (this.fileBuilder_ == null) {
               this.ensureFileIsMutable();
               this.file_.add(index, builderForValue.build());
               this.onChanged();
            } else {
               this.fileBuilder_.addMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addAllFile(Iterable values) {
            if (this.fileBuilder_ == null) {
               this.ensureFileIsMutable();
               AbstractMessageLite.Builder.addAll(values, this.file_);
               this.onChanged();
            } else {
               this.fileBuilder_.addAllMessages(values);
            }

            return this;
         }

         public Builder clearFile() {
            if (this.fileBuilder_ == null) {
               this.file_ = Collections.emptyList();
               this.bitField0_ &= -17;
               this.onChanged();
            } else {
               this.fileBuilder_.clear();
            }

            return this;
         }

         public Builder removeFile(int index) {
            if (this.fileBuilder_ == null) {
               this.ensureFileIsMutable();
               this.file_.remove(index);
               this.onChanged();
            } else {
               this.fileBuilder_.remove(index);
            }

            return this;
         }

         public File.Builder getFileBuilder(int index) {
            return (File.Builder)this.getFileFieldBuilder().getBuilder(index);
         }

         public FileOrBuilder getFileOrBuilder(int index) {
            return this.fileBuilder_ == null ? (FileOrBuilder)this.file_.get(index) : (FileOrBuilder)this.fileBuilder_.getMessageOrBuilder(index);
         }

         public List getFileOrBuilderList() {
            return this.fileBuilder_ != null ? this.fileBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.file_);
         }

         public File.Builder addFileBuilder() {
            return (File.Builder)this.getFileFieldBuilder().addBuilder(PluginProtos.CodeGeneratorResponse.File.getDefaultInstance());
         }

         public File.Builder addFileBuilder(int index) {
            return (File.Builder)this.getFileFieldBuilder().addBuilder(index, PluginProtos.CodeGeneratorResponse.File.getDefaultInstance());
         }

         public List getFileBuilderList() {
            return this.getFileFieldBuilder().getBuilderList();
         }

         private RepeatedFieldBuilder getFileFieldBuilder() {
            if (this.fileBuilder_ == null) {
               this.fileBuilder_ = new RepeatedFieldBuilder(this.file_, (this.bitField0_ & 16) != 0, this.getParentForChildren(), this.isClean());
               this.file_ = null;
            }

            return this.fileBuilder_;
         }
      }

      public interface FileOrBuilder extends MessageOrBuilder {
         boolean hasName();

         String getName();

         ByteString getNameBytes();

         boolean hasInsertionPoint();

         String getInsertionPoint();

         ByteString getInsertionPointBytes();

         boolean hasContent();

         String getContent();

         ByteString getContentBytes();

         boolean hasGeneratedCodeInfo();

         DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo();

         DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder();
      }
   }

   public interface CodeGeneratorRequestOrBuilder extends MessageOrBuilder {
      List getFileToGenerateList();

      int getFileToGenerateCount();

      String getFileToGenerate(int index);

      ByteString getFileToGenerateBytes(int index);

      boolean hasParameter();

      String getParameter();

      ByteString getParameterBytes();

      List getProtoFileList();

      DescriptorProtos.FileDescriptorProto getProtoFile(int index);

      int getProtoFileCount();

      List getProtoFileOrBuilderList();

      DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index);

      List getSourceFileDescriptorsList();

      DescriptorProtos.FileDescriptorProto getSourceFileDescriptors(int index);

      int getSourceFileDescriptorsCount();

      List getSourceFileDescriptorsOrBuilderList();

      DescriptorProtos.FileDescriptorProtoOrBuilder getSourceFileDescriptorsOrBuilder(int index);

      boolean hasCompilerVersion();

      Version getCompilerVersion();

      VersionOrBuilder getCompilerVersionOrBuilder();
   }

   public interface CodeGeneratorResponseOrBuilder extends MessageOrBuilder {
      boolean hasError();

      String getError();

      ByteString getErrorBytes();

      boolean hasSupportedFeatures();

      long getSupportedFeatures();

      boolean hasMinimumEdition();

      int getMinimumEdition();

      boolean hasMaximumEdition();

      int getMaximumEdition();

      List getFileList();

      CodeGeneratorResponse.File getFile(int index);

      int getFileCount();

      List getFileOrBuilderList();

      CodeGeneratorResponse.FileOrBuilder getFileOrBuilder(int index);
   }

   public interface VersionOrBuilder extends MessageOrBuilder {
      boolean hasMajor();

      int getMajor();

      boolean hasMinor();

      int getMinor();

      boolean hasPatch();

      int getPatch();

      boolean hasSuffix();

      String getSuffix();

      ByteString getSuffixBytes();
   }
}
