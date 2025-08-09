package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class JwtEcdsa {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtEcdsaPublicKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtEcdsaPublicKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtEcdsaPublicKey_CustomKid_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtEcdsaPublicKey_CustomKid_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtEcdsaPrivateKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtEcdsaPrivateKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtEcdsaKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtEcdsaKeyFormat_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private JwtEcdsa() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtEcdsa.class.getName());
      String[] descriptorData = new String[]{"\n\u0015proto/jwt_ecdsa.proto\u0012\u0012google.crypto.tink\"Õ\u0001\n\u0011JwtEcdsaPublicKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00128\n\talgorithm\u0018\u0002 \u0001(\u000e2%.google.crypto.tink.JwtEcdsaAlgorithm\u0012\t\n\u0001x\u0018\u0003 \u0001(\f\u0012\t\n\u0001y\u0018\u0004 \u0001(\f\u0012C\n\ncustom_kid\u0018\u0005 \u0001(\u000b2/.google.crypto.tink.JwtEcdsaPublicKey.CustomKid\u001a\u001a\n\tCustomKid\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\"s\n\u0012JwtEcdsaPrivateKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00129\n\npublic_key\u0018\u0002 \u0001(\u000b2%.google.crypto.tink.JwtEcdsaPublicKey\u0012\u0011\n\tkey_value\u0018\u0003 \u0001(\f\"^\n\u0011JwtEcdsaKeyFormat\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00128\n\talgorithm\u0018\u0002 \u0001(\u000e2%.google.crypto.tink.JwtEcdsaAlgorithm*D\n\u0011JwtEcdsaAlgorithm\u0012\u000e\n\nES_UNKNOWN\u0010\u0000\u0012\t\n\u0005ES256\u0010\u0001\u0012\t\n\u0005ES384\u0010\u0002\u0012\t\n\u0005ES512\u0010\u0003B\\\n\u001ccom.google.crypto.tink.protoP\u0001Z:github.com/tink-crypto/tink-go/v2/proto/jwt_ecdsa_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_JwtEcdsaPublicKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_JwtEcdsaPublicKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtEcdsaPublicKey_descriptor, new String[]{"Version", "Algorithm", "X", "Y", "CustomKid"});
      internal_static_google_crypto_tink_JwtEcdsaPublicKey_CustomKid_descriptor = (Descriptors.Descriptor)internal_static_google_crypto_tink_JwtEcdsaPublicKey_descriptor.getNestedTypes().get(0);
      internal_static_google_crypto_tink_JwtEcdsaPublicKey_CustomKid_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtEcdsaPublicKey_CustomKid_descriptor, new String[]{"Value"});
      internal_static_google_crypto_tink_JwtEcdsaPrivateKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_JwtEcdsaPrivateKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtEcdsaPrivateKey_descriptor, new String[]{"Version", "PublicKey", "KeyValue"});
      internal_static_google_crypto_tink_JwtEcdsaKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_JwtEcdsaKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtEcdsaKeyFormat_descriptor, new String[]{"Version", "Algorithm"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
