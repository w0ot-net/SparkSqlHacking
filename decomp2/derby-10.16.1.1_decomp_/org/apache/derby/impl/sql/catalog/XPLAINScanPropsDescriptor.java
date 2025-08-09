package org.apache.derby.impl.sql.catalog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;

public class XPLAINScanPropsDescriptor extends XPLAINTableDescriptor {
   private UUID scan_rs_id;
   private String scan_object_name;
   private String scan_object_type;
   private String scan_type;
   private String isolation_level;
   private Integer no_visited_pages;
   private Integer no_visited_rows;
   private Integer no_qualified_rows;
   private Integer no_visited_deleted_rows;
   private Integer no_fetched_columns;
   private String bitset_of_fetched_columns;
   private Integer btree_height;
   private Integer fetch_size;
   private String start_position;
   private String stop_position;
   private String scan_qualifiers;
   private String next_qualifiers;
   private String hash_key_column_numbers;
   private Integer hash_table_size;
   static final String TABLENAME_STRING = "SYSXPLAIN_SCAN_PROPS";
   private static final String[][] indexColumnNames = new String[][]{{"SCAN_RS_ID"}};

   public XPLAINScanPropsDescriptor() {
   }

   public XPLAINScanPropsDescriptor(UUID var1, String var2, String var3, String var4, String var5, Integer var6, Integer var7, Integer var8, Integer var9, Integer var10, String var11, Integer var12, Integer var13, String var14, String var15, String var16, String var17, String var18, Integer var19) {
      this.scan_rs_id = var1;
      this.scan_object_name = var2;
      this.scan_object_type = var3;
      this.scan_type = var4;
      this.isolation_level = var5;
      this.no_visited_pages = var6;
      this.no_visited_rows = var7;
      this.no_qualified_rows = var8;
      this.no_visited_deleted_rows = var9;
      this.no_fetched_columns = var10;
      this.bitset_of_fetched_columns = var11;
      this.btree_height = var12;
      this.fetch_size = var13;
      this.start_position = var14;
      this.stop_position = var15;
      this.scan_qualifiers = var16;
      this.next_qualifiers = var17;
      this.hash_key_column_numbers = var18;
      this.hash_table_size = var19;
   }

   public void setStatementParameters(PreparedStatement var1) throws SQLException {
      var1.setString(1, this.scan_rs_id.toString());
      var1.setString(2, this.scan_object_name);
      var1.setString(3, this.scan_object_type);
      var1.setString(4, this.scan_type);
      var1.setString(5, this.isolation_level);
      var1.setObject(6, this.no_visited_pages, 4);
      var1.setObject(7, this.no_visited_rows, 4);
      var1.setObject(8, this.no_qualified_rows, 4);
      var1.setObject(9, this.no_visited_deleted_rows, 4);
      var1.setObject(10, this.no_fetched_columns, 4);
      var1.setString(11, this.bitset_of_fetched_columns);
      var1.setObject(12, this.btree_height, 4);
      var1.setObject(13, this.fetch_size, 4);
      var1.setString(14, this.start_position);
      var1.setString(15, this.stop_position);
      var1.setString(16, this.scan_qualifiers);
      var1.setString(17, this.next_qualifiers);
      var1.setString(18, this.hash_key_column_numbers);
      var1.setObject(19, this.hash_table_size, 4);
   }

   public void setScan_type(String var1) {
      this.scan_type = var1;
   }

   public void setNo_visited_pages(Integer var1) {
      this.no_visited_pages = var1;
   }

   public void setNo_visited_rows(Integer var1) {
      this.no_visited_rows = var1;
   }

   public void setNo_qualified_rows(Integer var1) {
      this.no_qualified_rows = var1;
   }

   public void setNo_fetched_columns(Integer var1) {
      this.no_fetched_columns = var1;
   }

   public void setNo_visited_deleted_rows(Integer var1) {
      this.no_visited_deleted_rows = var1;
   }

   public void setBtree_height(Integer var1) {
      this.btree_height = var1;
   }

   public void setBitset_of_fetched_columns(String var1) {
      this.bitset_of_fetched_columns = var1;
   }

   public String getCatalogName() {
      return "SYSXPLAIN_SCAN_PROPS";
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("SCAN_RS_ID", false), SystemColumnImpl.getIdentifierColumn("SCAN_OBJECT_NAME", false), SystemColumnImpl.getIndicatorColumn("SCAN_OBJECT_TYPE"), SystemColumnImpl.getColumn("SCAN_TYPE", 1, false, 8), SystemColumnImpl.getColumn("ISOLATION_LEVEL", 1, true, 3), SystemColumnImpl.getColumn("NO_VISITED_PAGES", 4, true), SystemColumnImpl.getColumn("NO_VISITED_ROWS", 4, true), SystemColumnImpl.getColumn("NO_QUALIFIED_ROWS", 4, true), SystemColumnImpl.getColumn("NO_VISITED_DELETED_ROWS", 4, true), SystemColumnImpl.getColumn("NO_FETCHED_COLUMNS", 4, true), SystemColumnImpl.getColumn("BITSET_OF_FETCHED_COLUMNS", 12, true, 32672), SystemColumnImpl.getColumn("BTREE_HEIGHT", 4, true), SystemColumnImpl.getColumn("FETCH_SIZE", 4, true), SystemColumnImpl.getColumn("START_POSITION", 12, true, 32672), SystemColumnImpl.getColumn("STOP_POSITION", 12, true, 32672), SystemColumnImpl.getColumn("SCAN_QUALIFIERS", 12, true, 32672), SystemColumnImpl.getColumn("NEXT_QUALIFIERS", 12, true, 32672), SystemColumnImpl.getColumn("HASH_KEY_COLUMN_NUMBERS", 12, true, 32672), SystemColumnImpl.getColumn("HASH_TABLE_SIZE", 4, true)};
   }
}
