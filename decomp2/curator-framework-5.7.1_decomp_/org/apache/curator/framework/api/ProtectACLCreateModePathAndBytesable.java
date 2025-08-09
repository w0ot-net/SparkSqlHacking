package org.apache.curator.framework.api;

public interface ProtectACLCreateModePathAndBytesable extends ACLBackgroundPathAndBytesable, CreateModable {
   ACLCreateModeBackgroundPathAndBytesable withProtection();
}
