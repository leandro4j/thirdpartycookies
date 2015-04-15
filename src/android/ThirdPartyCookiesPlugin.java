package br.com.sankhya.cordova.thirdpartycookies;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;

public class ThirdPartyCookiesPlugin extends CordovaPlugin {

    @Override
    public void onNewIntent(Intent intent) {
    	Log.i("WebIntent", "Passando no onNewIntent force Extra = " + intent.toString());
    	//Forçamos atualização dos parâmetros da Itent (Intent Extra)
    	((CordovaActivity)this.cordova.getActivity()).setIntent(intent);
    }
    
    @TargetApi(12)
    private void allowThirdPartyCookieCompat() {
    	CookieManager.setAcceptFileSchemeCookies(true); 
    }
    
	@TargetApi(21)
	private void allowThirdPartyCookie(CordovaWebView webView) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webView,true);
	}
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    	 //Allow third party cookies for Android Lollipop
        if (Build.VERSION.SDK_INT >= 21) {
        	allowThirdPartyCookie(webView);
        }else if (Build.VERSION.SDK_INT >= 12) {
        	allowThirdPartyCookieCompat();
        }
    }
}
