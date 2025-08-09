package shaded.parquet.org.apache.thrift;

import java.io.Serializable;

public interface TBase extends Comparable, TSerializable, Serializable {
   TFieldIdEnum fieldForId(int var1);

   boolean isSet(TFieldIdEnum var1);

   Object getFieldValue(TFieldIdEnum var1);

   void setFieldValue(TFieldIdEnum var1, Object var2);

   TBase deepCopy();

   void clear();
}
