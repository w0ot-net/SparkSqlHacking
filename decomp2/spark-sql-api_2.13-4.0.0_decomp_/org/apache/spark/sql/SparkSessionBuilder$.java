package org.apache.spark.sql;

public final class SparkSessionBuilder$ {
   public static final SparkSessionBuilder$ MODULE$ = new SparkSessionBuilder$();
   private static final String MASTER_KEY = "spark.master";
   private static final String APP_NAME_KEY = "spark.app.name";
   private static final String CATALOG_IMPL_KEY = "spark.sql.catalogImplementation";
   private static final String CONNECT_REMOTE_KEY = "spark.connect.remote";
   private static final String API_MODE_KEY = "spark.api.mode";
   private static final String API_MODE_CLASSIC = "classic";
   private static final String API_MODE_CONNECT = "connect";

   public String MASTER_KEY() {
      return MASTER_KEY;
   }

   public String APP_NAME_KEY() {
      return APP_NAME_KEY;
   }

   public String CATALOG_IMPL_KEY() {
      return CATALOG_IMPL_KEY;
   }

   public String CONNECT_REMOTE_KEY() {
      return CONNECT_REMOTE_KEY;
   }

   public String API_MODE_KEY() {
      return API_MODE_KEY;
   }

   public String API_MODE_CLASSIC() {
      return API_MODE_CLASSIC;
   }

   public String API_MODE_CONNECT() {
      return API_MODE_CONNECT;
   }

   private SparkSessionBuilder$() {
   }
}
