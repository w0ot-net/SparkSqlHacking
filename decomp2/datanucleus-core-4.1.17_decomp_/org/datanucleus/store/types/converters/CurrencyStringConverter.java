package org.datanucleus.store.types.converters;

import java.util.Currency;

public class CurrencyStringConverter implements TypeConverter, ColumnLengthDefiningTypeConverter {
   private static final long serialVersionUID = 466510473779336706L;

   public Currency toMemberType(String str) {
      return str == null ? null : Currency.getInstance(str);
   }

   public String toDatastoreType(Currency curr) {
      return curr != null ? curr.toString() : null;
   }

   public int getDefaultColumnLength(int columnPosition) {
      return columnPosition != 0 ? -1 : 3;
   }
}
