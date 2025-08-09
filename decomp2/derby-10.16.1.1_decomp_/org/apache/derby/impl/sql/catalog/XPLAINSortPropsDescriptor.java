package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;

public class XPLAINSortPropsDescriptor extends XPLAINTableDescriptor {
   private UUID sort_rs_id;
   private String sort_type;
   private Integer no_input_rows;
   private Integer no_output_rows;
   private Integer no_merge_runs;
   private String merge_run_details;
   private String eliminate_dups;
   private String in_sort_order;
   private String distinct_aggregate;
   static final String TABLENAME_STRING = "SYSXPLAIN_SORT_PROPS";
   private static final String[][] indexColumnNames = new String[][]{{"SORT_RS_ID"}};

   public XPLAINSortPropsDescriptor() {
   }

   public XPLAINSortPropsDescriptor(UUID var1, String var2, Integer var3, Integer var4, Integer var5, String var6, String var7, String var8, String var9) {
      this.sort_rs_id = var1;
      this.sort_type = var2;
      this.no_input_rows = var3;
      this.no_output_rows = var4;
      this.no_merge_runs = var5;
      this.merge_run_details = var6;
      this.eliminate_dups = var7;
      this.in_sort_order = var8;
      this.distinct_aggregate = var9;
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.sort_rs_id.toString());
      var1.setString(2, this.sort_type);
      var1.setObject(3, this.no_input_rows, 4);
      var1.setObject(4, this.no_output_rows, 4);
      var1.setObject(5, this.no_merge_runs, 4);
      var1.setString(6, this.merge_run_details);
      var1.setString(7, this.eliminate_dups);
      var1.setString(8, this.in_sort_order);
      var1.setString(9, this.distinct_aggregate);
   }

   public void setSort_type(String var1) {
      this.sort_type = var1;
   }

   public void setNo_input_rows(Integer var1) {
      this.no_input_rows = var1;
   }

   public void setNo_output_rows(Integer var1) {
      this.no_output_rows = var1;
   }

   public void setNo_merge_runs(Integer var1) {
      this.no_merge_runs = var1;
   }

   public void setMerge_run_details(String var1) {
      this.merge_run_details = var1;
   }

   public String getCatalogName() {
      return "SYSXPLAIN_SORT_PROPS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("SORT_RS_ID", false), SystemColumnImpl.getColumn("SORT_TYPE", 1, true, 2), SystemColumnImpl.getColumn("NO_INPUT_ROWS", 4, true), SystemColumnImpl.getColumn("NO_OUTPUT_ROWS", 4, true), SystemColumnImpl.getColumn("NO_MERGE_RUNS", 4, true), SystemColumnImpl.getColumn("MERGE_RUN_DETAILS", 12, true, 32672), SystemColumnImpl.getColumn("ELIMINATE_DUPLICATES", 1, true, 1), SystemColumnImpl.getColumn("IN_SORT_ORDER", 1, true, 1), SystemColumnImpl.getColumn("DISTINCT_AGGREGATE", 1, true, 1)};
   }
}
