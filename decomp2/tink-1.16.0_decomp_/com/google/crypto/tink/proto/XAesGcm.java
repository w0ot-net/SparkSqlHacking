package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class XAesGcm {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_XAesGcmParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_XAesGcmParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_XAesGcmKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_XAesGcmKeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_XAesGcmKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_XAesGcmKey_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private XAesGcm() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", XAesGcm.class.getName());
      String[] descriptorData = new String[]{"\n\u0015proto/x_aes_gcm.proto\u0012\u0012google.crypto.tink\"\"\n\rXAesGcmParams\u0012\u0011\n\tsalt_size\u0018\u0001 \u0001(\r\"\\\n\u0010XAesGcmKeyFormat\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00121\n\u0006params\u0018\u0003 \u0001(\u000b2!.google.crypto.tink.XAesGcmParamsJ\u0004\b\u0002\u0010\u0003\"c\n\nXAesGcmKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00121\n\u0006params\u0018\u0002 \u0001(\u000b2!.google.crypto.tink.XAesGcmParams\u0012\u0011\n\tkey_value\u0018\u0003 \u0001(\fB\\\n\u001ccom.google.crypto.tink.protoP\u0001Z:github.com/tink-crypto/tink-go/v2/proto/x_aes_gcm_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_XAesGcmParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_XAesGcmParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_XAesGcmParams_descriptor, new String[]{"SaltSize"});
      internal_static_google_crypto_tink_XAesGcmKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_XAesGcmKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_XAesGcmKeyFormat_descriptor, new String[]{"Version", "Params"});
      internal_static_google_crypto_tink_XAesGcmKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_XAesGcmKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_XAesGcmKey_descriptor, new String[]{"Version", "Params", "KeyValue"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
