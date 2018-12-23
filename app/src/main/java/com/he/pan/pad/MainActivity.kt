package com.he.pan.pad

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dialog: SelectAppDialog by lazy {
        SelectAppDialog.newInstance()
    }

    companion object {
        val URL = "http://xn--fiqw8jpsekxqt9xpkh1qw.xn--kput3i/?from=singlemessage"
//        val URL = "https://www.baidu.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVebView()
        button.setOnClickListener {
            dialog.show(supportFragmentManager, "")
        }
        button.setOnLongClickListener {
            goSystemLauncher()
            return@setOnLongClickListener true
        }
        refresh.setOnRefreshListener {
            initVebView()
        }
    }

    private fun initVebView() {

        refresh.isRefreshing = false
        var set = webView.settings.apply {
            //            setSupportZoom(true)
//            builtInZoomControls = true
            // 全屏
            useWideViewPort = true
//            loadWithOverviewMode = true
            //响应点击
            javaScriptEnabled = true

            // 解决字体变大导致失真
            textZoom = 100
//            javaScriptCanOpenWindowsAutomatically = true
        }

//        webView.webViewClient= object :WebViewClient(){
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                view.loadUrl(request.url)
//                return super.shouldOverrideUrlLoading(view, request)
//            }
//        }
        webView.loadUrl(URL)

    }

    //屏蔽返回键
    override fun onBackPressed() {
//        super.onBackPressed()
    }


    fun goSystemLauncher() {
//        var pack = packageManager
//        var myi = Intent(Intent.ACTION_MAIN, null)
//        myi.addCategory(Intent.CATEGORY_HOME)
//        myi.addCategory(Intent.CATEGORY_DEFAULT)
//        var apps = pack.queryIntentActivities(myi, PackageManager.MATCH_ALL)
//        apps.forEach {
//            println(it.activityInfo.packageName+"  "+it.activityInfo.name)
//        }

        var compant = ComponentName("com.android.launcher3", "com.android.launcher3.Launcher")
        var myintent = Intent().apply {
            component = compant
        }
        startActivity(myintent)
        finish()
    }
}
