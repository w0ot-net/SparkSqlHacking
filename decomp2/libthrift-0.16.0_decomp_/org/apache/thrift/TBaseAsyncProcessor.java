package org.apache.thrift;

import java.util.Collections;
import java.util.Map;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.server.AbstractNonblockingServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TBaseAsyncProcessor implements TAsyncProcessor, TProcessor {
   protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
   final Object iface;
   final Map processMap;

   public TBaseAsyncProcessor(Object iface, Map processMap) {
      this.iface = iface;
      this.processMap = processMap;
   }

   public Map getProcessMapView() {
      return Collections.unmodifiableMap(this.processMap);
   }

   public void process(AbstractNonblockingServer.AsyncFrameBuffer fb) throws TException {
      TProtocol in = fb.getInputProtocol();
      TProtocol out = fb.getOutputProtocol();
      TMessage msg = in.readMessageBegin();
      AsyncProcessFunction fn = (AsyncProcessFunction)this.processMap.get(msg.name);
      if (fn == null) {
         TProtocolUtil.skip(in, (byte)12);
         in.readMessageEnd();
         TApplicationException x = new TApplicationException(1, "Invalid method name: '" + msg.name + "'");
         this.LOGGER.debug("Invalid method name", x);
         if (msg.type == 1) {
            out.writeMessageBegin(new TMessage(msg.name, (byte)3, msg.seqid));
            x.write(out);
            out.writeMessageEnd();
            out.getTransport().flush();
         }

         fb.responseReady();
      } else {
         TBase args = fn.getEmptyArgsInstance();

         try {
            args.read(in);
         } catch (TProtocolException e) {
            in.readMessageEnd();
            TApplicationException x = new TApplicationException(7, e.getMessage());
            this.LOGGER.debug("Could not retrieve function arguments", x);
            if (!fn.isOneway()) {
               out.writeMessageBegin(new TMessage(msg.name, (byte)3, msg.seqid));
               x.write(out);
               out.writeMessageEnd();
               out.getTransport().flush();
            }

            fb.responseReady();
            return;
         }

         in.readMessageEnd();
         if (fn.isOneway()) {
            fb.responseReady();
         }

         AsyncMethodCallback resultHandler = fn.getResultHandler(fb, msg.seqid);

         try {
            fn.start(this.iface, args, resultHandler);
         } catch (Exception e) {
            this.LOGGER.debug("Exception handling function", e);
            resultHandler.onError(e);
         }

      }
   }

   public void process(TProtocol in, TProtocol out) throws TException {
   }
}
