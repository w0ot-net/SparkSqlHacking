package org.apache.arrow.memory.util;

import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;

public class HistoricalLog {
   private final Deque history;
   private final String idString;
   private final int limit;
   private @Nullable Event firstEvent;

   @FormatMethod
   public HistoricalLog(@FormatString String idStringFormat, Object... args) {
      this(Integer.MAX_VALUE, idStringFormat, args);
   }

   @FormatMethod
   public HistoricalLog(int limit, @FormatString String idStringFormat, Object... args) {
      this.history = new ArrayDeque();
      this.limit = limit;
      this.idString = String.format(idStringFormat, args);
      this.firstEvent = null;
   }

   @FormatMethod
   public synchronized void recordEvent(@FormatString String noteFormat, Object... args) {
      String note = String.format(noteFormat, args);
      Event event = new Event(note);
      if (this.firstEvent == null) {
         this.firstEvent = event;
      }

      if (this.history.size() == this.limit) {
         this.history.removeFirst();
      }

      this.history.add(event);
   }

   public void buildHistory(StringBuilder sb, boolean includeStackTrace) {
      this.buildHistory(sb, 0, includeStackTrace);
   }

   public synchronized void buildHistory(StringBuilder sb, int indent, boolean includeStackTrace) {
      char[] indentation = new char[indent];
      char[] innerIndentation = new char[indent + 2];
      Arrays.fill(indentation, ' ');
      Arrays.fill(innerIndentation, ' ');
      sb.append(indentation).append("event log for: ").append(this.idString).append('\n');
      if (this.firstEvent != null) {
         long time = this.firstEvent.time;
         String note = this.firstEvent.note;
         StackTrace stackTrace = this.firstEvent.stackTrace;
         sb.append(innerIndentation).append(time).append(' ').append(note).append('\n');
         if (includeStackTrace) {
            stackTrace.writeToBuilder(sb, indent + 2);
         }

         for(Event event : this.history) {
            if (event != this.firstEvent) {
               sb.append(innerIndentation).append("  ").append(event.time).append(' ').append(event.note).append('\n');
               if (includeStackTrace) {
                  event.stackTrace.writeToBuilder(sb, indent + 2);
                  sb.append('\n');
               }
            }
         }
      }

   }

   public void logHistory(Logger logger) {
      StringBuilder sb = new StringBuilder();
      this.buildHistory(sb, 0, true);
      logger.debug(sb.toString());
   }

   private static class Event {
      private final String note;
      private final StackTrace stackTrace;
      private final long time;

      public Event(String note) {
         this.note = note;
         this.time = System.nanoTime();
         this.stackTrace = new StackTrace();
      }
   }
}
