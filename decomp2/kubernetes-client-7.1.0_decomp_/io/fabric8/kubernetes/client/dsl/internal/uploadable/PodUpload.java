package io.fabric8.kubernetes.client.dsl.internal.uploadable;

import io.fabric8.kubernetes.client.dsl.ExecWatch;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.PodOperationsImpl;
import io.fabric8.kubernetes.client.utils.InputStreamPumper;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodUpload {
   private static final Logger LOG = LoggerFactory.getLogger(PodUpload.class);
   private static final String TAR_PATH_DELIMITER = "/";

   private PodUpload() {
   }

   public static boolean upload(PodOperationsImpl operation, Path pathToUpload) throws IOException {
      File toUpload = pathToUpload.toFile();
      if (Utils.isNotNullOrEmpty(operation.getContext().getFile()) && toUpload.isFile()) {
         return uploadTar(operation, getDirectoryFromFile(operation.getContext().getFile()), (tar) -> addFileToTar((new File(operation.getContext().getFile())).getName(), toUpload, tar));
      } else if (Utils.isNotNullOrEmpty(operation.getContext().getDir()) && toUpload.isDirectory()) {
         return uploadTar(operation, ensureEndsWithSlash(operation.getContext().getDir()), (tar) -> {
            for(File file : (File[])Objects.requireNonNull(toUpload.listFiles())) {
               addFileToTar(file.getName(), file, tar);
            }

         });
      } else {
         throw new IllegalArgumentException("Provided arguments are not valid (file, directory, path)");
      }
   }

   private static String getDirectoryFromFile(String file) {
      String directoryTrimmedFromFilePath = file.substring(0, file.lastIndexOf(47));
      return ensureEndsWithSlash(directoryTrimmedFromFilePath.isEmpty() ? "/" : directoryTrimmedFromFilePath);
   }

   private static boolean upload(PodOperationsImpl operation, String file, UploadProcessor processor) throws IOException {
      String command = createExecCommandForUpload(file);
      int uploadRequestTimeout = operation.getRequestConfig().getUploadRequestTimeout();
      long uploadRequestTimeoutEnd = uploadRequestTimeout < 0 ? Long.MAX_VALUE : (long)uploadRequestTimeout + System.currentTimeMillis();
      long expected = 0L;
      ExecWatch execWatch = operation.redirectingInput().terminateOnError().exec("sh", "-c", command);

      CompletableFuture<Integer> exitFuture;
      try {
         OutputStream out = execWatch.getInput();
         CountingOutputStream countingStream = new CountingOutputStream(out);
         processor.process(countingStream);
         out.close();
         expected = countingStream.getBytesWritten();
         exitFuture = execWatch.exitCode();
      } catch (Throwable var18) {
         if (execWatch != null) {
            try {
               execWatch.close();
            } catch (Throwable var17) {
               var18.addSuppressed(var17);
            }
         }

         throw var18;
      }

      if (execWatch != null) {
         execWatch.close();
      }

      if (!Utils.waitUntilReady(exitFuture, Math.max(0L, uploadRequestTimeoutEnd - System.currentTimeMillis()), TimeUnit.MILLISECONDS)) {
         LOG.debug("failed to complete upload before timeout expired");
         return false;
      } else {
         Integer exitCode = (Integer)exitFuture.getNow((Object)null);
         if (exitCode != null && exitCode != 0) {
            LOG.debug("upload process failed with exit code {}", exitCode);
            return false;
         } else {
            ByteArrayOutputStream byteCount = new ByteArrayOutputStream();
            ExecWatch countWatch = operation.writingOutput(byteCount).exec(new String[]{"sh", "-c", String.format("wc -c < %s", PodOperationsImpl.shellQuote(file))});

            boolean var15;
            label91: {
               label92: {
                  boolean var14;
                  try {
                     CompletableFuture<Integer> countExitFuture = countWatch.exitCode();
                     if (Utils.waitUntilReady(countExitFuture, Math.max(0L, uploadRequestTimeoutEnd - System.currentTimeMillis()), TimeUnit.MILLISECONDS) && Integer.valueOf(0).equals(countExitFuture.getNow((Object)null))) {
                        String remoteSize = new String(byteCount.toByteArray(), StandardCharsets.UTF_8);
                        if (String.valueOf(expected).equals(remoteSize.trim())) {
                           break label92;
                        }

                        LOG.debug("upload file size validation failed, expected {}, but was {}", expected, remoteSize);
                        var15 = false;
                        break label91;
                     }

                     LOG.debug("failed to validate the upload size, exit code {}", countExitFuture.getNow((Object)null));
                     var14 = false;
                  } catch (Throwable var19) {
                     if (countWatch != null) {
                        try {
                           countWatch.close();
                        } catch (Throwable var16) {
                           var19.addSuppressed(var16);
                        }
                     }

                     throw var19;
                  }

                  if (countWatch != null) {
                     countWatch.close();
                  }

                  return var14;
               }

               if (countWatch != null) {
                  countWatch.close();
               }

               return true;
            }

            if (countWatch != null) {
               countWatch.close();
            }

            return var15;
         }
      }
   }

   public static boolean uploadFileData(PodOperationsImpl operation, InputStream inputStream) throws IOException {
      return upload(operation, operation.getContext().getFile(), (os) -> {
         Objects.requireNonNull(os);
         InputStreamPumper.transferTo(inputStream, os::write);
      });
   }

   private static boolean uploadTar(PodOperationsImpl operation, String directory, UploadProcessor processor) throws IOException {
      String fileName = String.format("%sfabric8-%s.tar", directory, Utils.generateId());
      boolean uploaded = upload(operation, fileName, (os) -> {
         TarArchiveOutputStream tar = new TarArchiveOutputStream(os);

         try {
            tar.setLongFileMode(3);
            tar.setBigNumberMode(2);
            processor.process(tar);
         } catch (Throwable var6) {
            try {
               tar.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         tar.close();
      });
      if (!uploaded) {
         ExecWatch rm = operation.writingOutput(new ByteArrayOutputStream()).exec(new String[]{"sh", "-c", String.format("rm %s", fileName)});

         try {
            if (!Utils.waitUntilReady(rm.exitCode(), (long)operation.getRequestConfig().getUploadRequestTimeout(), TimeUnit.MILLISECONDS) || !Integer.valueOf(0).equals(rm.exitCode().getNow((Object)null))) {
               LOG.warn("delete of temporary tar file {} may not have completed", fileName);
            }
         } catch (Throwable var11) {
            if (rm != null) {
               try {
                  rm.close();
               } catch (Throwable var10) {
                  var11.addSuppressed(var10);
               }
            }

            throw var11;
         }

         if (rm != null) {
            rm.close();
         }

         return false;
      } else {
         String command = extractTarCommand(directory, fileName);
         ExecWatch execWatch = operation.redirectingInput().exec("sh", "-c", command);

         boolean var8;
         try {
            CompletableFuture<Integer> countExitFuture = execWatch.exitCode();
            var8 = Utils.waitUntilReady(countExitFuture, (long)operation.getRequestConfig().getUploadRequestTimeout(), TimeUnit.MILLISECONDS) && Integer.valueOf(0).equals(countExitFuture.getNow((Object)null));
         } catch (Throwable var12) {
            if (execWatch != null) {
               try {
                  execWatch.close();
               } catch (Throwable var9) {
                  var12.addSuppressed(var9);
               }
            }

            throw var12;
         }

         if (execWatch != null) {
            execWatch.close();
         }

         return var8;
      }
   }

   static String extractTarCommand(String directory, String tar) {
      return String.format("mkdir -p %1$s; tar -C %1$s -xmf %2$s; e=$?; rm %2$s; exit $e", PodOperationsImpl.shellQuote(directory), tar);
   }

   private static void addFileToTar(String fileName, File file, TarArchiveOutputStream tar) throws IOException {
      tar.putArchiveEntry(new TarArchiveEntry(file, fileName));
      if (file.isFile()) {
         Files.copy(file.toPath(), tar);
         tar.closeArchiveEntry();
      } else if (file.isDirectory()) {
         tar.closeArchiveEntry();

         for(File fileInDirectory : (File[])Objects.requireNonNull(file.listFiles())) {
            addFileToTar(fileName + "/" + fileInDirectory.getName(), fileInDirectory, tar);
         }
      }

   }

   static String createExecCommandForUpload(String file) {
      return String.format("mkdir -p %s && cat - > %s && echo $?", PodOperationsImpl.shellQuote(getDirectoryFromFile(file)), PodOperationsImpl.shellQuote(file));
   }

   private static String ensureEndsWithSlash(String path) {
      return path.endsWith("/") ? path : path + "/";
   }
}
