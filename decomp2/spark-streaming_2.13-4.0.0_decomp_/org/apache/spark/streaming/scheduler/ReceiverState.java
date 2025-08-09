package org.apache.spark.streaming.scheduler;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a:aa\u0003\u0007\t\u000291bA\u0002\r\r\u0011\u0003q\u0011\u0004C\u0003!\u0003\u0011\u0005!%\u0002\u0003\u0019\u0003\u0001\u0019\u0003bB\u0014\u0002\u0005\u0004%\t\u0001\u000b\u0005\u0007S\u0005\u0001\u000b\u0011B\u0012\t\u000f)\n!\u0019!C\u0001Q!11&\u0001Q\u0001\n\rBq\u0001L\u0001C\u0002\u0013\u0005\u0001\u0006\u0003\u0004.\u0003\u0001\u0006Ia\t\u0005\b]\u0005\t\t\u0011\"\u00030\u00035\u0011VmY3jm\u0016\u00148\u000b^1uK*\u0011QBD\u0001\ng\u000eDW\rZ;mKJT!a\u0004\t\u0002\u0013M$(/Z1nS:<'BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0011\u0005]\tQ\"\u0001\u0007\u0003\u001bI+7-Z5wKJ\u001cF/\u0019;f'\t\t!\u0004\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBDA\u0006F]VlWM]1uS>t\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Y\u0001\"\u0001J\u0013\u000e\u0003\u0005I!A\n\u0010\u0003\u000bY\u000bG.^3\u0002\u0011%s\u0015i\u0011+J-\u0016+\u0012aI\u0001\n\u0013:\u000b5\tV%W\u000b\u0002\n\u0011bU\"I\u000b\u0012+F*\u0012#\u0002\u0015M\u001b\u0005*\u0012#V\u0019\u0016#\u0005%\u0001\u0004B\u0007RKe+R\u0001\b\u0003\u000e#\u0016JV#!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0001\u0004CA\u00197\u001b\u0005\u0011$BA\u001a5\u0003\u0011a\u0017M\\4\u000b\u0003U\nAA[1wC&\u0011qG\r\u0002\u0007\u001f\nTWm\u0019;"
)
public final class ReceiverState {
   public static Enumeration.Value ACTIVE() {
      return ReceiverState$.MODULE$.ACTIVE();
   }

   public static Enumeration.Value SCHEDULED() {
      return ReceiverState$.MODULE$.SCHEDULED();
   }

   public static Enumeration.Value INACTIVE() {
      return ReceiverState$.MODULE$.INACTIVE();
   }

   public static Enumeration.ValueSet ValueSet() {
      return ReceiverState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return ReceiverState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return ReceiverState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return ReceiverState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return ReceiverState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return ReceiverState$.MODULE$.values();
   }

   public static String toString() {
      return ReceiverState$.MODULE$.toString();
   }
}
