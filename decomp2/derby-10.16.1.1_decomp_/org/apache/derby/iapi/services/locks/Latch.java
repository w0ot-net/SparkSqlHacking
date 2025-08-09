package org.apache.derby.iapi.services.locks;

public interface Latch {
   CompatibilitySpace getCompatabilitySpace();

   Lockable getLockable();

   Object getQualifier();

   int getCount();
}
