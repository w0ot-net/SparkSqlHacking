package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.ZoneId;
import java.util.Locale;
import org.apache.spark.sql.internal.LegacyBehaviorPolicy$;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.Enumeration;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class TimestampFormatter$ implements Serializable {
   public static final TimestampFormatter$ MODULE$ = new TimestampFormatter$();
   private static final Locale defaultLocale;

   static {
      defaultLocale = Locale.US;
   }

   public Locale defaultLocale() {
      return defaultLocale;
   }

   public String defaultPattern() {
      return DateFormatter$.MODULE$.defaultPattern() + " HH:mm:ss";
   }

   private TimestampFormatter getFormatter(final Option format, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing, final boolean forTimestampNTZ) {
      TimestampFormatter var9;
      label24: {
         label23: {
            Enumeration.Value var10000 = SqlApiConf$.MODULE$.get().legacyTimeParserPolicy();
            Enumeration.Value var8 = LegacyBehaviorPolicy$.MODULE$.LEGACY();
            if (var10000 == null) {
               if (var8 != null) {
                  break label23;
               }
            } else if (!var10000.equals(var8)) {
               break label23;
            }

            if (!forTimestampNTZ) {
               var9 = this.getLegacyFormatter((String)format.getOrElse(() -> MODULE$.defaultPattern()), zoneId, locale, legacyFormat);
               break label24;
            }
         }

         var9 = (TimestampFormatter)format.map((x$2) -> new Iso8601TimestampFormatter(x$2, zoneId, locale, legacyFormat, isParsing)).getOrElse(() -> new DefaultTimestampFormatter(zoneId, locale, legacyFormat, isParsing));
      }

      TimestampFormatter formatter = var9;
      formatter.validatePatternString(!forTimestampNTZ);
      return formatter;
   }

   private Locale getFormatter$default$3() {
      return this.defaultLocale();
   }

   private Enumeration.Value getFormatter$default$4() {
      return LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
   }

   private boolean getFormatter$default$6() {
      return false;
   }

   public TimestampFormatter getLegacyFormatter(final String pattern, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat) {
      Enumeration.Value var10000 = LegacyDateFormats$.MODULE$.FAST_DATE_FORMAT();
      if (var10000 == null) {
         if (legacyFormat == null) {
            return new LegacyFastTimestampFormatter(pattern, zoneId, locale);
         }
      } else if (var10000.equals(legacyFormat)) {
         return new LegacyFastTimestampFormatter(pattern, zoneId, locale);
      }

      var10000 = LegacyDateFormats$.MODULE$.SIMPLE_DATE_FORMAT();
      if (var10000 == null) {
         if (legacyFormat == null) {
            return new LegacySimpleTimestampFormatter(pattern, zoneId, locale, false);
         }
      } else if (var10000.equals(legacyFormat)) {
         return new LegacySimpleTimestampFormatter(pattern, zoneId, locale, false);
      }

      var10000 = LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
      if (var10000 == null) {
         if (legacyFormat == null) {
            return new LegacySimpleTimestampFormatter(pattern, zoneId, locale, true);
         }
      } else if (var10000.equals(legacyFormat)) {
         return new LegacySimpleTimestampFormatter(pattern, zoneId, locale, true);
      }

      throw new MatchError(legacyFormat);
   }

   public TimestampFormatter apply(final Option format, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return this.getFormatter(format, zoneId, locale, legacyFormat, isParsing, this.getFormatter$default$6());
   }

   public TimestampFormatter apply(final String format, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return this.getFormatter(new Some(format), zoneId, locale, legacyFormat, isParsing, this.getFormatter$default$6());
   }

   public TimestampFormatter apply(final String format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return this.getFormatter(new Some(format), zoneId, this.defaultLocale(), legacyFormat, isParsing, this.getFormatter$default$6());
   }

   public TimestampFormatter apply(final Option format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing, final boolean forTimestampNTZ) {
      return this.getFormatter(format, zoneId, this.defaultLocale(), legacyFormat, isParsing, forTimestampNTZ);
   }

   public TimestampFormatter apply(final String format, final ZoneId zoneId, final Enumeration.Value legacyFormat, final boolean isParsing, final boolean forTimestampNTZ) {
      return this.getFormatter(new Some(format), zoneId, this.defaultLocale(), legacyFormat, isParsing, forTimestampNTZ);
   }

   public TimestampFormatter apply(final String format, final ZoneId zoneId, final boolean isParsing) {
      Some x$1 = new Some(format);
      Locale x$4 = this.getFormatter$default$3();
      Enumeration.Value x$5 = this.getFormatter$default$4();
      boolean x$6 = this.getFormatter$default$6();
      return this.getFormatter(x$1, zoneId, x$4, x$5, isParsing, x$6);
   }

   public TimestampFormatter apply(final String format, final ZoneId zoneId, final boolean isParsing, final boolean forTimestampNTZ) {
      Some x$1 = new Some(format);
      Locale x$5 = this.getFormatter$default$3();
      Enumeration.Value x$6 = this.getFormatter$default$4();
      return this.getFormatter(x$1, zoneId, x$5, x$6, isParsing, forTimestampNTZ);
   }

   public TimestampFormatter apply(final ZoneId zoneId) {
      None x$1 = .MODULE$;
      boolean x$3 = false;
      Locale x$4 = this.getFormatter$default$3();
      Enumeration.Value x$5 = this.getFormatter$default$4();
      boolean x$6 = this.getFormatter$default$6();
      return this.getFormatter(x$1, zoneId, x$4, x$5, false, x$6);
   }

   public TimestampFormatter getFractionFormatter(final ZoneId zoneId) {
      return new FractionTimestampFormatter(zoneId);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TimestampFormatter$.class);
   }

   private TimestampFormatter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
