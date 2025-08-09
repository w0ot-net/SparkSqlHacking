package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel;

public class ZipSplitReadOnlySeekableByteChannel extends MultiReadOnlySeekableByteChannel {
   private static final Path[] EMPTY_PATH_ARRAY = new Path[0];
   private static final int ZIP_SPLIT_SIGNATURE_LENGTH = 4;
   private final ByteBuffer zipSplitSignatureByteBuffer = ByteBuffer.allocate(4);

   public static SeekableByteChannel buildFromLastSplitSegment(File lastSegmentFile) throws IOException {
      return buildFromLastSplitSegment(lastSegmentFile.toPath());
   }

   public static SeekableByteChannel buildFromLastSplitSegment(Path lastSegmentPath) throws IOException {
      String extension = FileNameUtils.getExtension(lastSegmentPath);
      if (!extension.equalsIgnoreCase("zip")) {
         throw new IllegalArgumentException("The extension of last ZIP split segment should be .zip");
      } else {
         Path parent = Objects.nonNull(lastSegmentPath.getParent()) ? lastSegmentPath.getParent() : lastSegmentPath.getFileSystem().getPath(".");
         String fileBaseName = FileNameUtils.getBaseName(lastSegmentPath);
         Pattern pattern = Pattern.compile(Pattern.quote(fileBaseName) + ".[zZ][0-9]+");
         Stream<Path> walk = Files.walk(parent, 1, new FileVisitOption[0]);

         ArrayList<Path> splitZipSegments;
         try {
            splitZipSegments = (ArrayList)walk.filter((x$0) -> Files.isRegularFile(x$0, new LinkOption[0])).filter((path) -> pattern.matcher(path.getFileName().toString()).matches()).sorted(new ZipSplitSegmentComparator()).collect(Collectors.toCollection(ArrayList::new));
         } catch (Throwable var10) {
            if (walk != null) {
               try {
                  walk.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (walk != null) {
            walk.close();
         }

         return forPaths((Path)lastSegmentPath, (Iterable)splitZipSegments);
      }
   }

   public static SeekableByteChannel forFiles(File... files) throws IOException {
      List<Path> paths = new ArrayList();

      for(File f : (File[])Objects.requireNonNull(files, "files")) {
         paths.add(f.toPath());
      }

      return forPaths((Path[])paths.toArray(EMPTY_PATH_ARRAY));
   }

   public static SeekableByteChannel forFiles(File lastSegmentFile, Iterable files) throws IOException {
      Objects.requireNonNull(files, "files");
      Objects.requireNonNull(lastSegmentFile, "lastSegmentFile");
      List<Path> filesList = new ArrayList();
      files.forEach((f) -> filesList.add(f.toPath()));
      return forPaths((Path)lastSegmentFile.toPath(), (Iterable)filesList);
   }

   public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel... channels) throws IOException {
      return (SeekableByteChannel)(((SeekableByteChannel[])Objects.requireNonNull(channels, "channels")).length == 1 ? channels[0] : new ZipSplitReadOnlySeekableByteChannel(Arrays.asList(channels)));
   }

   public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel lastSegmentChannel, Iterable channels) throws IOException {
      Objects.requireNonNull(channels, "channels");
      Objects.requireNonNull(lastSegmentChannel, "lastSegmentChannel");
      List<SeekableByteChannel> channelsList = new ArrayList();
      Objects.requireNonNull(channelsList);
      channels.forEach(channelsList::add);
      channelsList.add(lastSegmentChannel);
      return forOrderedSeekableByteChannels((SeekableByteChannel[])channelsList.toArray(new SeekableByteChannel[0]));
   }

   public static SeekableByteChannel forPaths(List paths, OpenOption[] openOptions) throws IOException {
      List<SeekableByteChannel> channels = new ArrayList();

      for(Path path : (List)Objects.requireNonNull(paths, "paths")) {
         channels.add(Files.newByteChannel(path, openOptions));
      }

      return (SeekableByteChannel)(channels.size() == 1 ? (SeekableByteChannel)channels.get(0) : new ZipSplitReadOnlySeekableByteChannel(channels));
   }

   public static SeekableByteChannel forPaths(Path... paths) throws IOException {
      return forPaths(Arrays.asList(paths), new OpenOption[]{StandardOpenOption.READ});
   }

   public static SeekableByteChannel forPaths(Path lastSegmentPath, Iterable paths) throws IOException {
      Objects.requireNonNull(paths, "paths");
      Objects.requireNonNull(lastSegmentPath, "lastSegmentPath");
      List<Path> filesList = new ArrayList();
      Objects.requireNonNull(filesList);
      paths.forEach(filesList::add);
      filesList.add(lastSegmentPath);
      return forPaths((Path[])filesList.toArray(EMPTY_PATH_ARRAY));
   }

   public ZipSplitReadOnlySeekableByteChannel(List channels) throws IOException {
      super(channels);
      this.assertSplitSignature(channels);
   }

   private void assertSplitSignature(List channels) throws IOException {
      SeekableByteChannel channel = (SeekableByteChannel)channels.get(0);
      channel.position(0L);
      this.zipSplitSignatureByteBuffer.rewind();
      channel.read(this.zipSplitSignatureByteBuffer);
      ZipLong signature = new ZipLong(this.zipSplitSignatureByteBuffer.array());
      if (!signature.equals(ZipLong.DD_SIG)) {
         channel.position(0L);
         throw new IOException("The first ZIP split segment does not begin with split ZIP file signature");
      } else {
         channel.position(0L);
      }
   }

   private static final class ZipSplitSegmentComparator implements Comparator, Serializable {
      private static final long serialVersionUID = 20200123L;

      private ZipSplitSegmentComparator() {
      }

      public int compare(Path file1, Path file2) {
         String extension1 = FileNameUtils.getExtension(file1);
         String extension2 = FileNameUtils.getExtension(file2);
         if (!extension1.startsWith("z")) {
            return -1;
         } else if (!extension2.startsWith("z")) {
            return 1;
         } else {
            Integer splitSegmentNumber1 = Integer.parseInt(extension1.substring(1));
            Integer splitSegmentNumber2 = Integer.parseInt(extension2.substring(1));
            return splitSegmentNumber1.compareTo(splitSegmentNumber2);
         }
      }
   }
}
