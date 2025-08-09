package scala.jdk;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public final class OptionShape$ {
   public static final OptionShape$ MODULE$ = new OptionShape$();
   private static final OptionShape doubleOptionShape = new OptionShape() {
      public OptionalDouble fromJava(final Optional o) {
         return o.isPresent() ? OptionalDouble.of(BoxesRunTime.unboxToDouble(o.get())) : OptionalDouble.empty();
      }

      public OptionalDouble fromScala(final Option o) {
         return o instanceof Some ? OptionalDouble.of(BoxesRunTime.unboxToDouble(((Some)o).value())) : OptionalDouble.empty();
      }
   };
   private static final OptionShape jDoubleOptionShape;
   private static final OptionShape intOptionShape;
   private static final OptionShape jIntegerOptionShape;
   private static final OptionShape longOptionShape;
   private static final OptionShape jLongOptionShape;

   static {
      jDoubleOptionShape = MODULE$.doubleOptionShape();
      intOptionShape = new OptionShape() {
         public OptionalInt fromJava(final Optional o) {
            return o.isPresent() ? OptionalInt.of(BoxesRunTime.unboxToInt(o.get())) : OptionalInt.empty();
         }

         public OptionalInt fromScala(final Option o) {
            return o instanceof Some ? OptionalInt.of(BoxesRunTime.unboxToInt(((Some)o).value())) : OptionalInt.empty();
         }
      };
      jIntegerOptionShape = MODULE$.intOptionShape();
      longOptionShape = new OptionShape() {
         public OptionalLong fromJava(final Optional o) {
            return o.isPresent() ? OptionalLong.of(BoxesRunTime.unboxToLong(o.get())) : OptionalLong.empty();
         }

         public OptionalLong fromScala(final Option o) {
            return o instanceof Some ? OptionalLong.of(BoxesRunTime.unboxToLong(((Some)o).value())) : OptionalLong.empty();
         }
      };
      jLongOptionShape = MODULE$.longOptionShape();
   }

   public OptionShape doubleOptionShape() {
      return doubleOptionShape;
   }

   public OptionShape jDoubleOptionShape() {
      return jDoubleOptionShape;
   }

   public OptionShape intOptionShape() {
      return intOptionShape;
   }

   public OptionShape jIntegerOptionShape() {
      return jIntegerOptionShape;
   }

   public OptionShape longOptionShape() {
      return longOptionShape;
   }

   public OptionShape jLongOptionShape() {
      return jLongOptionShape;
   }

   private OptionShape$() {
   }
}
