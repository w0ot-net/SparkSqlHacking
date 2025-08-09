package org.apache.curator.framework.api;

public interface CreateProtectACLCreateModePathAndBytesable extends ProtectACLCreateModePathAndBytesable {
   ProtectACLCreateModePathAndBytesable creatingParentsIfNeeded();

   ProtectACLCreateModePathAndBytesable creatingParentContainersIfNeeded();

   ACLCreateModeBackgroundPathAndBytesable withProtection();
}
