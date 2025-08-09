package org.datanucleus.metadata;

import java.io.Serializable;
import org.datanucleus.util.StringUtils;

public class ExtensionMetaData implements Serializable {
   private static final long serialVersionUID = -7234998789929538447L;
   protected String vendorName;
   protected String key;
   protected String value;

   public ExtensionMetaData(String vendorName, String key, String value) {
      this.vendorName = StringUtils.isWhitespace(vendorName) ? null : vendorName;
      this.key = StringUtils.isWhitespace(key) ? null : key;
      this.value = StringUtils.isWhitespace(value) ? null : value;
   }

   public String getKey() {
      return this.key;
   }

   public String getValue() {
      return this.value;
   }

   public String getVendorName() {
      return this.vendorName;
   }

   public String toString() {
      return "<extension vendor-name=\"" + this.vendorName + "\" key=\"" + this.key + "\" value=\"" + this.value + "\"/>";
   }
}
