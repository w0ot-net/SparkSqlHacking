package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class AesCtrHmacAead {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_AesCtrHmacAeadKey_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private AesCtrHmacAead() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesCtrHmacAead.class.getName());
      String[] descriptorData = new String[]{"\n\u001dproto/aes_ctr_hmac_aead.proto\u0012\u0012google.crypto.tink\u001a\u0013proto/aes_ctr.proto\u001a\u0010proto/hmac.proto\"\u0096\u0001\n\u0017AesCtrHmacAeadKeyFormat\u0012?\n\u0012aes_ctr_key_format\u0018\u0001 \u0001(\u000b2#.google.crypto.tink.AesCtrKeyFormat\u0012:\n\u000fhmac_key_format\u0018\u0002 \u0001(\u000b2!.google.crypto.tink.HmacKeyFormat\"\u0087\u0001\n\u0011AesCtrHmacAeadKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u00122\n\u000baes_ctr_key\u0018\u0002 \u0001(\u000b2\u001d.google.crypto.tink.AesCtrKey\u0012-\n\bhmac_key\u0018\u0003 \u0001(\u000b2\u001b.google.crypto.tink.HmacKeyBd\n\u001ccom.google.crypto.tink.protoP\u0001ZBgithub.com/tink-crypto/tink-go/v2/proto/aes_ctr_hmac_aead_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AesCtr.getDescriptor(), Hmac.getDescriptor()});
      internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_AesCtrHmacAeadKeyFormat_descriptor, new String[]{"AesCtrKeyFormat", "HmacKeyFormat"});
      internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_AesCtrHmacAeadKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_AesCtrHmacAeadKey_descriptor, new String[]{"Version", "AesCtrKey", "HmacKey"});
      descriptor.resolveAllFeaturesImmutable();
      AesCtr.getDescriptor();
      Hmac.getDescriptor();
   }
}
