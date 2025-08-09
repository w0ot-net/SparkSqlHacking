package org.datanucleus.metadata;

public enum JdbcType {
   BIGINT(-5),
   BINARY(-2),
   BIT(-7),
   BLOB(2004),
   BOOLEAN(16),
   CHAR(1),
   CLOB(2005),
   DATALINK(70),
   DATE(91),
   DECIMAL(3),
   DOUBLE(8),
   FLOAT(6),
   INTEGER(4),
   LONGNVARCHAR(-16),
   LONGVARBINARY(-4),
   LONGVARCHAR(-1),
   NCHAR(-15),
   NCLOB(2011),
   NUMERIC(2),
   NVARCHAR(-9),
   OTHER(1111),
   REAL(7),
   SMALLINT(5),
   SQLXML(2009),
   TIME(92),
   TIME_WITH_TIMEZONE(2013),
   TIMESTAMP(93),
   TIMESTAMP_WITH_TIMEZONE(2014),
   TINYINT(-6),
   VARBINARY(-3),
   VARCHAR(12),
   XMLTYPE(2007);

   private int value;

   private JdbcType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   public static JdbcType getEnumByValue(int value) {
      switch (value) {
         case -16:
            return LONGNVARCHAR;
         case -15:
            return NCHAR;
         case -9:
            return NVARCHAR;
         case -7:
            return BIT;
         case -6:
            return TINYINT;
         case -5:
            return BIGINT;
         case -4:
            return LONGVARBINARY;
         case -3:
            return VARBINARY;
         case -2:
            return BINARY;
         case -1:
            return LONGVARCHAR;
         case 1:
            return CHAR;
         case 2:
            return NUMERIC;
         case 3:
            return DECIMAL;
         case 4:
            return INTEGER;
         case 5:
            return SMALLINT;
         case 6:
            return FLOAT;
         case 7:
            return REAL;
         case 8:
            return DOUBLE;
         case 12:
            return VARCHAR;
         case 16:
            return BOOLEAN;
         case 70:
            return DATALINK;
         case 91:
            return DATE;
         case 92:
            return TIME;
         case 93:
            return TIMESTAMP;
         case 1111:
            return OTHER;
         case 2004:
            return BLOB;
         case 2005:
            return CLOB;
         case 2007:
            return XMLTYPE;
         case 2009:
            return SQLXML;
         case 2011:
            return NCLOB;
         case 2013:
            return TIME_WITH_TIMEZONE;
         case 2014:
            return TIMESTAMP_WITH_TIMEZONE;
         default:
            return null;
      }
   }
}
