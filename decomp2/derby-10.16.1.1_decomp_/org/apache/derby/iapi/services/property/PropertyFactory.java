package org.apache.derby.iapi.services.property;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Properties;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public interface PropertyFactory {
   void addPropertySetNotification(PropertySetCallback var1);

   void verifyPropertySet(Properties var1, Properties var2) throws StandardException;

   void validateSingleProperty(String var1, Serializable var2, Dictionary var3) throws StandardException;

   Serializable doValidateApplyAndMap(TransactionController var1, String var2, Serializable var3, Dictionary var4, boolean var5) throws StandardException;

   Serializable doMap(String var1, Serializable var2, Dictionary var3) throws StandardException;
}
