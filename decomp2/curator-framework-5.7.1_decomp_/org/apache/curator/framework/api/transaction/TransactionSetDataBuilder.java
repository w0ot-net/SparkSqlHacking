package org.apache.curator.framework.api.transaction;

import org.apache.curator.framework.api.Compressible;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.curator.framework.api.VersionPathAndBytesable;
import org.apache.curator.framework.api.Versionable;

public interface TransactionSetDataBuilder extends PathAndBytesable, Versionable, VersionPathAndBytesable, Compressible {
}
