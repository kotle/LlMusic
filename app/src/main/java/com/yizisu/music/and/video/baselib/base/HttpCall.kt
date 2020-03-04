package com.yizisu.music.and.video.baselib.base

import android.widget.Toast
import com.yizisu.basemvvm.gson
import com.yizisu.basemvvm.mvvm.mvvm_helper.LiveBean
import com.yizisu.basemvvm.mvvm.mvvm_helper.fail
import com.yizisu.basemvvm.mvvm.mvvm_helper.start
import com.yizisu.basemvvm.mvvm.mvvm_helper.success
import com.yizisu.basemvvm.okHttpClient
import com.yizisu.basemvvm.utils.createFormBody
import com.yizisu.basemvvm.utils.toast
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

const val API_BASE_URL_NETEASE = ""
/**
 * 发送网络请求
 */
fun String.sendNeteaseHttp(
    params: MutableMap<String, String>,
    isGet: Boolean = false
): okhttp3.Call {
    val request = Request.Builder()
    var url = API_BASE_URL_NETEASE.trimEnd('/') + "/" + this.trimStart('/')
    if (isGet) {
        url += "?"
        for ((key, value) in params) {
            url += "${key}=${value}&"
        }
    } else {
        request.post(params.createFormBody())
    }
    request.url(url.trimEnd('&'))
    return okHttpClient.newCall(request.build())
}

/**
 * 发送网络请求
 */
fun String.sendHttp(params: MutableMap<String, String>, isGet: Boolean = false): okhttp3.Call {
    val request = Request.Builder()
    var url = this
    if (isGet) {
        url += "?"
        for ((key, value) in params) {
            url += "${key}=${value}&"
        }
    } else {
        request.post(params.createFormBody())
    }
    request.url(url.trimEnd('&'))
    return okHttpClient.newCall(request.build())
}

/**
 * 请求数据没有成功
 */
class NoSuccessException(msg: String?) : IOException(msg)

/**
 * 提供一个快速创建SimpleLiveDataCall的方法
 */
inline fun <reified P> LiveBean<P>.createRetrofitCall(isToastError: Boolean = true/*是否toast出错误*/): Retrofit2Call<P> {
    return object : Retrofit2Call<P>(this) {
        override fun onError(t: Throwable, errorCode: Int?) {
            super.onError(t, errorCode)
            if (isToastError) {
                errorMsg.toast(Toast.LENGTH_LONG)
            }
        }
    }
}

/**
 * retrofit请求回调
 */

abstract class Retrofit2Call<T>(private val data: LiveBean<T>) : retrofit2.Callback<T> {
    init {
        data.start()
    }


    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t, null)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() != 200) {
            onError(
                NoSuccessException("httpError:code-${response.code()},message:${response.message()}"),
                response.code()
            )
            return
        }
        val result = response.body()
        if (result == null) {
            onError(
                NullPointerException("HTTP Body Is Null:${response.errorBody()?.string()}"),
                null
            )
            return
        }
        onSuccess(result)
    }

    open fun onSuccess(result: T) {
        data.success(result)
    }

    open fun onError(t: Throwable, errorCode: Int?) {
        t.printStackTrace()
        data.fail(t.message ?: "unKnow Error", errorCode)
    }

}

/**
 * 提供一个快速创建SimpleLiveDataCall的方法
 */
inline fun <reified P> LiveBean<P>.createOkHttpCall(isToastError: Boolean = true/*是否toast出错误*/): okhttp3.Callback {
    return object : OkHttpCall<P>(P::class.java, this) {
        override fun onError(t: Throwable, errorCode: Int?) {
            super.onError(t, errorCode)
            if (isToastError) {
                errorMsg.toast(Toast.LENGTH_LONG)
            }
        }
    }
}

/**
 * OkHttp请求回调
 */
abstract class OkHttpCall<T>(private val cls: Class<T>, private val data: LiveBean<T>) :
    okhttp3.Callback {
    init {
        data.start()
    }

    override fun onFailure(call: okhttp3.Call, t: IOException) {
        onError(t, null)
    }

    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
        if (response.code() != 200) {
            onError(
                NoSuccessException("httpError:code:${response.code()},message:${response.message()}"),
                response.code()
            )
            return
        }
        val result = response.body()
        try {
            val bodyStr = result?.string()
            if (bodyStr.isNullOrBlank()) {
                onError(IOException("HTTP Body Is Null:${response.message()}"), null)
            } else {
                //简单判断是否是json字符串
                if (bodyStr.startsWith('{') || bodyStr.startsWith('[')) {
                    val bo = gson.fromJson<T>(bodyStr, cls)
                    onSuccess(bo)
                } else {
                    onError(IOException("HTTP Body Is Not Json:${bodyStr}"), null)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            onError(IOException(e.message), null)
        }
    }

    open fun onSuccess(result: T) {
        data.success(result)
    }

    open fun onError(t: Throwable, errorCode: Int?) {
        t.printStackTrace()
        data.fail(t.message ?: "unKnow Error", errorCode)
    }

}
