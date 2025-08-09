package org.datanucleus.store.rdbms.datasource.dbcp.pool.impl;

import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPoolFactory;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.PoolableObjectFactory;

public class StackObjectPoolFactory implements ObjectPoolFactory {
   /** @deprecated */
   protected PoolableObjectFactory _factory;
   /** @deprecated */
   protected int _maxSleeping;
   /** @deprecated */
   protected int _initCapacity;

   /** @deprecated */
   public StackObjectPoolFactory() {
      this((PoolableObjectFactory)null, 8, 4);
   }

   /** @deprecated */
   public StackObjectPoolFactory(int maxIdle) {
      this((PoolableObjectFactory)null, maxIdle, 4);
   }

   /** @deprecated */
   public StackObjectPoolFactory(int maxIdle, int initIdleCapacity) {
      this((PoolableObjectFactory)null, maxIdle, initIdleCapacity);
   }

   public StackObjectPoolFactory(PoolableObjectFactory factory) {
      this(factory, 8, 4);
   }

   public StackObjectPoolFactory(PoolableObjectFactory factory, int maxIdle) {
      this(factory, maxIdle, 4);
   }

   public StackObjectPoolFactory(PoolableObjectFactory factory, int maxIdle, int initIdleCapacity) {
      this._factory = null;
      this._maxSleeping = 8;
      this._initCapacity = 4;
      this._factory = factory;
      this._maxSleeping = maxIdle;
      this._initCapacity = initIdleCapacity;
   }

   public ObjectPool createPool() {
      return new StackObjectPool(this._factory, this._maxSleeping, this._initCapacity);
   }

   public PoolableObjectFactory getFactory() {
      return this._factory;
   }

   public int getMaxSleeping() {
      return this._maxSleeping;
   }

   public int getInitCapacity() {
      return this._initCapacity;
   }
}
