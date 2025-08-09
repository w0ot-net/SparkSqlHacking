package org.apache.derby.iapi.services.property;

import java.io.Serializable;
import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface PersistentSet {
   Serializable getProperty(String var1) throws StandardException;

   Serializable getPropertyDefault(String var1) throws StandardException;

   boolean propertyDefaultIsVisible(String var1) throws StandardException;

   void setProperty(String var1, Serializable var2, boolean var3) throws StandardException;

   void setPropertyDefault(String var1, Serializable var2) throws StandardException;

   Properties getProperties() throws StandardException;
}
