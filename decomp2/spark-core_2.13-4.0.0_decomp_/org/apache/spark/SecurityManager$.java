package org.apache.spark;

import org.apache.hadoop.io.Text;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Option;
import scala.None.;

public final class SecurityManager$ {
   public static final SecurityManager$ MODULE$ = new SecurityManager$();
   private static final String SPARK_AUTH_CONF;
   private static final String SPARK_AUTH_SECRET_CONF;
   private static final String ENV_AUTH_SECRET;
   private static final Text SECRET_LOOKUP_KEY;

   static {
      SPARK_AUTH_CONF = org.apache.spark.internal.config.package$.MODULE$.NETWORK_AUTH_ENABLED().key();
      SPARK_AUTH_SECRET_CONF = org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET().key();
      ENV_AUTH_SECRET = "_SPARK_AUTH_SECRET";
      SECRET_LOOKUP_KEY = new Text("sparkCookie");
   }

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public ConfigEntry $lessinit$greater$default$3() {
      return org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_FILE();
   }

   public String SPARK_AUTH_CONF() {
      return SPARK_AUTH_CONF;
   }

   public String SPARK_AUTH_SECRET_CONF() {
      return SPARK_AUTH_SECRET_CONF;
   }

   public String ENV_AUTH_SECRET() {
      return ENV_AUTH_SECRET;
   }

   public Text SECRET_LOOKUP_KEY() {
      return SECRET_LOOKUP_KEY;
   }

   private SecurityManager$() {
   }
}
