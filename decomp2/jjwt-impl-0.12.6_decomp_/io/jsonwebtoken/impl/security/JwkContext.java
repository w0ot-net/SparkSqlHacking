package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.impl.X509Context;
import io.jsonwebtoken.impl.lang.Nameable;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.security.HashAlgorithm;
import java.security.Key;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface JwkContext extends Identifiable, Map, ParameterReadable, Nameable, X509Context {
   JwkContext parameter(Parameter var1);

   JwkContext setId(String var1);

   JwkContext setIdThumbprintAlgorithm(HashAlgorithm var1);

   HashAlgorithm getIdThumbprintAlgorithm();

   String getType();

   JwkContext setType(String var1);

   Set getOperations();

   JwkContext setOperations(Collection var1);

   String getAlgorithm();

   JwkContext setAlgorithm(String var1);

   String getPublicKeyUse();

   JwkContext setPublicKeyUse(String var1);

   boolean isSigUse();

   Key getKey();

   JwkContext setKey(Key var1);

   PublicKey getPublicKey();

   JwkContext setPublicKey(PublicKey var1);

   Provider getProvider();

   JwkContext setProvider(Provider var1);

   SecureRandom getRandom();

   JwkContext setRandom(SecureRandom var1);
}
