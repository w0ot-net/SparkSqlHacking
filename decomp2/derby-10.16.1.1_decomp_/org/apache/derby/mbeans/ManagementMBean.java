package org.apache.derby.mbeans;

public interface ManagementMBean {
   boolean isManagementActive();

   String getSystemIdentifier();

   void startManagement();

   void stopManagement();
}
