package org.apache.ivy.plugins.resolver.util;

import org.apache.ivy.plugins.latest.LatestStrategy;

public interface HasLatestStrategy {
   LatestStrategy getLatestStrategy();

   void setLatestStrategy(LatestStrategy var1);

   String getLatest();
}
