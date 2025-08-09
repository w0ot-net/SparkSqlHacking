package scala.jdk.javaapi;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public final class OptionConverters$ {
   public static final OptionConverters$ MODULE$ = new OptionConverters$();

   public Optional toJava(final Option o) {
      return o instanceof Some ? Optional.ofNullable(((Some)o).value()) : Optional.empty();
   }

   public OptionalDouble toJavaOptionalDouble(final Option o) {
      return o instanceof Some ? OptionalDouble.of(BoxesRunTime.unboxToDouble((Double)((Some)o).value())) : OptionalDouble.empty();
   }

   public OptionalInt toJavaOptionalInt(final Option o) {
      return o instanceof Some ? OptionalInt.of(BoxesRunTime.unboxToInt((Integer)((Some)o).value())) : OptionalInt.empty();
   }

   public OptionalLong toJavaOptionalLong(final Option o) {
      return o instanceof Some ? OptionalLong.of(BoxesRunTime.unboxToLong((Long)((Some)o).value())) : OptionalLong.empty();
   }

   public Option toScala(final Optional o) {
      return (Option)(o.isPresent() ? new Some(o.get()) : None$.MODULE$);
   }

   public Option toScala(final OptionalDouble o) {
      return (Option)(o.isPresent() ? new Some(o.getAsDouble()) : None$.MODULE$);
   }

   public Option toScala(final OptionalInt o) {
      return (Option)(o.isPresent() ? new Some(o.getAsInt()) : None$.MODULE$);
   }

   public Option toScala(final OptionalLong o) {
      return (Option)(o.isPresent() ? new Some(o.getAsLong()) : None$.MODULE$);
   }

   private OptionConverters$() {
   }
}
