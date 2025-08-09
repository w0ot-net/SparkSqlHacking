package org.apache.thrift.meta_data;

import org.apache.thrift.TBase;

public class StructMetaData extends FieldValueMetaData {
   public final Class structClass;

   public StructMetaData(byte type, Class sClass) {
      super(type);
      this.structClass = sClass;
   }
}
