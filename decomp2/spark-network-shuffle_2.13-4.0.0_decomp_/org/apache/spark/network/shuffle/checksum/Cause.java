package org.apache.spark.network.shuffle.checksum;

public enum Cause {
   DISK_ISSUE,
   NETWORK_ISSUE,
   UNKNOWN_ISSUE,
   CHECKSUM_VERIFY_PASS,
   UNSUPPORTED_CHECKSUM_ALGORITHM;

   // $FF: synthetic method
   private static Cause[] $values() {
      return new Cause[]{DISK_ISSUE, NETWORK_ISSUE, UNKNOWN_ISSUE, CHECKSUM_VERIFY_PASS, UNSUPPORTED_CHECKSUM_ALGORITHM};
   }
}
