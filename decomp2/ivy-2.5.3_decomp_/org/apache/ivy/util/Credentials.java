package org.apache.ivy.util;

public class Credentials {
   private String realm;
   private String host;
   private String userName;
   private String passwd;

   public Credentials(String realm, String host, String userName, String passwd) {
      this.realm = realm;
      this.host = host;
      this.userName = userName;
      this.passwd = passwd;
   }

   public String getHost() {
      return this.host;
   }

   public String getPasswd() {
      return this.passwd;
   }

   public String getRealm() {
      return this.realm;
   }

   public String getUserName() {
      return this.userName;
   }

   public static String buildKey(String realm, String host) {
      return StringUtils.isNullOrEmpty(realm) ? host : realm + "@" + host;
   }

   public String toString() {
      return this.getKey() + " " + this.getUserName() + "/" + this.getPasswdAsStars();
   }

   private String getPasswdAsStars() {
      return this.passwd == null ? null : StringUtils.repeat("*", this.passwd.length());
   }

   public boolean equals(Object o) {
      return o instanceof Credentials && this.getKey().equals(((Credentials)o).getKey());
   }

   public int hashCode() {
      return this.getKey().hashCode();
   }

   public String getKey() {
      return buildKey(this.realm, this.host);
   }
}
