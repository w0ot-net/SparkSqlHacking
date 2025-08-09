package org.apache.derby.impl.sql.execute.xplain;

import java.util.Properties;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINSortPropsDescriptor;
import org.apache.derby.shared.common.i18n.MessageService;

public class XPLAINUtil {
   public static final String ISOLATION_READ_UNCOMMITED = "RU";
   public static final String ISOLATION_READ_COMMIT = "RC";
   public static final String ISOLATION_REPEAT_READ = "RR";
   public static final String ISOLATION_SERIALIZABLE = "SE";
   public static final String LOCK_MODE_EXCLUSIVE = "EX";
   public static final String LOCK_MODE_INSTANTENOUS_EXCLUSIVE = "IX";
   public static final String LOCK_MODE_SHARE = "SH";
   public static final String LOCK_MODE_INSTANTENOUS_SHARE = "IS";
   public static final String LOCK_GRANULARITY_TABLE = "T";
   public static final String LOCK_GRANULARITY_ROW = "R";
   public static final String OP_TABLESCAN = "TABLESCAN";
   public static final String OP_INDEXSCAN = "INDEXSCAN";
   public static final String OP_HASHSCAN = "HASHSCAN";
   public static final String OP_DISTINCTSCAN = "DISTINCTSCAN";
   public static final String OP_LASTINDEXKEYSCAN = "LASTINDEXKEYSCAN";
   public static final String OP_HASHTABLE = "HASHTABLE";
   public static final String OP_ROWIDSCAN = "ROWIDSCAN";
   public static final String OP_CONSTRAINTSCAN = "CONSTRAINTSCAN";
   public static final String OP_JOIN_NL = "NLJOIN";
   public static final String OP_JOIN_HASH = "HASHJOIN";
   public static final String OP_JOIN_NL_LO = "LONLJOIN";
   public static final String OP_JOIN_HASH_LO = "LOHASHJOIN";
   public static final String OP_UNION = "UNION";
   public static final String OP_SET = "SET";
   public static final String OP_SET_INTERSECT = "EXCEPT";
   public static final String OP_SET_EXCEPT = "INTERSECT";
   public static final String OP_INSERT = "INSERT";
   public static final String OP_UPDATE = "UPDATE";
   public static final String OP_DELETE = "DELETE";
   public static final String OP_CASCADE = "CASCADE";
   public static final String OP_VTI = "VTI";
   public static final String OP_BULK = "BULK";
   public static final String OP_DISTINCT = "DISTINCT";
   public static final String OP_NORMALIZE = "NORMALIZE";
   public static final String OP_ANY = "ANY";
   public static final String OP_SCROLL = "SCROLL";
   public static final String OP_MATERIALIZE = "MATERIALIZE";
   public static final String OP_ONCE = "ONCE";
   public static final String OP_VTI_RS = "VTI";
   public static final String OP_ROW = "ROW";
   public static final String OP_PROJECT = "PROJECTION";
   public static final String OP_FILTER = "FILTER";
   public static final String OP_AGGREGATE = "AGGREGATION";
   public static final String OP_PROJ_RESTRICT = "PROJECT-FILTER";
   public static final String OP_SORT = "SORT";
   public static final String OP_GROUP = "GROUPBY";
   public static final String OP_CURRENT_OF = "CURRENT-OF";
   public static final String OP_ROW_COUNT = "ROW-COUNT";
   public static final String OP_WINDOW = "WINDOW";
   public static final String SCAN_HEAP = "HEAP";
   public static final String SCAN_BTREE = "BTREE";
   public static final String SCAN_SORT = "SORT";
   public static final String SCAN_BITSET_ALL = "ALL";
   public static final String SELECT_STMT_TYPE = "S";
   public static final String SELECT_APPROXIMATE_STMT_TYPE = "SA";
   public static final String INSERT_STMT_TYPE = "I";
   public static final String UPDATE_STMT_TYPE = "U";
   public static final String DELETE_STMT_TYPE = "D";
   public static final String CALL_STMT_TYPE = "C";
   public static final String DDL_STMT_TYPE = "DDL";
   public static final String XPLAIN_ONLY = "O";
   public static final String XPLAIN_FULL = "F";
   public static final String SORT_EXTERNAL = "EX";
   public static final String SORT_INTERNAL = "IN";
   public static final String YES_CODE = "Y";
   public static final String NO_CODE = "N";

   public static String getYesNoCharFromBoolean(boolean var0) {
      return var0 ? "Y" : "N";
   }

   public static String getHashKeyColumnNumberString(int[] var0) {
      String var1;
      if (var0.length == 1) {
         String var10000 = MessageService.getTextMessage("43X53.U", new Object[0]);
         var1 = var10000 + " " + var0[0];
      } else {
         String var4 = MessageService.getTextMessage("43X54.U", new Object[0]);
         var1 = var4 + " (" + var0[0];

         for(int var2 = 1; var2 < var0.length; ++var2) {
            var1 = var1 + "," + var0[var2];
         }

         var1 = var1 + ")";
      }

      return var1;
   }

   public static String getLockModeCode(String var0) {
      var0 = var0.toUpperCase();
      if (var0.startsWith("EXCLUSIVE")) {
         return "EX";
      } else if (var0.startsWith("SHARE")) {
         return "SH";
      } else if (var0.startsWith("INSTANTANEOUS")) {
         int var1 = "INSTANTANEOUS".length();
         int var2 = var0.length();
         String var3 = var0.substring(var1 + 1, var2);
         if (var3.startsWith("EXCLUSIVE")) {
            return "IX";
         } else {
            return var3.startsWith("SHARE") ? "IS" : null;
         }
      } else {
         return null;
      }
   }

   public static String getIsolationLevelCode(String var0) {
      if (var0 == null) {
         return null;
      } else if (var0.equals(MessageService.getTextMessage("42Z80.U", new Object[0]))) {
         return "SE";
      } else if (var0.equals(MessageService.getTextMessage("42Z92", new Object[0]))) {
         return "RR";
      } else if (var0.equals(MessageService.getTextMessage("42Z81.U", new Object[0]))) {
         return "RC";
      } else {
         return var0.equals(MessageService.getTextMessage("42Z9A", new Object[0])) ? "RU" : null;
      }
   }

   public static String getLockGranularityCode(String var0) {
      var0 = var0.toUpperCase();
      return var0.endsWith("TABLE") ? "T" : "R";
   }

   public static String getStatementType(String var0) {
      String var1 = "";
      String var2 = var0.toUpperCase().trim();
      if (var2.startsWith("CALL")) {
         var1 = "C";
      } else if (var2.startsWith("SELECT")) {
         if (var2.indexOf("~") > -1) {
            var1 = "SA";
         } else {
            var1 = "S";
         }
      } else if (var2.startsWith("DELETE")) {
         var1 = "D";
      } else if (var2.startsWith("INSERT")) {
         var1 = "I";
      } else if (var2.startsWith("UPDATE")) {
         var1 = "U";
      } else if (var2.startsWith("CREATE") || var2.startsWith("ALTER") || var2.startsWith("DROP")) {
         var1 = "DDL";
      }

      return var1;
   }

   public static XPLAINScanPropsDescriptor extractScanProps(XPLAINScanPropsDescriptor var0, Properties var1) {
      String var2 = "";
      String var3 = var1.getProperty(MessageService.getTextMessage("XSAJ0.U", new Object[0]));
      if (var3 != null) {
         if (var3.equalsIgnoreCase(MessageService.getTextMessage("XSAJG.U", new Object[0]))) {
            var2 = "HEAP";
         } else if (var3.equalsIgnoreCase(MessageService.getTextMessage("XSAJH.U", new Object[0]))) {
            var2 = "SORT";
         } else if (var3.equalsIgnoreCase(MessageService.getTextMessage("XSAJF.U", new Object[0]))) {
            var2 = "BTREE";
         }
      } else {
         var2 = null;
      }

      var0.setScan_type(var2);
      String var4 = var1.getProperty(MessageService.getTextMessage("XSAJ1.U", new Object[0]));
      if (var4 != null) {
         var0.setNo_visited_pages(Integer.parseInt(var4));
      }

      String var5 = var1.getProperty(MessageService.getTextMessage("XSAJ2.U", new Object[0]));
      if (var5 != null) {
         var0.setNo_visited_rows(Integer.parseInt(var5));
      }

      String var6 = var1.getProperty(MessageService.getTextMessage("XSAJ4.U", new Object[0]));
      if (var6 != null) {
         var0.setNo_qualified_rows(Integer.parseInt(var6));
      }

      String var7 = var1.getProperty(MessageService.getTextMessage("XSAJ5.U", new Object[0]));
      if (var7 != null) {
         var0.setNo_fetched_columns(Integer.parseInt(var7));
      }

      String var8 = var1.getProperty(MessageService.getTextMessage("XSAJ3.U", new Object[0]));
      if (var8 != null) {
         var0.setNo_visited_deleted_rows(Integer.parseInt(var8));
      }

      String var9 = var1.getProperty(MessageService.getTextMessage("XSAJ7.U", new Object[0]));
      if (var9 != null) {
         var0.setBtree_height(Integer.parseInt(var9));
      }

      String var10 = var1.getProperty(MessageService.getTextMessage("XSAJ6.U", new Object[0]));
      if (var10 != null) {
         if (var10.equalsIgnoreCase(MessageService.getTextMessage("XSAJE.U", new Object[0]))) {
            var0.setBitset_of_fetched_columns("ALL");
         } else {
            var0.setBitset_of_fetched_columns(var10);
         }
      }

      return var0;
   }

   public static XPLAINSortPropsDescriptor extractSortProps(XPLAINSortPropsDescriptor var0, Properties var1) {
      String var2 = null;
      String var3 = var1.getProperty(MessageService.getTextMessage("XSAJ8.U", new Object[0]));
      if (var3 != null) {
         if (var3.equalsIgnoreCase(MessageService.getTextMessage("XSAJI.U", new Object[0]))) {
            var2 = "EX";
         } else {
            var2 = "IN";
         }
      }

      var0.setSort_type(var2);
      String var4 = var1.getProperty(MessageService.getTextMessage("XSAJA.U", new Object[0]));
      if (var4 != null) {
         var0.setNo_input_rows(Integer.parseInt(var4));
      }

      String var5 = var1.getProperty(MessageService.getTextMessage("XSAJB.U", new Object[0]));
      if (var5 != null) {
         var0.setNo_output_rows(Integer.parseInt(var5));
      }

      if (var2 == "EX") {
         String var6 = var1.getProperty(MessageService.getTextMessage("XSAJC.U", new Object[0]));
         if (var6 != null) {
            var0.setNo_merge_runs(Integer.parseInt(var6));
         }

         String var7 = var1.getProperty(MessageService.getTextMessage("XSAJD.U", new Object[0]));
         if (var7 != null) {
            var0.setMerge_run_details(var7);
         }
      }

      return var0;
   }

   public static Long getAVGNextTime(long var0, long var2) {
      if (var2 == 0L) {
         return null;
      } else {
         return var0 == 0L ? 0L : var0 / var2;
      }
   }
}
