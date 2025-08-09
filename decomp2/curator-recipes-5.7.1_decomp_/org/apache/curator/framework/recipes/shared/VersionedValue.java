package org.apache.curator.framework.recipes.shared;

import java.util.function.Function;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class VersionedValue {
   private final long zxid;
   private final int version;
   private final Object value;

   VersionedValue(long zxid, int version, Object value) {
      this.zxid = zxid;
      this.version = version;
      this.value = Preconditions.checkNotNull(value, "value cannot be null");
   }

   public long getZxid() {
      return this.zxid;
   }

   public int getVersion() {
      return this.version;
   }

   public Object getValue() {
      return this.value;
   }

   VersionedValue mapValue(Function f) {
      return new VersionedValue(this.zxid, this.version, f.apply(this.value));
   }
}
