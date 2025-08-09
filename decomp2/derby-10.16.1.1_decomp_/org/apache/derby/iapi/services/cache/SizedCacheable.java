package org.apache.derby.iapi.services.cache;

public interface SizedCacheable extends Cacheable {
   int getSize();
}
