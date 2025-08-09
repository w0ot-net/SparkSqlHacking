package org.apache.derby.iapi.sql.execute.xplain;

public interface XPLAINable {
   void accept(XPLAINVisitor var1);

   String getRSXplainType();

   String getRSXplainDetails();

   Object getResultSetDescriptor(Object var1, Object var2, Object var3, Object var4, Object var5, Object var6);

   Object getResultSetTimingsDescriptor(Object var1);

   Object getSortPropsDescriptor(Object var1);

   Object getScanPropsDescriptor(Object var1);
}
