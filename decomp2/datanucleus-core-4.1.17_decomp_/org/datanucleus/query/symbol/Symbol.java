package org.datanucleus.query.symbol;

public interface Symbol {
   int IDENTIFIER = 0;
   int PARAMETER = 1;
   int VARIABLE = 2;

   void setType(int var1);

   int getType();

   String getQualifiedName();

   void setValueType(Class var1);

   Class getValueType();
}
