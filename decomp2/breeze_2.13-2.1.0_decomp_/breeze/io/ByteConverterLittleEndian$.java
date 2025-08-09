package breeze.io;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;

public final class ByteConverterLittleEndian$ extends ByteConverter {
   public static final ByteConverterLittleEndian$ MODULE$ = new ByteConverterLittleEndian$();

   public short bytesToInt16(final byte b0, final byte b1) {
      return ByteConverterBigEndian$.MODULE$.bytesToInt16(b1, b0);
   }

   public char bytesToUInt16(final byte b0, final byte b1) {
      return ByteConverterBigEndian$.MODULE$.bytesToUInt16(b1, b0);
   }

   public int bytesToInt32(final byte b0, final byte b1, final byte b2, final byte b3) {
      return ByteConverterBigEndian$.MODULE$.bytesToInt32(b3, b2, b1, b0);
   }

   public long bytesToUInt32(final byte b0, final byte b1, final byte b2, final byte b3) {
      return ByteConverterBigEndian$.MODULE$.bytesToUInt32(b3, b2, b1, b0);
   }

   public long bytesToInt64(final byte b0, final byte b1, final byte b2, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7) {
      return ByteConverterBigEndian$.MODULE$.bytesToInt64(b7, b6, b5, b4, b3, b2, b1, b0);
   }

   public long bytesToUInt64Shifted(final byte b0, final byte b1, final byte b2, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7) {
      return ByteConverterBigEndian$.MODULE$.bytesToUInt64Shifted(b7, b6, b5, b4, b3, b2, b1, b0);
   }

   public byte[] int16ToBytes(final short value) {
      byte[] tempret = new byte[2];
      tempret[1] = (byte)(value >> 8);
      tempret[0] = (byte)(value & 255);
      return tempret;
   }

   public byte[] uInt16ToBytes(final char value) {
      .MODULE$.require(value <= '\uffff' && value >= 0, () -> (new StringBuilder(48)).append("Value ").append(value).append(" is out of range of 2-byte unsigned array.").toString());
      byte[] tempret = new byte[2];
      tempret[1] = (byte)(value >> 8 & 255);
      tempret[0] = (byte)(value & 255);
      return tempret;
   }

   public byte[] int32ToBytes(final int value) {
      byte[] tempret = new byte[4];
      tempret[3] = (byte)(value >> 24);
      tempret[2] = (byte)(value >> 16 & 255);
      tempret[1] = (byte)(value >> 8 & 255);
      tempret[0] = (byte)(value & 255);
      return tempret;
   }

   public byte[] uInt32ToBytes(final long value) {
      .MODULE$.require(value <= 4294967295L && value >= 0L, () -> (new StringBuilder(48)).append("Value ").append(value).append(" is out of range of 4-byte unsigned array.").toString());
      byte[] tempret = new byte[4];
      tempret[3] = (byte)((int)(value >> 24 & 255L));
      tempret[2] = (byte)((int)(value >> 16 & 255L));
      tempret[1] = (byte)((int)(value >> 8 & 255L));
      tempret[0] = (byte)((int)(value & 255L));
      return tempret;
   }

   public byte[] int64ToBytes(final long value) {
      byte[] tempret = new byte[8];
      tempret[7] = (byte)((int)(value >> 56));
      tempret[6] = (byte)((int)(value >> 48 & 255L));
      tempret[5] = (byte)((int)(value >> 40 & 255L));
      tempret[4] = (byte)((int)(value >> 32 & 255L));
      tempret[3] = (byte)((int)(value >> 24 & 255L));
      tempret[2] = (byte)((int)(value >> 16 & 255L));
      tempret[1] = (byte)((int)(value >> 8 & 255L));
      tempret[0] = (byte)((int)(value & 255L));
      return tempret;
   }

   public byte[] uInt64ToBytes(final long value) {
      byte[] tempret = new byte[8];
      long longValue = spire.math.ULong..MODULE$.ulongToBigInt(value).longValue();
      tempret[7] = (byte)((int)(longValue >> 56 & 255L));
      tempret[6] = (byte)((int)(longValue >> 48 & 255L));
      tempret[5] = (byte)((int)(longValue >> 40 & 255L));
      tempret[4] = (byte)((int)(longValue >> 32 & 255L));
      tempret[3] = (byte)((int)(longValue >> 24 & 255L));
      tempret[2] = (byte)((int)(longValue >> 16 & 255L));
      tempret[1] = (byte)((int)(longValue >> 8 & 255L));
      tempret[0] = (byte)((int)(longValue & 255L));
      return tempret;
   }

   public byte[] uInt64ShiftedToBytes(final long value) {
      byte[] tempret = new byte[8];
      tempret[7] = (byte)((int)(value >> 56 & 255L ^ 128L));
      tempret[6] = (byte)((int)(value >> 48 & 255L));
      tempret[5] = (byte)((int)(value >> 40 & 255L));
      tempret[4] = (byte)((int)(value >> 32 & 255L));
      tempret[3] = (byte)((int)(value >> 24 & 255L));
      tempret[2] = (byte)((int)(value >> 16 & 255L));
      tempret[1] = (byte)((int)(value >> 8 & 255L));
      tempret[0] = (byte)((int)(value & 255L));
      return tempret;
   }

   private ByteConverterLittleEndian$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
