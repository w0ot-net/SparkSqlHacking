package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ArrayMetadata;
import org.datanucleus.metadata.ArrayMetaData;

public class ArrayMetadataImpl extends AbstractMetadataImpl implements ArrayMetadata {
   public ArrayMetadataImpl(ArrayMetaData internal) {
      super(internal);
   }

   public ArrayMetaData getInternal() {
      return (ArrayMetaData)this.internalMD;
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

   public ArrayMetadata setDependentElement(boolean flag) {
      this.getInternal().setDependentElement(flag);
      return this;
   }

   public ArrayMetadata setElementType(String type) {
      this.getInternal().setElementType(type);
      return this;
   }

   public ArrayMetadata setEmbeddedElement(boolean flag) {
      this.getInternal().setEmbeddedElement(flag);
      return this;
   }

   public ArrayMetadata setSerializedElement(boolean flag) {
      this.getInternal().setSerializedElement(flag);
      return this;
   }
}
