package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class KmsAead {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_KmsAeadKeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_KmsAeadKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_KmsAeadKey_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private KmsAead() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KmsAead.class.getName());
      String[] descriptorData = new String[]{"\n\u0014proto/kms_aead.proto\u0012\u0012google.crypto.tink\"#\n\u0010KmsAeadKeyFormat\u0012\u000f\n\u0007key_uri\u0018\u0001 \u0001(\t\"S\n\nKmsAeadKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00124\n\u0006params\u0018\u0002 \u0001(\u000b2$.google.crypto.tink.KmsAeadKeyFormatB[\n\u001ccom.google.crypto.tink.protoP\u0001Z9github.com/tink-crypto/tink-go/v2/proto/kms_aead_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_KmsAeadKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_KmsAeadKeyFormat_descriptor, new String[]{"KeyUri"});
      internal_static_google_crypto_tink_KmsAeadKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_KmsAeadKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_KmsAeadKey_descriptor, new String[]{"Version", "Params"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
