package cfm.util

object Time {
  def nanoToSeconds(nanos: Long): Int = {
    (nanos / 1E9).toInt
  }
}