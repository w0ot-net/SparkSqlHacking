package org.apache.derby.iapi.services.loader;

import java.io.ObjectStreamClass;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public interface ClassFactory {
   GeneratedClass loadGeneratedClass(String var1, ByteArray var2) throws StandardException;

   ClassInspector getClassInspector();

   Class loadApplicationClass(String var1) throws ClassNotFoundException;

   Class loadApplicationClass(ObjectStreamClass var1) throws ClassNotFoundException;

   boolean isApplicationClass(Class var1);

   void notifyModifyJar(boolean var1) throws StandardException;

   void notifyModifyClasspath(String var1) throws StandardException;

   int getClassLoaderVersion();
}
