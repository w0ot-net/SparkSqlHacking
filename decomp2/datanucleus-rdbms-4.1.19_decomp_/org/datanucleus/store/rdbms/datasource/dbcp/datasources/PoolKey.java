package org.datanucleus.store.rdbms.datasource.dbcp.datasources;

import java.io.Serializable;

class PoolKey implements Serializable {
   private static final long serialVersionUID = 2252771047542484533L;
   private final String datasourceName;
   private final String username;

   PoolKey(String datasourceName, String username) {
      this.datasourceName = datasourceName;
      this.username = username;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof PoolKey)) {
         return false;
      } else {
         boolean var10000;
         label38: {
            label27: {
               PoolKey pk = (PoolKey)obj;
               if (null == this.datasourceName) {
                  if (null != pk.datasourceName) {
                     break label27;
                  }
               } else if (!this.datasourceName.equals(pk.datasourceName)) {
                  break label27;
               }

               if (null == this.username) {
                  if (null == pk.username) {
                     break label38;
                  }
               } else if (this.username.equals(pk.username)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      int h = 0;
      if (this.datasourceName != null) {
         h += this.datasourceName.hashCode();
      }

      if (this.username != null) {
         h = 29 * h + this.username.hashCode();
      }

      return h;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer(50);
      sb.append("PoolKey(");
      sb.append(this.username).append(", ").append(this.datasourceName);
      sb.append(')');
      return sb.toString();
   }
}
