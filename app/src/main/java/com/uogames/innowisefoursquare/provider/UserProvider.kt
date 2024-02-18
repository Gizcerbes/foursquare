package com.uogames.innowisefoursquare.provider

import com.uogames.innowisefoursquare.database.dao.UserDAO
import com.uogames.innowisefoursquare.network.v2.NetworkUserProvider
import com.uogames.innowisefoursquare.provider.dto.UserSelfDTO
import com.uogames.innowisefoursquare.provider.map.UserSelfMap.toDTO
import com.uogames.innowisefoursquare.provider.map.UserSelfMap.toEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class UserProvider @Inject constructor(
	private val provider: NetworkUserProvider,
	private val dao: UserDAO
) {


	suspend fun getUserSelf(
		uid: String? = null
	): UserSelfDTO? {
		return try {
			val r = provider.getUserSelf()
			withContext(coroutineContext) { dao.insert(r.toEntity()) }
			r.toDTO()
		} catch (e: Throwable) {
			if (uid != null) dao.getByUID(uid)?.toDTO()
			else null
		}
	}


	suspend fun clear(){
		dao.clear()
	}

}