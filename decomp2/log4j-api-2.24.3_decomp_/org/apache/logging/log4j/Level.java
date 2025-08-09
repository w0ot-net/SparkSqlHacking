package org.apache.logging.log4j;

import aQute.bnd.annotation.baseline.BaselineIgnore;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.spi.StandardLevel;
import org.apache.logging.log4j.util.Strings;

@BaselineIgnore("2.22.0")
public final class Level implements Comparable, Serializable {
   private static final Level[] EMPTY_ARRAY = new Level[0];
   private static final ConcurrentMap LEVELS = new ConcurrentHashMap();
   public static final Level OFF;
   public static final Level FATAL;
   public static final Level ERROR;
   public static final Level WARN;
   public static final Level INFO;
   public static final Level DEBUG;
   public static final Level TRACE;
   public static final Level ALL;
   public static final String CATEGORY = "Level";
   private static final long serialVersionUID = 1581082L;
   private final String name;
   private final int intLevel;
   private final StandardLevel standardLevel;

   private Level(final String name, final int intLevel) {
      if (Strings.isEmpty(name)) {
         throw new IllegalArgumentException("Illegal null or empty Level name.");
      } else if (intLevel < 0) {
         throw new IllegalArgumentException("Illegal Level int less than zero.");
      } else {
         this.name = name;
         this.intLevel = intLevel;
         this.standardLevel = StandardLevel.getStandardLevel(intLevel);
         if (LEVELS.putIfAbsent(Strings.toRootUpperCase(name.trim()), this) != null) {
            throw new IllegalStateException("Level " + name + " has already been defined.");
         }
      }
   }

   public int intLevel() {
      return this.intLevel;
   }

   public StandardLevel getStandardLevel() {
      return this.standardLevel;
   }

   public boolean isInRange(final Level minLevel, final Level maxLevel) {
      return this.intLevel >= minLevel.intLevel && this.intLevel <= maxLevel.intLevel;
   }

   public boolean isLessSpecificThan(final Level level) {
      return this.intLevel >= level.intLevel;
   }

   public boolean isMoreSpecificThan(final Level level) {
      return this.intLevel <= level.intLevel;
   }

   public Level clone() throws CloneNotSupportedException {
      throw new CloneNotSupportedException();
   }

   public int compareTo(final Level other) {
      return this.intLevel < other.intLevel ? -1 : (this.intLevel > other.intLevel ? 1 : 0);
   }

   public boolean equals(final Object other) {
      return other instanceof Level && other == this;
   }

   public Class getDeclaringClass() {
      return Level.class;
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public String name() {
      return this.name;
   }

   public String toString() {
      return this.name;
   }

   public static Level forName(final String name, final int intValue) {
      if (Strings.isEmpty(name)) {
         throw new IllegalArgumentException("Illegal null or empty Level name.");
      } else {
         String normalizedName = Strings.toRootUpperCase(name.trim());
         Level level = (Level)LEVELS.get(normalizedName);
         if (level != null) {
            return level;
         } else {
            try {
               return new Level(name, intValue);
            } catch (IllegalStateException var5) {
               return (Level)LEVELS.get(normalizedName);
            }
         }
      }
   }

   public static Level getLevel(final String name) {
      if (Strings.isEmpty(name)) {
         throw new IllegalArgumentException("Illegal null or empty Level name.");
      } else {
         return (Level)LEVELS.get(Strings.toRootUpperCase(name.trim()));
      }
   }

   public static Level toLevel(final String level) {
      return toLevel(level, DEBUG);
   }

   public static Level toLevel(final String name, final Level defaultLevel) {
      if (name == null) {
         return defaultLevel;
      } else {
         Level level = (Level)LEVELS.get(Strings.toRootUpperCase(name.trim()));
         return level == null ? defaultLevel : level;
      }
   }

   public static Level[] values() {
      return (Level[])LEVELS.values().toArray(EMPTY_ARRAY);
   }

   public static Level valueOf(final String name) {
      Objects.requireNonNull(name, "No level name given.");
      String levelName = Strings.toRootUpperCase(name.trim());
      Level level = (Level)LEVELS.get(levelName);
      if (level != null) {
         return level;
      } else {
         throw new IllegalArgumentException("Unknown level constant [" + levelName + "].");
      }
   }

   public static Enum valueOf(final Class enumType, final String name) {
      return Enum.valueOf(enumType, name);
   }

   private Object readResolve() {
      return valueOf(this.name);
   }

   static {
      OFF = new Level("OFF", StandardLevel.OFF.intLevel());
      FATAL = new Level("FATAL", StandardLevel.FATAL.intLevel());
      ERROR = new Level("ERROR", StandardLevel.ERROR.intLevel());
      WARN = new Level("WARN", StandardLevel.WARN.intLevel());
      INFO = new Level("INFO", StandardLevel.INFO.intLevel());
      DEBUG = new Level("DEBUG", StandardLevel.DEBUG.intLevel());
      TRACE = new Level("TRACE", StandardLevel.TRACE.intLevel());
      ALL = new Level("ALL", StandardLevel.ALL.intLevel());
   }
}
