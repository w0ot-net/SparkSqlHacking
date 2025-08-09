package org.apache.hadoop.hive.serde2.thrift;

import org.apache.hadoop.io.Text;
import org.apache.thrift.TException;

public interface WriteTextProtocol {
   void writeText(Text var1) throws TException;
}
