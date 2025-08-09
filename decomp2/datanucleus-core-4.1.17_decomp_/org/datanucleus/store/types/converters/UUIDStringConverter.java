package org.datanucleus.store.types.converters;

import java.util.UUID;

public class UUIDStringConverter implements TypeConverter, ColumnLengthDefiningTypeConverter {
   private static final long serialVersionUID = -7786945714314305089L;

   public UUID toMemberType(String str) {
      return str == null ? null : UUID.fromString(str);
   }

   public String toDatastoreType(UUID uuid) {
      return uuid != null ? uuid.toString() : null;
   }

   public int getDefaultColumnLength(int columnPosition) {
      return columnPosition != 0 ? -1 : 36;
   }
}
