package org.apache.derby.catalog;

import org.apache.derby.iapi.types.DataTypeDescriptor;

public interface TypeDescriptor {
   int MAXIMUM_WIDTH_UNKNOWN = -1;
   TypeDescriptor INTEGER = DataTypeDescriptor.INTEGER.getCatalogType();
   TypeDescriptor INTEGER_NOT_NULL = DataTypeDescriptor.INTEGER_NOT_NULL.getCatalogType();
   TypeDescriptor SMALLINT = DataTypeDescriptor.SMALLINT.getCatalogType();
   TypeDescriptor SMALLINT_NOT_NULL = DataTypeDescriptor.SMALLINT_NOT_NULL.getCatalogType();
   TypeDescriptor DOUBLE = DataTypeDescriptor.DOUBLE.getCatalogType();

   int getJDBCTypeId();

   int getMaximumWidth();

   int getMaximumWidthInBytes();

   int getPrecision();

   int getScale();

   boolean isNullable();

   String getTypeName();

   String getSQLstring();

   int getCollationType();

   boolean isRowMultiSet();

   boolean isUserDefinedType();

   TypeDescriptor[] getRowTypes();

   String[] getRowColumnNames();
}
