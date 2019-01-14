package br.com.android.aline.iddog.activity.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import br.com.android.aline.iddog.R
import br.com.android.aline.iddog.activity.login.LoginActivity
import br.com.android.aline.iddog.enum.EnumBrand
import br.com.android.aline.iddog.fragment.ListDogFragment
import br.com.android.aline.iddog.utils.PreferenceHelper
import kotlinx.android.synthetic.main.activity_home.*

class HomeDogActivity : AppCompatActivity(), IHomeDogView {

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.navigation_husky ->{
                openFragment(EnumBrand.HUSKY.brand)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hound ->{
                openFragment(EnumBrand.HOUND.brand)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_pug ->{
                openFragment(EnumBrand.PUG.brand)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_labrador ->{
                openFragment(EnumBrand.LABRADOR.brand)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        configToolbar()
        openDefaultFragment()
        navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun openDefaultFragment() {
        openFragment(EnumBrand.HUSKY.brand)
    }

    override fun configToolbar() {
        setSupportActionBar(toolbar_home as Toolbar)
        supportActionBar?.title = getString(R.string.title_toolbar_home)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_out ->
                alertDeleteLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun alertDeleteLogin() {
        AlertDialog.Builder(this)
                .setMessage("Tem certeza que deseja sair? Os dados de login serão excluídos.")
                .setPositiveButton("Sim, sair", { _, _ ->
                    deleteTokenUser()
                }).setNegativeButton("Cancelar", { dialog, _ ->
                    dialog.dismiss()
                }).setCancelable(false).show()
    }

    override fun deleteTokenUser() {
        val prefs = PreferenceHelper.customPreference(applicationContext, PreferenceHelper.TOKEN_USER)
        prefs.contains(PreferenceHelper.TOKEN_USER).let {
            prefs.edit().clear().apply()
            returnLogin()
        }
    }

    override fun returnLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun openFragment(brand : String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDogFragment.newInstance(brand))
                .commit()
    }

}
