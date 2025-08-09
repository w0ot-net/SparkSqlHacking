package io.netty.handler.ssl;

import io.netty.internal.tcnative.SessionTicketKey;

public final class OpenSslSessionTicketKey {
   public static final int NAME_SIZE = 16;
   public static final int HMAC_KEY_SIZE = 16;
   public static final int AES_KEY_SIZE = 16;
   public static final int TICKET_KEY_SIZE = 48;
   final SessionTicketKey key;

   public OpenSslSessionTicketKey(byte[] name, byte[] hmacKey, byte[] aesKey) {
      this.key = new SessionTicketKey((byte[])(([B)name).clone(), (byte[])(([B)hmacKey).clone(), (byte[])(([B)aesKey).clone());
   }

   public byte[] name() {
      return (byte[])this.key.getName().clone();
   }

   public byte[] hmacKey() {
      return (byte[])this.key.getHmacKey().clone();
   }

   public byte[] aesKey() {
      return (byte[])this.key.getAesKey().clone();
   }
}
