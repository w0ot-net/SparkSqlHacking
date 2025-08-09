package org.apache.hadoop.hive.metastore;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore;
import org.apache.hadoop.hive.thrift.TUGIContainingTransport;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.thrift.ProcessFunction;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TUGIBasedProcessor extends TSetIpAddressProcessor {
   private final ThriftHiveMetastore.Iface iface;
   private final Map functions;
   static final Logger LOG = LoggerFactory.getLogger(TUGIBasedProcessor.class);

   public TUGIBasedProcessor(ThriftHiveMetastore.Iface iface) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      super(iface);
      this.iface = iface;
      this.functions = this.getProcessMapView();
   }

   public void process(final TProtocol in, final TProtocol out) throws TException {
      this.setIpAddress(in);
      final TMessage msg = in.readMessageBegin();
      final ProcessFunction<ThriftHiveMetastore.Iface, ? extends TBase> fn = (ProcessFunction)this.functions.get(msg.name);
      if (fn == null) {
         TProtocolUtil.skip(in, (byte)12);
         in.readMessageEnd();
         TApplicationException x = new TApplicationException(1, "Invalid method name: '" + msg.name + "'");
         out.writeMessageBegin(new TMessage(msg.name, (byte)3, msg.seqid));
         x.write(out);
         out.writeMessageEnd();
         out.getTransport().flush();
      } else {
         TUGIContainingTransport ugiTrans = (TUGIContainingTransport)in.getTransport();
         if (msg.name.equalsIgnoreCase("set_ugi")) {
            try {
               this.handleSetUGI(ugiTrans, (ThriftHiveMetastore.Processor.set_ugi)fn, msg, in, out);
            } catch (TException e) {
               throw e;
            } catch (Exception e) {
               throw new TException(e.getCause());
            }
         } else {
            UserGroupInformation clientUgi = ugiTrans.getClientUGI();
            if (null == clientUgi) {
               fn.process(msg.seqid, in, out, this.iface);
            } else {
               PrivilegedExceptionAction<Void> pvea = new PrivilegedExceptionAction() {
                  public Void run() {
                     try {
                        fn.process(msg.seqid, in, out, TUGIBasedProcessor.this.iface);
                        return null;
                     } catch (TException te) {
                        throw new RuntimeException(te);
                     }
                  }
               };

               try {
                  clientUgi.doAs(pvea);
               } catch (RuntimeException rte) {
                  if (rte.getCause() instanceof TException) {
                     throw (TException)rte.getCause();
                  }

                  throw rte;
               } catch (InterruptedException ie) {
                  throw new RuntimeException(ie);
               } catch (IOException ioe) {
                  throw new RuntimeException(ioe);
               } finally {
                  try {
                     FileSystem.closeAllForUGI(clientUgi);
                  } catch (IOException e) {
                     LOG.error("Could not clean up file-system handles for UGI: " + clientUgi, e);
                  }

               }

            }
         }
      }
   }

   private void handleSetUGI(TUGIContainingTransport ugiTrans, ThriftHiveMetastore.Processor.set_ugi fn, TMessage msg, TProtocol iprot, TProtocol oprot) throws TException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
      UserGroupInformation clientUgi = ugiTrans.getClientUGI();
      if (null != clientUgi) {
         throw new TException(new IllegalStateException("UGI is already set. Resetting is not allowed. Current ugi is: " + clientUgi.getUserName()));
      } else {
         ThriftHiveMetastore.set_ugi_args args = fn.getEmptyArgsInstance();

         try {
            args.read(iprot);
         } catch (TProtocolException e) {
            iprot.readMessageEnd();
            TApplicationException x = new TApplicationException(7, e.getMessage());
            oprot.writeMessageBegin(new TMessage(msg.name, (byte)3, msg.seqid));
            x.write(oprot);
            oprot.writeMessageEnd();
            oprot.getTransport().flush();
            return;
         }

         iprot.readMessageEnd();
         ThriftHiveMetastore.set_ugi_result result = fn.getResult(this.iface, args);
         List<String> principals = result.getSuccess();
         ugiTrans.setClientUGI(UserGroupInformation.createRemoteUser((String)principals.remove(principals.size() - 1)));
         oprot.writeMessageBegin(new TMessage(msg.name, (byte)2, msg.seqid));
         result.write(oprot);
         oprot.writeMessageEnd();
         oprot.getTransport().flush();
      }
   }

   protected void setIpAddress(TProtocol in) {
      TUGIContainingTransport ugiTrans = (TUGIContainingTransport)in.getTransport();
      Socket socket = ugiTrans.getSocket();
      if (socket != null) {
         this.setIpAddress(socket);
      }

   }
}
