package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AggregateTranslator extends CharSequenceTranslator {
   private final List translators = new ArrayList();

   public AggregateTranslator(CharSequenceTranslator... translators) {
      if (translators != null) {
         Stream var10000 = Stream.of(translators).filter(Objects::nonNull);
         List var10001 = this.translators;
         Objects.requireNonNull(var10001);
         var10000.forEach(var10001::add);
      }

   }

   public int translate(CharSequence input, int index, Writer writer) throws IOException {
      for(CharSequenceTranslator translator : this.translators) {
         int consumed = translator.translate(input, index, writer);
         if (consumed != 0) {
            return consumed;
         }
      }

      return 0;
   }
}
