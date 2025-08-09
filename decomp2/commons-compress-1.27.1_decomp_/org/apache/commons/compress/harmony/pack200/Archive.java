package org.apache.commons.compress.harmony.pack200;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.GZIPOutputStream;

public class Archive {
   private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
   private final JarInputStream jarInputStream;
   private final OutputStream outputStream;
   private JarFile jarFile;
   private long currentSegmentSize;
   private final PackingOptions options;

   public Archive(JarFile jarFile, OutputStream outputStream, PackingOptions options) throws IOException {
      if (options == null) {
         options = new PackingOptions();
      }

      this.options = options;
      if (options.isGzip()) {
         outputStream = new GZIPOutputStream(outputStream);
      }

      this.outputStream = new BufferedOutputStream(outputStream);
      this.jarFile = jarFile;
      this.jarInputStream = null;
      PackingUtils.config(options);
   }

   public Archive(JarInputStream inputStream, OutputStream outputStream, PackingOptions options) throws IOException {
      this.jarInputStream = inputStream;
      if (options == null) {
         options = new PackingOptions();
      }

      this.options = options;
      if (options.isGzip()) {
         outputStream = new GZIPOutputStream(outputStream);
      }

      this.outputStream = new BufferedOutputStream(outputStream);
      PackingUtils.config(options);
   }

   private boolean addJarEntry(PackingFile packingFile, List javaClasses, List files) {
      long segmentLimit = this.options.getSegmentLimit();
      if (segmentLimit != -1L && segmentLimit != 0L) {
         long packedSize = this.estimateSize(packingFile);
         if (packedSize + this.currentSegmentSize > segmentLimit && this.currentSegmentSize > 0L) {
            return false;
         }

         this.currentSegmentSize += packedSize;
      }

      String name = packingFile.getName();
      if (name.endsWith(".class") && !this.options.isPassFile(name)) {
         Pack200ClassReader classParser = new Pack200ClassReader(packingFile.contents);
         classParser.setFileName(name);
         javaClasses.add(classParser);
         packingFile.contents = EMPTY_BYTE_ARRAY;
      }

      files.add(packingFile);
      return true;
   }

   private void doNormalPack() throws IOException, Pack200Exception {
      PackingUtils.log("Start to perform a normal packing");
      List<PackingFile> packingFileList;
      if (this.jarInputStream != null) {
         packingFileList = PackingUtils.getPackingFileListFromJar(this.jarInputStream, this.options.isKeepFileOrder());
      } else {
         packingFileList = PackingUtils.getPackingFileListFromJar(this.jarFile, this.options.isKeepFileOrder());
      }

      List<SegmentUnit> segmentUnitList = this.splitIntoSegments(packingFileList);
      int previousByteAmount = 0;
      int packedByteAmount = 0;
      int segmentSize = segmentUnitList.size();

      for(int index = 0; index < segmentSize; ++index) {
         SegmentUnit segmentUnit = (SegmentUnit)segmentUnitList.get(index);
         (new Segment()).pack(segmentUnit, this.outputStream, this.options);
         previousByteAmount += segmentUnit.getByteAmount();
         packedByteAmount += segmentUnit.getPackedByteAmount();
      }

      PackingUtils.log("Total: Packed " + previousByteAmount + " input bytes of " + packingFileList.size() + " files into " + packedByteAmount + " bytes in " + segmentSize + " segments");
      this.outputStream.close();
   }

   private void doZeroEffortPack() throws IOException {
      PackingUtils.log("Start to perform a zero-effort packing");
      if (this.jarInputStream != null) {
         PackingUtils.copyThroughJar(this.jarInputStream, this.outputStream);
      } else {
         PackingUtils.copyThroughJar(this.jarFile, this.outputStream);
      }

   }

   private long estimateSize(PackingFile packingFile) {
      String name = packingFile.getName();
      if (!name.startsWith("META-INF") && !name.startsWith("/META-INF")) {
         long fileSize = (long)packingFile.contents.length;
         if (fileSize < 0L) {
            fileSize = 0L;
         }

         return (long)name.length() + fileSize + 5L;
      } else {
         return 0L;
      }
   }

   public void pack() throws Pack200Exception, IOException {
      if (0 == this.options.getEffort()) {
         this.doZeroEffortPack();
      } else {
         this.doNormalPack();
      }

   }

   private List splitIntoSegments(List packingFileList) {
      List<SegmentUnit> segmentUnitList = new ArrayList();
      List<Pack200ClassReader> classes = new ArrayList();
      List<PackingFile> files = new ArrayList();
      long segmentLimit = this.options.getSegmentLimit();
      int size = packingFileList.size();

      for(int index = 0; index < size; ++index) {
         PackingFile packingFile = (PackingFile)packingFileList.get(index);
         if (!this.addJarEntry(packingFile, classes, files)) {
            segmentUnitList.add(new SegmentUnit(classes, files));
            classes = new ArrayList();
            files = new ArrayList();
            this.currentSegmentSize = 0L;
            this.addJarEntry(packingFile, classes, files);
            this.currentSegmentSize = 0L;
         } else if (segmentLimit == 0L && this.estimateSize(packingFile) > 0L) {
            segmentUnitList.add(new SegmentUnit(classes, files));
            classes = new ArrayList();
            files = new ArrayList();
         }
      }

      if (classes.size() > 0 || files.size() > 0) {
         segmentUnitList.add(new SegmentUnit(classes, files));
      }

      return segmentUnitList;
   }

   static class PackingFile {
      private final String name;
      private byte[] contents;
      private final long modtime;
      private final boolean deflateHint;
      private final boolean isDirectory;

      PackingFile(byte[] bytes, JarEntry jarEntry) {
         this.name = jarEntry.getName();
         this.contents = bytes;
         this.modtime = jarEntry.getTime();
         this.deflateHint = jarEntry.getMethod() == 8;
         this.isDirectory = jarEntry.isDirectory();
      }

      PackingFile(String name, byte[] contents, long modtime) {
         this.name = name;
         this.contents = contents;
         this.modtime = modtime;
         this.deflateHint = false;
         this.isDirectory = false;
      }

      public byte[] getContents() {
         return this.contents;
      }

      public long getModtime() {
         return this.modtime;
      }

      public String getName() {
         return this.name;
      }

      public boolean isDefalteHint() {
         return this.deflateHint;
      }

      public boolean isDirectory() {
         return this.isDirectory;
      }

      public void setContents(byte[] contents) {
         this.contents = contents;
      }

      public String toString() {
         return this.name;
      }
   }

   static class SegmentUnit {
      private final List classList;
      private final List fileList;
      private int byteAmount;
      private int packedByteAmount;

      SegmentUnit(List classes, List files) {
         this.classList = classes;
         this.fileList = files;
         this.byteAmount = 0;
         this.byteAmount += this.classList.stream().mapToInt((element) -> element.b.length).sum();
         this.byteAmount += this.fileList.stream().mapToInt((element) -> element.contents.length).sum();
      }

      public void addPackedByteAmount(int amount) {
         this.packedByteAmount += amount;
      }

      public int classListSize() {
         return this.classList.size();
      }

      public int fileListSize() {
         return this.fileList.size();
      }

      public int getByteAmount() {
         return this.byteAmount;
      }

      public List getClassList() {
         return this.classList;
      }

      public List getFileList() {
         return this.fileList;
      }

      public int getPackedByteAmount() {
         return this.packedByteAmount;
      }
   }
}
