package org.apache.parquet.column.values.delta;

import java.io.IOException;
import java.io.InputStream;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;

class DeltaBinaryPackingConfig {
   final int blockSizeInValues;
   final int miniBlockNumInABlock;
   final int miniBlockSizeInValues;

   public DeltaBinaryPackingConfig(int blockSizeInValues, int miniBlockNumInABlock) {
      this.blockSizeInValues = blockSizeInValues;
      this.miniBlockNumInABlock = miniBlockNumInABlock;
      double miniSize = (double)blockSizeInValues / (double)miniBlockNumInABlock;
      Preconditions.checkArgument(miniSize % (double)8.0F == (double)0.0F, "miniBlockSize must be multiple of 8, but it's %s", miniSize);
      this.miniBlockSizeInValues = (int)miniSize;
   }

   public static DeltaBinaryPackingConfig readConfig(InputStream in) throws IOException {
      return new DeltaBinaryPackingConfig(BytesUtils.readUnsignedVarInt(in), BytesUtils.readUnsignedVarInt(in));
   }

   public BytesInput toBytesInput() {
      return BytesInput.concat(new BytesInput[]{BytesInput.fromUnsignedVarInt(this.blockSizeInValues), BytesInput.fromUnsignedVarInt(this.miniBlockNumInABlock)});
   }
}
