package org.apache.logging.log4j.core.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive
public class AppenderControlArraySet {
   private static final AtomicReferenceFieldUpdater appenderArrayUpdater = AtomicReferenceFieldUpdater.newUpdater(AppenderControlArraySet.class, AppenderControl[].class, "appenderArray");
   private volatile AppenderControl[] appenderArray;

   public AppenderControlArraySet() {
      this.appenderArray = AppenderControl.EMPTY_ARRAY;
   }

   public boolean add(final AppenderControl control) {
      boolean success;
      do {
         AppenderControl[] original = this.appenderArray;

         for(AppenderControl existing : original) {
            if (existing.equals(control)) {
               return false;
            }
         }

         AppenderControl[] copy = (AppenderControl[])Arrays.copyOf(original, original.length + 1);
         copy[copy.length - 1] = control;
         success = appenderArrayUpdater.compareAndSet(this, original, copy);
      } while(!success);

      return true;
   }

   public AppenderControl remove(final String name) {
      boolean success;
      do {
         success = true;
         AppenderControl[] original = this.appenderArray;

         for(int i = 0; i < original.length; ++i) {
            AppenderControl appenderControl = original[i];
            if (Objects.equals(name, appenderControl.getAppenderName())) {
               AppenderControl[] copy = this.removeElementAt(i, original);
               if (appenderArrayUpdater.compareAndSet(this, original, copy)) {
                  return appenderControl;
               }

               success = false;
               break;
            }
         }
      } while(!success);

      return null;
   }

   private AppenderControl[] removeElementAt(final int i, final AppenderControl[] array) {
      AppenderControl[] result = (AppenderControl[])Arrays.copyOf(array, array.length - 1);
      System.arraycopy(array, i + 1, result, i, result.length - i);
      return result;
   }

   public Map asMap() {
      Map<String, Appender> result = new HashMap();

      for(AppenderControl appenderControl : this.appenderArray) {
         result.put(appenderControl.getAppenderName(), appenderControl.getAppender());
      }

      return result;
   }

   public AppenderControl[] clear() {
      return (AppenderControl[])appenderArrayUpdater.getAndSet(this, AppenderControl.EMPTY_ARRAY);
   }

   public boolean isEmpty() {
      return this.appenderArray.length == 0;
   }

   public AppenderControl[] get() {
      return this.appenderArray;
   }

   public String toString() {
      return "AppenderControlArraySet [appenderArray=" + Arrays.toString(this.appenderArray) + "]";
   }
}
