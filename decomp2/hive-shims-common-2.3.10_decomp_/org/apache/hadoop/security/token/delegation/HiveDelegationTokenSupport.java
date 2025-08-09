package org.apache.hadoop.security.token.delegation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.hadoop.io.WritableUtils;

public final class HiveDelegationTokenSupport {
   private HiveDelegationTokenSupport() {
   }

   public static byte[] encodeDelegationTokenInformation(AbstractDelegationTokenSecretManager.DelegationTokenInformation token) {
      try {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream out = new DataOutputStream(bos);
         WritableUtils.writeVInt(out, token.password.length);
         out.write(token.password);
         out.writeLong(token.renewDate);
         out.flush();
         return bos.toByteArray();
      } catch (IOException ex) {
         throw new RuntimeException("Failed to encode token.", ex);
      }
   }

   public static AbstractDelegationTokenSecretManager.DelegationTokenInformation decodeDelegationTokenInformation(byte[] tokenBytes) throws IOException {
      DataInputStream in = new DataInputStream(new ByteArrayInputStream(tokenBytes));
      AbstractDelegationTokenSecretManager.DelegationTokenInformation token = new AbstractDelegationTokenSecretManager.DelegationTokenInformation(0L, (byte[])null);
      int len = WritableUtils.readVInt(in);
      token.password = new byte[len];
      in.readFully(token.password);
      token.renewDate = in.readLong();
      return token;
   }

   public static void rollMasterKey(AbstractDelegationTokenSecretManager mgr) throws IOException {
      mgr.rollMasterKey();
   }
}
