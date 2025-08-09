package org.apache.zookeeper;

import java.util.List;
import java.util.Objects;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.server.EphemeralType;

public class CreateOptions {
   private final CreateMode createMode;
   private final List acl;
   private final long ttl;

   public CreateMode getCreateMode() {
      return this.createMode;
   }

   public List getAcl() {
      return this.acl;
   }

   public long getTtl() {
      return this.ttl;
   }

   public static Builder newBuilder(List acl, CreateMode createMode) {
      return new Builder(createMode, acl);
   }

   private CreateOptions(CreateMode createMode, List acl, long ttl) {
      this.createMode = createMode;
      this.acl = acl;
      this.ttl = ttl;
      EphemeralType.validateTTL(createMode, ttl);
   }

   public static class Builder {
      private final CreateMode createMode;
      private final List acl;
      private long ttl;

      private Builder(CreateMode createMode, List acl) {
         this.ttl = -1L;
         this.createMode = (CreateMode)Objects.requireNonNull(createMode, "create mode is mandatory for create options");
         this.acl = (List)Objects.requireNonNull(acl, "acl is mandatory for create options");
      }

      public Builder withTtl(long ttl) {
         this.ttl = ttl;
         return this;
      }

      public CreateOptions build() {
         return new CreateOptions(this.createMode, this.acl, this.ttl);
      }
   }
}
