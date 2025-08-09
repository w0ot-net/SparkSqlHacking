package org.apache.spark.deploy;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i:a!\u0004\b\t\u000291bA\u0002\r\u000f\u0011\u0003q\u0011\u0004C\u0003!\u0003\u0011\u0005!%\u0002\u0003\u0019\u0003\u0001\u0019\u0003bB\u0014\u0002\u0005\u0004%\t\u0001\u000b\u0005\u0007S\u0005\u0001\u000b\u0011B\u0012\t\u000f)\n!\u0019!C\u0001Q!11&\u0001Q\u0001\n\rBq\u0001L\u0001C\u0002\u0013\u0005\u0001\u0006\u0003\u0004.\u0003\u0001\u0006Ia\t\u0005\b]\u0005\u0011\r\u0011\"\u0001)\u0011\u0019y\u0013\u0001)A\u0005G!9\u0001'AA\u0001\n\u0013\t\u0014!E*qCJ\\7+\u001e2nSR\f5\r^5p]*\u0011q\u0002E\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u0004\"aF\u0001\u000e\u00039\u0011\u0011c\u00159be.\u001cVOY7ji\u0006\u001bG/[8o'\t\t!\u0004\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBDA\u0006F]VlWM]1uS>t\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Y\u0001\"\u0001J\u0013\u000e\u0003\u0005I!A\n\u0010\u0003\u000bY\u000bG.^3\u0002\rM+&)T%U+\u0005\u0019\u0013aB*V\u00056KE\u000bI\u0001\u0005\u0017&cE*A\u0003L\u00132c\u0005%\u0001\bS\u000bF+Vi\u0015+`'R\u000bE+V*\u0002\u001fI+\u0015+V#T)~\u001bF+\u0011+V'\u0002\nQ\u0002\u0015*J\u001dR{f+\u0012*T\u0013>s\u0015A\u0004)S\u0013:#vLV#S'&{e\nI\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002eA\u00111\u0007O\u0007\u0002i)\u0011QGN\u0001\u0005Y\u0006twMC\u00018\u0003\u0011Q\u0017M^1\n\u0005e\"$AB(cU\u0016\u001cG\u000f"
)
public final class SparkSubmitAction {
   public static Enumeration.Value PRINT_VERSION() {
      return SparkSubmitAction$.MODULE$.PRINT_VERSION();
   }

   public static Enumeration.Value REQUEST_STATUS() {
      return SparkSubmitAction$.MODULE$.REQUEST_STATUS();
   }

   public static Enumeration.Value KILL() {
      return SparkSubmitAction$.MODULE$.KILL();
   }

   public static Enumeration.Value SUBMIT() {
      return SparkSubmitAction$.MODULE$.SUBMIT();
   }

   public static Enumeration.ValueSet ValueSet() {
      return SparkSubmitAction$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return SparkSubmitAction$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return SparkSubmitAction$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return SparkSubmitAction$.MODULE$.apply(x);
   }

   public static int maxId() {
      return SparkSubmitAction$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return SparkSubmitAction$.MODULE$.values();
   }

   public static String toString() {
      return SparkSubmitAction$.MODULE$.toString();
   }
}
