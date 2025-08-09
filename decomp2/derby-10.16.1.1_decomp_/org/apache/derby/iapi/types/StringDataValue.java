package org.apache.derby.iapi.types;

import java.sql.Clob;
import java.text.RuleBasedCollator;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface StringDataValue extends ConcatableDataValue {
   int BOTH = 0;
   int TRAILING = 1;
   int LEADING = 2;
   int COLLATION_DERIVATION_NONE = 0;
   int COLLATION_DERIVATION_IMPLICIT = 1;
   int COLLATION_DERIVATION_EXPLICIT = 2;
   int COLLATION_TYPE_UCS_BASIC = 0;
   int COLLATION_TYPE_TERRITORY_BASED = 1;
   int COLLATION_TYPE_TERRITORY_BASED_PRIMARY = 2;
   int COLLATION_TYPE_TERRITORY_BASED_SECONDARY = 3;
   int COLLATION_TYPE_TERRITORY_BASED_TERTIARY = 4;
   int COLLATION_TYPE_TERRITORY_BASED_IDENTICAL = 5;

   StringDataValue concatenate(StringDataValue var1, StringDataValue var2, StringDataValue var3) throws StandardException;

   BooleanDataValue like(DataValueDescriptor var1) throws StandardException;

   BooleanDataValue like(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   StringDataValue ansiTrim(int var1, StringDataValue var2, StringDataValue var3) throws StandardException;

   StringDataValue upper(StringDataValue var1) throws StandardException;

   StringDataValue lower(StringDataValue var1) throws StandardException;

   NumberDataValue locate(StringDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   char[] getCharArray() throws StandardException;

   StringDataValue getValue(RuleBasedCollator var1);

   StreamHeaderGenerator getStreamHeaderGenerator();

   void setStreamHeaderFormat(Boolean var1);

   CharacterStreamDescriptor getStreamWithDescriptor() throws StandardException;

   void setValue(Clob var1) throws StandardException;
}
