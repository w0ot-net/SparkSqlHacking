package org.sparkproject.dmg.pmml;

public interface HasDataType {
   default DataType getDataType(DataType defaultDataType) {
      DataType dataType = this.getDataType();
      return dataType == null ? defaultDataType : dataType;
   }

   DataType getDataType();

   PMMLObject setDataType(DataType var1);
}
