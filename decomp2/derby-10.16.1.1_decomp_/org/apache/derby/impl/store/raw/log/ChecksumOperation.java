package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class ChecksumOperation implements Loggable {
   private byte checksumAlgo;
   private long checksumValue;
   private int dataLength;
   private Checksum checksum;
   public static final byte CRC32_ALGORITHM = 1;
   private static final int formatLength = FormatIdUtil.getFormatIdByteLength(453);

   public void init() {
      this.checksumAlgo = 1;
      this.initializeChecksumAlgo();
      this.dataLength = 0;
   }

   protected void update(byte[] var1, int var2, int var3) {
      this.checksum.update(var1, var2, var3);
      this.dataLength += var3;
   }

   protected void reset() {
      this.checksum.reset();
      this.dataLength = 0;
   }

   private void initializeChecksumAlgo() {
      if (this.checksumAlgo == 1) {
         this.checksum = new CRC32();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.checksumValue = this.checksum.getValue();
      var1.writeByte(this.checksumAlgo);
      var1.writeInt(this.dataLength);
      var1.writeLong(this.checksumValue);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.checksumAlgo = (byte)var1.readUnsignedByte();
      this.dataLength = var1.readInt();
      this.checksumValue = var1.readLong();
      this.initializeChecksumAlgo();
   }

   public int getStoredSize() {
      return formatLength + 1 + 4 + 8;
   }

   public int getTypeFormatId() {
      return 453;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
   }

   public ByteArray getPreparedLog() {
      return (ByteArray)null;
   }

   public boolean needsRedo(Transaction var1) {
      return false;
   }

   public void releaseResource(Transaction var1) {
   }

   public int group() {
      return 2304;
   }

   protected int getDataLength() {
      return this.dataLength;
   }

   protected boolean isChecksumValid(byte[] var1, int var2, int var3) {
      this.checksum.reset();
      this.checksum.update(var1, var2, var3);
      return this.checksum.getValue() == this.checksumValue;
   }

   public String toString() {
      return null;
   }
}
