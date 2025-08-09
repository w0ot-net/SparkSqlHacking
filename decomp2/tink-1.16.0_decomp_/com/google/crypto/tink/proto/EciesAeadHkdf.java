package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class EciesAeadHkdf {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesHkdfKemParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesAeadDemParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesAeadDemParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesAeadHkdfParams_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesAeadHkdfParams_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private EciesAeadHkdf() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesAeadHkdf.class.getName());
      String[] descriptorData = new String[]{"\n\u001bproto/ecies_aead_hkdf.proto\u0012\u0012google.crypto.tink\u001a\u0012proto/common.proto\u001a\u0010proto/tink.proto\"\u0098\u0001\n\u0012EciesHkdfKemParams\u00129\n\ncurve_type\u0018\u0001 \u0001(\u000e2%.google.crypto.tink.EllipticCurveType\u00124\n\u000ehkdf_hash_type\u0018\u0002 \u0001(\u000e2\u001c.google.crypto.tink.HashType\u0012\u0011\n\thkdf_salt\u0018\u000b \u0001(\f\"G\n\u0012EciesAeadDemParams\u00121\n\baead_dem\u0018\u0002 \u0001(\u000b2\u001f.google.crypto.tink.KeyTemplate\"É\u0001\n\u0013EciesAeadHkdfParams\u0012:\n\nkem_params\u0018\u0001 \u0001(\u000b2&.google.crypto.tink.EciesHkdfKemParams\u0012:\n\ndem_params\u0018\u0002 \u0001(\u000b2&.google.crypto.tink.EciesAeadDemParams\u0012:\n\u000fec_point_format\u0018\u0003 \u0001(\u000e2!.google.crypto.tink.EcPointFormat\"x\n\u0016EciesAeadHkdfPublicKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00127\n\u0006params\u0018\u0002 \u0001(\u000b2'.google.crypto.tink.EciesAeadHkdfParams\u0012\t\n\u0001x\u0018\u0003 \u0001(\f\u0012\t\n\u0001y\u0018\u0004 \u0001(\f\"}\n\u0017EciesAeadHkdfPrivateKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012>\n\npublic_key\u0018\u0002 \u0001(\u000b2*.google.crypto.tink.EciesAeadHkdfPublicKey\u0012\u0011\n\tkey_value\u0018\u0003 \u0001(\f\"Q\n\u0016EciesAeadHkdfKeyFormat\u00127\n\u0006params\u0018\u0001 \u0001(\u000b2'.google.crypto.tink.EciesAeadHkdfParamsBb\n\u001ccom.google.crypto.tink.protoP\u0001Z@github.com/tink-crypto/tink-go/v2/proto/ecies_aead_hkdf_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Common.getDescriptor(), Tink.getDescriptor()});
      internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_EciesHkdfKemParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesHkdfKemParams_descriptor, new String[]{"CurveType", "HkdfHashType", "HkdfSalt"});
      internal_static_google_crypto_tink_EciesAeadDemParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_EciesAeadDemParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesAeadDemParams_descriptor, new String[]{"AeadDem"});
      internal_static_google_crypto_tink_EciesAeadHkdfParams_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_EciesAeadHkdfParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesAeadHkdfParams_descriptor, new String[]{"KemParams", "DemParams", "EcPointFormat"});
      internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(3);
      internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesAeadHkdfPublicKey_descriptor, new String[]{"Version", "Params", "X", "Y"});
      internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(4);
      internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesAeadHkdfPrivateKey_descriptor, new String[]{"Version", "PublicKey", "KeyValue"});
      internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(5);
      internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor, new String[]{"Params"});
      descriptor.resolveAllFeaturesImmutable();
      Common.getDescriptor();
      Tink.getDescriptor();
   }
}
