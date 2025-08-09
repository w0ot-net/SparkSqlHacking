package org.apache.curator.framework.api;

import org.apache.curator.framework.api.transaction.CuratorMultiTransactionMain;

public interface ErrorListenerMultiTransactionMain extends CuratorMultiTransactionMain {
   CuratorMultiTransactionMain withUnhandledErrorListener(UnhandledErrorListener var1);
}
