package org.apache.derby.vti;

public class VTIContext {
   private String _vtiSchema;
   private String _vtiTable;
   private String _statementText;

   public VTIContext(String var1, String var2, String var3) {
      this._vtiSchema = var1;
      this._vtiTable = var2;
      this._statementText = var3;
   }

   public String vtiSchema() {
      return this._vtiSchema;
   }

   public String vtiTable() {
      return this._vtiTable;
   }

   public String statementText() {
      return this._statementText;
   }
}
