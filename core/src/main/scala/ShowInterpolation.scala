package shapelesscats

import cats.Show
import shapeless._
import shapeless.ops.hlist.{Mapper, ToTraversable}

final class ShowInterpolationOps(sc: StringContext) {
  object show extends ProductArgs {
    def applyProduct[L <: HList](l: L)(implicit stringify: Stringify[L]): String = {
      val expressions = stringify(l).iterator
      val strings = sc.parts.iterator
      val builder = new java.lang.StringBuilder(strings.next)
      while(strings.hasNext) {
        builder.append(expressions.next)
        builder.append(strings.next)
      }
      builder.toString
    }
  }
}

object ShowInterpolation {
  implicit def showLiteral(sc: StringContext): ShowInterpolationOps =
    new ShowInterpolationOps(sc)
}

trait Stringify[L] {
  def apply(l: L): List[String]
}

object Stringify {
  implicit val stringifyHNil: Stringify[HNil] = new Stringify[HNil] {
    def apply(l: HNil) = Nil
  }

  implicit def stringifyHCons[H, T <: HList](implicit showH: Show[H], stringifyTail: Stringify[T]): Stringify[H :: T] = new Stringify[H :: T] {
    def apply(l: H :: T): List[String] = showH.show(l.head) :: stringifyTail(l.tail)
  }
}
