package org.tukaani.xz;

import java.io.InputStream;

interface FilterDecoder extends FilterCoder {
   int getMemoryUsage();

   InputStream getInputStream(InputStream var1, ArrayCache var2);
}
