package org.datanucleus.state;

import org.datanucleus.ExecutionContext;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.FieldValues;

public interface ObjectProviderFactory {
   void close();

   void disconnectObjectProvider(ObjectProvider var1);

   ObjectProvider newForHollow(ExecutionContext var1, Class var2, Object var3);

   ObjectProvider newForHollowPreConstructed(ExecutionContext var1, Object var2, Object var3);

   ObjectProvider newForHollow(ExecutionContext var1, Class var2, Object var3, FieldValues var4);

   ObjectProvider newForPersistentClean(ExecutionContext var1, Object var2, Object var3);

   /** @deprecated */
   ObjectProvider newForHollowPopulatedAppId(ExecutionContext var1, Class var2, FieldValues var3);

   ObjectProvider newForEmbedded(ExecutionContext var1, Object var2, boolean var3, ObjectProvider var4, int var5);

   ObjectProvider newForEmbedded(ExecutionContext var1, AbstractClassMetaData var2, ObjectProvider var3, int var4);

   ObjectProvider newForPersistentNew(ExecutionContext var1, Object var2, FieldValues var3);

   ObjectProvider newForTransactionalTransient(ExecutionContext var1, Object var2);

   ObjectProvider newForDetached(ExecutionContext var1, Object var2, Object var3, Object var4);

   ObjectProvider newForPNewToBeDeleted(ExecutionContext var1, Object var2);

   ObjectProvider newForCachedPC(ExecutionContext var1, Object var2, CachedPC var3);
}
