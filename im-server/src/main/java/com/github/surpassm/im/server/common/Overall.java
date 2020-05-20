package com.github.surpassm.im.server.common;

import io.netty.util.AttributeKey;

public interface Overall {

    AttributeKey<String> key = AttributeKey.valueOf("username");
}
