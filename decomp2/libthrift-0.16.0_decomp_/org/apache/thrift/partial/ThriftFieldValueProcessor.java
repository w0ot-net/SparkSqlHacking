package org.apache.thrift.partial;

import java.nio.ByteBuffer;
import org.apache.thrift.TFieldIdEnum;

public interface ThriftFieldValueProcessor {
   Object createNewStruct(ThriftMetadata.ThriftStruct var1);

   Object prepareStruct(Object var1);

   void setBool(Object var1, TFieldIdEnum var2, boolean var3);

   void setByte(Object var1, TFieldIdEnum var2, byte var3);

   void setInt16(Object var1, TFieldIdEnum var2, short var3);

   void setInt32(Object var1, TFieldIdEnum var2, int var3);

   void setInt64(Object var1, TFieldIdEnum var2, long var3);

   void setDouble(Object var1, TFieldIdEnum var2, double var3);

   void setBinary(Object var1, TFieldIdEnum var2, ByteBuffer var3);

   void setString(Object var1, TFieldIdEnum var2, ByteBuffer var3);

   void setEnumField(Object var1, TFieldIdEnum var2, Object var3);

   void setListField(Object var1, TFieldIdEnum var2, Object var3);

   void setMapField(Object var1, TFieldIdEnum var2, Object var3);

   void setSetField(Object var1, TFieldIdEnum var2, Object var3);

   void setStructField(Object var1, TFieldIdEnum var2, Object var3);

   Object prepareEnum(Class var1, int var2);

   Object prepareString(ByteBuffer var1);

   Object prepareBinary(ByteBuffer var1);

   Object createNewList(int var1);

   void setListElement(Object var1, int var2, Object var3);

   Object prepareList(Object var1);

   Object createNewMap(int var1);

   void setMapElement(Object var1, int var2, Object var3, Object var4);

   Object prepareMap(Object var1);

   Object createNewSet(int var1);

   void setSetElement(Object var1, int var2, Object var3);

   Object prepareSet(Object var1);
}
