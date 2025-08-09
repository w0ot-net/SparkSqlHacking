package jakarta.ws.rs.core;

import jakarta.ws.rs.ext.RuntimeDelegate;
import java.util.Objects;

public class EntityTag {
   /** @deprecated */
   @Deprecated
   private static final RuntimeDelegate.HeaderDelegate HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(EntityTag.class);
   private String value;
   private boolean weak;

   public EntityTag(String value) {
      this(value, false);
   }

   public EntityTag(String value, boolean weak) {
      if (value == null) {
         throw new IllegalArgumentException("value==null");
      } else {
         this.value = value;
         this.weak = weak;
      }
   }

   /** @deprecated */
   @Deprecated
   public static EntityTag valueOf(String value) {
      return (EntityTag)HEADER_DELEGATE.fromString(value);
   }

   public boolean isWeak() {
      return this.weak;
   }

   public String getValue() {
      return this.value;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof EntityTag)) {
         return false;
      } else {
         EntityTag other = (EntityTag)obj;
         return Objects.equals(this.value, other.getValue()) && this.weak == other.isWeak();
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.value, this.weak});
   }

   /** @deprecated */
   @Deprecated
   public String toString() {
      return HEADER_DELEGATE.toString(this);
   }
}
