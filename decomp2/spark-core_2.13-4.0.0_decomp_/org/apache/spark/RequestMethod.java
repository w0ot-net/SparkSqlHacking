package org.apache.spark;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=:a\u0001C\u0005\t\u0002%yaAB\t\n\u0011\u0003I!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0004\u001d\u0003\t\u0007I\u0011A\u000f\t\r\t\n\u0001\u0015!\u0003\u001f\u0011\u001d\u0019\u0013A1A\u0005\u0002uAa\u0001J\u0001!\u0002\u0013q\u0002bB\u0013\u0002\u0003\u0003%IAJ\u0001\u000e%\u0016\fX/Z:u\u001b\u0016$\bn\u001c3\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u0004\"\u0001E\u0001\u000e\u0003%\u0011QBU3rk\u0016\u001cH/T3uQ>$7CA\u0001\u0014!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aD\u0001\b\u0005\u0006\u0013&+S#S+\u0005q\u0002CA\u0010!\u001b\u0005\t\u0011BA\u0011\u0018\u0005\u00151\u0016\r\\;f\u0003!\u0011\u0015I\u0015*J\u000bJ\u0003\u0013AC!M\u0019~;\u0015\t\u0016%F%\u0006Y\u0011\t\u0014'`\u000f\u0006#\u0006*\u0012*!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u00059\u0003C\u0001\u0015.\u001b\u0005I#B\u0001\u0016,\u0003\u0011a\u0017M\\4\u000b\u00031\nAA[1wC&\u0011a&\u000b\u0002\u0007\u001f\nTWm\u0019;"
)
public final class RequestMethod {
   public static Enumeration.Value ALL_GATHER() {
      return RequestMethod$.MODULE$.ALL_GATHER();
   }

   public static Enumeration.Value BARRIER() {
      return RequestMethod$.MODULE$.BARRIER();
   }

   public static Enumeration.ValueSet ValueSet() {
      return RequestMethod$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return RequestMethod$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return RequestMethod$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return RequestMethod$.MODULE$.apply(x);
   }

   public static int maxId() {
      return RequestMethod$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return RequestMethod$.MODULE$.values();
   }

   public static String toString() {
      return RequestMethod$.MODULE$.toString();
   }
}
