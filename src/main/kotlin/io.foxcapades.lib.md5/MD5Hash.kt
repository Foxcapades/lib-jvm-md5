package io.foxcapades.lib.md5

/**
 * # MD5 Hash Wrapper
 *
 * Wrapper type around a raw MD5 hash that provides methods for accessing the
 * data in various ways.
 *
 * This type is suitable for use in Sets or as Map keys.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 */
data class MD5Hash(private val raw: ByteArray) {

  /**
   * Gets a copy of the underlying data.
   *
   * Mutating the returned array will not affect the state of this MD5Hash
   * instance.
   */
  val bytes: ByteArray
    get() = raw.copyOf()

  /**
   * Gets the raw underlying data.
   *
   * **WARNING:** Mutating the returned array **will** affect the state of this
   * MD5Hash instance.
   */
  val unsafeBytes: ByteArray
    get() = raw

  /**
   * Gets the underlying data as a hex encoded string.
   *
   * @param lowercase Whether the hex encoded string should use lowercase or
   * uppercase characters.
   *
   * Defaults to `false`, meaning the default is uppercase.
   *
   * @return Hex encoded string.
   */
  @JvmOverloads
  fun toHexString(lowercase: Boolean = false) =
    (
      if (lowercase)
        StringBuilder(raw.size * 2).also { sb -> raw.forEach { b -> sb.appendHexL(b.toUByte()) } }
      else
        StringBuilder(raw.size * 2).also { sb -> raw.forEach { b -> sb.appendHexU(b.toUByte()) } }
    )
      .toString()

  override fun toString() =
    toHexString()

  override fun hashCode() =
    bytes.contentHashCode()

  override fun equals(other: Any?) =
    if (other is ByteArray) bytes.contentEquals(other) else false
}
