package org.apache.logging.log4j.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.EnglishEnums;

public interface Filter extends LifeCycle {
   Filter[] EMPTY_ARRAY = new Filter[0];
   String ELEMENT_TYPE = "filter";

   Result getOnMismatch();

   Result getOnMatch();

   Result filter(Logger logger, Level level, Marker marker, String msg, Object... params);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t);

   Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t);

   default Result filter(Logger logger, Level level, Marker marker, String msg) {
      return this.filter(logger, level, marker, msg, Constants.EMPTY_OBJECT_ARRAY);
   }

   Result filter(LogEvent event);

   public static enum Result {
      ACCEPT,
      NEUTRAL,
      DENY;

      public static Result toResult(final String name) {
         return toResult(name, (Result)null);
      }

      public static Result toResult(final String name, final Result defaultResult) {
         return (Result)EnglishEnums.valueOf(Result.class, name, defaultResult);
      }

      // $FF: synthetic method
      private static Result[] $values() {
         return new Result[]{ACCEPT, NEUTRAL, DENY};
      }
   }
}
