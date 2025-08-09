package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;

public class XPLAINResultSetTimingsDescriptor extends XPLAINTableDescriptor {
   private UUID timing_id;
   private Long constructor_time;
   private Long open_time;
   private Long next_time;
   private Long close_time;
   private Long execute_time;
   private Long avg_next_time_per_row;
   private Long projection_time;
   private Long restriction_time;
   private Long temp_cong_create_time;
   private Long temp_cong_fetch_time;
   static final String TABLENAME_STRING = "SYSXPLAIN_RESULTSET_TIMINGS";
   private static final String[][] indexColumnNames = new String[][]{{"TIMING_ID"}};

   public XPLAINResultSetTimingsDescriptor() {
   }

   public XPLAINResultSetTimingsDescriptor(UUID var1, Long var2, Long var3, Long var4, Long var5, Long var6, Long var7, Long var8, Long var9, Long var10, Long var11) {
      this.timing_id = var1;
      this.constructor_time = var2;
      this.open_time = var3;
      this.next_time = var4;
      this.close_time = var5;
      this.execute_time = var6;
      this.avg_next_time_per_row = var7;
      this.projection_time = var8;
      this.restriction_time = var9;
      this.temp_cong_create_time = var10;
      this.temp_cong_fetch_time = var11;
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.timing_id.toString());
      var1.setObject(2, this.constructor_time, -5);
      var1.setObject(3, this.open_time, -5);
      var1.setObject(4, this.next_time, -5);
      var1.setObject(5, this.close_time, -5);
      var1.setObject(6, this.execute_time, -5);
      var1.setObject(7, this.avg_next_time_per_row, -5);
      var1.setObject(8, this.projection_time, -5);
      var1.setObject(9, this.restriction_time, -5);
      var1.setObject(10, this.temp_cong_create_time, -5);
      var1.setObject(11, this.temp_cong_fetch_time, -5);
   }

   public String getCatalogName() {
      return "SYSXPLAIN_RESULTSET_TIMINGS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TIMING_ID", false), SystemColumnImpl.getColumn("CONSTRUCTOR_TIME", -5, true), SystemColumnImpl.getColumn("OPEN_TIME", -5, true), SystemColumnImpl.getColumn("NEXT_TIME", -5, true), SystemColumnImpl.getColumn("CLOSE_TIME", -5, true), SystemColumnImpl.getColumn("EXECUTE_TIME", -5, true), SystemColumnImpl.getColumn("AVG_NEXT_TIME_PER_ROW", -5, true), SystemColumnImpl.getColumn("PROJECTION_TIME", -5, true), SystemColumnImpl.getColumn("RESTRICTION_TIME", -5, true), SystemColumnImpl.getColumn("TEMP_CONG_CREATE_TIME", -5, true), SystemColumnImpl.getColumn("TEMP_CONG_FETCH_TIME", -5, true)};
   }
}
