package io.jsonwebtoken.security;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import javax.crypto.SecretKey;

public interface DynamicJwkBuilder extends JwkBuilder {
   PublicJwkBuilder chain(List var1) throws UnsupportedKeyException;

   SecretJwkBuilder key(SecretKey var1);

   RsaPublicJwkBuilder key(RSAPublicKey var1);

   RsaPrivateJwkBuilder key(RSAPrivateKey var1);

   EcPublicJwkBuilder key(ECPublicKey var1);

   EcPrivateJwkBuilder key(ECPrivateKey var1);

   PublicJwkBuilder key(PublicKey var1) throws UnsupportedKeyException;

   PrivateJwkBuilder key(PrivateKey var1) throws UnsupportedKeyException;

   PrivateJwkBuilder keyPair(java.security.KeyPair var1) throws UnsupportedKeyException;

   OctetPublicJwkBuilder octetKey(PublicKey var1);

   OctetPrivateJwkBuilder octetKey(PrivateKey var1);

   OctetPublicJwkBuilder octetChain(List var1);

   OctetPrivateJwkBuilder octetKeyPair(java.security.KeyPair var1);

   EcPublicJwkBuilder ecChain(List var1);

   EcPrivateJwkBuilder ecKeyPair(java.security.KeyPair var1) throws IllegalArgumentException;

   RsaPublicJwkBuilder rsaChain(List var1);

   RsaPrivateJwkBuilder rsaKeyPair(java.security.KeyPair var1) throws IllegalArgumentException;
}
