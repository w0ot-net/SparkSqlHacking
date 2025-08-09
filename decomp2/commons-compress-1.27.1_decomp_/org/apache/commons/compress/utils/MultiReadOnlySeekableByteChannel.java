package org.apache.commons.compress.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MultiReadOnlySeekableByteChannel implements SeekableByteChannel {
   private static final Path[] EMPTY_PATH_ARRAY = new Path[0];
   private final List channelList;
   private long globalPosition;
   private int currentChannelIdx;

   public static SeekableByteChannel forFiles(File... files) throws IOException {
      List<Path> paths = new ArrayList();

      for(File f : (File[])Objects.requireNonNull(files, "files")) {
         paths.add(f.toPath());
      }

      return forPaths((Path[])paths.toArray(EMPTY_PATH_ARRAY));
   }

   public static SeekableByteChannel forPaths(Path... paths) throws IOException {
      List<SeekableByteChannel> channels = new ArrayList();

      for(Path path : (Path[])Objects.requireNonNull(paths, "paths")) {
         channels.add(Files.newByteChannel(path, StandardOpenOption.READ));
      }

      return (SeekableByteChannel)(channels.size() == 1 ? (SeekableByteChannel)channels.get(0) : new MultiReadOnlySeekableByteChannel(channels));
   }

   public static SeekableByteChannel forSeekableByteChannels(SeekableByteChannel... channels) {
      return (SeekableByteChannel)(((SeekableByteChannel[])Objects.requireNonNull(channels, "channels")).length == 1 ? channels[0] : new MultiReadOnlySeekableByteChannel(Arrays.asList(channels)));
   }

   public MultiReadOnlySeekableByteChannel(List channels) {
      this.channelList = Collections.unmodifiableList(new ArrayList((Collection)Objects.requireNonNull(channels, "channels")));
   }

   public void close() throws IOException {
      IOException first = null;

      for(SeekableByteChannel ch : this.channelList) {
         try {
            ch.close();
         } catch (IOException ex) {
            if (first == null) {
               first = ex;
            }
         }
      }

      if (first != null) {
         throw new IOException("failed to close wrapped channel", first);
      }
   }

   public boolean isOpen() {
      return this.channelList.stream().allMatch(Channel::isOpen);
   }

   public long position() {
      return this.globalPosition;
   }

   public synchronized SeekableByteChannel position(long newPosition) throws IOException {
      if (newPosition < 0L) {
         throw new IllegalArgumentException("Negative position: " + newPosition);
      } else if (!this.isOpen()) {
         throw new ClosedChannelException();
      } else {
         this.globalPosition = newPosition;
         long pos = newPosition;

         for(int i = 0; i < this.channelList.size(); ++i) {
            SeekableByteChannel currentChannel = (SeekableByteChannel)this.channelList.get(i);
            long size = currentChannel.size();
            long newChannelPos;
            if (pos == -1L) {
               newChannelPos = 0L;
            } else if (pos <= size) {
               this.currentChannelIdx = i;
               long tmp = pos;
               pos = -1L;
               newChannelPos = tmp;
            } else {
               pos -= size;
               newChannelPos = size;
            }

            currentChannel.position(newChannelPos);
         }

         return this;
      }
   }

   public synchronized SeekableByteChannel position(long channelNumber, long relativeOffset) throws IOException {
      if (!this.isOpen()) {
         throw new ClosedChannelException();
      } else {
         long globalPosition = relativeOffset;

         for(int i = 0; (long)i < channelNumber; ++i) {
            globalPosition += ((SeekableByteChannel)this.channelList.get(i)).size();
         }

         return this.position(globalPosition);
      }
   }

   public synchronized int read(ByteBuffer dst) throws IOException {
      if (!this.isOpen()) {
         throw new ClosedChannelException();
      } else if (!dst.hasRemaining()) {
         return 0;
      } else {
         int totalBytesRead = 0;

         while(dst.hasRemaining() && this.currentChannelIdx < this.channelList.size()) {
            SeekableByteChannel currentChannel = (SeekableByteChannel)this.channelList.get(this.currentChannelIdx);
            int newBytesRead = currentChannel.read(dst);
            if (newBytesRead == -1) {
               ++this.currentChannelIdx;
            } else {
               if (currentChannel.position() >= currentChannel.size()) {
                  ++this.currentChannelIdx;
               }

               totalBytesRead += newBytesRead;
            }
         }

         if (totalBytesRead > 0) {
            this.globalPosition += (long)totalBytesRead;
            return totalBytesRead;
         } else {
            return -1;
         }
      }
   }

   public long size() throws IOException {
      if (!this.isOpen()) {
         throw new ClosedChannelException();
      } else {
         long acc = 0L;

         for(SeekableByteChannel ch : this.channelList) {
            acc += ch.size();
         }

         return acc;
      }
   }

   public SeekableByteChannel truncate(long size) {
      throw new NonWritableChannelException();
   }

   public int write(ByteBuffer src) {
      throw new NonWritableChannelException();
   }
}
