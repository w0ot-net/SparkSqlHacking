package org.apache.derby.iapi.store.access.conglomerate;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;

public interface MethodFactory extends ModuleSupportable {
   String MODULE = "org.apache.derby.iapi.store.access.conglomerate.MethodFactory";

   Properties defaultProperties();

   boolean supportsImplementation(String var1);

   String primaryImplementationType();

   boolean supportsFormat(UUID var1);

   UUID primaryFormat();
}
