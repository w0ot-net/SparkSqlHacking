package org.threeten.extra.scale;

import java.time.Instant;

public interface TimeSource {
   Instant instant();

   UtcInstant utcInstant();

   TaiInstant taiInstant();
}
