package org.apache.derby.impl.sql.compile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.dictionary.OptionalTool;
import org.apache.derby.shared.common.i18n.MessageService;

public class OptTraceViewer implements OptionalTool {
   public void loadTool(String... var1) throws SQLException {
      if (var1 != null && var1.length == 1) {
         String var2 = var1[0];
         String var3 = "create view planCost as\nselect *\nfrom table\n(\n    planCost\n    (\n        'FILE_URL',\n        'planCost',\n        asList( 'stmtText', 'stmtID', 'qbID' ),\n        asList( 'pcComplete', 'pcSummary', 'pcType', 'ceEstimatedCost', 'ceEstimatedRowCount' )\n     )\n) v\n".replace("FILE_URL", var2);
         Connection var4 = this.getDerbyConnection();
         this.executeDDL(var4, "create type ArrayList external name 'java.util.ArrayList' language java");
         this.executeDDL(var4, "create function asList( cell varchar( 32672 ) ... ) returns ArrayList\nlanguage java parameter style derby no sql\nexternal name 'org.apache.derby.vti.XmlVTI.asList'\n");
         this.executeDDL(var4, "create function planCost\n(\n    xmlResourceName varchar( 32672 ),\n    rowTag varchar( 32672 ),\n    parentTags ArrayList,\n    childTags ArrayList\n)\nreturns table\n(\n    text varchar( 32672 ),\n    stmtID    int,\n    qbID   int,\n    complete  boolean,\n    summary   varchar( 32672 ),\n    type        varchar( 50 ),\n    estimatedCost        double,\n    estimatedRowCount    bigint\n)\nlanguage java parameter style derby_jdbc_result_set no sql\nexternal name 'org.apache.derby.vti.XmlVTI.xmlVTI'\n");
         this.executeDDL(var4, var3);
      } else {
         throw this.wrap(MessageService.getTextMessage("X0Y89.S", new Object[0]));
      }
   }

   public void unloadTool(String... var1) throws SQLException {
      Connection var2 = this.getDerbyConnection();
      this.executeDDL(var2, "drop view planCost");
      this.executeDDL(var2, "drop function planCost");
      this.executeDDL(var2, "drop function asList");
      this.executeDDL(var2, "drop type ArrayList restrict");
   }

   private Connection getDerbyConnection() throws SQLException {
      return DriverManager.getConnection("jdbc:default:connection");
   }

   private void executeDDL(Connection var1, String var2) throws SQLException {
      PreparedStatement var3 = this.prepareStatement(var1, var2);
      var3.execute();
      var3.close();
   }

   private PreparedStatement prepareStatement(Connection var1, String var2) throws SQLException {
      return var1.prepareStatement(var2);
   }

   private SQLException wrap(Throwable var1) {
      return new SQLException(var1.getMessage(), var1);
   }

   private SQLException wrap(String var1) {
      String var2 = "XJ001.U".substring(0, 5);
      return new SQLException(var1, var2);
   }
}
