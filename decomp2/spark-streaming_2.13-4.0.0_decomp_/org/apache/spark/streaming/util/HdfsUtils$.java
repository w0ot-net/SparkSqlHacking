package org.apache.spark.streaming.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.spark.deploy.SparkHadoopUtil.;
import scala.Function0;
import scala.Option;

public final class HdfsUtils$ {
   public static final HdfsUtils$ MODULE$ = new HdfsUtils$();

   public FSDataOutputStream getOutputStream(final String path, final Configuration conf) {
      Path dfsPath = new Path(path);
      FileSystem dfs = this.getFileSystemForPath(dfsPath, conf);
      FSDataOutputStream var10000;
      if (.MODULE$.isFile(dfs, dfsPath)) {
         if (!conf.getBoolean("dfs.support.append", true) && !conf.getBoolean("hdfs.append.support", false) && !(dfs instanceof RawLocalFileSystem)) {
            throw new IllegalStateException("File exists and there is no append support!");
         }

         var10000 = dfs.append(dfsPath);
      } else {
         var10000 = .MODULE$.createFile(dfs, dfsPath, false);
      }

      FSDataOutputStream stream = var10000;
      return stream;
   }

   public FSDataInputStream getInputStream(final String path, final Configuration conf) {
      Path dfsPath = new Path(path);
      FileSystem dfs = this.getFileSystemForPath(dfsPath, conf);

      FSDataInputStream var10000;
      try {
         var10000 = dfs.open(dfsPath);
      } catch (FileNotFoundException var6) {
         var10000 = null;
      } catch (IOException var7) {
         if (dfs.getFileStatus(dfsPath).isFile()) {
            throw var7;
         }

         var10000 = null;
      }

      return var10000;
   }

   public void checkState(final boolean state, final Function0 errorMsg) {
      if (!state) {
         throw new IllegalStateException((String)errorMsg.apply());
      }
   }

   public String[] getFileSegmentLocations(final String path, final long offset, final long length, final Configuration conf) {
      Path dfsPath = new Path(path);
      FileSystem dfs = this.getFileSystemForPath(dfsPath, conf);
      FileStatus fileStatus = dfs.getFileStatus(dfsPath);
      Option blockLocs = scala.Option..MODULE$.apply(dfs.getFileBlockLocations(fileStatus, offset, length));
      return (String[])blockLocs.map((x$1) -> (String[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$1), (x$2) -> x$2.getHosts(), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(String.class))).getOrElse(() -> (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public FileSystem getFileSystemForPath(final Path path, final Configuration conf) {
      FileSystem fs = path.getFileSystem(conf);
      if (fs instanceof LocalFileSystem var6) {
         return var6.getRawFileSystem();
      } else {
         return fs;
      }
   }

   public boolean checkFileExists(final String path, final Configuration conf) {
      Path hdpPath = new Path(path);
      FileSystem fs = this.getFileSystemForPath(hdpPath, conf);

      boolean var10000;
      try {
         var10000 = fs.getFileStatus(hdpPath).isFile();
      } catch (FileNotFoundException var5) {
         var10000 = false;
      }

      return var10000;
   }

   private HdfsUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
