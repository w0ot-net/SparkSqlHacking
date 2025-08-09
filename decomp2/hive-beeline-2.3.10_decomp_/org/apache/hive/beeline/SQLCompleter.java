package org.apache.hive.beeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import jline.console.completer.StringsCompleter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SQLCompleter extends StringsCompleter {
   private static final Logger LOG = LoggerFactory.getLogger(SQLCompleter.class.getName());

   public SQLCompleter(Set completions) {
      super(completions);
   }

   public static Set getSQLCompleters(BeeLine beeLine, boolean skipmeta) throws IOException, SQLException {
      Set<String> completions = new TreeSet();
      String keywords = (new BufferedReader(new InputStreamReader(SQLCompleter.class.getResourceAsStream("/sql-keywords.properties")))).readLine();

      try {
         keywords = keywords + "," + beeLine.getDatabaseConnection().getDatabaseMetaData().getSQLKeywords();
      } catch (Exception e) {
         LOG.debug("fail to get SQL key words from database metadata due to the exception: " + e, e);
      }

      try {
         keywords = keywords + "," + beeLine.getDatabaseConnection().getDatabaseMetaData().getStringFunctions();
      } catch (Exception e) {
         LOG.debug("fail to get string function names from database metadata due to the exception: " + e, e);
      }

      try {
         keywords = keywords + "," + beeLine.getDatabaseConnection().getDatabaseMetaData().getNumericFunctions();
      } catch (Exception e) {
         LOG.debug("fail to get numeric function names from database metadata due to the exception: " + e, e);
      }

      try {
         keywords = keywords + "," + beeLine.getDatabaseConnection().getDatabaseMetaData().getSystemFunctions();
      } catch (Exception e) {
         LOG.debug("fail to get system function names from database metadata due to the exception: " + e, e);
      }

      try {
         keywords = keywords + "," + beeLine.getDatabaseConnection().getDatabaseMetaData().getTimeDateFunctions();
      } catch (Exception e) {
         LOG.debug("fail to get time date function names from database metadata due to the exception: " + e, e);
      }

      keywords = keywords + "," + keywords.toLowerCase();
      StringTokenizer tok = new StringTokenizer(keywords, ", ");

      while(tok.hasMoreTokens()) {
         completions.add(tok.nextToken());
      }

      if (!skipmeta) {
         String[] columns = beeLine.getColumnNames(beeLine.getDatabaseConnection().getDatabaseMetaData());

         for(int i = 0; columns != null && i < columns.length; ++i) {
            completions.add(columns[i++]);
         }
      }

      return completions;
   }
}
