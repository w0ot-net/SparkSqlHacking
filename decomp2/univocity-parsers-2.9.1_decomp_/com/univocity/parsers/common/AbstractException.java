package com.univocity.parsers.common;

import java.util.Arrays;

abstract class AbstractException extends RuntimeException {
   private static final long serialVersionUID = -2993096896413328423L;
   protected int errorContentLength = -1;

   protected AbstractException(String message, Throwable cause) {
      super(message, cause);
   }

   public final String getMessage() {
      String msg = super.getMessage();
      msg = msg == null ? this.getErrorDescription() + ": " : msg;
      String details = this.getDetails();
      if (details != null && !details.isEmpty()) {
         msg = msg + "\nInternal state when error was thrown: " + details;
      }

      msg = this.updateMessage(msg);
      return msg;
   }

   protected String updateMessage(String msg) {
      return msg;
   }

   protected abstract String getDetails();

   protected abstract String getErrorDescription();

   protected static String printIfNotEmpty(String previous, String description, Object o) {
      if (o != null && !o.toString().isEmpty()) {
         if (o instanceof Number && ((Number)o).intValue() < 0) {
            return previous;
         } else {
            String value;
            if (o.getClass().isArray()) {
               value = Arrays.toString(o);
            } else {
               value = String.valueOf(o);
            }

            String out = description + '=' + value;
            if (!previous.isEmpty()) {
               out = previous + ", " + out;
            }

            return out;
         }
      } else {
         return previous;
      }
   }

   public static String restrictContent(int errorContentLength, CharSequence content) {
      return ArgumentUtils.restrictContent(errorContentLength, content);
   }

   public static Object[] restrictContent(int errorContentLength, Object[] content) {
      return content != null && errorContentLength != 0 ? content : null;
   }

   public void setErrorContentLength(int errorContentLength) {
      this.errorContentLength = errorContentLength;
      Throwable cause = this.getCause();
      if (cause != null && cause instanceof AbstractException) {
         AbstractException e = (AbstractException)cause;
         if (e.errorContentLength != errorContentLength) {
            e.setErrorContentLength(errorContentLength);
         }
      }

   }

   protected String restrictContent(CharSequence content) {
      return restrictContent(this.errorContentLength, content);
   }

   protected String restrictContent(Object content) {
      return ArgumentUtils.restrictContent(this.errorContentLength, content);
   }

   protected Object[] restrictContent(Object[] content) {
      return restrictContent(this.errorContentLength, content);
   }
}
