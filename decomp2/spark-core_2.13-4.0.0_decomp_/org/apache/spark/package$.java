package org.apache.spark;

import org.apache.spark.SparkBuildInfo.;
import org.apache.spark.util.VersionUtils$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final String SPARK_VERSION;
   private static final String SPARK_VERSION_SHORT;
   private static final String SPARK_BRANCH;
   private static final String SPARK_REVISION;
   private static final String SPARK_BUILD_USER;
   private static final String SPARK_REPO_URL;
   private static final String SPARK_BUILD_DATE;
   private static final String SPARK_DOC_ROOT;

   static {
      SPARK_VERSION = .MODULE$.spark_version();
      SPARK_VERSION_SHORT = VersionUtils$.MODULE$.shortVersion(.MODULE$.spark_version());
      SPARK_BRANCH = .MODULE$.spark_branch();
      SPARK_REVISION = .MODULE$.spark_revision();
      SPARK_BUILD_USER = .MODULE$.spark_build_user();
      SPARK_REPO_URL = .MODULE$.spark_repo_url();
      SPARK_BUILD_DATE = .MODULE$.spark_build_date();
      SPARK_DOC_ROOT = .MODULE$.spark_doc_root();
   }

   public String SPARK_VERSION() {
      return SPARK_VERSION;
   }

   public String SPARK_VERSION_SHORT() {
      return SPARK_VERSION_SHORT;
   }

   public String SPARK_BRANCH() {
      return SPARK_BRANCH;
   }

   public String SPARK_REVISION() {
      return SPARK_REVISION;
   }

   public String SPARK_BUILD_USER() {
      return SPARK_BUILD_USER;
   }

   public String SPARK_REPO_URL() {
      return SPARK_REPO_URL;
   }

   public String SPARK_BUILD_DATE() {
      return SPARK_BUILD_DATE;
   }

   public String SPARK_DOC_ROOT() {
      return SPARK_DOC_ROOT;
   }

   private package$() {
   }
}
