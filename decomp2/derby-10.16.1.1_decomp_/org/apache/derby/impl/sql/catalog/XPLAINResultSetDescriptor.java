package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;

public class XPLAINResultSetDescriptor extends XPLAINTableDescriptor {
   private UUID rs_id;
   private String op_identifier;
   private String op_details;
   private Integer no_opens;
   private Integer no_index_updates;
   private String lock_granularity;
   private String lock_mode;
   private UUID parent_rs_id;
   private Double est_row_count;
   private Double est_cost;
   private Integer affected_rows;
   private String deferred_rows;
   private Integer input_rows;
   private Integer seen_rows;
   private Integer seen_rows_right;
   private Integer filtered_rows;
   private Integer returned_rows;
   private Integer empty_right_rows;
   private String index_key_optimization;
   private UUID scan_rs_id;
   private UUID sort_rs_id;
   private UUID stmt_id;
   private UUID timing_id;
   static final String TABLENAME_STRING = "SYSXPLAIN_RESULTSETS";
   private static final String[][] indexColumnNames = new String[][]{{"RS_ID"}};

   public XPLAINResultSetDescriptor() {
   }

   public XPLAINResultSetDescriptor(UUID var1, String var2, String var3, Integer var4, Integer var5, String var6, String var7, UUID var8, Double var9, Double var10, Integer var11, String var12, Integer var13, Integer var14, Integer var15, Integer var16, Integer var17, Integer var18, String var19, UUID var20, UUID var21, UUID var22, UUID var23) {
      this.rs_id = var1;
      this.op_identifier = var2;
      this.op_details = var3;
      this.no_opens = var4;
      this.no_index_updates = var5;
      this.lock_granularity = var7;
      this.lock_mode = var6;
      this.parent_rs_id = var8;
      this.est_row_count = var9;
      this.est_cost = var10;
      this.affected_rows = var11;
      this.deferred_rows = var12;
      this.input_rows = var13;
      this.seen_rows = var14;
      this.seen_rows_right = var15;
      this.filtered_rows = var16;
      this.returned_rows = var17;
      this.empty_right_rows = var18;
      this.index_key_optimization = var19;
      this.scan_rs_id = var20;
      this.sort_rs_id = var21;
      this.stmt_id = var22;
      this.timing_id = var23;
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.rs_id.toString());
      var1.setString(2, this.op_identifier);
      var1.setString(3, this.op_details);
      var1.setObject(4, this.no_opens, 4);
      var1.setObject(5, this.no_index_updates, 4);
      var1.setString(6, this.lock_mode);
      var1.setString(7, this.lock_granularity);
      var1.setString(8, this.parent_rs_id != null ? this.parent_rs_id.toString() : null);
      var1.setObject(9, this.est_row_count, 8);
      var1.setObject(10, this.est_cost, 8);
      var1.setObject(11, this.affected_rows, 4);
      var1.setString(12, this.deferred_rows);
      var1.setObject(13, this.input_rows, 4);
      var1.setObject(14, this.seen_rows, 4);
      var1.setObject(15, this.seen_rows_right, 4);
      var1.setObject(16, this.filtered_rows, 4);
      var1.setObject(17, this.returned_rows, 4);
      var1.setObject(18, this.empty_right_rows, 4);
      var1.setString(19, this.index_key_optimization);
      var1.setString(20, this.scan_rs_id != null ? this.scan_rs_id.toString() : null);
      var1.setString(21, this.sort_rs_id != null ? this.sort_rs_id.toString() : null);
      var1.setString(22, this.stmt_id != null ? this.stmt_id.toString() : null);
      var1.setString(23, this.timing_id != null ? this.timing_id.toString() : null);
   }

   public String getCatalogName() {
      return "SYSXPLAIN_RESULTSETS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("RS_ID", false), SystemColumnImpl.getColumn("OP_IDENTIFIER", 12, false, 32672), SystemColumnImpl.getColumn("OP_DETAILS", 12, true, 32672), SystemColumnImpl.getColumn("NO_OPENS", 4, true), SystemColumnImpl.getColumn("NO_INDEX_UPDATES", 4, true), SystemColumnImpl.getColumn("LOCK_MODE", 1, true, 2), SystemColumnImpl.getColumn("LOCK_GRANULARITY", 1, true, 1), SystemColumnImpl.getUUIDColumn("PARENT_RS_ID", true), SystemColumnImpl.getColumn("EST_ROW_COUNT", 8, true), SystemColumnImpl.getColumn("EST_COST", 8, true), SystemColumnImpl.getColumn("AFFECTED_ROWS", 4, true), SystemColumnImpl.getColumn("DEFERRED_ROWS", 1, true, 1), SystemColumnImpl.getColumn("INPUT_ROWS", 4, true), SystemColumnImpl.getColumn("SEEN_ROWS", 4, true), SystemColumnImpl.getColumn("SEEN_ROWS_RIGHT", 4, true), SystemColumnImpl.getColumn("FILTERED_ROWS", 4, true), SystemColumnImpl.getColumn("RETURNED_ROWS", 4, true), SystemColumnImpl.getColumn("EMPTY_RIGHT_ROWS", 4, true), SystemColumnImpl.getColumn("INDEX_KEY_OPT", 1, true, 1), SystemColumnImpl.getUUIDColumn("SCAN_RS_ID", true), SystemColumnImpl.getUUIDColumn("SORT_RS_ID", true), SystemColumnImpl.getUUIDColumn("STMT_ID", false), SystemColumnImpl.getUUIDColumn("TIMING_ID", true)};
   }
}
