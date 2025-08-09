package org.apache.zookeeper.server.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.server.ByteBufferInputStream;
import org.apache.zookeeper.server.NIOServerCnxn;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressFBWarnings(
   value = {"BC_UNCONFIRMED_CAST"},
   justification = "factory is ControllableConnectionFactory type."
)
public class ControllableConnection extends NIOServerCnxn {
   private static final Logger LOG = LoggerFactory.getLogger(ControllableConnection.class);
   private final ControllableConnectionFactory controller;

   public ControllableConnection(ZooKeeperServer zk, SocketChannel sock, SelectionKey sk, NIOServerCnxnFactory factory, NIOServerCnxnFactory.SelectorThread selectorThread) throws IOException {
      super(zk, sock, sk, factory, selectorThread);
      this.controller = (ControllableConnectionFactory)factory;
   }

   public int sendResponse(ReplyHeader header, Record record, String tag) {
      if (this.controller.shouldSendResponse()) {
         try {
            return super.sendResponse(header, record, tag);
         } catch (IOException ex) {
            LOG.warn("IO Exception occurred", ex);
         }
      } else {
         LOG.warn("Controller is configured to NOT sending response back to client.");
      }

      return -1;
   }

   protected void readRequest() throws IOException {
      if (this.controller.shouldFailNextRequest()) {
         ByteBuffer buffer = this.incomingBuffer.slice();
         BinaryInputArchive bia = BinaryInputArchive.getArchive(new ByteBufferInputStream(buffer));
         RequestHeader h = new RequestHeader();
         h.deserialize(bia, "header");
         super.sendResponse(new ReplyHeader(h.getXid(), 0L, KeeperException.Code.APIERROR.intValue()), (Record)null, (String)null);
      } else {
         this.controller.delayRequestIfNeeded();
         super.readRequest();
      }

   }
}
