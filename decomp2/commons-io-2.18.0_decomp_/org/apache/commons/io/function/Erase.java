package org.apache.commons.io.function;

import java.io.IOException;

public final class Erase {
   static void accept(IOBiConsumer consumer, Object t, Object u) {
      try {
         consumer.accept(t, u);
      } catch (IOException ex) {
         rethrow(ex);
      }

   }

   static void accept(IOConsumer consumer, Object t) {
      try {
         consumer.accept(t);
      } catch (IOException ex) {
         rethrow(ex);
      }

   }

   static Object apply(IOBiFunction mapper, Object t, Object u) {
      try {
         return mapper.apply(t, u);
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   static Object apply(IOFunction mapper, Object t) {
      try {
         return mapper.apply(t);
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   static int compare(IOComparator comparator, Object t, Object u) {
      try {
         return comparator.compare(t, u);
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   static Object get(IOSupplier supplier) {
      try {
         return supplier.get();
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   public static RuntimeException rethrow(Throwable throwable) throws Throwable {
      throw throwable;
   }

   static void run(IORunnable runnable) {
      try {
         runnable.run();
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   static boolean test(IOPredicate predicate, Object t) {
      try {
         return predicate.test(t);
      } catch (IOException e) {
         throw rethrow(e);
      }
   }

   private Erase() {
   }
}
