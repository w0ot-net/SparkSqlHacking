package org.glassfish.jersey.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.util.Producer;

public class Errors {
   private static final Logger LOGGER = Logger.getLogger(Errors.class.getName());
   private static final ThreadLocal errors = new ThreadLocal();
   private final ArrayList issues = new ArrayList(0);
   private Deque mark = new ArrayDeque(4);
   private int stack = 0;

   public static void error(String message, Severity severity) {
      error((Object)null, message, severity);
   }

   public static void error(Object source, String message, Severity severity) {
      getInstance().issues.add(new ErrorMessage(source, message, severity));
   }

   public static void fatal(Object source, String message) {
      error(source, message, Severity.FATAL);
   }

   public static void warning(Object source, String message) {
      error(source, message, Severity.WARNING);
   }

   public static void hint(Object source, String message) {
      getInstance().issues.add(new ErrorMessage(source, message, Severity.HINT));
   }

   private static void processErrors(boolean throwException) {
      List<ErrorMessage> errors = new ArrayList(((Errors)Errors.errors.get()).issues);
      boolean isFatal = logErrors(errors);
      if (throwException && isFatal) {
         throw new ErrorMessagesException(errors);
      }
   }

   public static boolean logErrors(boolean afterMark) {
      return logErrors(getInstance()._getErrorMessages(afterMark));
   }

   private static boolean logErrors(Collection errors) {
      boolean isFatal = false;
      if (!errors.isEmpty()) {
         StringBuilder fatals = new StringBuilder("\n");
         StringBuilder warnings = new StringBuilder();
         StringBuilder hints = new StringBuilder();

         for(ErrorMessage error : errors) {
            switch (error.getSeverity()) {
               case FATAL:
                  isFatal = true;
                  fatals.append(LocalizationMessages.ERROR_MSG(error.getMessage())).append('\n');
                  break;
               case WARNING:
                  warnings.append(LocalizationMessages.WARNING_MSG(error.getMessage())).append('\n');
                  break;
               case HINT:
                  hints.append(LocalizationMessages.HINT_MSG(error.getMessage())).append('\n');
            }
         }

         if (isFatal) {
            LOGGER.severe(LocalizationMessages.ERRORS_AND_WARNINGS_DETECTED(fatals.append(warnings).append(hints).toString()));
         } else {
            if (warnings.length() > 0) {
               LOGGER.warning(LocalizationMessages.WARNINGS_DETECTED(warnings.toString()));
            }

            if (hints.length() > 0) {
               LOGGER.config(LocalizationMessages.HINTS_DETECTED(hints.toString()));
            }
         }
      }

      return isFatal;
   }

   public static boolean fatalIssuesFound() {
      for(ErrorMessage message : getInstance().issues) {
         if (message.getSeverity() == Severity.FATAL) {
            return true;
         }
      }

      return false;
   }

   public static Object process(Producer producer) {
      return process(producer, false);
   }

   public static Object process(Callable task) throws Exception {
      return process(task, true);
   }

   public static Object processWithException(Producer producer) {
      return process(producer, true);
   }

   public static void process(final Runnable task) {
      process(new Producer() {
         public Void call() {
            task.run();
            return null;
         }
      }, false);
   }

   public static void processWithException(final Runnable task) {
      process(new Producer() {
         public Void call() {
            task.run();
            return null;
         }
      }, true);
   }

   private static Object process(Producer task, boolean throwException) {
      try {
         return process((Callable)task, throwException);
      } catch (RuntimeException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new RuntimeException(ex);
      }
   }

   private static Object process(Callable task, boolean throwException) throws Exception {
      Errors instance = (Errors)errors.get();
      if (instance == null) {
         instance = new Errors();
         errors.set(instance);
      }

      instance.preProcess();
      Exception caught = null;

      try {
         Object var4 = task.call();
         return var4;
      } catch (Exception re) {
         caught = re;
      } finally {
         instance.postProcess(throwException && caught == null);
      }

      throw caught;
   }

   private static Errors getInstance() {
      Errors instance = (Errors)errors.get();
      if (instance == null) {
         throw new IllegalStateException(LocalizationMessages.NO_ERROR_PROCESSING_IN_SCOPE());
      } else if (instance.stack == 0) {
         errors.remove();
         throw new IllegalStateException(LocalizationMessages.NO_ERROR_PROCESSING_IN_SCOPE());
      } else {
         return instance;
      }
   }

   public static List getErrorMessages() {
      return getErrorMessages(false);
   }

   public static List getErrorMessages(boolean afterMark) {
      return getInstance()._getErrorMessages(afterMark);
   }

   public static void mark() {
      getInstance()._mark();
   }

   public static void unmark() {
      getInstance()._unmark();
   }

   public static void reset() {
      getInstance()._reset();
   }

   private Errors() {
   }

   private void _mark() {
      this.mark.addLast(this.issues.size());
   }

   private void _unmark() {
      this.mark.pollLast();
   }

   private void _reset() {
      Integer _pos = (Integer)this.mark.pollLast();
      int markedPos = _pos == null ? -1 : _pos;
      if (markedPos >= 0 && markedPos < this.issues.size()) {
         this.issues.subList(markedPos, this.issues.size()).clear();
      }

   }

   private void preProcess() {
      ++this.stack;
   }

   private void postProcess(boolean throwException) {
      --this.stack;
      if (this.stack == 0) {
         try {
            if (!this.issues.isEmpty()) {
               processErrors(throwException);
            }
         } finally {
            errors.remove();
         }
      }

   }

   private List _getErrorMessages(boolean afterMark) {
      if (afterMark) {
         Integer _pos = (Integer)this.mark.peekLast();
         int markedPos = _pos == null ? -1 : _pos;
         if (markedPos >= 0 && markedPos < this.issues.size()) {
            return Collections.unmodifiableList(new ArrayList(this.issues.subList(markedPos, this.issues.size())));
         }
      }

      return Collections.unmodifiableList(new ArrayList(this.issues));
   }

   public static class ErrorMessagesException extends RuntimeException {
      private final List messages;

      private ErrorMessagesException(List messages) {
         this.messages = messages;
      }

      public List getMessages() {
         return this.messages;
      }
   }

   public static class ErrorMessage {
      private final Object source;
      private final String message;
      private final Severity severity;

      private ErrorMessage(Object source, String message, Severity severity) {
         this.source = source;
         this.message = message;
         this.severity = severity;
      }

      public Severity getSeverity() {
         return this.severity;
      }

      public String getMessage() {
         return this.message;
      }

      public Object getSource() {
         return this.source;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ErrorMessage that = (ErrorMessage)o;
            if (this.message != null) {
               if (!this.message.equals(that.message)) {
                  return false;
               }
            } else if (that.message != null) {
               return false;
            }

            if (this.severity != that.severity) {
               return false;
            } else {
               if (this.source != null) {
                  if (!this.source.equals(that.source)) {
                     return false;
                  }
               } else if (that.source != null) {
                  return false;
               }

               return true;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.source != null ? this.source.hashCode() : 0;
         result = 31 * result + (this.message != null ? this.message.hashCode() : 0);
         result = 31 * result + (this.severity != null ? this.severity.hashCode() : 0);
         return result;
      }
   }
}
