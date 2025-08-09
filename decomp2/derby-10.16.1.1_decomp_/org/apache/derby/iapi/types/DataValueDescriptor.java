package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.shared.common.error.StandardException;

public interface DataValueDescriptor extends Storable, Orderable {
   int UNKNOWN_LOGICAL_LENGTH = -1;

   int getLength() throws StandardException;

   String getString() throws StandardException;

   String getTraceString() throws StandardException;

   boolean getBoolean() throws StandardException;

   byte getByte() throws StandardException;

   short getShort() throws StandardException;

   int getInt() throws StandardException;

   long getLong() throws StandardException;

   float getFloat() throws StandardException;

   double getDouble() throws StandardException;

   int typeToBigDecimal() throws StandardException;

   byte[] getBytes() throws StandardException;

   Date getDate(Calendar var1) throws StandardException;

   Time getTime(Calendar var1) throws StandardException;

   Timestamp getTimestamp(Calendar var1) throws StandardException;

   Object getObject() throws StandardException;

   InputStream getStream() throws StandardException;

   boolean hasStream();

   DataValueDescriptor cloneHolder();

   DataValueDescriptor cloneValue(boolean var1);

   DataValueDescriptor recycle();

   DataValueDescriptor getNewNull();

   void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws StandardException, SQLException;

   void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException;

   void setInto(ResultSet var1, int var2) throws SQLException, StandardException;

   void setValue(int var1) throws StandardException;

   void setValue(double var1) throws StandardException;

   void setValue(float var1) throws StandardException;

   void setValue(short var1) throws StandardException;

   void setValue(long var1) throws StandardException;

   void setValue(byte var1) throws StandardException;

   void setValue(boolean var1) throws StandardException;

   void setValue(Object var1) throws StandardException;

   void setValue(byte[] var1) throws StandardException;

   void setBigDecimal(BigDecimal var1) throws StandardException;

   void setValue(String var1) throws StandardException;

   void setValue(Blob var1) throws StandardException;

   void setValue(Clob var1) throws StandardException;

   void setValue(Time var1) throws StandardException;

   void setValue(Time var1, Calendar var2) throws StandardException;

   void setValue(Timestamp var1) throws StandardException;

   void setValue(Timestamp var1, Calendar var2) throws StandardException;

   void setValue(Date var1) throws StandardException;

   void setValue(Date var1, Calendar var2) throws StandardException;

   void setValue(DataValueDescriptor var1) throws StandardException;

   void setToNull();

   void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue isNullOp();

   BooleanDataValue isNotNull();

   String getTypeName();

   void setObjectForCast(Object var1, boolean var2, String var3) throws StandardException;

   void readExternalFromArray(ArrayInputStream var1) throws IOException, ClassNotFoundException;

   int typePrecedence();

   BooleanDataValue equals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue notEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue lessThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue greaterThan(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue lessOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue greaterOrEquals(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException;

   DataValueDescriptor coalesce(DataValueDescriptor[] var1, DataValueDescriptor var2) throws StandardException;

   BooleanDataValue in(DataValueDescriptor var1, DataValueDescriptor[] var2, boolean var3) throws StandardException;

   int compare(DataValueDescriptor var1) throws StandardException;

   int compare(DataValueDescriptor var1, boolean var2) throws StandardException;

   boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException;

   boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4, boolean var5) throws StandardException;

   void setValue(InputStream var1, int var2) throws StandardException;

   void checkHostVariable(int var1) throws StandardException;

   int estimateMemoryUsage();
}
