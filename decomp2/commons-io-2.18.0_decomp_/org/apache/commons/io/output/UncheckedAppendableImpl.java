package org.apache.commons.io.output;

import java.util.Objects;
import org.apache.commons.io.function.Uncheck;

final class UncheckedAppendableImpl implements UncheckedAppendable {
   private final Appendable appendable;

   UncheckedAppendableImpl(Appendable appendable) {
      this.appendable = (Appendable)Objects.requireNonNull(appendable, "appendable");
   }

   public UncheckedAppendable append(char c) {
      Appendable var10000 = this.appendable;
      Objects.requireNonNull(var10000);
      Uncheck.apply(var10000::append, c);
      return this;
   }

   public UncheckedAppendable append(CharSequence csq) {
      Appendable var10000 = this.appendable;
      Objects.requireNonNull(var10000);
      Uncheck.apply(var10000::append, csq);
      return this;
   }

   public UncheckedAppendable append(CharSequence csq, int start, int end) {
      Appendable var10000 = this.appendable;
      Objects.requireNonNull(var10000);
      Uncheck.apply(var10000::append, csq, start, end);
      return this;
   }

   public String toString() {
      return this.appendable.toString();
   }
}
