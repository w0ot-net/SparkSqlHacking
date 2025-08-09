package org.apache.spark.network.shuffledb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.charset.StandardCharsets;

public class StoreVersion {
   public static final byte[] KEY;
   public final int major;
   public final int minor;

   @JsonCreator
   public StoreVersion(@JsonProperty("major") int major, @JsonProperty("minor") int minor) {
      this.major = major;
      this.minor = minor;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         StoreVersion that = (StoreVersion)o;
         return this.major == that.major && this.minor == that.minor;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.major;
      result = 31 * result + this.minor;
      return result;
   }

   public String toString() {
      return "StoreVersion[" + this.major + "." + this.minor + "]";
   }

   static {
      KEY = "StoreVersion".getBytes(StandardCharsets.UTF_8);
   }
}
