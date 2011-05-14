package cfm.util

object Util{
  def ptime[A](f: => A) = {
  val t0 = System.nanoTime
  val ans = f
  printf("Elapsed: %.3f sec\n",(System.nanoTime-t0)*1e-9)
  ans
}
}
