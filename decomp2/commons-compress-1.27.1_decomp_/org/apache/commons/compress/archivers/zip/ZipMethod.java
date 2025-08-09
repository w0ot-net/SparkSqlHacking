package org.apache.commons.compress.archivers.zip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ZipMethod {
   STORED(0),
   UNSHRINKING(1),
   EXPANDING_LEVEL_1(2),
   EXPANDING_LEVEL_2(3),
   EXPANDING_LEVEL_3(4),
   EXPANDING_LEVEL_4(5),
   IMPLODING(6),
   TOKENIZATION(7),
   DEFLATED(8),
   ENHANCED_DEFLATED(9),
   PKWARE_IMPLODING(10),
   BZIP2(12),
   LZMA(14),
   XZ(95),
   JPEG(96),
   WAVPACK(97),
   PPMD(98),
   AES_ENCRYPTED(99),
   UNKNOWN;

   static final int UNKNOWN_CODE = -1;
   private static final Map codeToEnum;
   private final int code;

   public static ZipMethod getMethodByCode(int code) {
      return (ZipMethod)codeToEnum.get(code);
   }

   private ZipMethod() {
      this(-1);
   }

   private ZipMethod(int code) {
      this.code = code;
   }

   public int getCode() {
      return this.code;
   }

   // $FF: synthetic method
   private static ZipMethod[] $values() {
      return new ZipMethod[]{STORED, UNSHRINKING, EXPANDING_LEVEL_1, EXPANDING_LEVEL_2, EXPANDING_LEVEL_3, EXPANDING_LEVEL_4, IMPLODING, TOKENIZATION, DEFLATED, ENHANCED_DEFLATED, PKWARE_IMPLODING, BZIP2, LZMA, XZ, JPEG, WAVPACK, PPMD, AES_ENCRYPTED, UNKNOWN};
   }

   static {
      Map<Integer, ZipMethod> cte = new HashMap();

      for(ZipMethod method : values()) {
         cte.put(method.getCode(), method);
      }

      codeToEnum = Collections.unmodifiableMap(cte);
   }
}
