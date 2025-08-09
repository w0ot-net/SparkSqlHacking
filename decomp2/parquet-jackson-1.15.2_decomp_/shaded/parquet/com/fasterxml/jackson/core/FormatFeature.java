package shaded.parquet.com.fasterxml.jackson.core;

import shaded.parquet.com.fasterxml.jackson.core.util.JacksonFeature;

public interface FormatFeature extends JacksonFeature {
   boolean enabledByDefault();

   int getMask();

   boolean enabledIn(int var1);
}
