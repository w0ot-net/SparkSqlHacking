package org.apache.commons.io.function;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Supplier;

public final class Uncheck {
   public static void accept(IOBiConsumer consumer, Object t, Object u) {
      try {
         consumer.accept(t, u);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static void accept(IOConsumer consumer, Object t) {
      try {
         consumer.accept(t);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static void accept(IOIntConsumer consumer, int i) {
      try {
         consumer.accept(i);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static void accept(IOTriConsumer consumer, Object t, Object u, Object v) {
      try {
         consumer.accept(t, u, v);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object apply(IOBiFunction function, Object t, Object u) {
      try {
         return function.apply(t, u);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object apply(IOFunction function, Object t) {
      try {
         return function.apply(t);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object apply(IOQuadFunction function, Object t, Object u, Object v, Object w) {
      try {
         return function.apply(t, u, v, w);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object apply(IOTriFunction function, Object t, Object u, Object v) {
      try {
         return function.apply(t, u, v);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static int compare(IOComparator comparator, Object t, Object u) {
      try {
         return comparator.compare(t, u);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object get(IOSupplier supplier) {
      try {
         return supplier.get();
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static Object get(IOSupplier supplier, Supplier message) {
      try {
         return supplier.get();
      } catch (IOException e) {
         throw wrap(e, message);
      }
   }

   public static int getAsInt(IOIntSupplier supplier) {
      try {
         return supplier.getAsInt();
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static int getAsInt(IOIntSupplier supplier, Supplier message) {
      try {
         return supplier.getAsInt();
      } catch (IOException e) {
         throw wrap(e, message);
      }
   }

   public static long getAsLong(IOLongSupplier supplier) {
      try {
         return supplier.getAsLong();
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static long getAsLong(IOLongSupplier supplier, Supplier message) {
      try {
         return supplier.getAsLong();
      } catch (IOException e) {
         throw wrap(e, message);
      }
   }

   public static void run(IORunnable runnable) {
      try {
         runnable.run();
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   public static void run(IORunnable runnable, Supplier message) {
      try {
         runnable.run();
      } catch (IOException e) {
         throw wrap(e, message);
      }
   }

   public static boolean test(IOPredicate predicate, Object t) {
      try {
         return predicate.test(t);
      } catch (IOException e) {
         throw wrap(e);
      }
   }

   private static UncheckedIOException wrap(IOException e) {
      return new UncheckedIOException(e);
   }

   private static UncheckedIOException wrap(IOException e, Supplier message) {
      return new UncheckedIOException((String)message.get(), e);
   }

   private Uncheck() {
   }
}
