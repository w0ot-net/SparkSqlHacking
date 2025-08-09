package io.jsonwebtoken.security;

import java.util.Map;
import java.util.Set;

public interface JwkSet extends Map, Iterable {
   Set getKeys();
}
