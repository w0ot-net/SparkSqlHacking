package org.apache.hadoop.hive.thrift;

import java.io.Closeable;
import java.util.List;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;

public interface DelegationTokenStore extends Configurable, Closeable {
   int addMasterKey(String var1) throws TokenStoreException;

   void updateMasterKey(int var1, String var2) throws TokenStoreException;

   boolean removeMasterKey(int var1);

   String[] getMasterKeys() throws TokenStoreException;

   boolean addToken(DelegationTokenIdentifier var1, AbstractDelegationTokenSecretManager.DelegationTokenInformation var2) throws TokenStoreException;

   AbstractDelegationTokenSecretManager.DelegationTokenInformation getToken(DelegationTokenIdentifier var1) throws TokenStoreException;

   boolean removeToken(DelegationTokenIdentifier var1) throws TokenStoreException;

   List getAllDelegationTokenIdentifiers() throws TokenStoreException;

   void init(Object var1, HadoopThriftAuthBridge.Server.ServerMode var2);

   public static class TokenStoreException extends RuntimeException {
      private static final long serialVersionUID = -8693819817623074083L;

      public TokenStoreException(Throwable cause) {
         super(cause);
      }

      public TokenStoreException(String message, Throwable cause) {
         super(message, cause);
      }
   }
}
