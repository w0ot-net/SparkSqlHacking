package org.apache.derby.impl.sql.execute.rts;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.execute.xplain.XPLAINUtil;
import org.apache.derby.shared.common.i18n.MessageService;

abstract class RealBasicNoPutResultSetStatistics implements ResultSetStatistics {
   public int numOpens;
   public int rowsSeen;
   public int rowsFiltered;
   public long constructorTime;
   public long openTime;
   public long nextTime;
   public long closeTime;
   public long inspectOverall;
   public long inspectNum;
   public String inspectDesc;
   public double optimizerEstimatedRowCount;
   public double optimizerEstimatedCost;

   public RealBasicNoPutResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, double var12, double var14) {
      this.numOpens = var1;
      this.rowsSeen = var2;
      this.rowsFiltered = var3;
      this.constructorTime = var4;
      this.openTime = var6;
      this.nextTime = var8;
      this.closeTime = var10;
      this.optimizerEstimatedRowCount = var12;
      this.optimizerEstimatedCost = var14;
   }

   protected final String dumpTimeStats(String var1, String var2) {
      return var2 + MessageService.getTextMessage("42Z33.U", new Object[0]) + " " + this.constructorTime + "\n" + var2 + MessageService.getTextMessage("42Z34.U", new Object[0]) + " " + this.openTime + "\n" + var2 + MessageService.getTextMessage("42Z35.U", new Object[0]) + " " + this.nextTime + "\n" + var2 + MessageService.getTextMessage("42Z36.U", new Object[0]) + " " + this.closeTime;
   }

   protected final String dumpEstimatedCosts(String var1) {
      return var1 + MessageService.getTextMessage("43X07.U", new Object[]{this.optimizerEstimatedRowCount}) + "\n" + var1 + MessageService.getTextMessage("43X08.U", new Object[]{this.optimizerEstimatedCost});
   }

   public Vector getChildren() {
      return new Vector();
   }

   public long getTotalTime() {
      return this.openTime + this.nextTime + this.closeTime;
   }

   public long getChildrenTime() {
      long var1 = 0L;

      for(Enumeration var3 = this.getChildren().elements(); var3.hasMoreElements(); var1 += ((RealBasicNoPutResultSetStatistics)var3.nextElement()).getTotalTime()) {
      }

      return var1;
   }

   public long getNodeTime() {
      return this.getTotalTime() - this.getChildrenTime();
   }

   public abstract String getNodeName();

   public String getNodeOn() {
      return "";
   }

   public double getEstimatedRowCount() {
      return this.optimizerEstimatedRowCount;
   }

   public String getRSXplainDetails() {
      return null;
   }

   public Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6) {
      return new XPLAINResultSetDescriptor((UUID)var1, this.getRSXplainType(), this.getRSXplainDetails(), this.numOpens, (Integer)null, (String)null, (String)null, (UUID)var2, this.optimizerEstimatedRowCount, this.optimizerEstimatedCost, (Integer)null, (String)null, (Integer)null, this.rowsSeen, (Integer)null, this.rowsFiltered, this.rowsSeen - this.rowsFiltered, (Integer)null, (String)null, (UUID)var3, (UUID)var4, (UUID)var5, (UUID)var6);
   }

   public Object getResultSetTimingsDescriptor(Object var1) {
      return new XPLAINResultSetTimingsDescriptor((UUID)var1, this.constructorTime, this.openTime, this.nextTime, this.closeTime, this.getNodeTime(), XPLAINUtil.getAVGNextTime(this.nextTime, (long)this.rowsSeen), (Long)null, (Long)null, (Long)null, (Long)null);
   }

   public Object getSortPropsDescriptor(Object var1) {
      return null;
   }

   public Object getScanPropsDescriptor(Object var1) {
      return null;
   }
}
