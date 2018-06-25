package oberon


final case class Error(private val message: String = "",
  private val cause: Throwable = None.orNull) extends Exception(message, cause)

