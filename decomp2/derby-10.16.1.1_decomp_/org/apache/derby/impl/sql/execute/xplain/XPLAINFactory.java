package org.apache.derby.impl.sql.execute.xplain;

import java.sql.SQLException;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINFactoryIF;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.shared.common.error.StandardException;

public class XPLAINFactory implements XPLAINFactoryIF {
   private XPLAINVisitor currentVisitor = new XPLAINDefaultVisitor();
   private String currentSchema = null;

   public XPLAINVisitor getXPLAINVisitor() throws StandardException {
      try {
         LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
         String var2 = var1.getXplainSchema();
         if (var2 != this.currentSchema) {
            this.currentSchema = var2;
            if (this.currentSchema == null) {
               this.currentVisitor = new XPLAINDefaultVisitor();
            } else {
               this.currentVisitor = new XPLAINSystemTableVisitor();
            }
         }
      } catch (SQLException var3) {
         throw StandardException.plainWrapException(var3);
      }

      return this.currentVisitor;
   }

   public void freeResources() {
      this.currentVisitor = null;
      this.currentSchema = null;
   }
}
