package org.apache.hadoop.hive.common;

import java.util.regex.Pattern;

public class AcidConstants {
   public static final String BASE_PREFIX = "base_";
   public static final String DELTA_PREFIX = "delta_";
   public static final String DELETE_DELTA_PREFIX = "delete_delta_";
   public static final String BUCKET_DIGITS = "%05d";
   public static final String LEGACY_FILE_BUCKET_DIGITS = "%06d";
   public static final String DELTA_DIGITS = "%07d";
   public static final String STATEMENT_DIGITS = "%04d";
   public static final String VISIBILITY_PREFIX = "_v";
   public static final Pattern VISIBILITY_PATTERN = Pattern.compile("_v\\d+");

   public static String baseDir(long writeId) {
      return "base_" + String.format("%07d", writeId);
   }
}
