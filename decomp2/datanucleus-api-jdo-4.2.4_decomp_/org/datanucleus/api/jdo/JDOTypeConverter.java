package org.datanucleus.api.jdo;

import javax.jdo.AttributeConverter;
import org.datanucleus.store.types.converters.TypeConverter;

public class JDOTypeConverter implements TypeConverter {
   private static final long serialVersionUID = -4250901331525617340L;
   AttributeConverter jdoConverter;
   Class memberType;
   Class dbType;

   public JDOTypeConverter(AttributeConverter conv, Class memberType, Class dbType) {
      this.jdoConverter = conv;
      this.dbType = dbType;
      this.memberType = memberType;
   }

   public Class getMemberClass() {
      return this.memberType;
   }

   public Class getDatastoreClass() {
      return this.dbType;
   }

   public Object toDatastoreType(Object memberValue) {
      return this.jdoConverter.convertToDatastore(memberValue);
   }

   public Object toMemberType(Object datastoreValue) {
      return this.jdoConverter.convertToAttribute(datastoreValue);
   }

   public String toString() {
      return "JDOTypeConverter<" + this.memberType.getName() + "," + this.dbType.getName() + ">";
   }
}
