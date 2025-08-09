package org.rocksdb;

public class PlainTableConfig extends TableFormatConfig {
   public static final int VARIABLE_LENGTH = 0;
   public static final int DEFAULT_BLOOM_BITS_PER_KEY = 10;
   public static final double DEFAULT_HASH_TABLE_RATIO = (double)0.75F;
   public static final int DEFAULT_INDEX_SPARSENESS = 16;
   public static final int DEFAULT_HUGE_TLB_SIZE = 0;
   public static final EncodingType DEFAULT_ENCODING_TYPE;
   public static final boolean DEFAULT_FULL_SCAN_MODE = false;
   public static final boolean DEFAULT_STORE_INDEX_IN_FILE = false;
   private int keySize_ = 0;
   private int bloomBitsPerKey_ = 10;
   private double hashTableRatio_ = (double)0.75F;
   private int indexSparseness_ = 16;
   private int hugePageTlbSize_ = 0;
   private EncodingType encodingType_;
   private boolean fullScanMode_;
   private boolean storeIndexInFile_;

   public PlainTableConfig() {
      this.encodingType_ = DEFAULT_ENCODING_TYPE;
      this.fullScanMode_ = false;
      this.storeIndexInFile_ = false;
   }

   public PlainTableConfig setKeySize(int var1) {
      this.keySize_ = var1;
      return this;
   }

   public int keySize() {
      return this.keySize_;
   }

   public PlainTableConfig setBloomBitsPerKey(int var1) {
      this.bloomBitsPerKey_ = var1;
      return this;
   }

   public int bloomBitsPerKey() {
      return this.bloomBitsPerKey_;
   }

   public PlainTableConfig setHashTableRatio(double var1) {
      this.hashTableRatio_ = var1;
      return this;
   }

   public double hashTableRatio() {
      return this.hashTableRatio_;
   }

   public PlainTableConfig setIndexSparseness(int var1) {
      this.indexSparseness_ = var1;
      return this;
   }

   public long indexSparseness() {
      return (long)this.indexSparseness_;
   }

   public PlainTableConfig setHugePageTlbSize(int var1) {
      this.hugePageTlbSize_ = var1;
      return this;
   }

   public int hugePageTlbSize() {
      return this.hugePageTlbSize_;
   }

   public PlainTableConfig setEncodingType(EncodingType var1) {
      this.encodingType_ = var1;
      return this;
   }

   public EncodingType encodingType() {
      return this.encodingType_;
   }

   public PlainTableConfig setFullScanMode(boolean var1) {
      this.fullScanMode_ = var1;
      return this;
   }

   public boolean fullScanMode() {
      return this.fullScanMode_;
   }

   public PlainTableConfig setStoreIndexInFile(boolean var1) {
      this.storeIndexInFile_ = var1;
      return this;
   }

   public boolean storeIndexInFile() {
      return this.storeIndexInFile_;
   }

   protected long newTableFactoryHandle() {
      return newTableFactoryHandle(this.keySize_, this.bloomBitsPerKey_, this.hashTableRatio_, this.indexSparseness_, this.hugePageTlbSize_, this.encodingType_.getValue(), this.fullScanMode_, this.storeIndexInFile_);
   }

   private static native long newTableFactoryHandle(int var0, int var1, double var2, int var4, int var5, byte var6, boolean var7, boolean var8);

   static {
      DEFAULT_ENCODING_TYPE = EncodingType.kPlain;
   }
}
