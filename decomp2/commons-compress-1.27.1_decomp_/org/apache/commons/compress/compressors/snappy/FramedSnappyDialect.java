package org.apache.commons.compress.compressors.snappy;

public enum FramedSnappyDialect {
   STANDARD(true, true),
   IWORK_ARCHIVE(false, false);

   private final boolean streamIdentifier;
   private final boolean checksumWithCompressedChunks;

   private FramedSnappyDialect(boolean hasStreamIdentifier, boolean usesChecksumWithCompressedChunks) {
      this.streamIdentifier = hasStreamIdentifier;
      this.checksumWithCompressedChunks = usesChecksumWithCompressedChunks;
   }

   boolean hasStreamIdentifier() {
      return this.streamIdentifier;
   }

   boolean usesChecksumWithCompressedChunks() {
      return this.checksumWithCompressedChunks;
   }

   // $FF: synthetic method
   private static FramedSnappyDialect[] $values() {
      return new FramedSnappyDialect[]{STANDARD, IWORK_ARCHIVE};
   }
}
