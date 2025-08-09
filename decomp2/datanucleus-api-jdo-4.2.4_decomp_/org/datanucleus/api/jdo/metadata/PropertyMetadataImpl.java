package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.PropertyMetadata;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class PropertyMetadataImpl extends MemberMetadataImpl implements PropertyMetadata {
   public PropertyMetadataImpl(PropertyMetaData internal) {
      super(internal);
   }

   public PropertyMetadataImpl(FetchGroupMemberMetaData internal) {
      super(internal);
   }

   public PropertyMetaData getInternal() {
      return (PropertyMetaData)this.internalMD;
   }

   public String getFieldName() {
      return this.getInternal().getFieldName();
   }

   public PropertyMetadata setFieldName(String name) {
      this.getInternal().setFieldName(name);
      return this;
   }
}
