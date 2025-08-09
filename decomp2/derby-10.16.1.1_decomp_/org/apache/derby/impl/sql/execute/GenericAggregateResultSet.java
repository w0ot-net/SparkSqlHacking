package org.apache.derby.impl.sql.execute;

import java.util.Vector;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.SQLWarningFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

abstract class GenericAggregateResultSet extends NoPutResultSetImpl {
   protected GenericAggregator[] aggregates;
   protected AggregatorInfoList aggInfoList;
   public NoPutResultSet source;
   protected NoPutResultSet originalSource;
   private final ExecIndexRow rowTemplate;

   GenericAggregateResultSet(NoPutResultSet var1, int var2, Activation var3, int var4, int var5, double var6, double var8) throws StandardException {
      super(var3, var5, var6, var8);
      this.source = var1;
      this.originalSource = var1;
      ExecPreparedStatement var10 = var3.getPreparedStatement();
      ExecutionFactory var11 = var3.getExecutionFactory();
      this.rowTemplate = var11.getIndexableRow(((ExecRowBuilder)var10.getSavedObject(var4)).build(var11));
      this.aggInfoList = (AggregatorInfoList)var10.getSavedObject(var2);
      this.aggregates = this.getSortAggregators(this.aggInfoList, false, var3.getLanguageConnectionContext(), var1);
   }

   ExecIndexRow getRowTemplate() {
      return this.rowTemplate;
   }

   protected final GenericAggregator[] getSortAggregators(AggregatorInfoList var1, boolean var2, LanguageConnectionContext var3, NoPutResultSet var4) throws StandardException {
      Vector var6 = new Vector();
      ClassFactory var7 = var3.getLanguageConnectionFactory().getClassFactory();

      for(AggregatorInfo var9 : var1) {
         if (!var2 || !var9.isDistinct()) {
            var6.addElement(new GenericAggregator(var9, var7));
         }
      }

      GenericAggregator[] var5 = new GenericAggregator[var6.size()];
      var6.copyInto(var5);
      return var5;
   }

   protected final ExecIndexRow finishAggregation(ExecIndexRow var1) throws StandardException {
      int var2 = this.aggregates.length;
      if (var1 == null) {
         var1 = this.getRowTemplate();
      }

      this.setCurrentRow(var1);
      boolean var3 = false;

      for(int var4 = 0; var4 < var2; ++var4) {
         GenericAggregator var5 = this.aggregates[var4];
         if (var5.finish(var1)) {
            var3 = true;
         }
      }

      if (var3) {
         this.addWarning(SQLWarningFactory.newSQLWarning("01003", new Object[0]));
      }

      return var1;
   }

   public void finish() throws StandardException {
      this.source.finish();
      super.finish();
   }

   public Element toXML(Element var1, String var2) throws Exception {
      Element var3 = super.toXML(var1, var2);
      NodeList var4 = var3.getChildNodes();

      for(int var5 = 0; var5 < var4.getLength(); ++var5) {
         Node var6 = var4.item(0);
         if ("originalSource".equals(var6.getNodeName())) {
            var3.removeChild(var6);
         }
      }

      return var3;
   }
}
