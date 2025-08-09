package org.apache.curator.framework.api.transaction;

import org.apache.curator.framework.api.ACLCreateModePathAndBytesable;
import org.apache.curator.framework.api.ACLPathAndBytesable;
import org.apache.curator.framework.api.Compressible;
import org.apache.curator.framework.api.CreateModable;
import org.apache.curator.framework.api.PathAndBytesable;

public interface TransactionCreateBuilder2 extends PathAndBytesable, CreateModable, ACLPathAndBytesable, ACLCreateModePathAndBytesable, Compressible {
}
