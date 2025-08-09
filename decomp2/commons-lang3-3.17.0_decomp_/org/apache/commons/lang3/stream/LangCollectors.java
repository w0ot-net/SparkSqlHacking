package org.apache.commons.lang3.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class LangCollectors {
   private static final Set CH_NOID = Collections.emptySet();

   public static Object collect(Collector collector, Object... array) {
      return Arrays.stream(array).collect(collector);
   }

   public static Collector joining() {
      return new SimpleCollector(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString, CH_NOID);
   }

   public static Collector joining(CharSequence delimiter) {
      return joining(delimiter, "", "");
   }

   public static Collector joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
      return joining(delimiter, prefix, suffix, Objects::toString);
   }

   public static Collector joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix, Function toString) {
      return new SimpleCollector(() -> new StringJoiner(delimiter, prefix, suffix), (a, t) -> a.add((CharSequence)toString.apply(t)), StringJoiner::merge, StringJoiner::toString, CH_NOID);
   }

   private LangCollectors() {
   }

   private static final class SimpleCollector implements Collector {
      private final BiConsumer accumulator;
      private final Set characteristics;
      private final BinaryOperator combiner;
      private final Function finisher;
      private final Supplier supplier;

      private SimpleCollector(Supplier supplier, BiConsumer accumulator, BinaryOperator combiner, Function finisher, Set characteristics) {
         this.supplier = supplier;
         this.accumulator = accumulator;
         this.combiner = combiner;
         this.finisher = finisher;
         this.characteristics = characteristics;
      }

      public BiConsumer accumulator() {
         return this.accumulator;
      }

      public Set characteristics() {
         return this.characteristics;
      }

      public BinaryOperator combiner() {
         return this.combiner;
      }

      public Function finisher() {
         return this.finisher;
      }

      public Supplier supplier() {
         return this.supplier;
      }
   }
}
