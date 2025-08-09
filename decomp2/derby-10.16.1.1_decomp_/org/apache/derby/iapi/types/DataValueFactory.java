package org.apache.derby.iapi.types;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

public interface DataValueFactory {
   NumberDataValue getDataValue(Integer var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(char var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(Short var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(Byte var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(Long var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(Float var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(Double var1, NumberDataValue var2) throws StandardException;

   BooleanDataValue getDataValue(Boolean var1, BooleanDataValue var2) throws StandardException;

   BitDataValue getLongVarbitDataValue(byte[] var1, BitDataValue var2) throws StandardException;

   BitDataValue getBlobDataValue(byte[] var1, BitDataValue var2) throws StandardException;

   BitDataValue getBlobDataValue(Blob var1, BitDataValue var2) throws StandardException;

   StringDataValue getVarcharDataValue(String var1);

   StringDataValue getVarcharDataValue(String var1, StringDataValue var2) throws StandardException;

   StringDataValue getVarcharDataValue(String var1, StringDataValue var2, int var3) throws StandardException;

   StringDataValue getLongvarcharDataValue(String var1);

   StringDataValue getLongvarcharDataValue(String var1, StringDataValue var2) throws StandardException;

   StringDataValue getLongvarcharDataValue(String var1, StringDataValue var2, int var3) throws StandardException;

   StringDataValue getClobDataValue(String var1, StringDataValue var2) throws StandardException;

   StringDataValue getClobDataValue(Clob var1, StringDataValue var2) throws StandardException;

   StringDataValue getClobDataValue(String var1, StringDataValue var2, int var3) throws StandardException;

   StringDataValue getClobDataValue(Clob var1, StringDataValue var2, int var3) throws StandardException;

   UserDataValue getDataValue(Object var1, UserDataValue var2);

   RefDataValue getDataValue(RowLocation var1, RefDataValue var2);

   NumberDataValue getDataValue(int var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(long var1, NumberDataValue var3) throws StandardException;

   NumberDataValue getDataValue(float var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(double var1, NumberDataValue var3) throws StandardException;

   NumberDataValue getDataValue(short var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDataValue(byte var1, NumberDataValue var2) throws StandardException;

   NumberDataValue getDecimalDataValue(Number var1, NumberDataValue var2) throws StandardException;

   BooleanDataValue getDataValue(boolean var1, BooleanDataValue var2) throws StandardException;

   BitDataValue getBitDataValue(byte[] var1) throws StandardException;

   BitDataValue getBitDataValue(byte[] var1, BitDataValue var2) throws StandardException;

   BitDataValue getVarbitDataValue(byte[] var1, BitDataValue var2) throws StandardException;

   StringDataValue getCharDataValue(String var1);

   StringDataValue getCharDataValue(String var1, StringDataValue var2) throws StandardException;

   StringDataValue getCharDataValue(String var1, StringDataValue var2, int var3) throws StandardException;

   DateTimeDataValue getDataValue(Date var1, DateTimeDataValue var2) throws StandardException;

   DateTimeDataValue getDataValue(Time var1, DateTimeDataValue var2) throws StandardException;

   DateTimeDataValue getDataValue(Timestamp var1, DateTimeDataValue var2) throws StandardException;

   DateTimeDataValue getTimestamp(DataValueDescriptor var1) throws StandardException;

   DateTimeDataValue getTimestamp(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   DateTimeDataValue getDate(DataValueDescriptor var1) throws StandardException;

   DateTimeDataValue getDateValue(String var1, boolean var2) throws StandardException;

   DateTimeDataValue getTimeValue(String var1, boolean var2) throws StandardException;

   DateTimeDataValue getTimestampValue(String var1, boolean var2) throws StandardException;

   XMLDataValue getXMLDataValue(XMLDataValue var1) throws StandardException;

   NumberDataValue getNullInteger(NumberDataValue var1);

   NumberDataValue getNullShort(NumberDataValue var1);

   NumberDataValue getNullByte(NumberDataValue var1);

   NumberDataValue getNullLong(NumberDataValue var1);

   NumberDataValue getNullFloat(NumberDataValue var1);

   NumberDataValue getNullDouble(NumberDataValue var1);

   NumberDataValue getNullDecimal(NumberDataValue var1);

   BooleanDataValue getNullBoolean(BooleanDataValue var1);

   BitDataValue getNullBit(BitDataValue var1) throws StandardException;

   BitDataValue getNullVarbit(BitDataValue var1) throws StandardException;

   BitDataValue getNullLongVarbit(BitDataValue var1) throws StandardException;

   BitDataValue getNullBlob(BitDataValue var1) throws StandardException;

   StringDataValue getNullChar(StringDataValue var1);

   StringDataValue getNullChar(StringDataValue var1, int var2) throws StandardException;

   StringDataValue getNullVarchar(StringDataValue var1);

   StringDataValue getNullVarchar(StringDataValue var1, int var2) throws StandardException;

   StringDataValue getNullLongvarchar(StringDataValue var1);

   StringDataValue getNullLongvarchar(StringDataValue var1, int var2) throws StandardException;

   StringDataValue getNullClob(StringDataValue var1);

   StringDataValue getNullClob(StringDataValue var1, int var2) throws StandardException;

   UserDataValue getNullObject(UserDataValue var1);

   RefDataValue getNullRef(RefDataValue var1);

   DateTimeDataValue getNullDate(DateTimeDataValue var1);

   DateTimeDataValue getNullTime(DateTimeDataValue var1);

   DateTimeDataValue getNullTimestamp(DateTimeDataValue var1);

   XMLDataValue getNullXML(XMLDataValue var1);

   RuleBasedCollator getCharacterCollator(int var1) throws StandardException;

   DataValueDescriptor getNull(int var1, int var2) throws StandardException;
}
