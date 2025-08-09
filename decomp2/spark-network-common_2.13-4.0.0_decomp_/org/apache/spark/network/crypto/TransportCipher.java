package org.apache.spark.network.crypto;

import io.netty.channel.Channel;
import java.io.IOException;
import java.security.GeneralSecurityException;

interface TransportCipher {
   String getKeyId() throws GeneralSecurityException;

   void addToChannel(Channel var1) throws IOException, GeneralSecurityException;
}
