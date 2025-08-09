package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.InterfaceMetadata;
import org.datanucleus.metadata.InterfaceMetaData;

public class InterfaceMetadataImpl extends TypeMetadataImpl implements InterfaceMetadata {
   public InterfaceMetadataImpl(InterfaceMetaData internal) {
      super(internal);
   }

   public AbstractMetadataImpl getParent() {
      if (this.parent == null) {
         this.parent = new PackageMetadataImpl(((InterfaceMetaData)this.internalMD).getPackageMetaData());
      }

      return super.getParent();
   }
}
