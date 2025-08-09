package org.apache.derby.impl.services.jce;

import java.util.Properties;
import org.apache.derby.iapi.services.crypto.CipherFactory;
import org.apache.derby.iapi.services.crypto.CipherFactoryBuilder;
import org.apache.derby.shared.common.error.StandardException;

public class JCECipherFactoryBuilder implements CipherFactoryBuilder {
   public CipherFactory createCipherFactory(boolean var1, Properties var2, boolean var3) throws StandardException {
      return new JCECipherFactory(var1, var2, var3);
   }
}
