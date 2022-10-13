package io.foxcapades.lib.md5

import java.io.File
import java.io.InputStream
import java.io.Reader
import java.security.MessageDigest

/**
 * # MD5 Hashing
 *
 * This object contains methods for generating MD5 checksums of given values.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 */
object MD5 {

  /**
   * Generates an MD5 checksum of the contents of the given string.
   *
   * @param value String to hash.
   *
   * @return An [MD5Hash] instance wrapping the raw digest of the given string.
   *
   * @throws MD5HashException If an exception occurs while attempting to hash
   * the given string.
   *
   * This exception will always have an underlying/causing exception attached.
   *
   * @since 1.0.0
   */
  @JvmStatic
  fun hash(value: String) = hash(value.byteInputStream())

  /**
   * Generates an MD5 checksum of the contents of the given file.
   *
   * @param file File to hash.
   *
   * @return An [MD5Hash] instance wrapping the raw digest of the given file.
   *
   * @throws MD5HashException If an exception occurs while attempting to hash
   * the given file.
   *
   * This exception will always have an underlying/causing exception attached.
   *
   * @since 1.0.0
   */
  @JvmStatic
  fun hash(file: File) = file.inputStream().use { hash(it) }

  /**
   * Generates an MD5 checksum of the contents of the given [Reader].
   *
   * This method **does not** close the given `Reader`
   *
   * @param stream `Reader` whose contents will be hashed.
   *
   * @param bufferSize Number of bytes to read at a time while hashing the
   * given [stream].
   *
   * @return An [MD5Hash] instance wrapping the raw digest of the given stream.
   *
   * @throws MD5HashException If an exception occurs while attempting to hash
   * the given stream.
   *
   * This exception will always have an underlying/causing exception attached.
   *
   * @since 1.0.0
   */
  @JvmStatic
  @JvmOverloads
  fun hash(stream: Reader, bufferSize: Int = 8192) = hash(ReaderInputStream(stream, bufferSize / 2), bufferSize)

  /**
   * Generates an MD5 checksum of the contents of the given [InputStream].
   *
   * This method **does not** close the given `InputStream`
   *
   * @param stream Stream whose contents will be hashed.
   *
   * @param bufferSize Number of bytes to read at a time while hashing the
   * given [stream].
   *
   * @return An [MD5Hash] instance wrapping the raw digest of the given stream.
   *
   * @throws MD5HashException If an exception occurs while attempting to hash
   * the given stream.
   *
   * This exception will always have an underlying/causing exception attached.
   *
   * @since 1.0.0
   */
  @JvmStatic
  @JvmOverloads
  @Throws(MD5HashException::class)
  fun hash(stream: InputStream, bufferSize: Int = 8192): MD5Hash {
    val hasher = MessageDigest.getInstance("MD5")
    val buffer = ByteArray(bufferSize)
    var read   = stream.read(buffer)

    while (read > -1) {
      hasher.update(buffer, 0, read)
      read = stream.read(buffer)
    }

    return MD5Hash(hasher.digest())
  }
}