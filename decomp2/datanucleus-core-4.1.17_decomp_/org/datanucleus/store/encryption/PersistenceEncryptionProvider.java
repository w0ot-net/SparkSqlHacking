package org.datanucleus.store.encryption;

import org.datanucleus.metadata.AbstractMemberMetaData;

public interface PersistenceEncryptionProvider {
   Object encryptValue(AbstractMemberMetaData var1, Object var2);

   Object decryptValue(AbstractMemberMetaData var1, Object var2);
}
