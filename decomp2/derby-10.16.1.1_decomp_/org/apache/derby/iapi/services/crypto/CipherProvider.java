package org.apache.derby.iapi.services.crypto;

import org.apache.derby.shared.common.error.StandardException;

public interface CipherProvider {
   int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException;

   int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException;

   int getEncryptionBlockSize();
}
