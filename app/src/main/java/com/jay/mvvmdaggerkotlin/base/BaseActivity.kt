package  com.jay.mvvmdaggerkotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    //extension property
    //    val Activity.app: BaseApplication
    //    get() = application as BaseApplication

    //val component by lazy { app.component.inject(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((applicationContext as BaseApplication).component.inject(this))

        //app.component.inject(this)

    }
}