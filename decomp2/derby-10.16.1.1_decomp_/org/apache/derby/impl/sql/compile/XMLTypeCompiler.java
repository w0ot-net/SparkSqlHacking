package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

public class XMLTypeCompiler extends BaseTypeCompiler {
   public boolean convertible(TypeId var1, boolean var2) {
      return var1.isXMLTypeId();
   }

   public boolean compatible(TypeId var1) {
      return var1.isXMLTypeId();
   }

   public boolean storable(TypeId var1, ClassFactory var2) {
      return var1.isXMLTypeId();
   }

   public String interfaceName() {
      return "org.apache.derby.iapi.types.XMLDataValue";
   }

   public String getCorrespondingPrimitiveTypeName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      return var1 == 456 ? "org.apache.derby.iapi.types.XML" : null;
   }

   public int getCastToCharWidth(DataTypeDescriptor var1) {
      return -1;
   }

   String nullMethodName() {
      return "getNullXML";
   }

   protected String dataValueMethodName() {
      int var1 = this.getStoredFormatIdFromTypeId();
      return var1 == 456 ? "getXMLDataValue" : null;
   }
}
