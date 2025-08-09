package org.apache.spark.sql.catalyst.util;

public final class ResolveDefaultColumnsUtils$ implements ResolveDefaultColumnsUtils {
   public static final ResolveDefaultColumnsUtils$ MODULE$ = new ResolveDefaultColumnsUtils$();
   private static String CURRENT_DEFAULT_COLUMN_METADATA_KEY;
   private static String EXISTS_DEFAULT_COLUMN_METADATA_KEY;

   static {
      ResolveDefaultColumnsUtils.$init$(MODULE$);
   }

   public String CURRENT_DEFAULT_COLUMN_METADATA_KEY() {
      return CURRENT_DEFAULT_COLUMN_METADATA_KEY;
   }

   public String EXISTS_DEFAULT_COLUMN_METADATA_KEY() {
      return EXISTS_DEFAULT_COLUMN_METADATA_KEY;
   }

   public void org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$CURRENT_DEFAULT_COLUMN_METADATA_KEY_$eq(final String x$1) {
      CURRENT_DEFAULT_COLUMN_METADATA_KEY = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$ResolveDefaultColumnsUtils$_setter_$EXISTS_DEFAULT_COLUMN_METADATA_KEY_$eq(final String x$1) {
      EXISTS_DEFAULT_COLUMN_METADATA_KEY = x$1;
   }

   private ResolveDefaultColumnsUtils$() {
   }
}
