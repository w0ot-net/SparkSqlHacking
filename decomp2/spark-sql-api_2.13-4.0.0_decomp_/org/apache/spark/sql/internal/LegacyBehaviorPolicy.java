package org.apache.spark.sql.internal;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:QAC\u0006\t\u0002Y1Q\u0001G\u0006\t\u0002eAQ\u0001I\u0001\u0005\u0002\u0005BqAI\u0001C\u0002\u0013\u00051\u0005\u0003\u0004)\u0003\u0001\u0006I\u0001\n\u0005\bS\u0005\u0011\r\u0011\"\u0001$\u0011\u0019Q\u0013\u0001)A\u0005I!91&\u0001b\u0001\n\u0003\u0019\u0003B\u0002\u0017\u0002A\u0003%A\u0005C\u0004.\u0003\u0005\u0005I\u0011\u0002\u0018\u0002)1+w-Y2z\u0005\u0016D\u0017M^5peB{G.[2z\u0015\taQ\"\u0001\u0005j]R,'O\\1m\u0015\tqq\"A\u0002tc2T!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001!\t9\u0012!D\u0001\f\u0005QaUmZ1ds\n+\u0007.\u0019<j_J\u0004v\u000e\\5dsN\u0011\u0011A\u0007\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\f\u000b:,X.\u001a:bi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0002-\u0005IQ\tW\"F!RKuJT\u000b\u0002IA\u0011QEJ\u0007\u0002\u0003%\u0011qE\b\u0002\u0006-\u0006dW/Z\u0001\u000b\u000bb\u001bU\t\u0015+J\u001f:\u0003\u0013A\u0002'F\u000f\u0006\u001b\u0015,A\u0004M\u000b\u001e\u000b5)\u0017\u0011\u0002\u0013\r{%KU#D)\u0016#\u0015AC\"P%J+5\tV#EA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\tq\u0006\u0005\u00021k5\t\u0011G\u0003\u00023g\u0005!A.\u00198h\u0015\u0005!\u0014\u0001\u00026bm\u0006L!AN\u0019\u0003\r=\u0013'.Z2u\u0001"
)
public final class LegacyBehaviorPolicy {
   public static Enumeration.Value CORRECTED() {
      return LegacyBehaviorPolicy$.MODULE$.CORRECTED();
   }

   public static Enumeration.Value LEGACY() {
      return LegacyBehaviorPolicy$.MODULE$.LEGACY();
   }

   public static Enumeration.Value EXCEPTION() {
      return LegacyBehaviorPolicy$.MODULE$.EXCEPTION();
   }

   public static Enumeration.ValueSet ValueSet() {
      return LegacyBehaviorPolicy$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return LegacyBehaviorPolicy$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return LegacyBehaviorPolicy$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return LegacyBehaviorPolicy$.MODULE$.apply(x);
   }

   public static int maxId() {
      return LegacyBehaviorPolicy$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return LegacyBehaviorPolicy$.MODULE$.values();
   }

   public static String toString() {
      return LegacyBehaviorPolicy$.MODULE$.toString();
   }
}
