package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.cache.Cacheable;

public class BaseDataFileFactoryJ4 extends BaseDataFileFactory {
   protected Cacheable newRAFContainer(BaseDataFileFactory var1) {
      return new RAFContainer4(var1);
   }
}
