package io.vertx.core.file;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import java.util.List;

@VertxGen
public interface FileSystem {
   @Fluent
   FileSystem copy(String var1, String var2, Handler var3);

   Future copy(String var1, String var2);

   @Fluent
   FileSystem copy(String var1, String var2, CopyOptions var3, Handler var4);

   Future copy(String var1, String var2, CopyOptions var3);

   @Fluent
   FileSystem copyBlocking(String var1, String var2);

   @Fluent
   FileSystem copyRecursive(String var1, String var2, boolean var3, Handler var4);

   Future copyRecursive(String var1, String var2, boolean var3);

   @Fluent
   FileSystem copyRecursiveBlocking(String var1, String var2, boolean var3);

   @Fluent
   FileSystem move(String var1, String var2, Handler var3);

   Future move(String var1, String var2);

   @Fluent
   FileSystem move(String var1, String var2, CopyOptions var3, Handler var4);

   Future move(String var1, String var2, CopyOptions var3);

   @Fluent
   FileSystem moveBlocking(String var1, String var2);

   @Fluent
   FileSystem truncate(String var1, long var2, Handler var4);

   Future truncate(String var1, long var2);

   @Fluent
   FileSystem truncateBlocking(String var1, long var2);

   @Fluent
   FileSystem chmod(String var1, String var2, Handler var3);

   Future chmod(String var1, String var2);

   @Fluent
   FileSystem chmodBlocking(String var1, String var2);

   @Fluent
   FileSystem chmodRecursive(String var1, String var2, String var3, Handler var4);

   Future chmodRecursive(String var1, String var2, String var3);

   @Fluent
   FileSystem chmodRecursiveBlocking(String var1, String var2, String var3);

   @Fluent
   FileSystem chown(String var1, @Nullable String var2, @Nullable String var3, Handler var4);

   Future chown(String var1, @Nullable String var2, @Nullable String var3);

   @Fluent
   FileSystem chownBlocking(String var1, @Nullable String var2, @Nullable String var3);

   @Fluent
   FileSystem props(String var1, Handler var2);

   Future props(String var1);

   FileProps propsBlocking(String var1);

   @Fluent
   FileSystem lprops(String var1, Handler var2);

   Future lprops(String var1);

   FileProps lpropsBlocking(String var1);

   @Fluent
   FileSystem link(String var1, String var2, Handler var3);

   Future link(String var1, String var2);

   @Fluent
   FileSystem linkBlocking(String var1, String var2);

   @Fluent
   FileSystem symlink(String var1, String var2, Handler var3);

   Future symlink(String var1, String var2);

   @Fluent
   FileSystem symlinkBlocking(String var1, String var2);

   @Fluent
   FileSystem unlink(String var1, Handler var2);

   Future unlink(String var1);

   @Fluent
   FileSystem unlinkBlocking(String var1);

   @Fluent
   FileSystem readSymlink(String var1, Handler var2);

   Future readSymlink(String var1);

   String readSymlinkBlocking(String var1);

   @Fluent
   FileSystem delete(String var1, Handler var2);

   Future delete(String var1);

   @Fluent
   FileSystem deleteBlocking(String var1);

   @Fluent
   FileSystem deleteRecursive(String var1, boolean var2, Handler var3);

   Future deleteRecursive(String var1, boolean var2);

   @Fluent
   FileSystem deleteRecursiveBlocking(String var1, boolean var2);

   @Fluent
   FileSystem mkdir(String var1, Handler var2);

   Future mkdir(String var1);

   @Fluent
   FileSystem mkdirBlocking(String var1);

   @Fluent
   FileSystem mkdir(String var1, String var2, Handler var3);

   Future mkdir(String var1, String var2);

   @Fluent
   FileSystem mkdirBlocking(String var1, String var2);

   @Fluent
   FileSystem mkdirs(String var1, Handler var2);

   Future mkdirs(String var1);

   @Fluent
   FileSystem mkdirsBlocking(String var1);

   @Fluent
   FileSystem mkdirs(String var1, String var2, Handler var3);

   Future mkdirs(String var1, String var2);

   @Fluent
   FileSystem mkdirsBlocking(String var1, String var2);

   @Fluent
   FileSystem readDir(String var1, Handler var2);

   Future readDir(String var1);

   List readDirBlocking(String var1);

   @Fluent
   FileSystem readDir(String var1, String var2, Handler var3);

   Future readDir(String var1, String var2);

   List readDirBlocking(String var1, String var2);

   @Fluent
   FileSystem readFile(String var1, Handler var2);

   Future readFile(String var1);

   Buffer readFileBlocking(String var1);

   @Fluent
   FileSystem writeFile(String var1, Buffer var2, Handler var3);

   Future writeFile(String var1, Buffer var2);

   @Fluent
   FileSystem writeFileBlocking(String var1, Buffer var2);

   @Fluent
   FileSystem open(String var1, OpenOptions var2, Handler var3);

   Future open(String var1, OpenOptions var2);

   AsyncFile openBlocking(String var1, OpenOptions var2);

   @Fluent
   FileSystem createFile(String var1, Handler var2);

   Future createFile(String var1);

   @Fluent
   FileSystem createFileBlocking(String var1);

   @Fluent
   FileSystem createFile(String var1, String var2, Handler var3);

   Future createFile(String var1, String var2);

   @Fluent
   FileSystem createFileBlocking(String var1, String var2);

   @Fluent
   FileSystem exists(String var1, Handler var2);

   Future exists(String var1);

   boolean existsBlocking(String var1);

   @Fluent
   FileSystem fsProps(String var1, Handler var2);

   Future fsProps(String var1);

   FileSystemProps fsPropsBlocking(String var1);

   @Fluent
   FileSystem createTempDirectory(String var1, Handler var2);

   Future createTempDirectory(String var1);

   String createTempDirectoryBlocking(String var1);

   @Fluent
   FileSystem createTempDirectory(String var1, String var2, Handler var3);

   Future createTempDirectory(String var1, String var2);

   String createTempDirectoryBlocking(String var1, String var2);

   @Fluent
   FileSystem createTempDirectory(String var1, String var2, String var3, Handler var4);

   Future createTempDirectory(String var1, String var2, String var3);

   String createTempDirectoryBlocking(String var1, String var2, String var3);

   @Fluent
   FileSystem createTempFile(String var1, String var2, Handler var3);

   Future createTempFile(String var1, String var2);

   String createTempFileBlocking(String var1, String var2);

   @Fluent
   FileSystem createTempFile(String var1, String var2, String var3, Handler var4);

   Future createTempFile(String var1, String var2, String var3);

   String createTempFileBlocking(String var1, String var2, String var3);

   @Fluent
   FileSystem createTempFile(String var1, String var2, String var3, String var4, Handler var5);

   Future createTempFile(String var1, String var2, String var3, String var4);

   String createTempFileBlocking(String var1, String var2, String var3, String var4);
}
