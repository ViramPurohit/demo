package shaadi.com.api

import android.os.AsyncTask
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.reactivestreams.Publisher
import shaadi.com.base.BaseRepository
import shaadi.com.db.ShaadiUserDao
import shaadi.com.db.ShaadiUsers
import shaadi.com.model.UserListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShaadiRepository @Inject constructor(

    private val shaadiUserDao: ShaadiUserDao,
    private val apiService: ApiService
) : BaseRepository() {

    fun getShaadiUserCount(): Flowable<Int> {
        return shaadiUserDao.getUserCount()
    }



    fun getAll(): Flowable<List<ShaadiUsers>> {
        return shaadiUserDao.getAll()
    }

/*
* Add users
* */
    fun insertUser(results: List<ShaadiUsers>) {
     shaadiUserDao.insertUsers(results)
}

    fun fetchUserDetails(size: Int): Observable<UserListResponse> {
        return Observable.create { emitter ->
            apiService.productList(size)
                .enqueue(getBaseCallback<UserListResponse,
                        UserListResponse>(emitter) { response ->
                    emitter.onNext(response)
                })
        }
    }



    fun updateUsers(shaadiUsers: ShaadiUsers) {
        return shaadiUserDao.updateUsers(shaadiUsers)
    }

}