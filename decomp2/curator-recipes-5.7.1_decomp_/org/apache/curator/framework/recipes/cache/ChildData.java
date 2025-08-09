package org.apache.curator.framework.recipes.cache;

import java.util.Arrays;
import org.apache.curator.utils.PathUtils;
import org.apache.zookeeper.data.Stat;

public class ChildData implements Comparable {
   private final String path;
   private final Stat stat;
   private final byte[] data;

   public ChildData(String path, Stat stat, byte[] data) {
      this.path = PathUtils.validatePath(path);
      this.stat = stat;
      this.data = data;
   }

   public int compareTo(ChildData rhs) {
      if (this == rhs) {
         return 0;
      } else {
         return rhs != null && this.getClass() == rhs.getClass() ? this.path.compareTo(rhs.path) : -1;
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ChildData childData = (ChildData)o;
         if (!Arrays.equals(this.data, childData.data)) {
            return false;
         } else {
            if (this.path != null) {
               if (!this.path.equals(childData.path)) {
                  return false;
               }
            } else if (childData.path != null) {
               return false;
            }

            if (this.stat != null) {
               if (!this.stat.equals(childData.stat)) {
                  return false;
               }
            } else if (childData.stat != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.path != null ? this.path.hashCode() : 0;
      result = 31 * result + (this.stat != null ? this.stat.hashCode() : 0);
      result = 31 * result + Arrays.hashCode(this.data);
      return result;
   }

   public String getPath() {
      return this.path;
   }

   public Stat getStat() {
      return this.stat;
   }

   public byte[] getData() {
      return this.data;
   }

   public String toString() {
      return "ChildData{path='" + this.path + '\'' + ", stat=" + this.stat + ", data=" + Arrays.toString(this.data) + '}';
   }
}
