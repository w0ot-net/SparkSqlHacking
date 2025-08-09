package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.thrift.TException;

public class RowSetFactory {
   public static RowSet create(TableSchema schema, TProtocolVersion version, boolean isBlobBased) {
      return (RowSet)(version.getValue() >= TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V6.getValue() ? new ColumnBasedSet(schema, isBlobBased) : new RowBasedSet(schema));
   }

   public static RowSet create(TRowSet results, TProtocolVersion version) throws TException {
      return (RowSet)(version.getValue() >= TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V6.getValue() ? new ColumnBasedSet(results) : new RowBasedSet(results));
   }
}
