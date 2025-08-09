package org.bouncycastle.pqc.crypto.rainbow;

enum Version {
   CLASSIC,
   CIRCUMZENITHAL,
   COMPRESSED;

   // $FF: synthetic method
   private static Version[] $values() {
      return new Version[]{CLASSIC, CIRCUMZENITHAL, COMPRESSED};
   }
}
