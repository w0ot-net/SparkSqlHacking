package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class AesSiv {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_AesSivKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_AesSivKeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_AesSivKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_AesSivKey_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private AesSiv() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesSiv.class.getName());
      String[] descriptorData = new String[]{"\n\u0013proto/aes_siv.proto\u0012\u0012google.crypto.tink\"4\n\u000fAesSivKeyFormat\u0012\u0010\n\bkey_size\u0018\u0001 \u0001(\r\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\r\"/\n\tAesSivKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012\u0011\n\tkey_value\u0018\u0002 \u0001(\fBZ\n\u001ccom.google.crypto.tink.protoP\u0001Z8github.com/tink-crypto/tink-go/v2/proto/aes_siv_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_AesSivKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_AesSivKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_AesSivKeyFormat_descriptor, new String[]{"KeySize", "Version"});
      internal_static_google_crypto_tink_AesSivKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_AesSivKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_AesSivKey_descriptor, new String[]{"Version", "KeyValue"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
