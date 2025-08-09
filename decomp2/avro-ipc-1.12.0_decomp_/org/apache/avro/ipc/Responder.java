package org.apache.avro.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.UnresolvedUnionException;
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
import org.apache.avro.util.Utf8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Responder {
   private static final Logger LOG = LoggerFactory.getLogger(Responder.class);
   private static final Schema META;
   private static final GenericDatumReader META_READER;
   private static final GenericDatumWriter META_WRITER;
   private static final ThreadLocal REMOTE;
   private final Map protocols = new ConcurrentHashMap();
   private final Protocol local;
   private final MD5 localHash;
   protected final List rpcMetaPlugins;
   private SpecificDatumWriter handshakeWriter = new SpecificDatumWriter(HandshakeResponse.class);
   private SpecificDatumReader handshakeReader = new SpecificDatumReader(HandshakeRequest.class);

   protected Responder(Protocol local) {
      this.local = local;
      this.localHash = new MD5();
      this.localHash.bytes(local.getMD5());
      this.protocols.put(this.localHash, local);
      this.rpcMetaPlugins = new CopyOnWriteArrayList();
   }

   public static Protocol getRemote() {
      return (Protocol)REMOTE.get();
   }

   public Protocol getLocal() {
      return this.local;
   }

   public void addRPCPlugin(RPCPlugin plugin) {
      this.rpcMetaPlugins.add(plugin);
   }

   public List respond(List buffers) throws IOException {
      return this.respond((List)buffers, (Transceiver)null);
   }

   public List respond(List buffers, Transceiver connection) throws IOException {
      Decoder in = DecoderFactory.get().binaryDecoder(new ByteBufferInputStream(buffers), (BinaryDecoder)null);
      ByteBufferOutputStream bbo = new ByteBufferOutputStream();
      BinaryEncoder out = EncoderFactory.get().binaryEncoder(bbo, (BinaryEncoder)null);
      Exception error = null;
      RPCContext context = new RPCContext();
      List<ByteBuffer> payload = null;
      List<ByteBuffer> handshake = null;
      boolean wasConnected = connection != null && connection.isConnected();

      try {
         Protocol remote = this.handshake(in, out, connection);
         out.flush();
         if (remote == null) {
            return bbo.getBufferList();
         }

         handshake = bbo.getBufferList();
         context.setRequestCallMeta((Map)META_READER.read((Object)null, in));
         String messageName = in.readString((Utf8)null).toString();
         if (messageName.equals("")) {
            return handshake;
         }

         Protocol.Message rm = (Protocol.Message)remote.getMessages().get(messageName);
         if (rm == null) {
            throw new AvroRuntimeException("No such remote message: " + messageName);
         }

         Protocol.Message m = (Protocol.Message)this.getLocal().getMessages().get(messageName);
         if (m == null) {
            throw new AvroRuntimeException("No message named " + messageName + " in " + String.valueOf(this.getLocal()));
         }

         Object request = this.readRequest(rm.getRequest(), m.getRequest(), in);
         context.setMessage(rm);

         for(RPCPlugin plugin : this.rpcMetaPlugins) {
            plugin.serverReceiveRequest(context);
         }

         if (m.isOneWay() != rm.isOneWay() && wasConnected) {
            throw new AvroRuntimeException("Not both one-way: " + messageName);
         }

         Object response = null;

         try {
            REMOTE.set(remote);
            response = this.respond(m, request);
            context.setResponse(response);
         } catch (Exception e) {
            error = e;
            context.setError(e);
            LOG.warn("user error", e);
         } finally {
            REMOTE.set((Object)null);
         }

         if (m.isOneWay() && wasConnected) {
            return null;
         }

         out.writeBoolean(error != null);
         if (error == null) {
            this.writeResponse(m.getResponse(), response, out);
         } else {
            try {
               this.writeError(m.getErrors(), error, out);
            } catch (UnresolvedUnionException var23) {
               throw error;
            }
         }
      } catch (Exception e) {
         LOG.warn("system error", e);
         context.setError(e);
         bbo = new ByteBufferOutputStream();
         out = EncoderFactory.get().binaryEncoder(bbo, (BinaryEncoder)null);
         out.writeBoolean(true);
         this.writeError(Protocol.SYSTEM_ERRORS, new Utf8(e.toString()), out);
         if (null == handshake) {
            handshake = (new ByteBufferOutputStream()).getBufferList();
         }
      }

      out.flush();
      payload = bbo.getBufferList();
      context.setResponsePayload(payload);

      for(RPCPlugin plugin : this.rpcMetaPlugins) {
         plugin.serverSendResponse(context);
      }

      META_WRITER.write(context.responseCallMeta(), out);
      out.flush();
      bbo.prepend(handshake);
      bbo.append(payload);
      return bbo.getBufferList();
   }

   private Protocol handshake(Decoder in, Encoder out, Transceiver connection) throws IOException {
      if (connection != null && connection.isConnected()) {
         return connection.getRemote();
      } else {
         HandshakeRequest request = (HandshakeRequest)this.handshakeReader.read((Object)null, in);
         Protocol remote = (Protocol)this.protocols.get(request.getClientHash());
         if (remote == null && request.getClientProtocol() != null) {
            remote = Protocol.parse(request.getClientProtocol().toString());
            this.protocols.put(request.getClientHash(), remote);
         }

         HandshakeResponse response = new HandshakeResponse();
         if (this.localHash.equals(request.getServerHash())) {
            response.setMatch(remote == null ? HandshakeMatch.NONE : HandshakeMatch.BOTH);
         } else {
            response.setMatch(remote == null ? HandshakeMatch.NONE : HandshakeMatch.CLIENT);
         }

         if (response.getMatch() != HandshakeMatch.BOTH) {
            response.setServerProtocol(this.local.toString());
            response.setServerHash(this.localHash);
         }

         RPCContext context = new RPCContext();
         context.setHandshakeRequest(request);
         context.setHandshakeResponse(response);

         for(RPCPlugin plugin : this.rpcMetaPlugins) {
            plugin.serverConnecting(context);
         }

         this.handshakeWriter.write(response, out);
         if (connection != null && response.getMatch() != HandshakeMatch.NONE) {
            connection.setRemote(remote);
         }

         return remote;
      }
   }

   public abstract Object respond(Protocol.Message message, Object request) throws Exception;

   public abstract Object readRequest(Schema actual, Schema expected, Decoder in) throws IOException;

   public abstract void writeResponse(Schema schema, Object response, Encoder out) throws IOException;

   public abstract void writeError(Schema schema, Object error, Encoder out) throws IOException;

   static {
      META = Schema.createMap(Schema.create(Type.BYTES));
      META_READER = new GenericDatumReader(META);
      META_WRITER = new GenericDatumWriter(META);
      REMOTE = new ThreadLocal();
   }
}
