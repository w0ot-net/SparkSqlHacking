package org.apache.commons.collections4.functors;

import [Lorg.apache.commons.collections4.Closure;;
import [Lorg.apache.commons.collections4.Predicate;;
import [Lorg.apache.commons.collections4.Transformer;;
import java.util.Collection;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

class FunctorUtils {
   private FunctorUtils() {
   }

   static Predicate[] copy(Predicate... predicates) {
      return predicates == null ? null : (Predicate[])((Predicate[])((Predicate;)predicates).clone());
   }

   static Predicate coerce(Predicate predicate) {
      return predicate;
   }

   static void validate(Predicate... predicates) {
      if (predicates == null) {
         throw new NullPointerException("The predicate array must not be null");
      } else {
         for(int i = 0; i < predicates.length; ++i) {
            if (predicates[i] == null) {
               throw new NullPointerException("The predicate array must not contain a null predicate, index " + i + " was null");
            }
         }

      }
   }

   static Predicate[] validate(Collection predicates) {
      if (predicates == null) {
         throw new NullPointerException("The predicate collection must not be null");
      } else {
         Predicate<? super T>[] preds = new Predicate[predicates.size()];
         int i = 0;

         for(Predicate predicate : predicates) {
            preds[i] = predicate;
            if (preds[i] == null) {
               throw new NullPointerException("The predicate collection must not contain a null predicate, index " + i + " was null");
            }

            ++i;
         }

         return preds;
      }
   }

   static Closure[] copy(Closure... closures) {
      return closures == null ? null : (Closure[])((Closure[])((Closure;)closures).clone());
   }

   static void validate(Closure... closures) {
      if (closures == null) {
         throw new NullPointerException("The closure array must not be null");
      } else {
         for(int i = 0; i < closures.length; ++i) {
            if (closures[i] == null) {
               throw new NullPointerException("The closure array must not contain a null closure, index " + i + " was null");
            }
         }

      }
   }

   static Closure coerce(Closure closure) {
      return closure;
   }

   static Transformer[] copy(Transformer... transformers) {
      return transformers == null ? null : (Transformer[])((Transformer[])((Transformer;)transformers).clone());
   }

   static void validate(Transformer... transformers) {
      if (transformers == null) {
         throw new NullPointerException("The transformer array must not be null");
      } else {
         for(int i = 0; i < transformers.length; ++i) {
            if (transformers[i] == null) {
               throw new NullPointerException("The transformer array must not contain a null transformer, index " + i + " was null");
            }
         }

      }
   }

   static Transformer coerce(Transformer transformer) {
      return transformer;
   }
}
