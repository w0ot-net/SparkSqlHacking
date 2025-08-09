module org.apache.derby.tools {
   requires java.base;
   requires java.logging;
   requires java.sql;
   requires java.xml;
   requires org.apache.derby.commons;
   requires static org.apache.derby.engine;
   requires static org.apache.derby.client;
   requires static java.naming;

   exports org.apache.derby.jdbc;
   exports org.apache.derby.iapi.tools.i18n to
      org.apache.derby.server,
      org.apache.derby.optionaltools,
      org.apache.derby.runner,
      org.apache.derby.tests;
   exports org.apache.derby.impl.tools.ij to
      org.apache.derby.tests;
   exports org.apache.derby.impl.tools.planexporter to
      org.apache.derby.tests;
   exports org.apache.derby.impl.tools.sysinfo to
      org.apache.derby.server,
      org.apache.derby.tests;
   exports org.apache.derby.tools to
      org.apache.derby.optionaltools,
      org.apache.derby.runner,
      org.apache.derby.tests;

   opens org.apache.derby.loc.tools;
   opens org.apache.derby.info.tools;
   opens org.apache.derby.impl.tools.optional;

   provides org.apache.derby.shared.api.DerbyModuleAPI with
      org.apache.derby.info.tools.DerbyModule;
}
