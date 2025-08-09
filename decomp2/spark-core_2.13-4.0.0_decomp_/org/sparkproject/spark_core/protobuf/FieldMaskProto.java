package org.sparkproject.spark_core.protobuf;

public final class FieldMaskProto {
   static final Descriptors.Descriptor internal_static_google_protobuf_FieldMask_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldMask_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private FieldMaskProto() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", FieldMaskProto.class.getName());
      String[] descriptorData = new String[]{"\n google/protobuf/field_mask.proto\u0012\u000fgoogle.protobuf\"!\n\tFieldMask\u0012\u0014\n\u0005paths\u0018\u0001 \u0003(\tR\u0005pathsB\u0085\u0001\n\u0013com.google.protobufB\u000eFieldMaskProtoP\u0001Z2google.golang.org/protobuf/types/known/fieldmaskpbø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"};
      descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_protobuf_FieldMask_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_protobuf_FieldMask_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FieldMask_descriptor, new String[]{"Paths"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
