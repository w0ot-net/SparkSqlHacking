package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
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

public final class DateFormatter$ implements Serializable {
   public static final DateFormatter$ MODULE$ = new DateFormatter$();
   private static final Locale defaultLocale;
   private static final String defaultPattern;

   static {
      defaultLocale = Locale.US;
      defaultPattern = "yyyy-MM-dd";
   }

   public Locale defaultLocale() {
      return defaultLocale;
   }

   public String defaultPattern() {
      return defaultPattern;
   }

   private DateFormatter getFormatter(final Option format, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      Enumeration.Value var10000 = SqlApiConf$.MODULE$.get().legacyTimeParserPolicy();
      Enumeration.Value var5 = LegacyBehaviorPolicy$.MODULE$.LEGACY();
      if (var10000 == null) {
         if (var5 == null) {
            return this.getLegacyFormatter((String)format.getOrElse(() -> MODULE$.defaultPattern()), locale, legacyFormat);
         }
      } else if (var10000.equals(var5)) {
         return this.getLegacyFormatter((String)format.getOrElse(() -> MODULE$.defaultPattern()), locale, legacyFormat);
      }

      Iso8601DateFormatter df = (Iso8601DateFormatter)format.map((x$1) -> new Iso8601DateFormatter(x$1, locale, legacyFormat, isParsing)).getOrElse(() -> new DefaultDateFormatter(locale, legacyFormat, isParsing));
      df.validatePatternString();
      return df;
   }

   private Locale getFormatter$default$2() {
      return this.defaultLocale();
   }

   private Enumeration.Value getFormatter$default$3() {
      return LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
   }

   public DateFormatter getLegacyFormatter(final String pattern, final Locale locale, final Enumeration.Value legacyFormat) {
      Enumeration.Value var10000 = LegacyDateFormats$.MODULE$.FAST_DATE_FORMAT();
      if (var10000 == null) {
         if (legacyFormat == null) {
            return new LegacyFastDateFormatter(pattern, locale);
         }
      } else if (var10000.equals(legacyFormat)) {
         return new LegacyFastDateFormatter(pattern, locale);
      }

      label42: {
         label51: {
            var10000 = LegacyDateFormats$.MODULE$.SIMPLE_DATE_FORMAT();
            if (var10000 == null) {
               if (legacyFormat == null) {
                  break label51;
               }
            } else if (var10000.equals(legacyFormat)) {
               break label51;
            }

            label34: {
               var10000 = LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
               if (var10000 == null) {
                  if (legacyFormat == null) {
                     break label34;
                  }
               } else if (var10000.equals(legacyFormat)) {
                  break label34;
               }

               var12 = false;
               break label42;
            }

            var12 = true;
            break label42;
         }

         var12 = true;
      }

      if (var12) {
         return new LegacySimpleDateFormatter(pattern, locale);
      } else {
         throw new MatchError(legacyFormat);
      }
   }

   public DateFormatter apply(final Option format, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return this.getFormatter(format, locale, legacyFormat, isParsing);
   }

   public DateFormatter apply(final String format, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      return this.getFormatter(new Some(format), locale, legacyFormat, isParsing);
   }

   public DateFormatter apply(final String format, final boolean isParsing) {
      Some x$1 = new Some(format);
      Locale x$3 = this.getFormatter$default$2();
      Enumeration.Value x$4 = this.getFormatter$default$3();
      return this.getFormatter(x$1, x$3, x$4, isParsing);
   }

   public DateFormatter apply() {
      None x$1 = .MODULE$;
      boolean x$2 = false;
      Locale x$3 = this.getFormatter$default$2();
      Enumeration.Value x$4 = this.getFormatter$default$3();
      return this.getFormatter(x$1, x$3, x$4, false);
   }

   public boolean apply$default$2() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DateFormatter$.class);
   }

   private DateFormatter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
