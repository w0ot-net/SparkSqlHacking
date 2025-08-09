package org.apache.hadoop.hive.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompressionUtils {
   static final Logger LOG = LoggerFactory.getLogger(CompressionUtils.class);

   public static void tar(String parentDir, String[] inputFiles, String outputFile) throws IOException {
      FileOutputStream out = null;

      try {
         out = new FileOutputStream(new File(parentDir, outputFile));
         TarArchiveOutputStream tOut = new TarArchiveOutputStream(new GzipCompressorOutputStream(new BufferedOutputStream(out)));

         for(int i = 0; i < inputFiles.length; ++i) {
            File f = new File(parentDir, inputFiles[i]);
            TarArchiveEntry tarEntry = new TarArchiveEntry(f, f.getName());
            tOut.setLongFileMode(2);
            tOut.putArchiveEntry(tarEntry);
            FileInputStream input = new FileInputStream(f);

            try {
               IOUtils.copy(input, tOut);
            } finally {
               input.close();
            }

            tOut.closeArchiveEntry();
         }

         tOut.close();
      } finally {
         org.apache.hadoop.io.IOUtils.closeStream(out);
      }

   }

   public static void zip(String parentDir, String[] inputFiles, String outputFile) throws IOException {
      ZipOutputStream output = null;

      try {
         output = new ZipOutputStream(new FileOutputStream(new File(parentDir, outputFile)));

         for(int i = 0; i < inputFiles.length; ++i) {
            File f = new File(parentDir, inputFiles[i]);
            FileInputStream input = new FileInputStream(f);
            output.putNextEntry(new ZipEntry(inputFiles[i]));

            try {
               IOUtils.copy(input, output);
            } finally {
               input.close();
            }
         }
      } finally {
         org.apache.hadoop.io.IOUtils.closeStream(output);
      }

   }

   public static List unTar(String inputFileName, String outputDirName) throws FileNotFoundException, IOException, ArchiveException {
      return unTar(inputFileName, outputDirName, false);
   }

   public static List unTar(String inputFileName, String outputDirName, boolean flatten) throws FileNotFoundException, IOException, ArchiveException {
      File inputFile = new File(inputFileName);
      File outputDir = new File(outputDirName);
      List<File> untaredFiles = new LinkedList();
      InputStream is;
      if (inputFileName.endsWith(".gz")) {
         is = new GzipCompressorInputStream(new FileInputStream(inputFile));
      } else {
         is = new FileInputStream(inputFile);
      }

      TarArchiveInputStream debInputStream = (TarArchiveInputStream)(new ArchiveStreamFactory()).createArchiveInputStream("tar", is);
      TarArchiveEntry entry = null;

      while((entry = (TarArchiveEntry)debInputStream.getNextEntry()) != null) {
         File outputFile = new File(outputDir, entry.getName());
         if (entry.isDirectory()) {
            if (flatten) {
               continue;
            }

            LOG.debug(String.format("Attempting to write output directory %s.", outputFile.getAbsolutePath()));
            if (!outputFile.exists()) {
               LOG.debug(String.format("Attempting to create output directory %s.", outputFile.getAbsolutePath()));
               if (!outputFile.mkdirs()) {
                  throw new IllegalStateException(String.format("Couldn't create directory %s.", outputFile.getAbsolutePath()));
               }
            }
         } else {
            OutputStream outputFileStream;
            if (flatten) {
               File flatOutputFile = new File(outputDir, outputFile.getName());
               LOG.debug(String.format("Creating flat output file %s.", flatOutputFile.getAbsolutePath()));
               outputFileStream = new FileOutputStream(flatOutputFile);
            } else if (!outputFile.getParentFile().exists()) {
               LOG.debug(String.format("Attempting to create output directory %s.", outputFile.getParentFile().getAbsoluteFile()));
               if (!outputFile.getParentFile().getAbsoluteFile().mkdirs()) {
                  throw new IllegalStateException(String.format("Couldn't create directory %s.", outputFile.getParentFile().getAbsolutePath()));
               }

               LOG.debug(String.format("Creating output file %s.", outputFile.getAbsolutePath()));
               outputFileStream = new FileOutputStream(outputFile);
            } else {
               outputFileStream = new FileOutputStream(outputFile);
            }

            IOUtils.copy(debInputStream, outputFileStream);
            outputFileStream.close();
         }

         untaredFiles.add(outputFile);
      }

      debInputStream.close();
      return untaredFiles;
   }
}
