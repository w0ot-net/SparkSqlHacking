package org.apache.zookeeper.compat;

import java.io.IOException;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.zookeeper.proto.ConnectRequest;
import org.apache.zookeeper.proto.ConnectResponse;

public final class ProtocolManager {
   private volatile Boolean isReadonlyAvailable = null;

   public boolean isReadonlyAvailable() {
      return this.isReadonlyAvailable != null && this.isReadonlyAvailable;
   }

   public ConnectRequest deserializeConnectRequest(InputArchive inputArchive) throws IOException {
      if (this.isReadonlyAvailable != null) {
         return this.isReadonlyAvailable ? this.deserializeConnectRequestWithReadonly(inputArchive) : this.deserializeConnectRequestWithoutReadonly(inputArchive);
      } else {
         ConnectRequest request = this.deserializeConnectRequestWithoutReadonly(inputArchive);

         try {
            request.setReadOnly(inputArchive.readBool("readOnly"));
            this.isReadonlyAvailable = true;
         } catch (Exception var4) {
            request.setReadOnly(false);
            this.isReadonlyAvailable = false;
         }

         return request;
      }
   }

   private ConnectRequest deserializeConnectRequestWithReadonly(InputArchive inputArchive) throws IOException {
      ConnectRequest request = new ConnectRequest();
      request.deserialize(inputArchive, "connect");
      return request;
   }

   private ConnectRequest deserializeConnectRequestWithoutReadonly(InputArchive inputArchive) throws IOException {
      ConnectRequest request = new ConnectRequest();
      inputArchive.startRecord("connect");
      request.setProtocolVersion(inputArchive.readInt("protocolVersion"));
      request.setLastZxidSeen(inputArchive.readLong("lastZxidSeen"));
      request.setTimeOut(inputArchive.readInt("timeOut"));
      request.setSessionId(inputArchive.readLong("sessionId"));
      request.setPasswd(inputArchive.readBuffer("passwd"));
      inputArchive.endRecord("connect");
      return request;
   }

   public ConnectResponse deserializeConnectResponse(InputArchive inputArchive) throws IOException {
      if (this.isReadonlyAvailable != null) {
         return this.isReadonlyAvailable ? this.deserializeConnectResponseWithReadonly(inputArchive) : this.deserializeConnectResponseWithoutReadonly(inputArchive);
      } else {
         ConnectResponse response = this.deserializeConnectResponseWithoutReadonly(inputArchive);

         try {
            response.setReadOnly(inputArchive.readBool("readOnly"));
            this.isReadonlyAvailable = true;
         } catch (Exception var4) {
            response.setReadOnly(false);
            this.isReadonlyAvailable = false;
         }

         return response;
      }
   }

   private ConnectResponse deserializeConnectResponseWithReadonly(InputArchive inputArchive) throws IOException {
      ConnectResponse response = new ConnectResponse();
      response.deserialize(inputArchive, "connect");
      return response;
   }

   private ConnectResponse deserializeConnectResponseWithoutReadonly(InputArchive inputArchive) throws IOException {
      ConnectResponse response = new ConnectResponse();
      inputArchive.startRecord("connect");
      response.setProtocolVersion(inputArchive.readInt("protocolVersion"));
      response.setTimeOut(inputArchive.readInt("timeOut"));
      response.setSessionId(inputArchive.readLong("sessionId"));
      response.setPasswd(inputArchive.readBuffer("passwd"));
      inputArchive.endRecord("connect");
      return response;
   }

   public void serializeConnectResponse(ConnectResponse response, OutputArchive outputArchive) throws IOException {
      serializeConnectResponse(response, outputArchive, this.isReadonlyAvailable());
   }

   private static void serializeConnectResponse(ConnectResponse response, OutputArchive outputArchive, boolean withReadonly) throws IOException {
      if (withReadonly) {
         serializeConnectResponseWithReadonly(response, outputArchive);
      } else {
         serializeConnectResponseWithoutReadonly(response, outputArchive);
      }

   }

   private static void serializeConnectResponseWithReadonly(ConnectResponse response, OutputArchive outputArchive) throws IOException {
      response.serialize(outputArchive, "connect");
   }

   private static void serializeConnectResponseWithoutReadonly(ConnectResponse response, OutputArchive outputArchive) throws IOException {
      outputArchive.startRecord(response, "connect");
      outputArchive.writeInt(response.getProtocolVersion(), "protocolVersion");
      outputArchive.writeInt(response.getTimeOut(), "timeOut");
      outputArchive.writeLong(response.getSessionId(), "sessionId");
      outputArchive.writeBuffer(response.getPasswd(), "passwd");
      outputArchive.endRecord(response, "connect");
   }
}
