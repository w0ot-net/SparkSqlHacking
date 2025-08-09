package org.apache.spark;

import java.io.InputStream;
import java.util.Properties;
import scala.MatchError;
import scala.Tuple7;

public final class SparkBuildInfo$ {
   public static final SparkBuildInfo$ MODULE$ = new SparkBuildInfo$();
   // $FF: synthetic field
   private static final Tuple7 x$1;
   private static final String spark_version;
   private static final String spark_branch;
   private static final String spark_revision;
   private static final String spark_build_user;
   private static final String spark_repo_url;
   private static final String spark_build_date;
   private static final String spark_doc_root;

   static {
      InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spark-version-info.properties");
      if (resourceStream == null) {
         throw new SparkException("Could not find spark-version-info.properties");
      } else {
         Tuple7 var1 = liftedTree1$1(resourceStream);
         if (var1 != null) {
            String spark_version = (String)var1._1();
            String spark_branch = (String)var1._2();
            String spark_revision = (String)var1._3();
            String spark_build_user = (String)var1._4();
            String spark_repo_url = (String)var1._5();
            String spark_build_date = (String)var1._6();
            String spark_doc_root = (String)var1._7();
            if (spark_version != null && spark_branch != null && spark_revision != null && spark_build_user != null && spark_repo_url != null && spark_build_date != null && spark_doc_root != null) {
               x$1 = new Tuple7(spark_version, spark_branch, spark_revision, spark_build_user, spark_repo_url, spark_build_date, spark_doc_root);
               spark_version = (String)x$1._1();
               spark_branch = (String)x$1._2();
               spark_revision = (String)x$1._3();
               spark_build_user = (String)x$1._4();
               spark_repo_url = (String)x$1._5();
               spark_build_date = (String)x$1._6();
               spark_doc_root = (String)x$1._7();
               return;
            }
         }

         throw new MatchError(var1);
      }
   }

   public String spark_version() {
      return spark_version;
   }

   public String spark_branch() {
      return spark_branch;
   }

   public String spark_revision() {
      return spark_revision;
   }

   public String spark_build_user() {
      return spark_build_user;
   }

   public String spark_repo_url() {
      return spark_repo_url;
   }

   public String spark_build_date() {
      return spark_build_date;
   }

   public String spark_doc_root() {
      return spark_doc_root;
   }

   // $FF: synthetic method
   private static final Tuple7 liftedTree1$1(final InputStream resourceStream$1) {
      boolean var11 = false;

      Tuple7 var10000;
      try {
         var11 = true;
         String unknownProp = "<unknown>";
         Properties props = new Properties();
         props.load(resourceStream$1);
         var10000 = new Tuple7(props.getProperty("version", unknownProp), props.getProperty("branch", unknownProp), props.getProperty("revision", unknownProp), props.getProperty("user", unknownProp), props.getProperty("url", unknownProp), props.getProperty("date", unknownProp), props.getProperty("docroot", unknownProp));
         var11 = false;
      } catch (Exception var14) {
         throw new SparkException("Error loading properties from spark-version-info.properties", var14);
      } finally {
         if (var11) {
            if (resourceStream$1 != null) {
               try {
                  resourceStream$1.close();
               } catch (Exception var12) {
                  throw new SparkException("Error closing spark build info resource stream", var12);
               }
            }

         }
      }

      Tuple7 var1 = var10000;
      if (resourceStream$1 != null) {
         try {
            resourceStream$1.close();
         } catch (Exception var13) {
            throw new SparkException("Error closing spark build info resource stream", var13);
         }
      }

      return var1;
   }

   private SparkBuildInfo$() {
   }
}
