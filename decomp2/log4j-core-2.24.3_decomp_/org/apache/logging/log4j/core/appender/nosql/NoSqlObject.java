package org.apache.logging.log4j.core.appender.nosql;

public interface NoSqlObject {
   void set(String field, Object value);

   void set(String field, NoSqlObject value);

   void set(String field, Object[] values);

   void set(String field, NoSqlObject[] values);

   Object unwrap();
}
