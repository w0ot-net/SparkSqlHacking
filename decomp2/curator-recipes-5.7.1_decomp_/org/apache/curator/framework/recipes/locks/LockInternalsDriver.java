package org.apache.curator.framework.recipes.locks;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;

public interface LockInternalsDriver extends LockInternalsSorter {
   PredicateResults getsTheLock(CuratorFramework var1, List var2, String var3, int var4) throws Exception;

   String createsTheLock(CuratorFramework var1, String var2, byte[] var3) throws Exception;
}
