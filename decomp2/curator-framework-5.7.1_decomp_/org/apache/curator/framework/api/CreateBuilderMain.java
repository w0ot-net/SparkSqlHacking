package org.apache.curator.framework.api;

public interface CreateBuilderMain extends BackgroundPathAndBytesable, CreateModable, ACLCreateModeBackgroundPathAndBytesable, Compressible, Statable {
   ProtectACLCreateModeStatPathAndBytesable creatingParentsIfNeeded();

   ProtectACLCreateModeStatPathAndBytesable creatingParentContainersIfNeeded();

   /** @deprecated */
   @Deprecated
   ACLPathAndBytesable withProtectedEphemeralSequential();

   ACLCreateModeStatBackgroundPathAndBytesable withProtection();
}
