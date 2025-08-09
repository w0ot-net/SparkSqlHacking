package org.apache.curator.framework.api;

public interface CreateBackgroundModeACLable extends BackgroundPathAndBytesable, CreateModable, ACLCreateModeBackgroundPathAndBytesable {
   ACLCreateModePathAndBytesable creatingParentsIfNeeded();

   ACLCreateModePathAndBytesable creatingParentContainersIfNeeded();

   ACLPathAndBytesable withProtectedEphemeralSequential();
}
