package org.apache.derby.iapi.store.access;

public interface AccessFactoryGlobals {
   String USER_TRANS_NAME = "UserTransaction";
   String SYS_TRANS_NAME = "SystemTransaction";
   int BTREE_OVERFLOW_THRESHOLD = 50;
   int HEAP_OVERFLOW_THRESHOLD = 100;
   int SORT_OVERFLOW_THRESHOLD = 100;
   String CFG_CONGLOMDIR_CACHE = "ConglomerateDirectoryCache";
   String HEAP = "heap";
   String DEFAULT_PROPERTY_NAME = "derby.defaultPropertyName";
   String PAGE_RESERVED_SPACE_PROP = "0";
   String CONGLOM_PROP = "derby.access.Conglomerate.type";
   String IMPL_TYPE = "implType";
   String SORT_EXTERNAL = "sort external";
   String SORT_INTERNAL = "sort internal";
   String SORT_UNIQUEWITHDUPLICATENULLS_EXTERNAL = "sort almost unique external";
   String NESTED_READONLY_USER_TRANS = "nestedReadOnlyUserTransaction";
   String NESTED_UPDATE_USER_TRANS = "nestedUpdateUserTransaction";
   String RAMXACT_CONTEXT_ID = "RAMTransactionContext";
   String RAMXACT_CHILD_CONTEXT_ID = "RAMChildContext";
   String RAMXACT_INTERNAL_CONTEXT_ID = "RAMInternalContext";
}
