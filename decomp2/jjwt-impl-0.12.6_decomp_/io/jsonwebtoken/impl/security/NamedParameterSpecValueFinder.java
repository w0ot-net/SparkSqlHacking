package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.lang.Functions;
import io.jsonwebtoken.impl.lang.OptionalMethodInvoker;
import java.security.Key;

public class NamedParameterSpecValueFinder implements Function {
   private static final Function EDEC_KEY_GET_PARAMS = new OptionalMethodInvoker("java.security.interfaces.EdECKey", "getParams");
   private static final Function XEC_KEY_GET_PARAMS = new OptionalMethodInvoker("java.security.interfaces.XECKey", "getParams");
   private static final Function GET_NAME = new OptionalMethodInvoker("java.security.spec.NamedParameterSpec", "getName");
   private static final Function COMPOSED;

   public String apply(Key key) {
      return (String)COMPOSED.apply(key);
   }

   static {
      COMPOSED = Functions.andThen(Functions.firstResult(EDEC_KEY_GET_PARAMS, XEC_KEY_GET_PARAMS), GET_NAME);
   }
}
