package org.datanucleus.store.schema.table;

import java.util.List;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.types.converters.TypeConverter;

public interface SchemaVerifier {
   TypeConverter verifyTypeConverterForMember(AbstractMemberMetaData var1, TypeConverter var2);

   void attributeMember(MemberColumnMapping var1, AbstractMemberMetaData var2);

   void attributeMember(MemberColumnMapping var1);

   void attributeEmbeddedMember(MemberColumnMapping var1, List var2);
}
