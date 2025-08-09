package org.apache.spark.deploy;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q;aAF\f\t\u0002]ybAB\u0011\u0018\u0011\u00039\"\u0005C\u0003*\u0003\u0011\u00051\u0006C\u0004-\u0003\t\u0007I\u0011A\u0017\t\rI\n\u0001\u0015!\u0003/\u0011\u001d\u0019\u0014A1A\u0005\u00025Ba\u0001N\u0001!\u0002\u0013q\u0003bB\u001b\u0002\u0005\u0004%\t!\f\u0005\u0007m\u0005\u0001\u000b\u0011\u0002\u0018\t\u000f]\n!\u0019!C\u0001[!1\u0001(\u0001Q\u0001\n9Bq!O\u0001C\u0002\u0013\u0005Q\u0006\u0003\u0004;\u0003\u0001\u0006IA\f\u0005\bw\u0005\u0011\r\u0011\"\u0001.\u0011\u0019a\u0014\u0001)A\u0005]!9Q(\u0001b\u0001\n\u0003i\u0003B\u0002 \u0002A\u0003%a&\u0002\u0003\"\u0003\u0001q\u0003bB \u0002\u0005\u0004%I\u0001\u0011\u0005\u0007\u0013\u0006\u0001\u000b\u0011B!\t\u000b)\u000bA\u0011A&\t\u000fI\u000b\u0011\u0011!C\u0005'\u0006iQ\t_3dkR|'o\u0015;bi\u0016T!\u0001G\r\u0002\r\u0011,\u0007\u000f\\8z\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<\u0007C\u0001\u0011\u0002\u001b\u00059\"!D#yK\u000e,Ho\u001c:Ti\u0006$Xm\u0005\u0002\u0002GA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0010\u0002\u00131\u000bUKT\"I\u0013:;U#\u0001\u0018\u0011\u0005=\u0002T\"A\u0001\n\u0005E:#!\u0002,bYV,\u0017A\u0003'B+:\u001b\u0005*\u0013(HA\u00059!+\u0016(O\u0013:;\u0015\u0001\u0003*V\u001d:Kej\u0012\u0011\u0002\r-KE\nT#E\u0003\u001dY\u0015\n\u0014'F\t\u0002\naAR!J\u0019\u0016#\u0015a\u0002$B\u00132+E\tI\u0001\u0005\u0019>\u001bF+A\u0003M\u001fN#\u0006%\u0001\u0004F1&#V\tR\u0001\b\u000bbKE+\u0012#!\u00039!UiQ(N\u001b&\u001b6+S(O\u000b\u0012\u000bq\u0002R#D\u001f6k\u0015jU*J\u001f:+E\tI\u0001\u000fM&t\u0017n\u001d5fIN#\u0018\r^3t+\u0005\t\u0005c\u0001\"H]5\t1I\u0003\u0002E\u000b\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\r\u0016\n!bY8mY\u0016\u001cG/[8o\u0013\tA5IA\u0002TKF\fqBZ5oSNDW\rZ*uCR,7\u000fI\u0001\u000bSN4\u0015N\\5tQ\u0016$GC\u0001'P!\t!S*\u0003\u0002OK\t9!i\\8mK\u0006t\u0007\"\u0002)\u0015\u0001\u0004\t\u0016!B:uCR,\u0007CA\u0018\u0012\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005!\u0006CA+[\u001b\u00051&BA,Y\u0003\u0011a\u0017M\\4\u000b\u0003e\u000bAA[1wC&\u00111L\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public final class ExecutorState {
   public static boolean isFinished(final Enumeration.Value state) {
      return ExecutorState$.MODULE$.isFinished(state);
   }

   public static Enumeration.Value DECOMMISSIONED() {
      return ExecutorState$.MODULE$.DECOMMISSIONED();
   }

   public static Enumeration.Value EXITED() {
      return ExecutorState$.MODULE$.EXITED();
   }

   public static Enumeration.Value LOST() {
      return ExecutorState$.MODULE$.LOST();
   }

   public static Enumeration.Value FAILED() {
      return ExecutorState$.MODULE$.FAILED();
   }

   public static Enumeration.Value KILLED() {
      return ExecutorState$.MODULE$.KILLED();
   }

   public static Enumeration.Value RUNNING() {
      return ExecutorState$.MODULE$.RUNNING();
   }

   public static Enumeration.Value LAUNCHING() {
      return ExecutorState$.MODULE$.LAUNCHING();
   }

   public static Enumeration.ValueSet ValueSet() {
      return ExecutorState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return ExecutorState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return ExecutorState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return ExecutorState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return ExecutorState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return ExecutorState$.MODULE$.values();
   }

   public static String toString() {
      return ExecutorState$.MODULE$.toString();
   }
}
