package org.apache.orc.impl;

import org.apache.hadoop.conf.Configuration;

/** @deprecated */
@Deprecated
public class MemoryManager extends MemoryManagerImpl {
   public MemoryManager(Configuration conf) {
      super(conf);
   }
}
