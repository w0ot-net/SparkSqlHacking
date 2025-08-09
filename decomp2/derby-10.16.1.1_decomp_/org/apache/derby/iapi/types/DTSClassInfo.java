package org.apache.derby.iapi.types;

import org.apache.derby.iapi.services.io.FormatableInstanceGetter;

public class DTSClassInfo extends FormatableInstanceGetter {
   public Object getNewInstance() {
      DataValueDescriptor var1 = DataValueFactoryImpl.getNullDVDWithUCS_BASICcollation(this.fmtId);
      return var1;
   }
}
