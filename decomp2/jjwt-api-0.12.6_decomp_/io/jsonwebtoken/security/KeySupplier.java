package io.jsonwebtoken.security;

import java.security.Key;

public interface KeySupplier {
   Key getKey();
}
