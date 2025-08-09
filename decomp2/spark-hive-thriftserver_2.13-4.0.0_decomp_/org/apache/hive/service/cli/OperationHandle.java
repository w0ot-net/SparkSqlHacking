package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;

public class OperationHandle extends Handle {
   private final OperationType opType;
   private final TProtocolVersion protocol;
   private boolean hasResultSet;

   public OperationHandle(OperationType opType, TProtocolVersion protocol) {
      this.hasResultSet = false;
      this.opType = opType;
      this.protocol = protocol;
   }

   public OperationHandle(TOperationHandle tOperationHandle) {
      this(tOperationHandle, TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V1);
   }

   public OperationHandle(TOperationHandle tOperationHandle, TProtocolVersion protocol) {
      super(tOperationHandle.getOperationId());
      this.hasResultSet = false;
      this.opType = OperationType.getOperationType(tOperationHandle.getOperationType());
      this.hasResultSet = tOperationHandle.isHasResultSet();
      this.protocol = protocol;
   }

   public OperationType getOperationType() {
      return this.opType;
   }

   public void setHasResultSet(boolean hasResultSet) {
      this.hasResultSet = hasResultSet;
   }

   public boolean hasResultSet() {
      return this.hasResultSet;
   }

   public TOperationHandle toTOperationHandle() {
      TOperationHandle tOperationHandle = new TOperationHandle();
      tOperationHandle.setOperationId(this.getHandleIdentifier().toTHandleIdentifier());
      tOperationHandle.setOperationType(this.opType.toTOperationType());
      tOperationHandle.setHasResultSet(this.hasResultSet);
      return tOperationHandle;
   }

   public TProtocolVersion getProtocolVersion() {
      return this.protocol;
   }

   public int hashCode() {
      int prime = 31;
      int result = super.hashCode();
      result = 31 * result + (this.opType == null ? 0 : this.opType.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (!(obj instanceof OperationHandle)) {
         return false;
      } else {
         OperationHandle other = (OperationHandle)obj;
         return this.opType == other.opType;
      }
   }

   public String toString() {
      String var10000 = String.valueOf(this.opType);
      return "OperationHandle [opType=" + var10000 + ", getHandleIdentifier()=" + String.valueOf(this.getHandleIdentifier()) + "]";
   }
}
