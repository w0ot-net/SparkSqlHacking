package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

@FunctionalInterface
public interface FloatConsumer extends Consumer, DoubleConsumer {
   void accept(float var1);

   /** @deprecated */
   @Deprecated
   default void accept(double t) {
      this.accept(SafeMath.safeDoubleToFloat(t));
   }

   /** @deprecated */
   @Deprecated
   default void accept(Float t) {
      this.accept(t);
   }

   default FloatConsumer andThen(FloatConsumer after) {
      Objects.requireNonNull(after);
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   default FloatConsumer andThen(DoubleConsumer after) {
      FloatConsumer var10001;
      if (after instanceof FloatConsumer) {
         var10001 = (FloatConsumer)after;
      } else {
         Objects.requireNonNull(after);
         var10001 = after::accept;
      }

      return this.andThen(var10001);
   }

   /** @deprecated */
   @Deprecated
   default Consumer andThen(Consumer after) {
      return super.andThen(after);
   }
}
