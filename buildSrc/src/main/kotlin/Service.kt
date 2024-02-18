import java.io.File
import java.util.Base64


fun main() {
	encodeEnvironments()
}


private fun encodeEnvironments(){
	val f = File("buildSrc/src/main/kotlin/Environments.kt").readBytes()
	val encoded = Base64.getEncoder().encode(f)
	File("encoded.Environments").outputStream().apply {
		write(encoded)
		flush()
	}.close()
}


