package org.datanucleus.query.symbol;

import java.io.Serializable;

public class PropertySymbol implements Symbol, Serializable {
   private static final long serialVersionUID = -7781522317458406758L;
   int type;
   final String qualifiedName;
   Class valueType;

   public PropertySymbol(String qualifiedName) {
      this.qualifiedName = qualifiedName;
   }

   public PropertySymbol(String qualifiedName, Class type) {
      this.qualifiedName = qualifiedName;
      this.valueType = type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public int getType() {
      return this.type;
   }

   public String getQualifiedName() {
      return this.qualifiedName;
   }

   public Class getValueType() {
      return this.valueType;
   }

   public void setValueType(Class type) {
      this.valueType = type;
   }

   public String toString() {
      String typeName = null;
      if (this.type == 0) {
         typeName = "IDENTIFIER";
      } else if (this.type == 1) {
         typeName = "PARAMETER";
      } else if (this.type == 2) {
         typeName = "VARIABLE";
      }

      return "Symbol: " + this.qualifiedName + " [valueType=" + this.valueType + ", " + typeName + "]";
   }
}
