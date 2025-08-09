package io.netty.handler.codec.dns;

import io.netty.util.internal.StringUtil;

public abstract class AbstractDnsOptPseudoRrRecord extends AbstractDnsRecord implements DnsOptPseudoRecord {
   protected AbstractDnsOptPseudoRrRecord(int maxPayloadSize, int extendedRcode, int version) {
      super("", DnsRecordType.OPT, maxPayloadSize, packIntoLong(extendedRcode, version));
   }

   protected AbstractDnsOptPseudoRrRecord(int maxPayloadSize) {
      super("", DnsRecordType.OPT, maxPayloadSize, 0L);
   }

   private static long packIntoLong(int val, int val2) {
      return (((long)val & 255L) << 24 | (long)((val2 & 255) << 16)) & 4294967295L;
   }

   public int extendedRcode() {
      return (short)((int)this.timeToLive() >> 24 & 255);
   }

   public int version() {
      return (short)((int)this.timeToLive() >> 16 & 255);
   }

   public int flags() {
      return (short)((short)((int)this.timeToLive()) & 255);
   }

   public String toString() {
      return this.toStringBuilder().toString();
   }

   final StringBuilder toStringBuilder() {
      return (new StringBuilder(64)).append(StringUtil.simpleClassName(this)).append('(').append("OPT flags:").append(this.flags()).append(" version:").append(this.version()).append(" extendedRecode:").append(this.extendedRcode()).append(" udp:").append(this.dnsClass()).append(')');
   }
}
