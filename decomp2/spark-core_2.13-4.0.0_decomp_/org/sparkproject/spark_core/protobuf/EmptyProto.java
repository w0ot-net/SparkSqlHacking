package org.sparkproject.spark_core.protobuf;

public final class EmptyProto {
   static final Descriptors.Descriptor internal_static_google_protobuf_Empty_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_Empty_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private EmptyProto() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", EmptyProto.class.getName());
      String[] descriptorData = new String[]{"\n\u001bgoogle/protobuf/empty.proto\u0012\u000fgoogle.protobuf\"\u0007\n\u0005EmptyB}\n\u0013com.google.protobufB\nEmptyProtoP\u0001Z.google.golang.org/protobuf/types/known/emptypbø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"};
      descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_protobuf_Empty_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_protobuf_Empty_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_Empty_descriptor, new String[0]);
      descriptor.resolveAllFeaturesImmutable();
   }
}
