package org.apache.derby.shared.common.reference;

public interface Module {
   String CacheFactory = "org.apache.derby.iapi.services.cache.CacheFactory";
   String CipherFactoryBuilder = "org.apache.derby.iapi.services.crypto.CipherFactoryBuilder";
   String ClassFactory = "org.apache.derby.iapi.services.loader.ClassFactory";
   String DaemonFactory = "org.apache.derby.iapi.services.daemon.DaemonFactory";
   String JavaFactory = "org.apache.derby.iapi.services.compiler.JavaFactory";
   String LockFactory = "org.apache.derby.iapi.services.locks.LockFactory";
   String PropertyFactory = "org.apache.derby.iapi.services.property.PropertyFactory";
   String ResourceAdapter = "org.apache.derby.iapi.jdbc.ResourceAdapter";
   String JMX = "org.apache.derby.iapi.services.jmx.ManagementService";
}
