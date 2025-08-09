package org.apache.spark.network.sasl;

import javax.security.sasl.SaslException;

interface SaslEncryptionBackend {
   void dispose();

   byte[] wrap(byte[] var1, int var2, int var3) throws SaslException;

   byte[] unwrap(byte[] var1, int var2, int var3) throws SaslException;
}
