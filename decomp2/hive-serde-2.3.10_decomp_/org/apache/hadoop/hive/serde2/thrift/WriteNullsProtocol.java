package org.apache.hadoop.hive.serde2.thrift;

import org.apache.thrift.TException;

public interface WriteNullsProtocol {
   boolean lastPrimitiveWasNull() throws TException;

   void writeNull() throws TException;
}
