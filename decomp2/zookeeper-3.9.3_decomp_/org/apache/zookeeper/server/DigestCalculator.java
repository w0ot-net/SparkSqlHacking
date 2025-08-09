package org.apache.zookeeper.server;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import org.apache.zookeeper.data.StatPersisted;

public class DigestCalculator {
   private static final int DIGEST_VERSION = 2;

   long calculateDigest(String path, byte[] data, StatPersisted stat) {
      if (!ZooKeeperServer.isDigestEnabled()) {
         return 0L;
      } else if (path.startsWith("/zookeeper/")) {
         return 0L;
      } else {
         if (path.equals("/")) {
            path = "";
         }

         byte[] b = new byte[60];
         ByteBuffer bb = ByteBuffer.wrap(b);
         bb.putLong(stat.getCzxid());
         bb.putLong(stat.getMzxid());
         bb.putLong(stat.getPzxid());
         bb.putLong(stat.getCtime());
         bb.putLong(stat.getMtime());
         bb.putInt(stat.getVersion());
         bb.putInt(stat.getCversion());
         bb.putInt(stat.getAversion());
         bb.putLong(stat.getEphemeralOwner());
         CRC32 crc = new CRC32();
         crc.update(path.getBytes());
         if (data != null) {
            crc.update(data);
         }

         crc.update(b);
         return crc.getValue();
      }
   }

   long calculateDigest(String path, DataNode node) {
      if (!node.isDigestCached()) {
         node.setDigest(this.calculateDigest(path, node.getData(), node.stat));
         node.setDigestCached(true);
      }

      return node.getDigest();
   }

   int getDigestVersion() {
      return 2;
   }
}
