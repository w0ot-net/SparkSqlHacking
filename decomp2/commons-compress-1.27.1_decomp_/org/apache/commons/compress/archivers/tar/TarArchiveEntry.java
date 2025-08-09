package org.apache.commons.compress.archivers.tar;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.ParsingUtils;
import org.apache.commons.compress.utils.TimeUtils;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.lang3.SystemProperties;

public class TarArchiveEntry implements ArchiveEntry, TarConstants, EntryStreamOffsets {
   private static final TarArchiveEntry[] EMPTY_TAR_ARCHIVE_ENTRY_ARRAY = new TarArchiveEntry[0];
   public static final long UNKNOWN = -1L;
   public static final int MAX_NAMELEN = 31;
   public static final int DEFAULT_DIR_MODE = 16877;
   public static final int DEFAULT_FILE_MODE = 33188;
   /** @deprecated */
   @Deprecated
   public static final int MILLIS_PER_SECOND = 1000;
   private static final Pattern PAX_EXTENDED_HEADER_FILE_TIMES_PATTERN = Pattern.compile("-?\\d{1,19}(?:\\.\\d{1,19})?");
   private String name;
   private final boolean preserveAbsolutePath;
   private int mode;
   private long userId;
   private long groupId;
   private long size;
   private FileTime mTime;
   private FileTime cTime;
   private FileTime aTime;
   private FileTime birthTime;
   private boolean checkSumOK;
   private byte linkFlag;
   private String linkName;
   private String magic;
   private String version;
   private String userName;
   private String groupName;
   private int devMajor;
   private int devMinor;
   private List sparseHeaders;
   private boolean isExtended;
   private long realSize;
   private boolean paxGNUSparse;
   private boolean paxGNU1XSparse;
   private boolean starSparse;
   private final Path file;
   private final LinkOption[] linkOptions;
   private final Map extraPaxHeaders;
   private long dataOffset;

   private static FileTime fileTimeFromOptionalSeconds(long seconds) {
      return seconds <= 0L ? null : FileTimes.fromUnixTime(seconds);
   }

   private static String normalizeFileName(String fileName, boolean preserveAbsolutePath) {
      if (!preserveAbsolutePath) {
         String property = SystemProperties.getOsName();
         if (property != null) {
            String osName = property.toLowerCase(Locale.ROOT);
            if (osName.startsWith("windows")) {
               if (fileName.length() > 2) {
                  char ch1 = fileName.charAt(0);
                  char ch2 = fileName.charAt(1);
                  if (ch2 == ':' && (ch1 >= 'a' && ch1 <= 'z' || ch1 >= 'A' && ch1 <= 'Z')) {
                     fileName = fileName.substring(2);
                  }
               }
            } else if (osName.contains("netware")) {
               int colon = fileName.indexOf(58);
               if (colon != -1) {
                  fileName = fileName.substring(colon + 1);
               }
            }
         }
      }

      for(fileName = fileName.replace(File.separatorChar, '/'); !preserveAbsolutePath && fileName.startsWith("/"); fileName = fileName.substring(1)) {
      }

      return fileName;
   }

   private static Instant parseInstantFromDecimalSeconds(String value) throws IOException {
      if (!PAX_EXTENDED_HEADER_FILE_TIMES_PATTERN.matcher(value).matches()) {
         throw new IOException("Corrupted PAX header. Time field value is invalid '" + value + "'");
      } else {
         BigDecimal epochSeconds = new BigDecimal(value);
         long seconds = epochSeconds.longValue();
         long nanos = epochSeconds.remainder(BigDecimal.ONE).movePointRight(9).longValue();

         try {
            return Instant.ofEpochSecond(seconds, nanos);
         } catch (ArithmeticException | DateTimeException e) {
            throw new IOException("Corrupted PAX header. Time field value is invalid '" + value + "'", e);
         }
      }
   }

   private TarArchiveEntry(boolean preserveAbsolutePath) {
      this.name = "";
      this.linkName = "";
      this.magic = "ustar\u0000";
      this.version = "00";
      this.groupName = "";
      this.extraPaxHeaders = new HashMap();
      this.dataOffset = -1L;
      String user = System.getProperty("user.name", "");
      if (user.length() > 31) {
         user = user.substring(0, 31);
      }

      this.userName = user;
      this.file = null;
      this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;
      this.preserveAbsolutePath = preserveAbsolutePath;
   }

   public TarArchiveEntry(byte[] headerBuf) {
      this(false);
      this.parseTarHeader(headerBuf);
   }

   public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding) throws IOException {
      this(headerBuf, encoding, false);
   }

   public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding, boolean lenient) throws IOException {
      this(Collections.emptyMap(), headerBuf, encoding, lenient);
   }

   public TarArchiveEntry(byte[] headerBuf, ZipEncoding encoding, boolean lenient, long dataOffset) throws IOException {
      this(headerBuf, encoding, lenient);
      this.setDataOffset(dataOffset);
   }

   public TarArchiveEntry(File file) {
      this(file, file.getPath());
   }

   public TarArchiveEntry(File file, String fileName) {
      this.name = "";
      this.linkName = "";
      this.magic = "ustar\u0000";
      this.version = "00";
      this.groupName = "";
      this.extraPaxHeaders = new HashMap();
      this.dataOffset = -1L;
      String normalizedName = normalizeFileName(fileName, false);
      this.file = file.toPath();
      this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;

      try {
         this.readFileMode(this.file, normalizedName);
      } catch (IOException var6) {
         if (!file.isDirectory()) {
            this.size = file.length();
         }
      }

      this.userName = "";

      try {
         this.readOsSpecificProperties(this.file);
      } catch (IOException var5) {
         this.mTime = FileTime.fromMillis(file.lastModified());
      }

      this.preserveAbsolutePath = false;
   }

   public TarArchiveEntry(Map globalPaxHeaders, byte[] headerBuf, ZipEncoding encoding, boolean lenient) throws IOException {
      this(false);
      this.parseTarHeader(globalPaxHeaders, headerBuf, encoding, false, lenient);
   }

   public TarArchiveEntry(Map globalPaxHeaders, byte[] headerBuf, ZipEncoding encoding, boolean lenient, long dataOffset) throws IOException {
      this(globalPaxHeaders, headerBuf, encoding, lenient);
      this.setDataOffset(dataOffset);
   }

   public TarArchiveEntry(Path file) throws IOException {
      this(file, file.toString());
   }

   public TarArchiveEntry(Path file, String fileName, LinkOption... linkOptions) throws IOException {
      this.name = "";
      this.linkName = "";
      this.magic = "ustar\u0000";
      this.version = "00";
      this.groupName = "";
      this.extraPaxHeaders = new HashMap();
      this.dataOffset = -1L;
      String normalizedName = normalizeFileName(fileName, false);
      this.file = file;
      this.linkOptions = linkOptions == null ? IOUtils.EMPTY_LINK_OPTIONS : linkOptions;
      this.readFileMode(file, normalizedName, linkOptions);
      this.userName = "";
      this.readOsSpecificProperties(file);
      this.preserveAbsolutePath = false;
   }

   public TarArchiveEntry(String name) {
      this(name, false);
   }

   public TarArchiveEntry(String name, boolean preserveAbsolutePath) {
      this(preserveAbsolutePath);
      name = normalizeFileName(name, preserveAbsolutePath);
      boolean isDir = name.endsWith("/");
      this.name = name;
      this.mode = isDir ? 16877 : '膤';
      this.linkFlag = (byte)(isDir ? 53 : 48);
      this.mTime = FileTime.from(Instant.now());
      this.userName = "";
   }

   public TarArchiveEntry(String name, byte linkFlag) {
      this(name, linkFlag, false);
   }

   public TarArchiveEntry(String name, byte linkFlag, boolean preserveAbsolutePath) {
      this(name, preserveAbsolutePath);
      this.linkFlag = linkFlag;
      if (linkFlag == 76) {
         this.magic = "ustar ";
         this.version = " \u0000";
      }

   }

   public void addPaxHeader(String name, String value) {
      try {
         this.processPaxHeader(name, value);
      } catch (IOException ex) {
         throw new IllegalArgumentException("Invalid input", ex);
      }
   }

   public void clearExtraPaxHeaders() {
      this.extraPaxHeaders.clear();
   }

   public boolean equals(Object it) {
      return it != null && this.getClass() == it.getClass() ? this.equals((TarArchiveEntry)it) : false;
   }

   public boolean equals(TarArchiveEntry it) {
      return it != null && this.getName().equals(it.getName());
   }

   private int evaluateType(Map globalPaxHeaders, byte[] header) {
      if (ArchiveUtils.matchAsciiBuffer("ustar ", header, 257, 6)) {
         return 2;
      } else if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", header, 257, 6)) {
         return this.isXstar(globalPaxHeaders, header) ? 4 : 3;
      } else {
         return 0;
      }
   }

   private int fill(byte value, int offset, byte[] outbuf, int length) {
      for(int i = 0; i < length; ++i) {
         outbuf[offset + i] = value;
      }

      return offset + length;
   }

   private int fill(int value, int offset, byte[] outbuf, int length) {
      return this.fill((byte)value, offset, outbuf, length);
   }

   void fillGNUSparse0xData(Map headers) throws IOException {
      this.paxGNUSparse = true;
      this.realSize = (long)ParsingUtils.parseIntValue((String)headers.get("GNU.sparse.size"));
      if (headers.containsKey("GNU.sparse.name")) {
         this.name = (String)headers.get("GNU.sparse.name");
      }

   }

   void fillGNUSparse1xData(Map headers) throws IOException {
      this.paxGNUSparse = true;
      this.paxGNU1XSparse = true;
      if (headers.containsKey("GNU.sparse.name")) {
         this.name = (String)headers.get("GNU.sparse.name");
      }

      if (headers.containsKey("GNU.sparse.realsize")) {
         this.realSize = (long)ParsingUtils.parseIntValue((String)headers.get("GNU.sparse.realsize"));
      }

   }

   void fillStarSparseData(Map headers) throws IOException {
      this.starSparse = true;
      if (headers.containsKey("SCHILY.realsize")) {
         this.realSize = ParsingUtils.parseLongValue((String)headers.get("SCHILY.realsize"));
      }

   }

   public FileTime getCreationTime() {
      return this.birthTime;
   }

   public long getDataOffset() {
      return this.dataOffset;
   }

   public int getDevMajor() {
      return this.devMajor;
   }

   public int getDevMinor() {
      return this.devMinor;
   }

   public TarArchiveEntry[] getDirectoryEntries() {
      if (this.file != null && this.isDirectory()) {
         List<TarArchiveEntry> entries = new ArrayList();

         try {
            DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.file);

            try {
               for(Path p : dirStream) {
                  entries.add(new TarArchiveEntry(p));
               }
            } catch (Throwable var6) {
               if (dirStream != null) {
                  try {
                     dirStream.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (dirStream != null) {
               dirStream.close();
            }
         } catch (IOException var7) {
            return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
         }

         return (TarArchiveEntry[])entries.toArray(EMPTY_TAR_ARCHIVE_ENTRY_ARRAY);
      } else {
         return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
      }
   }

   public String getExtraPaxHeader(String name) {
      return (String)this.extraPaxHeaders.get(name);
   }

   public Map getExtraPaxHeaders() {
      return Collections.unmodifiableMap(this.extraPaxHeaders);
   }

   public File getFile() {
      return this.file == null ? null : this.file.toFile();
   }

   /** @deprecated */
   @Deprecated
   public int getGroupId() {
      return (int)(this.groupId & -1L);
   }

   public String getGroupName() {
      return this.groupName;
   }

   public FileTime getLastAccessTime() {
      return this.aTime;
   }

   public Date getLastModifiedDate() {
      return this.getModTime();
   }

   public FileTime getLastModifiedTime() {
      return this.mTime;
   }

   public byte getLinkFlag() {
      return this.linkFlag;
   }

   public String getLinkName() {
      return this.linkName;
   }

   public long getLongGroupId() {
      return this.groupId;
   }

   public long getLongUserId() {
      return this.userId;
   }

   public int getMode() {
      return this.mode;
   }

   public Date getModTime() {
      FileTime fileTime = this.mTime;
      return FileTimes.toDate(fileTime);
   }

   public String getName() {
      return this.name;
   }

   public List getOrderedSparseHeaders() throws IOException {
      if (this.sparseHeaders != null && !this.sparseHeaders.isEmpty()) {
         List<TarArchiveStructSparse> orderedAndFiltered = (List)this.sparseHeaders.stream().filter((s) -> s.getOffset() > 0L || s.getNumbytes() > 0L).sorted(Comparator.comparingLong(TarArchiveStructSparse::getOffset)).collect(Collectors.toList());
         int numberOfHeaders = orderedAndFiltered.size();

         for(int i = 0; i < numberOfHeaders; ++i) {
            TarArchiveStructSparse str = (TarArchiveStructSparse)orderedAndFiltered.get(i);
            if (i + 1 < numberOfHeaders && str.getOffset() + str.getNumbytes() > ((TarArchiveStructSparse)orderedAndFiltered.get(i + 1)).getOffset()) {
               throw new IOException("Corrupted TAR archive. Sparse blocks for " + this.getName() + " overlap each other.");
            }

            if (str.getOffset() + str.getNumbytes() < 0L) {
               throw new IOException("Unreadable TAR archive. Offset and numbytes for sparse block in " + this.getName() + " too large.");
            }
         }

         if (!orderedAndFiltered.isEmpty()) {
            TarArchiveStructSparse last = (TarArchiveStructSparse)orderedAndFiltered.get(numberOfHeaders - 1);
            if (last.getOffset() + last.getNumbytes() > this.getRealSize()) {
               throw new IOException("Corrupted TAR archive. Sparse block extends beyond real size of the entry");
            }
         }

         return orderedAndFiltered;
      } else {
         return Collections.emptyList();
      }
   }

   public Path getPath() {
      return this.file;
   }

   public long getRealSize() {
      return !this.isSparse() ? this.getSize() : this.realSize;
   }

   public long getSize() {
      return this.size;
   }

   public List getSparseHeaders() {
      return this.sparseHeaders;
   }

   public FileTime getStatusChangeTime() {
      return this.cTime;
   }

   /** @deprecated */
   @Deprecated
   public int getUserId() {
      return (int)(this.userId & -1L);
   }

   public String getUserName() {
      return this.userName;
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public boolean isBlockDevice() {
      return this.linkFlag == 52;
   }

   public boolean isCharacterDevice() {
      return this.linkFlag == 51;
   }

   public boolean isCheckSumOK() {
      return this.checkSumOK;
   }

   public boolean isDescendent(TarArchiveEntry desc) {
      return desc.getName().startsWith(this.getName());
   }

   public boolean isDirectory() {
      if (this.file != null) {
         return Files.isDirectory(this.file, this.linkOptions);
      } else if (this.linkFlag == 53) {
         return true;
      } else {
         return !this.isPaxHeader() && !this.isGlobalPaxHeader() && this.getName().endsWith("/");
      }
   }

   public boolean isExtended() {
      return this.isExtended;
   }

   public boolean isFIFO() {
      return this.linkFlag == 54;
   }

   public boolean isFile() {
      if (this.file != null) {
         return Files.isRegularFile(this.file, this.linkOptions);
      } else if (this.linkFlag != 0 && this.linkFlag != 48) {
         return this.linkFlag != 53 && !this.getName().endsWith("/");
      } else {
         return true;
      }
   }

   public boolean isGlobalPaxHeader() {
      return this.linkFlag == 103;
   }

   public boolean isGNULongLinkEntry() {
      return this.linkFlag == 75;
   }

   public boolean isGNULongNameEntry() {
      return this.linkFlag == 76;
   }

   public boolean isGNUSparse() {
      return this.isOldGNUSparse() || this.isPaxGNUSparse();
   }

   private boolean isInvalidPrefix(byte[] header) {
      if (header[475] != 0) {
         if (header[156] != 77) {
            return true;
         }

         if ((header[464] & 128) == 0 && header[475] != 32) {
            return true;
         }
      }

      return false;
   }

   private boolean isInvalidXtarTime(byte[] buffer, int offset, int length) {
      if ((buffer[offset] & 128) == 0) {
         int lastIndex = length - 1;

         for(int i = 0; i < lastIndex; ++i) {
            byte b = buffer[offset + i];
            if (b < 48 || b > 55) {
               return true;
            }
         }

         byte b = buffer[offset + lastIndex];
         if (b != 32 && b != 0) {
            return true;
         }
      }

      return false;
   }

   public boolean isLink() {
      return this.linkFlag == 49;
   }

   public boolean isOldGNUSparse() {
      return this.linkFlag == 83;
   }

   public boolean isPaxGNU1XSparse() {
      return this.paxGNU1XSparse;
   }

   public boolean isPaxGNUSparse() {
      return this.paxGNUSparse;
   }

   public boolean isPaxHeader() {
      return this.linkFlag == 120 || this.linkFlag == 88;
   }

   public boolean isSparse() {
      return this.isGNUSparse() || this.isStarSparse();
   }

   public boolean isStarSparse() {
      return this.starSparse;
   }

   public boolean isStreamContiguous() {
      return true;
   }

   public boolean isSymbolicLink() {
      return this.linkFlag == 50;
   }

   private boolean isXstar(Map globalPaxHeaders, byte[] header) {
      if (ArchiveUtils.matchAsciiBuffer("tar\u0000", header, 508, 4)) {
         return true;
      } else {
         String archType = (String)globalPaxHeaders.get("SCHILY.archtype");
         if (archType == null) {
            if (this.isInvalidPrefix(header)) {
               return false;
            } else if (this.isInvalidXtarTime(header, 476, 12)) {
               return false;
            } else {
               return !this.isInvalidXtarTime(header, 488, 12);
            }
         } else {
            return "xustar".equals(archType) || "exustar".equals(archType);
         }
      }
   }

   private long parseOctalOrBinary(byte[] header, int offset, int length, boolean lenient) {
      if (lenient) {
         try {
            return TarUtils.parseOctalOrBinary(header, offset, length);
         } catch (IllegalArgumentException var6) {
            return -1L;
         }
      } else {
         return TarUtils.parseOctalOrBinary(header, offset, length);
      }
   }

   public void parseTarHeader(byte[] header) {
      try {
         this.parseTarHeader(header, TarUtils.DEFAULT_ENCODING);
      } catch (IOException var5) {
         try {
            this.parseTarHeader(header, TarUtils.DEFAULT_ENCODING, true, false);
         } catch (IOException ex2) {
            throw new UncheckedIOException(ex2);
         }
      }

   }

   public void parseTarHeader(byte[] header, ZipEncoding encoding) throws IOException {
      this.parseTarHeader(header, encoding, false, false);
   }

   private void parseTarHeader(byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
      this.parseTarHeader(Collections.emptyMap(), header, encoding, oldStyle, lenient);
   }

   private void parseTarHeader(Map globalPaxHeaders, byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
      try {
         this.parseTarHeaderUnwrapped(globalPaxHeaders, header, encoding, oldStyle, lenient);
      } catch (IllegalArgumentException ex) {
         throw new IOException("Corrupted TAR archive.", ex);
      }
   }

   private void parseTarHeaderUnwrapped(Map globalPaxHeaders, byte[] header, ZipEncoding encoding, boolean oldStyle, boolean lenient) throws IOException {
      int offset = 0;
      this.name = oldStyle ? TarUtils.parseName(header, offset, 100) : TarUtils.parseName(header, offset, 100, encoding);
      offset += 100;
      this.mode = (int)this.parseOctalOrBinary(header, offset, 8, lenient);
      offset += 8;
      this.userId = (long)((int)this.parseOctalOrBinary(header, offset, 8, lenient));
      offset += 8;
      this.groupId = (long)((int)this.parseOctalOrBinary(header, offset, 8, lenient));
      offset += 8;
      this.size = TarUtils.parseOctalOrBinary(header, offset, 12);
      if (this.size < 0L) {
         throw new IOException("broken archive, entry with negative size");
      } else {
         offset += 12;
         this.mTime = FileTimes.fromUnixTime(this.parseOctalOrBinary(header, offset, 12, lenient));
         offset += 12;
         this.checkSumOK = TarUtils.verifyCheckSum(header);
         offset += 8;
         this.linkFlag = header[offset++];
         this.linkName = oldStyle ? TarUtils.parseName(header, offset, 100) : TarUtils.parseName(header, offset, 100, encoding);
         offset += 100;
         this.magic = TarUtils.parseName(header, offset, 6);
         offset += 6;
         this.version = TarUtils.parseName(header, offset, 2);
         offset += 2;
         this.userName = oldStyle ? TarUtils.parseName(header, offset, 32) : TarUtils.parseName(header, offset, 32, encoding);
         offset += 32;
         this.groupName = oldStyle ? TarUtils.parseName(header, offset, 32) : TarUtils.parseName(header, offset, 32, encoding);
         offset += 32;
         if (this.linkFlag != 51 && this.linkFlag != 52) {
            offset += 16;
         } else {
            this.devMajor = (int)this.parseOctalOrBinary(header, offset, 8, lenient);
            offset += 8;
            this.devMinor = (int)this.parseOctalOrBinary(header, offset, 8, lenient);
            offset += 8;
         }

         int type = this.evaluateType(globalPaxHeaders, header);
         switch (type) {
            case 2:
               this.aTime = fileTimeFromOptionalSeconds(this.parseOctalOrBinary(header, offset, 12, lenient));
               offset += 12;
               this.cTime = fileTimeFromOptionalSeconds(this.parseOctalOrBinary(header, offset, 12, lenient));
               offset += 12;
               offset += 12;
               offset += 4;
               ++offset;
               this.sparseHeaders = new ArrayList(TarUtils.readSparseStructs(header, offset, 4));
               offset += 96;
               this.isExtended = TarUtils.parseBoolean(header, offset);
               ++offset;
               this.realSize = TarUtils.parseOctal(header, offset, 12);
               offset += 12;
               break;
            case 3:
            default:
               String prefix = oldStyle ? TarUtils.parseName(header, offset, 155) : TarUtils.parseName(header, offset, 155, encoding);
               offset += 155;
               if (this.isDirectory() && !this.name.endsWith("/")) {
                  this.name = this.name + "/";
               }

               if (!prefix.isEmpty()) {
                  this.name = prefix + "/" + this.name;
               }
               break;
            case 4:
               String xstarPrefix = oldStyle ? TarUtils.parseName(header, offset, 131) : TarUtils.parseName(header, offset, 131, encoding);
               offset += 131;
               if (!xstarPrefix.isEmpty()) {
                  this.name = xstarPrefix + "/" + this.name;
               }

               this.aTime = fileTimeFromOptionalSeconds(this.parseOctalOrBinary(header, offset, 12, lenient));
               offset += 12;
               this.cTime = fileTimeFromOptionalSeconds(this.parseOctalOrBinary(header, offset, 12, lenient));
               offset += 12;
         }

      }
   }

   private void processPaxHeader(String key, String val) throws IOException {
      this.processPaxHeader(key, val, this.extraPaxHeaders);
   }

   private void processPaxHeader(String key, String val, Map headers) throws IOException {
      switch (key) {
         case "path":
            this.setName(val);
            break;
         case "linkpath":
            this.setLinkName(val);
            break;
         case "gid":
            this.setGroupId(ParsingUtils.parseLongValue(val));
            break;
         case "gname":
            this.setGroupName(val);
            break;
         case "uid":
            this.setUserId(ParsingUtils.parseLongValue(val));
            break;
         case "uname":
            this.setUserName(val);
            break;
         case "size":
            long size = ParsingUtils.parseLongValue(val);
            if (size < 0L) {
               throw new IOException("Corrupted TAR archive. Entry size is negative");
            }

            this.setSize(size);
            break;
         case "mtime":
            this.setLastModifiedTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
            break;
         case "atime":
            this.setLastAccessTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
            break;
         case "ctime":
            this.setStatusChangeTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
            break;
         case "LIBARCHIVE.creationtime":
            this.setCreationTime(FileTime.from(parseInstantFromDecimalSeconds(val)));
            break;
         case "SCHILY.devminor":
            int devMinor = ParsingUtils.parseIntValue(val);
            if (devMinor < 0) {
               throw new IOException("Corrupted TAR archive. Dev-Minor is negative");
            }

            this.setDevMinor(devMinor);
            break;
         case "SCHILY.devmajor":
            int devMajor = ParsingUtils.parseIntValue(val);
            if (devMajor < 0) {
               throw new IOException("Corrupted TAR archive. Dev-Major is negative");
            }

            this.setDevMajor(devMajor);
            break;
         case "GNU.sparse.size":
            this.fillGNUSparse0xData(headers);
            break;
         case "GNU.sparse.realsize":
            this.fillGNUSparse1xData(headers);
            break;
         case "SCHILY.filetype":
            if ("sparse".equals(val)) {
               this.fillStarSparseData(headers);
            }
            break;
         default:
            this.extraPaxHeaders.put(key, val);
      }

   }

   private void readFileMode(Path file, String normalizedName, LinkOption... options) throws IOException {
      if (Files.isDirectory(file, options)) {
         this.mode = 16877;
         this.linkFlag = 53;
         int nameLength = normalizedName.length();
         if (nameLength != 0 && normalizedName.charAt(nameLength - 1) == '/') {
            this.name = normalizedName;
         } else {
            this.name = normalizedName + "/";
         }
      } else {
         this.mode = 33188;
         this.linkFlag = 48;
         this.name = normalizedName;
         this.size = Files.size(file);
      }

   }

   private void readOsSpecificProperties(Path file, LinkOption... options) throws IOException {
      Set<String> availableAttributeViews = file.getFileSystem().supportedFileAttributeViews();
      if (availableAttributeViews.contains("posix")) {
         PosixFileAttributes posixFileAttributes = (PosixFileAttributes)Files.readAttributes(file, PosixFileAttributes.class, options);
         this.setLastModifiedTime(posixFileAttributes.lastModifiedTime());
         this.setCreationTime(posixFileAttributes.creationTime());
         this.setLastAccessTime(posixFileAttributes.lastAccessTime());
         this.userName = posixFileAttributes.owner().getName();
         this.groupName = posixFileAttributes.group().getName();
         if (availableAttributeViews.contains("unix")) {
            this.userId = ((Number)Files.getAttribute(file, "unix:uid", options)).longValue();
            this.groupId = ((Number)Files.getAttribute(file, "unix:gid", options)).longValue();

            try {
               this.setStatusChangeTime((FileTime)Files.getAttribute(file, "unix:ctime", options));
            } catch (IllegalArgumentException var6) {
            }
         }
      } else {
         if (availableAttributeViews.contains("dos")) {
            DosFileAttributes dosFileAttributes = (DosFileAttributes)Files.readAttributes(file, DosFileAttributes.class, options);
            this.setLastModifiedTime(dosFileAttributes.lastModifiedTime());
            this.setCreationTime(dosFileAttributes.creationTime());
            this.setLastAccessTime(dosFileAttributes.lastAccessTime());
         } else {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(file, BasicFileAttributes.class, options);
            this.setLastModifiedTime(basicFileAttributes.lastModifiedTime());
            this.setCreationTime(basicFileAttributes.creationTime());
            this.setLastAccessTime(basicFileAttributes.lastAccessTime());
         }

         this.userName = Files.getOwner(file, options).getName();
      }

   }

   public void setCreationTime(FileTime time) {
      this.birthTime = time;
   }

   public void setDataOffset(long dataOffset) {
      if (dataOffset < 0L) {
         throw new IllegalArgumentException("The offset can not be smaller than 0");
      } else {
         this.dataOffset = dataOffset;
      }
   }

   public void setDevMajor(int devNo) {
      if (devNo < 0) {
         throw new IllegalArgumentException("Major device number is out of range: " + devNo);
      } else {
         this.devMajor = devNo;
      }
   }

   public void setDevMinor(int devNo) {
      if (devNo < 0) {
         throw new IllegalArgumentException("Minor device number is out of range: " + devNo);
      } else {
         this.devMinor = devNo;
      }
   }

   public void setGroupId(int groupId) {
      this.setGroupId((long)groupId);
   }

   public void setGroupId(long groupId) {
      this.groupId = groupId;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public void setIds(int userId, int groupId) {
      this.setUserId(userId);
      this.setGroupId(groupId);
   }

   public void setLastAccessTime(FileTime time) {
      this.aTime = time;
   }

   public void setLastModifiedTime(FileTime time) {
      this.mTime = (FileTime)Objects.requireNonNull(time, "time");
   }

   public void setLinkName(String link) {
      this.linkName = link;
   }

   public void setMode(int mode) {
      this.mode = mode;
   }

   public void setModTime(Date time) {
      this.setLastModifiedTime(FileTimes.toFileTime(time));
   }

   public void setModTime(FileTime time) {
      this.setLastModifiedTime(time);
   }

   public void setModTime(long time) {
      this.setLastModifiedTime(FileTime.fromMillis(time));
   }

   public void setName(String name) {
      this.name = normalizeFileName(name, this.preserveAbsolutePath);
   }

   public void setNames(String userName, String groupName) {
      this.setUserName(userName);
      this.setGroupName(groupName);
   }

   public void setSize(long size) {
      if (size < 0L) {
         throw new IllegalArgumentException("Size is out of range: " + size);
      } else {
         this.size = size;
      }
   }

   public void setSparseHeaders(List sparseHeaders) {
      this.sparseHeaders = sparseHeaders;
   }

   public void setStatusChangeTime(FileTime time) {
      this.cTime = time;
   }

   public void setUserId(int userId) {
      this.setUserId((long)userId);
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   void updateEntryFromPaxHeaders(Map headers) throws IOException {
      for(Map.Entry ent : headers.entrySet()) {
         this.processPaxHeader((String)ent.getKey(), (String)ent.getValue(), headers);
      }

   }

   public void writeEntryHeader(byte[] outbuf) {
      try {
         this.writeEntryHeader(outbuf, TarUtils.DEFAULT_ENCODING, false);
      } catch (IOException var5) {
         try {
            this.writeEntryHeader(outbuf, TarUtils.FALLBACK_ENCODING, false);
         } catch (IOException ex2) {
            throw new UncheckedIOException(ex2);
         }
      }

   }

   public void writeEntryHeader(byte[] outbuf, ZipEncoding encoding, boolean starMode) throws IOException {
      int offset = 0;
      offset = TarUtils.formatNameBytes(this.name, outbuf, offset, 100, encoding);
      offset = this.writeEntryHeaderField((long)this.mode, outbuf, offset, 8, starMode);
      offset = this.writeEntryHeaderField(this.userId, outbuf, offset, 8, starMode);
      offset = this.writeEntryHeaderField(this.groupId, outbuf, offset, 8, starMode);
      offset = this.writeEntryHeaderField(this.size, outbuf, offset, 12, starMode);
      offset = this.writeEntryHeaderField(TimeUtils.toUnixTime(this.mTime), outbuf, offset, 12, starMode);
      offset = this.fill((byte)32, offset, outbuf, 8);
      outbuf[offset++] = this.linkFlag;
      offset = TarUtils.formatNameBytes(this.linkName, outbuf, offset, 100, encoding);
      offset = TarUtils.formatNameBytes(this.magic, outbuf, offset, 6);
      offset = TarUtils.formatNameBytes(this.version, outbuf, offset, 2);
      offset = TarUtils.formatNameBytes(this.userName, outbuf, offset, 32, encoding);
      offset = TarUtils.formatNameBytes(this.groupName, outbuf, offset, 32, encoding);
      offset = this.writeEntryHeaderField((long)this.devMajor, outbuf, offset, 8, starMode);
      offset = this.writeEntryHeaderField((long)this.devMinor, outbuf, offset, 8, starMode);
      if (starMode) {
         offset = this.fill((int)0, offset, outbuf, 131);
         offset = this.writeEntryHeaderOptionalTimeField(this.aTime, offset, outbuf, 12);
         offset = this.writeEntryHeaderOptionalTimeField(this.cTime, offset, outbuf, 12);
         offset = this.fill((int)0, offset, outbuf, 8);
         offset = this.fill((int)0, offset, outbuf, 4);
      }

      this.fill((int)0, offset, outbuf, outbuf.length - offset);
      long chk = TarUtils.computeCheckSum(outbuf);
      TarUtils.formatCheckSumOctalBytes(chk, outbuf, offset, 8);
   }

   private int writeEntryHeaderField(long value, byte[] outbuf, int offset, int length, boolean starMode) {
      return starMode || value >= 0L && value < 1L << 3 * (length - 1) ? TarUtils.formatLongOctalOrBinaryBytes(value, outbuf, offset, length) : TarUtils.formatLongOctalBytes(0L, outbuf, offset, length);
   }

   private int writeEntryHeaderOptionalTimeField(FileTime time, int offset, byte[] outbuf, int fieldLength) {
      if (time != null) {
         offset = this.writeEntryHeaderField(TimeUtils.toUnixTime(time), outbuf, offset, fieldLength, true);
      } else {
         offset = this.fill((int)0, offset, outbuf, fieldLength);
      }

      return offset;
   }
}
