package org.apache.spark.scheduler;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y:Qa\u0003\u0007\t\u0002U1Qa\u0006\u0007\t\u0002aAQaH\u0001\u0005\u0002\u0001*AaF\u0001\u0001C!9Q%\u0001b\u0001\n\u00031\u0003BB\u0014\u0002A\u0003%\u0011\u0005C\u0004)\u0003\t\u0007I\u0011\u0001\u0014\t\r%\n\u0001\u0015!\u0003\"\u0011\u001dQ\u0013A1A\u0005\u0002\u0019BaaK\u0001!\u0002\u0013\t\u0003b\u0002\u0017\u0002\u0003\u0003%I!L\u0001\u000f'\u000eDW\rZ;mS:<Wj\u001c3f\u0015\tia\"A\u0005tG\",G-\u001e7fe*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005Y\tQ\"\u0001\u0007\u0003\u001dM\u001b\u0007.\u001a3vY&tw-T8eKN\u0011\u0011!\u0007\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\f\u000b:,X.\u001a:bi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0002+A\u0011!eI\u0007\u0002\u0003%\u0011A%\b\u0002\u0006-\u0006dW/Z\u0001\u0005\r\u0006K%+F\u0001\"\u0003\u00151\u0015)\u0013*!\u0003\u00111\u0015JR(\u0002\u000b\u0019Kei\u0014\u0011\u0002\t9{e*R\u0001\u0006\u001d>sU\tI\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002]A\u0011q\u0006N\u0007\u0002a)\u0011\u0011GM\u0001\u0005Y\u0006twMC\u00014\u0003\u0011Q\u0017M^1\n\u0005U\u0002$AB(cU\u0016\u001cG\u000f"
)
public final class SchedulingMode {
   public static Enumeration.Value NONE() {
      return SchedulingMode$.MODULE$.NONE();
   }

   public static Enumeration.Value FIFO() {
      return SchedulingMode$.MODULE$.FIFO();
   }

   public static Enumeration.Value FAIR() {
      return SchedulingMode$.MODULE$.FAIR();
   }

   public static Enumeration.ValueSet ValueSet() {
      return SchedulingMode$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return SchedulingMode$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return SchedulingMode$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return SchedulingMode$.MODULE$.apply(x);
   }

   public static int maxId() {
      return SchedulingMode$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return SchedulingMode$.MODULE$.values();
   }

   public static String toString() {
      return SchedulingMode$.MODULE$.toString();
   }
}
