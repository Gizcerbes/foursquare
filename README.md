# Demonstration

### Uses Foursquare API

### There is a launcher

### Requests places at coordinates 53.9057644, 27.5582305 with a radius of 4000 and limits it to 30 places.

### Uses OAuth 2.0 for authorization.

### Data is cached and used when the Internet is unavailable.

### Clears data when the user logs out.


https://github.com/Gizcerbes/foursquare/assets/26376753/c9444f1f-d7a8-4453-9d22-a5a399801661


</video>

# Add files to build

#### path `buildSrc/src/main/kotlin/Environments.kt`

```
object Environments {
	
	const val API_CLIENT_ID = "\" ... \""
	const val API_CLIENT_SECRET = "\" ... \""
	const val API_KEY = "\" ... \""

	const val STORE_FILE = " ... "
	const val STORE_PASSWORD = " ... "
	const val KEY_ALIAS = " ... "
	const val KEY_PASSWORD = " ... "
	
}
```

#### path `app/keystore/keystore.jks`
