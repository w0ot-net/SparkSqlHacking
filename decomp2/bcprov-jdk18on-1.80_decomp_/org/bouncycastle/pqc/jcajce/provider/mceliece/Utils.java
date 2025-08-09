package org.bouncycastle.pqc.jcajce.provider.mceliece;

import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;

class Utils {
   static Digest getDigest(AlgorithmIdentifier var0) {
      if (var0.getAlgorithm().equals(OIWObjectIdentifiers.idSHA1)) {
         return DigestFactory.createSHA1();
      } else if (var0.getAlgorithm().equals(NISTObjectIdentifiers.id_sha224)) {
         return DigestFactory.createSHA224();
      } else if (var0.getAlgorithm().equals(NISTObjectIdentifiers.id_sha256)) {
         return DigestFactory.createSHA256();
      } else if (var0.getAlgorithm().equals(NISTObjectIdentifiers.id_sha384)) {
         return DigestFactory.createSHA384();
      } else if (var0.getAlgorithm().equals(NISTObjectIdentifiers.id_sha512)) {
         return DigestFactory.createSHA512();
      } else {
         throw new IllegalArgumentException("unrecognised OID in digest algorithm identifier: " + var0.getAlgorithm());
      }
   }
}
