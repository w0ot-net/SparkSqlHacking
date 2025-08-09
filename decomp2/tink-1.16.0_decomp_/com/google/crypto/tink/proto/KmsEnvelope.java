package com.google.crypto.tink.proto;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;

public final class KmsEnvelope {
   static final Descriptors.Descriptor internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_fieldAccessorTable;
   static final Descriptors.Descriptor internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor;
   static final GeneratedMessage.FieldAccessorTable internal_static_google_crypto_tink_KmsEnvelopeAeadKey_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private KmsEnvelope() {
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
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KmsEnvelope.class.getName());
      String[] descriptorData = new String[]{"\n\u0018proto/kms_envelope.proto\u0012\u0012google.crypto.tink\u001a\u0010proto/tink.proto\"b\n\u0018KmsEnvelopeAeadKeyFormat\u0012\u000f\n\u0007kek_uri\u0018\u0001 \u0001(\t\u00125\n\fdek_template\u0018\u0002 \u0001(\u000b2\u001f.google.crypto.tink.KeyTemplate\"c\n\u0012KmsEnvelopeAeadKey\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\r\u0012<\n\u0006params\u0018\u0002 \u0001(\u000b2,.google.crypto.tink.KmsEnvelopeAeadKeyFormatB_\n\u001ccom.google.crypto.tink.protoP\u0001Z=github.com/tink-crypto/tink-go/v2/proto/kms_envelope_go_protob\u0006proto3"};
      descriptor = FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Tink.getDescriptor()});
      internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(0);
      internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_KmsEnvelopeAeadKeyFormat_descriptor, new String[]{"KekUri", "DekTemplate"});
      internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor = (Descriptors.Descriptor)getDescriptor().getMessageTypes().get(1);
      internal_static_google_crypto_tink_KmsEnvelopeAeadKey_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor, new String[]{"Version", "Params"});
      descriptor.resolveAllFeaturesImmutable();
      Tink.getDescriptor();
   }
}
