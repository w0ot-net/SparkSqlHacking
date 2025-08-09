package org.datanucleus.store.rdbms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.StringTokenizer;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class JDBCUtils {
   public static String getSubprotocolForURL(String url) {
      StringTokenizer tokeniser = new StringTokenizer(url, ":");
      tokeniser.nextToken();
      return tokeniser.nextToken();
   }

   public static void logWarnings(SQLWarning warning) {
      while(warning != null) {
         NucleusLogger.DATASTORE.warn(Localiser.msg("052700", new Object[]{warning.getMessage()}));
         warning = warning.getNextWarning();
      }

   }

   public static void logWarnings(Connection conn) {
      try {
         logWarnings(conn.getWarnings());
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("052701", new Object[]{conn}), e);
      }
   }

   public static void logWarnings(Statement stmt) {
      try {
         logWarnings(stmt.getWarnings());
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("052702", new Object[]{stmt}), e);
      }
   }

   public static void logWarnings(ResultSet rs) {
      try {
         logWarnings(rs.getWarnings());
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("052703", new Object[]{rs}), e);
      }
   }
}
