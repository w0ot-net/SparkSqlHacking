package org.sparkproject.guava.escape;

import com.google.errorprone.annotations.DoNotMock;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;

@DoNotMock("Use Escapers.nullEscaper() or another methods from the *Escapers classes")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class Escaper {
   private final Function asFunction = this::escape;

   protected Escaper() {
   }

   public abstract String escape(String string);

   public final Function asFunction() {
      return this.asFunction;
   }
}
