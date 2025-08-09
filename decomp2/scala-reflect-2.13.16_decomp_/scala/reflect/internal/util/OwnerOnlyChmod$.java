package scala.reflect.internal.util;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.List;

public final class OwnerOnlyChmod$ {
   public static final OwnerOnlyChmod$ MODULE$ = new OwnerOnlyChmod$();
   private static final EnumSet posixDir;
   private static final EnumSet posixFile;

   static {
      posixDir = EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE);
      posixFile = EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
   }

   private boolean canPosix(final Path path) {
      return Files.getFileStore(path).supportsFileAttributeView(PosixFileAttributeView.class);
   }

   public void chmod(final Path path) {
      if (this.canPosix(path)) {
         Files.setPosixFilePermissions(path, Files.isDirectory(path, new LinkOption[0]) ? posixDir : posixFile);
      } else {
         AclFileAttributeView view = (AclFileAttributeView)Files.getFileAttributeView(path, AclFileAttributeView.class);
         if (view == null) {
            throw new UnsupportedOperationException((new StringBuilder(35)).append("Cannot get file attribute view for ").append(path).toString());
         } else {
            AclEntry.Builder builder = AclEntry.newBuilder();
            builder.setPrincipal(view.getOwner());
            builder.setPermissions(AclEntryPermission.values());
            builder.setType(AclEntryType.ALLOW);
            List acls = java.util.Collections.singletonList(builder.build());
            view.setAcl(acls);
         }
      }
   }

   public void chmodFileOrCreateEmpty(final Path path) {
      Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.CREATE)).close();
      this.chmod(path);
   }

   public void chmodFileAndWrite(final Path path, final byte[] contents) {
      SeekableByteChannel sbc = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));

      try {
         sbc.write(ByteBuffer.wrap(contents));
      } finally {
         sbc.close();
      }

      this.chmod(path);
   }

   private OwnerOnlyChmod$() {
   }
}
