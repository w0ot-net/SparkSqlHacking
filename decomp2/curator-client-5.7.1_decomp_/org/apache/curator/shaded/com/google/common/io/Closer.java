package org.apache.curator.shaded.com.google.common.io;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Throwables;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Closer implements Closeable {
   private static final Suppressor SUPPRESSOR;
   @VisibleForTesting
   final Suppressor suppressor;
   private final Deque stack = new ArrayDeque(4);
   @CheckForNull
   private Throwable thrown;

   public static Closer create() {
      return new Closer(SUPPRESSOR);
   }

   @VisibleForTesting
   Closer(Suppressor suppressor) {
      this.suppressor = (Suppressor)Preconditions.checkNotNull(suppressor);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Closeable register(@ParametricNullness Closeable closeable) {
      if (closeable != null) {
         this.stack.addFirst(closeable);
      }

      return closeable;
   }

   public RuntimeException rethrow(Throwable e) throws IOException {
      Preconditions.checkNotNull(e);
      this.thrown = e;
      Throwables.propagateIfPossible(e, IOException.class);
      throw new RuntimeException(e);
   }

   public RuntimeException rethrow(Throwable e, Class declaredType) throws IOException, Exception {
      Preconditions.checkNotNull(e);
      this.thrown = e;
      Throwables.propagateIfPossible(e, IOException.class);
      Throwables.propagateIfPossible(e, declaredType);
      throw new RuntimeException(e);
   }

   public RuntimeException rethrow(Throwable e, Class declaredType1, Class declaredType2) throws IOException, Exception, Exception {
      Preconditions.checkNotNull(e);
      this.thrown = e;
      Throwables.propagateIfPossible(e, IOException.class);
      Throwables.propagateIfPossible(e, declaredType1, declaredType2);
      throw new RuntimeException(e);
   }

   public void close() throws IOException {
      Throwable throwable = this.thrown;

      while(!this.stack.isEmpty()) {
         Closeable closeable = (Closeable)this.stack.removeFirst();

         try {
            closeable.close();
         } catch (Throwable e) {
            if (throwable == null) {
               throwable = e;
            } else {
               this.suppressor.suppress(closeable, throwable, e);
            }
         }
      }

      if (this.thrown == null && throwable != null) {
         Throwables.propagateIfPossible(throwable, IOException.class);
         throw new AssertionError(throwable);
      }
   }

   static {
      SuppressingSuppressor suppressingSuppressor = Closer.SuppressingSuppressor.tryCreate();
      SUPPRESSOR = (Suppressor)(suppressingSuppressor == null ? Closer.LoggingSuppressor.INSTANCE : suppressingSuppressor);
   }

   @VisibleForTesting
   static final class LoggingSuppressor implements Suppressor {
      static final LoggingSuppressor INSTANCE = new LoggingSuppressor();

      public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed) {
         Closeables.logger.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, suppressed);
      }
   }

   @VisibleForTesting
   static final class SuppressingSuppressor implements Suppressor {
      private final Method addSuppressed;

      @CheckForNull
      static SuppressingSuppressor tryCreate() {
         Method addSuppressed;
         try {
            addSuppressed = Throwable.class.getMethod("addSuppressed", Throwable.class);
         } catch (Throwable var2) {
            return null;
         }

         return new SuppressingSuppressor(addSuppressed);
      }

      private SuppressingSuppressor(Method addSuppressed) {
         this.addSuppressed = addSuppressed;
      }

      public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed) {
         if (thrown != suppressed) {
            try {
               this.addSuppressed.invoke(thrown, suppressed);
            } catch (Throwable var5) {
               Closer.LoggingSuppressor.INSTANCE.suppress(closeable, thrown, suppressed);
            }

         }
      }
   }

   @VisibleForTesting
   interface Suppressor {
      void suppress(Closeable closeable, Throwable thrown, Throwable suppressed);
   }
}
