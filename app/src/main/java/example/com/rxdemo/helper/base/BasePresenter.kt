package example.com.rxdemo.helper.base


interface BasePresenter<in V : BaseView>
{
    fun attachView(view: V)

    fun detachView()
}