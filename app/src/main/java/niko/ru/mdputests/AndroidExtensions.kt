package niko.ru.mdputests

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun Context.getAssetData(file: String): String {
    resources.assets.open(file).bufferedReader().use {
        return it.readLine();
    }
}

fun AppCompatActivity.showFragment(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit();
}

fun Fragment.showFragment(id: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit();
}

fun AppCompatActivity.showFragmentWithoutBackStack(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(id, fragment).commit();
}



fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show();
}
