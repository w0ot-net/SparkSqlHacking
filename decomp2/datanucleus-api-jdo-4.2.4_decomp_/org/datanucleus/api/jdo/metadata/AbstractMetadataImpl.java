package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ExtensionMetadata;
import javax.jdo.metadata.Metadata;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.MetaData;

public class AbstractMetadataImpl implements Metadata {
   AbstractMetadataImpl parent;
   MetaData internalMD;

   public AbstractMetadataImpl(MetaData internal) {
      this.internalMD = internal;
   }

   public String toString() {
      return this.internalMD.toString("", "    ");
   }

   public ExtensionMetadata[] getExtensions() {
      ExtensionMetaData[] exts = this.internalMD.getExtensions();
      if (exts == null) {
         return null;
      } else {
         ExtensionMetadata[] extensions = new ExtensionMetadata[exts.length];

         for(int i = 0; i < extensions.length; ++i) {
            extensions[i] = new ExtensionMetadataImpl(exts[i]);
         }

         return extensions;
      }
   }

   public int getNumberOfExtensions() {
      return this.internalMD.getNoOfExtensions();
   }

   public ExtensionMetadata newExtensionMetadata(String vendor, String key, String value) {
      return new ExtensionMetadataImpl(this.internalMD.newExtensionMetaData(vendor, key, value));
   }

   public AbstractMetadataImpl getParent() {
      return this.parent;
   }
}
