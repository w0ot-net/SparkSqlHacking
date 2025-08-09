package org.apache.hadoop.hive.thrift;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryTokenStore implements DelegationTokenStore {
   private static final Logger LOG = LoggerFactory.getLogger(MemoryTokenStore.class);
   private final Map masterKeys = new ConcurrentHashMap();
   private final ConcurrentHashMap tokens = new ConcurrentHashMap();
   private final AtomicInteger masterKeySeq = new AtomicInteger();
   private Configuration conf;

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }

   public int addMasterKey(String s) {
      int keySeq = this.masterKeySeq.getAndIncrement();
      if (LOG.isTraceEnabled()) {
         LOG.trace("addMasterKey: s = " + s + ", keySeq = " + keySeq);
      }

      this.masterKeys.put(keySeq, s);
      return keySeq;
   }

   public void updateMasterKey(int keySeq, String s) {
      if (LOG.isTraceEnabled()) {
         LOG.trace("updateMasterKey: s = " + s + ", keySeq = " + keySeq);
      }

      this.masterKeys.put(keySeq, s);
   }

   public boolean removeMasterKey(int keySeq) {
      if (LOG.isTraceEnabled()) {
         LOG.trace("removeMasterKey: keySeq = " + keySeq);
      }

      return this.masterKeys.remove(keySeq) != null;
   }

   public String[] getMasterKeys() {
      return (String[])this.masterKeys.values().toArray(new String[0]);
   }

   public boolean addToken(DelegationTokenIdentifier tokenIdentifier, AbstractDelegationTokenSecretManager.DelegationTokenInformation token) {
      AbstractDelegationTokenSecretManager.DelegationTokenInformation tokenInfo = (AbstractDelegationTokenSecretManager.DelegationTokenInformation)this.tokens.putIfAbsent(tokenIdentifier, token);
      if (LOG.isTraceEnabled()) {
         LOG.trace("addToken: tokenIdentifier = " + tokenIdentifier + ", added = " + (tokenInfo == null));
      }

      return tokenInfo == null;
   }

   public boolean removeToken(DelegationTokenIdentifier tokenIdentifier) {
      AbstractDelegationTokenSecretManager.DelegationTokenInformation tokenInfo = (AbstractDelegationTokenSecretManager.DelegationTokenInformation)this.tokens.remove(tokenIdentifier);
      if (LOG.isTraceEnabled()) {
         LOG.trace("removeToken: tokenIdentifier = " + tokenIdentifier + ", removed = " + (tokenInfo != null));
      }

      return tokenInfo != null;
   }

   public AbstractDelegationTokenSecretManager.DelegationTokenInformation getToken(DelegationTokenIdentifier tokenIdentifier) {
      AbstractDelegationTokenSecretManager.DelegationTokenInformation result = (AbstractDelegationTokenSecretManager.DelegationTokenInformation)this.tokens.get(tokenIdentifier);
      if (LOG.isTraceEnabled()) {
         LOG.trace("getToken: tokenIdentifier = " + tokenIdentifier + ", result = " + result);
      }

      return result;
   }

   public List getAllDelegationTokenIdentifiers() {
      List<DelegationTokenIdentifier> result = new ArrayList(this.tokens.size());

      for(DelegationTokenIdentifier id : this.tokens.keySet()) {
         result.add(id);
      }

      return result;
   }

   public void close() throws IOException {
   }

   public void init(Object hmsHandler, HadoopThriftAuthBridge.Server.ServerMode smode) throws DelegationTokenStore.TokenStoreException {
   }
}
