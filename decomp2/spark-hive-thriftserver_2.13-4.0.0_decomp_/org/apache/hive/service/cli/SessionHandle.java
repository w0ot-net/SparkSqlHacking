package org.apache.hive.service.cli;

import java.util.UUID;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TSessionHandle;

public class SessionHandle extends Handle {
   private final TProtocolVersion protocol;

   public SessionHandle(TProtocolVersion protocol) {
      this.protocol = protocol;
   }

   public SessionHandle(TSessionHandle tSessionHandle) {
      this(tSessionHandle, TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V1);
   }

   public SessionHandle(TSessionHandle tSessionHandle, TProtocolVersion protocol) {
      super(tSessionHandle.getSessionId());
      this.protocol = protocol;
   }

   public SessionHandle(HandleIdentifier handleId, TProtocolVersion protocol) {
      super(handleId);
      this.protocol = protocol;
   }

   public UUID getSessionId() {
      return this.getHandleIdentifier().getPublicId();
   }

   public TSessionHandle toTSessionHandle() {
      TSessionHandle tSessionHandle = new TSessionHandle();
      tSessionHandle.setSessionId(this.getHandleIdentifier().toTHandleIdentifier());
      return tSessionHandle;
   }

   public TProtocolVersion getProtocolVersion() {
      return this.protocol;
   }

   public String toString() {
      return "SessionHandle [" + String.valueOf(this.getHandleIdentifier()) + "]";
   }
}
