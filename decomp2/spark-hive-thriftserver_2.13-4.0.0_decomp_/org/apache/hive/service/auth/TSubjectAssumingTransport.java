package org.apache.hive.service.auth;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import javax.security.auth.Subject;
import org.apache.hadoop.hive.thrift.TFilterTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TSubjectAssumingTransport extends TFilterTransport {
   public TSubjectAssumingTransport(TTransport wrapped) {
      super(wrapped);
   }

   public void open() throws TTransportException {
      try {
         AccessControlContext context = AccessController.getContext();
         Subject subject = Subject.getSubject(context);
         Subject.doAs(subject, () -> {
            try {
               this.wrapped.open();
               return null;
            } catch (TTransportException tte) {
               throw new RuntimeException(tte);
            }
         });
      } catch (PrivilegedActionException ioe) {
         throw new RuntimeException("Received an ioe we never threw!", ioe);
      } catch (RuntimeException rte) {
         if (rte.getCause() instanceof TTransportException) {
            throw (TTransportException)rte.getCause();
         } else {
            throw rte;
         }
      }
   }
}
