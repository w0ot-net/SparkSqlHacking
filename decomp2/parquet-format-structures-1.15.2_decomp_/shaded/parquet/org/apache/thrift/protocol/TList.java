package shaded.parquet.org.apache.thrift.protocol;

public final class TList {
   public final byte elemType;
   public final int size;

   public TList() {
      this((byte)0, 0);
   }

   public TList(byte t, int s) {
      this.elemType = t;
      this.size = s;
   }

   public byte getElemType() {
      return this.elemType;
   }

   public int getSize() {
      return this.size;
   }
}
