package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class Hmac {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HmacParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HmacParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HmacKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HmacKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HmacKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HmacKeyFormat_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private Hmac() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", Hmac.class.getName());
      String[] descriptorData = new String[]{"\n\u0010proto/hmac.proto\u0012\u0012google.crypto.tink\u001a\u0012proto/common.proto\"J\n\nHmacParams\u0012*\n\u0004hash\u0018\u0001 \u0001(\u000e2\u001c.google.crypto.tink.HashType\u0012\u0010\n\btag_size\u0018\u0002 \u0001(\r\"]\n\u0007HmacKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012.\n\u0006params\u0018\u0002 \u0001(\u000b2\u001e.google.crypto.tink.HmacParams\u0012\u0011\n\tkey_value\u0018\u0003 \u0001(\f\"b\n\rHmacKeyFormat\u0012.\n\u0006params\u0018\u0001 \u0001(\u000b2\u001e.google.crypto.tink.HmacParams\u0012\u0010\n\bkey_size\u0018\u0002 \u0001(\r\u0012\u000f\n\u0007version\u0018\u0003 \u0001(\rBW\n\u001ccom.google.crypto.tink.protoP\u0001Z5github.com/tink-crypto/tink-go/v2/proto/hmac_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Common.getDescriptor()});
      internal_static_google_crypto_tink_HmacParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_HmacParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HmacParams_descriptor, new String[]{"Hash", "TagSize"});
      internal_static_google_crypto_tink_HmacKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_HmacKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HmacKey_descriptor, new String[]{"Version", "Params", "KeyValue"});
      internal_static_google_crypto_tink_HmacKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_HmacKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HmacKeyFormat_descriptor, new String[]{"Params", "KeySize", "Version"});
      descriptor.resolveAllFeaturesImmutable();
      Common.getDescriptor();
   }
}
