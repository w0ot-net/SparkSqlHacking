package org.apache.hadoop.hive.serde2.thrift;

import org.apache.thrift.TException;

public interface SkippableTProtocol {
   void skip(byte var1) throws TException;
}
