package org.apache.spark.shuffle.sort;

final class PackedRecordPointer {
   static final int MAXIMUM_PAGE_SIZE_BYTES = 134217728;
   static final int MAXIMUM_PARTITION_ID = 16777215;
   static final int PARTITION_ID_START_BYTE_INDEX = 5;
   static final int PARTITION_ID_END_BYTE_INDEX = 7;
   private static final long MASK_LONG_LOWER_40_BITS = 1099511627775L;
   private static final long MASK_LONG_UPPER_24_BITS = -1099511627776L;
   private static final long MASK_LONG_LOWER_27_BITS = 134217727L;
   private static final long MASK_LONG_LOWER_51_BITS = 2251799813685247L;
   private static final long MASK_LONG_UPPER_13_BITS = -2251799813685248L;
   private long packedRecordPointer;

   public static long packPointer(long recordPointer, int partitionId) {
      assert partitionId <= 16777215;

      long pageNumber = (recordPointer & -2251799813685248L) >>> 24;
      long compressedAddress = pageNumber | recordPointer & 134217727L;
      return (long)partitionId << 40 | compressedAddress;
   }

   public void set(long packedRecordPointer) {
      this.packedRecordPointer = packedRecordPointer;
   }

   public int getPartitionId() {
      return (int)((this.packedRecordPointer & -1099511627776L) >>> 40);
   }

   public long getRecordPointer() {
      long pageNumber = this.packedRecordPointer << 24 & -2251799813685248L;
      long offsetInPage = this.packedRecordPointer & 134217727L;
      return pageNumber | offsetInPage;
   }
}
