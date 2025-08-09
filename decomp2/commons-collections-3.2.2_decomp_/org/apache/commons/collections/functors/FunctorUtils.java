package org.apache.commons.collections.functors;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

class FunctorUtils {
   static final String UNSAFE_SERIALIZABLE_PROPERTY = "org.apache.commons.collections.enableUnsafeSerialization";

   private FunctorUtils() {
   }

   static Predicate[] copy(Predicate[] predicates) {
      return predicates == null ? null : (Predicate[])((Predicate[])predicates.clone());
   }

   static void validate(Predicate[] predicates) {
      if (predicates == null) {
         throw new IllegalArgumentException("The predicate array must not be null");
      } else {
         for(int i = 0; i < predicates.length; ++i) {
            if (predicates[i] == null) {
               throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null");
            }
         }

      }
   }

   static Predicate[] validate(Collection predicates) {
      if (predicates == null) {
         throw new IllegalArgumentException("The predicate collection must not be null");
      } else {
         Predicate[] preds = new Predicate[predicates.size()];
         int i = 0;

         for(Iterator it = predicates.iterator(); it.hasNext(); ++i) {
            preds[i] = (Predicate)it.next();
            if (preds[i] == null) {
               throw new IllegalArgumentException("The predicate collection must not contain a null predicate, index " + i + " was null");
            }
         }

         return preds;
      }
   }

   static Closure[] copy(Closure[] closures) {
      return closures == null ? null : (Closure[])((Closure[])closures.clone());
   }

   static void validate(Closure[] closures) {
      if (closures == null) {
         throw new IllegalArgumentException("The closure array must not be null");
      } else {
         for(int i = 0; i < closures.length; ++i) {
            if (closures[i] == null) {
               throw new IllegalArgumentException("The closure array must not contain a null closure, index " + i + " was null");
            }
         }

      }
   }

   static Transformer[] copy(Transformer[] transformers) {
      return transformers == null ? null : (Transformer[])((Transformer[])transformers.clone());
   }

   static void validate(Transformer[] transformers) {
      if (transformers == null) {
         throw new IllegalArgumentException("The transformer array must not be null");
      } else {
         for(int i = 0; i < transformers.length; ++i) {
            if (transformers[i] == null) {
               throw new IllegalArgumentException("The transformer array must not contain a null transformer, index " + i + " was null");
            }
         }

      }
   }

   static void checkUnsafeSerialization(Class clazz) {
      String unsafeSerializableProperty;
      try {
         unsafeSerializableProperty = (String)AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               return System.getProperty("org.apache.commons.collections.enableUnsafeSerialization");
            }
         });
      } catch (SecurityException var3) {
         unsafeSerializableProperty = null;
      }

      if (!"true".equalsIgnoreCase(unsafeSerializableProperty)) {
         throw new UnsupportedOperationException("Serialization support for " + clazz.getName() + " is disabled for security reasons. " + "To enable it set system property '" + "org.apache.commons.collections.enableUnsafeSerialization" + "' to 'true', " + "but you must ensure that your application does not de-serialize objects from untrusted sources.");
      }
   }
}
