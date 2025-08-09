package io.netty.resolver;

import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public final class HostsFileEntriesProvider {
   static final HostsFileEntriesProvider EMPTY = new HostsFileEntriesProvider(Collections.emptyMap(), Collections.emptyMap());
   private final Map ipv4Entries;
   private final Map ipv6Entries;

   public static Parser parser() {
      return HostsFileEntriesProvider.ParserImpl.INSTANCE;
   }

   HostsFileEntriesProvider(Map ipv4Entries, Map ipv6Entries) {
      this.ipv4Entries = Collections.unmodifiableMap(new HashMap(ipv4Entries));
      this.ipv6Entries = Collections.unmodifiableMap(new HashMap(ipv6Entries));
   }

   public Map ipv4Entries() {
      return this.ipv4Entries;
   }

   public Map ipv6Entries() {
      return this.ipv6Entries;
   }

   private static final class ParserImpl implements Parser {
      private static final String WINDOWS_DEFAULT_SYSTEM_ROOT = "C:\\Windows";
      private static final String WINDOWS_HOSTS_FILE_RELATIVE_PATH = "\\system32\\drivers\\etc\\hosts";
      private static final String X_PLATFORMS_HOSTS_FILE_PATH = "/etc/hosts";
      private static final Pattern WHITESPACES = Pattern.compile("[ \t]+");
      private static final InternalLogger logger = InternalLoggerFactory.getInstance(Parser.class);
      static final ParserImpl INSTANCE = new ParserImpl();

      public HostsFileEntriesProvider parse() throws IOException {
         return this.parse(locateHostsFile(), Charset.defaultCharset());
      }

      public HostsFileEntriesProvider parse(Charset... charsets) throws IOException {
         return this.parse(locateHostsFile(), charsets);
      }

      public HostsFileEntriesProvider parse(File file, Charset... charsets) throws IOException {
         ObjectUtil.checkNotNull(file, "file");
         ObjectUtil.checkNotNull(charsets, "charsets");
         if (charsets.length == 0) {
            charsets = new Charset[]{Charset.defaultCharset()};
         }

         if (file.exists() && file.isFile()) {
            for(Charset charset : charsets) {
               BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

               try {
                  HostsFileEntriesProvider entries = this.parse((Reader)reader);
                  if (entries != HostsFileEntriesProvider.EMPTY) {
                     HostsFileEntriesProvider var9 = entries;
                     return var9;
                  }
               } finally {
                  reader.close();
               }
            }

            return HostsFileEntriesProvider.EMPTY;
         } else {
            return HostsFileEntriesProvider.EMPTY;
         }
      }

      public HostsFileEntriesProvider parse(Reader reader) throws IOException {
         ObjectUtil.checkNotNull(reader, "reader");
         BufferedReader buff = new BufferedReader(reader);

         HostsFileEntriesProvider var22;
         try {
            Map<String, List<InetAddress>> ipv4Entries = new HashMap();
            Map<String, List<InetAddress>> ipv6Entries = new HashMap();

            String line;
            while((line = buff.readLine()) != null) {
               int commentPosition = line.indexOf(35);
               if (commentPosition != -1) {
                  line = line.substring(0, commentPosition);
               }

               line = line.trim();
               if (!line.isEmpty()) {
                  List<String> lineParts = new ArrayList();

                  for(String s : WHITESPACES.split(line)) {
                     if (!s.isEmpty()) {
                        lineParts.add(s);
                     }
                  }

                  if (lineParts.size() >= 2) {
                     byte[] ipBytes = NetUtil.createByteArrayFromIpAddressString((String)lineParts.get(0));
                     if (ipBytes != null) {
                        for(int i = 1; i < lineParts.size(); ++i) {
                           String hostname = (String)lineParts.get(i);
                           String hostnameLower = hostname.toLowerCase(Locale.ENGLISH);
                           InetAddress address = InetAddress.getByAddress(hostname, ipBytes);
                           List<InetAddress> addresses;
                           if (address instanceof Inet4Address) {
                              addresses = (List)ipv4Entries.get(hostnameLower);
                              if (addresses == null) {
                                 addresses = new ArrayList();
                                 ipv4Entries.put(hostnameLower, addresses);
                              }
                           } else {
                              addresses = (List)ipv6Entries.get(hostnameLower);
                              if (addresses == null) {
                                 addresses = new ArrayList();
                                 ipv6Entries.put(hostnameLower, addresses);
                              }
                           }

                           addresses.add(address);
                        }
                     }
                  }
               }
            }

            var22 = ipv4Entries.isEmpty() && ipv6Entries.isEmpty() ? HostsFileEntriesProvider.EMPTY : new HostsFileEntriesProvider(ipv4Entries, ipv6Entries);
         } finally {
            try {
               buff.close();
            } catch (IOException e) {
               logger.warn("Failed to close a reader", e);
            }

         }

         return var22;
      }

      public HostsFileEntriesProvider parseSilently() {
         return this.parseSilently(locateHostsFile(), Charset.defaultCharset());
      }

      public HostsFileEntriesProvider parseSilently(Charset... charsets) {
         return this.parseSilently(locateHostsFile(), charsets);
      }

      public HostsFileEntriesProvider parseSilently(File file, Charset... charsets) {
         try {
            return this.parse(file, charsets);
         } catch (IOException e) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to load and parse hosts file at " + file.getPath(), e);
            }

            return HostsFileEntriesProvider.EMPTY;
         }
      }

      private static File locateHostsFile() {
         File hostsFile;
         if (PlatformDependent.isWindows()) {
            hostsFile = new File(System.getenv("SystemRoot") + "\\system32\\drivers\\etc\\hosts");
            if (!hostsFile.exists()) {
               hostsFile = new File("C:\\Windows\\system32\\drivers\\etc\\hosts");
            }
         } else {
            hostsFile = new File("/etc/hosts");
         }

         return hostsFile;
      }
   }

   public interface Parser {
      HostsFileEntriesProvider parse() throws IOException;

      HostsFileEntriesProvider parse(Charset... var1) throws IOException;

      HostsFileEntriesProvider parse(File var1, Charset... var2) throws IOException;

      HostsFileEntriesProvider parse(Reader var1) throws IOException;

      HostsFileEntriesProvider parseSilently();

      HostsFileEntriesProvider parseSilently(Charset... var1);

      HostsFileEntriesProvider parseSilently(File var1, Charset... var2);
   }
}
