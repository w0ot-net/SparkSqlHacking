package io.vertx.core.dns.impl.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.dns.DnsPtrRecord;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.util.CharsetUtil;
import io.vertx.core.dns.impl.MxRecordImpl;
import io.vertx.core.dns.impl.SrvRecordImpl;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RecordDecoder {
   private static final Logger log = LoggerFactory.getLogger(RecordDecoder.class);
   public static final Function MX = (record) -> {
      ByteBuf packet = ((DnsRawRecord)record).content();
      int priority = packet.readShort();
      String name = readName(packet);
      return new MxRecordImpl(priority, name);
   };
   public static final Function DOMAIN = (record) -> {
      if (record instanceof DnsPtrRecord) {
         String val = ((DnsPtrRecord)record).hostname();
         if (val.endsWith(".")) {
            val = val.substring(0, val.length() - 1);
         }

         return val;
      } else {
         ByteBuf content = ((DnsRawRecord)record).content();
         return getName(content, content.readerIndex());
      }
   };
   public static final Function A = address(4);
   public static final Function AAAA = address(16);
   public static final Function SRV = (record) -> {
      ByteBuf packet = ((DnsRawRecord)record).content();
      int priority = packet.readShort();
      int weight = packet.readShort();
      int port = packet.readUnsignedShort();
      String target = readName(packet);
      String[] parts = record.name().split("\\.", 3);
      String service = parts[0];
      String protocol = parts[1];
      String name = parts[2];
      return new SrvRecordImpl(priority, weight, port, name, protocol, service, target);
   };
   public static final Function SOA = (record) -> {
      ByteBuf packet = ((DnsRawRecord)record).content();
      String mName = readName(packet);
      String rName = readName(packet);
      long serial = packet.readUnsignedInt();
      int refresh = packet.readInt();
      int retry = packet.readInt();
      int expire = packet.readInt();
      long minimum = packet.readUnsignedInt();
      return new StartOfAuthorityRecord(mName, rName, serial, refresh, retry, expire, minimum);
   };
   public static final Function TXT = (record) -> {
      List<String> list = new ArrayList();
      ByteBuf data = ((DnsRawRecord)record).content();

      int len;
      for(int index = data.readerIndex(); index < data.writerIndex(); index += len) {
         len = data.getUnsignedByte(index++);
         list.add(data.toString(index, len, CharsetUtil.UTF_8));
      }

      return list;
   };
   private static final Map decoders = new HashMap();

   static Function address(int octets) {
      return (record) -> {
         ByteBuf data = ((DnsRawRecord)record).content();
         int size = data.readableBytes();
         if (size != octets) {
            throw new DecoderException("Invalid content length, or reader index when decoding address [index: " + data.readerIndex() + ", expected length: " + octets + ", actual: " + size + "].");
         } else {
            byte[] address = new byte[octets];
            data.getBytes(data.readerIndex(), address);

            try {
               return InetAddress.getByAddress(address).getHostAddress();
            } catch (UnknownHostException var6) {
               throw new DecoderException("Could not convert address " + data.toString(data.readerIndex(), size, CharsetUtil.UTF_8) + " to InetAddress.");
            }
         }
      };
   }

   static String readName(ByteBuf buf) {
      int position = -1;
      StringBuilder name = new StringBuilder();

      for(int len = buf.readUnsignedByte(); buf.isReadable() && len != 0; len = buf.readUnsignedByte()) {
         boolean pointer = (len & 192) == 192;
         if (pointer) {
            if (position == -1) {
               position = buf.readerIndex() + 1;
            }

            buf.readerIndex((len & 63) << 8 | buf.readUnsignedByte());
         } else {
            name.append(buf.toString(buf.readerIndex(), len, CharsetUtil.UTF_8)).append(".");
            buf.skipBytes(len);
         }
      }

      if (position != -1) {
         buf.readerIndex(position);
      }

      return name.length() == 0 ? null : name.substring(0, name.length() - 1);
   }

   static String getName(ByteBuf param0, int param1) {
      // $FF: Couldn't be decompiled
   }

   public static Object decode(DnsRecord record) {
      DnsRecordType type = record.type();
      Function<DnsRecord, ?> decoder = (Function)decoders.get(type);
      if (decoder == null) {
         throw new DecoderException("DNS record decoding error occurred: Unsupported resource record type [id: " + type + "].");
      } else {
         T result = (T)null;

         try {
            result = (T)decoder.apply(record);
         } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
         }

         return result;
      }
   }

   static {
      decoders.put(DnsRecordType.A, A);
      decoders.put(DnsRecordType.AAAA, AAAA);
      decoders.put(DnsRecordType.MX, MX);
      decoders.put(DnsRecordType.TXT, TXT);
      decoders.put(DnsRecordType.SRV, SRV);
      decoders.put(DnsRecordType.NS, DOMAIN);
      decoders.put(DnsRecordType.CNAME, DOMAIN);
      decoders.put(DnsRecordType.PTR, DOMAIN);
      decoders.put(DnsRecordType.SOA, SOA);
   }
}
