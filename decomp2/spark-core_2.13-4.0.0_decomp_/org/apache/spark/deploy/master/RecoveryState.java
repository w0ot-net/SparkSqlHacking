package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:a!\u0004\b\t\u0002AAbA\u0002\u000e\u000f\u0011\u0003\u00012\u0004C\u0003#\u0003\u0011\u0005A%\u0002\u0003&\u0003\u00011\u0003b\u0002\u0016\u0002\u0005\u0004%\ta\u000b\u0005\u0007Y\u0005\u0001\u000b\u0011\u0002\u0014\t\u000f5\n!\u0019!C\u0001W!1a&\u0001Q\u0001\n\u0019BqaL\u0001C\u0002\u0013\u00051\u0006\u0003\u00041\u0003\u0001\u0006IA\n\u0005\bc\u0005\u0011\r\u0011\"\u0001,\u0011\u0019\u0011\u0014\u0001)A\u0005M!91'AA\u0001\n\u0013!\u0014!\u0004*fG>4XM]=Ti\u0006$XM\u0003\u0002\u0010!\u00051Q.Y:uKJT!!\u0005\n\u0002\r\u0011,\u0007\u000f\\8z\u0015\t\u0019B#A\u0003ta\u0006\u00148N\u0003\u0002\u0016-\u00051\u0011\r]1dQ\u0016T\u0011aF\u0001\u0004_J<\u0007CA\r\u0002\u001b\u0005q!!\u0004*fG>4XM]=Ti\u0006$Xm\u0005\u0002\u00029A\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\r\u0003\u00175\u000b7\u000f^3s'R\fG/\u001a\t\u0003O!j\u0011!A\u0005\u0003S\u0001\u0012QAV1mk\u0016\fqa\u0015+B\u001d\u0012\u0013\u0015,F\u0001'\u0003!\u0019F+\u0011(E\u0005f\u0003\u0013!B!M\u0013Z+\u0015AB!M\u0013Z+\u0005%\u0001\u0006S\u000b\u000e{e+\u0012*J\u001d\u001e\u000b1BU#D\u001fZ+%+\u0013(HA\u0005\u00192iT'Q\u0019\u0016#\u0016JT$`%\u0016\u001buJV#S3\u0006!2iT'Q\u0019\u0016#\u0016JT$`%\u0016\u001buJV#S3\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u000e\t\u0003mmj\u0011a\u000e\u0006\u0003qe\nA\u0001\\1oO*\t!(\u0001\u0003kCZ\f\u0017B\u0001\u001f8\u0005\u0019y%M[3di\u0002"
)
public final class RecoveryState {
   public static Enumeration.Value COMPLETING_RECOVERY() {
      return RecoveryState$.MODULE$.COMPLETING_RECOVERY();
   }

   public static Enumeration.Value RECOVERING() {
      return RecoveryState$.MODULE$.RECOVERING();
   }

   public static Enumeration.Value ALIVE() {
      return RecoveryState$.MODULE$.ALIVE();
   }

   public static Enumeration.Value STANDBY() {
      return RecoveryState$.MODULE$.STANDBY();
   }

   public static Enumeration.ValueSet ValueSet() {
      return RecoveryState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return RecoveryState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return RecoveryState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return RecoveryState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return RecoveryState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return RecoveryState$.MODULE$.values();
   }

   public static String toString() {
      return RecoveryState$.MODULE$.toString();
   }
}
