package org.apache.curator.framework.imps;

import java.util.List;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.utils.InternalACLProvider;
import org.apache.zookeeper.data.ACL;

class ACLing implements InternalACLProvider {
   private final List aclList;
   private final ACLProvider aclProvider;
   private final boolean applyToParents;

   ACLing(ACLProvider aclProvider) {
      this(aclProvider, (List)null);
   }

   ACLing(ACLProvider aclProvider, List aclList) {
      this(aclProvider, aclList, false);
   }

   ACLing(ACLProvider aclProvider, List aclList, boolean applyToParents) {
      this.aclProvider = aclProvider;
      this.aclList = aclList != null ? ImmutableList.copyOf(aclList) : null;
      this.applyToParents = applyToParents;
   }

   InternalACLProvider getACLProviderForParents() {
      return (InternalACLProvider)(this.applyToParents ? this : this.aclProvider);
   }

   List getAclList(String path) {
      if (this.aclList != null) {
         return this.aclList;
      } else {
         if (path != null) {
            List<ACL> localAclList = this.aclProvider.getAclForPath(path);
            if (localAclList != null) {
               return localAclList;
            }
         }

         return this.aclProvider.getDefaultAcl();
      }
   }

   public List getDefaultAcl() {
      return this.aclProvider.getDefaultAcl();
   }

   public List getAclForPath(String path) {
      return this.getAclList(path);
   }
}
