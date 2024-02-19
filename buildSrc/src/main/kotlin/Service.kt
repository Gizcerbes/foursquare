import java.io.File
import java.util.Base64


fun main() {
	encodeEnvironments()
	//encodeKeystore()
	//repeat(3){ passGenerator()}
}


private fun encodeEnvironments(){
	val f = File("buildSrc/src/main/kotlin/Environments.kt").readBytes()
	val encoded = Base64.getEncoder().encode(f)
	File("encoded.Environments").outputStream().apply {
		write(encoded)
		flush()
	}.close()
}

private fun encodeKeystore(){
	val f = File("app/keystore/keystore.jks").readBytes()
	val encoded = Base64.getEncoder().encode(f)
	File("encoded.keystore").outputStream().apply {
		write(encoded)
		flush()
	}.close()
}


private fun passGenerator(len: Int = 100){
	val lit = ('a'..'z').toList()
	val uplit = ('A'..'Z').toList()
	val num = ('0'..'9').toList()
	val passBuilder = StringBuilder()
	for (i in 0..len){
		val keys = (Math.random() * 3).toInt()
		when(keys){
			0 -> passBuilder.append(lit.random())
			1 -> passBuilder.append(uplit.random())
			2 -> passBuilder.append(num.random())
		}
	}
	println(passBuilder)
}

