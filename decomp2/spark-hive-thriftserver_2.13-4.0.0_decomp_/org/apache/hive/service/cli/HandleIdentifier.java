package org.apache.hive.service.cli;

import java.nio.ByteBuffer;
import java.util.UUID;
import org.apache.hive.service.rpc.thrift.THandleIdentifier;

public class HandleIdentifier {
   private final UUID publicId;
   private final UUID secretId;

   public HandleIdentifier() {
      this.publicId = UUID.randomUUID();
      this.secretId = UUID.randomUUID();
   }

   public HandleIdentifier(UUID publicId, UUID secretId) {
      this.publicId = publicId;
      this.secretId = secretId;
   }

   public HandleIdentifier(THandleIdentifier tHandleId) {
      ByteBuffer bb = ByteBuffer.wrap(tHandleId.getGuid());
      this.publicId = new UUID(bb.getLong(), bb.getLong());
      bb = ByteBuffer.wrap(tHandleId.getSecret());
      this.secretId = new UUID(bb.getLong(), bb.getLong());
   }

   public UUID getPublicId() {
      return this.publicId;
   }

   public UUID getSecretId() {
      return this.secretId;
   }

   public THandleIdentifier toTHandleIdentifier() {
      byte[] guid = new byte[16];
      byte[] secret = new byte[16];
      ByteBuffer guidBB = ByteBuffer.wrap(guid);
      ByteBuffer secretBB = ByteBuffer.wrap(secret);
      guidBB.putLong(this.publicId.getMostSignificantBits());
      guidBB.putLong(this.publicId.getLeastSignificantBits());
      secretBB.putLong(this.secretId.getMostSignificantBits());
      secretBB.putLong(this.secretId.getLeastSignificantBits());
      return new THandleIdentifier(ByteBuffer.wrap(guid), ByteBuffer.wrap(secret));
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.publicId == null ? 0 : this.publicId.hashCode());
      result = 31 * result + (this.secretId == null ? 0 : this.secretId.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof HandleIdentifier)) {
         return false;
      } else {
         HandleIdentifier other = (HandleIdentifier)obj;
         if (this.publicId == null) {
            if (other.publicId != null) {
               return false;
            }
         } else if (!this.publicId.equals(other.publicId)) {
            return false;
         }

         if (this.secretId == null) {
            if (other.secretId != null) {
               return false;
            }
         } else if (!this.secretId.equals(other.secretId)) {
            return false;
         }

         return true;
      }
   }

   public String toString() {
      return this.publicId.toString();
   }
}
