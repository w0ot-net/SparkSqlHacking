package org.apache.thrift.transport.sasl;

public class DataFrameReader extends FrameReader {
   public DataFrameReader() {
      super(new DataFrameHeaderReader());
   }
}
