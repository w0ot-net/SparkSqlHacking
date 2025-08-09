package org.apache.logging.log4j.message;

import java.util.Objects;
import java.util.ResourceBundle;
import org.jspecify.annotations.Nullable;

public class LocalizedMessageFactory extends AbstractMessageFactory {
   private static final long serialVersionUID = -1996295808703146741L;
   private final transient @Nullable ResourceBundle resourceBundle;
   private final @Nullable String baseName;

   public LocalizedMessageFactory(final ResourceBundle resourceBundle) {
      this.resourceBundle = resourceBundle;
      this.baseName = null;
   }

   public LocalizedMessageFactory(final String baseName) {
      this.resourceBundle = null;
      this.baseName = baseName;
   }

   public String getBaseName() {
      return this.baseName;
   }

   public ResourceBundle getResourceBundle() {
      return this.resourceBundle;
   }

   public Message newMessage(final String key) {
      return this.resourceBundle == null ? new LocalizedMessage(this.baseName, key) : new LocalizedMessage(this.resourceBundle, key);
   }

   public Message newMessage(final String key, final Object... params) {
      return this.resourceBundle == null ? new LocalizedMessage(this.baseName, key, params) : new LocalizedMessage(this.resourceBundle, key, params);
   }

   public boolean equals(final Object object) {
      if (this == object) {
         return true;
      } else if (object != null && this.getClass() == object.getClass()) {
         LocalizedMessageFactory that = (LocalizedMessageFactory)object;
         return Objects.equals(this.resourceBundle, that.resourceBundle) && Objects.equals(this.baseName, that.baseName);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.resourceBundle, this.baseName});
   }
}
