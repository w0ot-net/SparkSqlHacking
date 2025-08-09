package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.impl.DefaultJweHeader;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.Curve;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.DynamicJwkBuilder;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Jwks;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.KeyLengthSupplier;
import io.jsonwebtoken.security.KeyPairBuilder;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.OctetPublicJwk;
import io.jsonwebtoken.security.PublicJwk;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SecurityException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECKey;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

class EcdhKeyAlgorithm extends CryptoAlgorithm implements KeyAlgorithm {
   protected static final String JCA_NAME = "ECDH";
   protected static final String XDH_JCA_NAME = "XDH";
   protected static final String DEFAULT_ID = "ECDH-ES";
   private static final String CONCAT_KDF_HASH_ALG_NAME = "SHA-256";
   private static final ConcatKDF CONCAT_KDF = new ConcatKDF("SHA-256");
   private final KeyAlgorithm WRAP_ALG;

   private static String idFor(KeyAlgorithm wrapAlg) {
      return wrapAlg instanceof DirectKeyAlgorithm ? "ECDH-ES" : "ECDH-ES+" + wrapAlg.getId();
   }

   EcdhKeyAlgorithm() {
      this(new DirectKeyAlgorithm());
   }

   EcdhKeyAlgorithm(KeyAlgorithm wrapAlg) {
      super(idFor(wrapAlg), "ECDH");
      this.WRAP_ALG = (KeyAlgorithm)Assert.notNull(wrapAlg, "Wrap algorithm cannot be null.");
   }

   protected KeyPair generateKeyPair(Curve curve, Provider provider, SecureRandom random) {
      return (KeyPair)((KeyPairBuilder)((KeyPairBuilder)curve.keyPair().provider(provider)).random(random)).build();
   }

   protected byte[] generateZ(final KeyRequest request, final PublicKey pub, final PrivateKey priv) {
      return (byte[])this.jca(request).withKeyAgreement(new CheckedFunction() {
         public byte[] apply(KeyAgreement keyAgreement) throws Exception {
            keyAgreement.init(KeysBridge.root((Key)priv), CryptoAlgorithm.ensureSecureRandom(request));
            keyAgreement.doPhase(pub, true);
            return keyAgreement.generateSecret();
         }
      });
   }

   protected String getConcatKDFAlgorithmId(AeadAlgorithm enc) {
      return this.WRAP_ALG instanceof DirectKeyAlgorithm ? (String)Assert.hasText(enc.getId(), "AeadAlgorithm id cannot be null or empty.") : this.getId();
   }

   private byte[] createOtherInfo(int keydatalen, String AlgorithmID, byte[] PartyUInfo, byte[] PartyVInfo) {
      Assert.hasText(AlgorithmID, "AlgorithmId cannot be null or empty.");
      byte[] algIdBytes = AlgorithmID.getBytes(StandardCharsets.US_ASCII);
      PartyUInfo = Arrays.length(PartyUInfo) == 0 ? Bytes.EMPTY : PartyUInfo;
      PartyVInfo = Arrays.length(PartyVInfo) == 0 ? Bytes.EMPTY : PartyVInfo;
      return Bytes.concat(Bytes.toBytes(algIdBytes.length), algIdBytes, Bytes.toBytes(PartyUInfo.length), PartyUInfo, Bytes.toBytes(PartyVInfo.length), PartyVInfo, Bytes.toBytes(keydatalen), Bytes.EMPTY);
   }

   private int getKeyBitLength(AeadAlgorithm enc) {
      int bitLength = this.WRAP_ALG instanceof KeyLengthSupplier ? ((KeyLengthSupplier)this.WRAP_ALG).getKeyBitLength() : enc.getKeyBitLength();
      return (Integer)Assert.gt(bitLength, 0, "Algorithm keyBitLength must be > 0");
   }

   private SecretKey deriveKey(KeyRequest request, PublicKey publicKey, PrivateKey privateKey) {
      AeadAlgorithm enc = (AeadAlgorithm)Assert.notNull(request.getEncryptionAlgorithm(), "Request encryptionAlgorithm cannot be null.");
      int requiredCekBitLen = this.getKeyBitLength(enc);
      String AlgorithmID = this.getConcatKDFAlgorithmId(enc);
      byte[] apu = request.getHeader().getAgreementPartyUInfo();
      byte[] apv = request.getHeader().getAgreementPartyVInfo();
      byte[] OtherInfo = this.createOtherInfo(requiredCekBitLen, AlgorithmID, apu, apv);
      byte[] Z = this.generateZ(request, publicKey, privateKey);

      SecretKey var11;
      try {
         var11 = CONCAT_KDF.deriveKey(Z, (long)requiredCekBitLen, OtherInfo);
      } finally {
         Bytes.clear(Z);
      }

      return var11;
   }

   protected String getJcaName(Request request) {
      if (request instanceof SecureRequest) {
         return ((SecureRequest)request).getKey() instanceof ECKey ? super.getJcaName(request) : "XDH";
      } else {
         return request.getPayload() instanceof ECKey ? super.getJcaName(request) : "XDH";
      }
   }

   private static AbstractCurve assertCurve(Key key) {
      Curve curve = StandardCurves.findByKey(key);
      if (curve == null) {
         String type = key instanceof PublicKey ? "encryption " : "decryption ";
         String msg = "Unable to determine JWA-standard Elliptic Curve for " + type + "key [" + KeysBridge.toString(key) + "]";
         throw new InvalidKeyException(msg);
      } else if (curve instanceof EdwardsCurve && ((EdwardsCurve)curve).isSignatureCurve()) {
         String msg = curve.getId() + " keys may not be used with ECDH-ES key agreement algorithms per " + "https://www.rfc-editor.org/rfc/rfc8037#section-3.1.";
         throw new InvalidKeyException(msg);
      } else {
         return (AbstractCurve)Assert.isInstanceOf(AbstractCurve.class, curve, "AbstractCurve instance expected.");
      }
   }

   public KeyResult getEncryptionKey(KeyRequest request) throws SecurityException {
      Assert.notNull(request, "Request cannot be null.");
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "Request JweHeader cannot be null.");
      PublicKey publicKey = (PublicKey)Assert.notNull(request.getPayload(), "Encryption PublicKey cannot be null.");
      Curve curve = assertCurve(publicKey);
      Assert.stateNotNull(curve, "Internal implementation state: Curve cannot be null.");
      SecureRandom random = ensureSecureRandom(request);
      DynamicJwkBuilder<?, ?> jwkBuilder = (DynamicJwkBuilder)Jwks.builder().random(random);
      KeyPair pair = this.generateKeyPair(curve, (Provider)null, random);
      Assert.stateNotNull(pair, "Internal implementation state: KeyPair cannot be null.");
      PublicJwk<?> jwk = (PublicJwk)jwkBuilder.key(pair.getPublic()).build();
      SecretKey derived = this.deriveKey(request, publicKey, pair.getPrivate());
      KeyRequest<SecretKey> wrapReq = new DefaultKeyRequest(derived, request.getProvider(), request.getSecureRandom(), request.getHeader(), request.getEncryptionAlgorithm());
      KeyResult result = this.WRAP_ALG.getEncryptionKey(wrapReq);
      header.put(DefaultJweHeader.EPK.getId(), jwk);
      return result;
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      Assert.notNull(request, "Request cannot be null.");
      JweHeader header = (JweHeader)Assert.notNull(request.getHeader(), "Request JweHeader cannot be null.");
      PrivateKey privateKey = (PrivateKey)Assert.notNull(request.getKey(), "Decryption PrivateKey cannot be null.");
      ParameterReadable reader = new RequiredParameterReader(header);
      PublicJwk<?> epk = (PublicJwk)reader.get(DefaultJweHeader.EPK);
      AbstractCurve curve = assertCurve(privateKey);
      Assert.stateNotNull(curve, "Internal implementation state: Curve cannot be null.");
      Class<?> epkClass = curve instanceof ECCurve ? EcPublicJwk.class : OctetPublicJwk.class;
      if (!epkClass.isInstance(epk)) {
         String msg = "JWE Header " + DefaultJweHeader.EPK + " value is not an Elliptic Curve " + "Public JWK. Value: " + epk;
         throw new InvalidKeyException(msg);
      } else if (!curve.contains(epk.toKey())) {
         String msg = "JWE Header " + DefaultJweHeader.EPK + " value does not represent " + "a point on the expected curve. Value: " + epk;
         throw new InvalidKeyException(msg);
      } else {
         SecretKey derived = this.deriveKey(request, (PublicKey)epk.toKey(), privateKey);
         DecryptionKeyRequest<SecretKey> unwrapReq = new DefaultDecryptionKeyRequest((byte[])request.getPayload(), (Provider)null, request.getSecureRandom(), header, request.getEncryptionAlgorithm(), derived);
         return this.WRAP_ALG.getDecryptionKey(unwrapReq);
      }
   }
}
