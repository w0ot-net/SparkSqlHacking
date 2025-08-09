package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.CheckedSupplier;
import io.jsonwebtoken.impl.lang.DefaultRegistry;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

public class JcaTemplate {
   private static final List FACTORIES = Collections.of(new InstanceFactory[]{new CipherFactory(), new KeyFactoryFactory(), new SecretKeyFactoryFactory(), new KeyGeneratorFactory(), new KeyPairGeneratorFactory(), new KeyAgreementFactory(), new MessageDigestFactory(), new SignatureFactory(), new MacFactory(), new AlgorithmParametersFactory(), new CertificateFactoryFactory()});
   private static final Registry REGISTRY;
   private final String jcaName;
   private final Provider provider;
   private final SecureRandom secureRandom;

   protected Provider findBouncyCastle() {
      return Providers.findBouncyCastle();
   }

   JcaTemplate(String jcaName) {
      this(jcaName, (Provider)null);
   }

   JcaTemplate(String jcaName, Provider provider) {
      this(jcaName, provider, (SecureRandom)null);
   }

   JcaTemplate(String jcaName, Provider provider, SecureRandom secureRandom) {
      this.jcaName = (String)Assert.hasText(jcaName, "jcaName string cannot be null or empty.");
      this.secureRandom = secureRandom != null ? secureRandom : Randoms.secureRandom();
      this.provider = provider;
   }

   private Object execute(Class clazz, CheckedFunction callback, Provider provider) throws Exception {
      InstanceFactory<?> factory = (InstanceFactory)REGISTRY.get(clazz);
      Assert.notNull(factory, "Unsupported JCA instance class.");
      Object object = factory.get(this.jcaName, provider);
      T instance = (T)Assert.isInstanceOf(clazz, object, "Factory instance does not match expected type.");
      return callback.apply(instance);
   }

   private Object execute(Class clazz, CheckedSupplier fn) throws SecurityException {
      try {
         return fn.get();
      } catch (SecurityException se) {
         throw se;
      } catch (Throwable t) {
         String msg = clazz.getSimpleName() + " callback execution failed: " + t.getMessage();
         throw new SecurityException(msg, t);
      }
   }

   private Object execute(final Class clazz, final CheckedFunction fn) throws SecurityException {
      return this.execute(clazz, new CheckedSupplier() {
         public Object get() throws Exception {
            return JcaTemplate.this.execute(clazz, fn, JcaTemplate.this.provider);
         }
      });
   }

   protected Object fallback(final Class clazz, final CheckedFunction callback) throws SecurityException {
      return this.execute(clazz, new CheckedSupplier() {
         public Object get() throws Exception {
            try {
               return JcaTemplate.this.execute(clazz, callback, JcaTemplate.this.provider);
            } catch (Exception e) {
               try {
                  Provider bc = JcaTemplate.this.findBouncyCastle();
                  if (bc != null) {
                     return JcaTemplate.this.execute(clazz, callback, bc);
                  }
               } catch (Throwable var3) {
               }

               throw e;
            }
         }
      });
   }

   public Object withCipher(CheckedFunction fn) throws SecurityException {
      return this.execute(Cipher.class, fn);
   }

   public Object withKeyFactory(CheckedFunction fn) throws SecurityException {
      return this.execute(KeyFactory.class, fn);
   }

   public Object withSecretKeyFactory(CheckedFunction fn) throws SecurityException {
      return this.execute(SecretKeyFactory.class, fn);
   }

   public Object withKeyGenerator(CheckedFunction fn) throws SecurityException {
      return this.execute(KeyGenerator.class, fn);
   }

   public Object withKeyAgreement(CheckedFunction fn) throws SecurityException {
      return this.execute(KeyAgreement.class, fn);
   }

   public Object withKeyPairGenerator(CheckedFunction fn) throws SecurityException {
      return this.execute(KeyPairGenerator.class, fn);
   }

   public Object withMessageDigest(CheckedFunction fn) throws SecurityException {
      return this.execute(MessageDigest.class, fn);
   }

   public Object withSignature(CheckedFunction fn) throws SecurityException {
      return this.execute(Signature.class, fn);
   }

   public Object withMac(CheckedFunction fn) throws SecurityException {
      return this.execute(Mac.class, fn);
   }

   public Object withAlgorithmParameters(CheckedFunction fn) throws SecurityException {
      return this.execute(AlgorithmParameters.class, fn);
   }

   public Object withCertificateFactory(CheckedFunction fn) throws SecurityException {
      return this.execute(CertificateFactory.class, fn);
   }

   public SecretKey generateSecretKey(final int keyBitLength) {
      return (SecretKey)this.withKeyGenerator(new CheckedFunction() {
         public SecretKey apply(KeyGenerator generator) {
            generator.init(keyBitLength, JcaTemplate.this.secureRandom);
            return generator.generateKey();
         }
      });
   }

   public KeyPair generateKeyPair() {
      return (KeyPair)this.withKeyPairGenerator(new CheckedFunction() {
         public KeyPair apply(KeyPairGenerator gen) {
            return gen.generateKeyPair();
         }
      });
   }

   public KeyPair generateKeyPair(final int keyBitLength) {
      return (KeyPair)this.withKeyPairGenerator(new CheckedFunction() {
         public KeyPair apply(KeyPairGenerator generator) {
            generator.initialize(keyBitLength, JcaTemplate.this.secureRandom);
            return generator.generateKeyPair();
         }
      });
   }

   public KeyPair generateKeyPair(final AlgorithmParameterSpec params) {
      return (KeyPair)this.withKeyPairGenerator(new CheckedFunction() {
         public KeyPair apply(KeyPairGenerator generator) throws InvalidAlgorithmParameterException {
            generator.initialize(params, JcaTemplate.this.secureRandom);
            return generator.generateKeyPair();
         }
      });
   }

   public PublicKey generatePublic(final KeySpec spec) {
      return (PublicKey)this.fallback(KeyFactory.class, new CheckedFunction() {
         public PublicKey apply(KeyFactory keyFactory) throws Exception {
            return keyFactory.generatePublic(spec);
         }
      });
   }

   protected boolean isJdk11() {
      return System.getProperty("java.version").startsWith("11");
   }

   private boolean isJdk8213363Bug(InvalidKeySpecException e) {
      return this.isJdk11() && ("XDH".equals(this.jcaName) || "X25519".equals(this.jcaName) || "X448".equals(this.jcaName)) && e.getCause() instanceof InvalidKeyException && !Objects.isEmpty(e.getStackTrace()) && "sun.security.ec.XDHKeyFactory".equals(e.getStackTrace()[0].getClassName()) && "engineGeneratePrivate".equals(e.getStackTrace()[0].getMethodName());
   }

   private int getJdk8213363BugExpectedSize(InvalidKeyException e) {
      String msg = e.getMessage();
      String prefix = "key length must be ";
      if (Strings.hasText(msg) && msg.startsWith(prefix)) {
         String expectedSizeString = msg.substring(prefix.length());

         try {
            return Integer.parseInt(expectedSizeString);
         } catch (NumberFormatException var6) {
         }
      }

      return -1;
   }

   private KeySpec respecIfNecessary(InvalidKeySpecException e, KeySpec spec) {
      if (!(spec instanceof PKCS8EncodedKeySpec)) {
         return null;
      } else {
         PKCS8EncodedKeySpec pkcs8Spec = (PKCS8EncodedKeySpec)spec;
         byte[] encoded = pkcs8Spec.getEncoded();
         if (this.isJdk8213363Bug(e)) {
            InvalidKeyException cause = (InvalidKeyException)Assert.isInstanceOf(InvalidKeyException.class, e.getCause(), "Unexpected argument.");
            int size = this.getJdk8213363BugExpectedSize(cause);
            if ((size == 32 || size == 56) && Bytes.length(encoded) >= size) {
               byte[] adjusted = new byte[size];
               System.arraycopy(encoded, encoded.length - size, adjusted, 0, size);
               EdwardsCurve curve = size == 32 ? EdwardsCurve.X25519 : EdwardsCurve.X448;
               return curve.privateKeySpec(adjusted, false);
            }
         }

         return null;
      }
   }

   protected PrivateKey generatePrivate(KeyFactory factory, KeySpec spec) throws InvalidKeySpecException {
      return factory.generatePrivate(spec);
   }

   public PrivateKey generatePrivate(final KeySpec spec) {
      return (PrivateKey)this.fallback(KeyFactory.class, new CheckedFunction() {
         public PrivateKey apply(KeyFactory keyFactory) throws Exception {
            try {
               return JcaTemplate.this.generatePrivate(keyFactory, spec);
            } catch (InvalidKeySpecException e) {
               KeySpec respec = JcaTemplate.this.respecIfNecessary(e, spec);
               if (respec != null) {
                  return JcaTemplate.this.generatePrivate(keyFactory, respec);
               } else {
                  throw e;
               }
            }
         }
      });
   }

   public X509Certificate generateX509Certificate(final byte[] x509DerBytes) {
      return (X509Certificate)this.fallback(CertificateFactory.class, new CheckedFunction() {
         public X509Certificate apply(CertificateFactory cf) throws CertificateException {
            InputStream is = Streams.of(x509DerBytes);
            return (X509Certificate)cf.generateCertificate(is);
         }
      });
   }

   static {
      REGISTRY = new DefaultRegistry("JCA Instance Factory", "instance class", FACTORIES, new Function() {
         public Class apply(InstanceFactory factory) {
            return factory.getInstanceClass();
         }
      });
   }

   private abstract static class JcaInstanceFactory implements InstanceFactory {
      private final Class clazz;
      private final ConcurrentMap FALLBACK_ATTEMPTS = new ConcurrentHashMap();

      JcaInstanceFactory(Class clazz) {
         this.clazz = (Class)Assert.notNull(clazz, "Class argument cannot be null.");
      }

      public Class getInstanceClass() {
         return this.clazz;
      }

      public String getId() {
         return this.clazz.getSimpleName();
      }

      protected Provider findBouncyCastle() {
         return Providers.findBouncyCastle();
      }

      public final Object get(String jcaName, Provider specifiedProvider) throws Exception {
         Assert.hasText(jcaName, "jcaName cannot be null or empty.");
         Provider provider = specifiedProvider;
         Boolean attempted = (Boolean)this.FALLBACK_ATTEMPTS.get(jcaName);
         if (specifiedProvider == null && attempted != null && attempted) {
            provider = this.findBouncyCastle();
         }

         try {
            return this.doGet(jcaName, provider);
         } catch (NoSuchAlgorithmException nsa) {
            if (specifiedProvider == null && attempted == null) {
               Provider fallback = this.findBouncyCastle();
               if (fallback != null) {
                  try {
                     T value = (T)this.doGet(jcaName, fallback);
                     this.FALLBACK_ATTEMPTS.putIfAbsent(jcaName, Boolean.TRUE);
                     return value;
                  } catch (Throwable var8) {
                     this.FALLBACK_ATTEMPTS.putIfAbsent(jcaName, Boolean.FALSE);
                  }
               }
            }

            throw this.wrap(nsa, jcaName, specifiedProvider, (Provider)null);
         } catch (Exception e) {
            throw this.wrap(e, jcaName, specifiedProvider, (Provider)null);
         }
      }

      protected abstract Object doGet(String var1, Provider var2) throws Exception;

      protected Exception wrap(Exception e, String jcaName, Provider specifiedProvider, Provider fallbackProvider) {
         String msg = "Unable to obtain '" + jcaName + "' " + this.getId() + " instance from ";
         if (specifiedProvider != null) {
            msg = msg + "specified '" + specifiedProvider + "' Provider";
         } else {
            msg = msg + "default JCA Provider";
         }

         if (fallbackProvider != null) {
            msg = msg + " or fallback '" + fallbackProvider + "' Provider";
         }

         msg = msg + ": " + e.getMessage();
         return this.wrap(msg, e);
      }

      protected Exception wrap(String msg, Exception cause) {
         return (Exception)(!Signature.class.isAssignableFrom(this.clazz) && !Mac.class.isAssignableFrom(this.clazz) ? new SecurityException(msg, cause) : new SignatureException(msg, cause));
      }
   }

   private static class CipherFactory extends JcaInstanceFactory {
      CipherFactory() {
         super(Cipher.class);
      }

      public Cipher doGet(String jcaName, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException {
         return provider != null ? Cipher.getInstance(jcaName, provider) : Cipher.getInstance(jcaName);
      }
   }

   private static class KeyFactoryFactory extends JcaInstanceFactory {
      KeyFactoryFactory() {
         super(KeyFactory.class);
      }

      public KeyFactory doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? KeyFactory.getInstance(jcaName, provider) : KeyFactory.getInstance(jcaName);
      }
   }

   private static class SecretKeyFactoryFactory extends JcaInstanceFactory {
      SecretKeyFactoryFactory() {
         super(SecretKeyFactory.class);
      }

      public SecretKeyFactory doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? SecretKeyFactory.getInstance(jcaName, provider) : SecretKeyFactory.getInstance(jcaName);
      }
   }

   private static class KeyGeneratorFactory extends JcaInstanceFactory {
      KeyGeneratorFactory() {
         super(KeyGenerator.class);
      }

      public KeyGenerator doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? KeyGenerator.getInstance(jcaName, provider) : KeyGenerator.getInstance(jcaName);
      }
   }

   private static class KeyPairGeneratorFactory extends JcaInstanceFactory {
      KeyPairGeneratorFactory() {
         super(KeyPairGenerator.class);
      }

      public KeyPairGenerator doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? KeyPairGenerator.getInstance(jcaName, provider) : KeyPairGenerator.getInstance(jcaName);
      }
   }

   private static class KeyAgreementFactory extends JcaInstanceFactory {
      KeyAgreementFactory() {
         super(KeyAgreement.class);
      }

      public KeyAgreement doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? KeyAgreement.getInstance(jcaName, provider) : KeyAgreement.getInstance(jcaName);
      }
   }

   private static class MessageDigestFactory extends JcaInstanceFactory {
      MessageDigestFactory() {
         super(MessageDigest.class);
      }

      public MessageDigest doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? MessageDigest.getInstance(jcaName, provider) : MessageDigest.getInstance(jcaName);
      }
   }

   private static class SignatureFactory extends JcaInstanceFactory {
      SignatureFactory() {
         super(Signature.class);
      }

      public Signature doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? Signature.getInstance(jcaName, provider) : Signature.getInstance(jcaName);
      }
   }

   private static class MacFactory extends JcaInstanceFactory {
      MacFactory() {
         super(Mac.class);
      }

      public Mac doGet(String jcaName, Provider provider) throws NoSuchAlgorithmException {
         return provider != null ? Mac.getInstance(jcaName, provider) : Mac.getInstance(jcaName);
      }
   }

   private static class AlgorithmParametersFactory extends JcaInstanceFactory {
      AlgorithmParametersFactory() {
         super(AlgorithmParameters.class);
      }

      protected AlgorithmParameters doGet(String jcaName, Provider provider) throws Exception {
         return provider != null ? AlgorithmParameters.getInstance(jcaName, provider) : AlgorithmParameters.getInstance(jcaName);
      }
   }

   private static class CertificateFactoryFactory extends JcaInstanceFactory {
      CertificateFactoryFactory() {
         super(CertificateFactory.class);
      }

      protected CertificateFactory doGet(String jcaName, Provider provider) throws Exception {
         return provider != null ? CertificateFactory.getInstance(jcaName, provider) : CertificateFactory.getInstance(jcaName);
      }
   }

   private interface InstanceFactory extends Identifiable {
      Class getInstanceClass();

      Object get(String var1, Provider var2) throws Exception;
   }
}
