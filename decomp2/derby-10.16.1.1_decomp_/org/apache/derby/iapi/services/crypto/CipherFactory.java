package org.apache.derby.iapi.services.crypto;

import java.security.SecureRandom;
import java.util.Properties;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface CipherFactory {
   int MIN_BOOTPASS_LENGTH = 8;
   int ENCRYPT = 1;
   int DECRYPT = 2;

   SecureRandom getSecureRandom();

   CipherProvider createNewCipher(int var1) throws StandardException;

   String changeBootPassword(String var1, Properties var2, CipherProvider var3) throws StandardException;

   void verifyKey(boolean var1, StorageFactory var2, Properties var3) throws StandardException;

   void saveProperties(Properties var1);
}
