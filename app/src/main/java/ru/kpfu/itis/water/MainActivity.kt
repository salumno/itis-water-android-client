package ru.kpfu.itis.water

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val loginManager by lazy {
        LoginManager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun handleLogInButton(view: View) {
        val login = loginEditText.text.toString()
        val password = passwordEditText.text.toString()
        loginManager.logIn(login, password)
                .subscribeOn(Schedulers.io())
                .subscribe(
                { postLoginForm ->
                    val intent = Intent(this, TicketActivity::class.java)
                    intent.putExtra("userId", postLoginForm.userId)
                    startActivity(intent)
                },
                {
                    Snackbar.make(view, "Incorrect login or password", Snackbar.LENGTH_LONG).show()
                }
        )
    }
}
