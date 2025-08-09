package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;Q!\u0003\u0006\t\u0002]1Q!\u0007\u0006\t\u0002iAQ!I\u0001\u0005\u0002\t*A!G\u0001\u0001G!9\u0001'\u0001b\u0001\n\u0003\t\u0004BB\u001a\u0002A\u0003%1\u0005C\u00046\u0003\t\u0007I\u0011A\u0019\t\r]\n\u0001\u0015!\u0003$\u0011\u001dI\u0014!!A\u0005\ni\n1BR3biV\u0014X\rV=qK*\u00111\u0002D\u0001\u000eG>tg-[4ve\u0006$\u0018n\u001c8\u000b\u00055q\u0011\u0001\u0002;sK\u0016T!a\u0004\t\u0002\u000b5dG.\u001b2\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0019\u00035\t!BA\u0006GK\u0006$XO]3UsB,7CA\u0001\u001c!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\rqJg.\u001b;?)\u00059\u0002C\u0001\u0013&\u001b\u0005\t\u0011B\u0001\u0014 \u0005\u00151\u0016\r\\;fQ\r\u0019\u0001F\f\t\u0003S1j\u0011A\u000b\u0006\u0003WA\t!\"\u00198o_R\fG/[8o\u0013\ti#FA\u0003TS:\u001cW-I\u00010\u0003\u0015\td\u0006\r\u00181\u0003)\u0019uN\u001c;j]V|Wo]\u000b\u0002G!\u001aA\u0001\u000b\u0018\u0002\u0017\r{g\u000e^5ok>,8\u000f\t\u0015\u0004\u000b!r\u0013aC\"bi\u0016<wN]5dC2D3A\u0002\u0015/\u00031\u0019\u0015\r^3h_JL7-\u00197!Q\r9\u0001FL\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002wA\u0011A(Q\u0007\u0002{)\u0011ahP\u0001\u0005Y\u0006twMC\u0001A\u0003\u0011Q\u0017M^1\n\u0005\tk$AB(cU\u0016\u001cG\u000fK\u0002\u0002Q9B3\u0001\u0001\u0015/\u0001"
)
public final class FeatureType {
   public static Enumeration.Value Categorical() {
      return FeatureType$.MODULE$.Categorical();
   }

   public static Enumeration.Value Continuous() {
      return FeatureType$.MODULE$.Continuous();
   }

   public static Enumeration.ValueSet ValueSet() {
      return FeatureType$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return FeatureType$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return FeatureType$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return FeatureType$.MODULE$.apply(x);
   }

   public static int maxId() {
      return FeatureType$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return FeatureType$.MODULE$.values();
   }

   public static String toString() {
      return FeatureType$.MODULE$.toString();
   }
}
