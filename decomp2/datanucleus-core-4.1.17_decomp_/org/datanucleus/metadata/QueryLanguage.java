package org.datanucleus.metadata;

public enum QueryLanguage {
   JDOQL,
   SQL,
   JPQL,
   STOREDPROC;

   public static QueryLanguage getQueryLanguage(String value) {
      if (value == null) {
         return JDOQL;
      } else if (JDOQL.toString().equalsIgnoreCase(value)) {
         return JDOQL;
      } else if (SQL.toString().equalsIgnoreCase(value)) {
         return SQL;
      } else if (JPQL.toString().equalsIgnoreCase(value)) {
         return JPQL;
      } else {
         return STOREDPROC.toString().equalsIgnoreCase(value) ? STOREDPROC : null;
      }
   }
}
