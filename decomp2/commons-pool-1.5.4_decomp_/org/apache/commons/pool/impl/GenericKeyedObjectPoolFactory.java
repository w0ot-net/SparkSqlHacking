package org.apache.commons.pool.impl;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.KeyedPoolableObjectFactory;

public class GenericKeyedObjectPoolFactory implements KeyedObjectPoolFactory {
   protected int _maxIdle;
   protected int _maxActive;
   protected int _maxTotal;
   protected int _minIdle;
   protected long _maxWait;
   protected byte _whenExhaustedAction;
   protected boolean _testOnBorrow;
   protected boolean _testOnReturn;
   protected boolean _testWhileIdle;
   protected long _timeBetweenEvictionRunsMillis;
   protected int _numTestsPerEvictionRun;
   protected long _minEvictableIdleTimeMillis;
   protected KeyedPoolableObjectFactory _factory;
   protected boolean _lifo;

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory) {
      this(factory, 8, (byte)1, -1L, 8, false, false, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, GenericKeyedObjectPool.Config config) throws NullPointerException {
      this(factory, config.maxActive, config.whenExhaustedAction, config.maxWait, config.maxIdle, config.maxTotal, config.minIdle, config.testOnBorrow, config.testOnReturn, config.timeBetweenEvictionRunsMillis, config.numTestsPerEvictionRun, config.minEvictableIdleTimeMillis, config.testWhileIdle, config.lifo);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive) {
      this(factory, maxActive, (byte)1, -1L, 8, -1, false, false, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait) {
      this(factory, maxActive, whenExhaustedAction, maxWait, 8, -1, false, false, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, boolean testOnBorrow, boolean testOnReturn) {
      this(factory, maxActive, whenExhaustedAction, maxWait, 8, -1, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, false, false, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, false, false, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, testOnBorrow, testOnReturn, -1L, 3, 1800000L, false);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, -1, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, 0, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
      this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, maxTotal, minIdle, testOnBorrow, testOnReturn, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle, true);
   }

   public GenericKeyedObjectPoolFactory(KeyedPoolableObjectFactory factory, int maxActive, byte whenExhaustedAction, long maxWait, int maxIdle, int maxTotal, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis, int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle, boolean lifo) {
      this._maxIdle = 8;
      this._maxActive = 8;
      this._maxTotal = -1;
      this._minIdle = 0;
      this._maxWait = -1L;
      this._whenExhaustedAction = 1;
      this._testOnBorrow = false;
      this._testOnReturn = false;
      this._testWhileIdle = false;
      this._timeBetweenEvictionRunsMillis = -1L;
      this._numTestsPerEvictionRun = 3;
      this._minEvictableIdleTimeMillis = 1800000L;
      this._factory = null;
      this._lifo = true;
      this._maxIdle = maxIdle;
      this._maxActive = maxActive;
      this._maxTotal = maxTotal;
      this._minIdle = minIdle;
      this._maxWait = maxWait;
      this._whenExhaustedAction = whenExhaustedAction;
      this._testOnBorrow = testOnBorrow;
      this._testOnReturn = testOnReturn;
      this._testWhileIdle = testWhileIdle;
      this._timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
      this._numTestsPerEvictionRun = numTestsPerEvictionRun;
      this._minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
      this._factory = factory;
      this._lifo = lifo;
   }

   public KeyedObjectPool createPool() {
      return new GenericKeyedObjectPool(this._factory, this._maxActive, this._whenExhaustedAction, this._maxWait, this._maxIdle, this._maxTotal, this._minIdle, this._testOnBorrow, this._testOnReturn, this._timeBetweenEvictionRunsMillis, this._numTestsPerEvictionRun, this._minEvictableIdleTimeMillis, this._testWhileIdle, this._lifo);
   }
}
