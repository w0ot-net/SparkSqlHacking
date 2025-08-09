package shaded.parquet.org.apache.thrift.partial;

public class TFieldData {
   public static int encode(byte type) {
      return type & 255;
   }

   public static int encode(byte type, short id) {
      return type & 255 | id << 8;
   }

   public static byte getType(int data) {
      return (byte)(255 & data);
   }

   public static short getId(int data) {
      return (short)((16776960 & data) >> 8);
   }
}
