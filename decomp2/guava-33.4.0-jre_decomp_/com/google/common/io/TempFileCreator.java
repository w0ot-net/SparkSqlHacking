package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
abstract class TempFileCreator {
   static final TempFileCreator INSTANCE = pickSecureCreator();

   abstract File createTempDir();

   abstract File createTempFile(String prefix) throws IOException;

   private static TempFileCreator pickSecureCreator() {
      try {
         Class.forName("java.nio.file.Path");
         return new JavaNioCreator();
      } catch (ClassNotFoundException var5) {
         try {
            int version = (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object)null);
            int jellyBean = (Integer)Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get((Object)null);
            if (version < jellyBean) {
               return new ThrowingCreator();
            }
         } catch (NoSuchFieldException var2) {
            return new ThrowingCreator();
         } catch (ClassNotFoundException var3) {
            return new ThrowingCreator();
         } catch (IllegalAccessException var4) {
            return new ThrowingCreator();
         }

         return new JavaIoCreator();
      }
   }

   @IgnoreJRERequirement
   @VisibleForTesting
   static void testMakingUserPermissionsFromScratch() throws IOException {
      FileAttribute<?> unused = TempFileCreator.JavaNioCreator.userPermissions().get();
   }

   private TempFileCreator() {
   }

   @IgnoreJRERequirement
   private static final class JavaNioCreator extends TempFileCreator {
      private static final PermissionSupplier filePermissions;
      private static final PermissionSupplier directoryPermissions;

      private JavaNioCreator() {
      }

      File createTempDir() {
         try {
            return java.nio.file.Files.createTempDirectory(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value()), (String)null, directoryPermissions.get()).toFile();
         } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory", e);
         }
      }

      File createTempFile(String prefix) throws IOException {
         return java.nio.file.Files.createTempFile(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value()), prefix, (String)null, filePermissions.get()).toFile();
      }

      private static PermissionSupplier userPermissions() {
         try {
            UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(getUsername());
            final ImmutableList<AclEntry> acl = ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(user).setPermissions(EnumSet.allOf(AclEntryPermission.class)).setFlags(AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT).build());
            FileAttribute<ImmutableList<AclEntry>> attribute = new FileAttribute() {
               public String name() {
                  return "acl:acl";
               }

               public ImmutableList value() {
                  return acl;
               }
            };
            return () -> attribute;
         } catch (IOException e) {
            return () -> {
               throw new IOException("Could not find user", e);
            };
         }
      }

      private static String getUsername() {
         String fromSystemProperty = (String)Objects.requireNonNull(StandardSystemProperty.USER_NAME.value());

         try {
            Class<?> processHandleClass = Class.forName("java.lang.ProcessHandle");
            Class<?> processHandleInfoClass = Class.forName("java.lang.ProcessHandle$Info");
            Class<?> optionalClass = Class.forName("java.util.Optional");
            Method currentMethod = processHandleClass.getMethod("current");
            Method infoMethod = processHandleClass.getMethod("info");
            Method userMethod = processHandleInfoClass.getMethod("user");
            Method orElseMethod = optionalClass.getMethod("orElse", Object.class);
            Object current = currentMethod.invoke((Object)null);
            Object info = infoMethod.invoke(current);
            Object user = userMethod.invoke(info);
            return (String)Objects.requireNonNull(orElseMethod.invoke(user, fromSystemProperty));
         } catch (ClassNotFoundException var11) {
            return fromSystemProperty;
         } catch (InvocationTargetException e) {
            Throwables.throwIfUnchecked(e.getCause());
            return fromSystemProperty;
         } catch (NoSuchMethodException var13) {
            return fromSystemProperty;
         } catch (IllegalAccessException var14) {
            return fromSystemProperty;
         }
      }

      static {
         Set<String> views = FileSystems.getDefault().supportedFileAttributeViews();
         if (views.contains("posix")) {
            filePermissions = () -> PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));
            directoryPermissions = () -> PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
         } else if (views.contains("acl")) {
            filePermissions = directoryPermissions = userPermissions();
         } else {
            filePermissions = directoryPermissions = () -> {
               throw new IOException("unrecognized FileSystem type " + FileSystems.getDefault());
            };
         }

      }

      @IgnoreJRERequirement
      private interface PermissionSupplier {
         FileAttribute get() throws IOException;
      }
   }

   private static final class JavaIoCreator extends TempFileCreator {
      private static final int TEMP_DIR_ATTEMPTS = 10000;

      private JavaIoCreator() {
      }

      File createTempDir() {
         File baseDir = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value());
         String baseName = System.currentTimeMillis() + "-";

         for(int counter = 0; counter < 10000; ++counter) {
            File tempDir = new File(baseDir, baseName + counter);
            if (tempDir.mkdir()) {
               return tempDir;
            }
         }

         throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + baseName + "0 to " + baseName + 9999 + ')');
      }

      File createTempFile(String prefix) throws IOException {
         return File.createTempFile(prefix, (String)null, (File)null);
      }
   }

   private static final class ThrowingCreator extends TempFileCreator {
      private static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

      private ThrowingCreator() {
      }

      File createTempDir() {
         throw new IllegalStateException("Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().");
      }

      File createTempFile(String prefix) throws IOException {
         throw new IOException("Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().");
      }
   }
}
