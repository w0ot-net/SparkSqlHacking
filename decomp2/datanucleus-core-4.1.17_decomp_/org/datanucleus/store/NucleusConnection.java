package org.datanucleus.store;

public interface NucleusConnection {
   Object getNativeConnection();

   void close();

   boolean isAvailable();
}
