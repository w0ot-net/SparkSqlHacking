package org.apache.zookeeper.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZKHostnameVerifier implements HostnameVerifier {
   private final Logger log = LoggerFactory.getLogger(ZKHostnameVerifier.class);

   public boolean verify(String host, SSLSession session) {
      try {
         Certificate[] certs = session.getPeerCertificates();
         X509Certificate x509 = (X509Certificate)certs[0];
         this.verify(host, x509);
         return true;
      } catch (SSLException ex) {
         this.log.debug("Unexpected exception", ex);
         return false;
      }
   }

   void verify(String host, X509Certificate cert) throws SSLException {
      HostNameType hostType = determineHostFormat(host);
      List<SubjectName> subjectAlts = getSubjectAltNames(cert);
      if (subjectAlts != null && !subjectAlts.isEmpty()) {
         switch (hostType) {
            case IPv4:
               matchIPAddress(host, subjectAlts);
               break;
            case IPv6:
               matchIPv6Address(host, subjectAlts);
               break;
            default:
               matchDNSName(host, subjectAlts);
         }
      } else {
         X500Principal subjectPrincipal = cert.getSubjectX500Principal();
         String cn = extractCN(subjectPrincipal.getName("RFC2253"));
         if (cn == null) {
            throw new SSLException("Certificate subject for <" + host + "> doesn't contain a common name and does not have alternative names");
         }

         matchCN(host, cn);
      }

   }

   private static void matchIPAddress(String host, List subjectAlts) throws SSLException {
      for(int i = 0; i < subjectAlts.size(); ++i) {
         SubjectName subjectAlt = (SubjectName)subjectAlts.get(i);
         if (subjectAlt.getType() == 7 && host.equals(subjectAlt.getValue())) {
            return;
         }
      }

      throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any of the subject alternative names: " + subjectAlts);
   }

   private static void matchIPv6Address(String host, List subjectAlts) throws SSLException {
      String normalisedHost = normaliseAddress(host);

      for(int i = 0; i < subjectAlts.size(); ++i) {
         SubjectName subjectAlt = (SubjectName)subjectAlts.get(i);
         if (subjectAlt.getType() == 7) {
            String normalizedSubjectAlt = normaliseAddress(subjectAlt.getValue());
            if (normalisedHost.equals(normalizedSubjectAlt)) {
               return;
            }
         }
      }

      throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any of the subject alternative names: " + subjectAlts);
   }

   private static void matchDNSName(String host, List subjectAlts) throws SSLException {
      String normalizedHost = host.toLowerCase(Locale.ROOT);

      for(int i = 0; i < subjectAlts.size(); ++i) {
         SubjectName subjectAlt = (SubjectName)subjectAlts.get(i);
         if (subjectAlt.getType() == 2) {
            String normalizedSubjectAlt = subjectAlt.getValue().toLowerCase(Locale.ROOT);
            if (matchIdentityStrict(normalizedHost, normalizedSubjectAlt)) {
               return;
            }
         }
      }

      throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any of the subject alternative names: " + subjectAlts);
   }

   private static void matchCN(String host, String cn) throws SSLException {
      String normalizedHost = host.toLowerCase(Locale.ROOT);
      String normalizedCn = cn.toLowerCase(Locale.ROOT);
      if (!matchIdentityStrict(normalizedHost, normalizedCn)) {
         throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match common name of the certificate subject: " + cn);
      }
   }

   private static boolean matchIdentity(String host, String identity, boolean strict) {
      int asteriskIdx = identity.indexOf(42);
      if (asteriskIdx != -1) {
         String prefix = identity.substring(0, asteriskIdx);
         String suffix = identity.substring(asteriskIdx + 1);
         if (!prefix.isEmpty() && !host.startsWith(prefix)) {
            return false;
         } else if (!suffix.isEmpty() && !host.endsWith(suffix)) {
            return false;
         } else if (strict) {
            String remainder = host.substring(prefix.length(), host.length() - suffix.length());
            return !remainder.contains(".");
         } else {
            return true;
         }
      } else {
         return host.equalsIgnoreCase(identity);
      }
   }

   private static boolean matchIdentityStrict(String host, String identity) {
      return matchIdentity(host, identity, true);
   }

   private static String extractCN(String subjectPrincipal) throws SSLException {
      if (subjectPrincipal == null) {
         return null;
      } else {
         try {
            LdapName subjectDN = new LdapName(subjectPrincipal);
            List<Rdn> rdns = subjectDN.getRdns();

            for(int i = rdns.size() - 1; i >= 0; --i) {
               Rdn rds = (Rdn)rdns.get(i);
               Attributes attributes = rds.toAttributes();
               Attribute cn = attributes.get("cn");
               if (cn != null) {
                  try {
                     Object value = cn.get();
                     if (value != null) {
                        return value.toString();
                     }
                  } catch (NoSuchElementException var8) {
                  } catch (NamingException var9) {
                  }
               }
            }

            return null;
         } catch (InvalidNameException var10) {
            throw new SSLException(subjectPrincipal + " is not a valid X500 distinguished name");
         }
      }
   }

   private static HostNameType determineHostFormat(String host) {
      if (ZKHostnameVerifier.InetAddressUtils.isIPv4Address(host)) {
         return ZKHostnameVerifier.HostNameType.IPv4;
      } else {
         String s = host;
         if (host.startsWith("[") && host.endsWith("]")) {
            s = host.substring(1, host.length() - 1);
         }

         return ZKHostnameVerifier.InetAddressUtils.isIPv6Address(s) ? ZKHostnameVerifier.HostNameType.IPv6 : ZKHostnameVerifier.HostNameType.DNS;
      }
   }

   private static List getSubjectAltNames(X509Certificate cert) {
      try {
         Collection<List<?>> entries = cert.getSubjectAlternativeNames();
         if (entries == null) {
            return Collections.emptyList();
         } else {
            List<SubjectName> result = new ArrayList();

            for(List entry : entries) {
               Integer type = entry.size() >= 2 ? (Integer)entry.get(0) : null;
               if (type != null && (type == 2 || type == 7)) {
                  Object o = entry.get(1);
                  if (o instanceof String) {
                     result.add(new SubjectName((String)o, type));
                  } else if (o instanceof byte[]) {
                  }
               }
            }

            return result;
         }
      } catch (CertificateParsingException var7) {
         return Collections.emptyList();
      }
   }

   private static String normaliseAddress(String hostname) {
      if (hostname == null) {
         return hostname;
      } else {
         try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            return inetAddress.getHostAddress();
         } catch (UnknownHostException var2) {
            return hostname;
         }
      }
   }

   private static final class SubjectName {
      static final int DNS = 2;
      static final int IP = 7;
      private final String value;
      private final int type;

      static SubjectName IP(String value) {
         return new SubjectName(value, 7);
      }

      static SubjectName DNS(String value) {
         return new SubjectName(value, 2);
      }

      SubjectName(String value, int type) {
         if (type != 2 && type != 7) {
            throw new IllegalArgumentException("Invalid type: " + type);
         } else {
            this.value = (String)Objects.requireNonNull(value);
            this.type = type;
         }
      }

      public int getType() {
         return this.type;
      }

      public String getValue() {
         return this.value;
      }

      public String toString() {
         return this.value;
      }
   }

   private static class InetAddressUtils {
      private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
      private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
      private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

      static boolean isIPv4Address(String input) {
         return IPV4_PATTERN.matcher(input).matches();
      }

      static boolean isIPv6StdAddress(String input) {
         return IPV6_STD_PATTERN.matcher(input).matches();
      }

      static boolean isIPv6HexCompressedAddress(String input) {
         return IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
      }

      static boolean isIPv6Address(String input) {
         return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
      }
   }

   static enum HostNameType {
      IPv4(7),
      IPv6(7),
      DNS(2);

      final int subjectType;

      private HostNameType(int subjectType) {
         this.subjectType = subjectType;
      }
   }
}
