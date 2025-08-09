package org.apache.hadoop.hive.thrift;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;
import org.apache.hadoop.security.token.delegation.HiveDelegationTokenSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBTokenStore implements DelegationTokenStore {
   private static final Logger LOG = LoggerFactory.getLogger(DBTokenStore.class);
   private Configuration conf;
   private Object handler;
   private HadoopThriftAuthBridge.Server.ServerMode smode;

   public int addMasterKey(String s) throws DelegationTokenStore.TokenStoreException {
      if (LOG.isTraceEnabled()) {
         LOG.trace("addMasterKey: s = " + s);
      }

      return (Integer)this.invokeOnTokenStore("addMasterKey", new Object[]{s}, String.class);
   }

   public void updateMasterKey(int keySeq, String s) throws DelegationTokenStore.TokenStoreException {
      if (LOG.isTraceEnabled()) {
         LOG.trace("updateMasterKey: s = " + s + ", keySeq = " + keySeq);
      }

      this.invokeOnTokenStore("updateMasterKey", new Object[]{keySeq, s}, Integer.class, String.class);
   }

   public boolean removeMasterKey(int keySeq) {
      return (Boolean)this.invokeOnTokenStore("removeMasterKey", new Object[]{keySeq}, Integer.class);
   }

   public String[] getMasterKeys() throws DelegationTokenStore.TokenStoreException {
      return (String[])this.invokeOnTokenStore("getMasterKeys", new Object[0]);
   }

   public boolean addToken(DelegationTokenIdentifier tokenIdentifier, AbstractDelegationTokenSecretManager.DelegationTokenInformation token) throws DelegationTokenStore.TokenStoreException {
      try {
         String identifier = TokenStoreDelegationTokenSecretManager.encodeWritable(tokenIdentifier);
         String tokenStr = Base64.encodeBase64URLSafeString(HiveDelegationTokenSupport.encodeDelegationTokenInformation(token));
         boolean result = (Boolean)this.invokeOnTokenStore("addToken", new Object[]{identifier, tokenStr}, String.class, String.class);
         if (LOG.isTraceEnabled()) {
            LOG.trace("addToken: tokenIdentifier = " + tokenIdentifier + ", added = " + result);
         }

         return result;
      } catch (IOException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      }
   }

   public AbstractDelegationTokenSecretManager.DelegationTokenInformation getToken(DelegationTokenIdentifier tokenIdentifier) throws DelegationTokenStore.TokenStoreException {
      try {
         String tokenStr = (String)this.invokeOnTokenStore("getToken", new Object[]{TokenStoreDelegationTokenSecretManager.encodeWritable(tokenIdentifier)}, String.class);
         AbstractDelegationTokenSecretManager.DelegationTokenInformation result = null;
         if (tokenStr != null) {
            result = HiveDelegationTokenSupport.decodeDelegationTokenInformation(Base64.decodeBase64(tokenStr));
         }

         if (LOG.isTraceEnabled()) {
            LOG.trace("getToken: tokenIdentifier = " + tokenIdentifier + ", result = " + result);
         }

         return result;
      } catch (IOException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      }
   }

   public boolean removeToken(DelegationTokenIdentifier tokenIdentifier) throws DelegationTokenStore.TokenStoreException {
      try {
         boolean result = (Boolean)this.invokeOnTokenStore("removeToken", new Object[]{TokenStoreDelegationTokenSecretManager.encodeWritable(tokenIdentifier)}, String.class);
         if (LOG.isTraceEnabled()) {
            LOG.trace("removeToken: tokenIdentifier = " + tokenIdentifier + ", removed = " + result);
         }

         return result;
      } catch (IOException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      }
   }

   public List getAllDelegationTokenIdentifiers() throws DelegationTokenStore.TokenStoreException {
      List<String> tokenIdents = (List)this.invokeOnTokenStore("getAllTokenIdentifiers", new Object[0]);
      List<DelegationTokenIdentifier> delTokenIdents = new ArrayList(tokenIdents.size());

      for(String tokenIdent : tokenIdents) {
         DelegationTokenIdentifier delToken = new DelegationTokenIdentifier();

         try {
            TokenStoreDelegationTokenSecretManager.decodeWritable(delToken, tokenIdent);
         } catch (IOException e) {
            throw new DelegationTokenStore.TokenStoreException(e);
         }

         delTokenIdents.add(delToken);
      }

      return delTokenIdents;
   }

   public void init(Object handler, HadoopThriftAuthBridge.Server.ServerMode smode) throws DelegationTokenStore.TokenStoreException {
      this.handler = handler;
      this.smode = smode;
   }

   private Object invokeOnTokenStore(String methName, Object[] params, Class... paramTypes) throws DelegationTokenStore.TokenStoreException {
      try {
         Object tokenStore;
         switch (this.smode) {
            case METASTORE:
               tokenStore = this.handler.getClass().getMethod("getMS").invoke(this.handler);
               break;
            case HIVESERVER2:
               Object hiveObject = ((Class)this.handler).getMethod("get", Configuration.class, Class.class).invoke(this.handler, this.conf, DBTokenStore.class);
               tokenStore = ((Class)this.handler).getMethod("getMSC").invoke(hiveObject);
               break;
            default:
               throw new DelegationTokenStore.TokenStoreException(new Exception("unknown server mode"));
         }

         return tokenStore.getClass().getMethod(methName, paramTypes).invoke(tokenStore, params);
      } catch (IllegalArgumentException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      } catch (SecurityException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      } catch (IllegalAccessException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      } catch (InvocationTargetException e) {
         throw new DelegationTokenStore.TokenStoreException(e.getCause());
      } catch (NoSuchMethodException e) {
         throw new DelegationTokenStore.TokenStoreException(e);
      }
   }

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }

   public void close() throws IOException {
   }
}
