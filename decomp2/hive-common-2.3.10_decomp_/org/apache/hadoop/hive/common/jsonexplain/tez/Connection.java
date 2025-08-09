package org.apache.hadoop.hive.common.jsonexplain.tez;

public final class Connection {
   public final String type;
   public final Vertex from;

   public Connection(String type, Vertex from) {
      this.type = type;
      this.from = from;
   }
}
