package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.types.DataTypeUtilities;

public class XPLAINStatementDescriptor extends XPLAINTableDescriptor {
   private UUID stmt_id;
   private String stmt_name;
   private String stmt_type;
   private String stmt_text;
   private String jvm_id;
   private String os_id;
   private String xplain_mode;
   private Timestamp xplain_time;
   private String thread_id;
   private String xa_id;
   private String session_id;
   private String db_name;
   private String drda_id;
   private UUID timing_id;
   static final String TABLENAME_STRING = "SYSXPLAIN_STATEMENTS";
   private static final String[][] indexColumnNames = new String[][]{{"STMT_ID"}};

   public XPLAINStatementDescriptor() {
   }

   public XPLAINStatementDescriptor(UUID var1, String var2, String var3, String var4, String var5, String var6, String var7, Timestamp var8, String var9, String var10, String var11, String var12, String var13, UUID var14) {
      this.stmt_id = var1;
      this.stmt_name = var2;
      this.stmt_type = var3;
      this.stmt_text = var4;
      this.jvm_id = var5;
      this.os_id = var6;
      this.xplain_mode = var7;
      this.xplain_time = DataTypeUtilities.clone(var8);
      this.thread_id = var9;
      this.xa_id = var10;
      this.session_id = var11;
      this.db_name = var12;
      this.drda_id = var13;
      this.timing_id = var14;
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.stmt_id.toString());
      var1.setString(2, this.stmt_name);
      var1.setString(3, this.stmt_type);
      var1.setString(4, this.stmt_text);
      var1.setString(5, this.jvm_id);
      var1.setString(6, this.os_id);
      var1.setString(7, this.xplain_mode);
      var1.setTimestamp(8, this.xplain_time);
      var1.setString(9, this.thread_id);
      var1.setString(10, this.xa_id);
      var1.setString(11, this.session_id);
      var1.setString(12, this.db_name);
      var1.setString(13, this.drda_id);
      var1.setString(14, this.timing_id != null ? this.timing_id.toString() : null);
   }

   public String getCatalogName() {
      return "SYSXPLAIN_STATEMENTS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("STMT_ID", false), SystemColumnImpl.getIdentifierColumn("STMT_NAME", true), SystemColumnImpl.getColumn("STMT_TYPE", 1, false, 3), SystemColumnImpl.getColumn("STMT_TEXT", 12, false, 32672), SystemColumnImpl.getColumn("JVM_ID", 12, false, 32672), SystemColumnImpl.getColumn("OS_IDENTIFIER", 12, false, 32672), SystemColumnImpl.getColumn("XPLAIN_MODE", 1, true, 1), SystemColumnImpl.getColumn("XPLAIN_TIME", 93, true), SystemColumnImpl.getColumn("XPLAIN_THREAD_ID", 12, false, 32672), SystemColumnImpl.getColumn("TRANSACTION_ID", 12, false, 32672), SystemColumnImpl.getColumn("SESSION_ID", 12, false, 32672), SystemColumnImpl.getIdentifierColumn("DATABASE_NAME", false), SystemColumnImpl.getColumn("DRDA_ID", 12, true, 32672), SystemColumnImpl.getUUIDColumn("TIMING_ID", true)};
   }
}
