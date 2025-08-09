package org.datanucleus.transaction.jta;

import javax.transaction.TransactionManager;
import org.datanucleus.ClassLoaderResolver;

public interface TransactionManagerLocator {
   TransactionManager getTransactionManager(ClassLoaderResolver var1);
}
