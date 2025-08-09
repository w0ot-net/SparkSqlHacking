package org.apache.spark.rdd;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y:aa\u0003\u0007\t\u00029!bA\u0002\f\r\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001%\u0002\u0003\u0017\u0003\u0001\t\u0003bB\u0013\u0002\u0005\u0004%\tA\n\u0005\u0007O\u0005\u0001\u000b\u0011B\u0011\t\u000f!\n!\u0019!C\u0001M!1\u0011&\u0001Q\u0001\n\u0005BqAK\u0001C\u0002\u0013\u0005a\u0005\u0003\u0004,\u0003\u0001\u0006I!\t\u0005\bY\u0005\t\t\u0011\"\u0003.\u0003=\u0019\u0005.Z2la>Lg\u000e^*uCR,'BA\u0007\u000f\u0003\r\u0011H\r\u001a\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sOB\u0011Q#A\u0007\u0002\u0019\ty1\t[3dWB|\u0017N\u001c;Ti\u0006$Xm\u0005\u0002\u00021A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u000b\u0011\u0005\t\u001aS\"A\u0001\n\u0005\u0011b\"!\u0002,bYV,\u0017aC%oSRL\u0017\r\\5{K\u0012,\u0012!I\u0001\r\u0013:LG/[1mSj,G\rI\u0001\u0018\u0007\",7m\u001b9pS:$\u0018N\\4J]B\u0013xn\u001a:fgN\f\u0001d\u00115fG.\u0004x.\u001b8uS:<\u0017J\u001c)s_\u001e\u0014Xm]:!\u00031\u0019\u0005.Z2la>Lg\u000e^3e\u00035\u0019\u0005.Z2la>Lg\u000e^3eA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\ta\u0006\u0005\u00020i5\t\u0001G\u0003\u00022e\u0005!A.\u00198h\u0015\u0005\u0019\u0014\u0001\u00026bm\u0006L!!\u000e\u0019\u0003\r=\u0013'.Z2u\u0001"
)
public final class CheckpointState {
   public static Enumeration.Value Checkpointed() {
      return CheckpointState$.MODULE$.Checkpointed();
   }

   public static Enumeration.Value CheckpointingInProgress() {
      return CheckpointState$.MODULE$.CheckpointingInProgress();
   }

   public static Enumeration.Value Initialized() {
      return CheckpointState$.MODULE$.Initialized();
   }

   public static Enumeration.ValueSet ValueSet() {
      return CheckpointState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return CheckpointState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return CheckpointState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return CheckpointState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return CheckpointState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return CheckpointState$.MODULE$.values();
   }

   public static String toString() {
      return CheckpointState$.MODULE$.toString();
   }
}
