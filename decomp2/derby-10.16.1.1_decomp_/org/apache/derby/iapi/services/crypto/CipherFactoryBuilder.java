package org.apache.derby.iapi.services.crypto;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface CipherFactoryBuilder {
   CipherFactory createCipherFactory(boolean var1, Properties var2, boolean var3) throws StandardException;
}
