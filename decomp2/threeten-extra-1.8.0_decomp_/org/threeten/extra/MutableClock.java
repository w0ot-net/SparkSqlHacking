package org.threeten.extra;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

public final class MutableClock extends Clock implements Serializable {
   private static final long serialVersionUID = -6152029959790119695L;
   private final transient InstantHolder instantHolder;
   private final transient ZoneId zone;

   public static MutableClock epochUTC() {
      return of(Instant.EPOCH, ZoneOffset.UTC);
   }

   public static MutableClock of(Instant instant, ZoneId zone) {
      Objects.requireNonNull(instant, "instant");
      Objects.requireNonNull(zone, "zone");
      return new MutableClock(new InstantHolder(instant), zone);
   }

   private MutableClock(InstantHolder instantHolder, ZoneId zone) {
      this.instantHolder = instantHolder;
      this.zone = zone;
   }

   public void setInstant(Instant instant) {
      Objects.requireNonNull(instant, "instant");
      this.instantHolder.set(instant);
   }

   public void add(TemporalAmount amountToAdd) {
      Objects.requireNonNull(amountToAdd, "amountToAdd");
      synchronized(this.instantHolder) {
         ZonedDateTime current = ZonedDateTime.ofInstant(this.instantHolder.get(), this.zone);
         ZonedDateTime result = current.plus(amountToAdd);
         this.instantHolder.set(result.toInstant());
      }
   }

   public void add(long amountToAdd, TemporalUnit unit) {
      Objects.requireNonNull(unit, "unit");
      synchronized(this.instantHolder) {
         ZonedDateTime current = ZonedDateTime.ofInstant(this.instantHolder.get(), this.zone);
         ZonedDateTime result = current.plus(amountToAdd, unit);
         this.instantHolder.set(result.toInstant());
      }
   }

   public void set(TemporalAdjuster adjuster) {
      Objects.requireNonNull(adjuster, "adjuster");
      synchronized(this.instantHolder) {
         ZonedDateTime current = ZonedDateTime.ofInstant(this.instantHolder.get(), this.zone);
         ZonedDateTime result = current.with(adjuster);
         this.instantHolder.set(result.toInstant());
      }
   }

   public void set(TemporalField field, long newValue) {
      Objects.requireNonNull(field, "field");
      synchronized(this.instantHolder) {
         ZonedDateTime current = ZonedDateTime.ofInstant(this.instantHolder.get(), this.zone);
         ZonedDateTime result = current.with(field, newValue);
         this.instantHolder.set(result.toInstant());
      }
   }

   public ZoneId getZone() {
      return this.zone;
   }

   public MutableClock withZone(ZoneId zone) {
      Objects.requireNonNull(zone, "zone");
      return zone.equals(this.zone) ? this : new MutableClock(this.instantHolder, zone);
   }

   public Instant instant() {
      return this.instantHolder.get();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof MutableClock)) {
         return false;
      } else {
         MutableClock other = (MutableClock)obj;
         return this.instantHolder == other.instantHolder && this.zone.equals(other.zone);
      }
   }

   public int hashCode() {
      return System.identityHashCode(this.instantHolder) ^ this.zone.hashCode();
   }

   public String toString() {
      return "MutableClock[" + this.instant() + "," + this.getZone() + "]";
   }

   private Object writeReplace() {
      return new SerializationProxy(this);
   }

   private void readObject(ObjectInputStream s) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
   }

   private static final class SerializationProxy implements Serializable {
      private static final long serialVersionUID = 8602110640241828260L;
      private final Instant instant;
      private final ZoneId zone;

      SerializationProxy(MutableClock clock) {
         this.instant = clock.instant();
         this.zone = clock.getZone();
      }

      private Object readResolve() throws InvalidObjectException {
         if (this.instant == null) {
            throw new InvalidObjectException("null instant");
         } else if (this.zone == null) {
            throw new InvalidObjectException("null zone");
         } else {
            return MutableClock.of(this.instant, this.zone);
         }
      }
   }

   private static final class InstantHolder {
      private volatile Instant value;

      InstantHolder(Instant value) {
         this.value = value;
      }

      Instant get() {
         return this.value;
      }

      void set(Instant value) {
         this.value = value;
      }
   }
}
