package org.apache.curator.framework.api.transaction;

import java.util.List;

public interface CuratorMultiTransactionMain {
   List forOperations(CuratorOp... var1) throws Exception;

   List forOperations(List var1) throws Exception;
}
