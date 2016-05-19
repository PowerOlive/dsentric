package dsentric

trait Matcher  {
  def apply(j:Any):Boolean
  protected def default:Any
}

object ExistenceMatcher extends Matcher {
  def apply(j:Any):Boolean = true
  protected def default:Any = Dsentric.dNull
}

trait DataMatchers {
  implicit def valueMatcher[T](value:T)(implicit _codec:DCodec[T]) = new Matcher {
    protected val default: Any = _codec(value).value
    def apply(j: Any): Boolean = j == default
  }
}
