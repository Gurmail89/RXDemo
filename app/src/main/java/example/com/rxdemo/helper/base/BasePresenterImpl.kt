package example.com.rxdemo.helper.base

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    private var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    protected fun getView(): V? = view
}