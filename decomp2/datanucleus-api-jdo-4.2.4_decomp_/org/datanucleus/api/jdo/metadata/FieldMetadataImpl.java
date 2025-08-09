package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.FieldMetadata;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.FieldMetaData;

public class FieldMetadataImpl extends MemberMetadataImpl implements FieldMetadata {
   public FieldMetadataImpl(FieldMetaData internal) {
      super(internal);
   }

   public FieldMetadataImpl(FetchGroupMemberMetaData internal) {
      super(internal);
   }
}
