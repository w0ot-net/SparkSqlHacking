package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Ordering;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class FuturesGetChecked {
   private static final Ordering WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf((input) -> Arrays.asList(input.getParameterTypes()).contains(String.class)).reverse();

   @ParametricNullness
   @CanIgnoreReturnValue
   static Object getChecked(Future future, Class exceptionClass) throws Exception {
      return getChecked(bestGetCheckedTypeValidator(), future, exceptionClass);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   @VisibleForTesting
   static Object getChecked(GetCheckedTypeValidator validator, Future future, Class exceptionClass) throws Exception {
      validator.validateClass(exceptionClass);

      try {
         return future.get();
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw newWithCause(exceptionClass, e);
      } catch (ExecutionException e) {
         wrapAndThrowExceptionOrError(e.getCause(), exceptionClass);
         throw new AssertionError();
      }
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   static Object getChecked(Future future, Class exceptionClass, long timeout, TimeUnit unit) throws Exception {
      bestGetCheckedTypeValidator().validateClass(exceptionClass);

      try {
         return future.get(timeout, unit);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw newWithCause(exceptionClass, e);
      } catch (TimeoutException e) {
         throw newWithCause(exceptionClass, e);
      } catch (ExecutionException e) {
         wrapAndThrowExceptionOrError(e.getCause(), exceptionClass);
         throw new AssertionError();
      }
   }

   private static GetCheckedTypeValidator bestGetCheckedTypeValidator() {
      return FuturesGetChecked.GetCheckedTypeValidatorHolder.BEST_VALIDATOR;
   }

   @VisibleForTesting
   static GetCheckedTypeValidator weakSetValidator() {
      return FuturesGetChecked.GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
   }

   @VisibleForTesting
   static GetCheckedTypeValidator classValueValidator() {
      return FuturesGetChecked.GetCheckedTypeValidatorHolder.ClassValueValidator.INSTANCE;
   }

   private static void wrapAndThrowExceptionOrError(Throwable cause, Class exceptionClass) throws Exception {
      if (cause instanceof Error) {
         throw new ExecutionError((Error)cause);
      } else if (cause instanceof RuntimeException) {
         throw new UncheckedExecutionException(cause);
      } else {
         throw newWithCause(exceptionClass, cause);
      }
   }

   private static boolean hasConstructorUsableByGetChecked(Class exceptionClass) {
      try {
         newWithCause(exceptionClass, new Exception());
         return true;
      } catch (Error | RuntimeException var2) {
         return false;
      }
   }

   private static Exception newWithCause(Class exceptionClass, Throwable cause) {
      List<Constructor<X>> constructors = Arrays.asList(exceptionClass.getConstructors());

      for(Constructor constructor : preferringStrings(constructors)) {
         X instance = (X)((Exception)newFromConstructor(constructor, cause));
         if (instance != null) {
            if (instance.getCause() == null) {
               instance.initCause(cause);
            }

            return instance;
         }
      }

      throw new IllegalArgumentException("No appropriate constructor for exception of type " + exceptionClass + " in response to chained exception", cause);
   }

   private static List preferringStrings(List constructors) {
      return WITH_STRING_PARAM_FIRST.sortedCopy(constructors);
   }

   @CheckForNull
   private static Object newFromConstructor(Constructor constructor, Throwable cause) {
      Class<?>[] paramTypes = constructor.getParameterTypes();
      Object[] params = new Object[paramTypes.length];

      for(int i = 0; i < paramTypes.length; ++i) {
         Class<?> paramType = paramTypes[i];
         if (paramType.equals(String.class)) {
            params[i] = cause.toString();
         } else {
            if (!paramType.equals(Throwable.class)) {
               return null;
            }

            params[i] = cause;
         }
      }

      try {
         return constructor.newInstance(params);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException var6) {
         return null;
      }
   }

   @VisibleForTesting
   static boolean isCheckedException(Class type) {
      return !RuntimeException.class.isAssignableFrom(type);
   }

   @VisibleForTesting
   static void checkExceptionClassValidity(Class exceptionClass) {
      Preconditions.checkArgument(isCheckedException(exceptionClass), "Futures.getChecked exception type (%s) must not be a RuntimeException", (Object)exceptionClass);
      Preconditions.checkArgument(hasConstructorUsableByGetChecked(exceptionClass), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", (Object)exceptionClass);
   }

   private FuturesGetChecked() {
   }

   @VisibleForTesting
   static class GetCheckedTypeValidatorHolder {
      static final String CLASS_VALUE_VALIDATOR_NAME = GetCheckedTypeValidatorHolder.class.getName() + "$ClassValueValidator";
      static final GetCheckedTypeValidator BEST_VALIDATOR = getBestValidator();

      static GetCheckedTypeValidator getBestValidator() {
         try {
            Class<? extends Enum> theClass = Class.forName(CLASS_VALUE_VALIDATOR_NAME).asSubclass(Enum.class);
            return (GetCheckedTypeValidator)((Enum[])theClass.getEnumConstants())[0];
         } catch (RuntimeException | Error | ClassNotFoundException var1) {
            return FuturesGetChecked.weakSetValidator();
         }
      }

      static enum ClassValueValidator implements GetCheckedTypeValidator {
         INSTANCE;

         private static final ClassValue isValidClass = new ClassValue() {
            protected Boolean computeValue(Class type) {
               FuturesGetChecked.checkExceptionClassValidity(type.asSubclass(Exception.class));
               return true;
            }
         };

         public void validateClass(Class exceptionClass) {
            isValidClass.get(exceptionClass);
         }

         // $FF: synthetic method
         private static ClassValueValidator[] $values() {
            return new ClassValueValidator[]{INSTANCE};
         }
      }

      static enum WeakSetValidator implements GetCheckedTypeValidator {
         INSTANCE;

         private static final Set validClasses = new CopyOnWriteArraySet();

         public void validateClass(Class exceptionClass) {
            for(WeakReference knownGood : validClasses) {
               if (exceptionClass.equals(knownGood.get())) {
                  return;
               }
            }

            FuturesGetChecked.checkExceptionClassValidity(exceptionClass);
            if (validClasses.size() > 1000) {
               validClasses.clear();
            }

            validClasses.add(new WeakReference(exceptionClass));
         }

         // $FF: synthetic method
         private static WeakSetValidator[] $values() {
            return new WeakSetValidator[]{INSTANCE};
         }
      }
   }

   @VisibleForTesting
   interface GetCheckedTypeValidator {
      void validateClass(Class exceptionClass);
   }
}
