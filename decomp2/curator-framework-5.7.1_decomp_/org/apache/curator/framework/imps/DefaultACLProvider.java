package org.apache.curator.framework.imps;

import java.util.List;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.zookeeper.ZooDefs.Ids;

public class DefaultACLProvider implements ACLProvider {
   public List getDefaultAcl() {
      return Ids.OPEN_ACL_UNSAFE;
   }

   public List getAclForPath(String path) {
      return Ids.OPEN_ACL_UNSAFE;
   }
}
