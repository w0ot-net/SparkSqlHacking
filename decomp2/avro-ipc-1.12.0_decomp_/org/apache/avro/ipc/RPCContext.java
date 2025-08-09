package org.apache.avro.ipc;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.avro.Protocol;

public class RPCContext {
   private HandshakeRequest handshakeRequest;
   private HandshakeResponse handshakeResponse;
   protected Map requestCallMeta;
   protected Map responseCallMeta;
   protected Object response;
   protected Exception error;
   private Protocol.Message message;
   List requestPayload;
   List responsePayload;

   public void setHandshakeRequest(HandshakeRequest handshakeRequest) {
      this.handshakeRequest = handshakeRequest;
   }

   public HandshakeRequest getHandshakeRequest() {
      return this.handshakeRequest;
   }

   public void setHandshakeResponse(HandshakeResponse handshakeResponse) {
      this.handshakeResponse = handshakeResponse;
   }

   public HandshakeResponse getHandshakeResponse() {
      return this.handshakeResponse;
   }

   public Map requestHandshakeMeta() {
      if (this.handshakeRequest.getMeta() == null) {
         this.handshakeRequest.setMeta(new HashMap());
      }

      return this.handshakeRequest.getMeta();
   }

   void setRequestHandshakeMeta(Map newmeta) {
      this.handshakeRequest.setMeta(newmeta);
   }

   public Map responseHandshakeMeta() {
      if (this.handshakeResponse.getMeta() == null) {
         this.handshakeResponse.setMeta(new HashMap());
      }

      return this.handshakeResponse.getMeta();
   }

   void setResponseHandshakeMeta(Map newmeta) {
      this.handshakeResponse.setMeta(newmeta);
   }

   public Map requestCallMeta() {
      if (this.requestCallMeta == null) {
         this.requestCallMeta = new HashMap();
      }

      return this.requestCallMeta;
   }

   void setRequestCallMeta(Map newmeta) {
      this.requestCallMeta = newmeta;
   }

   public Map responseCallMeta() {
      if (this.responseCallMeta == null) {
         this.responseCallMeta = new HashMap();
      }

      return this.responseCallMeta;
   }

   void setResponseCallMeta(Map newmeta) {
      this.responseCallMeta = newmeta;
   }

   void setResponse(Object response) {
      this.response = response;
      this.error = null;
   }

   public Object response() {
      return this.response;
   }

   void setError(Exception error) {
      this.response = null;
      this.error = error;
   }

   public Exception error() {
      return this.error;
   }

   public boolean isError() {
      return this.error != null;
   }

   public void setMessage(Protocol.Message message) {
      this.message = message;
   }

   public Protocol.Message getMessage() {
      return this.message;
   }

   public void setRequestPayload(List payload) {
      this.requestPayload = payload;
   }

   public List getRequestPayload() {
      return this.requestPayload;
   }

   public List getResponsePayload() {
      return this.responsePayload;
   }

   public void setResponsePayload(List payload) {
      this.responsePayload = payload;
   }
}
