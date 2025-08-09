package org.apache.hadoop.hive.metastore.messaging;

import org.apache.hadoop.hive.metastore.api.Index;

public abstract class AlterIndexMessage extends EventMessage {
   public abstract Index getIndexObjBefore() throws Exception;

   public abstract Index getIndexObjAfter() throws Exception;

   protected AlterIndexMessage() {
      super(EventMessage.EventType.ALTER_INDEX);
   }
}
