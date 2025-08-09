package org.apache.derby.iapi.services.loader;

import java.lang.reflect.InvocationTargetException;

public interface InstanceGetter {
   Object getNewInstance() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
