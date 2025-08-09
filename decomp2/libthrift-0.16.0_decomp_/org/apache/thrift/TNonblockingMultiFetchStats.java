package org.apache.thrift;

public class TNonblockingMultiFetchStats {
   private int numTotalServers;
   private int numReadCompletedServers;
   private int numConnectErrorServers;
   private int totalRecvBufBytes;
   private int maxResponseBytes;
   private int numOverflowedRecvBuf;
   private int numInvalidFrameSize;
   private long readTime;

   public TNonblockingMultiFetchStats() {
      this.clear();
   }

   public void clear() {
      this.numTotalServers = 0;
      this.numReadCompletedServers = 0;
      this.numConnectErrorServers = 0;
      this.totalRecvBufBytes = 0;
      this.maxResponseBytes = 0;
      this.numOverflowedRecvBuf = 0;
      this.numInvalidFrameSize = 0;
      this.readTime = 0L;
   }

   public String toString() {
      String stats = String.format("numTotalServers=%d, numReadCompletedServers=%d, numConnectErrorServers=%d, numUnresponsiveServers=%d, totalRecvBufBytes=%fM, maxResponseBytes=%d, numOverflowedRecvBuf=%d, numInvalidFrameSize=%d, readTime=%dms", this.numTotalServers, this.numReadCompletedServers, this.numConnectErrorServers, this.numTotalServers - this.numReadCompletedServers - this.numConnectErrorServers, (double)this.totalRecvBufBytes / (double)1024.0F / (double)1024.0F, this.maxResponseBytes, this.numOverflowedRecvBuf, this.numInvalidFrameSize, this.readTime);
      return stats;
   }

   public void setNumTotalServers(int val) {
      this.numTotalServers = val;
   }

   public void setMaxResponseBytes(int val) {
      this.maxResponseBytes = val;
   }

   public void setReadTime(long val) {
      this.readTime = val;
   }

   public void incNumReadCompletedServers() {
      ++this.numReadCompletedServers;
   }

   public void incNumConnectErrorServers() {
      ++this.numConnectErrorServers;
   }

   public void incNumOverflowedRecvBuf() {
      ++this.numOverflowedRecvBuf;
   }

   public void incTotalRecvBufBytes(int val) {
      this.totalRecvBufBytes += val;
   }

   public void incNumInvalidFrameSize() {
      ++this.numInvalidFrameSize;
   }

   public int getMaxResponseBytes() {
      return this.maxResponseBytes;
   }

   public int getNumReadCompletedServers() {
      return this.numReadCompletedServers;
   }

   public int getNumConnectErrorServers() {
      return this.numConnectErrorServers;
   }

   public int getNumTotalServers() {
      return this.numTotalServers;
   }

   public int getNumOverflowedRecvBuf() {
      return this.numOverflowedRecvBuf;
   }

   public int getTotalRecvBufBytes() {
      return this.totalRecvBufBytes;
   }

   public int getNumInvalidFrameSize() {
      return this.numInvalidFrameSize;
   }

   public long getReadTime() {
      return this.readTime;
   }
}
