package org.apache.spark.sql.internal;

import java.util.TimeZone;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.TimestampType$;
import scala.Enumeration;

public final class DefaultSqlApiConf$ implements SqlApiConf {
   public static final DefaultSqlApiConf$ MODULE$ = new DefaultSqlApiConf$();

   public boolean ansiEnabled() {
      return false;
   }

   public boolean caseSensitiveAnalysis() {
      return false;
   }

   public int maxToStringFields() {
      return 50;
   }

   public boolean setOpsPrecedenceEnforced() {
      return false;
   }

   public boolean exponentLiteralAsDecimalEnabled() {
      return false;
   }

   public boolean enforceReservedKeywords() {
      return false;
   }

   public boolean doubleQuotedIdentifiers() {
      return false;
   }

   public AtomicType timestampType() {
      return TimestampType$.MODULE$;
   }

   public boolean allowNegativeScaleOfDecimalEnabled() {
      return false;
   }

   public boolean charVarcharAsString() {
      return false;
   }

   public boolean preserveCharVarcharTypeInfo() {
      return false;
   }

   public boolean datetimeJava8ApiEnabled() {
      return false;
   }

   public String sessionLocalTimeZone() {
      return TimeZone.getDefault().getID();
   }

   public Enumeration.Value legacyTimeParserPolicy() {
      return LegacyBehaviorPolicy$.MODULE$.CORRECTED();
   }

   public int stackTracesInDataFrameContext() {
      return 1;
   }

   public boolean dataFrameQueryContextEnabled() {
      return true;
   }

   public boolean legacyAllowUntypedScalaUDFs() {
      return false;
   }

   private DefaultSqlApiConf$() {
   }
}
