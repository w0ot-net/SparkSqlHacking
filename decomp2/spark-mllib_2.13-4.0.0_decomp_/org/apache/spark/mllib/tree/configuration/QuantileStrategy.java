package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;Qa\u0003\u0007\t\u0002e1Qa\u0007\u0007\t\u0002qAQaI\u0001\u0005\u0002\u0011*AaG\u0001\u0001K!9!'\u0001b\u0001\n\u0003\u0019\u0004BB\u001b\u0002A\u0003%Q\u0005C\u00048\u0003\t\u0007I\u0011A\u001a\t\re\n\u0001\u0015!\u0003&\u0011\u001dY\u0014A1A\u0005\u0002MBa!P\u0001!\u0002\u0013)\u0003bB \u0002\u0003\u0003%I\u0001Q\u0001\u0011#V\fg\u000e^5mKN#(/\u0019;fOfT!!\u0004\b\u0002\u001b\r|gNZ5hkJ\fG/[8o\u0015\ty\u0001#\u0001\u0003ue\u0016,'BA\t\u0013\u0003\u0015iG\u000e\\5c\u0015\t\u0019B#A\u0003ta\u0006\u00148N\u0003\u0002\u0016-\u00051\u0011\r]1dQ\u0016T\u0011aF\u0001\u0004_J<7\u0001\u0001\t\u00035\u0005i\u0011\u0001\u0004\u0002\u0011#V\fg\u000e^5mKN#(/\u0019;fOf\u001c\"!A\u000f\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\u0001\"AJ\u0014\u000e\u0003\u0005I!\u0001K\u0011\u0003\u000bY\u000bG.^3)\u0007\rQ\u0003\u0007\u0005\u0002,]5\tAF\u0003\u0002.%\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=b#!B*j]\u000e,\u0017%A\u0019\u0002\u000bEr\u0003G\f\u0019\u0002\tM{'\u000f^\u000b\u0002K!\u001aAA\u000b\u0019\u0002\u000bM{'\u000f\u001e\u0011)\u0007\u0015Q\u0003'\u0001\u0004NS:l\u0015\r\u001f\u0015\u0004\r)\u0002\u0014aB'j]6\u000b\u0007\u0010\t\u0015\u0004\u000f)\u0002\u0014AC!qaJ|\u0007\u0010S5ti\"\u001a\u0001B\u000b\u0019\u0002\u0017\u0005\u0003\bO]8y\u0011&\u001cH\u000f\t\u0015\u0004\u0013)\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,G#A!\u0011\u0005\t;U\"A\"\u000b\u0005\u0011+\u0015\u0001\u00027b]\u001eT\u0011AR\u0001\u0005U\u00064\u0018-\u0003\u0002I\u0007\n1qJ\u00196fGRD3!\u0001\u00161Q\r\u0001!\u0006\r"
)
public final class QuantileStrategy {
   public static Enumeration.Value ApproxHist() {
      return QuantileStrategy$.MODULE$.ApproxHist();
   }

   public static Enumeration.Value MinMax() {
      return QuantileStrategy$.MODULE$.MinMax();
   }

   public static Enumeration.Value Sort() {
      return QuantileStrategy$.MODULE$.Sort();
   }

   public static Enumeration.ValueSet ValueSet() {
      return QuantileStrategy$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return QuantileStrategy$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return QuantileStrategy$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return QuantileStrategy$.MODULE$.apply(x);
   }

   public static int maxId() {
      return QuantileStrategy$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return QuantileStrategy$.MODULE$.values();
   }

   public static String toString() {
      return QuantileStrategy$.MODULE$.toString();
   }
}
