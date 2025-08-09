package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.THandleIdentifier;

public abstract class Handle {
   private final HandleIdentifier handleId;

   public Handle() {
      this.handleId = new HandleIdentifier();
   }

   public Handle(HandleIdentifier handleId) {
      this.handleId = handleId;
   }

   public Handle(THandleIdentifier tHandleIdentifier) {
      this.handleId = new HandleIdentifier(tHandleIdentifier);
   }

   public HandleIdentifier getHandleIdentifier() {
      return this.handleId;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.handleId == null ? 0 : this.handleId.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof Handle)) {
         return false;
      } else {
         Handle other = (Handle)obj;
         if (this.handleId == null) {
            if (other.handleId != null) {
               return false;
            }
         } else if (!this.handleId.equals(other.handleId)) {
            return false;
         }

         return true;
      }
   }

   public abstract String toString();
}
