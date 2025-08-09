package shaded.parquet.org.apache.thrift.protocol;

public final class TMap {
   public final byte keyType;
   public final byte valueType;
   public final int size;

   public TMap() {
      this((byte)0, (byte)0, 0);
   }

   public TMap(byte k, byte v, int s) {
      this.keyType = k;
      this.valueType = v;
      this.size = s;
   }

   public byte getKeyType() {
      return this.keyType;
   }

   public byte getValueType() {
      return this.valueType;
   }

   public int getSize() {
      return this.size;
   }
}
