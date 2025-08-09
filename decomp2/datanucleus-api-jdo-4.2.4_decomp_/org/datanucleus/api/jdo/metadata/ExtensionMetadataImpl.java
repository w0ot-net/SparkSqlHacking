package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ExtensionMetadata;
import org.datanucleus.metadata.ExtensionMetaData;

public class ExtensionMetadataImpl implements ExtensionMetadata {
   ExtensionMetaData extmd;

   public ExtensionMetadataImpl(String vendor, String key, String value) {
      this.extmd = new ExtensionMetaData(vendor, key, value);
   }

   public ExtensionMetadataImpl(ExtensionMetaData extmd) {
      this.extmd = extmd;
   }

   public String getKey() {
      return this.extmd.getKey();
   }

   public String getValue() {
      return this.extmd.getValue();
   }

   public String getVendorName() {
      return this.extmd.getVendorName();
   }
}
