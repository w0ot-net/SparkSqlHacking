package org.apache.thrift;

import java.util.Collections;
import java.util.Map;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;

public abstract class TBaseProcessor implements TProcessor {
   private final Object iface;
   private final Map processMap;

   protected TBaseProcessor(Object iface, Map processFunctionMap) {
      this.iface = iface;
      this.processMap = processFunctionMap;
   }

   public Map getProcessMapView() {
      return Collections.unmodifiableMap(this.processMap);
   }

   public void process(TProtocol in, TProtocol out) throws TException {
      TMessage msg = in.readMessageBegin();
      ProcessFunction fn = (ProcessFunction)this.processMap.get(msg.name);
      if (fn == null) {
         TProtocolUtil.skip(in, (byte)12);
         in.readMessageEnd();
         TApplicationException x = new TApplicationException(1, "Invalid method name: '" + msg.name + "'");
         out.writeMessageBegin(new TMessage(msg.name, (byte)3, msg.seqid));
         x.write(out);
         out.writeMessageEnd();
         out.getTransport().flush();
      } else {
         fn.process(msg.seqid, in, out, this.iface);
      }

   }
}
