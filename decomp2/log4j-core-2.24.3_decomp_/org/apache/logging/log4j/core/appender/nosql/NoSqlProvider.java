package org.apache.logging.log4j.core.appender.nosql;

public interface NoSqlProvider {
   NoSqlConnection getConnection();

   String toString();
}
