package io.foxcapades.lib.md5

class MD5HashException(cause: Throwable) :
  RuntimeException("Failed to generate an MD5 hash due to an exception.", cause)