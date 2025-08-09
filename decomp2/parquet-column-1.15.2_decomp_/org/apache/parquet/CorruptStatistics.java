package org.apache.parquet;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.parquet.schema.PrimitiveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorruptStatistics {
   private static final AtomicBoolean alreadyLogged = new AtomicBoolean(false);
   private static final Logger LOG = LoggerFactory.getLogger(CorruptStatistics.class);
   private static final SemanticVersion PARQUET_251_FIXED_VERSION = new SemanticVersion(1, 8, 0);
   private static final SemanticVersion CDH_5_PARQUET_251_FIXED_START = new SemanticVersion(1, 5, 0, (String)null, "cdh5.5.0", (String)null);
   private static final SemanticVersion CDH_5_PARQUET_251_FIXED_END = new SemanticVersion(1, 5, 0);

   public static boolean shouldIgnoreStatistics(String createdBy, PrimitiveType.PrimitiveTypeName columnType) {
      if (columnType != PrimitiveType.PrimitiveTypeName.BINARY && columnType != PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
         return false;
      } else if (Strings.isNullOrEmpty(createdBy)) {
         warnOnce("Ignoring statistics because created_by is null or empty! See PARQUET-251 and PARQUET-297");
         return true;
      } else {
         try {
            VersionParser.ParsedVersion version = VersionParser.parse(createdBy);
            if (!"parquet-mr".equals(version.application)) {
               return false;
            } else if (Strings.isNullOrEmpty(version.version)) {
               warnOnce("Ignoring statistics because created_by did not contain a semver (see PARQUET-251): " + createdBy);
               return true;
            } else {
               SemanticVersion semver = SemanticVersion.parse(version.version);
               if (semver.compareTo(PARQUET_251_FIXED_VERSION) >= 0 || semver.compareTo(CDH_5_PARQUET_251_FIXED_START) >= 0 && semver.compareTo(CDH_5_PARQUET_251_FIXED_END) < 0) {
                  return false;
               } else {
                  warnOnce("Ignoring statistics because this file was created prior to " + PARQUET_251_FIXED_VERSION + ", see PARQUET-251");
                  return true;
               }
            }
         } catch (SemanticVersion.SemanticVersionParseException | VersionParser.VersionParseException | RuntimeException e) {
            warnParseErrorOnce(createdBy, e);
            return true;
         }
      }
   }

   private static void warnParseErrorOnce(String createdBy, Throwable e) {
      if (!alreadyLogged.getAndSet(true)) {
         LOG.warn("Ignoring statistics because created_by could not be parsed (see PARQUET-251): " + createdBy, e);
      }

   }

   private static void warnOnce(String message) {
      if (!alreadyLogged.getAndSet(true)) {
         LOG.warn(message);
      }

   }
}
