package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyException;
import io.jsonwebtoken.security.KeyLengthSupplier;
import io.jsonwebtoken.security.KeyPairBuilder;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class EdwardsCurve extends AbstractCurve implements KeyLengthSupplier {
   private static final String OID_PREFIX = "1.3.101.";
   private static final byte[] ASN1_OID_PREFIX = new byte[]{6, 3, 43, 101};
   private static final Function CURVE_NAME_FINDER = new NamedParameterSpecValueFinder();
   public static final EdwardsCurve X25519 = new EdwardsCurve("X25519", 110);
   public static final EdwardsCurve X448 = new EdwardsCurve("X448", 111);
   public static final EdwardsCurve Ed25519 = new EdwardsCurve("Ed25519", 112);
   public static final EdwardsCurve Ed448 = new EdwardsCurve("Ed448", 113);
   public static final Collection VALUES;
   private static final Map REGISTRY;
   private static final Map BY_OID_TERMINAL_NODE;
   private final String OID;
   final byte[] ASN1_OID;
   private final int keyBitLength;
   private final int encodedKeyByteLength;
   private final byte[] PUBLIC_KEY_ASN1_PREFIX;
   private final byte[] PRIVATE_KEY_ASN1_PREFIX;
   private final byte[] PRIVATE_KEY_JDK11_PREFIX;
   private final boolean signatureCurve;

   private static byte[] publicKeyAsn1Prefix(int byteLength, byte[] ASN1_OID) {
      return Bytes.concat(new byte[]{48, (byte)(byteLength + 10), 48, 5}, ASN1_OID, new byte[]{3, (byte)(byteLength + 1), 0});
   }

   private static byte[] privateKeyPkcs8Prefix(int byteLength, byte[] ASN1_OID, boolean ber) {
      byte[] keyPrefix = ber ? new byte[]{4, (byte)(byteLength + 2), 4, (byte)byteLength} : new byte[]{4, (byte)byteLength};
      return Bytes.concat(new byte[]{48, (byte)(5 + ASN1_OID.length + keyPrefix.length + byteLength), 2, 1, 0, 48, 5}, ASN1_OID, keyPrefix);
   }

   EdwardsCurve(String id, int oidTerminalNode) {
      super(id, id);
      if (oidTerminalNode >= 110 && oidTerminalNode <= 113) {
         this.keyBitLength = oidTerminalNode % 2 == 0 ? 255 : 448;
         int encodingBitLen = oidTerminalNode == 113 ? this.keyBitLength + 8 : this.keyBitLength;
         this.encodedKeyByteLength = Bytes.length(encodingBitLen);
         this.OID = "1.3.101." + oidTerminalNode;
         this.signatureCurve = oidTerminalNode == 112 || oidTerminalNode == 113;
         byte[] suffix = new byte[]{(byte)oidTerminalNode};
         this.ASN1_OID = Bytes.concat(ASN1_OID_PREFIX, suffix);
         this.PUBLIC_KEY_ASN1_PREFIX = publicKeyAsn1Prefix(this.encodedKeyByteLength, this.ASN1_OID);
         this.PRIVATE_KEY_ASN1_PREFIX = privateKeyPkcs8Prefix(this.encodedKeyByteLength, this.ASN1_OID, true);
         this.PRIVATE_KEY_JDK11_PREFIX = privateKeyPkcs8Prefix(this.encodedKeyByteLength, this.ASN1_OID, false);
      } else {
         String msg = "Invalid Edwards Curve ASN.1 OID terminal node value";
         throw new IllegalArgumentException(msg);
      }
   }

   public int getKeyBitLength() {
      return this.keyBitLength;
   }

   public byte[] getKeyMaterial(Key key) {
      try {
         return this.doGetKeyMaterial(key);
      } catch (Throwable t) {
         if (t instanceof KeyException) {
            throw (KeyException)t;
         } else {
            String msg = "Invalid " + this.getId() + " ASN.1 encoding: " + t.getMessage();
            throw new InvalidKeyException(msg, t);
         }
      }
   }

   protected byte[] doGetKeyMaterial(Key key) {
      byte[] encoded = KeysBridge.getEncoded(key);
      int i = Bytes.indexOf(encoded, this.ASN1_OID);
      Assert.gt(i, -1, "Missing or incorrect algorithm OID.");
      i += this.ASN1_OID.length;
      int keyLen = 0;
      if (encoded[i] == 5) {
         ++i;
         int unusedBytes = encoded[i];
         Assert.eq(unusedBytes, 0, "OID NULL terminator should indicate zero unused bytes.");
         ++i;
      }

      if (encoded[i] == 3) {
         ++i;
         keyLen = encoded[i++];
         int unusedBytes = encoded[i++];
         Assert.eq(unusedBytes, 0, "BIT STREAM should not indicate unused bytes.");
         --keyLen;
      } else if (encoded[i] == 4) {
         ++i;
         keyLen = encoded[i++];
         if (encoded[i] == 4) {
            ++i;
            keyLen = encoded[i++];
         }
      }

      Assert.eq(keyLen, this.encodedKeyByteLength, "Invalid key length.");
      byte[] result = Arrays.copyOfRange(encoded, i, i + keyLen);
      keyLen = Bytes.length(result);
      Assert.eq(keyLen, this.encodedKeyByteLength, "Invalid key length.");
      return result;
   }

   private void assertLength(byte[] raw, boolean isPublic) {
      int len = Bytes.length(raw);
      if (len != this.encodedKeyByteLength) {
         String msg = "Invalid " + this.getId() + " encoded " + (isPublic ? "PublicKey" : "PrivateKey") + " length. Should be " + Bytes.bytesMsg(this.encodedKeyByteLength) + ", found " + Bytes.bytesMsg(len) + ".";
         throw new InvalidKeyException(msg);
      }
   }

   public PublicKey toPublicKey(byte[] x, Provider provider) {
      this.assertLength(x, true);
      byte[] encoded = Bytes.concat(this.PUBLIC_KEY_ASN1_PREFIX, x);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(encoded);
      JcaTemplate template = new JcaTemplate(this.getJcaName(), provider);
      return template.generatePublic(spec);
   }

   KeySpec privateKeySpec(byte[] d, boolean standard) {
      byte[] prefix = standard ? this.PRIVATE_KEY_ASN1_PREFIX : this.PRIVATE_KEY_JDK11_PREFIX;
      byte[] encoded = Bytes.concat(prefix, d);
      return new PKCS8EncodedKeySpec(encoded);
   }

   public PrivateKey toPrivateKey(byte[] d, Provider provider) {
      this.assertLength(d, false);
      KeySpec spec = this.privateKeySpec(d, true);
      JcaTemplate template = new JcaTemplate(this.getJcaName(), provider);
      return template.generatePrivate(spec);
   }

   public boolean isSignatureCurve() {
      return this.signatureCurve;
   }

   public KeyPairBuilder keyPair() {
      return new DefaultKeyPairBuilder(this.getJcaName(), this.keyBitLength);
   }

   public static boolean isEdwards(Key key) {
      if (key == null) {
         return false;
      } else {
         String alg = Strings.clean(key.getAlgorithm());
         return "EdDSA".equals(alg) || "XDH".equals(alg) || findByKey(key) != null;
      }
   }

   public static PublicKey derivePublic(PrivateKey pk) throws KeyException {
      return (PublicKey)EdwardsPublicKeyDeriver.INSTANCE.apply(pk);
   }

   public static EdwardsCurve findById(String id) {
      return (EdwardsCurve)REGISTRY.get(id);
   }

   public static EdwardsCurve findByKey(Key key) {
      if (key == null) {
         return null;
      } else {
         String alg = key.getAlgorithm();
         EdwardsCurve curve = findById(alg);
         if (curve == null) {
            alg = (String)CURVE_NAME_FINDER.apply(key);
            curve = findById(alg);
         }

         byte[] encoded = KeysBridge.findEncoded(key);
         if (curve == null && !Bytes.isEmpty(encoded)) {
            int oidTerminalNode = findOidTerminalNode(encoded);
            curve = (EdwardsCurve)BY_OID_TERMINAL_NODE.get(oidTerminalNode);
         }

         if (curve != null && !Bytes.isEmpty(encoded)) {
            try {
               curve.getKeyMaterial(key);
            } catch (Throwable var5) {
               curve = null;
            }
         }

         return curve;
      }
   }

   public boolean contains(Key key) {
      EdwardsCurve curve = findByKey(key);
      return curve.equals(this);
   }

   private static int findOidTerminalNode(byte[] encoded) {
      int index = Bytes.indexOf(encoded, ASN1_OID_PREFIX);
      if (index > -1) {
         index += ASN1_OID_PREFIX.length;
         if (index < encoded.length) {
            return encoded[index];
         }
      }

      return -1;
   }

   public static EdwardsCurve forKey(Key key) {
      Assert.notNull(key, "Key cannot be null.");
      EdwardsCurve curve = findByKey(key);
      if (curve == null) {
         String msg = "Unrecognized Edwards Curve key: [" + KeysBridge.toString(key) + "]";
         throw new InvalidKeyException(msg);
      } else {
         return curve;
      }
   }

   static Key assertEdwards(Key key) {
      forKey(key);
      return key;
   }

   static {
      VALUES = Collections.of(new EdwardsCurve[]{X25519, X448, Ed25519, Ed448});
      REGISTRY = new LinkedHashMap(8);
      BY_OID_TERMINAL_NODE = new LinkedHashMap(4);

      for(EdwardsCurve curve : VALUES) {
         int subcategoryId = curve.ASN1_OID[curve.ASN1_OID.length - 1];
         BY_OID_TERMINAL_NODE.put(subcategoryId, curve);
         REGISTRY.put(curve.getId(), curve);
         REGISTRY.put(curve.OID, curve);
      }

   }
}
