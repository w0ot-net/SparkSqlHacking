package org.apache.curator.framework.api.transaction;

import org.apache.zookeeper.Op;

public interface CuratorOp {
   Op get();

   TypeAndPath getTypeAndPath();
}
