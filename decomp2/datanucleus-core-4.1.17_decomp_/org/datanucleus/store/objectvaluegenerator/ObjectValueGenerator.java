package org.datanucleus.store.objectvaluegenerator;

import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.ExtensionMetaData;

public interface ObjectValueGenerator {
   Object generate(ExecutionContext var1, Object var2, ExtensionMetaData[] var3);
}
