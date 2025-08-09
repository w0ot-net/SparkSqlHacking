package org.apache.avro.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.util.ByteBufferInputStream;
import org.apache.avro.util.ByteBufferOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Requestor {
   private static final Logger LOG = LoggerFactory.getLogger(Requestor.class);
   private static final Schema META;
   private static final GenericDatumReader META_READER;
   private static final GenericDatumWriter META_WRITER;
   private final Protocol local;
   private volatile Protocol remote;
   private volatile boolean sendLocalText;
   private final Transceiver transceiver;
   private final ReentrantLock handshakeLock = new ReentrantLock();
   protected final List rpcMetaPlugins;
   private static final EncoderFactory ENCODER_FACTORY;
   private static final ConcurrentMap REMOTE_HASHES;
   private static final ConcurrentMap REMOTE_PROTOCOLS;
   private static final SpecificDatumWriter HANDSHAKE_WRITER;
   private static final SpecificDatumReader HANDSHAKE_READER;

   public Protocol getLocal() {
      return this.local;
   }

   public Transceiver getTransceiver() {
      return this.transceiver;
   }

   protected Requestor(Protocol local, Transceiver transceiver) throws IOException {
      this.local = local;
      this.transceiver = transceiver;
      this.rpcMetaPlugins = new CopyOnWriteArrayList();
   }

   public void addRPCPlugin(RPCPlugin plugin) {
      this.rpcMetaPlugins.add(plugin);
   }

   public Object request(String messageName, Object request) throws Exception {
      Request rpcRequest = new Request(messageName, request, new RPCContext());
      CallFuture<Object> future = rpcRequest.getMessage().isOneWay() ? null : new CallFuture();
      this.request((Request)rpcRequest, (Callback)future);
      if (future == null) {
         return null;
      } else {
         try {
            return future.get();
         } catch (ExecutionException e) {
            Throwable error = e.getCause();
            if (error instanceof Exception) {
               throw (Exception)error;
            } else {
               throw new AvroRuntimeException(error);
            }
         }
      }
   }

   public void request(String messageName, Object request, Callback callback) throws AvroRemoteException, IOException {
      this.request(new Request(messageName, request, new RPCContext()), callback);
   }

   void request(Request request, Callback callback) throws AvroRemoteException, IOException {
      Transceiver t = this.getTransceiver();
      if (!t.isConnected()) {
         this.handshakeLock.lock();

         try {
            if (!t.isConnected()) {
               CallFuture<T> callFuture = new CallFuture(callback);
               t.transceive(request.getBytes(), new TransceiverCallback(request, callFuture));

               try {
                  callFuture.await();
               } catch (InterruptedException var14) {
                  Thread.currentThread().interrupt();
               }

               if (!request.getMessage().isOneWay()) {
                  return;
               }

               Throwable error = callFuture.getError();
               if (error != null) {
                  if (error instanceof AvroRemoteException) {
                     throw (AvroRemoteException)error;
                  }

                  if (error instanceof AvroRuntimeException) {
                     throw (AvroRuntimeException)error;
                  }

                  if (error instanceof IOException) {
                     throw (IOException)error;
                  }

                  throw new AvroRuntimeException(error);
               }

               return;
            }

            this.handshakeLock.unlock();
         } finally {
            if (this.handshakeLock.isHeldByCurrentThread()) {
               this.handshakeLock.unlock();
            }

         }
      }

      if (request.getMessage().isOneWay()) {
         t.lockChannel();

         try {
            t.writeBuffers(request.getBytes());
            if (callback != null) {
               callback.handleResult((Object)null);
            }
         } finally {
            t.unlockChannel();
         }
      } else {
         t.transceive(request.getBytes(), new TransceiverCallback(request, callback));
      }

   }

   private void writeHandshake(Encoder out) throws IOException {
      if (!this.getTransceiver().isConnected()) {
         MD5 localHash = new MD5();
         localHash.bytes(this.local.getMD5());
         String remoteName = this.transceiver.getRemoteName();
         MD5 remoteHash = (MD5)REMOTE_HASHES.get(remoteName);
         if (remoteHash == null) {
            remoteHash = localHash;
            this.remote = this.local;
         } else {
            this.remote = (Protocol)REMOTE_PROTOCOLS.get(remoteHash);
         }

         HandshakeRequest handshake = new HandshakeRequest();
         handshake.setClientHash(localHash);
         handshake.setServerHash(remoteHash);
         if (this.sendLocalText) {
            handshake.setClientProtocol(this.local.toString());
         }

         RPCContext context = new RPCContext();
         context.setHandshakeRequest(handshake);

         for(RPCPlugin plugin : this.rpcMetaPlugins) {
            plugin.clientStartConnect(context);
         }

         handshake.setMeta(context.requestHandshakeMeta());
         HANDSHAKE_WRITER.write(handshake, out);
      }
   }

   private boolean readHandshake(Decoder in) throws IOException {
      if (this.getTransceiver().isConnected()) {
         return true;
      } else {
         boolean established = false;
         HandshakeResponse handshake = (HandshakeResponse)HANDSHAKE_READER.read((Object)null, in);
         switch (handshake.getMatch()) {
            case BOTH:
               established = true;
               this.sendLocalText = false;
               break;
            case CLIENT:
               LOG.debug("Handshake match = CLIENT");
               this.setRemote(handshake);
               established = true;
               this.sendLocalText = false;
               break;
            case NONE:
               LOG.debug("Handshake match = NONE");
               this.setRemote(handshake);
               this.sendLocalText = true;
               break;
            default:
               throw new AvroRuntimeException("Unexpected match: " + String.valueOf(handshake.getMatch()));
         }

         RPCContext context = new RPCContext();
         context.setHandshakeResponse(handshake);

         for(RPCPlugin plugin : this.rpcMetaPlugins) {
            plugin.clientFinishConnect(context);
         }

         if (established) {
            this.getTransceiver().setRemote(this.remote);
         }

         return established;
      }
   }

   private void setRemote(HandshakeResponse handshake) throws IOException {
      this.remote = Protocol.parse(handshake.getServerProtocol().toString());
      MD5 remoteHash = handshake.getServerHash();
      REMOTE_HASHES.put(this.transceiver.getRemoteName(), remoteHash);
      REMOTE_PROTOCOLS.putIfAbsent(remoteHash, this.remote);
   }

   public Protocol getRemote() throws IOException {
      if (this.remote != null) {
         return this.remote;
      } else {
         MD5 remoteHash = (MD5)REMOTE_HASHES.get(this.transceiver.getRemoteName());
         if (remoteHash != null) {
            this.remote = (Protocol)REMOTE_PROTOCOLS.get(remoteHash);
            if (this.remote != null) {
               return this.remote;
            }
         }

         this.handshakeLock.lock();

         Protocol var7;
         try {
            ByteBufferOutputStream bbo = new ByteBufferOutputStream();
            Encoder out = ENCODER_FACTORY.directBinaryEncoder(bbo, (BinaryEncoder)null);
            this.writeHandshake(out);
            out.writeInt(0);
            out.writeString("");
            List<ByteBuffer> response = this.getTransceiver().transceive(bbo.getBufferList());
            ByteBufferInputStream bbi = new ByteBufferInputStream(response);
            BinaryDecoder in = DecoderFactory.get().binaryDecoder(bbi, (BinaryDecoder)null);
            this.readHandshake(in);
            var7 = this.remote;
         } finally {
            this.handshakeLock.unlock();
         }

         return var7;
      }
   }

   public abstract void writeRequest(Schema schema, Object request, Encoder out) throws IOException;

   /** @deprecated */
   @Deprecated
   public Object readResponse(Schema schema, Decoder in) throws IOException {
      return this.readResponse(schema, schema, in);
   }

   public abstract Object readResponse(Schema writer, Schema reader, Decoder in) throws IOException;

   /** @deprecated */
   @Deprecated
   public Object readError(Schema schema, Decoder in) throws IOException {
      return this.readError(schema, schema, in);
   }

   public abstract Exception readError(Schema writer, Schema reader, Decoder in) throws IOException;

   static {
      META = Schema.createMap(Schema.create(Type.BYTES));
      META_READER = new GenericDatumReader(META);
      META_WRITER = new GenericDatumWriter(META);
      ENCODER_FACTORY = new EncoderFactory();
      REMOTE_HASHES = new ConcurrentHashMap();
      REMOTE_PROTOCOLS = new ConcurrentHashMap();
      HANDSHAKE_WRITER = new SpecificDatumWriter(HandshakeRequest.class);
      HANDSHAKE_READER = new SpecificDatumReader(HandshakeResponse.class);
   }

   protected class TransceiverCallback implements Callback {
      private final Request request;
      private final Callback callback;

      public TransceiverCallback(Request request, Callback callback) {
         this.request = request;
         this.callback = callback;
      }

      public void handleResult(List responseBytes) {
         ByteBufferInputStream bbi = new ByteBufferInputStream(responseBytes);
         BinaryDecoder in = DecoderFactory.get().binaryDecoder(bbi, (BinaryDecoder)null);

         try {
            if (!Requestor.this.readHandshake(in)) {
               Request handshake = Requestor.this.new Request(this.request);
               Requestor.this.getTransceiver().transceive(handshake.getBytes(), Requestor.this.new TransceiverCallback(handshake, this.callback));
               return;
            }
         } catch (Exception e) {
            Requestor.LOG.error("Error handling transceiver callback: " + String.valueOf(e), e);
         }

         Response response = Requestor.this.new Response(this.request, in);

         try {
            Object responseObject;
            try {
               responseObject = response.getResponse();
            } catch (Exception e) {
               if (this.callback != null) {
                  this.callback.handleError(e);
               }

               return;
            }

            if (this.callback != null) {
               this.callback.handleResult(responseObject);
            }
         } catch (Throwable t) {
            Requestor.LOG.error("Error in callback handler: " + String.valueOf(t), t);
         }

      }

      public void handleError(Throwable error) {
         this.callback.handleError(error);
      }
   }

   class Request {
      private final String messageName;
      private final Object request;
      private final RPCContext context;
      private final BinaryEncoder encoder;
      private Protocol.Message message;
      private List requestBytes;

      public Request(String messageName, Object request, RPCContext context) {
         this(messageName, request, context, (BinaryEncoder)null);
      }

      public Request(String messageName, Object request, RPCContext context, BinaryEncoder encoder) {
         this.messageName = messageName;
         this.request = request;
         this.context = context;
         this.encoder = Requestor.ENCODER_FACTORY.binaryEncoder(new ByteBufferOutputStream(), encoder);
      }

      public Request(Request other) {
         this.messageName = other.messageName;
         this.request = other.request;
         this.context = other.context;
         this.encoder = other.encoder;
      }

      public String getMessageName() {
         return this.messageName;
      }

      public RPCContext getContext() {
         return this.context;
      }

      public Protocol.Message getMessage() {
         if (this.message == null) {
            this.message = (Protocol.Message)Requestor.this.getLocal().getMessages().get(this.messageName);
            if (this.message == null) {
               throw new AvroRuntimeException("Not a local message: " + this.messageName);
            }
         }

         return this.message;
      }

      public List getBytes() throws IOException {
         if (this.requestBytes == null) {
            ByteBufferOutputStream bbo = new ByteBufferOutputStream();
            BinaryEncoder out = Requestor.ENCODER_FACTORY.binaryEncoder(bbo, this.encoder);
            Protocol.Message m = this.getMessage();
            this.context.setMessage(m);
            Requestor.this.writeRequest(m.getRequest(), this.request, out);
            out.flush();
            List<ByteBuffer> payload = bbo.getBufferList();
            Requestor.this.writeHandshake(out);
            this.context.setRequestPayload(payload);

            for(RPCPlugin plugin : Requestor.this.rpcMetaPlugins) {
               plugin.clientSendRequest(this.context);
            }

            Requestor.META_WRITER.write(this.context.requestCallMeta(), out);
            out.writeString(m.getName());
            out.flush();
            bbo.append(payload);
            this.requestBytes = bbo.getBufferList();
         }

         return this.requestBytes;
      }
   }

   class Response {
      private final Request request;
      private final BinaryDecoder in;

      public Response(Request request) {
         this(request, (BinaryDecoder)null);
      }

      public Response(Request request, BinaryDecoder in) {
         this.request = request;
         this.in = in;
      }

      public Object getResponse() throws Exception {
         Protocol.Message lm = this.request.getMessage();
         Protocol.Message rm = (Protocol.Message)Requestor.this.remote.getMessages().get(this.request.getMessageName());
         if (rm == null) {
            throw new AvroRuntimeException("Not a remote message: " + this.request.getMessageName());
         } else {
            Transceiver t = Requestor.this.getTransceiver();
            if (lm.isOneWay() != rm.isOneWay() && t.isConnected()) {
               throw new AvroRuntimeException("Not both one-way messages: " + this.request.getMessageName());
            } else if (lm.isOneWay() && t.isConnected()) {
               return null;
            } else {
               RPCContext context = this.request.getContext();
               context.setResponseCallMeta((Map)Requestor.META_READER.read((Object)null, this.in));
               if (!this.in.readBoolean()) {
                  Object response = Requestor.this.readResponse(rm.getResponse(), lm.getResponse(), this.in);
                  context.setResponse(response);

                  for(RPCPlugin plugin : Requestor.this.rpcMetaPlugins) {
                     plugin.clientReceiveResponse(context);
                  }

                  return response;
               } else {
                  Exception error = Requestor.this.readError(rm.getErrors(), lm.getErrors(), this.in);
                  context.setError(error);

                  for(RPCPlugin plugin : Requestor.this.rpcMetaPlugins) {
                     plugin.clientReceiveResponse(context);
                  }

                  throw error;
               }
            }
         }
      }
   }
}
