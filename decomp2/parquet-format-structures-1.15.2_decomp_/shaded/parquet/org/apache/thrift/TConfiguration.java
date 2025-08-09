package shaded.parquet.org.apache.thrift;

public class TConfiguration {
   public static final int DEFAULT_MAX_MESSAGE_SIZE = 104857600;
   public static final int DEFAULT_MAX_FRAME_SIZE = 16384000;
   public static final int DEFAULT_RECURSION_DEPTH = 64;
   private int maxMessageSize;
   private int maxFrameSize;
   private int recursionLimit;
   public static final TConfiguration DEFAULT = (new Builder()).build();

   public TConfiguration() {
      this(104857600, 16384000, 64);
   }

   public TConfiguration(int maxMessageSize, int maxFrameSize, int recursionLimit) {
      this.maxFrameSize = maxFrameSize;
      this.maxMessageSize = maxMessageSize;
      this.recursionLimit = recursionLimit;
   }

   public int getMaxMessageSize() {
      return this.maxMessageSize;
   }

   public int getMaxFrameSize() {
      return this.maxFrameSize;
   }

   public int getRecursionLimit() {
      return this.recursionLimit;
   }

   public void setMaxMessageSize(int maxMessageSize) {
      this.maxMessageSize = maxMessageSize;
   }

   public void setMaxFrameSize(int maxFrameSize) {
      this.maxFrameSize = maxFrameSize;
   }

   public void setRecursionLimit(int recursionLimit) {
      this.recursionLimit = recursionLimit;
   }

   public static Builder custom() {
      return new Builder();
   }

   public static class Builder {
      private int maxMessageSize = 104857600;
      private int maxFrameSize = 16384000;
      private int recursionLimit = 64;

      Builder() {
      }

      public Builder setMaxMessageSize(int maxMessageSize) {
         this.maxMessageSize = maxMessageSize;
         return this;
      }

      public Builder setMaxFrameSize(int maxFrameSize) {
         this.maxFrameSize = maxFrameSize;
         return this;
      }

      public Builder setRecursionLimit(int recursionLimit) {
         this.recursionLimit = recursionLimit;
         return this;
      }

      public TConfiguration build() {
         return new TConfiguration(this.maxMessageSize, this.maxFrameSize, this.recursionLimit);
      }
   }
}
