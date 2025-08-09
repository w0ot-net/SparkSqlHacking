package org.apache.hive.service.cli.operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.TABLE_TYPE.;
import org.sparkproject.guava.collect.ArrayListMultimap;
import org.sparkproject.guava.collect.Iterables;
import org.sparkproject.guava.collect.Multimap;

public class ClassicTableTypeMapping implements TableTypeMapping {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(ClassicTableTypeMapping.class);
   private final Map hiveToClientMap = new HashMap();
   private final Multimap clientToHiveMap = ArrayListMultimap.create();

   public ClassicTableTypeMapping() {
      this.hiveToClientMap.put(TableType.MANAGED_TABLE.name(), ClassicTableTypeMapping.ClassicTableTypes.TABLE.name());
      this.hiveToClientMap.put(TableType.EXTERNAL_TABLE.name(), ClassicTableTypeMapping.ClassicTableTypes.TABLE.name());
      this.hiveToClientMap.put(TableType.VIRTUAL_VIEW.name(), ClassicTableTypeMapping.ClassicTableTypes.VIEW.name());
      this.hiveToClientMap.put(TableType.MATERIALIZED_VIEW.toString(), ClassicTableTypeMapping.ClassicTableTypes.MATERIALIZED_VIEW.toString());
      this.clientToHiveMap.putAll(ClassicTableTypeMapping.ClassicTableTypes.TABLE.name(), Arrays.asList(TableType.MANAGED_TABLE.name(), TableType.EXTERNAL_TABLE.name()));
      this.clientToHiveMap.put(ClassicTableTypeMapping.ClassicTableTypes.VIEW.name(), TableType.VIRTUAL_VIEW.name());
      this.clientToHiveMap.put(ClassicTableTypeMapping.ClassicTableTypes.MATERIALIZED_VIEW.toString(), TableType.MATERIALIZED_VIEW.toString());
   }

   public String[] mapToHiveType(String clientTypeName) {
      Collection<String> hiveTableType = this.clientToHiveMap.get(clientTypeName.toUpperCase());
      if (hiveTableType == null) {
         LOG.warn("Not supported client table type {}", new MDC[]{MDC.of(.MODULE$, clientTypeName)});
         return new String[]{clientTypeName};
      } else {
         return (String[])Iterables.toArray(hiveTableType, String.class);
      }
   }

   public String mapToClientType(String hiveTypeName) {
      String clientTypeName = (String)this.hiveToClientMap.get(hiveTypeName);
      if (clientTypeName == null) {
         LOG.warn("Invalid hive table type {}", new MDC[]{MDC.of(.MODULE$, hiveTypeName)});
         return hiveTypeName;
      } else {
         return clientTypeName;
      }
   }

   public Set getTableTypeNames() {
      Set<String> typeNameSet = new HashSet();

      for(ClassicTableTypes typeNames : ClassicTableTypeMapping.ClassicTableTypes.values()) {
         typeNameSet.add(typeNames.name());
      }

      return typeNameSet;
   }

   public static enum ClassicTableTypes {
      TABLE,
      VIEW,
      MATERIALIZED_VIEW;

      // $FF: synthetic method
      private static ClassicTableTypes[] $values() {
         return new ClassicTableTypes[]{TABLE, VIEW, MATERIALIZED_VIEW};
      }
   }
}
