package org.apache.ivy.core.settings;

public interface TimeoutConstraint {
   int getConnectionTimeout();

   int getReadTimeout();
}
