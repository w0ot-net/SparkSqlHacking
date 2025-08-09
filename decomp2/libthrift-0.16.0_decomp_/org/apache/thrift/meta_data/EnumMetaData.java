package org.apache.thrift.meta_data;

import org.apache.thrift.TEnum;

public class EnumMetaData extends FieldValueMetaData {
   public final Class enumClass;

   public EnumMetaData(byte type, Class sClass) {
      super(type);
      this.enumClass = sClass;
   }
}
