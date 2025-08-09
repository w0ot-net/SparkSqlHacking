package org.apache.thrift.transport;

public class TEOFException extends TTransportException {
   public TEOFException(String message) {
      super(4, (String)message);
   }
}
