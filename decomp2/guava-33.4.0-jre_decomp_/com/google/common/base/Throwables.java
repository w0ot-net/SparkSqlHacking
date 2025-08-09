package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Throwables {
   @J2ktIncompatible
   @GwtIncompatible
   private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
   @J2ktIncompatible
   @GwtIncompatible
   @VisibleForTesting
   static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static final Object jla = getJLA();
   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static final Method getStackTraceElementMethod;
   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static final Method getStackTraceDepthMethod;

   private Throwables() {
   }

   @GwtIncompatible
   public static void throwIfInstanceOf(Throwable throwable, Class declaredType) throws Throwable {
      Preconditions.checkNotNull(throwable);
      if (declaredType.isInstance(throwable)) {
         throw (Throwable)declaredType.cast(throwable);
      }
   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static void propagateIfInstanceOf(@CheckForNull Throwable throwable, Class declaredType) throws Throwable {
      if (throwable != null) {
         throwIfInstanceOf(throwable, declaredType);
      }

   }

   public static void throwIfUnchecked(Throwable throwable) {
      Preconditions.checkNotNull(throwable);
      if (throwable instanceof RuntimeException) {
         throw (RuntimeException)throwable;
      } else if (throwable instanceof Error) {
         throw (Error)throwable;
      }
   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static void propagateIfPossible(@CheckForNull Throwable throwable) {
      if (throwable != null) {
         throwIfUnchecked(throwable);
      }

   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static void propagateIfPossible(@CheckForNull Throwable throwable, Class declaredType) throws Throwable {
      propagateIfInstanceOf(throwable, declaredType);
      propagateIfPossible(throwable);
   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static void propagateIfPossible(@CheckForNull Throwable throwable, Class declaredType1, Class declaredType2) throws Throwable, Throwable {
      Preconditions.checkNotNull(declaredType2);
      propagateIfInstanceOf(throwable, declaredType1);
      propagateIfPossible(throwable, declaredType2);
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static RuntimeException propagate(Throwable throwable) {
      throwIfUnchecked(throwable);
      throw new RuntimeException(throwable);
   }

   public static Throwable getRootCause(Throwable throwable) {
      Throwable slowPointer = throwable;

      Throwable cause;
      for(boolean advanceSlowPointer = false; (cause = throwable.getCause()) != null; advanceSlowPointer = !advanceSlowPointer) {
         throwable = cause;
         if (cause == slowPointer) {
            throw new IllegalArgumentException("Loop in causal chain detected.", cause);
         }

         if (advanceSlowPointer) {
            slowPointer = slowPointer.getCause();
         }
      }

      return throwable;
   }

   public static List getCausalChain(Throwable throwable) {
      Preconditions.checkNotNull(throwable);
      List<Throwable> causes = new ArrayList(4);
      causes.add(throwable);
      Throwable slowPointer = throwable;

      Throwable cause;
      for(boolean advanceSlowPointer = false; (cause = throwable.getCause()) != null; advanceSlowPointer = !advanceSlowPointer) {
         throwable = cause;
         causes.add(cause);
         if (cause == slowPointer) {
            throw new IllegalArgumentException("Loop in causal chain detected.", cause);
         }

         if (advanceSlowPointer) {
            slowPointer = slowPointer.getCause();
         }
      }

      return Collections.unmodifiableList(causes);
   }

   @CheckForNull
   @GwtIncompatible
   public static Throwable getCauseAs(Throwable throwable, Class expectedCauseType) {
      try {
         return (Throwable)expectedCauseType.cast(throwable.getCause());
      } catch (ClassCastException e) {
         e.initCause(throwable);
         throw e;
      }
   }

   @GwtIncompatible
   public static String getStackTraceAsString(Throwable throwable) {
      StringWriter stringWriter = new StringWriter();
      throwable.printStackTrace(new PrintWriter(stringWriter));
      return stringWriter.toString();
   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static List lazyStackTrace(Throwable throwable) {
      return lazyStackTraceIsLazy() ? jlaStackTrace(throwable) : Collections.unmodifiableList(Arrays.asList(throwable.getStackTrace()));
   }

   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static boolean lazyStackTraceIsLazy() {
      return getStackTraceElementMethod != null && getStackTraceDepthMethod != null;
   }

   @J2ktIncompatible
   @GwtIncompatible
   private static List jlaStackTrace(final Throwable t) {
      Preconditions.checkNotNull(t);
      return new AbstractList() {
         public StackTraceElement get(int n) {
            return (StackTraceElement)Throwables.invokeAccessibleNonThrowingMethod((Method)java.util.Objects.requireNonNull(Throwables.getStackTraceElementMethod), java.util.Objects.requireNonNull(Throwables.jla), t, n);
         }

         public int size() {
            return (Integer)Throwables.invokeAccessibleNonThrowingMethod((Method)java.util.Objects.requireNonNull(Throwables.getStackTraceDepthMethod), java.util.Objects.requireNonNull(Throwables.jla), t);
         }
      };
   }

   @J2ktIncompatible
   @GwtIncompatible
   private static Object invokeAccessibleNonThrowingMethod(Method method, Object receiver, Object... params) {
      try {
         return method.invoke(receiver, params);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
         throw propagate(e.getCause());
      }
   }

   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static Object getJLA() {
      try {
         Class<?> sharedSecrets = Class.forName("sun.misc.SharedSecrets", false, (ClassLoader)null);
         Method langAccess = sharedSecrets.getMethod("getJavaLangAccess");
         return langAccess.invoke((Object)null);
      } catch (ThreadDeath death) {
         throw death;
      } catch (Throwable var3) {
         return null;
      }
   }

   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static Method getGetMethod() {
      return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
   }

   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static Method getSizeMethod(Object jla) {
      try {
         Method getStackTraceDepth = getJlaMethod("getStackTraceDepth", Throwable.class);
         if (getStackTraceDepth == null) {
            return null;
         } else {
            getStackTraceDepth.invoke(jla, new Throwable());
            return getStackTraceDepth;
         }
      } catch (IllegalAccessException | InvocationTargetException | UnsupportedOperationException var2) {
         return null;
      }
   }

   @CheckForNull
   @J2ktIncompatible
   @GwtIncompatible
   private static Method getJlaMethod(String name, Class... parameterTypes) throws ThreadDeath {
      try {
         return Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader)null).getMethod(name, parameterTypes);
      } catch (ThreadDeath death) {
         throw death;
      } catch (Throwable var4) {
         return null;
      }
   }

   static {
      getStackTraceElementMethod = jla == null ? null : getGetMethod();
      getStackTraceDepthMethod = jla == null ? null : getSizeMethod(jla);
   }
}
