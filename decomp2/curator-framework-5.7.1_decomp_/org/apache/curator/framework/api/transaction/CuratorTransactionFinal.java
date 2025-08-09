package org.apache.curator.framework.api.transaction;

import java.util.Collection;

public interface CuratorTransactionFinal extends CuratorTransaction {
   Collection commit() throws Exception;
}
