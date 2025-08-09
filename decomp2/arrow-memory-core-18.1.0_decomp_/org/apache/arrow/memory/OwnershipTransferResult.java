package org.apache.arrow.memory;

public interface OwnershipTransferResult {
   boolean getAllocationFit();

   ArrowBuf getTransferredBuffer();
}
