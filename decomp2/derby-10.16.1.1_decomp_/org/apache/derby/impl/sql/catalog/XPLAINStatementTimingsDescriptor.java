package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.types.DataTypeUtilities;

public class XPLAINStatementTimingsDescriptor extends XPLAINTableDescriptor {
   private UUID timing_id;
   private Long parse_time;
   private Long bind_time;
   private Long optimize_time;
   private Long generate_time;
   private Long compile_time;
   private Long execute_time;
   private Timestamp begin_comp_time;
   private Timestamp end_comp_time;
   private Timestamp begin_exe_time;
   private Timestamp end_exe_time;
   static final String TABLENAME_STRING = "SYSXPLAIN_STATEMENT_TIMINGS";
   private static final String[][] indexColumnNames = new String[][]{{"TIMING_ID"}};

   public XPLAINStatementTimingsDescriptor() {
   }

   public XPLAINStatementTimingsDescriptor(UUID var1, Long var2, Long var3, Long var4, Long var5, Long var6, Long var7, Timestamp var8, Timestamp var9, Timestamp var10, Timestamp var11) {
      this.timing_id = var1;
      this.parse_time = var2;
      this.bind_time = var3;
      this.optimize_time = var4;
      this.generate_time = var5;
      this.compile_time = var6;
      this.execute_time = var7;
      this.begin_comp_time = DataTypeUtilities.clone(var8);
      this.end_comp_time = DataTypeUtilities.clone(var9);
      this.begin_exe_time = DataTypeUtilities.clone(var10);
      this.end_exe_time = DataTypeUtilities.clone(var11);
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.timing_id.toString());
      var1.setObject(2, this.parse_time, -5);
      var1.setObject(3, this.bind_time, -5);
      var1.setObject(4, this.optimize_time, -5);
      var1.setObject(5, this.generate_time, -5);
      var1.setObject(6, this.compile_time, -5);
      var1.setObject(7, this.execute_time, -5);
      var1.setTimestamp(8, this.begin_comp_time);
      var1.setTimestamp(9, this.end_comp_time);
      var1.setTimestamp(10, this.begin_exe_time);
      var1.setTimestamp(11, this.end_exe_time);
   }

   public String getCatalogName() {
      return "SYSXPLAIN_STATEMENT_TIMINGS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TIMING_ID", false), SystemColumnImpl.getColumn("PARSE_TIME", -5, false), SystemColumnImpl.getColumn("BIND_TIME", -5, false), SystemColumnImpl.getColumn("OPTIMIZE_TIME", -5, false), SystemColumnImpl.getColumn("GENERATE_TIME", -5, false), SystemColumnImpl.getColumn("COMPILE_TIME", -5, false), SystemColumnImpl.getColumn("EXECUTE_TIME", -5, false), SystemColumnImpl.getColumn("BEGIN_COMP_TIME", 93, false), SystemColumnImpl.getColumn("END_COMP_TIME", 93, false), SystemColumnImpl.getColumn("BEGIN_EXE_TIME", 93, false), SystemColumnImpl.getColumn("END_EXE_TIME", 93, false)};
   }
}
