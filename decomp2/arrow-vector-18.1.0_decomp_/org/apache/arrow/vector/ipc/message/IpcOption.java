package org.apache.arrow.vector.ipc.message;

import org.apache.arrow.vector.types.MetadataVersion;

public class IpcOption {
   public final boolean write_legacy_ipc_format;
   public final MetadataVersion metadataVersion;
   public static final IpcOption DEFAULT = new IpcOption();

   public IpcOption() {
      this(false, MetadataVersion.DEFAULT);
   }

   public IpcOption(boolean writeLegacyIpcFormat, MetadataVersion metadataVersion) {
      this.write_legacy_ipc_format = writeLegacyIpcFormat;
      this.metadataVersion = metadataVersion;
   }
}
