package org.apache.commons.compress.archivers.zip;

public class X0014_X509Certificates extends PKWareExtraHeader {
   static final ZipShort HEADER_ID = new ZipShort(20);

   public X0014_X509Certificates() {
      super(HEADER_ID);
   }
}
