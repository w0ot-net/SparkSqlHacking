package org.apache.spark.deploy.history;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:a\u0001C\u0005\t\u0002%\u0019bAB\u000b\n\u0011\u0003Ia\u0003C\u0003\u001e\u0003\u0011\u0005q\u0004C\u0004!\u0003\t\u0007I\u0011A\u0011\t\r\u0019\n\u0001\u0015!\u0003#\u0011\u001d9\u0013A1A\u0005\u0002\u0005Ba\u0001K\u0001!\u0002\u0013\u0011\u0003bB\u0015\u0002\u0003\u0003%IAK\u0001\b\u0019><G+\u001f9f\u0015\tQ1\"A\u0004iSN$xN]=\u000b\u00051i\u0011A\u00023fa2|\u0017P\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h!\t!\u0012!D\u0001\n\u0005\u001daun\u001a+za\u0016\u001c\"!A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1#\u0001\u0006Ee&4XM\u001d'pON,\u0012A\t\t\u0003G\u0011j\u0011!A\u0005\u0003Km\u0011QAV1mk\u0016\f1\u0002\u0012:jm\u0016\u0014Hj\\4tA\u0005IQI^3oi2{wm]\u0001\u000b\u000bZ,g\u000e\u001e'pON\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A\u0016\u0011\u00051\nT\"A\u0017\u000b\u00059z\u0013\u0001\u00027b]\u001eT\u0011\u0001M\u0001\u0005U\u00064\u0018-\u0003\u00023[\t1qJ\u00196fGR\u0004"
)
public final class LogType {
   public static Enumeration.Value EventLogs() {
      return LogType$.MODULE$.EventLogs();
   }

   public static Enumeration.Value DriverLogs() {
      return LogType$.MODULE$.DriverLogs();
   }

   public static Enumeration.ValueSet ValueSet() {
      return LogType$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return LogType$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return LogType$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return LogType$.MODULE$.apply(x);
   }

   public static int maxId() {
      return LogType$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return LogType$.MODULE$.values();
   }

   public static String toString() {
      return LogType$.MODULE$.toString();
   }
}
