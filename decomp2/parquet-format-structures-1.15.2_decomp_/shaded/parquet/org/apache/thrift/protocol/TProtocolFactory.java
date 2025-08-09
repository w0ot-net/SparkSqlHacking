package shaded.parquet.org.apache.thrift.protocol;

import java.io.Serializable;
import shaded.parquet.org.apache.thrift.transport.TTransport;

public interface TProtocolFactory extends Serializable {
   TProtocol getProtocol(TTransport var1);
}
