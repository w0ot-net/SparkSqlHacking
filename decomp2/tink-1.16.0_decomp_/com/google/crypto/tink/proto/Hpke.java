package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class Hpke {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HpkeParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HpkeParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HpkePublicKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HpkePublicKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HpkePrivateKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HpkePrivateKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_HpkeKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_HpkeKeyFormat_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private Hpke() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", Hpke.class.getName());
      String[] descriptorData = new String[]{"\n\u0010proto/hpke.proto\u0012\u0012google.crypto.tink\"\u008c\u0001\n\nHpkeParams\u0012(\n\u0003kem\u0018\u0001 \u0001(\u000e2\u001b.google.crypto.tink.HpkeKem\u0012(\n\u0003kdf\u0018\u0002 \u0001(\u000e2\u001b.google.crypto.tink.HpkeKdf\u0012*\n\u0004aead\u0018\u0003 \u0001(\u000e2\u001c.google.crypto.tink.HpkeAead\"d\n\rHpkePublicKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012.\n\u0006params\u0018\u0002 \u0001(\u000b2\u001e.google.crypto.tink.HpkeParams\u0012\u0012\n\npublic_key\u0018\u0003 \u0001(\f\"m\n\u000eHpkePrivateKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00125\n\npublic_key\u0018\u0002 \u0001(\u000b2!.google.crypto.tink.HpkePublicKey\u0012\u0013\n\u000bprivate_key\u0018\u0003 \u0001(\f\"?\n\rHpkeKeyFormat\u0012.\n\u0006params\u0018\u0001 \u0001(\u000b2\u001e.google.crypto.tink.HpkeParams*\u008c\u0001\n\u0007HpkeKem\u0012\u000f\n\u000bKEM_UNKNOWN\u0010\u0000\u0012\u001c\n\u0018DHKEM_X25519_HKDF_SHA256\u0010\u0001\u0012\u001a\n\u0016DHKEM_P256_HKDF_SHA256\u0010\u0002\u0012\u001a\n\u0016DHKEM_P384_HKDF_SHA384\u0010\u0003\u0012\u001a\n\u0016DHKEM_P521_HKDF_SHA512\u0010\u0004*M\n\u0007HpkeKdf\u0012\u000f\n\u000bKDF_UNKNOWN\u0010\u0000\u0012\u000f\n\u000bHKDF_SHA256\u0010\u0001\u0012\u000f\n\u000bHKDF_SHA384\u0010\u0002\u0012\u000f\n\u000bHKDF_SHA512\u0010\u0003*U\n\bHpkeAead\u0012\u0010\n\fAEAD_UNKNOWN\u0010\u0000\u0012\u000f\n\u000bAES_128_GCM\u0010\u0001\u0012\u000f\n\u000bAES_256_GCM\u0010\u0002\u0012\u0015\n\u0011CHACHA20_POLY1305\u0010\u0003BT\n\u001ccom.google.crypto.tink.protoP\u0001Z2github.com/tink-crypto/tink-go/v2/proto/hpke_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_HpkeParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_HpkeParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HpkeParams_descriptor, new String[]{"Kem", "Kdf", "Aead"});
      internal_static_google_crypto_tink_HpkePublicKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_HpkePublicKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HpkePublicKey_descriptor, new String[]{"Version", "Params", "PublicKey"});
      internal_static_google_crypto_tink_HpkePrivateKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_HpkePrivateKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HpkePrivateKey_descriptor, new String[]{"Version", "PublicKey", "PrivateKey"});
      internal_static_google_crypto_tink_HpkeKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(3);
      internal_static_google_crypto_tink_HpkeKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_HpkeKeyFormat_descriptor, new String[]{"Params"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
