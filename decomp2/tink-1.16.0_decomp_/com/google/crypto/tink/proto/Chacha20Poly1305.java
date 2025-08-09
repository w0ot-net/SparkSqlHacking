package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class Chacha20Poly1305 {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_ChaCha20Poly1305Key_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_ChaCha20Poly1305Key_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private Chacha20Poly1305() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", Chacha20Poly1305.class.getName());
      String[] descriptorData = new String[]{"\n\u001dproto/chacha20_poly1305.proto\u0012\u0012google.crypto.tink\"\u001b\n\u0019ChaCha20Poly1305KeyFormat\"9\n\u0013ChaCha20Poly1305Key\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012\u0011\n\tkey_value\u0018\u0002 \u0001(\fBd\n\u001ccom.google.crypto.tink.protoP\u0001ZBgithub.com/tink-crypto/tink-go/v2/proto/chacha20_poly1305_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
      internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_ChaCha20Poly1305KeyFormat_descriptor, new String[0]);
      internal_static_google_crypto_tink_ChaCha20Poly1305Key_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_ChaCha20Poly1305Key_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_ChaCha20Poly1305Key_descriptor, new String[]{"Version", "KeyValue"});
      descriptor.resolveAllFeaturesImmutable();
   }
}
