package org.apache.hadoop.hive.thrift;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;
import org.apache.hadoop.security.token.delegation.DelegationKey;
import org.apache.hadoop.security.token.delegation.HiveDelegationTokenSupport;
import org.apache.hadoop.util.Daemon;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenStoreDelegationTokenSecretManager extends DelegationTokenSecretManager {
   private static final Logger LOGGER = LoggerFactory.getLogger(TokenStoreDelegationTokenSecretManager.class.getName());
   private final long keyUpdateInterval;
   private final long tokenRemoverScanInterval;
   private Thread tokenRemoverThread;
   private final DelegationTokenStore tokenStore;

   public TokenStoreDelegationTokenSecretManager(long delegationKeyUpdateInterval, long delegationTokenMaxLifetime, long delegationTokenRenewInterval, long delegationTokenRemoverScanInterval, DelegationTokenStore sharedStore) {
      super(delegationKeyUpdateInterval, delegationTokenMaxLifetime, delegationTokenRenewInterval, delegationTokenRemoverScanInterval);
      this.keyUpdateInterval = delegationKeyUpdateInterval;
      this.tokenRemoverScanInterval = delegationTokenRemoverScanInterval;
      this.tokenStore = sharedStore;
   }

   protected Map reloadKeys() {
      String[] allKeys = this.tokenStore.getMasterKeys();
      Map<Integer, DelegationKey> keys = new HashMap(allKeys.length);

      for(String keyStr : allKeys) {
         DelegationKey key = new DelegationKey();

         try {
            decodeWritable(key, keyStr);
            keys.put(key.getKeyId(), key);
         } catch (IOException ex) {
            LOGGER.error("Failed to load master key.", ex);
         }
      }

      synchronized(this) {
         super.allKeys.clear();
         super.allKeys.putAll(keys);
         return keys;
      }
   }

   public byte[] retrievePassword(DelegationTokenIdentifier identifier) throws SecretManager.InvalidToken {
      AbstractDelegationTokenSecretManager.DelegationTokenInformation info = this.tokenStore.getToken(identifier);
      if (info == null) {
         throw new SecretManager.InvalidToken("token expired or does not exist: " + identifier);
      } else {
         synchronized(this) {
            byte[] var4;
            try {
               super.currentTokens.put(identifier, info);
               var4 = super.retrievePassword(identifier);
            } finally {
               super.currentTokens.remove(identifier);
            }

            return var4;
         }
      }
   }

   public DelegationTokenIdentifier cancelToken(Token token, String canceller) throws IOException {
      DelegationTokenIdentifier id = this.getTokenIdentifier(token);
      LOGGER.info("Token cancelation requested for identifier: " + id);
      this.tokenStore.removeToken(id);
      return id;
   }

   protected byte[] createPassword(DelegationTokenIdentifier id) {
      byte[] password;
      AbstractDelegationTokenSecretManager.DelegationTokenInformation info;
      synchronized(this) {
         password = super.createPassword(id);
         info = (AbstractDelegationTokenSecretManager.DelegationTokenInformation)super.currentTokens.remove(id);
         if (info == null) {
            throw new IllegalStateException("Failed to retrieve token after creation");
         }
      }

      this.tokenStore.addToken(id, info);
      return password;
   }

   public long renewToken(Token token, String renewer) throws SecretManager.InvalidToken, IOException {
      DelegationTokenIdentifier id = this.getTokenIdentifier(token);
      AbstractDelegationTokenSecretManager.DelegationTokenInformation tokenInfo = this.tokenStore.getToken(id);
      if (tokenInfo == null) {
         throw new SecretManager.InvalidToken("token does not exist: " + id);
      } else {
         if (!super.allKeys.containsKey(id.getMasterKeyId())) {
            LOGGER.info("Unknown master key (id={}), (re)loading keys from token store.", id.getMasterKeyId());
            this.reloadKeys();
         }

         synchronized(this) {
            super.currentTokens.put(id, tokenInfo);

            long var8;
            try {
               long res = super.renewToken(token, renewer);
               this.tokenStore.removeToken(id);
               this.tokenStore.addToken(id, (AbstractDelegationTokenSecretManager.DelegationTokenInformation)super.currentTokens.get(id));
               var8 = res;
            } finally {
               super.currentTokens.remove(id);
            }

            return var8;
         }
      }
   }

   public static String encodeWritable(Writable key) throws IOException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bos);
      key.write(dos);
      dos.flush();
      return Base64.encodeBase64URLSafeString(bos.toByteArray());
   }

   public static void decodeWritable(Writable w, String idStr) throws IOException {
      DataInputStream in = new DataInputStream(new ByteArrayInputStream(Base64.decodeBase64(idStr)));
      w.readFields(in);
   }

   protected void logUpdateMasterKey(DelegationKey key) throws IOException {
      int keySeq = this.tokenStore.addMasterKey(encodeWritable(key));
      DelegationKey keyWithSeq = new DelegationKey(keySeq, key.getExpiryDate(), key.getKey());
      String keyStr = encodeWritable(keyWithSeq);
      this.tokenStore.updateMasterKey(keySeq, keyStr);
      decodeWritable(key, keyStr);
      LOGGER.info("New master key with key id={}", key.getKeyId());
      super.logUpdateMasterKey(key);
   }

   public synchronized void startThreads() throws IOException {
      try {
         Method m = AbstractDelegationTokenSecretManager.class.getDeclaredMethod("updateCurrentKey");
         m.setAccessible(true);
         m.invoke(this);
      } catch (Exception e) {
         throw new IOException("Failed to initialize master key", e);
      }

      this.running = true;
      this.tokenRemoverThread = new Daemon(new ExpiredTokenRemover());
      this.tokenRemoverThread.start();
   }

   public synchronized void stopThreads() {
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Stopping expired delegation token remover thread");
      }

      this.running = false;
      if (this.tokenRemoverThread != null) {
         this.tokenRemoverThread.interrupt();
      }

   }

   protected void removeExpiredTokens() {
      long now = System.currentTimeMillis();

      for(DelegationTokenIdentifier id : this.tokenStore.getAllDelegationTokenIdentifiers()) {
         if (now > id.getMaxDate()) {
            this.tokenStore.removeToken(id);
         } else {
            AbstractDelegationTokenSecretManager.DelegationTokenInformation tokenInfo = this.tokenStore.getToken(id);
            if (tokenInfo != null && now > tokenInfo.getRenewDate()) {
               this.tokenStore.removeToken(id);
            }
         }
      }

   }

   protected void rollMasterKeyExt() throws IOException {
      Map<Integer, DelegationKey> keys = this.reloadKeys();
      int currentKeyId = super.currentId;
      HiveDelegationTokenSupport.rollMasterKey(this);

      for(DelegationKey key : Arrays.asList(this.getAllKeys())) {
         keys.remove(key.getKeyId());
         if (key.getKeyId() == currentKeyId) {
            this.tokenStore.updateMasterKey(currentKeyId, encodeWritable(key));
         }
      }

      for(DelegationKey expiredKey : keys.values()) {
         LOGGER.info("Removing expired key id={}", expiredKey.getKeyId());

         try {
            this.tokenStore.removeMasterKey(expiredKey.getKeyId());
         } catch (Exception e) {
            LOGGER.error("Error removing expired key id={}", expiredKey.getKeyId(), e);
         }
      }

   }

   protected class ExpiredTokenRemover extends Thread {
      private long lastMasterKeyUpdate;
      private long lastTokenCacheCleanup;

      public void run() {
         TokenStoreDelegationTokenSecretManager.LOGGER.info("Starting expired delegation token remover thread, tokenRemoverScanInterval=" + TokenStoreDelegationTokenSecretManager.this.tokenRemoverScanInterval / 60000L + " min(s)");

         while(TokenStoreDelegationTokenSecretManager.this.running) {
            try {
               long now = System.currentTimeMillis();
               if (this.lastMasterKeyUpdate + TokenStoreDelegationTokenSecretManager.this.keyUpdateInterval < now) {
                  try {
                     TokenStoreDelegationTokenSecretManager.this.rollMasterKeyExt();
                     this.lastMasterKeyUpdate = now;
                  } catch (IOException e) {
                     TokenStoreDelegationTokenSecretManager.LOGGER.error("Master key updating failed. " + StringUtils.stringifyException(e));
                  }
               }

               if (this.lastTokenCacheCleanup + TokenStoreDelegationTokenSecretManager.this.tokenRemoverScanInterval < now) {
                  TokenStoreDelegationTokenSecretManager.this.removeExpiredTokens();
                  this.lastTokenCacheCleanup = now;
               }

               try {
                  Thread.sleep(5000L);
               } catch (InterruptedException ie) {
                  TokenStoreDelegationTokenSecretManager.LOGGER.error("InterruptedException received for ExpiredTokenRemover thread " + ie);
               }
            } catch (Throwable t) {
               TokenStoreDelegationTokenSecretManager.LOGGER.error("ExpiredTokenRemover thread received unexpected exception. " + t, t);

               try {
                  Thread.sleep(5000L);
               } catch (InterruptedException ie) {
                  TokenStoreDelegationTokenSecretManager.LOGGER.error("InterruptedException received for ExpiredTokenRemover thread during wait in exception sleep " + ie);
               }
            }
         }

      }
   }
}
