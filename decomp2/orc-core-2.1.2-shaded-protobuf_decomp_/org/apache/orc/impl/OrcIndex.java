package org.apache.orc.impl;

import org.apache.orc.OrcProto;

public final class OrcIndex {
   OrcProto.RowIndex[] rowGroupIndex;
   OrcProto.Stream.Kind[] bloomFilterKinds;
   OrcProto.BloomFilterIndex[] bloomFilterIndex;

   public OrcIndex(OrcProto.RowIndex[] rgIndex, OrcProto.Stream.Kind[] bloomFilterKinds, OrcProto.BloomFilterIndex[] bfIndex) {
      this.rowGroupIndex = rgIndex;
      this.bloomFilterKinds = bloomFilterKinds;
      this.bloomFilterIndex = bfIndex;
   }

   public OrcProto.RowIndex[] getRowGroupIndex() {
      return this.rowGroupIndex;
   }

   public OrcProto.BloomFilterIndex[] getBloomFilterIndex() {
      return this.bloomFilterIndex;
   }

   public OrcProto.Stream.Kind[] getBloomFilterKinds() {
      return this.bloomFilterKinds;
   }

   public void setRowGroupIndex(OrcProto.RowIndex[] rowGroupIndex) {
      this.rowGroupIndex = rowGroupIndex;
   }
}
