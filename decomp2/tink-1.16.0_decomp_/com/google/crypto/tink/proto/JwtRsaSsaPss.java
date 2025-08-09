package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class JwtRsaSsaPss {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_CustomKid_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_CustomKid_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_JwtRsaSsaPssKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_JwtRsaSsaPssKeyFormat_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private JwtRsaSsaPss() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtRsaSsaPss.class.getName());
      String[] descriptorData = new String[]{"\n\u001bproto/jwt_rsa_ssa_pss.proto\u0012\u0012google.crypto.tink\"á\u0001\n\u0015JwtRsaSsaPssPublicKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012<\n\talgorithm\u0018\u0002 \u0001(\u000e2).google.crypto.tink.JwtRsaSsaPssAlgorithm\u0012\t\n\u0001n\u0018\u0003 \u0001(\f\u0012\t\n\u0001e\u0018\u0004 \u0001(\f\u0012G\n\ncustom_kid\u0018\u0005 \u0001(\u000b23.google.crypto.tink.JwtRsaSsaPssPublicKey.CustomKid\u001a\u001a\n\tCustomKid\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\"®\u0001\n\u0016JwtRsaSsaPssPrivateKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012=\n\npublic_key\u0018\u0002 \u0001(\u000b2).google.crypto.tink.JwtRsaSsaPssPublicKey\u0012\t\n\u0001d\u0018\u0003 \u0001(\f\u0012\t\n\u0001p\u0018\u0004 \u0001(\f\u0012\t\n\u0001q\u0018\u0005 \u0001(\f\u0012\n\n\u0002dp\u0018\u0006 \u0001(\f\u0012\n\n\u0002dq\u0018\u0007 \u0001(\f\u0012\u000b\n\u0003crt\u0018\b \u0001(\f\"\u009d\u0001\n\u0015JwtRsaSsaPssKeyFormat\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012<\n\talgorithm\u0018\u0002 \u0001(\u000e2).google.crypto.tink.JwtRsaSsaPssAlgorithm\u0012\u001c\n\u0014modulus_size_in_bits\u0018\u0003 \u0001(\r\u0012\u0017\n\u000fpublic_exponent\u0018\u0004 \u0001(\f*H\n\u0015JwtRsaSsaPssAlgorithm\u0012\u000e\n\nPS_UNKNOWN\u0010\u0000\u0012\t\n\u0005PS256\u0010\u0001\u0012\t\n\u0005PS384\u0010\u0002\u0012\t\n\u0005PS512\u0010\u0003Bb\n\u001ccom.google.crypto.tink.protoP\u0001Z@github.com/tink-crypto/tink-go/v2/proto/jwt_rsa_ssa_pss_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_descriptor, new String[]{"Version", "Algorithm", "N", "E", "CustomKid"});
      internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_CustomKid_descriptor = (Descriptors.Descriptor)internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_descriptor.getNestedTypes().get(0);
      internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_CustomKid_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtRsaSsaPssPublicKey_CustomKid_descriptor, new String[]{"Value"});
      internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor, new String[]{"Version", "PublicKey", "D", "P", "Q", "Dp", "Dq", "Crt"});
      internal_static_google_crypto_tink_JwtRsaSsaPssKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(2);
      internal_static_google_crypto_tink_JwtRsaSsaPssKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_JwtRsaSsaPssKeyFormat_descriptor, new String[]{"Version", "Algorithm", "ModulusSizeInBits", "PublicExponent"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
