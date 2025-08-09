package io.vertx.core.dns.impl.decoder;

public class StartOfAuthorityRecord {
   private final String primaryNameServer;
   private final String responsiblePerson;
   private final long serial;
   private final int refreshTime;
   private final int retryTime;
   private final int expireTime;
   private final long minimumTtl;

   public StartOfAuthorityRecord(String primaryNameServer, String responsiblePerson, long serial, int refreshTime, int retryTime, int expireTime, long minimumTtl) {
      this.primaryNameServer = primaryNameServer;
      this.responsiblePerson = responsiblePerson;
      this.serial = serial;
      this.refreshTime = refreshTime;
      this.retryTime = retryTime;
      this.expireTime = expireTime;
      this.minimumTtl = minimumTtl;
   }

   public String primaryNameServer() {
      return this.primaryNameServer;
   }

   public String responsiblePerson() {
      return this.responsiblePerson;
   }

   public long serial() {
      return this.serial;
   }

   public int refreshTime() {
      return this.refreshTime;
   }

   public int retryTime() {
      return this.retryTime;
   }

   public int expireTime() {
      return this.expireTime;
   }

   public long minimumTtl() {
      return this.minimumTtl;
   }
}
