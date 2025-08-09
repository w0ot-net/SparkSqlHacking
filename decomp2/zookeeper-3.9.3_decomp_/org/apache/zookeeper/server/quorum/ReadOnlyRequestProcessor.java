package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.proto.ReplyHeader;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ZooKeeperCriticalThread;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadOnlyRequestProcessor extends ZooKeeperCriticalThread implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyRequestProcessor.class);
   private final LinkedBlockingQueue queuedRequests = new LinkedBlockingQueue();
   private volatile boolean finished = false;
   private final RequestProcessor nextProcessor;
   private final ZooKeeperServer zks;

   public ReadOnlyRequestProcessor(ZooKeeperServer zks, RequestProcessor nextProcessor) {
      super("ReadOnlyRequestProcessor:" + zks.getServerId(), zks.getZooKeeperServerListener());
      this.zks = zks;
      this.nextProcessor = nextProcessor;
   }

   public void run() {
      try {
         while(!this.finished) {
            Request request = (Request)this.queuedRequests.take();
            if (LOG.isTraceEnabled()) {
               long traceMask = 2L;
               if (request.type == 11) {
                  traceMask = 8L;
               }

               ZooTrace.logRequest(LOG, traceMask, 'R', request, "");
            }

            if (Request.requestOfDeath == request) {
               break;
            }

            switch (request.type) {
               case -11:
               case -10:
                  if (!request.isLocalSession()) {
                     this.sendErrorResponse(request);
                     break;
                  }
               case -9:
               case -8:
               case -7:
               case -6:
               case -5:
               case -4:
               case -3:
               case -2:
               case -1:
               case 0:
               case 3:
               case 4:
               case 6:
               case 8:
               case 10:
               case 11:
               case 12:
               case 17:
               case 18:
               default:
                  if (this.nextProcessor != null) {
                     this.nextProcessor.processRequest(request);
                  }
                  break;
               case 1:
               case 2:
               case 5:
               case 7:
               case 9:
               case 13:
               case 14:
               case 15:
               case 16:
               case 19:
               case 20:
               case 21:
                  this.sendErrorResponse(request);
            }
         }
      } catch (Exception e) {
         this.handleException(this.getName(), e);
      }

      LOG.info("ReadOnlyRequestProcessor exited loop!");
   }

   private void sendErrorResponse(Request request) {
      ReplyHeader hdr = new ReplyHeader(request.cxid, this.zks.getZKDatabase().getDataTreeLastProcessedZxid(), KeeperException.Code.NOTREADONLY.intValue());

      try {
         request.cnxn.sendResponse(hdr, (Record)null, (String)null);
      } catch (IOException e) {
         LOG.error("IO exception while sending response", e);
      }

   }

   public void processRequest(Request request) {
      if (!this.finished) {
         this.queuedRequests.add(request);
      }

   }

   public void shutdown() {
      this.finished = true;
      this.queuedRequests.clear();
      this.queuedRequests.add(Request.requestOfDeath);
      this.nextProcessor.shutdown();
   }
}
