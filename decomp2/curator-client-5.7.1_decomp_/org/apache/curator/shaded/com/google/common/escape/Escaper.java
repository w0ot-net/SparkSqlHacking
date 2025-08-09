package org.apache.curator.shaded.com.google.common.escape;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

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
