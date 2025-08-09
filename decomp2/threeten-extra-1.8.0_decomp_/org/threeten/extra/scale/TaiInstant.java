package org.threeten.extra.scale;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class TaiInstant implements Comparable, Serializable {
   private static final int NANOS_PER_SECOND = 1000000000;
   private static final Pattern PARSER = Pattern.compile("([-]?[0-9]+)\\.([0-9]{9})s[(]TAI[)]");
   private static final long serialVersionUID = 2133469726395847026L;
   private final long seconds;
   private final int nanos;

   public static TaiInstant ofTaiSeconds(long taiSeconds, long nanoAdjustment) {
      long secs = Math.addExact(taiSeconds, Math.floorDiv(nanoAdjustment, 1000000000L));
      int nos = (int)Math.floorMod(nanoAdjustment, 1000000000L);
      return new TaiInstant(secs, nos);
   }

   public static TaiInstant of(Instant instant) {
      return UtcRules.system().convertToTai(instant);
   }

   public static TaiInstant of(UtcInstant instant) {
      return UtcRules.system().convertToTai(instant);
   }

   @FromString
   public static TaiInstant parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PARSER.matcher(text);
      if (matcher.matches()) {
         try {
            long seconds = Long.parseLong(matcher.group(1));
            long nanos = Long.parseLong(matcher.group(2));
            return ofTaiSeconds(seconds, nanos);
         } catch (NumberFormatException ex) {
            throw new DateTimeParseException("The text could not be parsed", text, 0, ex);
         }
      } else {
         throw new DateTimeParseException("The text could not be parsed", text, 0);
      }
   }

   private TaiInstant(long taiSeconds, int nanoOfSecond) {
      this.seconds = taiSeconds;
      this.nanos = nanoOfSecond;
   }

   public long getTaiSeconds() {
      return this.seconds;
   }

   public TaiInstant withTaiSeconds(long taiSeconds) {
      return ofTaiSeconds(taiSeconds, (long)this.nanos);
   }

   public int getNano() {
      return this.nanos;
   }

   public TaiInstant withNano(int nanoOfSecond) {
      if (nanoOfSecond >= 0 && nanoOfSecond < 1000000000) {
         return ofTaiSeconds(this.seconds, (long)nanoOfSecond);
      } else {
         throw new IllegalArgumentException("NanoOfSecond must be from 0 to 999,999,999");
      }
   }

   public TaiInstant plus(Duration duration) {
      long secsToAdd = duration.getSeconds();
      int nanosToAdd = duration.getNano();
      if ((secsToAdd | (long)nanosToAdd) == 0L) {
         return this;
      } else {
         long secs = Math.addExact(this.seconds, secsToAdd);
         long nanoAdjustment = (long)this.nanos + (long)nanosToAdd;
         return ofTaiSeconds(secs, nanoAdjustment);
      }
   }

   public TaiInstant minus(Duration duration) {
      long secsToSubtract = duration.getSeconds();
      int nanosToSubtract = duration.getNano();
      if ((secsToSubtract | (long)nanosToSubtract) == 0L) {
         return this;
      } else {
         long secs = Math.subtractExact(this.seconds, secsToSubtract);
         long nanoAdjustment = (long)this.nanos - (long)nanosToSubtract;
         return ofTaiSeconds(secs, nanoAdjustment);
      }
   }

   public Duration durationUntil(TaiInstant otherInstant) {
      long durSecs = Math.subtractExact(otherInstant.seconds, this.seconds);
      long durNanos = (long)(otherInstant.nanos - this.nanos);
      return Duration.ofSeconds(durSecs, durNanos);
   }

   public Instant toInstant() {
      return UtcRules.system().convertToInstant(this);
   }

   public UtcInstant toUtcInstant() {
      return UtcRules.system().convertToUtc(this);
   }

   public int compareTo(TaiInstant otherInstant) {
      int cmp = Long.compare(this.seconds, otherInstant.seconds);
      return cmp != 0 ? cmp : this.nanos - otherInstant.nanos;
   }

   public boolean isAfter(TaiInstant otherInstant) {
      return this.compareTo(otherInstant) > 0;
   }

   public boolean isBefore(TaiInstant otherInstant) {
      return this.compareTo(otherInstant) < 0;
   }

   public boolean equals(Object otherInstant) {
      if (this == otherInstant) {
         return true;
      } else if (!(otherInstant instanceof TaiInstant)) {
         return false;
      } else {
         TaiInstant other = (TaiInstant)otherInstant;
         return this.seconds == other.seconds && this.nanos == other.nanos;
      }
   }

   public int hashCode() {
      return (int)(this.seconds ^ this.seconds >>> 32) + 51 * this.nanos;
   }

   @ToString
   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append(this.seconds);
      int pos = buf.length();
      buf.append(this.nanos + 1000000000);
      buf.setCharAt(pos, '.');
      buf.append("s(TAI)");
      return buf.toString();
   }
}
