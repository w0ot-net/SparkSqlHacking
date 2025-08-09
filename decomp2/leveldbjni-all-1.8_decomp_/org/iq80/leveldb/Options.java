package org.iq80.leveldb;

public class Options {
   private boolean createIfMissing = true;
   private boolean errorIfExists;
   private int writeBufferSize = 4194304;
   private int maxOpenFiles = 1000;
   private int blockRestartInterval = 16;
   private int blockSize = 4096;
   private CompressionType compressionType;
   private boolean verifyChecksums;
   private boolean paranoidChecks;
   private DBComparator comparator;
   private Logger logger;
   private long cacheSize;

   public Options() {
      this.compressionType = CompressionType.SNAPPY;
      this.verifyChecksums = true;
      this.paranoidChecks = false;
      this.logger = null;
   }

   static void checkArgNotNull(Object value, String name) {
      if (value == null) {
         throw new IllegalArgumentException("The " + name + " argument cannot be null");
      }
   }

   public boolean createIfMissing() {
      return this.createIfMissing;
   }

   public Options createIfMissing(boolean createIfMissing) {
      this.createIfMissing = createIfMissing;
      return this;
   }

   public boolean errorIfExists() {
      return this.errorIfExists;
   }

   public Options errorIfExists(boolean errorIfExists) {
      this.errorIfExists = errorIfExists;
      return this;
   }

   public int writeBufferSize() {
      return this.writeBufferSize;
   }

   public Options writeBufferSize(int writeBufferSize) {
      this.writeBufferSize = writeBufferSize;
      return this;
   }

   public int maxOpenFiles() {
      return this.maxOpenFiles;
   }

   public Options maxOpenFiles(int maxOpenFiles) {
      this.maxOpenFiles = maxOpenFiles;
      return this;
   }

   public int blockRestartInterval() {
      return this.blockRestartInterval;
   }

   public Options blockRestartInterval(int blockRestartInterval) {
      this.blockRestartInterval = blockRestartInterval;
      return this;
   }

   public int blockSize() {
      return this.blockSize;
   }

   public Options blockSize(int blockSize) {
      this.blockSize = blockSize;
      return this;
   }

   public CompressionType compressionType() {
      return this.compressionType;
   }

   public Options compressionType(CompressionType compressionType) {
      checkArgNotNull(compressionType, "compressionType");
      this.compressionType = compressionType;
      return this;
   }

   public boolean verifyChecksums() {
      return this.verifyChecksums;
   }

   public Options verifyChecksums(boolean verifyChecksums) {
      this.verifyChecksums = verifyChecksums;
      return this;
   }

   public long cacheSize() {
      return this.cacheSize;
   }

   public Options cacheSize(long cacheSize) {
      this.cacheSize = cacheSize;
      return this;
   }

   public DBComparator comparator() {
      return this.comparator;
   }

   public Options comparator(DBComparator comparator) {
      this.comparator = comparator;
      return this;
   }

   public Logger logger() {
      return this.logger;
   }

   public Options logger(Logger logger) {
      this.logger = logger;
      return this;
   }

   public boolean paranoidChecks() {
      return this.paranoidChecks;
   }

   public Options paranoidChecks(boolean paranoidChecks) {
      this.paranoidChecks = paranoidChecks;
      return this;
   }
}
