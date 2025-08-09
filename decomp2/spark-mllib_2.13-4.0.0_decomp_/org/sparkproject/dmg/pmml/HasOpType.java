package org.sparkproject.dmg.pmml;

public interface HasOpType {
   default OpType getOpType(OpType defaultOpType) {
      OpType opType = this.getOpType();
      return opType == null ? defaultOpType : opType;
   }

   OpType getOpType();

   PMMLObject setOpType(OpType var1);
}
