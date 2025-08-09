package org.apache.curator.framework.api;

public interface CreateBackgroundModeStatACLable extends BackgroundPathAndBytesable, CreateModable, ACLCreateModeBackgroundPathAndBytesable, Statable {
   ACLCreateModePathAndBytesable creatingParentsIfNeeded();

   ACLCreateModePathAndBytesable creatingParentContainersIfNeeded();

   ACLPathAndBytesable withProtectedEphemeralSequential();
}
