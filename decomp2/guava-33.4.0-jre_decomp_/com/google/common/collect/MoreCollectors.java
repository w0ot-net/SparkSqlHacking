package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class MoreCollectors {
   private static final Collector TO_OPTIONAL;
   private static final Object NULL_PLACEHOLDER;
   private static final Collector ONLY_ELEMENT;

   public static Collector toOptional() {
      return TO_OPTIONAL;
   }

   public static Collector onlyElement() {
      return ONLY_ELEMENT;
   }

   private MoreCollectors() {
   }

   static {
      TO_OPTIONAL = Collector.of(ToOptionalState::new, ToOptionalState::add, ToOptionalState::combine, ToOptionalState::getOptional, Characteristics.UNORDERED);
      NULL_PLACEHOLDER = new Object();
      ONLY_ELEMENT = Collector.of(ToOptionalState::new, (state, o) -> state.add(o == null ? NULL_PLACEHOLDER : o), ToOptionalState::combine, (state) -> {
         Object result = state.getElement();
         return result == NULL_PLACEHOLDER ? null : result;
      }, Characteristics.UNORDERED);
   }

   private static final class ToOptionalState {
      static final int MAX_EXTRAS = 4;
      @CheckForNull
      Object element = null;
      List extras = Collections.emptyList();

      ToOptionalState() {
      }

      IllegalArgumentException multiples(boolean overflow) {
         StringBuilder sb = (new StringBuilder()).append("expected one element but was: <").append(this.element);

         for(Object o : this.extras) {
            sb.append(", ").append(o);
         }

         if (overflow) {
            sb.append(", ...");
         }

         sb.append('>');
         throw new IllegalArgumentException(sb.toString());
      }

      void add(Object o) {
         Preconditions.checkNotNull(o);
         if (this.element == null) {
            this.element = o;
         } else if (this.extras.isEmpty()) {
            this.extras = new ArrayList(4);
            this.extras.add(o);
         } else {
            if (this.extras.size() >= 4) {
               throw this.multiples(true);
            }

            this.extras.add(o);
         }

      }

      ToOptionalState combine(ToOptionalState other) {
         if (this.element == null) {
            return other;
         } else if (other.element == null) {
            return this;
         } else {
            if (this.extras.isEmpty()) {
               this.extras = new ArrayList();
            }

            this.extras.add(other.element);
            this.extras.addAll(other.extras);
            if (this.extras.size() > 4) {
               this.extras.subList(4, this.extras.size()).clear();
               throw this.multiples(true);
            } else {
               return this;
            }
         }
      }

      Optional getOptional() {
         if (this.extras.isEmpty()) {
            return Optional.ofNullable(this.element);
         } else {
            throw this.multiples(false);
         }
      }

      Object getElement() {
         if (this.element == null) {
            throw new NoSuchElementException();
         } else if (this.extras.isEmpty()) {
            return this.element;
         } else {
            throw this.multiples(false);
         }
      }
   }
}
