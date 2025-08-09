package org.apache.derby.iapi.services.property;

import java.io.Serializable;
import java.util.Dictionary;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.shared.common.error.StandardException;

public interface PropertySetCallback {
   void init(boolean var1, Dictionary var2);

   boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException;

   Serviceable apply(String var1, Serializable var2, Dictionary var3) throws StandardException;

   Serializable map(String var1, Serializable var2, Dictionary var3) throws StandardException;
}
