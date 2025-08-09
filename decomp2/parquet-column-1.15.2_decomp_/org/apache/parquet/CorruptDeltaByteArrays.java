package org.apache.parquet;

import org.apache.parquet.column.Encoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorruptDeltaByteArrays {
   private static final Logger LOG = LoggerFactory.getLogger(CorruptStatistics.class);
   private static final SemanticVersion PARQUET_246_FIXED_VERSION = new SemanticVersion(1, 8, 0);

   public static boolean requiresSequentialReads(VersionParser.ParsedVersion version, Encoding encoding) {
      if (encoding != Encoding.DELTA_BYTE_ARRAY) {
         return false;
      } else if (version == null) {
         return true;
      } else if (!"parquet-mr".equals(version.application)) {
         return false;
      } else if (!version.hasSemanticVersion()) {
         LOG.warn("Requiring sequential reads because created_by did not contain a valid version (see PARQUET-246): {}", version.version);
         return true;
      } else {
         return requiresSequentialReads(version.getSemanticVersion(), encoding);
      }
   }

   public static boolean requiresSequentialReads(SemanticVersion semver, Encoding encoding) {
      if (encoding != Encoding.DELTA_BYTE_ARRAY) {
         return false;
      } else if (semver == null) {
         return true;
      } else if (semver.compareTo(PARQUET_246_FIXED_VERSION) < 0) {
         LOG.info("Requiring sequential reads because this file was created prior to {}. See PARQUET-246", PARQUET_246_FIXED_VERSION);
         return true;
      } else {
         return false;
      }
   }

   public static boolean requiresSequentialReads(String createdBy, Encoding encoding) {
      if (encoding != Encoding.DELTA_BYTE_ARRAY) {
         return false;
      } else if (Strings.isNullOrEmpty(createdBy)) {
         LOG.info("Requiring sequential reads because file version is empty. See PARQUET-246");
         return true;
      } else {
         try {
            return requiresSequentialReads(VersionParser.parse(createdBy), encoding);
         } catch (VersionParser.VersionParseException | RuntimeException e) {
            warnParseError(createdBy, e);
            return true;
         }
      }
   }

   private static void warnParseError(String createdBy, Throwable e) {
      LOG.warn("Requiring sequential reads because created_by could not be parsed (see PARQUET-246): " + createdBy, e);
   }
}
