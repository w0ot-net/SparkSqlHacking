package org.apache.zookeeper.server.quorum;

import java.util.List;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestRecord;
import org.apache.zookeeper.server.ServerCnxn;

public class LearnerSyncRequest extends Request {
   LearnerHandler fh;

   public LearnerSyncRequest(LearnerHandler fh, long sessionId, int xid, int type, RequestRecord request, List authInfo) {
      super((ServerCnxn)null, sessionId, xid, type, request, authInfo);
      this.fh = fh;
   }
}
