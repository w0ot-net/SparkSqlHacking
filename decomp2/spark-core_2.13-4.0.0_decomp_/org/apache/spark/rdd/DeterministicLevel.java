package org.apache.spark.rdd;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005u:QAC\u0006\t\u0002Q1QAF\u0006\t\u0002]AQAH\u0001\u0005\u0002}Aq\u0001I\u0001C\u0002\u0013\u0005\u0011\u0005\u0003\u0004'\u0003\u0001\u0006IA\t\u0005\bO\u0005\u0011\r\u0011\"\u0001\"\u0011\u0019A\u0013\u0001)A\u0005E!9\u0011&\u0001b\u0001\n\u0003\t\u0003B\u0002\u0016\u0002A\u0003%!\u0005C\u0004,\u0003\u0005\u0005I\u0011\u0002\u0017\u0002%\u0011+G/\u001a:nS:L7\u000f^5d\u0019\u00164X\r\u001c\u0006\u0003\u00195\t1A\u001d3e\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001\u0001\t\u0003+\u0005i\u0011a\u0003\u0002\u0013\t\u0016$XM]7j]&\u001cH/[2MKZ,Gn\u0005\u0002\u00021A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q\tA#A\u0006E\u000bR+%+T%O\u0003R+U#\u0001\u0012\u0011\u0005\r\"S\"A\u0001\n\u0005\u0015b\"!\u0002,bYV,\u0017\u0001\u0004#F)\u0016\u0013V*\u0013(B)\u0016\u0003\u0013!C+O\u001fJ#UIU#E\u0003))fj\u0014*E\u000bJ+E\tI\u0001\u000e\u0013:#U\tV#S\u001b&s\u0015\tV#\u0002\u001d%sE)\u0012+F%6Ke*\u0011+FA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\tQ\u0006\u0005\u0002/g5\tqF\u0003\u00021c\u0005!A.\u00198h\u0015\u0005\u0011\u0014\u0001\u00026bm\u0006L!\u0001N\u0018\u0003\r=\u0013'.Z2uQ\t\ta\u0007\u0005\u00028u5\t\u0001H\u0003\u0002:\u001b\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005mB$\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007F\u0001\u00017\u0001"
)
public final class DeterministicLevel {
   public static Enumeration.Value INDETERMINATE() {
      return DeterministicLevel$.MODULE$.INDETERMINATE();
   }

   public static Enumeration.Value UNORDERED() {
      return DeterministicLevel$.MODULE$.UNORDERED();
   }

   public static Enumeration.Value DETERMINATE() {
      return DeterministicLevel$.MODULE$.DETERMINATE();
   }

   public static Enumeration.ValueSet ValueSet() {
      return DeterministicLevel$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return DeterministicLevel$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return DeterministicLevel$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return DeterministicLevel$.MODULE$.apply(x);
   }

   public static int maxId() {
      return DeterministicLevel$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return DeterministicLevel$.MODULE$.values();
   }

   public static String toString() {
      return DeterministicLevel$.MODULE$.toString();
   }
}
