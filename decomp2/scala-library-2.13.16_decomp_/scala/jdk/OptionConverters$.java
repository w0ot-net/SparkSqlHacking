package scala.jdk;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import scala.Option;

public final class OptionConverters$ {
   public static final OptionConverters$ MODULE$ = new OptionConverters$();

   public Optional RichOptional(final Optional o) {
      return o;
   }

   public Option RichOption(final Option o) {
      return o;
   }

   public OptionalDouble RichOptionalDouble(final OptionalDouble o) {
      return o;
   }

   public OptionalInt RichOptionalInt(final OptionalInt o) {
      return o;
   }

   public OptionalLong RichOptionalLong(final OptionalLong o) {
      return o;
   }

   private OptionConverters$() {
   }
}
