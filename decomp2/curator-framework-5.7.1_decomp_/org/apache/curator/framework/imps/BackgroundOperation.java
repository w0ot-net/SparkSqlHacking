package org.apache.curator.framework.imps;

import org.apache.curator.framework.api.CuratorEventType;

interface BackgroundOperation {
   void performBackgroundOperation(OperationAndData var1) throws Exception;

   CuratorEventType getBackgroundEventType();
}
