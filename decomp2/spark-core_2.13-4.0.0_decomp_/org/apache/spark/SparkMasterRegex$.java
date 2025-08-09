package org.apache.spark;

import scala.collection.StringOps.;
import scala.util.matching.Regex;

public final class SparkMasterRegex$ {
   public static final SparkMasterRegex$ MODULE$ = new SparkMasterRegex$();
   private static final Regex LOCAL_N_REGEX;
   private static final Regex LOCAL_N_FAILURES_REGEX;
   private static final Regex LOCAL_CLUSTER_REGEX;
   private static final Regex SPARK_REGEX;
   private static final Regex KUBERNETES_REGEX;

   static {
      LOCAL_N_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("local\\[([0-9]+|\\*)\\]"));
      LOCAL_N_FAILURES_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("local\\[([0-9]+|\\*)\\s*,\\s*([0-9]+)\\]"));
      LOCAL_CLUSTER_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("local-cluster\\[\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*]"));
      SPARK_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("spark://(.*)"));
      KUBERNETES_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("k8s://(.*)"));
   }

   public Regex LOCAL_N_REGEX() {
      return LOCAL_N_REGEX;
   }

   public Regex LOCAL_N_FAILURES_REGEX() {
      return LOCAL_N_FAILURES_REGEX;
   }

   public Regex LOCAL_CLUSTER_REGEX() {
      return LOCAL_CLUSTER_REGEX;
   }

   public Regex SPARK_REGEX() {
      return SPARK_REGEX;
   }

   public Regex KUBERNETES_REGEX() {
      return KUBERNETES_REGEX;
   }

   private SparkMasterRegex$() {
   }
}
