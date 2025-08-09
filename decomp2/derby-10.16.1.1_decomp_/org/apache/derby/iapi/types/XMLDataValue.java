package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public interface XMLDataValue extends DataValueDescriptor {
   XMLDataValue XMLParse(StringDataValue var1, boolean var2, SqlXmlUtil var3) throws StandardException;

   StringDataValue XMLSerialize(StringDataValue var1, int var2, int var3, int var4) throws StandardException;

   BooleanDataValue XMLExists(SqlXmlUtil var1) throws StandardException;

   XMLDataValue XMLQuery(SqlXmlUtil var1, XMLDataValue var2) throws StandardException;

   void setXType(int var1);

   int getXType();

   void markAsHavingTopLevelAttr();

   boolean hasTopLevelAttr();
}
