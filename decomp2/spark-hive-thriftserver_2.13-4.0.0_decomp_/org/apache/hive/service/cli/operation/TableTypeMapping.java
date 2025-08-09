package org.apache.hive.service.cli.operation;

import java.util.Set;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;

@Public
@Evolving
public interface TableTypeMapping {
   String[] mapToHiveType(String var1);

   String mapToClientType(String var1);

   Set getTableTypeNames();
}
