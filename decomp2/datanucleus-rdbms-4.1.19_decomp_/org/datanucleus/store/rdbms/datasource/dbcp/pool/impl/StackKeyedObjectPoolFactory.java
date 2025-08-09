package org.datanucleus.store.rdbms.datasource.dbcp.pool.impl;

import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPoolFactory;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedPoolableObjectFactory;

public class StackKeyedObjectPoolFactory implements KeyedObjectPoolFactory {
   /** @deprecated */
   protected KeyedPoolableObjectFactory _factory;
   /** @deprecated */
   protected int _maxSleeping;
   /** @deprecated */
   protected int _initCapacity;

   public StackKeyedObjectPoolFactory() {
      this((KeyedPoolableObjectFactory)null, 8, 4);
   }

   public StackKeyedObjectPoolFactory(int maxSleeping) {
      this((KeyedPoolableObjectFactory)null, maxSleeping, 4);
   }

   public StackKeyedObjectPoolFactory(int maxSleeping, int initialCapacity) {
      this((KeyedPoolableObjectFactory)null, maxSleeping, initialCapacity);
   }

   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory) {
      this(factory, 8, 4);
   }

   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxSleeping) {
      this(factory, maxSleeping, 4);
   }

   public StackKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxSleeping, int initialCapacity) {
      this._factory = null;
      this._maxSleeping = 8;
      this._initCapacity = 4;
      this._factory = factory;
      this._maxSleeping = maxSleeping;
      this._initCapacity = initialCapacity;
   }

   public KeyedObjectPool createPool() {
      return new StackKeyedObjectPool(this._factory, this._maxSleeping, this._initCapacity);
   }

   public KeyedPoolableObjectFactory getFactory() {
      return this._factory;
   }

   public int getMaxSleeping() {
      return this._maxSleeping;
   }

   public int getInitialCapacity() {
      return this._initCapacity;
   }
}
