package org.apache.hadoop.hive.metastore;

public enum TableType {
   MANAGED_TABLE,
   EXTERNAL_TABLE,
   VIRTUAL_VIEW,
   INDEX_TABLE,
   MATERIALIZED_VIEW;
}
