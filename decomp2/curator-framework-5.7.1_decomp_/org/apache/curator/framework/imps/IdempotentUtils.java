package org.apache.curator.framework.imps;

import java.util.Arrays;

class IdempotentUtils {
   static boolean matches(int expectedVersion, byte[] expectedData, int actualVersion, byte[] actualData) {
      return expectedVersion == actualVersion && Arrays.equals(expectedData, actualData);
   }
}
