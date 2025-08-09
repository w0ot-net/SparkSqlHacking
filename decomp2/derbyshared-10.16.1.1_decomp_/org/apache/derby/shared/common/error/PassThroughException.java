package org.apache.derby.shared.common.error;

public final class PassThroughException extends RuntimeException {
   public PassThroughException(Throwable var1) {
      super(var1);
   }
}
