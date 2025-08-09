package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.io.file.attribute.FileTimes;

public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry, EntryStreamOffsets {
   private static final String ZIP_DIR_SEP = "/";
   static final ZipArchiveEntry[] EMPTY_ARRAY = new ZipArchiveEntry[0];
   static LinkedList EMPTY_LINKED_LIST = new LinkedList();
   public static final int PLATFORM_UNIX = 3;
   public static final int PLATFORM_FAT = 0;
   public static final int CRC_UNKNOWN = -1;
   private static final int SHORT_MASK = 65535;
   private static final int SHORT_SHIFT = 16;
   private int method;
   private long size;
   private int internalAttributes;
   private int versionRequired;
   private int versionMadeBy;
   private int platform;
   private int rawFlag;
   private long externalAttributes;
   private int alignment;
   private ZipExtraField[] extraFields;
   private UnparseableExtraFieldData unparseableExtra;
   private String name;
   private byte[] rawName;
   private GeneralPurposeBit generalPurposeBit;
   private long localHeaderOffset;
   private long dataOffset;
   private boolean isStreamContiguous;
   private NameSource nameSource;
   private final Function extraFieldFactory;
   private CommentSource commentSource;
   private long diskNumberStart;
   private boolean lastModifiedDateSet;
   private long time;

   private static boolean canConvertToInfoZipExtendedTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
      return FileTimes.isUnixTime(lastModifiedTime) && FileTimes.isUnixTime(lastAccessTime) && FileTimes.isUnixTime(creationTime);
   }

   private static boolean isDirectoryEntryName(String entryName) {
      return entryName.endsWith("/");
   }

   private static String toDirectoryEntryName(String entryName) {
      return isDirectoryEntryName(entryName) ? entryName : entryName + "/";
   }

   private static String toEntryName(File inputFile, String entryName) {
      return inputFile.isDirectory() ? toDirectoryEntryName(entryName) : entryName;
   }

   private static String toEntryName(Path inputPath, String entryName, LinkOption... options) {
      return Files.isDirectory(inputPath, options) ? toDirectoryEntryName(entryName) : entryName;
   }

   protected ZipArchiveEntry() {
      this("");
   }

   public ZipArchiveEntry(File inputFile, String entryName) {
      this((Function)null, (File)inputFile, (String)entryName);
   }

   private ZipArchiveEntry(Function extraFieldFactory, File inputFile, String entryName) {
      this(extraFieldFactory, toEntryName(inputFile, entryName));

      try {
         this.setAttributes(inputFile.toPath());
      } catch (IOException var5) {
         if (inputFile.isFile()) {
            this.setSize(inputFile.length());
         }

         this.setTime(inputFile.lastModified());
      }

   }

   private ZipArchiveEntry(Function extraFieldFactory, Path inputPath, String entryName, LinkOption... options) throws IOException {
      this(extraFieldFactory, toEntryName(inputPath, entryName, options));
      this.setAttributes(inputPath, options);
   }

   private ZipArchiveEntry(Function extraFieldFactory, String name) {
      super(name);
      this.method = -1;
      this.size = -1L;
      this.platform = 0;
      this.generalPurposeBit = new GeneralPurposeBit();
      this.localHeaderOffset = -1L;
      this.dataOffset = -1L;
      this.nameSource = ZipArchiveEntry.NameSource.NAME;
      this.commentSource = ZipArchiveEntry.CommentSource.COMMENT;
      this.time = -1L;
      this.extraFieldFactory = extraFieldFactory;
      this.setName(name);
   }

   private ZipArchiveEntry(Function extraFieldFactory, ZipEntry entry) throws ZipException {
      super(entry);
      this.method = -1;
      this.size = -1L;
      this.platform = 0;
      this.generalPurposeBit = new GeneralPurposeBit();
      this.localHeaderOffset = -1L;
      this.dataOffset = -1L;
      this.nameSource = ZipArchiveEntry.NameSource.NAME;
      this.commentSource = ZipArchiveEntry.CommentSource.COMMENT;
      this.time = -1L;
      this.extraFieldFactory = extraFieldFactory;
      this.setName(entry.getName());
      byte[] extra = entry.getExtra();
      if (extra != null) {
         this.setExtraFields(this.parseExtraFields(extra, true, ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT));
      } else {
         this.setExtra();
      }

      this.setMethod(entry.getMethod());
      this.size = entry.getSize();
   }

   public ZipArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
      this((Function)null, inputPath, entryName, options);
   }

   public ZipArchiveEntry(String name) {
      this((Function)null, name);
   }

   public ZipArchiveEntry(ZipArchiveEntry entry) throws ZipException {
      this((ZipEntry)entry);
      this.setInternalAttributes(entry.getInternalAttributes());
      this.setExternalAttributes(entry.getExternalAttributes());
      this.setExtraFields(entry.getAllExtraFieldsNoCopy());
      this.setPlatform(entry.getPlatform());
      GeneralPurposeBit other = entry.getGeneralPurposeBit();
      this.setGeneralPurposeBit(other == null ? null : (GeneralPurposeBit)other.clone());
   }

   public ZipArchiveEntry(ZipEntry entry) throws ZipException {
      this((Function)null, (ZipEntry)entry);
   }

   public void addAsFirstExtraField(ZipExtraField ze) {
      if (ze instanceof UnparseableExtraFieldData) {
         this.unparseableExtra = (UnparseableExtraFieldData)ze;
      } else {
         if (this.getExtraField(ze.getHeaderId()) != null) {
            this.internalRemoveExtraField(ze.getHeaderId());
         }

         ZipExtraField[] copy = this.extraFields;
         int newLen = this.extraFields != null ? this.extraFields.length + 1 : 1;
         this.extraFields = new ZipExtraField[newLen];
         this.extraFields[0] = ze;
         if (copy != null) {
            System.arraycopy(copy, 0, this.extraFields, 1, this.extraFields.length - 1);
         }
      }

      this.setExtra();
   }

   public void addExtraField(ZipExtraField ze) {
      this.internalAddExtraField(ze);
      this.setExtra();
   }

   private void addInfoZipExtendedTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
      X5455_ExtendedTimestamp infoZipTimestamp = new X5455_ExtendedTimestamp();
      if (lastModifiedTime != null) {
         infoZipTimestamp.setModifyFileTime(lastModifiedTime);
      }

      if (lastAccessTime != null) {
         infoZipTimestamp.setAccessFileTime(lastAccessTime);
      }

      if (creationTime != null) {
         infoZipTimestamp.setCreateFileTime(creationTime);
      }

      this.internalAddExtraField(infoZipTimestamp);
   }

   private void addNTFSTimestamp(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime) {
      X000A_NTFS ntfsTimestamp = new X000A_NTFS();
      if (lastModifiedTime != null) {
         ntfsTimestamp.setModifyFileTime(lastModifiedTime);
      }

      if (lastAccessTime != null) {
         ntfsTimestamp.setAccessFileTime(lastAccessTime);
      }

      if (creationTime != null) {
         ntfsTimestamp.setCreateFileTime(creationTime);
      }

      this.internalAddExtraField(ntfsTimestamp);
   }

   public Object clone() {
      ZipArchiveEntry e = (ZipArchiveEntry)super.clone();
      e.setInternalAttributes(this.getInternalAttributes());
      e.setExternalAttributes(this.getExternalAttributes());
      e.setExtraFields(this.getAllExtraFieldsNoCopy());
      return e;
   }

   private ZipExtraField[] copyOf(ZipExtraField[] src, int length) {
      return (ZipExtraField[])Arrays.copyOf(src, length);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         ZipArchiveEntry other = (ZipArchiveEntry)obj;
         String myName = this.getName();
         String otherName = other.getName();
         if (!Objects.equals(myName, otherName)) {
            return false;
         } else {
            String myComment = this.getComment();
            String otherComment = other.getComment();
            if (myComment == null) {
               myComment = "";
            }

            if (otherComment == null) {
               otherComment = "";
            }

            return Objects.equals(this.getLastModifiedTime(), other.getLastModifiedTime()) && Objects.equals(this.getLastAccessTime(), other.getLastAccessTime()) && Objects.equals(this.getCreationTime(), other.getCreationTime()) && myComment.equals(otherComment) && this.getInternalAttributes() == other.getInternalAttributes() && this.getPlatform() == other.getPlatform() && this.getExternalAttributes() == other.getExternalAttributes() && this.getMethod() == other.getMethod() && this.getSize() == other.getSize() && this.getCrc() == other.getCrc() && this.getCompressedSize() == other.getCompressedSize() && Arrays.equals(this.getCentralDirectoryExtra(), other.getCentralDirectoryExtra()) && Arrays.equals(this.getLocalFileDataExtra(), other.getLocalFileDataExtra()) && this.localHeaderOffset == other.localHeaderOffset && this.dataOffset == other.dataOffset && this.generalPurposeBit.equals(other.generalPurposeBit);
         }
      } else {
         return false;
      }
   }

   private ZipExtraField findMatching(ZipShort headerId, List fs) {
      return (ZipExtraField)fs.stream().filter((f) -> headerId.equals(f.getHeaderId())).findFirst().orElse((Object)null);
   }

   private ZipExtraField findUnparseable(List fs) {
      Stream var10000 = fs.stream();
      Objects.requireNonNull(UnparseableExtraFieldData.class);
      return (ZipExtraField)var10000.filter(UnparseableExtraFieldData.class::isInstance).findFirst().orElse((Object)null);
   }

   protected int getAlignment() {
      return this.alignment;
   }

   private ZipExtraField[] getAllExtraFields() {
      ZipExtraField[] allExtraFieldsNoCopy = this.getAllExtraFieldsNoCopy();
      return allExtraFieldsNoCopy == this.extraFields ? this.copyOf(allExtraFieldsNoCopy, allExtraFieldsNoCopy.length) : allExtraFieldsNoCopy;
   }

   private ZipExtraField[] getAllExtraFieldsNoCopy() {
      if (this.extraFields == null) {
         return this.getUnparseableOnly();
      } else {
         return this.unparseableExtra != null ? this.getMergedFields() : this.extraFields;
      }
   }

   public byte[] getCentralDirectoryExtra() {
      return ExtraFieldUtils.mergeCentralDirectoryData(this.getAllExtraFieldsNoCopy());
   }

   public CommentSource getCommentSource() {
      return this.commentSource;
   }

   public long getDataOffset() {
      return this.dataOffset;
   }

   public long getDiskNumberStart() {
      return this.diskNumberStart;
   }

   public long getExternalAttributes() {
      return this.externalAttributes;
   }

   public ZipExtraField getExtraField(ZipShort type) {
      if (this.extraFields != null) {
         for(ZipExtraField extraField : this.extraFields) {
            if (type.equals(extraField.getHeaderId())) {
               return extraField;
            }
         }
      }

      return null;
   }

   public ZipExtraField[] getExtraFields() {
      return this.getParseableExtraFields();
   }

   public ZipExtraField[] getExtraFields(boolean includeUnparseable) {
      return includeUnparseable ? this.getAllExtraFields() : this.getParseableExtraFields();
   }

   public ZipExtraField[] getExtraFields(ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
      if (parsingBehavior == ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT) {
         return this.getExtraFields(true);
      } else if (parsingBehavior == ZipArchiveEntry.ExtraFieldParsingMode.ONLY_PARSEABLE_LENIENT) {
         return this.getExtraFields(false);
      } else {
         byte[] local = this.getExtra();
         List<ZipExtraField> localFields = new ArrayList(Arrays.asList(this.parseExtraFields(local, true, parsingBehavior)));
         byte[] central = this.getCentralDirectoryExtra();
         List<ZipExtraField> centralFields = new ArrayList(Arrays.asList(this.parseExtraFields(central, false, parsingBehavior)));
         List<ZipExtraField> merged = new ArrayList();

         for(ZipExtraField l : localFields) {
            ZipExtraField c;
            if (l instanceof UnparseableExtraFieldData) {
               c = this.findUnparseable(centralFields);
            } else {
               c = this.findMatching(l.getHeaderId(), centralFields);
            }

            if (c != null) {
               byte[] cd = c.getCentralDirectoryData();
               if (cd != null && cd.length > 0) {
                  l.parseFromCentralDirectoryData(cd, 0, cd.length);
               }

               centralFields.remove(c);
            }

            merged.add(l);
         }

         merged.addAll(centralFields);
         return (ZipExtraField[])merged.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
      }
   }

   public GeneralPurposeBit getGeneralPurposeBit() {
      return this.generalPurposeBit;
   }

   public int getInternalAttributes() {
      return this.internalAttributes;
   }

   public Date getLastModifiedDate() {
      return new Date(this.getTime());
   }

   public byte[] getLocalFileDataExtra() {
      byte[] extra = this.getExtra();
      return extra != null ? extra : ByteUtils.EMPTY_BYTE_ARRAY;
   }

   public long getLocalHeaderOffset() {
      return this.localHeaderOffset;
   }

   private ZipExtraField[] getMergedFields() {
      ZipExtraField[] zipExtraFields = this.copyOf(this.extraFields, this.extraFields.length + 1);
      zipExtraFields[this.extraFields.length] = this.unparseableExtra;
      return zipExtraFields;
   }

   public int getMethod() {
      return this.method;
   }

   public String getName() {
      return this.name == null ? super.getName() : this.name;
   }

   public NameSource getNameSource() {
      return this.nameSource;
   }

   private ZipExtraField[] getParseableExtraFields() {
      ZipExtraField[] parseableExtraFields = this.getParseableExtraFieldsNoCopy();
      return parseableExtraFields == this.extraFields ? this.copyOf(parseableExtraFields, parseableExtraFields.length) : parseableExtraFields;
   }

   private ZipExtraField[] getParseableExtraFieldsNoCopy() {
      return this.extraFields == null ? ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY : this.extraFields;
   }

   public int getPlatform() {
      return this.platform;
   }

   public int getRawFlag() {
      return this.rawFlag;
   }

   public byte[] getRawName() {
      return this.rawName != null ? Arrays.copyOf(this.rawName, this.rawName.length) : null;
   }

   public long getSize() {
      return this.size;
   }

   public long getTime() {
      if (this.lastModifiedDateSet) {
         return this.getLastModifiedTime().toMillis();
      } else {
         return this.time != -1L ? this.time : super.getTime();
      }
   }

   public int getUnixMode() {
      return this.platform != 3 ? 0 : (int)(this.getExternalAttributes() >> 16 & 65535L);
   }

   public UnparseableExtraFieldData getUnparseableExtraFieldData() {
      return this.unparseableExtra;
   }

   private ZipExtraField[] getUnparseableOnly() {
      return this.unparseableExtra == null ? ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY : new ZipExtraField[]{this.unparseableExtra};
   }

   public int getVersionMadeBy() {
      return this.versionMadeBy;
   }

   public int getVersionRequired() {
      return this.versionRequired;
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   private void internalAddExtraField(ZipExtraField ze) {
      if (ze instanceof UnparseableExtraFieldData) {
         this.unparseableExtra = (UnparseableExtraFieldData)ze;
      } else if (this.extraFields == null) {
         this.extraFields = new ZipExtraField[]{ze};
      } else {
         if (this.getExtraField(ze.getHeaderId()) != null) {
            this.internalRemoveExtraField(ze.getHeaderId());
         }

         ZipExtraField[] zipExtraFields = this.copyOf(this.extraFields, this.extraFields.length + 1);
         zipExtraFields[zipExtraFields.length - 1] = ze;
         this.extraFields = zipExtraFields;
      }

   }

   private void internalRemoveExtraField(ZipShort type) {
      if (this.extraFields != null) {
         List<ZipExtraField> newResult = new ArrayList();

         for(ZipExtraField extraField : this.extraFields) {
            if (!type.equals(extraField.getHeaderId())) {
               newResult.add(extraField);
            }
         }

         if (this.extraFields.length != newResult.size()) {
            this.extraFields = (ZipExtraField[])newResult.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
         }
      }
   }

   private void internalSetLastModifiedTime(FileTime time) {
      super.setLastModifiedTime(time);
      this.time = time.toMillis();
      this.lastModifiedDateSet = true;
   }

   public boolean isDirectory() {
      return isDirectoryEntryName(this.getName());
   }

   public boolean isStreamContiguous() {
      return this.isStreamContiguous;
   }

   public boolean isUnixSymlink() {
      return (this.getUnixMode() & '\uf000') == 40960;
   }

   private void mergeExtraFields(ZipExtraField[] f, boolean local) {
      if (this.extraFields == null) {
         this.setExtraFields(f);
      } else {
         for(ZipExtraField element : f) {
            ZipExtraField existing;
            if (element instanceof UnparseableExtraFieldData) {
               existing = this.unparseableExtra;
            } else {
               existing = this.getExtraField(element.getHeaderId());
            }

            if (existing == null) {
               this.internalAddExtraField(element);
            } else {
               byte[] b = local ? element.getLocalFileDataData() : element.getCentralDirectoryData();

               try {
                  if (local) {
                     existing.parseFromLocalFileData(b, 0, b.length);
                  } else {
                     existing.parseFromCentralDirectoryData(b, 0, b.length);
                  }
               } catch (ZipException var11) {
                  UnrecognizedExtraField u = new UnrecognizedExtraField();
                  u.setHeaderId(existing.getHeaderId());
                  if (local) {
                     u.setLocalFileDataData(b);
                     u.setCentralDirectoryData(existing.getCentralDirectoryData());
                  } else {
                     u.setLocalFileDataData(existing.getLocalFileDataData());
                     u.setCentralDirectoryData(b);
                  }

                  this.internalRemoveExtraField(existing.getHeaderId());
                  this.internalAddExtraField(u);
               }
            }
         }

         this.setExtra();
      }

   }

   private ZipExtraField[] parseExtraFields(byte[] data, boolean local, final ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
      return this.extraFieldFactory != null ? ExtraFieldUtils.parse(data, local, new ExtraFieldParsingBehavior() {
         public ZipExtraField createExtraField(ZipShort headerId) throws ZipException, InstantiationException, IllegalAccessException {
            ZipExtraField field = (ZipExtraField)ZipArchiveEntry.this.extraFieldFactory.apply(headerId);
            return field == null ? parsingBehavior.createExtraField(headerId) : field;
         }

         public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
            return parsingBehavior.fill(field, data, off, len, local);
         }

         public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            return parsingBehavior.onUnparseableExtraField(data, off, len, local, claimedLength);
         }
      }) : ExtraFieldUtils.parse(data, local, parsingBehavior);
   }

   public void removeExtraField(ZipShort type) {
      if (this.getExtraField(type) == null) {
         throw new NoSuchElementException();
      } else {
         this.internalRemoveExtraField(type);
         this.setExtra();
      }
   }

   public void removeUnparseableExtraFieldData() {
      if (this.unparseableExtra == null) {
         throw new NoSuchElementException();
      } else {
         this.unparseableExtra = null;
         this.setExtra();
      }
   }

   private boolean requiresExtraTimeFields() {
      return this.getLastAccessTime() == null && this.getCreationTime() == null ? this.lastModifiedDateSet : true;
   }

   public void setAlignment(int alignment) {
      if ((alignment & alignment - 1) == 0 && alignment <= 65535) {
         this.alignment = alignment;
      } else {
         throw new IllegalArgumentException("Invalid value for alignment, must be power of two and no bigger than 65535 but is " + alignment);
      }
   }

   private void setAttributes(Path inputPath, LinkOption... options) throws IOException {
      BasicFileAttributes attributes = Files.readAttributes(inputPath, BasicFileAttributes.class, options);
      if (attributes.isRegularFile()) {
         this.setSize(attributes.size());
      }

      super.setLastModifiedTime(attributes.lastModifiedTime());
      super.setCreationTime(attributes.creationTime());
      super.setLastAccessTime(attributes.lastAccessTime());
      this.setExtraTimeFields();
   }

   public void setCentralDirectoryExtra(byte[] b) {
      try {
         this.mergeExtraFields(this.parseExtraFields(b, false, ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT), false);
      } catch (ZipException e) {
         throw new IllegalArgumentException(e.getMessage(), e);
      }
   }

   public void setCommentSource(CommentSource commentSource) {
      this.commentSource = commentSource;
   }

   public ZipEntry setCreationTime(FileTime time) {
      super.setCreationTime(time);
      this.setExtraTimeFields();
      return this;
   }

   protected void setDataOffset(long dataOffset) {
      this.dataOffset = dataOffset;
   }

   public void setDiskNumberStart(long diskNumberStart) {
      this.diskNumberStart = diskNumberStart;
   }

   public void setExternalAttributes(long value) {
      this.externalAttributes = value;
   }

   protected void setExtra() {
      super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(this.getAllExtraFieldsNoCopy()));
      this.updateTimeFieldsFromExtraFields();
   }

   public void setExtra(byte[] extra) throws RuntimeException {
      try {
         this.mergeExtraFields(this.parseExtraFields(extra, true, ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT), true);
      } catch (ZipException e) {
         throw new IllegalArgumentException("Error parsing extra fields for entry: " + this.getName() + " - " + e.getMessage(), e);
      }
   }

   public void setExtraFields(ZipExtraField[] fields) {
      this.unparseableExtra = null;
      List<ZipExtraField> newFields = new ArrayList();
      if (fields != null) {
         for(ZipExtraField field : fields) {
            if (field instanceof UnparseableExtraFieldData) {
               this.unparseableExtra = (UnparseableExtraFieldData)field;
            } else {
               newFields.add(field);
            }
         }
      }

      this.extraFields = (ZipExtraField[])newFields.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
      this.setExtra();
   }

   private void setExtraTimeFields() {
      if (this.getExtraField(X5455_ExtendedTimestamp.HEADER_ID) != null) {
         this.internalRemoveExtraField(X5455_ExtendedTimestamp.HEADER_ID);
      }

      if (this.getExtraField(X000A_NTFS.HEADER_ID) != null) {
         this.internalRemoveExtraField(X000A_NTFS.HEADER_ID);
      }

      if (this.requiresExtraTimeFields()) {
         FileTime lastModifiedTime = this.getLastModifiedTime();
         FileTime lastAccessTime = this.getLastAccessTime();
         FileTime creationTime = this.getCreationTime();
         if (canConvertToInfoZipExtendedTimestamp(lastModifiedTime, lastAccessTime, creationTime)) {
            this.addInfoZipExtendedTimestamp(lastModifiedTime, lastAccessTime, creationTime);
         }

         this.addNTFSTimestamp(lastModifiedTime, lastAccessTime, creationTime);
      }

      this.setExtra();
   }

   public void setGeneralPurposeBit(GeneralPurposeBit generalPurposeBit) {
      this.generalPurposeBit = generalPurposeBit;
   }

   public void setInternalAttributes(int internalAttributes) {
      this.internalAttributes = internalAttributes;
   }

   public ZipEntry setLastAccessTime(FileTime fileTime) {
      super.setLastAccessTime(fileTime);
      this.setExtraTimeFields();
      return this;
   }

   public ZipEntry setLastModifiedTime(FileTime fileTime) {
      this.internalSetLastModifiedTime(fileTime);
      this.setExtraTimeFields();
      return this;
   }

   protected void setLocalHeaderOffset(long localHeaderOffset) {
      this.localHeaderOffset = localHeaderOffset;
   }

   public void setMethod(int method) {
      if (method < 0) {
         throw new IllegalArgumentException("ZIP compression method can not be negative: " + method);
      } else {
         this.method = method;
      }
   }

   protected void setName(String name) {
      if (name != null && this.getPlatform() == 0 && !name.contains("/")) {
         name = name.replace('\\', '/');
      }

      this.name = name;
   }

   protected void setName(String name, byte[] rawName) {
      this.setName(name);
      this.rawName = rawName;
   }

   public void setNameSource(NameSource nameSource) {
      this.nameSource = nameSource;
   }

   protected void setPlatform(int platform) {
      this.platform = platform;
   }

   public void setRawFlag(int rawFlag) {
      this.rawFlag = rawFlag;
   }

   public void setSize(long size) {
      if (size < 0L) {
         throw new IllegalArgumentException("Invalid entry size");
      } else {
         this.size = size;
      }
   }

   protected void setStreamContiguous(boolean isStreamContiguous) {
      this.isStreamContiguous = isStreamContiguous;
   }

   public void setTime(FileTime fileTime) {
      this.setTime(fileTime.toMillis());
   }

   public void setTime(long timeEpochMillis) {
      if (ZipUtil.isDosTime(timeEpochMillis)) {
         super.setTime(timeEpochMillis);
         this.time = timeEpochMillis;
         this.lastModifiedDateSet = false;
         this.setExtraTimeFields();
      } else {
         this.setLastModifiedTime(FileTime.fromMillis(timeEpochMillis));
      }

   }

   public void setUnixMode(int mode) {
      this.setExternalAttributes((long)(mode << 16 | ((mode & 128) == 0 ? 1 : 0) | (this.isDirectory() ? 16 : 0)));
      this.platform = 3;
   }

   public void setVersionMadeBy(int versionMadeBy) {
      this.versionMadeBy = versionMadeBy;
   }

   public void setVersionRequired(int versionRequired) {
      this.versionRequired = versionRequired;
   }

   private void updateTimeFieldsFromExtraFields() {
      this.updateTimeFromExtendedTimestampField();
      this.updateTimeFromNtfsField();
   }

   private void updateTimeFromExtendedTimestampField() {
      ZipExtraField extraField = this.getExtraField(X5455_ExtendedTimestamp.HEADER_ID);
      if (extraField instanceof X5455_ExtendedTimestamp) {
         X5455_ExtendedTimestamp extendedTimestamp = (X5455_ExtendedTimestamp)extraField;
         if (extendedTimestamp.isBit0_modifyTimePresent()) {
            FileTime modifyTime = extendedTimestamp.getModifyFileTime();
            if (modifyTime != null) {
               this.internalSetLastModifiedTime(modifyTime);
            }
         }

         if (extendedTimestamp.isBit1_accessTimePresent()) {
            FileTime accessTime = extendedTimestamp.getAccessFileTime();
            if (accessTime != null) {
               super.setLastAccessTime(accessTime);
            }
         }

         if (extendedTimestamp.isBit2_createTimePresent()) {
            FileTime creationTime = extendedTimestamp.getCreateFileTime();
            if (creationTime != null) {
               super.setCreationTime(creationTime);
            }
         }
      }

   }

   private void updateTimeFromNtfsField() {
      ZipExtraField extraField = this.getExtraField(X000A_NTFS.HEADER_ID);
      if (extraField instanceof X000A_NTFS) {
         X000A_NTFS ntfsTimestamp = (X000A_NTFS)extraField;
         FileTime modifyTime = ntfsTimestamp.getModifyFileTime();
         if (modifyTime != null) {
            this.internalSetLastModifiedTime(modifyTime);
         }

         FileTime accessTime = ntfsTimestamp.getAccessFileTime();
         if (accessTime != null) {
            super.setLastAccessTime(accessTime);
         }

         FileTime creationTime = ntfsTimestamp.getCreateFileTime();
         if (creationTime != null) {
            super.setCreationTime(creationTime);
         }
      }

   }

   public static enum CommentSource {
      COMMENT,
      UNICODE_EXTRA_FIELD;

      // $FF: synthetic method
      private static CommentSource[] $values() {
         return new CommentSource[]{COMMENT, UNICODE_EXTRA_FIELD};
      }
   }

   public static enum ExtraFieldParsingMode implements ExtraFieldParsingBehavior {
      BEST_EFFORT(ExtraFieldUtils.UnparseableExtraField.READ) {
         public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
            return ZipArchiveEntry.ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
         }
      },
      STRICT_FOR_KNOW_EXTRA_FIELDS(ExtraFieldUtils.UnparseableExtraField.READ),
      ONLY_PARSEABLE_LENIENT(ExtraFieldUtils.UnparseableExtraField.SKIP) {
         public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) {
            return ZipArchiveEntry.ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(field, data, off, len, local);
         }
      },
      ONLY_PARSEABLE_STRICT(ExtraFieldUtils.UnparseableExtraField.SKIP),
      DRACONIC(ExtraFieldUtils.UnparseableExtraField.THROW);

      private final ExtraFieldUtils.UnparseableExtraField onUnparseableData;

      private static ZipExtraField fillAndMakeUnrecognizedOnError(ZipExtraField field, byte[] data, int off, int len, boolean local) {
         try {
            return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
         } catch (ZipException var7) {
            UnrecognizedExtraField u = new UnrecognizedExtraField();
            u.setHeaderId(field.getHeaderId());
            if (local) {
               u.setLocalFileDataData(Arrays.copyOfRange(data, off, off + len));
            } else {
               u.setCentralDirectoryData(Arrays.copyOfRange(data, off, off + len));
            }

            return u;
         }
      }

      private ExtraFieldParsingMode(ExtraFieldUtils.UnparseableExtraField onUnparseableData) {
         this.onUnparseableData = onUnparseableData;
      }

      public ZipExtraField createExtraField(ZipShort headerId) {
         return ExtraFieldUtils.createExtraField(headerId);
      }

      public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
         return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
      }

      public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
         return this.onUnparseableData.onUnparseableExtraField(data, off, len, local, claimedLength);
      }

      // $FF: synthetic method
      private static ExtraFieldParsingMode[] $values() {
         return new ExtraFieldParsingMode[]{BEST_EFFORT, STRICT_FOR_KNOW_EXTRA_FIELDS, ONLY_PARSEABLE_LENIENT, ONLY_PARSEABLE_STRICT, DRACONIC};
      }
   }

   public static enum NameSource {
      NAME,
      NAME_WITH_EFS_FLAG,
      UNICODE_EXTRA_FIELD;

      // $FF: synthetic method
      private static NameSource[] $values() {
         return new NameSource[]{NAME, NAME_WITH_EFS_FLAG, UNICODE_EXTRA_FIELD};
      }
   }
}
