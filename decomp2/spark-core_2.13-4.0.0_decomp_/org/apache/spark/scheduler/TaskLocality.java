package org.apache.spark.scheduler;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005E;Q\u0001E\t\t\u0002i1Q\u0001H\t\t\u0002uAQ\u0001J\u0001\u0005\u0002\u0015BqAJ\u0001C\u0002\u0013\u0005q\u0005\u0003\u0004-\u0003\u0001\u0006I\u0001\u000b\u0005\b[\u0005\u0011\r\u0011\"\u0001(\u0011\u0019q\u0013\u0001)A\u0005Q!9q&\u0001b\u0001\n\u00039\u0003B\u0002\u0019\u0002A\u0003%\u0001\u0006C\u00042\u0003\t\u0007I\u0011A\u0014\t\rI\n\u0001\u0015!\u0003)\u0011\u001d\u0019\u0014A1A\u0005\u0002\u001dBa\u0001N\u0001!\u0002\u0013AS\u0001\u0002\u000f\u0002\u0001!BQ!N\u0001\u0005\u0002YBqaP\u0001\u0002\u0002\u0013%\u0001)\u0001\u0007UCN\\Gj\\2bY&$\u0018P\u0003\u0002\u0013'\u0005I1o\u00195fIVdWM\u001d\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u0001\u0001CA\u000e\u0002\u001b\u0005\t\"\u0001\u0004+bg.dunY1mSRL8CA\u0001\u001f!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\rqJg.\u001b;?)\u0005Q\u0012!\u0004)S\u001f\u000e+5kU0M\u001f\u000e\u000bE*F\u0001)!\tI#&D\u0001\u0002\u0013\tY#EA\u0003WC2,X-\u0001\bQ%>\u001bUiU*`\u0019>\u001b\u0015\t\u0014\u0011\u0002\u00159{E)R0M\u001f\u000e\u000bE*A\u0006O\u001f\u0012+u\fT(D\u00032\u0003\u0013a\u0002(P?B\u0013VIR\u0001\t\u001d>{\u0006KU#GA\u0005Q!+Q\"L?2{5)\u0011'\u0002\u0017I\u000b5iS0M\u001f\u000e\u000bE\nI\u0001\u0004\u0003:K\u0016\u0001B!O3\u0002\n\u0011\"[:BY2|w/\u001a3\u0015\u0007]RT\b\u0005\u0002 q%\u0011\u0011\b\t\u0002\b\u0005>|G.Z1o\u0011\u0015Yd\u00021\u0001=\u0003)\u0019wN\\:ue\u0006Lg\u000e\u001e\t\u0003S5AQA\u0010\bA\u0002q\n\u0011bY8oI&$\u0018n\u001c8\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003\u0005\u0003\"AQ$\u000e\u0003\rS!\u0001R#\u0002\t1\fgn\u001a\u0006\u0002\r\u0006!!.\u0019<b\u0013\tA5I\u0001\u0004PE*,7\r\u001e\u0015\u0003\u0003)\u0003\"a\u0013(\u000e\u00031S!!T\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002P\u0019\naA)\u001a<fY>\u0004XM]!qS\"\u0012\u0001A\u0013"
)
public final class TaskLocality {
   public static boolean isAllowed(final Enumeration.Value constraint, final Enumeration.Value condition) {
      return TaskLocality$.MODULE$.isAllowed(constraint, condition);
   }

   public static Enumeration.Value ANY() {
      return TaskLocality$.MODULE$.ANY();
   }

   public static Enumeration.Value RACK_LOCAL() {
      return TaskLocality$.MODULE$.RACK_LOCAL();
   }

   public static Enumeration.Value NO_PREF() {
      return TaskLocality$.MODULE$.NO_PREF();
   }

   public static Enumeration.Value NODE_LOCAL() {
      return TaskLocality$.MODULE$.NODE_LOCAL();
   }

   public static Enumeration.Value PROCESS_LOCAL() {
      return TaskLocality$.MODULE$.PROCESS_LOCAL();
   }

   public static Enumeration.ValueSet ValueSet() {
      return TaskLocality$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return TaskLocality$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return TaskLocality$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return TaskLocality$.MODULE$.apply(x);
   }

   public static int maxId() {
      return TaskLocality$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return TaskLocality$.MODULE$.values();
   }

   public static String toString() {
      return TaskLocality$.MODULE$.toString();
   }
}
