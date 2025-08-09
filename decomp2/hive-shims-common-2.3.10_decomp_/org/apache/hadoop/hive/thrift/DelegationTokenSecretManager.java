package org.apache.hadoop.hive.thrift;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSecretManager;

public class DelegationTokenSecretManager extends AbstractDelegationTokenSecretManager {
   public DelegationTokenSecretManager(long delegationKeyUpdateInterval, long delegationTokenMaxLifetime, long delegationTokenRenewInterval, long delegationTokenRemoverScanInterval) {
      super(delegationKeyUpdateInterval, delegationTokenMaxLifetime, delegationTokenRenewInterval, delegationTokenRemoverScanInterval);
   }

   public DelegationTokenIdentifier createIdentifier() {
      return new DelegationTokenIdentifier();
   }

   public synchronized String verifyDelegationToken(String tokenStrForm) throws IOException {
      Token<DelegationTokenIdentifier> t = new Token();
      t.decodeFromUrlString(tokenStrForm);
      DelegationTokenIdentifier id = this.getTokenIdentifier(t);
      this.verifyToken(id, t.getPassword());
      return id.getUser().getShortUserName();
   }

   protected DelegationTokenIdentifier getTokenIdentifier(Token token) throws IOException {
      ByteArrayInputStream buf = new ByteArrayInputStream(token.getIdentifier());
      DataInputStream in = new DataInputStream(buf);
      DelegationTokenIdentifier id = this.createIdentifier();
      id.readFields(in);
      return id;
   }

   public synchronized void cancelDelegationToken(String tokenStrForm) throws IOException {
      Token<DelegationTokenIdentifier> t = new Token();
      t.decodeFromUrlString(tokenStrForm);
      String user = UserGroupInformation.getCurrentUser().getUserName();
      this.cancelToken(t, user);
   }

   public synchronized long renewDelegationToken(String tokenStrForm) throws IOException {
      Token<DelegationTokenIdentifier> t = new Token();
      t.decodeFromUrlString(tokenStrForm);
      String user = UserGroupInformation.getCurrentUser().getUserName();
      return this.renewToken(t, user);
   }

   public synchronized String getDelegationToken(String renewer) throws IOException {
      UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
      Text owner = new Text(ugi.getUserName());
      Text realUser = null;
      if (ugi.getRealUser() != null) {
         realUser = new Text(ugi.getRealUser().getUserName());
      }

      DelegationTokenIdentifier ident = new DelegationTokenIdentifier(owner, new Text(renewer), realUser);
      Token<DelegationTokenIdentifier> t = new Token(ident, this);
      return t.encodeToUrlString();
   }

   public String getUserFromToken(String tokenStr) throws IOException {
      Token<DelegationTokenIdentifier> delegationToken = new Token();
      delegationToken.decodeFromUrlString(tokenStr);
      ByteArrayInputStream buf = new ByteArrayInputStream(delegationToken.getIdentifier());
      DataInputStream in = new DataInputStream(buf);
      DelegationTokenIdentifier id = this.createIdentifier();
      id.readFields(in);
      return id.getUser().getShortUserName();
   }
}
