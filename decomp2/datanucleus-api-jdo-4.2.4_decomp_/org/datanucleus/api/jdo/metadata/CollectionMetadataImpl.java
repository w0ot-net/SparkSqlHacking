package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.CollectionMetadata;
import org.datanucleus.metadata.CollectionMetaData;

public class CollectionMetadataImpl extends AbstractMetadataImpl implements CollectionMetadata {
   public CollectionMetadataImpl(CollectionMetaData internal) {
      super(internal);
   }

   public CollectionMetaData getInternal() {
      return (CollectionMetaData)this.internalMD;
   }

   public Boolean getDependentElement() {
      return this.getInternal().isDependentElement();
   }

   public String getElementType() {
      return this.getInternal().getElementType();
   }

   public Boolean getEmbeddedElement() {
      return this.getInternal().isEmbeddedElement();
   }

   public Boolean getSerializedElement() {
      return this.getInternal().isSerializedElement();
   }

   public CollectionMetadata setDependentElement(boolean flag) {
      this.getInternal().setDependentElement(flag);
      return this;
   }

   public CollectionMetadata setElementType(String type) {
      this.getInternal().setElementType(type);
      return this;
   }

   public CollectionMetadata setEmbeddedElement(boolean flag) {
      this.getInternal().setEmbeddedElement(flag);
      return this;
   }

   public CollectionMetadata setSerializedElement(boolean flag) {
      this.getInternal().setSerializedElement(flag);
      return this;
   }
}
