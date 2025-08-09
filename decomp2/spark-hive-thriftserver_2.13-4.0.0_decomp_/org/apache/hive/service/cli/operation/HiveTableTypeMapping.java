package org.apache.hive.service.cli.operation;

import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.hive.metastore.TableType;

public class HiveTableTypeMapping implements TableTypeMapping {
   public String[] mapToHiveType(String clientTypeName) {
      return new String[]{this.mapToClientType(clientTypeName)};
   }

   public String mapToClientType(String hiveTypeName) {
      return hiveTypeName;
   }

   public Set getTableTypeNames() {
      Set<String> typeNameSet = new HashSet();

      for(TableType typeNames : TableType.values()) {
         typeNameSet.add(typeNames.name());
      }

      return typeNameSet;
   }
}
