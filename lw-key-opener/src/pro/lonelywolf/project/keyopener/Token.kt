package pro.lonelywolf.project.keyopener

interface TokenProvider {
  fun provide(): String
}