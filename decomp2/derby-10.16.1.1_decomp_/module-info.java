module org.apache.derby.engine {
   requires java.base;
   requires java.logging;
   requires java.management;
   requires java.sql;
   requires java.xml;
   requires org.apache.derby.commons;
   requires static java.naming;
   requires static org.osgi.framework;

   exports org.apache.derby.agg;
   exports org.apache.derby.authentication;
   exports org.apache.derby.catalog;
   exports org.apache.derby.vti;
   exports org.apache.derby.security;
   exports org.apache.derby.diag;
   exports org.apache.derby.iapi.db;
   exports org.apache.derby.iapi.services.io;
   exports org.apache.derby.iapi.services.loader;
   exports org.apache.derby.iapi.sql;
   exports org.apache.derby.iapi.sql.conn;
   exports org.apache.derby.iapi.sql.execute;
   exports org.apache.derby.iapi.store.access;
   exports org.apache.derby.iapi.types;
   exports org.apache.derby.iapi.util;
   exports org.apache.derby.impl.sql.execute;
   exports org.apache.derby.impl.load;
   exports org.apache.derby.impl.jdbc;
   exports org.apache.derby.catalog.types to
      org.apache.derby.tests;
   exports org.apache.derby.database to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.jdbc to
      org.apache.derby.server,
      org.apache.derby.tools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.cache to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.context to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.crypto to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.daemon to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.diag to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.jmx to
      org.apache.derby.server;
   exports org.apache.derby.iapi.services.locks to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.monitor to
      org.apache.derby.server,
      org.apache.derby.tools,
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.property to
      org.apache.derby.server,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.services.uuid to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.sql.compile to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.sql.depend to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.sql.dictionary to
      org.apache.derby.tools,
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.access.conglomerate to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.access.xa to
      org.apache.derby.server,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.raw to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.raw.data to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.raw.log to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.store.raw.xact to
      org.apache.derby.tests;
   exports org.apache.derby.iapi.transaction to
      org.apache.derby.server;
   exports org.apache.derby.impl.io to
      org.apache.derby.tests;
   exports org.apache.derby.impl.io.vfmem to
      org.apache.derby.tests;
   exports org.apache.derby.impl.jdbc.authentication to
      org.apache.derby.optionaltools;
   exports org.apache.derby.impl.services.jce to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.impl.sql to
      org.apache.derby.tests;
   exports org.apache.derby.impl.sql.catalog to
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.access.btree to
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.access.btree.index to
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.access.conglomerate to
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.access.heap to
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.raw.data to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.impl.store.raw.log to
      org.apache.derby.tests;
   exports org.apache.derby.io to
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.mbeans to
      java.management,
      org.apache.derby.server;

   opens org.apache.derby;
   opens org.apache.derby.loc;
   opens org.apache.derby.info.engine;
   opens org.apache.derby.impl.jdbc to
      org.apache.derby.commons;
   opens org.apache.derby.mbeans;

   provides org.apache.derby.shared.api.DerbyModuleAPI with
      org.apache.derby.info.engine.DerbyModule;
   provides java.sql.Driver with
      org.apache.derby.iapi.jdbc.AutoloadedDriver;
}
