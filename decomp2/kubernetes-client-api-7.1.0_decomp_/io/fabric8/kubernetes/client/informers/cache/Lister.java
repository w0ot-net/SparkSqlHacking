package io.fabric8.kubernetes.client.informers.cache;

import java.util.List;

public class Lister {
   private final String namespace;
   private final String indexName;
   private final Indexer indexer;

   public Lister(Indexer indexer) {
      this(indexer, (String)null, "namespace");
   }

   public Lister(Indexer indexer, String namespace) {
      this(indexer, namespace, "namespace");
   }

   public Lister(Indexer indexer, String namespace, String indexName) {
      this.indexer = indexer;
      this.namespace = namespace;
      this.indexName = indexName;
   }

   public List list() {
      return this.namespace != null && !this.namespace.isEmpty() ? this.indexer.byIndex(this.indexName, this.namespace) : this.indexer.list();
   }

   public Object get(String name) {
      String key = name;
      if (this.namespace != null && !this.namespace.isEmpty()) {
         key = this.namespace + "/" + name;
      }

      return this.indexer.getByKey(key);
   }

   public Lister namespace(String namespace) {
      return new Lister(this.indexer, namespace, "namespace");
   }
}
