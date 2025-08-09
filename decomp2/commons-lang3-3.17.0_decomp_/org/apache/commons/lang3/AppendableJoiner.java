package org.apache.commons.lang3;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Supplier;
import org.apache.commons.lang3.exception.UncheckedException;
import org.apache.commons.lang3.function.FailableBiConsumer;

public final class AppendableJoiner {
   private final CharSequence prefix;
   private final CharSequence suffix;
   private final CharSequence delimiter;
   private final FailableBiConsumer appender;

   public static Builder builder() {
      return new Builder();
   }

   @SafeVarargs
   static Appendable joinA(Appendable appendable, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender, Object... elements) throws IOException {
      return joinArray(appendable, prefix, suffix, delimiter, appender, elements);
   }

   private static Appendable joinArray(Appendable appendable, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender, Object[] elements) throws IOException {
      appendable.append(prefix);
      if (elements != null) {
         if (elements.length > 0) {
            appender.accept(appendable, elements[0]);
         }

         for(int i = 1; i < elements.length; ++i) {
            appendable.append(delimiter);
            appender.accept(appendable, elements[i]);
         }
      }

      appendable.append(suffix);
      return appendable;
   }

   static StringBuilder joinI(StringBuilder stringBuilder, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender, Iterable elements) {
      try {
         return (StringBuilder)joinIterable(stringBuilder, prefix, suffix, delimiter, appender, elements);
      } catch (IOException e) {
         throw new UncheckedException(e);
      }
   }

   private static Appendable joinIterable(Appendable appendable, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender, Iterable elements) throws IOException {
      appendable.append(prefix);
      if (elements != null) {
         Iterator<T> iterator = elements.iterator();
         if (iterator.hasNext()) {
            appender.accept(appendable, iterator.next());
         }

         while(iterator.hasNext()) {
            appendable.append(delimiter);
            appender.accept(appendable, iterator.next());
         }
      }

      appendable.append(suffix);
      return appendable;
   }

   @SafeVarargs
   static StringBuilder joinSB(StringBuilder stringBuilder, CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender, Object... elements) {
      try {
         return (StringBuilder)joinArray(stringBuilder, prefix, suffix, delimiter, appender, elements);
      } catch (IOException e) {
         throw new UncheckedException(e);
      }
   }

   private static CharSequence nonNull(CharSequence value) {
      return (CharSequence)(value != null ? value : "");
   }

   private AppendableJoiner(CharSequence prefix, CharSequence suffix, CharSequence delimiter, FailableBiConsumer appender) {
      this.prefix = nonNull(prefix);
      this.suffix = nonNull(suffix);
      this.delimiter = nonNull(delimiter);
      this.appender = appender != null ? appender : (a, e) -> a.append(String.valueOf(e));
   }

   public StringBuilder join(StringBuilder stringBuilder, Iterable elements) {
      return joinI(stringBuilder, this.prefix, this.suffix, this.delimiter, this.appender, elements);
   }

   public StringBuilder join(StringBuilder stringBuilder, Object... elements) {
      return joinSB(stringBuilder, this.prefix, this.suffix, this.delimiter, this.appender, elements);
   }

   public Appendable joinA(Appendable appendable, Iterable elements) throws IOException {
      return joinIterable(appendable, this.prefix, this.suffix, this.delimiter, this.appender, elements);
   }

   public Appendable joinA(Appendable appendable, Object... elements) throws IOException {
      return joinA(appendable, this.prefix, this.suffix, this.delimiter, this.appender, elements);
   }

   public static final class Builder implements Supplier {
      private CharSequence prefix;
      private CharSequence suffix;
      private CharSequence delimiter;
      private FailableBiConsumer appender;

      Builder() {
      }

      public AppendableJoiner get() {
         return new AppendableJoiner(this.prefix, this.suffix, this.delimiter, this.appender);
      }

      public Builder setDelimiter(CharSequence delimiter) {
         this.delimiter = delimiter;
         return this;
      }

      public Builder setElementAppender(FailableBiConsumer appender) {
         this.appender = appender;
         return this;
      }

      public Builder setPrefix(CharSequence prefix) {
         this.prefix = prefix;
         return this;
      }

      public Builder setSuffix(CharSequence suffix) {
         this.suffix = suffix;
         return this;
      }
   }
}
