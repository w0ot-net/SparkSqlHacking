package org.apache.arrow.memory.util;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

public class StackTrace {
   private final @Nullable StackTraceElement[] stackTraceElements;

   public StackTrace() {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
      this.stackTraceElements = (StackTraceElement[])Arrays.copyOfRange(stack, 2, stack.length);
   }

   public void writeToBuilder(StringBuilder sb, int indent) {
      char[] indentation = new char[indent * 2];
      Arrays.fill(indentation, ' ');

      for(StackTraceElement ste : this.stackTraceElements) {
         if (ste != null) {
            sb.append(indentation).append("at ").append(ste.getClassName()).append('.').append(ste.getMethodName()).append('(').append(ste.getFileName()).append(':').append(Integer.toString(ste.getLineNumber())).append(")\n");
         }
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      this.writeToBuilder(sb, 0);
      return sb.toString();
   }
}
