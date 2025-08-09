package org.datanucleus.enhancement;

public interface ExecutionContextReference {
   Object getOwner();

   Object findObject(Object var1, boolean var2);
}
