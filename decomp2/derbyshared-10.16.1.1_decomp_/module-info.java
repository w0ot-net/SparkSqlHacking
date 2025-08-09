module org.apache.derby.commons {
   requires java.base;
   requires java.sql;

   exports org.apache.derby.shared.api;
   exports org.apache.derby.shared.common.security;
   exports org.apache.derby.shared.common.error;
   exports org.apache.derby.shared.common.drda to
      org.apache.derby.client,
      org.apache.derby.server,
      org.apache.derby.optionaltools;
   exports org.apache.derby.shared.common.i18n to
      org.apache.derby.engine,
      org.apache.derby.client,
      org.apache.derby.server,
      org.apache.derby.tools,
      org.apache.derby.tests;
   exports org.apache.derby.shared.common.info to
      org.apache.derby.engine,
      org.apache.derby.client,
      org.apache.derby.server,
      org.apache.derby.tools,
      org.apache.derby.tests;
   exports org.apache.derby.shared.common.reference to
      org.apache.derby.engine,
      org.apache.derby.client,
      org.apache.derby.server,
      org.apache.derby.tools,
      org.apache.derby.optionaltools,
      org.apache.derby.tests;
   exports org.apache.derby.shared.common.sanity to
      org.apache.derby.engine,
      org.apache.derby.client,
      org.apache.derby.server,
      org.apache.derby.tests;
   exports org.apache.derby.shared.common.stream to
      org.apache.derby.engine,
      org.apache.derby.server,
      org.apache.derby.tests;
   exports org.apache.derby.shared.common.util to
      org.apache.derby.engine;

   uses org.apache.derby.shared.api.DerbyModuleAPI;

   provides org.apache.derby.shared.api.DerbyModuleAPI with
      org.apache.derby.info.shared.DerbyModule;
}
