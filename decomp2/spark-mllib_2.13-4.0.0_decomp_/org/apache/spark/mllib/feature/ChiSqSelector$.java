package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ChiSqSelector$ implements Serializable {
   public static final ChiSqSelector$ MODULE$ = new ChiSqSelector$();
   private static final String NumTopFeatures = "numTopFeatures";
   private static final String Percentile = "percentile";
   private static final String FPR = "fpr";
   private static final String FDR = "fdr";
   private static final String FWE = "fwe";
   private static final String[] supportedSelectorTypes;

   static {
      supportedSelectorTypes = (String[])((Object[])(new String[]{MODULE$.NumTopFeatures(), MODULE$.Percentile(), MODULE$.FPR(), MODULE$.FDR(), MODULE$.FWE()}));
   }

   public String NumTopFeatures() {
      return NumTopFeatures;
   }

   public String Percentile() {
      return Percentile;
   }

   public String FPR() {
      return FPR;
   }

   public String FDR() {
      return FDR;
   }

   public String FWE() {
      return FWE;
   }

   public String[] supportedSelectorTypes() {
      return supportedSelectorTypes;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChiSqSelector$.class);
   }

   private ChiSqSelector$() {
   }
}
