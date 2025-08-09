package org.apache.arrow.memory;

public class OwnershipTransferNOOP implements OwnershipTransferResult {
   private final ArrowBuf buffer;

   OwnershipTransferNOOP(ArrowBuf buf) {
      this.buffer = buf;
   }

   public ArrowBuf getTransferredBuffer() {
      return this.buffer;
   }

   public boolean getAllocationFit() {
      return true;
   }
}
