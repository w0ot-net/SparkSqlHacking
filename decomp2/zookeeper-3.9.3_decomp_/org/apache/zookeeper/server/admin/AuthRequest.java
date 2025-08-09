package org.apache.zookeeper.server.admin;

public class AuthRequest {
   private final int permission;
   private final String path;

   public AuthRequest(int permission, String path) {
      this.permission = permission;
      this.path = path;
   }

   public int getPermission() {
      return this.permission;
   }

   public String getPath() {
      return this.path;
   }

   public String toString() {
      return "AuthRequest{permission=" + this.permission + ", path='" + this.path + '\'' + '}';
   }
}
