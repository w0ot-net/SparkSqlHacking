package org.apache.avro;

import java.util.ServiceLoader;

class SchemaFormatterCache {
   static final ServiceLoader LOADER = ServiceLoader.load(SchemaFormatterFactory.class);
}
