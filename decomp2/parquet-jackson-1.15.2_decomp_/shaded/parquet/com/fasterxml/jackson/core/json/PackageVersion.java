package shaded.parquet.com.fasterxml.jackson.core.json;

import shaded.parquet.com.fasterxml.jackson.core.Version;
import shaded.parquet.com.fasterxml.jackson.core.Versioned;
import shaded.parquet.com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion implements Versioned {
   public static final Version VERSION = VersionUtil.parseVersion("2.18.1", "shaded.parquet.com.fasterxml.jackson.core", "jackson-core");

   public Version version() {
      return VERSION;
   }
}
