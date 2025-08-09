package org.apache.derby.impl.tools.planexporter;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.tools.ToolUtils;

public class AccessDatabase {
   private final Connection conn;
   private final String schema;
   private final String query;
   private final boolean schemaExists;
   private TreeNode[] data;
   private int depth;
   private String xmlDetails;
   private static final int ID = 0;
   private static final int P_ID = 1;
   private static final int NODE_TYPE = 2;
   private static final int NO_OF_OPENS = 3;
   private static final int INPUT_ROWS = 4;
   private static final int RETURNED_ROWS = 5;
   private static final int VISITED_PAGES = 6;
   private static final int SCAN_QUALIFIERS = 7;
   private static final int NEXT_QUALIFIERS = 8;
   private static final int SCANNED_OBJECT = 9;
   private static final int SCAN_TYPE = 10;
   private static final int SORT_TYPE = 11;
   private static final int NO_OF_OUTPUT_ROWS_BY_SORTER = 12;

   public String getQuery() {
      return this.query;
   }

   public int getDepth() {
      return this.depth;
   }

   public AccessDatabase(String var1, String var2, String var3) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
      this(createConnection(var1), var2, var3);
   }

   public AccessDatabase(Connection var1, String var2, String var3) throws SQLException {
      this.depth = 0;
      this.xmlDetails = "";
      this.conn = var1;
      this.schema = var2;
      this.query = var3;
      this.schemaExists = this.schemaExists();
      if (this.schemaExists) {
         this.setSchema();
      }

   }

   private static Connection createConnection(String var0) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
      Class var1 = var0.indexOf("://") != -1 ? Class.forName("org.apache.derby.jdbc.ClientDriver") : Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      var1.getConstructor().newInstance();
      return DriverManager.getConnection(var0);
   }

   private void setSchema() throws SQLException {
      PreparedStatement var1 = this.conn.prepareStatement("SET SCHEMA ?");
      var1.setString(1, this.schema);
      var1.execute();
      var1.close();
   }

   private boolean schemaExists() throws SQLException {
      ResultSet var1 = this.conn.getMetaData().getSchemas();

      try {
         while(var1.next()) {
            if (var1.getString(1).equals(this.schema)) {
               boolean var2 = true;
               return var2;
            }
         }

         return false;
      } finally {
         var1.close();
      }
   }

   public boolean verifySchemaExistance() {
      return this.schemaExists;
   }

   public void createXMLFragment() throws SQLException {
      this.createXMLData("select 'id=\"' ||RS_ID|| '\"' from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 0);
      this.createXMLData("select PARENT_RS_ID from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 1);
      this.createXMLData("select 'name=\"' ||OP_IDENTIFIER|| '\"' from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 2);
      this.createXMLData("select 'no_opens=\"' || TRIM(CHAR(NO_OPENS))|| '\"' from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 3);
      this.createXMLData("select 'input_rows=\"' || TRIM(CHAR(INPUT_ROWS))|| '\"' from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 4);
      this.createXMLData("select 'returned_rows=\"' || TRIM(CHAR(RETURNED_ROWS))|| '\"' from SYSXPLAIN_RESULTSETS where STMT_ID = ?", 5);
      this.createXMLData("select 'visited_pages=\"'|| TRIM(CHAR(NO_VISITED_PAGES))|| '\"' from (SYSXPLAIN_SCAN_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 6);
      this.createXMLData("select 'scan_qualifiers=\"'||SCAN_QUALIFIERS|| '\"' from (SYSXPLAIN_SCAN_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 7);
      this.createXMLData("select 'next_qualifiers=\"'||NEXT_QUALIFIERS|| '\"' from (SYSXPLAIN_SCAN_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 8);
      this.createXMLData("select 'scanned_object=\"'||SCAN_OBJECT_NAME|| '\"' from (SYSXPLAIN_SCAN_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 9);
      this.createXMLData("select 'scan_type=\"'||TRIM(SCAN_TYPE)|| '\"' from (SYSXPLAIN_SCAN_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 10);
      this.createXMLData("select 'sort_type=\"'||TRIM(SORT_TYPE)|| '\"' from (SYSXPLAIN_SORT_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 11);
      this.createXMLData("select 'sorter_output=\"'||TRIM(CHAR(NO_OUTPUT_ROWS))|| '\"' from (SYSXPLAIN_SORT_PROPS NATURAL RIGHT OUTER JOIN SYSXPLAIN_RESULTSETS) where STMT_ID = ?", 12);
   }

   public String getXmlString() {
      for(int var1 = 0; var1 < this.data.length; ++var1) {
         if (this.data[var1].getDepth() == 0) {
            String var10001 = this.xmlDetails;
            this.xmlDetails = var10001 + this.indent(1);
            var10001 = this.xmlDetails;
            this.xmlDetails = var10001 + this.data[var1].toString();
            this.getChildren(1, this.data[var1].getId());
            var10001 = this.xmlDetails;
            this.xmlDetails = var10001 + this.indent(1) + "</node>\n";
            break;
         }
      }

      return this.xmlDetails;
   }

   private void getChildren(int var1, String var2) {
      if (var1 <= this.depth) {
         for(int var3 = 0; var3 < this.data.length; ++var3) {
            if (this.data[var3].getDepth() == var1 && var2.indexOf(this.data[var3].getParent()) != -1) {
               String var10001 = this.xmlDetails;
               this.xmlDetails = var10001 + this.indent(var1 + 1);
               var10001 = this.xmlDetails;
               this.xmlDetails = var10001 + this.data[var3].toString();
               this.getChildren(var1 + 1, this.data[var3].getId());
               var10001 = this.xmlDetails;
               this.xmlDetails = var10001 + this.indent(var1 + 1) + "</node>\n";
            }
         }
      }

   }

   public String indent(int var1) {
      String var2 = "";

      for(int var3 = 0; var3 <= var1 + 1; ++var3) {
         var2 = var2 + "    ";
      }

      return var2;
   }

   public void markTheDepth() {
      int var1;
      for(var1 = 0; this.data[var1].getParent().indexOf("null") == -1; ++var1) {
      }

      this.data[var1].setDepth(this.depth);
      this.findChildren(var1, this.depth);
   }

   private void findChildren(int var1, int var2) {
      if (var2 > this.depth) {
         this.depth = var2;
      }

      for(int var3 = 0; var3 < this.data.length; ++var3) {
         if (this.data[var3].getParent().indexOf("null") == -1 && this.data[var1].getId().indexOf(this.data[var3].getParent()) != -1 && var3 != var1) {
            this.data[var3].setDepth(var2 + 1);
            this.findChildren(var3, var2 + 1);
         }
      }

   }

   public boolean initializeDataArray() throws SQLException {
      if (this.noOfNodes() == 0) {
         return false;
      } else {
         this.data = new TreeNode[this.noOfNodes()];

         for(int var1 = 0; var1 < this.data.length; ++var1) {
            this.data[var1] = new TreeNode();
         }

         return true;
      }
   }

   private void createXMLData(String var1, int var2) throws SQLException {
      PreparedStatement var3 = this.conn.prepareStatement(var1);
      var3.setString(1, this.getQuery());
      ResultSet var4 = var3.executeQuery();

      for(int var5 = 0; var4.next(); ++var5) {
         String var6 = var4.getString(1);
         if (var6 != null) {
            var6 = this.escapeInAttribute(var6);
            switch (var2) {
               case 0:
                  this.data[var5].setId(var6 + " ");
                  break;
               case 1:
                  this.data[var5].setParent(var6);
                  break;
               case 2:
                  this.data[var5].setNodeType(var6 + " ");
                  break;
               case 3:
                  this.data[var5].setNoOfOpens(var6 + " ");
                  break;
               case 4:
                  this.data[var5].setInputRows(var6 + " ");
                  break;
               case 5:
                  this.data[var5].setReturnedRows(var6 + " ");
                  break;
               case 6:
                  this.data[var5].setVisitedPages(var6 + " ");
                  break;
               case 7:
                  this.data[var5].setScanQualifiers(var6 + " ");
                  break;
               case 8:
                  this.data[var5].setNextQualifiers(var6 + " ");
                  break;
               case 9:
                  this.data[var5].setScannedObject(var6 + " ");
                  break;
               case 10:
                  this.data[var5].setScanType(var6 + " ");
                  break;
               case 11:
                  this.data[var5].setSortType(var6 + " ");
                  break;
               case 12:
                  this.data[var5].setSorterOutput(var6 + " ");
            }
         } else {
            switch (var2) {
               case 1:
                  this.data[var5].setParent("" + var6);
            }
         }
      }

      var4.close();
      var3.close();
   }

   private int noOfNodes() throws SQLException {
      PreparedStatement var1 = this.conn.prepareStatement("select count(*) from SYSXPLAIN_RESULTSETS where STMT_ID = ?");
      var1.setString(1, this.getQuery());
      ResultSet var2 = var1.executeQuery();
      var2.next();
      int var3 = var2.getInt(1);
      var2.close();
      var1.close();
      return var3;
   }

   public String statement() throws SQLException {
      PreparedStatement var1 = this.conn.prepareStatement("select STMT_TEXT from SYSXPLAIN_STATEMENTS where STMT_ID = ?");
      var1.setString(1, this.getQuery());
      ResultSet var2 = var1.executeQuery();
      var2.next();
      String var3 = var2.getString(1);
      var2.close();
      var1.close();
      var3 = escapeForXML(var3);
      return "<statement>" + var3 + "</statement>\n";
   }

   private static String escapeForXML(String var0) {
      StringBuffer var1 = new StringBuffer();

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         switch (var3) {
            case '"':
               var1.append("&quot;");
               break;
            case '&':
               var1.append("&amp;");
               break;
            case '\'':
               var1.append("&apos;");
               break;
            case '<':
               var1.append("&lt;");
               break;
            case '>':
               var1.append("&gt;");
               break;
            default:
               var1.append(var3);
         }
      }

      return var1.toString();
   }

   private String escapeInAttribute(String var1) {
      if (var1.indexOf(34) == -1) {
         return var1;
      } else {
         String var2 = escapeForXML(var1.substring(var1.indexOf(34) + 1, var1.length() - 1));
         String var10000 = var1.substring(0, var1.indexOf(34) + 1);
         return var10000 + var2 + "\"";
      }
   }

   public String time() throws SQLException {
      PreparedStatement var1 = this.conn.prepareStatement("select '<time>'||TRIM(CHAR(XPLAIN_TIME))||'</time>' from SYSXPLAIN_STATEMENTS where STMT_ID = ?");
      var1.setString(1, this.getQuery());
      ResultSet var2 = var1.executeQuery();
      var2.next();
      String var3 = var2.getString(1);
      var2.close();
      var1.close();
      return var3 + "\n";
   }

   public String stmtID() {
      return "<stmt_id>" + this.getQuery() + "</stmt_id>\n";
   }

   public void closeConnection() {
      try {
         if (this.conn != null) {
            this.conn.close();
         }
      } catch (SQLException var2) {
      }

   }

   public TreeNode[] getData() {
      return (TreeNode[])ToolUtils.copy((Object[])this.data);
   }
}
