package org.joda.time;

import java.io.Serializable;
import org.joda.convert.FromString;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class Instant extends AbstractInstant implements ReadableInstant, Serializable {
   public static final Instant EPOCH = new Instant(0L);
   private static final long serialVersionUID = 3299096530934209741L;
   private final long iMillis;

   public static Instant now() {
      return new Instant();
   }

   public static Instant ofEpochMilli(long var0) {
      return new Instant(var0);
   }

   public static Instant ofEpochSecond(long var0) {
      return new Instant(FieldUtils.safeMultiply(var0, 1000));
   }

   @FromString
   public static Instant parse(String var0) {
      return parse(var0, ISODateTimeFormat.dateTimeParser());
   }

   public static Instant parse(String var0, DateTimeFormatter var1) {
      return var1.parseDateTime(var0).toInstant();
   }

   public Instant() {
      this.iMillis = DateTimeUtils.currentTimeMillis();
   }

   public Instant(long var1) {
      this.iMillis = var1;
   }

   public Instant(Object var1) {
      InstantConverter var2 = ConverterManager.getInstance().getInstantConverter(var1);
      this.iMillis = var2.getInstantMillis(var1, ISOChronology.getInstanceUTC());
   }

   public Instant toInstant() {
      return this;
   }

   public Instant withMillis(long var1) {
      return var1 == this.iMillis ? this : new Instant(var1);
   }

   public Instant withDurationAdded(long var1, int var3) {
      if (var1 != 0L && var3 != 0) {
         long var4 = this.getChronology().add(this.getMillis(), var1, var3);
         return this.withMillis(var4);
      } else {
         return this;
      }
   }

   public Instant withDurationAdded(ReadableDuration var1, int var2) {
      return var1 != null && var2 != 0 ? this.withDurationAdded(var1.getMillis(), var2) : this;
   }

   public Instant plus(long var1) {
      return this.withDurationAdded(var1, 1);
   }

   public Instant plus(ReadableDuration var1) {
      return this.withDurationAdded(var1, 1);
   }

   public Instant minus(long var1) {
      return this.withDurationAdded(var1, -1);
   }

   public Instant minus(ReadableDuration var1) {
      return this.withDurationAdded(var1, -1);
   }

   public long getMillis() {
      return this.iMillis;
   }

   public Chronology getChronology() {
      return ISOChronology.getInstanceUTC();
   }

   public DateTime toDateTime() {
      return new DateTime(this.getMillis(), ISOChronology.getInstance());
   }

   /** @deprecated */
   @Deprecated
   public DateTime toDateTimeISO() {
      return this.toDateTime();
   }

   public MutableDateTime toMutableDateTime() {
      return new MutableDateTime(this.getMillis(), ISOChronology.getInstance());
   }

   /** @deprecated */
   @Deprecated
   public MutableDateTime toMutableDateTimeISO() {
      return this.toMutableDateTime();
   }
}
