package io.buddho.bootblocks.security.json

import io.buddho.bootblocks.security.Authority
import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JNull, JString}


object SecuritySerializers {

  def all = List(AuthoritySerializer)

}

case object AuthoritySerializer extends CustomSerializer[Authority](format => ({
  case JString(s) => Authority(s)
  case JNull => null
}, {
  case a: Authority => JString(a.authority)
}))
