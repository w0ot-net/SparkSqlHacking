package org.apache.derby.impl.sql.execute.rts;

abstract class RealNoPutResultSetStatistics extends RealBasicNoPutResultSetStatistics {
   public int resultSetNumber;
   protected String indent;
   protected String subIndent;
   protected int sourceDepth;

   public RealNoPutResultSetStatistics(int var1, int var2, int var3, long var4, long var6, long var8, long var10, int var12, double var13, double var15) {
      super(var1, var2, var3, var4, var6, var8, var10, var13, var15);
      this.resultSetNumber = var12;
   }

   protected void initFormatInfo(int var1) {
      char[] var2 = new char[var1];
      char[] var3 = new char[var1 + 1];
      this.sourceDepth = var1 + 1;

      for(var3[var1] = '\t'; var1 > 0; --var1) {
         var3[var1 - 1] = '\t';
         var2[var1 - 1] = '\t';
      }

      this.indent = new String(var2);
      this.subIndent = new String(var3);
   }
}
