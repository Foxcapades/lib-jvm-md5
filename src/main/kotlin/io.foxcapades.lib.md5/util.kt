@file:Suppress("NOTHING_TO_INLINE")

package io.foxcapades.lib.md5

internal inline infix fun UByte.shr(p: Int) = (toInt() shr p).toUByte()

internal inline fun StringBuilder.appendHexU(b: UByte) =
  if (b < 15u) {
    append('0')
    append(byteToHexCharU(b))
  } else {
    append(byteToHexCharU(b shr 4))
    append(byteToHexCharU(b and 15u))
  }

internal inline fun StringBuilder.appendHexL(b: UByte) =
  if (b < 15u) {
    append('0')
    append(byteToHexCharL(b))
  } else {
    append(byteToHexCharL(b shr 4))
    append(byteToHexCharL(b and 15u))
  }


internal inline fun byteToHexCharU(b: UByte) =
  if (b < 10u) { b + 48u } else { b + 55u }.toInt().toChar()
internal inline fun byteToHexCharL(b: UByte) =
  if (b < 10u) { b + 48u } else { b + 87u }.toInt().toChar()


