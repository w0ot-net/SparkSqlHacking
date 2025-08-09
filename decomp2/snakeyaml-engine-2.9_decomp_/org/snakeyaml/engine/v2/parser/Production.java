package org.snakeyaml.engine.v2.parser;

import org.snakeyaml.engine.v2.events.Event;

interface Production {
   Event produce();
}
