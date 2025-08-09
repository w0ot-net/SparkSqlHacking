package org.apache.hadoop.hive.conf;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public interface Validator {
   String validate(String var1);

   String toDescription();

   public static class StringSet implements Validator {
      private final boolean caseSensitive;
      private final Set expected;

      public StringSet(String... values) {
         this(false, values);
      }

      public StringSet(boolean caseSensitive, String... values) {
         this.expected = new LinkedHashSet();
         this.caseSensitive = caseSensitive;

         for(String value : values) {
            this.expected.add(caseSensitive ? value : value.toLowerCase());
         }

      }

      public Set getExpected() {
         return new HashSet(this.expected);
      }

      public String validate(String value) {
         return value != null && this.expected.contains(this.caseSensitive ? value : value.toLowerCase()) ? null : "Invalid value.. expects one of " + this.expected;
      }

      public String toDescription() {
         return "Expects one of " + this.expected;
      }
   }

   public static enum TYPE {
      INT {
         protected boolean inRange(String value, Object lower, Object upper) {
            int ivalue = Integer.parseInt(value);
            if (lower != null && ivalue < (Integer)lower) {
               return false;
            } else {
               return upper == null || ivalue <= (Integer)upper;
            }
         }
      },
      LONG {
         protected boolean inRange(String value, Object lower, Object upper) {
            long lvalue = Long.parseLong(value);
            if (lower != null && lvalue < (Long)lower) {
               return false;
            } else {
               return upper == null || lvalue <= (Long)upper;
            }
         }
      },
      FLOAT {
         protected boolean inRange(String value, Object lower, Object upper) {
            float fvalue = Float.parseFloat(value);
            if (lower != null && fvalue < (Float)lower) {
               return false;
            } else {
               return upper == null || !(fvalue > (Float)upper);
            }
         }
      };

      private TYPE() {
      }

      public static TYPE valueOf(Object lower, Object upper) {
         if (!(lower instanceof Integer) && !(upper instanceof Integer)) {
            if (!(lower instanceof Long) && !(upper instanceof Long)) {
               if (!(lower instanceof Float) && !(upper instanceof Float)) {
                  throw new IllegalArgumentException("invalid range from " + lower + " to " + upper);
               } else {
                  return FLOAT;
               }
            } else {
               return LONG;
            }
         } else {
            return INT;
         }
      }

      protected abstract boolean inRange(String var1, Object var2, Object var3);
   }

   public static class RangeValidator implements Validator {
      private final TYPE type;
      private final Object lower;
      private final Object upper;

      public RangeValidator(Object lower, Object upper) {
         this.lower = lower;
         this.upper = upper;
         this.type = Validator.TYPE.valueOf(lower, upper);
      }

      public String validate(String value) {
         try {
            if (value == null) {
               return "Value cannot be null";
            } else {
               return !this.type.inRange(value.trim(), this.lower, this.upper) ? "Invalid value  " + value + ", which should be in between " + this.lower + " and " + this.upper : null;
            }
         } catch (Exception e) {
            return e.toString();
         }
      }

      public String toDescription() {
         if (this.lower == null && this.upper == null) {
            return null;
         } else if (this.lower != null && this.upper != null) {
            return "Expects value between " + this.lower + " and " + this.upper;
         } else {
            return this.lower != null ? "Expects value bigger than " + this.lower : "Expects value smaller than " + this.upper;
         }
      }
   }

   public static class PatternSet implements Validator {
      private final List expected = new ArrayList();

      public PatternSet(String... values) {
         for(String value : values) {
            this.expected.add(Pattern.compile(value));
         }

      }

      public String validate(String value) {
         if (value == null) {
            return "Invalid value.. expects one of patterns " + this.expected;
         } else {
            for(Pattern pattern : this.expected) {
               if (pattern.matcher(value).matches()) {
                  return null;
               }
            }

            return "Invalid value.. expects one of patterns " + this.expected;
         }
      }

      public String toDescription() {
         return "Expects one of the pattern in " + this.expected;
      }
   }

   public static class RatioValidator implements Validator {
      public String validate(String value) {
         try {
            float fvalue = Float.parseFloat(value);
            return !(fvalue < 0.0F) && !(fvalue > 1.0F) ? null : "Invalid ratio " + value + ", which should be in between 0 to 1";
         } catch (NumberFormatException e) {
            return e.toString();
         }
      }

      public String toDescription() {
         return "Expects value between 0.0f and 1.0f";
      }
   }

   public static class TimeValidator implements Validator {
      private final TimeUnit timeUnit;
      private final Long min;
      private final boolean minInclusive;
      private final Long max;
      private final boolean maxInclusive;

      public TimeValidator(TimeUnit timeUnit) {
         this(timeUnit, (Long)null, false, (Long)null, false);
      }

      public TimeValidator(TimeUnit timeUnit, Long min, boolean minInclusive, Long max, boolean maxInclusive) {
         this.timeUnit = timeUnit;
         this.min = min;
         this.minInclusive = minInclusive;
         this.max = max;
         this.maxInclusive = maxInclusive;
      }

      public TimeUnit getTimeUnit() {
         return this.timeUnit;
      }

      public String validate(String value) {
         try {
            long time = HiveConf.toTime(value, this.timeUnit, this.timeUnit);
            if (this.min != null) {
               if (this.minInclusive) {
                  if (time < this.min) {
                     return value + " is smaller than " + this.timeString(this.min);
                  }
               } else if (time <= this.min) {
                  return value + " is smaller than " + this.timeString(this.min);
               }
            }

            if (this.max != null) {
               if (this.maxInclusive) {
                  if (time > this.max) {
                     return value + " is bigger than " + this.timeString(this.max);
                  }
               } else if (time >= this.max) {
                  return value + " is bigger than " + this.timeString(this.max);
               }
            }

            return null;
         } catch (Exception e) {
            return e.toString();
         }
      }

      public String toDescription() {
         String description = "Expects a time value with unit (d/day, h/hour, m/min, s/sec, ms/msec, us/usec, ns/nsec), which is " + HiveConf.stringFor(this.timeUnit) + " if not specified";
         if (this.min != null && this.max != null) {
            description = description + ".\nThe time should be in between " + this.timeString(this.min) + (this.minInclusive ? " (inclusive)" : " (exclusive)") + " and " + this.timeString(this.max) + (this.maxInclusive ? " (inclusive)" : " (exclusive)");
         } else if (this.min != null) {
            description = description + ".\nThe time should be bigger than " + (this.minInclusive ? "or equal to " : "") + this.timeString(this.min);
         } else if (this.max != null) {
            description = description + ".\nThe time should be smaller than " + (this.maxInclusive ? "or equal to " : "") + this.timeString(this.max);
         }

         return description;
      }

      private String timeString(long time) {
         return time + " " + HiveConf.stringFor(this.timeUnit);
      }
   }

   public static class SizeValidator implements Validator {
      private final Long min;
      private final boolean minInclusive;
      private final Long max;
      private final boolean maxInclusive;

      public SizeValidator() {
         this((Long)null, false, (Long)null, false);
      }

      public SizeValidator(Long min, boolean minInclusive, Long max, boolean maxInclusive) {
         this.min = min;
         this.minInclusive = minInclusive;
         this.max = max;
         this.maxInclusive = maxInclusive;
      }

      public String validate(String value) {
         try {
            long size = HiveConf.toSizeBytes(value);
            if (this.min != null) {
               if (this.minInclusive) {
                  if (size < this.min) {
                     return value + " is smaller than " + this.sizeString(this.min);
                  }
               } else if (size <= this.min) {
                  return value + " is smaller than " + this.sizeString(this.min);
               }
            }

            if (this.max != null) {
               if (this.maxInclusive) {
                  if (size > this.max) {
                     return value + " is bigger than " + this.sizeString(this.max);
                  }
               } else if (size >= this.max) {
                  return value + " is bigger than " + this.sizeString(this.max);
               }
            }

            return null;
         } catch (Exception e) {
            return e.toString();
         }
      }

      public String toDescription() {
         String description = "Expects a byte size value with unit (blank for bytes, kb, mb, gb, tb, pb)";
         if (this.min != null && this.max != null) {
            description = description + ".\nThe size should be in between " + this.sizeString(this.min) + (this.minInclusive ? " (inclusive)" : " (exclusive)") + " and " + this.sizeString(this.max) + (this.maxInclusive ? " (inclusive)" : " (exclusive)");
         } else if (this.min != null) {
            description = description + ".\nThe time should be bigger than " + (this.minInclusive ? "or equal to " : "") + this.sizeString(this.min);
         } else if (this.max != null) {
            description = description + ".\nThe size should be smaller than " + (this.maxInclusive ? "or equal to " : "") + this.sizeString(this.max);
         }

         return description;
      }

      private String sizeString(long size) {
         String[] units = new String[]{" bytes", "Kb", "Mb", "Gb", "Tb"};
         long current = 1L;

         for(int i = 0; i < units.length && current > 0L; ++i) {
            long next = current << 10;
            if ((size & next - 1L) != 0L) {
               return size / current + units[i];
            }

            current = next;
         }

         return current > 0L ? size / current + "Pb" : size + units[0];
      }
   }

   public static class WritableDirectoryValidator implements Validator {
      public String validate(String value) {
         Path path = FileSystems.getDefault().getPath(value);
         if (path == null && value != null) {
            return String.format("Path '%s' provided could not be located.", value);
         } else {
            boolean isDir = Files.isDirectory(path, new LinkOption[0]);
            boolean isWritable = Files.isWritable(path);
            if (!isDir) {
               return String.format("Path '%s' provided is not a directory.", value);
            } else {
               return !isWritable ? String.format("Path '%s' provided is not writable.", value) : null;
            }
         }
      }

      public String toDescription() {
         return "Expects a writable directory on the local filesystem";
      }
   }
}
