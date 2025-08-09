package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Closer implements Closeable {
   @VisibleForTesting
   final Suppressor suppressor;
   private final Deque stack = new ArrayDeque(4);
   @CheckForNull
   private Throwable thrown;
   private static final Suppressor SUPPRESSING_SUPPRESSOR = (closeable, thrown, suppressed) -> {
      if (thrown != suppressed) {
         try {
            thrown.addSuppressed(suppressed);
         } catch (Throwable var4) {
            Closeables.logger.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, suppressed);
         }

      }
   };

   public static Closer create() {
      return new Closer(SUPPRESSING_SUPPRESSOR);
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
      Throwables.throwIfInstanceOf(e, IOException.class);
      Throwables.throwIfUnchecked(e);
      throw new RuntimeException(e);
   }

   public RuntimeException rethrow(Throwable e, Class declaredType) throws IOException, Exception {
      Preconditions.checkNotNull(e);
      this.thrown = e;
      Throwables.throwIfInstanceOf(e, IOException.class);
      Throwables.throwIfInstanceOf(e, declaredType);
      Throwables.throwIfUnchecked(e);
      throw new RuntimeException(e);
   }

   public RuntimeException rethrow(Throwable e, Class declaredType1, Class declaredType2) throws IOException, Exception, Exception {
      Preconditions.checkNotNull(e);
      this.thrown = e;
      Throwables.throwIfInstanceOf(e, IOException.class);
      Throwables.throwIfInstanceOf(e, declaredType1);
      Throwables.throwIfInstanceOf(e, declaredType2);
      Throwables.throwIfUnchecked(e);
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
         Throwables.throwIfInstanceOf(throwable, IOException.class);
         Throwables.throwIfUnchecked(throwable);
         throw new AssertionError(throwable);
      }
   }

   @VisibleForTesting
   interface Suppressor {
      void suppress(Closeable closeable, Throwable thrown, Throwable suppressed);
   }
}
