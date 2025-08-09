package io.vertx.core.file.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.CopyOptions;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.file.FileSystemProps;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class FileSystemImpl implements FileSystem {
   private static final CopyOptions DEFAULT_OPTIONS = new CopyOptions();
   protected final VertxInternal vertx;

   public FileSystemImpl(VertxInternal vertx) {
      this.vertx = vertx;
   }

   public FileSystem copy(String from, String to, Handler handler) {
      return this.copy(from, to, DEFAULT_OPTIONS, handler);
   }

   public Future copy(String from, String to) {
      return this.copy(from, to, DEFAULT_OPTIONS);
   }

   public FileSystem copy(String from, String to, CopyOptions options, Handler handler) {
      this.copy(from, to, options).onComplete(handler);
      return this;
   }

   public Future copy(String from, String to, CopyOptions options) {
      return this.copyInternal(from, to, options).run();
   }

   public FileSystem copyBlocking(String from, String to) {
      this.copyInternal(from, to, DEFAULT_OPTIONS).perform();
      return this;
   }

   public FileSystem copyRecursive(String from, String to, boolean recursive, Handler handler) {
      this.copyRecursive(from, to, recursive).onComplete(handler);
      return this;
   }

   public Future copyRecursive(String from, String to, boolean recursive) {
      return this.copyRecursiveInternal(from, to, recursive).run();
   }

   public FileSystem copyRecursiveBlocking(String from, String to, boolean recursive) {
      this.copyRecursiveInternal(from, to, recursive).perform();
      return this;
   }

   public FileSystem move(String from, String to, Handler handler) {
      return this.move(from, to, DEFAULT_OPTIONS, handler);
   }

   public Future move(String from, String to) {
      return this.move(from, to, DEFAULT_OPTIONS);
   }

   public FileSystem move(String from, String to, CopyOptions options, Handler handler) {
      this.move(from, to, options).onComplete(handler);
      return this;
   }

   public Future move(String from, String to, CopyOptions options) {
      return this.moveInternal(from, to, options).run();
   }

   public FileSystem moveBlocking(String from, String to) {
      this.moveInternal(from, to, DEFAULT_OPTIONS).perform();
      return this;
   }

   public FileSystem truncate(String path, long len, Handler handler) {
      this.truncate(path, len).onComplete(handler);
      return this;
   }

   public Future truncate(String path, long len) {
      return this.truncateInternal(path, len).run();
   }

   public FileSystem truncateBlocking(String path, long len) {
      this.truncateInternal(path, len).perform();
      return this;
   }

   public FileSystem chmod(String path, String perms, Handler handler) {
      this.chmod(path, perms).onComplete(handler);
      return this;
   }

   public Future chmod(String path, String perms) {
      return this.chmodInternal(path, perms).run();
   }

   public FileSystem chmodBlocking(String path, String perms) {
      this.chmodInternal(path, perms).perform();
      return this;
   }

   public FileSystem chmodRecursive(String path, String perms, String dirPerms, Handler handler) {
      this.chmodRecursive(path, perms, dirPerms).onComplete(handler);
      return this;
   }

   public Future chmodRecursive(String path, String perms, String dirPerms) {
      return this.chmodInternal(path, perms, dirPerms).run();
   }

   public FileSystem chmodRecursiveBlocking(String path, String perms, String dirPerms) {
      this.chmodInternal(path, perms, dirPerms).perform();
      return this;
   }

   public FileSystem chown(String path, String user, String group, Handler handler) {
      this.chown(path, user, group).onComplete(handler);
      return this;
   }

   public Future chown(String path, @Nullable String user, @Nullable String group) {
      return this.chownInternal(path, user, group).run();
   }

   public FileSystem chownBlocking(String path, String user, String group) {
      this.chownInternal(path, user, group).perform();
      return this;
   }

   public FileSystem props(String path, Handler handler) {
      this.props(path).onComplete(handler);
      return this;
   }

   public Future props(String path) {
      return this.propsInternal(path).run();
   }

   public FileProps propsBlocking(String path) {
      return (FileProps)this.propsInternal(path).perform();
   }

   public FileSystem lprops(String path, Handler handler) {
      this.lprops(path).onComplete(handler);
      return this;
   }

   public Future lprops(String path) {
      return this.lpropsInternal(path).run();
   }

   public FileProps lpropsBlocking(String path) {
      return (FileProps)this.lpropsInternal(path).perform();
   }

   public FileSystem link(String link, String existing, Handler handler) {
      this.link(link, existing).onComplete(handler);
      return this;
   }

   public Future link(String link, String existing) {
      return this.linkInternal(link, existing).run();
   }

   public FileSystem linkBlocking(String link, String existing) {
      this.linkInternal(link, existing).perform();
      return this;
   }

   public FileSystem symlink(String link, String existing, Handler handler) {
      this.symlink(link, existing).onComplete(handler);
      return this;
   }

   public Future symlink(String link, String existing) {
      return this.symlinkInternal(link, existing).run();
   }

   public FileSystem symlinkBlocking(String link, String existing) {
      this.symlinkInternal(link, existing).perform();
      return this;
   }

   public FileSystem unlink(String link, Handler handler) {
      this.unlink(link).onComplete(handler);
      return this;
   }

   public Future unlink(String link) {
      return this.unlinkInternal(link).run();
   }

   public FileSystem unlinkBlocking(String link) {
      this.unlinkInternal(link).perform();
      return this;
   }

   public FileSystem readSymlink(String link, Handler handler) {
      this.readSymlink(link).onComplete(handler);
      return this;
   }

   public Future readSymlink(String link) {
      return this.readSymlinkInternal(link).run();
   }

   public String readSymlinkBlocking(String link) {
      return (String)this.readSymlinkInternal(link).perform();
   }

   public FileSystem delete(String path, Handler handler) {
      this.delete(path).onComplete(handler);
      return this;
   }

   public Future delete(String path) {
      return this.deleteInternal(path).run();
   }

   public FileSystem deleteBlocking(String path) {
      this.deleteInternal(path).perform();
      return this;
   }

   public FileSystem deleteRecursive(String path, boolean recursive, Handler handler) {
      this.deleteRecursive(path, recursive).onComplete(handler);
      return this;
   }

   public Future deleteRecursive(String path, boolean recursive) {
      return this.deleteInternal(path, recursive).run();
   }

   public FileSystem deleteRecursiveBlocking(String path, boolean recursive) {
      this.deleteInternal(path, recursive).perform();
      return this;
   }

   public FileSystem mkdir(String path, Handler handler) {
      this.mkdir(path).onComplete(handler);
      return this;
   }

   public Future mkdir(String path) {
      return this.mkdirInternal(path).run();
   }

   public FileSystem mkdirBlocking(String path) {
      this.mkdirInternal(path).perform();
      return this;
   }

   public FileSystem mkdirs(String path, Handler handler) {
      this.mkdirs(path).onComplete(handler);
      return this;
   }

   public Future mkdirs(String path) {
      return this.mkdirInternal(path, true).run();
   }

   public FileSystem mkdirsBlocking(String path) {
      this.mkdirInternal(path, true).perform();
      return this;
   }

   public FileSystem mkdir(String path, String perms, Handler handler) {
      this.mkdir(path, perms).onComplete(handler);
      return this;
   }

   public Future mkdir(String path, String perms) {
      return this.mkdirInternal(path, perms).run();
   }

   public FileSystem mkdirBlocking(String path, String perms) {
      this.mkdirInternal(path, perms).perform();
      return this;
   }

   public FileSystem mkdirs(String path, String perms, Handler handler) {
      this.mkdirs(path, perms).onComplete(handler);
      return this;
   }

   public Future mkdirs(String path, String perms) {
      return this.mkdirInternal(path, perms, true).run();
   }

   public FileSystem mkdirsBlocking(String path, String perms) {
      this.mkdirInternal(path, perms, true).perform();
      return this;
   }

   public FileSystem readDir(String path, Handler handler) {
      this.readDir(path).onComplete(handler);
      return this;
   }

   public Future readDir(String path) {
      return this.readDirInternal(path).run();
   }

   public List readDirBlocking(String path) {
      return (List)this.readDirInternal(path).perform();
   }

   public FileSystem readDir(String path, String filter, Handler handler) {
      this.readDir(path, filter).onComplete(handler);
      return this;
   }

   public Future readDir(String path, String filter) {
      return this.readDirInternal(path, filter).run();
   }

   public List readDirBlocking(String path, String filter) {
      return (List)this.readDirInternal(path, filter).perform();
   }

   public FileSystem readFile(String path, Handler handler) {
      this.readFile(path).onComplete(handler);
      return this;
   }

   public Future readFile(String path) {
      return this.readFileInternal(path).run();
   }

   public Buffer readFileBlocking(String path) {
      return (Buffer)this.readFileInternal(path).perform();
   }

   public FileSystem writeFile(String path, Buffer data, Handler handler) {
      this.writeFile(path, data).onComplete(handler);
      return this;
   }

   public Future writeFile(String path, Buffer data) {
      return this.writeFileInternal(path, data).run();
   }

   public FileSystem writeFileBlocking(String path, Buffer data) {
      this.writeFileInternal(path, data).perform();
      return this;
   }

   public FileSystem open(String path, OpenOptions options, Handler handler) {
      this.open(path, options).onComplete(handler);
      return this;
   }

   public Future open(String path, OpenOptions options) {
      return this.openInternal(path, options).run();
   }

   public AsyncFile openBlocking(String path, OpenOptions options) {
      return (AsyncFile)this.openInternal(path, options).perform();
   }

   public FileSystem createFile(String path, Handler handler) {
      this.createFile(path).onComplete(handler);
      return this;
   }

   public Future createFile(String path) {
      return this.createFileInternal(path).run();
   }

   public FileSystem createFileBlocking(String path) {
      this.createFileInternal(path).perform();
      return this;
   }

   public FileSystem createFile(String path, String perms, Handler handler) {
      this.createFile(path, perms).onComplete(handler);
      return this;
   }

   public Future createFile(String path, String perms) {
      return this.createFileInternal(path, perms).run();
   }

   public FileSystem createFileBlocking(String path, String perms) {
      this.createFileInternal(path, perms).perform();
      return this;
   }

   public FileSystem exists(String path, Handler handler) {
      this.exists(path).onComplete(handler);
      return this;
   }

   public Future exists(String path) {
      return this.existsInternal(path).run();
   }

   public boolean existsBlocking(String path) {
      return (Boolean)this.existsInternal(path).perform();
   }

   public FileSystem fsProps(String path, Handler handler) {
      this.fsProps(path).onComplete(handler);
      return this;
   }

   public Future fsProps(String path) {
      return this.fsPropsInternal(path).run();
   }

   public FileSystemProps fsPropsBlocking(String path) {
      return (FileSystemProps)this.fsPropsInternal(path).perform();
   }

   public FileSystem createTempDirectory(String prefix, Handler handler) {
      this.createTempDirectory(prefix).onComplete(handler);
      return this;
   }

   public Future createTempDirectory(String prefix) {
      return this.createTempDirectoryInternal((String)null, prefix, (String)null).run();
   }

   public String createTempDirectoryBlocking(String prefix) {
      return (String)this.createTempDirectoryInternal((String)null, prefix, (String)null).perform();
   }

   public FileSystem createTempDirectory(String prefix, String perms, Handler handler) {
      this.createTempDirectory(prefix, perms).onComplete(handler);
      return this;
   }

   public Future createTempDirectory(String prefix, String perms) {
      return this.createTempDirectoryInternal((String)null, prefix, perms).run();
   }

   public String createTempDirectoryBlocking(String prefix, String perms) {
      return (String)this.createTempDirectoryInternal((String)null, prefix, perms).perform();
   }

   public FileSystem createTempDirectory(String dir, String prefix, String perms, Handler handler) {
      this.createTempDirectory(dir, prefix, perms).onComplete(handler);
      return this;
   }

   public Future createTempDirectory(String dir, String prefix, String perms) {
      return this.createTempDirectoryInternal(dir, prefix, perms).run();
   }

   public String createTempDirectoryBlocking(String dir, String prefix, String perms) {
      return (String)this.createTempDirectoryInternal(dir, prefix, perms).perform();
   }

   public FileSystem createTempFile(String prefix, String suffix, Handler handler) {
      this.createTempFile(prefix, suffix).onComplete(handler);
      return this;
   }

   public Future createTempFile(String prefix, String suffix) {
      return this.createTempFileInternal((String)null, prefix, suffix, (String)null).run();
   }

   public String createTempFileBlocking(String prefix, String suffix) {
      return (String)this.createTempFileInternal((String)null, prefix, suffix, (String)null).perform();
   }

   public FileSystem createTempFile(String prefix, String suffix, String perms, Handler handler) {
      this.createTempFile(prefix, suffix, perms).onComplete(handler);
      return this;
   }

   public Future createTempFile(String prefix, String suffix, String perms) {
      return this.createTempFileInternal((String)null, prefix, suffix, perms).run();
   }

   public String createTempFileBlocking(String prefix, String suffix, String perms) {
      return (String)this.createTempFileInternal((String)null, prefix, suffix, perms).perform();
   }

   public FileSystem createTempFile(String dir, String prefix, String suffix, String perms, Handler handler) {
      this.createTempFile(dir, prefix, suffix, perms).onComplete(handler);
      return this;
   }

   public Future createTempFile(String dir, String prefix, String suffix, String perms) {
      return this.createTempFileInternal(dir, prefix, suffix, perms).run();
   }

   public String createTempFileBlocking(String dir, String prefix, String suffix, String perms) {
      return (String)this.createTempFileInternal(dir, prefix, suffix, perms).perform();
   }

   static String getFileAccessErrorMessage(String action, String path) {
      return "Unable to " + action + " file at path '" + path + "'";
   }

   static String getFolderAccessErrorMessage(String action, String path) {
      return "Unable to " + action + " folder at path '" + path + "'";
   }

   static String getFileCopyErrorMessage(String from, String to) {
      return getFileDualOperationErrorMessage("copy", from, to);
   }

   static String getFileMoveErrorMessage(String from, String to) {
      return getFileDualOperationErrorMessage("move", from, to);
   }

   static String getFileDualOperationErrorMessage(String action, String from, String to) {
      return "Unable to " + action + " file from '" + from + "' to '" + to + "'";
   }

   private BlockingAction copyInternal(final String from, final String to, CopyOptions options) {
      Objects.requireNonNull(from);
      Objects.requireNonNull(to);
      Objects.requireNonNull(options);
      Set<CopyOption> copyOptionSet = toCopyOptionSet(options);
      final CopyOption[] copyOptions = (CopyOption[])copyOptionSet.toArray(new CopyOption[0]);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
               Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
               Files.copy(source, target, copyOptions);
               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileCopyErrorMessage(from, to), e);
            }
         }
      };
   }

   private BlockingAction copyRecursiveInternal(final String from, final String to, final boolean recursive) {
      Objects.requireNonNull(from);
      Objects.requireNonNull(to);
      return new BlockingAction() {
         public Void perform() {
            try {
               final Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
               final Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
               if (recursive) {
                  Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor() {
                     public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        Path targetDir = target.resolve(source.relativize(dir));

                        try {
                           Files.copy(dir, targetDir);
                        } catch (FileAlreadyExistsException e) {
                           if (!Files.isDirectory(targetDir, new LinkOption[0])) {
                              throw e;
                           }
                        }

                        return FileVisitResult.CONTINUE;
                     }

                     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.copy(file, target.resolve(source.relativize(file)));
                        return FileVisitResult.CONTINUE;
                     }
                  });
               } else {
                  Files.copy(source, target);
               }

               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileCopyErrorMessage(from, to), e);
            }
         }
      };
   }

   private BlockingAction moveInternal(final String from, final String to, CopyOptions options) {
      Objects.requireNonNull(from);
      Objects.requireNonNull(to);
      Objects.requireNonNull(options);
      Set<CopyOption> copyOptionSet = toCopyOptionSet(options);
      final CopyOption[] copyOptions = (CopyOption[])copyOptionSet.toArray(new CopyOption[0]);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(from).toPath();
               Path target = FileSystemImpl.this.vertx.resolveFile(to).toPath();
               Files.move(source, target, copyOptions);
               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileMoveErrorMessage(from, to), e);
            }
         }
      };
   }

   private BlockingAction truncateInternal(final String p, final long len) {
      Objects.requireNonNull(p);
      return new BlockingAction() {
         public Void perform() {
            try {
               String path = FileSystemImpl.this.vertx.resolveFile(p).getAbsolutePath();
               if (len < 0L) {
                  throw new FileSystemException("Cannot truncate file to size < 0");
               } else if (!Files.exists(Paths.get(path), new LinkOption[0])) {
                  throw new FileSystemException("Cannot truncate file " + path + ". Does not exist");
               } else {
                  RandomAccessFile raf = new RandomAccessFile(path, "rw");
                  Throwable var3 = null;

                  try {
                     raf.setLength(len);
                  } catch (Throwable var13) {
                     var3 = var13;
                     throw var13;
                  } finally {
                     if (raf != null) {
                        if (var3 != null) {
                           try {
                              raf.close();
                           } catch (Throwable var12) {
                              var3.addSuppressed(var12);
                           }
                        } else {
                           raf.close();
                        }
                     }

                  }

                  return null;
               }
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("truncate", p), e);
            }
         }
      };
   }

   private BlockingAction chmodInternal(String path, String perms) {
      return this.chmodInternal(path, perms, (String)null);
   }

   protected BlockingAction chmodInternal(final String path, String perms, String dirPerms) {
      Objects.requireNonNull(path);
      final Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perms);
      final Set<PosixFilePermission> dirPermissions = dirPerms == null ? null : PosixFilePermissions.fromString(dirPerms);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               if (dirPermissions != null) {
                  Files.walkFileTree(target, new SimpleFileVisitor() {
                     public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        Files.setPosixFilePermissions(dir, dirPermissions);
                        return FileVisitResult.CONTINUE;
                     }

                     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.setPosixFilePermissions(file, permissions);
                        return FileVisitResult.CONTINUE;
                     }
                  });
               } else {
                  Files.setPosixFilePermissions(target, permissions);
               }

               return null;
            } catch (SecurityException var2) {
               throw new FileSystemException("Accessed denied for chmod on " + path);
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("chmod", path), e);
            }
         }
      };
   }

   protected BlockingAction chownInternal(final String path, final String user, final String group) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               UserPrincipalLookupService service = target.getFileSystem().getUserPrincipalLookupService();
               UserPrincipal userPrincipal = user == null ? null : service.lookupPrincipalByName(user);
               GroupPrincipal groupPrincipal = group == null ? null : service.lookupPrincipalByGroupName(group);
               if (groupPrincipal != null) {
                  PosixFileAttributeView view = (PosixFileAttributeView)Files.getFileAttributeView(target, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
                  if (view == null) {
                     throw new FileSystemException("Change group of file not supported");
                  }

                  view.setGroup(groupPrincipal);
               }

               if (userPrincipal != null) {
                  Files.setOwner(target, userPrincipal);
               }

               return null;
            } catch (SecurityException var6) {
               throw new FileSystemException("Accessed denied for chown on " + path);
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("crown", path), e);
            }
         }
      };
   }

   private BlockingAction propsInternal(String path) {
      return this.props(path, true);
   }

   private BlockingAction lpropsInternal(String path) {
      return this.props(path, false);
   }

   private BlockingAction props(final String path, final boolean followLinks) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public FileProps perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               BasicFileAttributes attrs;
               if (followLinks) {
                  attrs = Files.readAttributes(target, BasicFileAttributes.class);
               } else {
                  attrs = Files.readAttributes(target, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
               }

               return new FilePropsImpl(attrs);
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("analyse", path), e);
            }
         }
      };
   }

   private BlockingAction linkInternal(String link, String existing) {
      return this.link(link, existing, false);
   }

   private BlockingAction symlinkInternal(String link, String existing) {
      return this.link(link, existing, true);
   }

   private BlockingAction link(final String link, final String existing, final boolean symbolic) {
      Objects.requireNonNull(link);
      Objects.requireNonNull(existing);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(link).toPath();
               Path target = FileSystemImpl.this.vertx.resolveFile(existing).toPath();
               if (symbolic) {
                  Files.createSymbolicLink(source, target);
               } else {
                  Files.createLink(source, target);
               }

               return null;
            } catch (IOException e) {
               String message = "Unable to link existing file '" + existing + "' to '" + link + "'";
               throw new FileSystemException(message, e);
            }
         }
      };
   }

   private BlockingAction unlinkInternal(String link) {
      return this.deleteInternal(link);
   }

   private BlockingAction readSymlinkInternal(final String link) {
      Objects.requireNonNull(link);
      return new BlockingAction() {
         public String perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(link).toPath();
               return Files.readSymbolicLink(source).toString();
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("read", link), e);
            }
         }
      };
   }

   private BlockingAction deleteInternal(String path) {
      return this.deleteInternal(path, false);
   }

   private BlockingAction deleteInternal(final String path, final boolean recursive) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               FileSystemImpl.delete(source, recursive);
               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("delete", path), e);
            }
         }
      };
   }

   public static void delete(Path path, boolean recursive) throws IOException {
      if (recursive) {
         Files.walkFileTree(path, new SimpleFileVisitor() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
               Files.delete(file);
               return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
               if (e == null) {
                  Files.delete(dir);
                  return FileVisitResult.CONTINUE;
               } else {
                  throw e;
               }
            }
         });
      } else {
         Files.delete(path);
      }

   }

   private BlockingAction mkdirInternal(String path) {
      return this.mkdirInternal(path, (String)null, false);
   }

   private BlockingAction mkdirInternal(String path, boolean createParents) {
      return this.mkdirInternal(path, (String)null, createParents);
   }

   private BlockingAction mkdirInternal(String path, String perms) {
      return this.mkdirInternal(path, perms, false);
   }

   protected BlockingAction mkdirInternal(final String path, String perms, final boolean createParents) {
      Objects.requireNonNull(path);
      final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
      return new BlockingAction() {
         public Void perform() {
            try {
               Path source = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               if (createParents) {
                  if (attrs != null) {
                     Files.createDirectories(source, attrs);
                  } else {
                     Files.createDirectories(source);
                  }
               } else if (attrs != null) {
                  Files.createDirectory(source, attrs);
               } else {
                  Files.createDirectory(source);
               }

               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFolderAccessErrorMessage("create", path), e);
            }
         }
      };
   }

   protected BlockingAction createTempDirectoryInternal(final String parentDir, final String prefix, String perms) {
      final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
      return new BlockingAction() {
         public String perform() {
            try {
               Path tmpDir;
               if (parentDir != null) {
                  Path dir = FileSystemImpl.this.vertx.resolveFile(parentDir).toPath();
                  if (attrs != null) {
                     tmpDir = Files.createTempDirectory(dir, prefix, attrs);
                  } else {
                     tmpDir = Files.createTempDirectory(dir, prefix);
                  }
               } else if (attrs != null) {
                  tmpDir = Files.createTempDirectory(prefix, attrs);
               } else {
                  tmpDir = Files.createTempDirectory(prefix);
               }

               return tmpDir.toFile().getAbsolutePath();
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFolderAccessErrorMessage("create subfolder of", parentDir), e);
            }
         }
      };
   }

   protected BlockingAction createTempFileInternal(final String parentDir, final String prefix, final String suffix, String perms) {
      final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
      return new BlockingAction() {
         public String perform() {
            try {
               Path tmpFile;
               if (parentDir != null) {
                  Path dir = FileSystemImpl.this.vertx.resolveFile(parentDir).toPath();
                  if (attrs != null) {
                     tmpFile = Files.createTempFile(dir, prefix, suffix, attrs);
                  } else {
                     tmpFile = Files.createTempFile(dir, prefix, suffix);
                  }
               } else if (attrs != null) {
                  tmpFile = Files.createTempFile(prefix, suffix, attrs);
               } else {
                  tmpFile = Files.createTempFile(prefix, suffix);
               }

               return tmpFile.toFile().getAbsolutePath();
            } catch (IOException e) {
               String message = "Unable to create temporary file with prefix '" + prefix + "' and suffix '" + suffix + "' at " + parentDir;
               throw new FileSystemException(message, e);
            }
         }
      };
   }

   private BlockingAction readDirInternal(String path) {
      return this.readDirInternal(path, (String)null);
   }

   private BlockingAction readDirInternal(final String p, final String filter) {
      Objects.requireNonNull(p);
      return new BlockingAction() {
         public List perform() {
            try {
               File file = FileSystemImpl.this.vertx.resolveFile(p);
               if (!file.exists()) {
                  throw new FileSystemException("Cannot read directory " + file + ". Does not exist");
               } else if (!file.isDirectory()) {
                  throw new FileSystemException("Cannot read directory " + file + ". It's not a directory");
               } else {
                  FilenameFilter fnFilter;
                  if (filter != null) {
                     fnFilter = new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                           return Pattern.matches(filter, name);
                        }
                     };
                  } else {
                     fnFilter = null;
                  }

                  File[] files;
                  if (fnFilter == null) {
                     files = file.listFiles();
                  } else {
                     files = file.listFiles(fnFilter);
                  }

                  List<String> ret = new ArrayList(files.length);

                  for(File f : files) {
                     ret.add(f.getCanonicalPath());
                  }

                  return ret;
               }
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFolderAccessErrorMessage("read", p), e);
            }
         }
      };
   }

   private BlockingAction readFileInternal(final String path) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public Buffer perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               byte[] bytes = Files.readAllBytes(target);
               return Buffer.buffer(bytes);
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("read", path), e);
            }
         }
      };
   }

   private BlockingAction writeFileInternal(final String path, final Buffer data) {
      Objects.requireNonNull(path);
      Objects.requireNonNull(data);
      return new BlockingAction() {
         public Void perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               Files.write(target, data.getBytes(), new OpenOption[0]);
               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("write", path), e);
            }
         }
      };
   }

   private BlockingAction openInternal(final String p, final OpenOptions options) {
      Objects.requireNonNull(p);
      Objects.requireNonNull(options);
      return new BlockingAction() {
         public AsyncFile perform() {
            String path = FileSystemImpl.this.vertx.resolveFile(p).getAbsolutePath();
            return FileSystemImpl.this.doOpen(path, options, this.context);
         }
      };
   }

   protected AsyncFile doOpen(String path, OpenOptions options, ContextInternal context) {
      return new AsyncFileImpl(this.vertx, path, options, context);
   }

   private BlockingAction createFileInternal(String path) {
      return this.createFileInternal(path, (String)null);
   }

   protected BlockingAction createFileInternal(final String p, String perms) {
      Objects.requireNonNull(p);
      final FileAttribute<?> attrs = perms == null ? null : PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perms));
      return new BlockingAction() {
         public Void perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(p).toPath();
               if (attrs != null) {
                  Files.createFile(target, attrs);
               } else {
                  Files.createFile(target);
               }

               return null;
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("create", p), e);
            }
         }
      };
   }

   private BlockingAction existsInternal(final String path) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public Boolean perform() {
            File file = FileSystemImpl.this.vertx.resolveFile(path);
            return file.exists();
         }
      };
   }

   private BlockingAction fsPropsInternal(final String path) {
      Objects.requireNonNull(path);
      return new BlockingAction() {
         public FileSystemProps perform() {
            try {
               Path target = FileSystemImpl.this.vertx.resolveFile(path).toPath();
               FileStore fs = Files.getFileStore(target);
               return new FileSystemPropsImpl(fs.getTotalSpace(), fs.getUnallocatedSpace(), fs.getUsableSpace());
            } catch (IOException e) {
               throw new FileSystemException(FileSystemImpl.getFileAccessErrorMessage("analyse", path), e);
            }
         }
      };
   }

   static Set toCopyOptionSet(CopyOptions copyOptions) {
      Set<CopyOption> copyOptionSet = new HashSet();
      if (copyOptions.isReplaceExisting()) {
         copyOptionSet.add(StandardCopyOption.REPLACE_EXISTING);
      }

      if (copyOptions.isCopyAttributes()) {
         copyOptionSet.add(StandardCopyOption.COPY_ATTRIBUTES);
      }

      if (copyOptions.isAtomicMove()) {
         copyOptionSet.add(StandardCopyOption.ATOMIC_MOVE);
      }

      if (copyOptions.isNofollowLinks()) {
         copyOptionSet.add(LinkOption.NOFOLLOW_LINKS);
      }

      return copyOptionSet;
   }

   protected abstract class BlockingAction implements Handler {
      protected final ContextInternal context;

      protected BlockingAction() {
         this.context = FileSystemImpl.this.vertx.getOrCreateContext();
      }

      public Future run() {
         return this.context.executeBlockingInternal((Handler)this);
      }

      public void handle(Promise fut) {
         try {
            T result = (T)this.perform();
            fut.complete(result);
         } catch (Exception e) {
            fut.fail((Throwable)e);
         }

      }

      public abstract Object perform();
   }
}
