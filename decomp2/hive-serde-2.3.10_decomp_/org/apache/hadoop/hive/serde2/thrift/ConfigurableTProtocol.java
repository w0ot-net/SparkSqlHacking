package org.apache.hadoop.hive.serde2.thrift;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.thrift.TException;

public interface ConfigurableTProtocol {
   void initialize(Configuration var1, Properties var2) throws TException;
}
