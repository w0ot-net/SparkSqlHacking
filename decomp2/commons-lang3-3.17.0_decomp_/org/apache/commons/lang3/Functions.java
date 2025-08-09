package org.apache.commons.lang3;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableBooleanSupplier;

/** @deprecated */
@Deprecated
public class Functions {
   public static void accept(FailableBiConsumer consumer, Object object1, Object object2) {
      run(() -> consumer.accept(object1, object2));
   }

   public static void accept(FailableConsumer consumer, Object object) {
      run(() -> consumer.accept(object));
   }

   public static Object apply(FailableBiFunction function, Object input1, Object input2) {
      return get(() -> function.apply(input1, input2));
   }

   public static Object apply(FailableFunction function, Object input) {
      return get(() -> function.apply(input));
   }

   public static BiConsumer asBiConsumer(FailableBiConsumer consumer) {
      return (input1, input2) -> accept(consumer, input1, input2);
   }

   public static BiFunction asBiFunction(FailableBiFunction function) {
      return (input1, input2) -> apply(function, input1, input2);
   }

   public static BiPredicate asBiPredicate(FailableBiPredicate predicate) {
      return (input1, input2) -> test(predicate, input1, input2);
   }

   public static Callable asCallable(FailableCallable callable) {
      return () -> call(callable);
   }

   public static Consumer asConsumer(FailableConsumer consumer) {
      return (input) -> accept(consumer, input);
   }

   public static Function asFunction(FailableFunction function) {
      return (input) -> apply(function, input);
   }

   public static Predicate asPredicate(FailablePredicate predicate) {
      return (input) -> test(predicate, input);
   }

   public static Runnable asRunnable(FailableRunnable runnable) {
      return () -> run(runnable);
   }

   public static Supplier asSupplier(FailableSupplier supplier) {
      return () -> get(supplier);
   }

   public static Object call(FailableCallable callable) {
      Objects.requireNonNull(callable);
      return get(callable::call);
   }

   public static Object get(FailableSupplier supplier) {
      try {
         return supplier.get();
      } catch (Throwable t) {
         throw rethrow(t);
      }
   }

   private static boolean getAsBoolean(FailableBooleanSupplier supplier) {
      try {
         return supplier.getAsBoolean();
      } catch (Throwable t) {
         throw rethrow(t);
      }
   }

   public static RuntimeException rethrow(Throwable throwable) {
      Objects.requireNonNull(throwable, "throwable");
      ExceptionUtils.throwUnchecked(throwable);
      if (throwable instanceof IOException) {
         throw new UncheckedIOException((IOException)throwable);
      } else {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public static void run(FailableRunnable runnable) {
      try {
         runnable.run();
      } catch (Throwable t) {
         throw rethrow(t);
      }
   }

   public static Streams.FailableStream stream(Collection collection) {
      return new Streams.FailableStream(collection.stream());
   }

   public static Streams.FailableStream stream(Stream stream) {
      return new Streams.FailableStream(stream);
   }

   public static boolean test(FailableBiPredicate predicate, Object object1, Object object2) {
      return getAsBoolean(() -> predicate.test(object1, object2));
   }

   public static boolean test(FailablePredicate predicate, Object object) {
      return getAsBoolean(() -> predicate.test(object));
   }

   @SafeVarargs
   public static void tryWithResources(FailableRunnable action, FailableConsumer errorHandler, FailableRunnable... resources) {
      org.apache.commons.lang3.function.FailableRunnable<?>[] fr = new org.apache.commons.lang3.function.FailableRunnable[resources.length];
      Arrays.setAll(fr, (i) -> () -> resources[i].run());
      Objects.requireNonNull(action);
      org.apache.commons.lang3.function.FailableRunnable var10000 = action::run;
      org.apache.commons.lang3.function.FailableConsumer var10001;
      if (errorHandler != null) {
         Objects.requireNonNull(errorHandler);
         var10001 = errorHandler::accept;
      } else {
         var10001 = null;
      }

      Failable.tryWithResources(var10000, var10001, fr);
   }

   @SafeVarargs
   public static void tryWithResources(FailableRunnable action, FailableRunnable... resources) {
      tryWithResources(action, (FailableConsumer)null, resources);
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableBiConsumer {
      void accept(Object var1, Object var2) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableBiFunction {
      Object apply(Object var1, Object var2) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableBiPredicate {
      boolean test(Object var1, Object var2) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableCallable {
      Object call() throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableConsumer {
      void accept(Object var1) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableFunction {
      Object apply(Object var1) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailablePredicate {
      boolean test(Object var1) throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableRunnable {
      void run() throws Throwable;
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface FailableSupplier {
      Object get() throws Throwable;
   }
}
