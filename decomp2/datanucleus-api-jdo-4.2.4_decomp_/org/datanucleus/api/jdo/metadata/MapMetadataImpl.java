package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.MapMetadata;
import org.datanucleus.metadata.MapMetaData;

public class MapMetadataImpl extends AbstractMetadataImpl implements MapMetadata {
   public MapMetadataImpl(MapMetaData internal) {
      super(internal);
   }

   public MapMetaData getInternal() {
      return (MapMetaData)this.internalMD;
   }

   public Boolean getDependentKey() {
      return this.getInternal().isDependentKey();
   }

   public Boolean getDependentValue() {
      return this.getInternal().isDependentValue();
   }

   public Boolean getEmbeddedKey() {
      return this.getInternal().isEmbeddedKey();
   }

   public Boolean getEmbeddedValue() {
      return this.getInternal().isEmbeddedValue();
   }

   public String getKeyType() {
      return this.getInternal().getKeyType();
   }

   public Boolean getSerializedKey() {
      return this.getInternal().isSerializedKey();
   }

   public Boolean getSerializedValue() {
      return this.getInternal().isSerializedValue();
   }

   public String getValueType() {
      return this.getInternal().getValueType();
   }

   public MapMetadata setDependentKey(boolean flag) {
      this.getInternal().setDependentKey(flag);
      return this;
   }

   public MapMetadata setDependentValue(boolean flag) {
      this.getInternal().setDependentValue(flag);
      return this;
   }

   public MapMetadata setEmbeddedKey(boolean flag) {
      this.getInternal().setEmbeddedKey(flag);
      return this;
   }

   public MapMetadata setEmbeddedValue(boolean flag) {
      this.getInternal().setEmbeddedValue(flag);
      return this;
   }

   public MapMetadata setKeyType(String type) {
      this.getInternal().setKeyType(type);
      return this;
   }

   public MapMetadata setSerializedKey(boolean flag) {
      this.getInternal().setSerializedKey(flag);
      return this;
   }

   public MapMetadata setSerializedValue(boolean flag) {
      this.getInternal().setSerializedValue(flag);
      return this;
   }

   public MapMetadata setValueType(String type) {
      this.getInternal().setValueType(type);
      return this;
   }
}
