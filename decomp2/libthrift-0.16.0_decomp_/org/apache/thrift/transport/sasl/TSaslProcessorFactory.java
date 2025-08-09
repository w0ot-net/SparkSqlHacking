package org.apache.thrift.transport.sasl;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;

public interface TSaslProcessorFactory {
   TProcessor getProcessor(NonblockingSaslHandler var1) throws TException;
}
