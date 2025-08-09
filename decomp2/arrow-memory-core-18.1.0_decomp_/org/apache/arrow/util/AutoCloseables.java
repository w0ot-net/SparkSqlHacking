package org.apache.arrow.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public final class AutoCloseables {
   private static final AutoCloseable noOpAutocloseable = new AutoCloseable() {
      public void close() {
      }
   };

   private AutoCloseables() {
   }

   public static AutoCloseable all(final Collection autoCloseables) {
      return new AutoCloseable() {
         public void close() throws Exception {
            AutoCloseables.close((Iterable)autoCloseables);
         }
      };
   }

   public static void close(Throwable t, AutoCloseable... autoCloseables) {
      close(t, (Iterable)Arrays.asList(autoCloseables));
   }

   public static void close(Throwable t, Iterable autoCloseables) {
      try {
         close(autoCloseables);
      } catch (Exception e) {
         t.addSuppressed(e);
      }

   }

   public static void close(AutoCloseable... autoCloseables) throws Exception {
      close((Iterable)Arrays.asList(autoCloseables));
   }

   public static void close(Iterable ac) throws Exception {
      if (ac != null) {
         if (ac instanceof AutoCloseable) {
            ((AutoCloseable)ac).close();
         } else {
            Exception topLevelException = null;

            for(AutoCloseable closeable : ac) {
               try {
                  if (closeable != null) {
                     closeable.close();
                  }
               } catch (Exception e) {
                  if (topLevelException == null) {
                     topLevelException = e;
                  } else if (e != topLevelException) {
                     topLevelException.addSuppressed(e);
                  }
               }
            }

            if (topLevelException != null) {
               throw topLevelException;
            }
         }
      }
   }

   @SafeVarargs
   public static void close(Iterable... closeables) throws Exception {
      close(flatten(closeables));
   }

   @SafeVarargs
   private static Iterable flatten(final Iterable... closeables) {
      return new Iterable() {
         public Iterator iterator() {
            return Arrays.stream(closeables).flatMap((i) -> StreamSupport.stream(i.spliterator(), false)).iterator();
         }
      };
   }

   public static Iterable iter(AutoCloseable... ac) {
      if (ac.length == 0) {
         return Collections.emptyList();
      } else {
         List<AutoCloseable> nonNullAc = new ArrayList();

         for(AutoCloseable autoCloseable : ac) {
            if (autoCloseable != null) {
               nonNullAc.add(autoCloseable);
            }
         }

         return nonNullAc;
      }
   }

   public static RollbackCloseable rollbackable(AutoCloseable... closeables) {
      return new RollbackCloseable(closeables);
   }

   public static void closeNoChecked(AutoCloseable autoCloseable) {
      if (autoCloseable != null) {
         try {
            autoCloseable.close();
         } catch (Exception e) {
            throw new RuntimeException("Exception while closing: " + e.getMessage(), e);
         }
      }

   }

   public static AutoCloseable noop() {
      return noOpAutocloseable;
   }

   public static class RollbackCloseable implements AutoCloseable {
      private boolean commit = false;
      private List closeables;

      public RollbackCloseable(AutoCloseable... closeables) {
         this.closeables = new ArrayList(Arrays.asList(closeables));
      }

      public AutoCloseable add(AutoCloseable t) {
         this.closeables.add(t);
         return t;
      }

      public void addAll(AutoCloseable... list) {
         this.closeables.addAll(Arrays.asList(list));
      }

      public void addAll(Iterable list) {
         for(AutoCloseable ac : list) {
            this.closeables.add(ac);
         }

      }

      public void commit() {
         this.commit = true;
      }

      public void close() throws Exception {
         if (!this.commit) {
            AutoCloseables.close((Iterable)this.closeables);
         }

      }
   }
}
